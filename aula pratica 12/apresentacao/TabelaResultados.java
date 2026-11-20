package apresentacao;

import javax.swing.table.AbstractTableModel;
import dados.CalculadoraEstatistica;

public class TabelaResultados extends AbstractTableModel {
    private String[] colunas = { "Sorteado", "Somatório", "Média Aritmética", "Média Geométrica", "Variância",
            "Desvio Padrão", "Amplitude" };

    private CalculadoraEstatistica calculadora = CalculadoraEstatistica.getInstance();

    // O valor sorteado é guardado: getValueAt é chamado a cada repintura da tabela e
    // sortear ali faria o número mudar sozinho na tela.
    private Integer sorteado = null;

    public String getColumnName(int column) {
        return colunas[column];
    }

    public int getColumnCount() {
        return colunas.length;
    }

    public int getRowCount() {
        return 1;
    }

    public Object getValueAt(int linha, int coluna) {
        if (!calculadora.getValores().isEmpty()) {
            switch (coluna) {
                case 0:
                    if (sorteado == null) {
                        sorteado = calculadora.sortear();
                    }
                    return sorteado;
                case 1:
                    return calculadora.somatorio();
                case 2:
                    return calculadora.mediaAritmetica();
                case 3:
                    double mediaGeometrica = calculadora.mediaGeometrica();
                    return Double.isNaN(mediaGeometrica) ? "−" : mediaGeometrica;
                case 4:
                    return calculadora.variancia();
                case 5:
                    return calculadora.desvioPadrao();
                case 6:
                    return calculadora.amplitude();
            }
        } else {
            return "−"; // O valor a ser exibido quando a lista de valores está vazia.
        }
        return null;
    }

    public void atualizar() {
        sorteado = null;
        fireTableDataChanged();
    }

}
