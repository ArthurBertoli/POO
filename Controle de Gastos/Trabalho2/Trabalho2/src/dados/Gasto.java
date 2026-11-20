package dados;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Gasto {
    private static final Locale PT_BR = Locale.forLanguageTag("pt-BR");

    /** Identificador no banco de dados (0 enquanto o gasto não foi gravado no banco). */
    private int id;
    /** CPF do usuário dono do gasto. */
    private String usuarioCpf;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuarioCpf() {
        return usuarioCpf;
    }

    public void setUsuarioCpf(String usuarioCpf) {
        this.usuarioCpf = usuarioCpf;
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

    /** Nome do mês da data do gasto, em português (ex.: "março"). */
    private static String calcularMonth(Date data) {
        if (data == null) {
            return null;
        }
        return new SimpleDateFormat("MMMM", PT_BR).format(data);
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    @Override
    public String toString() {
        return nome + " (" + new SimpleDateFormat("dd/MM/yyyy").format(data) + ", " + categoria + ", R$ "
                + String.format(PT_BR, "%.2f", valor) + ")";
    }
}
