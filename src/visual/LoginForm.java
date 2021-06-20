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

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;

public class LoginForm {

	private JFrame frmLogin;
	private JTextField text_login;
	private JPasswordField text_senha;
	Toolkit toolkit = Toolkit.getDefaultToolkit();

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
		frmLogin.setIconImage(toolkit.getImage(this.getClass().getResource("/logo.png")));
		frmLogin.setResizable(false);
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 400, 320);
		// faz com que a janela inicie no centro da tela
		frmLogin.setLocationRelativeTo(null);

		JPanel panel_1 = new JPanel();
		frmLogin.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Login:");
		lblNewLabel_1.setBounds(214, 62, 46, 14);
		panel_1.add(lblNewLabel_1);

		text_login = new JTextField();
		text_login.setBounds(214, 83, 140, 20);
		panel_1.add(text_login);
		text_login.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Senha:");
		lblNewLabel_1_1.setBounds(214, 114, 46, 14);
		panel_1.add(lblNewLabel_1_1);

		JLabel lblNewLabel = new JLabel("Esqueceu a senha?");
		lblNewLabel.setBounds(214, 160, 140, 14);
		panel_1.add(lblNewLabel);

		text_senha = new JPasswordField();
		text_senha.setBounds(214, 135, 140, 20);
		text_senha.addKeyListener(new KeyAdapter() {
			@Override
			// ação ao pressionar enter
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					login();
				}
			}
		});
		panel_1.add(text_senha);

		JButton btn_entrar = new JButton("Entrar");
		btn_entrar.setBounds(249, 200, 63, 23);
		panel_1.add(btn_entrar);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblNewLabel_2.setInheritsPopupMenu(false);
		lblNewLabel_2.setAlignmentY(0.0f);
		lblNewLabel_2.setIconTextGap(0);
		lblNewLabel_2.setIcon(new ImageIcon(toolkit.getImage(this.getClass().getResource("/loginImg.png"))));
		lblNewLabel_2.setBounds(0, 0, 180, 290);
		panel_1.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel(".Dev");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3.setBounds(358, 255, 26, 14);
		panel_1.add(lblNewLabel_3);
		btn_entrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
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
