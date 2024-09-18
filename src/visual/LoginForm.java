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

import com.formdev.flatlaf.FlatDarkLaf;

import java.awt.Font;

public class LoginForm {

	private JFrame frmLogin;
	private JTextField text_login;
	private JPasswordField text_senha;
	// toolkit
	Toolkit toolkit = Toolkit.getDefaultToolkit();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// define o estilo da janela para o estilo padr�o do sistema operacional
					javax.swing.UIManager.setLookAndFeel(new FlatDarkLaf());
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
		// logo icon
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
		// a��o ao pressionar alguma tecla no campo senha
		text_senha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// a��o ao pressionar enter
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					login();
				}
				// a��o ao pressionar F1
				if (e.getKeyCode() == KeyEvent.VK_F1) {
					String numero = JOptionPane.showInputDialog("Digite a senha:");
					if (numero.equals("1234")) {
						new CadastroUsers().setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Senha incorreta!");
					}
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
		// imagem na label
		lblNewLabel_2.setIcon(new ImageIcon(toolkit.getImage(this.getClass().getResource("/loginImg.png"))));
		lblNewLabel_2.setBounds(0, 0, 180, 290);
		panel_1.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel(".Beta 1");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3.setBounds(347, 254, 26, 14);
		panel_1.add(lblNewLabel_3);
		btn_entrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
	}

	// m�todos
	// m�todo de verifica��o de login
	private void login() {
		UsuarioDTO usuariodto = new UsuarioDTO();
		usuariodto.setLogin(text_login.getText());
		usuariodto.setSenha(String.valueOf(text_senha.getPassword()));

		if (new UsuarioDAO().checkLogin(usuariodto).getIdUsuario() != 0) {
			new PrincipalForm(usuariodto).setVisible(true);
			frmLogin.dispose();
		}
	}

	public void setVisible(boolean b) {
		frmLogin.setVisible(b);
	}
}
