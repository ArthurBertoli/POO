package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import dados.Gasto;

public class AlterarGasto extends JFrame {
    private static final long serialVersionUID = 1L;
    private Gasto gasto;
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


    public AlterarGasto(Gasto gasto, JTable tabela) {
        this.gasto = gasto;
        this.tabela = tabela;
        initialize();
    }

    private void initialize() {
        setTitle("Alterar Gastos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JLabel lblNome = new JLabel("Nome:");
        txtNome = new JTextField(gasto.getNome());
        add(createPanel(lblNome, txtNome));

        JLabel lblData = new JLabel("Data:");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        txtData = new JTextField(dateFormat.format(gasto.getData()));
        add(createPanel(lblData, txtData));

        JLabel lblDescricao = new JLabel("Descrição:");
        txtDescricao = new JTextField(gasto.getDescricao());
        add(createPanel(lblDescricao, txtDescricao));

        JLabel lblValor = new JLabel("Valor:");
        txtValor = new JTextField(String.valueOf(gasto.getValor()));
        add(createPanel(lblValor, txtValor));

        JLabel lblCategoria = new JLabel("Categoria:");
        String[] categorias = {"Comida", "Lazer", "Educacao", "Saude", "Transporte", "Outros"};
        comboCategoria = new JComboBox<>(categorias);
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

    private JPanel createPanel(JLabel label, JTextField textField) {
        JPanel panel = new JPanel();
        panel.add(label);
        panel.add(textField);
        return panel;
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
            valor = Double.parseDouble(valorStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Valor inválido. Insira um número válido.");
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            gasto.setNome(nome);
            gasto.setData(dateFormat.parse(dataStr));
            gasto.setDescricao(descricao);
            gasto.setValor(valor);
            gasto.setCategoria((String) comboCategoria.getSelectedItem());

            ((TabelaGastosModel) tabela.getModel()).fireTableDataChanged();

            JOptionPane.showMessageDialog(this, "Alterações salvas com sucesso!");
            dispose();
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar as alterações. Verifique os dados e tente novamente.");
        }
    }
}
