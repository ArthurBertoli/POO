package view;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dados.Gasto;
import negocio.Sistema;

public class AlterarGastoTabela extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTable tabela;
    private TabelaGastosModel tabelaModel;
    private Sistema sistema;

    public AlterarGastoTabela(Sistema sistema) {
        this.sistema = sistema;
        initialize();
    }

    private void initialize() {
        setTitle("Alterar Gastos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        tabelaModel = new TabelaGastosModel(sistema.obterGastos());
        tabela = new JTable(tabelaModel);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

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
            // Converte o índice da tela para o do modelo (caso a tabela seja ordenada).
            Gasto gastoSelecionado = tabelaModel.getGastoAt(tabela.convertRowIndexToModel(linhaSelecionada));
            new AlterarGasto(gastoSelecionado, tabela, sistema);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um gasto para editar.");
        }
    }
}
