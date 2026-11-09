
import java.util.ArrayList;
import java.util.List;

public class Matriz<T> {
    private int ordem;
    private T[][] matriz;

    public Matriz(int n, int m) {
        if (n <= 0 || m <= 0) {
            throw new IllegalArgumentException("A ordem da matriz deve ser maior que zero.");
        }
        ordem = n;
        matriz = (T[][]) new Object[n][m];
    }

    public boolean set(T objeto, int i, int j) {
        if (i < 0 || i >= ordem || j < 0 || j >= matriz[0].length) {
            return false;
        }
        matriz[i][j] = objeto;
        return true;
    }

    public T get(int i, int j) {
        if (i < 0 || i >= ordem || j < 0 || j >= matriz[0].length) {
            return null;
        }
        return matriz[i][j];
    }

    public List<T> getLinha(int linha) {
        if (linha < 0 || linha >= ordem) {
            return null;
        }
        List<T> lista = new ArrayList<>();
        for (int j = 0; j < matriz[0].length; j++) {
            lista.add(matriz[linha][j]);
        }
        return lista;
    }

    public List<T> getColuna(int coluna) {
        if (coluna < 0 || coluna >= matriz[0].length) {
            return null;
        }
        List<T> lista = new ArrayList<>();
        for (int i = 0; i < ordem; i++) {
            lista.add(matriz[i][coluna]);
        }
        return lista;
    }

    public List<T> getElementosQuadrante(Quadrante quadrante) {
        List<T> lista = new ArrayList<>();
        int limiteI, limiteJ;

        switch (quadrante) {
            case PRIMEIRO:
                limiteI = ordem / 2;
                limiteJ = matriz[0].length / 2;
                break;
            case SEGUNDO:
                limiteI = ordem / 2;
                limiteJ = matriz[0].length;
                break;
            case TERCEIRO:
                limiteI = ordem;
                limiteJ = matriz[0].length / 2;
                break;
            case QUARTO:
                limiteI = ordem;
                limiteJ = matriz[0].length;
                break;
            default:
                return null;
        }

        for (int i = 0; i < limiteI; i++) {
            for (int j = 0; j < limiteJ; j++) {
                lista.add(matriz[i][j]);
            }
        }
        return lista;
    }

    public enum Quadrante {
        PRIMEIRO, SEGUNDO, TERCEIRO, QUARTO
    }
}
