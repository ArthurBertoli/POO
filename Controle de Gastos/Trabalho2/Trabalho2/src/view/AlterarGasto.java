package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Date;

import dados.Gasto;
import negocio.Sistema;

public class AlterarGasto extends JFrame {
    private static final long serialVersionUID = 1L;
    private Gasto gasto;
    private Sistema sistema;
    private JTextField txtNome;
    private JTextField txtData;
    private JTextField txtDescricao;
    private JTextField txtValor;
    private JComboBox<String> comboCategoria;
    private JTable tabela;

    private JPanel createPanel(JLabel label, JComponent component) {
        JPanel panel = new JPanel();
        panel.add(label);
        panel.add(component);
        return panel;
    }

    public AlterarGasto(Gasto gasto, JTable tabela, Sistema sistema) {
        this.gasto = gasto;
        this.tabela = tabela;
        this.sistema = sistema;
        initialize();
    }

    private void initialize() {
        setTitle("Alterar Gastos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JLabel lblNome = new JLabel("Nome:");
        txtNome = new JTextField(gasto.getNome(), 20);
        add(createPanel(lblNome, txtNome));

        JLabel lblData = new JLabel("Data:");
        txtData = new JTextField(Formatos.formatarData(gasto.getData()), 20);
        add(createPanel(lblData, txtData));

        JLabel lblDescricao = new JLabel("Descrição:");
        txtDescricao = new JTextField(gasto.getDescricao(), 20);
        add(createPanel(lblDescricao, txtDescricao));

        JLabel lblValor = new JLabel("Valor:");
        txtValor = new JTextField(String.valueOf(gasto.getValor()), 20);
        add(createPanel(lblValor, txtValor));

        JLabel lblCategoria = new JLabel("Categoria:");
        comboCategoria = new JComboBox<>(Sistema.CATEGORIAS);
        comboCategoria.setSelectedItem(gasto.getCategoria());
        add(createPanel(lblCategoria, comboCategoria));

        JButton btnSalvar = new JButton("Salvar Alterações");
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarAlteracoes();
            }
        });
        add(btnSalvar);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void salvarAlteracoes() {
        String nome = txtNome.getText().trim();
        String dataStr = txtData.getText().trim();
        String descricao = txtDescricao.getText().trim();
        String valorStr = txtValor.getText().trim();

        if (nome.isEmpty() || dataStr.isEmpty() || descricao.isEmpty() || valorStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos obrigatórios.");
            return;
        }

        double valor;
        try {
            valor = Formatos.lerValor(valorStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Valor inválido. Insira um número válido.");
            return;
        }

        // A data é validada antes de mexer no gasto, para não deixá-lo alterado pela metade.
        Date data;
        try {
            data = Formatos.lerData(dataStr);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Data inválida. Use o formato dd/MM/yyyy.");
            return;
        }

        try {
            sistema.alterarGasto(gasto, nome, data, descricao, valor, (String) comboCategoria.getSelectedItem());
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
            return;
        }

        ((TabelaGastosModel) tabela.getModel()).fireTableDataChanged();

        JOptionPane.showMessageDialog(this, "Alterações salvas com sucesso!");
        String aviso = sistema.consumirAvisoPersistencia();
        if (aviso != null) {
            JOptionPane.showMessageDialog(this, aviso, "Banco de dados indisponível", JOptionPane.WARNING_MESSAGE);
        }
        dispose();
    }
}
