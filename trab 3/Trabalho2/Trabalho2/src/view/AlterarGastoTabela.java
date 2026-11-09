package view;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import dados.Gasto;
import negocio.Sistema;

public class AlterarGastoTabela extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tabela;
    private List<Gasto> gastos;
    
    public AlterarGastoTabela(List<Gasto> gastos, Sistema sistema) {
        this.gastos = gastos;
        initialize();
    }

    private void initialize() {
        setTitle("Alterar Gastos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        TabelaGastosModel tabelaModel = new TabelaGastosModel(gastos);
        tabela = new JTable(tabelaModel);

        JScrollPane scrollPane = new JScrollPane(tabela);
        add(scrollPane);

        JButton btnAlterar = new JButton("Alterar Gasto Selecionado");
        btnAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alterarGastoSelecionado();
            }
        });

        JPanel panel = new JPanel();
        panel.add(btnAlterar);
        add(panel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void alterarGastoSelecionado() {
        int linhaSelecionada = tabela.getSelectedRow();
        if (linhaSelecionada >= 0) {
            Gasto gastoSelecionado = gastos.get(linhaSelecionada);

            AlterarGasto telaEdicao = new AlterarGasto(gastoSelecionado, tabela);
            telaEdicao.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um gasto para editar.");
        }
    }
}
