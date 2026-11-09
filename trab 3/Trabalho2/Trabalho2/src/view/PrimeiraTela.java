package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import negocio.Sistema;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrimeiraTela {

    private JFrame frame;
    private JTextField txtCpf;
    private JPasswordField passwordField;

    private Sistema sistema;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PrimeiraTela window = new PrimeiraTela();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public PrimeiraTela() {
        sistema = new Sistema();
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("CPF:");
        lblNewLabel.setBounds(101, 50, 46, 14);
        frame.getContentPane().add(lblNewLabel);

        txtCpf = new JTextField();
        txtCpf.setBounds(101, 75, 208, 20);
        frame.getContentPane().add(txtCpf);
        txtCpf.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Senha");
        lblNewLabel_1.setBounds(101, 98, 46, 14);
        frame.getContentPane().add(lblNewLabel_1);

        passwordField = new JPasswordField();
        passwordField.setBounds(101, 116, 208, 20);
        frame.getContentPane().add(passwordField);

        JButton btnNewButton = new JButton("Entrar");
        btnNewButton.setBounds(150, 147, 89, 23);
        frame.getContentPane().add(btnNewButton);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(101, 180, 193, 23);
        frame.getContentPane().add(btnCadastrar);

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cpf = txtCpf.getText();
                String senha = new String(passwordField.getPassword());

                if (sistema.autenticarUsuario(cpf, senha)) {
                    SegundaTela segundaTela = new SegundaTela(sistema);
                    segundaTela.getFrame().setVisible(true);
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "Credenciais inválidas. Tente novamente.");
                }
            }
        });

        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TelaCadastro telaCadastro = new TelaCadastro(PrimeiraTela.this, sistema);
                telaCadastro.getFrame().setVisible(true);
                frame.dispose();
            }
        });
    }

    public JFrame getFrame() {
        return frame;
    }
}
