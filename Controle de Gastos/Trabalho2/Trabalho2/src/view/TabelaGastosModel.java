package view;

import javax.swing.table.AbstractTableModel;
import dados.Gasto;

import java.util.List;

public class TabelaGastosModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private List<Gasto> gastos;
    private String[] colunas = {"Nome", "Data", "Descrição", "Valor", "Categoria"};

    public TabelaGastosModel(List<Gasto> gastos) {
        this.gastos = gastos;
    }

    @Override
    public int getRowCount() {
        return gastos.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Gasto gasto = gastos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return gasto.getNome();
            case 1:
                return gasto.getData();
            case 2:
                return gasto.getDescricao();
            case 3:
                return gasto.getValor();
            case 4:
                return gasto.getCategoria();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (getRowCount() > 0) {
            return getValueAt(0, columnIndex).getClass();
        }
        return Object.class;
    }

    public void setGastos(List<Gasto> gastos) {
        this.gastos = gastos;
        fireTableDataChanged();
    }

    public Gasto getGastoAt(int rowIndex) {
        return gastos.get(rowIndex);
    }
}

