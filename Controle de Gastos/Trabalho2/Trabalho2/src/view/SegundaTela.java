package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dados.Gasto;
import negocio.Sistema;

public class SegundaTela {

    private JFrame frame;
    private JTextField txtNome;
    private JTextField txtData;
    private JTextField txtDescricao;
    private JTextField txtValor;
    private JComboBox<String> comboCategoria;

    private Sistema sistema;

    public SegundaTela(Sistema sistema) {
        this.sistema = sistema;
        initialize();
    }

    private void initialize() {
        String usuario = sistema.getUsuarioLogado() != null ? sistema.getUsuarioLogado().getNome() : "";
        frame = new JFrame("Controle de Gastos - " + usuario
                + (sistema.isPersistente() ? " (PostgreSQL)" : " (modo memória)"));
        frame.setBounds(100, 100, 515, 469);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(50, 30, 46, 14);
        frame.getContentPane().add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(150, 30, 200, 20);
        frame.getContentPane().add(txtNome);
        txtNome.setColumns(10);

        JLabel lblData = new JLabel("Data (dd/MM/aaaa):");
        lblData.setBounds(10, 60, 135, 14);
        frame.getContentPane().add(lblData);

        txtData = new JTextField();
        txtData.setBounds(150, 60, 200, 20);
        frame.getContentPane().add(txtData);
        txtData.setColumns(10);

        JLabel lblDescricao = new JLabel("Descrição:");
        lblDescricao.setBounds(50, 90, 90, 14);
        frame.getContentPane().add(lblDescricao);

        txtDescricao = new JTextField();
        txtDescricao.setBounds(150, 90, 200, 20);
        frame.getContentPane().add(txtDescricao);
        txtDescricao.setColumns(10);

        JLabel lblValor = new JLabel("Valor:");
        lblValor.setBounds(50, 120, 46, 14);
        frame.getContentPane().add(lblValor);

        txtValor = new JTextField();
        txtValor.setBounds(150, 120, 200, 20);
        frame.getContentPane().add(txtValor);
        txtValor.setColumns(10);

        JLabel lblCategoria = new JLabel("Categoria:");
        lblCategoria.setBounds(50, 153, 90, 14);
        frame.getContentPane().add(lblCategoria);

        comboCategoria = new JComboBox<>(Sistema.CATEGORIAS);
        comboCategoria.setBounds(150, 150, 200, 20);
        frame.getContentPane().add(comboCategoria);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(150, 180, 100, 23);
        frame.getContentPane().add(btnCadastrar);

        JButton btnVisualizarGraficoBarra = new JButton("Visualizar Gráfico de Barras");
        btnVisualizarGraficoBarra.setBounds(10, 232, 230, 32);
        frame.getContentPane().add(btnVisualizarGraficoBarra);

        JButton btnAbrirTabelaGastos = new JButton("Visualizar Tabela de Gastos");
        btnAbrirTabelaGastos.setBounds(250, 232, 230, 32);
        frame.getContentPane().add(btnAbrirTabelaGastos);
        
        JButton btnVisualizarGraficoAnual = new JButton("Visualizar Gráfico Anual");
        btnVisualizarGraficoAnual.setBounds(10, 282, 230, 32);
        frame.getContentPane().add(btnVisualizarGraficoAnual);
        
        JButton btnAlterarGastos = new JButton("Alterar Gastos");
        btnAlterarGastos.setBounds(250, 282, 230, 32);
        frame.getContentPane().add(btnAlterarGastos);
        
        btnAlterarGastos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirTabelaParaAlteracao();
            }
        });
               

        btnAbrirTabelaGastos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirTabelaGastos();
            }
        });

        btnVisualizarGraficoBarra.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirTelaGraficoBarra();
            }
        });
        
        btnVisualizarGraficoAnual.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirGraficoAnual();
            }
        });


        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = txtNome.getText().trim();
                String dataStr = txtData.getText().trim();
                String descricao = txtDescricao.getText().trim();
                String valorStr = txtValor.getText().trim();

                if (nome.isEmpty() || dataStr.isEmpty() || descricao.isEmpty() || valorStr.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Por favor, preencha todos os campos obrigatórios.");
                    return;
                }

                double valor;
                try {
                    valor = Formatos.lerValor(valorStr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Valor inválido. Insira um número válido.");
                    return;
                }

                Date dataComoDate;
                try {
                    dataComoDate = Formatos.lerData(dataStr);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(frame, "Data inválida. Use o formato dd/MM/yyyy.");
                    return;
                }

                String categoria = (String) comboCategoria.getSelectedItem();
                Gasto novoGasto = new Gasto(nome, dataComoDate, descricao, valor, categoria);

                try {
                    sistema.cadastrarGasto(novoGasto, categoria);
                } catch (IllegalArgumentException | IllegalStateException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage());
                    return;
                }

                JOptionPane.showMessageDialog(frame, "Gasto cadastrado com sucesso!");
                mostrarAvisoPersistencia();

                txtNome.setText("");
                txtData.setText("");
                txtDescricao.setText("");
                txtValor.setText("");
            }
        });

    }

    private void mostrarAvisoPersistencia() {
        String aviso = sistema.consumirAvisoPersistencia();
        if (aviso != null) {
            JOptionPane.showMessageDialog(frame, aviso, "Banco de dados indisponível", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void abrirTelaGraficoBarra() {
        TelaGraficoBarra telaGraficoBarra = new TelaGraficoBarra("Gráfico de Gastos", sistema.obterGastos());
        telaGraficoBarra.pack();
        telaGraficoBarra.setLocationRelativeTo(frame);
        telaGraficoBarra.setVisible(true);
    }

    private void abrirTabelaGastos() {
        TabelaGastos tabelaGastos = new TabelaGastos(sistema);
        tabelaGastos.setVisible(true);
    }
    
    private void abrirGraficoAnual() {
        List<Gasto> gastos = sistema.obterGastos();
        
        Map<YearMonth, Double> valoresMensais = new HashMap<>();

        for (Gasto gasto : gastos) {         
            YearMonth mesGasto = YearMonth.from(gasto.getData().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

            if (valoresMensais.containsKey(mesGasto)) {
                valoresMensais.put(mesGasto, valoresMensais.get(mesGasto) + gasto.getValor());
            } else {
                valoresMensais.put(mesGasto, gasto.getValor());
            }
        }
        
        Scatter graficoAnual = new Scatter(valoresMensais);

        JFrame frameGraficoAnual = new JFrame("Gráfico Anual");
        frameGraficoAnual.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameGraficoAnual.getContentPane().add(graficoAnual.getChartPanel());
        
        frameGraficoAnual.pack();
        frameGraficoAnual.setLocationRelativeTo(frame);
        frameGraficoAnual.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    private void abrirTabelaParaAlteracao() {
        if (sistema.obterGastos().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Nenhum gasto cadastrado para alterar.");
            return;
        }
        new AlterarGastoTabela(sistema);
    }
}
