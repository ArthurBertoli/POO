package dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Usuario {
    private String cpf;
    private String nome;
    private String email;
    private String senha;

    public Usuario(String cpf, String nome, String email, String senha) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "CPF: " + cpf + "\n" +
                "Nome: " + nome + "\n" +
                "Email: " + email;
    }
    
    public void salvarUsuario(Connection conexao) {
        String sql = "INSERT INTO usuario (cpf, nome, email, senha) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setString(1, this.getCpf());
            statement.setString(2, this.getNome());
            statement.setString(3, this.getEmail());
            statement.setString(4, this.getSenha());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar o usuário no banco de dados.", e);
        }
    }
}
