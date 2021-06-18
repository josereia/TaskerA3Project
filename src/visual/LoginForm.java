package visual;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.UsuarioDAO;
import dto.UsuarioDTO;

import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginForm {

	private JFrame frmLogin;
	private JTextField text_login;
	private JPasswordField text_senha;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// define o estilo da janela para o estilo padrão do sistema operacional
					javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
					LoginForm window = new LoginForm();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginForm() {
		initialize();
	}

	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmLogin.setIconImage(Toolkit.getDefaultToolkit().getImage("resources/logo.png"));
		frmLogin.setResizable(false);
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 240, 240);
		// faz com que a janela inicie no centro da tela
		frmLogin.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		frmLogin.getContentPane().add(panel, BorderLayout.SOUTH);

		JButton btn_entrar = new JButton("Entrar");
		btn_entrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});

		panel.add(btn_entrar);

		JPanel panel_1 = new JPanel();
		frmLogin.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Login:");
		lblNewLabel_1.setBounds(44, 21, 46, 14);
		panel_1.add(lblNewLabel_1);

		text_login = new JTextField();

		text_login.setBounds(44, 42, 140, 20);
		panel_1.add(text_login);
		text_login.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Senha:");
		lblNewLabel_1_1.setBounds(44, 73, 46, 14);
		panel_1.add(lblNewLabel_1_1);

		JLabel lblNewLabel = new JLabel("Esqueceu a senha?");
		lblNewLabel.setBounds(44, 120, 140, 14);
		panel_1.add(lblNewLabel);

		text_senha = new JPasswordField();
		text_senha.addKeyListener(new KeyAdapter() {
			@Override
			// ação ao pressionar enter
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					login();
				}
			}
		});
		text_senha.setBounds(44, 94, 140, 20);
		panel_1.add(text_senha);
	}

	public void setVisible(boolean b) {
		frmLogin.setVisible(b);
	}

	// métodos
	// método de verificação de login
	public void login() {
		UsuarioDTO usuariodto = new UsuarioDTO();
		usuariodto.setLogin(text_login.getText());
		usuariodto.setSenha(String.valueOf(text_senha.getPassword()));

		if (new UsuarioDAO().checkLogin(usuariodto) != null) {
			new PrincipalForm(usuariodto).setVisible(true);
			frmLogin.dispose();
		} else {
			JOptionPane.showMessageDialog(frmLogin, "Login ou senha incorretos!");
		}
	}
}
