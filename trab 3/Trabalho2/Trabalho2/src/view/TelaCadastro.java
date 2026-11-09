package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import negocio.Sistema;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCadastro {

    private JFrame frame;
    private JTextField txtCpf;
    private JTextField txtNome;
    private JTextField txtEmail;
    private JPasswordField passwordField;

    private Sistema sistema;
    private PrimeiraTela primeiraTela;

    public TelaCadastro(PrimeiraTela primeiraTela, Sistema sistema) {
        this.primeiraTela = primeiraTela;
        this.sistema = sistema;
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setBounds(50, 50, 46, 14);
        frame.getContentPane().add(lblCpf);

        txtCpf = new JTextField();
        txtCpf.setBounds(100, 50, 200, 20);
        frame.getContentPane().add(txtCpf);
        txtCpf.setColumns(10);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(50, 80, 46, 14);
        frame.getContentPane().add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(100, 80, 200, 20);
        frame.getContentPane().add(txtNome);
        txtNome.setColumns(10);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(50, 110, 46, 14);
        frame.getContentPane().add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(100, 110, 200, 20);
        frame.getContentPane().add(txtEmail);
        txtEmail.setColumns(10);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(50, 140, 46, 14);
        frame.getContentPane().add(lblSenha);

        passwordField = new JPasswordField();
        passwordField.setBounds(100, 140, 200, 20);
        frame.getContentPane().add(passwordField);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(150, 180, 89, 23);
        frame.getContentPane().add(btnCadastrar);

        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cpf = txtCpf.getText();
                String nome = txtNome.getText();
                String email = txtEmail.getText();
                String senha = new String(passwordField.getPassword());

                if (sistema.cadastrarUsuario(cpf, nome, email, senha)) {
                    JOptionPane.showMessageDialog(frame, "Cadastro realizado com sucesso!");
                    primeiraTela.getFrame().setVisible(true);
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "Falha no cadastro. Verifique os dados e tente novamente.");
                }
            }
        });

        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }
}
