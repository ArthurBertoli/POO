package dados;

public class TrianguloEquilatero extends FormaGeometrica {

    private int lado;

    public TrianguloEquilatero(int lado) {
        super(lado, lado);
        this.lado = lado;
    }

    public void setLado(int valor) {
        lado = valor;
    }

    public int calculaArea() {
        return (int) ((Math.sqrt(3) / 4) * (lado * lado));
    }

    public int calculaPerimetro() {
        return 3 * lado;
    }

    public String toString() {
        return "Triângulo Equilátero [Lado: " + lado + ", Área: " + calculaArea() + ", Perímetro: " + calculaPerimetro()
                + "]";
    }
}