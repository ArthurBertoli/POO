package negocio;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/*import Exceptions.ConexaoBancoDeDadosException;
import Exceptions.FechamentoConexaoException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;*/
import dados.Gasto;
import dados.Usuario;

public class Sistema {
    public List<Usuario> usuarios;
    private List<Gasto> gastos;
    private List<String> tiposDeGastos;
    //private Connection conexao;

    public Sistema() {
        this.usuarios = new ArrayList<>();
        this.gastos = new ArrayList<>();
        this.tiposDeGastos = new ArrayList<>();
        //this.conexao = obterConexaoDAO();
    }

    public boolean cadastrarUsuario(String cpf, String nome, String email, String senha) {
        Usuario usuario = new Usuario(cpf, nome, email, senha);
        usuarios.add(usuario);
        
        //usuario.salvarUsuario(conexao);

        return true;
    }

    public boolean autenticarUsuario(String cpf, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getCpf().equals(cpf) && usuario.getSenha().equals(senha)) {
                return true;
            }
        }
        return false;
    }

    public void cadastrarGasto(Gasto gasto, String tipo) {
        if (!tiposDeGastos.contains(tipo)) {
            cadastrarString(tipo);
        }

        gasto.setCategoria(tipo);
        gastos.add(gasto);
    }

    public void cadastrarString(String tipo) {
        if (!tiposDeGastos.contains(tipo)) {
            tiposDeGastos.add(tipo);
        }
    }

    public List<Gasto> filtrarGastosPorMes(int mes) {
        List<Gasto> gastosFiltrados = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM");

        for (Gasto gasto : gastos) {
            String dataString = dateFormat.format(gasto.getData());
			int mesDoGasto = Integer.parseInt(dataString);
			if (mesDoGasto == mes) {
			    gastosFiltrados.add(gasto);
			}
        }

        return gastosFiltrados;
    }


    public List<Gasto> filtrarGastosPorCategoria(String categoria) {
        List<Gasto> gastosFiltrados = new ArrayList<>();

        for (Gasto gasto : gastos) {
            if (gasto.getCategoria().equalsIgnoreCase(categoria)) {
                gastosFiltrados.add(gasto);
            }
        }

        return gastosFiltrados;
    }

    public void removerGasto(Gasto gasto) {
        if (gastos.contains(gasto)) {
            gastos.remove(gasto);
        }
    }

    public void alterarGasto(Gasto gasto) {
        for (int i = 0; i < gastos.size(); i++) {
            Gasto gastoAtual = gastos.get(i);
            if (gastoAtual.equals(gasto)) {
                gastos.set(i, gasto);
                return;
            }
        }
    }

    public Gasto buscarGastoPeloNome(String nomeGasto) {
        for (Gasto gasto : gastos) {
            if (gasto.getNome().equalsIgnoreCase(nomeGasto)) {
                return gasto;
            }
        }
        return null;
    }
    
	public List<Gasto> obterGastos() {
		return gastos;
	}
	
	/*public void salvarGastoNoBanco(Gasto gasto) {
        gasto.salvarGasto(conexao);
    }
	
	public Connection obterConexaoDAO() {
        try {
            Class.forName("org.postgresql.Driver");   
            String url = "jdbc:postgresql://localhost:5432/trab3?usuario=postgre&password=udesc";
            //String usuario = "postgres";
           // String senha = "udesc";
            return DriverManager.getConnection(url);
        } catch (ClassNotFoundException | SQLException e) {
        	throw new ConexaoBancoDeDadosException("Erro ao obter a conexão com o banco de dados. Verifique as configurações e tente novamente.", e);
        }
    }
	
	public void fecharConexao() {
	    try {
	        if (conexao != null && !conexao.isClosed()) {
	            conexao.close();
	        }
	    } catch (SQLException e) {
	        throw new FechamentoConexaoException("Erro ao fechar a conexão com o banco de dados.", e);
	    }
	}*/


}

