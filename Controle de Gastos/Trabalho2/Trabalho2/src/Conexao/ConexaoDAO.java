package Conexao;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.CodeSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import Exceptions.ConexaoBancoDeDadosException;
import Exceptions.FechamentoConexaoException;

/**
 * Lê a configuração do banco (arquivo db.properties) e abre a conexão com o PostgreSQL.
 *
 * O arquivo é procurado, nesta ordem:
 * 1. no caminho informado pela propriedade de sistema -Ddb.config=caminho/do/arquivo;
 * 2. na pasta de trabalho atual (db.properties);
 * 3. na pasta do projeto, isto é, a pasta acima de onde estão as classes compiladas (bin/../db.properties).
 */
public class ConexaoDAO {

    public static final String NOME_ARQUIVO = "db.properties";
    private static final String DRIVER_POSTGRES = "org.postgresql.Driver";
    private static final int TIMEOUT_PADRAO_SEGUNDOS = 5;

    /** Comandos de criação das tabelas; os mesmos do arquivo trab3.sql. */
    private static final String[] DDL = {
        "CREATE TABLE IF NOT EXISTS usuarios ("
            + " id serial PRIMARY KEY,"
            + " cpf varchar(11) NOT NULL UNIQUE,"
            + " nome varchar(100) NOT NULL,"
            + " email varchar(100),"
            + " senha varchar(100) NOT NULL)",
        "CREATE TABLE IF NOT EXISTS gasto ("
            + " id serial PRIMARY KEY,"
            + " usuario_cpf varchar(11) NOT NULL REFERENCES usuarios (cpf) ON DELETE CASCADE,"
            + " nome varchar(100) NOT NULL,"
            + " data date NOT NULL,"
            + " descricao varchar(255) NOT NULL,"
            + " valor double precision NOT NULL CHECK (valor > 0),"
            + " categoria varchar(50) NOT NULL,"
            + " mes varchar(20))"
    };

    private final Properties config;
    private final Path arquivoConfig;

    public ConexaoDAO() {
        this(localizarArquivoConfig());
    }

    public ConexaoDAO(Path arquivoConfig) {
        this.arquivoConfig = arquivoConfig;
        this.config = carregar(arquivoConfig);
    }

    /** Indica se o arquivo db.properties foi encontrado e lido. */
    public boolean isConfiguracaoEncontrada() {
        return config != null;
    }

    public Path getArquivoConfig() {
        return arquivoConfig;
    }

    /** db.enabled=false desliga o banco e o programa usa apenas a memória. */
    public boolean isHabilitado() {
        return config != null && !"false".equalsIgnoreCase(propriedade("db.enabled", "true"));
    }

    public String getUrl() {
        return propriedade("db.url", "");
    }

    /**
     * Abre uma nova conexão com o banco configurado.
     *
     * @throws ConexaoBancoDeDadosException se o arquivo não existir, o driver JDBC não estiver
     *         no classpath ou o banco recusar a conexão.
     */
    public Connection abrirConexao() {
        if (config == null) {
            throw new ConexaoBancoDeDadosException("Arquivo " + NOME_ARQUIVO + " não encontrado.");
        }
        String url = getUrl();
        if (url.isEmpty()) {
            throw new ConexaoBancoDeDadosException("A propriedade db.url não foi preenchida em " + arquivoConfig + ".");
        }

        try {
            Class.forName(DRIVER_POSTGRES);
        } catch (ClassNotFoundException e) {
            throw new ConexaoBancoDeDadosException(
                    "Driver JDBC do PostgreSQL (" + DRIVER_POSTGRES + ") não encontrado no classpath. "
                    + "Coloque o arquivo lib/postgresql-<versão>.jar no classpath.", e);
        }

        int timeout = lerTimeout();
        Properties propriedadesConexao = new Properties();
        propriedadesConexao.setProperty("user", propriedade("db.user", ""));
        propriedadesConexao.setProperty("password", propriedade("db.password", ""));
        propriedadesConexao.setProperty("connectTimeout", String.valueOf(timeout));
        propriedadesConexao.setProperty("loginTimeout", String.valueOf(timeout));
        DriverManager.setLoginTimeout(timeout);

        try {
            return DriverManager.getConnection(url, propriedadesConexao);
        } catch (SQLException e) {
            throw new ConexaoBancoDeDadosException(
                    "Não foi possível conectar em " + url + ": " + e.getMessage(), e);
        }
    }

    /** Cria as tabelas usadas pelo programa, caso ainda não existam. */
    public static void criarTabelasSeNecessario(Connection conexao) throws SQLException {
        try (Statement statement = conexao.createStatement()) {
            for (String comando : DDL) {
                statement.execute(comando);
            }
        }
    }

    public static void fecharConexao(Connection conexao) {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        } catch (SQLException e) {
            throw new FechamentoConexaoException("Erro ao fechar a conexão com o banco de dados.", e);
        }
    }

    /** Procura o db.properties nos locais descritos na documentação da classe. */
    public static Path localizarArquivoConfig() {
        String informado = System.getProperty("db.config");
        if (informado != null && !informado.trim().isEmpty()) {
            return Paths.get(informado.trim());
        }

        Path naPastaAtual = Paths.get(NOME_ARQUIVO).toAbsolutePath();
        if (Files.isRegularFile(naPastaAtual)) {
            return naPastaAtual;
        }

        try {
            CodeSource origem = ConexaoDAO.class.getProtectionDomain().getCodeSource();
            if (origem != null) {
                Path pastaClasses = Paths.get(origem.getLocation().toURI());
                Path pastaProjeto = pastaClasses.getParent();
                if (pastaProjeto != null) {
                    Path noProjeto = pastaProjeto.resolve(NOME_ARQUIVO);
                    if (Files.isRegularFile(noProjeto)) {
                        return noProjeto;
                    }
                }
            }
        } catch (URISyntaxException | SecurityException | IllegalArgumentException e) {
            // Sem acesso à origem das classes: segue sem arquivo de configuração.
        }
        return null;
    }

    private static Properties carregar(Path arquivo) {
        if (arquivo == null || !Files.isRegularFile(arquivo)) {
            return null;
        }
        Properties propriedades = new Properties();
        try (InputStream entrada = Files.newInputStream(arquivo)) {
            propriedades.load(entrada);
            return propriedades;
        } catch (IOException e) {
            return null;
        }
    }

    private String propriedade(String chave, String padrao) {
        if (config == null) {
            return padrao;
        }
        String valor = config.getProperty(chave);
        return valor == null ? padrao : valor.trim();
    }

    private int lerTimeout() {
        try {
            int valor = Integer.parseInt(propriedade("db.timeout", String.valueOf(TIMEOUT_PADRAO_SEGUNDOS)));
            return valor > 0 ? valor : TIMEOUT_PADRAO_SEGUNDOS;
        } catch (NumberFormatException e) {
            return TIMEOUT_PADRAO_SEGUNDOS;
        }
    }
}
