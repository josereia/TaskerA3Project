package visual;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import dto.UsuarioDTO;

import java.awt.BorderLayout;

public class PrincipalForm {

	private JFrame frmPrincipal;
	private UsuarioDTO usuariodto;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// define o estilo da janela para o estilo padrão do sistema operacional
					javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
					PrincipalForm window = new PrincipalForm();
					window.frmPrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PrincipalForm() {
		if (usuariodto == null) {
			new LoginForm().setVisible(true);
		} else {
			initialize();
		}
	}

	public PrincipalForm(UsuarioDTO usuariodto) {
		if (usuariodto == null) {
			new LoginForm().setVisible(true);
		} else {
			this.usuariodto = usuariodto;
			initialize();
		}
	}

	private void initialize() {
		frmPrincipal = new JFrame();
		frmPrincipal.setTitle("Principal");
		frmPrincipal.setBounds(100, 100, 450, 300);
		frmPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// faz com que a janela inicie no centro da tela
		frmPrincipal.setLocationRelativeTo(null);

		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setText(usuariodto.getNome() + " " + usuariodto.getSobrenome());

		frmPrincipal.getContentPane().add(lblNewLabel, BorderLayout.NORTH);
	}

	public void setVisible(boolean b) {
		frmPrincipal.setVisible(b);
	}

}
