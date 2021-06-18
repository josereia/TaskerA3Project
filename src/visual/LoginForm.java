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

public class LoginForm {

	private JFrame frmLogin;
	private JTextField text_login;
	private JTextField text_senha;

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
		frmLogin.setIconImage(Toolkit.getDefaultToolkit().getImage("assets/logo.png"));
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
				UsuarioDTO usuariodto = new UsuarioDTO();
				usuariodto.setLogin(text_login.getText());
				usuariodto.setSenha(text_senha.getText());

				if (new UsuarioDAO().checkLogin(usuariodto) != null) {
					new PrincipalForm(usuariodto).setVisible(true);
					frmLogin.dispose();
				} else {
					JOptionPane.showMessageDialog(frmLogin, "Login ou senha incorretos!");
				}
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

		text_senha = new JTextField();
		text_senha.setColumns(10);
		text_senha.setBounds(44, 94, 140, 20);
		panel_1.add(text_senha);

		JLabel lblNewLabel = new JLabel("Esqueceu a senha?");
		lblNewLabel.setBounds(44, 120, 140, 14);
		panel_1.add(lblNewLabel);
	}

	public void setVisible(boolean b) {
		frmLogin.setVisible(b);
	}
}
