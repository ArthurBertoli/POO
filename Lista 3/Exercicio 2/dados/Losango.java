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
        return ((D * d) / 2);
    }

    public int calculaPerimetro() {
        return ((4 * ((D * d) / 4) + ((d * d) / 4)));
    }

    public String toString() {
        return "Losango [D: " + D + ", d: " + d + ", Área: " + calculaArea() + ", Perímetro: " + calculaPerimetro()
                + "]";
    }
}