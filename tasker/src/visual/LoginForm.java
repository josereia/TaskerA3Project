package visual;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;

public class LoginForm {

	private JFrame frmLogin;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
					LoginForm window = new LoginForm();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\joaol\\eclipse-workspace\\tasker\\assets\\logo.png"));
		frmLogin.setResizable(false);
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 260, 300);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);

		JButton btnNewButton = new JButton("Entrar");
		btnNewButton.setBounds(50, 219, 63, 23);
		frmLogin.getContentPane().add(btnNewButton);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(123, 219, 81, 23);
		frmLogin.getContentPane().add(btnCadastrar);
		
		JLabel lblNewLabel = new JLabel("Login:");
		lblNewLabel.setBounds(50, 41, 46, 14);
		frmLogin.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(50, 57, 154, 20);
		frmLogin.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(50, 88, 46, 14);
		frmLogin.getContentPane().add(lblSenha);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(50, 104, 154, 20);
		frmLogin.getContentPane().add(passwordField);
		
		JLabel lblNewLabel_1 = new JLabel("Esqueceu a senha?");
		lblNewLabel_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_1.setForeground(new Color(0, 51, 102));
		lblNewLabel_1.setBounds(50, 146, 120, 14);
		frmLogin.getContentPane().add(lblNewLabel_1);
	}
}
