package main;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import apresentacao.TabelaResultados;
import apresentacao.TabelaValores;

public class Calculadora extends JFrame {
    private JPanel painelEntrada = new JPanel();
    private JLabel infoCaixaTexto = new JLabel("Digite um valor:");
    private JTextField caixaTexto = new JTextField();
    private JButton botaoAdicionar = new JButton("Adicionar");
    private JButton botaoLimpar = new JButton("Limpar");
    private JScrollPane painelScrollTabelaResultados = new JScrollPane();
    private JTable tabelaResultados;
    private TabelaResultados resultados = new TabelaResultados();
    private JScrollPane painelScrollTabelaValores = new JScrollPane();
    private JTable tabelaValores;
    private TabelaValores valores = new TabelaValores();

    public Calculadora() {
        setTitle("Calculadora Estatística");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 920, 310);
        // Layout absoluto: sem isso o BorderLayout padrão ignoraria os setBounds abaixo.
        getContentPane().setLayout(null);

        painelEntrada.setBounds(15, 80, 280, 173);
        painelEntrada.setLayout(null);
        getContentPane().add(painelEntrada);

        infoCaixaTexto.setBounds(30, 30, 200, 15);
        painelEntrada.add(infoCaixaTexto);

        caixaTexto.setBounds(30, 50, 200, 20);
        painelEntrada.add(caixaTexto);

        botaoAdicionar.setBounds(77, 94, 117, 25);
        painelEntrada.add(botaoAdicionar);
        getRootPane().setDefaultButton(botaoAdicionar); // Enter também adiciona

        botaoLimpar.setBounds(77, 136, 117, 25);
        painelEntrada.add(botaoLimpar);

        tabelaResultados = new JTable(resultados);
        painelScrollTabelaResultados.setBounds(10, 10, 880, 50);
        painelScrollTabelaResultados.setViewportView(tabelaResultados);
        getContentPane().add(painelScrollTabelaResultados);

        tabelaValores = new JTable(valores);
        painelScrollTabelaValores.setBounds(307, 80, 173, 173);
        painelScrollTabelaValores.setViewportView(tabelaValores);
        getContentPane().add(painelScrollTabelaValores);

        botaoAdicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int valor;
                try {
                    valor = Integer.parseInt(caixaTexto.getText().trim());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(Calculadora.this, "Digite um número inteiro válido.");
                    return;
                }
                valores.adicionarValor(valor);
                resultados.atualizar();
                caixaTexto.setText("");
                caixaTexto.requestFocusInWindow();
            }
        });

        botaoLimpar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                valores.limpar();
                resultados.atualizar();
            }
        });
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Calculadora frame = new Calculadora();
                frame.setVisible(true);
            }
        });
    }
}
