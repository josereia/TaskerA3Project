package visual;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;

import dto.UsuarioDTO;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

public class PrincipalForm {

	private JFrame frmPrincipal;
	private UsuarioDTO usuariodto;
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	private JTable table;

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

	/**
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		frmPrincipal = new JFrame();
		frmPrincipal.setMinimumSize(new Dimension(640, 400));
		frmPrincipal.setTitle("Principal");
		frmPrincipal.setIconImage(toolkit.getImage(this.getClass().getResource("/logo.png")));
		frmPrincipal.setBounds(100, 100, 640, 400);
		frmPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// faz com que a janela inicie no centro da tela
		frmPrincipal.setLocationRelativeTo(null);

		JMenuBar menuBar = new JMenuBar();
		frmPrincipal.setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Arquivo");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Gerar relat\u00F3rio");
		mnNewMenu.add(mntmNewMenuItem_2);

		JMenuItem mntmSair = new JMenuItem("Sair");
		mnNewMenu.add(mntmSair);

		JMenu mnAdm = new JMenu("Administrador");
		menuBar.add(mnAdm);

		JMenuItem mntmGerFuncionarios = new JMenuItem("Ger. Funcion\u00E1rios");
		mnAdm.add(mntmGerFuncionarios);

		table = new JTable();

		JButton btn_excluir = new JButton("Excluir");

		JButton btnNewButton_1 = new JButton("Alterar");

		JButton btnNewButton_2 = new JButton("Inserir");
		GroupLayout groupLayout = new GroupLayout(frmPrincipal.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(table, GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup().addComponent(btnNewButton_2)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnNewButton_1)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btn_excluir)))
						.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(table, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE).addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(btn_excluir)
								.addComponent(btnNewButton_1).addComponent(btnNewButton_2))
						.addContainerGap(15, Short.MAX_VALUE)));
		frmPrincipal.getContentPane().setLayout(groupLayout);

		if (usuariodto.isAcesso() == false) {
			mnAdm.setVisible(false);
			btn_excluir.setEnabled(false);
		}
	}

	public void setVisible(boolean b) {
		frmPrincipal.setVisible(b);
	}
}
