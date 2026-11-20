package view;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

import negocio.Sistema;
import dados.Gasto;

public class TabelaGastos extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final String TODOS_MESES = "Todos os meses";
    private static final String TODAS_CATEGORIAS = "Todas as categorias";

    private DefaultTableModel tableModel;
    private JComboBox<String> comboMes;
    private JComboBox<String> comboCategoria;
    private JButton btnFiltrar;
    private Sistema sistema;

    public TabelaGastos(Sistema sistema) {
        this.sistema = sistema;
        initialize();
        loadTableData(sistema.obterGastos());
    }

    private void initialize() {
        this.setTitle("Tabela de Gastos");
        // Fechar a tabela não deve encerrar o programa.
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBounds(100, 100, 800, 600);

        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        tableModel = new DefaultTableModel() {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // a edição é feita pela tela "Alterar Gastos"
            }
        };
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 50, 760, 400);
        contentPane.add(scrollPane);

        String[] meses = {TODOS_MESES, "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};

        String[] categorias = new String[Sistema.CATEGORIAS.length + 1];
        categorias[0] = TODAS_CATEGORIAS;
        System.arraycopy(Sistema.CATEGORIAS, 0, categorias, 1, Sistema.CATEGORIAS.length);

        comboMes = new JComboBox<>(meses);
        comboMes.setBounds(10, 10, 150, 30);
        contentPane.add(comboMes);

        comboCategoria = new JComboBox<>(categorias);
        comboCategoria.setBounds(170, 10, 170, 30);
        contentPane.add(comboCategoria);

        btnFiltrar = new JButton("Filtrar");
        btnFiltrar.setBounds(350, 10, 100, 30);
        contentPane.add(btnFiltrar);

        btnFiltrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indiceMes = comboMes.getSelectedIndex();
                Integer mes = indiceMes <= 0 ? null : indiceMes; // índice 1 = janeiro
                String categoria = comboCategoria.getSelectedIndex() <= 0
                        ? null : (String) comboCategoria.getSelectedItem();

                loadTableData(sistema.filtrarGastos(mes, categoria));
            }
        });
    }

    private void loadTableData(List<Gasto> gastos) {
        tableModel.setColumnIdentifiers(new String[]{"Nome", "Data", "Descrição", "Valor", "Categoria"});
        tableModel.setRowCount(0);

        for (Gasto gasto : gastos) {
            tableModel.addRow(new Object[]{
                gasto.getNome(),
                Formatos.formatarData(gasto.getData()),
                gasto.getDescricao(),
                gasto.getValor(),
                gasto.getCategoria()
            });
        }
    }
}
