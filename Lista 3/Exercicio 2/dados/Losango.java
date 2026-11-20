package dados;

public class Losango extends FormaGeometrica {

    private int D;
    private int d;

    public Losango(int D, int d) {
        super(D, d);
        this.D = D;
        this.d = d;
    }

    public void setD(int valor) {
        D = valor;
    }

    public void setd(int valor) {
        d = valor;
    }

    public int calculaArea() {
        return (int) Math.round((D * d) / 2.0);
    }

    // Lado = hipotenusa do triângulo formado pelas metades das diagonais; perímetro = 4 * lado.
    public int calculaPerimetro() {
        double lado = Math.sqrt((D / 2.0) * (D / 2.0) + (d / 2.0) * (d / 2.0));
        return (int) Math.round(4 * lado);
    }

    public String toString() {
        return "Losango [D: " + D + ", d: " + d + ", Área: " + calculaArea() + ", Perímetro: " + calculaPerimetro()
                + "]";
    }
}