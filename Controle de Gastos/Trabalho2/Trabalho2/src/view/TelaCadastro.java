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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
        frame = new JFrame("Cadastro de usuário");
        frame.setBounds(100, 100, 450, 300);
        // Fechar o cadastro volta para a tela de login em vez de encerrar o programa.
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                primeiraTela.getFrame().setVisible(true);
                primeiraTela.mostrarAvisoPersistencia();
            }
        });
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
        btnCadastrar.setBounds(150, 180, 100, 23);
        frame.getContentPane().add(btnCadastrar);
        frame.getRootPane().setDefaultButton(btnCadastrar);

        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cpf = txtCpf.getText();
                String nome = txtNome.getText();
                String email = txtEmail.getText();
                String senha = new String(passwordField.getPassword());

                try {
                    sistema.cadastrarUsuario(cpf, nome, email, senha);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Falha no cadastro", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                JOptionPane.showMessageDialog(frame, "Cadastro realizado com sucesso!");
                frame.dispose(); // volta para o login (windowClosed)
            }
        });
    }

    public JFrame getFrame() {
        return frame;
    }
}
