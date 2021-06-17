package visual;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;

public class RegisterForm {

	private JFrame frmCadastrar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
					RegisterForm window = new RegisterForm();
					window.frmCadastrar.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RegisterForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCadastrar = new JFrame();
		frmCadastrar.setTitle("Cadastrar");
		frmCadastrar.setIconImage(Toolkit.getDefaultToolkit().getImage("assets/logo.png"));
		frmCadastrar.setBounds(100, 100, 450, 300);
		frmCadastrar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCadastrar.setLocationRelativeTo(null);
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		frmCadastrar.setVisible(b);
	}

}
