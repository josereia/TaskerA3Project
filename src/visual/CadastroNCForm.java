package visual;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class CadastroNCForm {

	private JFrame CadastroNCForm;
	Toolkit toolkit = Toolkit.getDefaultToolkit();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// define o estilo da janela para o estilo padrão do sistema operacional
					javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
					CadastroNCForm window = new CadastroNCForm();
					window.CadastroNCForm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CadastroNCForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		CadastroNCForm = new JFrame();
		CadastroNCForm.setBounds(100, 100, 450, 300);
		CadastroNCForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//icone da janela
		CadastroNCForm.setIconImage(toolkit.getImage(this.getClass().getResource("/logo.png")));
		// faz com que a janela inicie no centro da tela
		CadastroNCForm.setLocationRelativeTo(null);
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		CadastroNCForm.setVisible(b);
	}

}
