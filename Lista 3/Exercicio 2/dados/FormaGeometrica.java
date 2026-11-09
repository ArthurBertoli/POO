package dados;

public abstract class FormaGeometrica {

    protected int medida1;
    protected int medida2;

    public FormaGeometrica(int medida1, int medida2) {
        this.medida1 = medida1;
        this.medida2 = medida2;
    }

    public abstract int calculaArea();

    public abstract int calculaPerimetro();
}