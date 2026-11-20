package negocio;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import Conexao.ConexaoDAO;
import Conexao.GastoDAO;
import Conexao.UsuarioDAO;
import Exceptions.ConexaoBancoDeDadosException;
import Exceptions.FechamentoConexaoException;
import dados.Gasto;
import dados.Usuario;

/**
 * Regras de negócio do Controle de Gastos.
 *
 * Os dados sempre ficam em memória (listas). Quando o PostgreSQL está configurado em db.properties
 * e o driver JDBC está no classpath, os dados também são lidos e gravados no banco. Se o banco não
 * estiver disponível, o sistema continua funcionando só em memória e guarda um aviso para a interface
 * mostrar ao usuário (veja {@link #consumirAvisoPersistencia()}).
 */
public class Sistema {

    /** Categorias de gasto usadas em todas as telas. */
    public static final String[] CATEGORIAS = {"Comida", "Lazer", "Educação", "Saúde", "Transporte", "Outros"};

    private static final int TAMANHO_MAXIMO_TEXTO = 100;
    private static final int TAMANHO_MAXIMO_DESCRICAO = 255;

    private final List<Usuario> usuarios;
    private final List<Gasto> gastos;
    private final List<String> tiposDeGastos;
    private Usuario usuarioLogado;

    private Connection conexao;
    private UsuarioDAO usuarioDAO;
    private GastoDAO gastoDAO;
    private String avisoPersistencia;

    /** Usa o banco configurado em db.properties, se houver; senão, funciona em memória. */
    public Sistema() {
        this(new ConexaoDAO());
    }

    /**
     * @param conexaoDAO configuração do banco; {@code null} cria um sistema somente em memória,
     *                   sem nenhum aviso.
     */
    public Sistema(ConexaoDAO conexaoDAO) {
        this.usuarios = new ArrayList<>();
        this.gastos = new ArrayList<>();
        this.tiposDeGastos = new ArrayList<>();
        iniciarPersistencia(conexaoDAO);
    }

    /** Cria um sistema que guarda os dados apenas em memória. */
    public static Sistema emMemoria() {
        return new Sistema(null);
    }

    // ------------------------------------------------------------------ persistência

    private void iniciarPersistencia(ConexaoDAO conexaoDAO) {
        if (conexaoDAO == null) {
            return;
        }
        if (!conexaoDAO.isConfiguracaoEncontrada()) {
            String arquivo = conexaoDAO.getArquivoConfig() != null
                    ? conexaoDAO.getArquivoConfig().toString() : ConexaoDAO.NOME_ARQUIVO;
            avisoPersistencia = "Arquivo de configuração " + arquivo + " não encontrado.\n"
                    + "O programa vai funcionar em modo memória: os dados serão perdidos ao fechar.";
            return;
        }
        if (!conexaoDAO.isHabilitado()) {
            return; // db.enabled=false: o usuário escolheu o modo memória.
        }

        try {
            conexao = conexaoDAO.abrirConexao();
            ConexaoDAO.criarTabelasSeNecessario(conexao);
            usuarioDAO = new UsuarioDAO(conexao);
            gastoDAO = new GastoDAO(conexao);
            usuarios.addAll(usuarioDAO.listar());
            for (Gasto gasto : gastoDAO.listar()) {
                gastos.add(gasto);
                cadastrarString(gasto.getCategoria());
            }
            Runtime.getRuntime().addShutdownHook(new Thread(this::fecharConexaoSilenciosamente));
        } catch (ConexaoBancoDeDadosException | SQLException e) {
            usuarios.clear();
            gastos.clear();
            desativarBanco("Não foi possível usar o banco de dados PostgreSQL.\n" + e.getMessage());
        }
    }

    private void desativarBanco(String motivo) {
        fecharConexaoSilenciosamente();
        conexao = null;
        usuarioDAO = null;
        gastoDAO = null;
        avisoPersistencia = motivo + "\nO programa vai continuar em modo memória: "
                + "os dados novos serão perdidos ao fechar.";
    }

    private void falhaNoBanco(String operacao, SQLException e) {
        desativarBanco("Erro ao " + operacao + " no banco de dados: " + e.getMessage());
    }

    /** {@code true} quando os dados estão sendo gravados no PostgreSQL. */
    public boolean isPersistente() {
        return conexao != null;
    }

    /** Devolve (e limpa) o último aviso sobre o banco de dados, ou {@code null} se não houver. */
    public String consumirAvisoPersistencia() {
        String aviso = avisoPersistencia;
        avisoPersistencia = null;
        return aviso;
    }

    public void fecharConexao() {
        try {
            ConexaoDAO.fecharConexao(conexao);
        } finally {
            conexao = null;
            usuarioDAO = null;
            gastoDAO = null;
        }
    }

    private void fecharConexaoSilenciosamente() {
        try {
            ConexaoDAO.fecharConexao(conexao);
        } catch (FechamentoConexaoException e) {
            // Nada a fazer: o programa está encerrando ou já trocou para o modo memória.
        }
    }

    // ------------------------------------------------------------------ usuários

    /**
     * Cadastra um usuário.
     *
     * @throws IllegalArgumentException com a mensagem a ser mostrada se algum dado for inválido.
     */
    public boolean cadastrarUsuario(String cpf, String nome, String email, String senha) {
        String cpfNormalizado = normalizarCpf(cpf);
        nome = texto(nome);
        email = texto(email);
        senha = senha == null ? "" : senha;

        if (cpfNormalizado.length() != 11) {
            throw new IllegalArgumentException("CPF inválido. Informe os 11 dígitos do CPF.");
        }
        if (nome.isEmpty() || senha.isEmpty()) {
            throw new IllegalArgumentException("Preencha pelo menos CPF, nome e senha.");
        }
        validarTamanho("Nome", nome, TAMANHO_MAXIMO_TEXTO);
        validarTamanho("E-mail", email, TAMANHO_MAXIMO_TEXTO);
        validarTamanho("Senha", senha, TAMANHO_MAXIMO_TEXTO);
        if (buscarUsuario(cpfNormalizado) != null) {
            throw new IllegalArgumentException("Já existe um usuário cadastrado com este CPF.");
        }

        Usuario usuario = new Usuario(cpfNormalizado, nome, email, senha);
        if (usuarioDAO != null) {
            try {
                usuarioDAO.inserir(usuario);
            } catch (SQLException e) {
                falhaNoBanco("salvar o usuário", e);
            }
        }
        usuarios.add(usuario);
        return true;
    }

    /** Confere CPF e senha; se estiverem certos, o usuário passa a ser o usuário logado. */
    public boolean autenticarUsuario(String cpf, String senha) {
        Usuario usuario = buscarUsuario(normalizarCpf(cpf));
        if (usuario != null && usuario.getSenha().equals(senha)) {
            usuarioLogado = usuario;
            return true;
        }
        return false;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public List<Usuario> getUsuarios() {
        return new ArrayList<>(usuarios);
    }

    private Usuario buscarUsuario(String cpfNormalizado) {
        for (Usuario usuario : usuarios) {
            if (usuario.getCpf().equals(cpfNormalizado)) {
                return usuario;
            }
        }
        return null;
    }

    private static String normalizarCpf(String cpf) {
        return cpf == null ? "" : cpf.replaceAll("\\D", "");
    }

    // ------------------------------------------------------------------ gastos

    /**
     * Cadastra um gasto para o usuário logado.
     *
     * @throws IllegalArgumentException se algum dado do gasto for inválido.
     * @throws IllegalStateException se nenhum usuário estiver logado.
     */
    public void cadastrarGasto(Gasto gasto, String tipo) {
        if (usuarioLogado == null) {
            throw new IllegalStateException("Faça login antes de cadastrar gastos.");
        }
        validarGasto(gasto.getNome(), gasto.getData(), gasto.getDescricao(), gasto.getValor(), tipo);

        cadastrarString(tipo);
        gasto.setCategoria(tipo);
        gasto.setUsuarioCpf(usuarioLogado.getCpf());

        if (gastoDAO != null) {
            try {
                gastoDAO.inserir(gasto);
            } catch (SQLException e) {
                falhaNoBanco("salvar o gasto", e);
            }
        }
        gastos.add(gasto);
    }

    public void cadastrarString(String tipo) {
        if (tipo != null && !tiposDeGastos.contains(tipo)) {
            tiposDeGastos.add(tipo);
        }
    }

    /** Gastos do usuário logado (cópia da lista, em ordem de cadastro). */
    public List<Gasto> obterGastos() {
        List<Gasto> doUsuario = new ArrayList<>();
        if (usuarioLogado == null) {
            return doUsuario;
        }
        for (Gasto gasto : gastos) {
            if (usuarioLogado.getCpf().equals(gasto.getUsuarioCpf())) {
                doUsuario.add(gasto);
            }
        }
        return doUsuario;
    }

    /** @param mes de 1 (janeiro) a 12 (dezembro). */
    public List<Gasto> filtrarGastosPorMes(int mes) {
        return filtrarGastos(mes, null);
    }

    public List<Gasto> filtrarGastosPorCategoria(String categoria) {
        return filtrarGastos(null, categoria);
    }

    /**
     * Filtra os gastos do usuário logado.
     *
     * @param mes       de 1 a 12, ou {@code null} para todos os meses.
     * @param categoria categoria, ou {@code null} para todas.
     */
    public List<Gasto> filtrarGastos(Integer mes, String categoria) {
        List<Gasto> gastosFiltrados = new ArrayList<>();
        Calendar calendario = Calendar.getInstance();

        for (Gasto gasto : obterGastos()) {
            calendario.setTime(gasto.getData());
            boolean mesConfere = mes == null || calendario.get(Calendar.MONTH) + 1 == mes;
            boolean categoriaConfere = categoria == null || categoria.equalsIgnoreCase(gasto.getCategoria());
            if (mesConfere && categoriaConfere) {
                gastosFiltrados.add(gasto);
            }
        }
        return gastosFiltrados;
    }

    public void removerGasto(Gasto gasto) {
        if (!gastos.contains(gasto)) {
            return;
        }
        if (gastoDAO != null && gasto.getId() > 0) {
            try {
                gastoDAO.remover(gasto);
            } catch (SQLException e) {
                falhaNoBanco("remover o gasto", e);
            }
        }
        gastos.remove(gasto);
    }

    /**
     * Valida os novos dados e altera o gasto (na memória e, se houver, no banco).
     *
     * @throws IllegalArgumentException se algum dado for inválido; nesse caso o gasto não é alterado.
     */
    public void alterarGasto(Gasto gasto, String nome, Date data, String descricao, double valor, String categoria) {
        validarGasto(nome, data, descricao, valor, categoria);
        gasto.setNome(nome.trim());
        gasto.setData(data);
        gasto.setDescricao(descricao.trim());
        gasto.setValor(valor);
        gasto.setCategoria(categoria);
        cadastrarString(categoria);
        alterarGasto(gasto);
    }

    /** Grava no banco o estado atual de um gasto já cadastrado. */
    public void alterarGasto(Gasto gasto) {
        if (gastoDAO != null && gastos.contains(gasto) && gasto.getId() > 0) {
            try {
                gastoDAO.atualizar(gasto);
            } catch (SQLException e) {
                falhaNoBanco("alterar o gasto", e);
            }
        }
    }

    public Gasto buscarGastoPeloNome(String nomeGasto) {
        for (Gasto gasto : obterGastos()) {
            if (gasto.getNome().equalsIgnoreCase(nomeGasto)) {
                return gasto;
            }
        }
        return null;
    }

    private void validarGasto(String nome, Date data, String descricao, double valor, String categoria) {
        if (texto(nome).isEmpty() || data == null || texto(descricao).isEmpty() || texto(categoria).isEmpty()) {
            throw new IllegalArgumentException("Por favor, preencha todos os campos obrigatórios.");
        }
        validarTamanho("Nome", nome.trim(), TAMANHO_MAXIMO_TEXTO);
        validarTamanho("Descrição", descricao.trim(), TAMANHO_MAXIMO_DESCRICAO);
        if (Double.isNaN(valor) || Double.isInfinite(valor) || valor <= 0) {
            throw new IllegalArgumentException("Valor inválido. Informe um número maior que zero.");
        }
    }

    private static void validarTamanho(String campo, String valor, int maximo) {
        if (valor.length() > maximo) {
            throw new IllegalArgumentException(campo + " deve ter no máximo " + maximo + " caracteres.");
        }
    }

    private static String texto(String valor) {
        return valor == null ? "" : valor.trim();
    }
}
