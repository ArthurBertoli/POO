package Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dados.Gasto;

/** Acesso à tabela gasto. */
public class GastoDAO {

    private final Connection conexao;

    public GastoDAO(Connection conexao) {
        this.conexao = conexao;
    }

    /** Insere o gasto e guarda no objeto o id gerado pelo banco. */
    public void inserir(Gasto gasto) throws SQLException {
        String sql = "INSERT INTO gasto (usuario_cpf, nome, data, descricao, valor, categoria, mes)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id";
        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            preencher(statement, gasto);
            try (ResultSet resultado = statement.executeQuery()) {
                if (resultado.next()) {
                    gasto.setId(resultado.getInt(1));
                }
            }
        }
    }

    public void atualizar(Gasto gasto) throws SQLException {
        String sql = "UPDATE gasto SET usuario_cpf = ?, nome = ?, data = ?, descricao = ?, valor = ?,"
                + " categoria = ?, mes = ? WHERE id = ?";
        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            preencher(statement, gasto);
            statement.setInt(8, gasto.getId());
            statement.executeUpdate();
        }
    }

    public void remover(Gasto gasto) throws SQLException {
        try (PreparedStatement statement = conexao.prepareStatement("DELETE FROM gasto WHERE id = ?")) {
            statement.setInt(1, gasto.getId());
            statement.executeUpdate();
        }
    }

    public List<Gasto> listar() throws SQLException {
        List<Gasto> gastos = new ArrayList<>();
        String sql = "SELECT id, usuario_cpf, nome, data, descricao, valor, categoria FROM gasto ORDER BY data, id";
        try (PreparedStatement statement = conexao.prepareStatement(sql);
             ResultSet resultado = statement.executeQuery()) {
            while (resultado.next()) {
                Gasto gasto = new Gasto(
                        resultado.getString("nome"),
                        new Date(resultado.getDate("data").getTime()),
                        resultado.getString("descricao"),
                        resultado.getDouble("valor"),
                        resultado.getString("categoria"));
                gasto.setId(resultado.getInt("id"));
                gasto.setUsuarioCpf(resultado.getString("usuario_cpf"));
                gastos.add(gasto);
            }
        }
        return gastos;
    }

    private void preencher(PreparedStatement statement, Gasto gasto) throws SQLException {
        statement.setString(1, gasto.getUsuarioCpf());
        statement.setString(2, gasto.getNome());
        statement.setDate(3, new java.sql.Date(gasto.getData().getTime()));
        statement.setString(4, gasto.getDescricao());
        statement.setDouble(5, gasto.getValor());
        statement.setString(6, gasto.getCategoria());
        statement.setString(7, gasto.getMonth());
    }
}
