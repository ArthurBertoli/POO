package dados;

public class Circulo extends FormaGeometrica {

    private int raio;

    public Circulo(int raio) {
        super(raio, raio);
        this.raio = raio;
    }

    public void setRaio(int valor) {
        raio = valor;
    }

    public int calculaArea() {
        return (int) (3.14 * (raio * raio));
    }

    public int calculaPerimetro() {
        return (int) (3.14 * (raio + raio));
    }

    public String toString() {
        return "Circulo [Raio: " + raio + ", Área: " + calculaArea() + ", Perímetro: " + calculaPerimetro()
                + "]";
    }
}