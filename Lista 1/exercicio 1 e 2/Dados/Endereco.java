package Dados;

public class Endereco {

    private String cep;
    private String complemento;
    private String pais;

    public Endereco() {
    }

    public String getCep() {
        return this.cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return this.complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getPais() {
        return this.pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Endereco(String cep, String complemento) {
        this.cep = cep;
        this.complemento = complemento;
    }

}
