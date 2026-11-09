package dados;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Gasto {
    private String nome;
    private Date data;
    private String descricao;
    private double valor;
    private String categoria;
    private String month;

    public Gasto(String nome, Date data, String descricao, double valor, String categoria) {
        this.nome = nome;
        this.data = data;
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
        this.setMonth(calcularMonth(data));
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
        this.setMonth(calcularMonth(data));
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    private String calcularMonth(Date data) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM");
        return dateFormat.format(data);
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
    
    public void salvarGasto(Connection conexao) {
        String sql = "INSERT INTO gasto (nome, data, descricao, valor, categoria, mes) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setString(1, this.getNome());
            statement.setDate(2, new java.sql.Date(this.getData().getTime()));
            statement.setString(3, this.getDescricao());
            statement.setDouble(4, this.getValor());
            statement.setString(5, this.getCategoria());
            statement.setString(6, this.getMonth());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar o gasto no banco de dados.", e);
        }

    }
}

