package view;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

import negocio.Sistema;
import dados.Gasto;

public class TabelaGastos extends JFrame {

    private static final long serialVersionUID = 1L;
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
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 800, 600);

        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        tableModel = new DefaultTableModel();
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 50, 760, 400);
        contentPane.add(scrollPane);

        String[] meses = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};

        String[] categorias = {"Comida", "Lazer", "Educacao", "Saude", "Transporte", "Outros"};
        comboMes = new JComboBox<>(meses);
        comboMes.setBounds(10, 10, 150, 30);
        contentPane.add(comboMes);

        comboCategoria = new JComboBox<>(categorias);
        comboCategoria.setBounds(170, 10, 150, 30);
        contentPane.add(comboCategoria);

        btnFiltrar = new JButton("Filtrar");
        btnFiltrar.setBounds(330, 10, 100, 30);
        contentPane.add(btnFiltrar);
        
        btnFiltrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mesSelecionado = (String) comboMes.getSelectedItem();
                String categoriaSelecionada = (String) comboCategoria.getSelectedItem();

                List<Gasto> gastos = sistema.obterGastos();
                List<Gasto> gastosFiltrados = new ArrayList<>();

                for (Gasto gasto : gastos) {
                    String categoria = gasto.getCategoria();
                    String mesGasto = new SimpleDateFormat("MMMM", new Locale("pt", "BR")).format(gasto.getData());

                    if (mesSelecionado.equalsIgnoreCase(mesGasto) && categoriaSelecionada.equals(categoria)) {
                        gastosFiltrados.add(gasto);
                    }
                }
                
                System.out.println("Mes selecionado: " + mesSelecionado);
                System.out.println("Categoria selecionada: " + categoriaSelecionada);
                System.out.println("Gastos filtrados: " + gastosFiltrados);

                loadTableData(gastosFiltrados);
            }
        });
    }

    private void loadTableData(List<Gasto> gastos) {
        tableModel.setColumnIdentifiers(new String[]{"Nome", "Data", "Descrição", "Valor", "Categoria"});
        tableModel.setRowCount(0);

        for (Gasto gasto : gastos) {
            tableModel.addRow(new Object[]{
                gasto.getNome(),
                gasto.getData(),
                gasto.getDescricao(),
                gasto.getValor(),
                gasto.getCategoria()
            });
        }
    }

	public void setGastos(List<Gasto> gastos) {
		
	}
}
