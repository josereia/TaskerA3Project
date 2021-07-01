package visual;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;

import dto.NcsDTO;
import dto.UsuarioDTO;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.JButton;

import dao.NcsDAO;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class PrincipalForm {

	private JFrame frmPrincipal;
	private UsuarioDTO usuariodto;
	// toolkit
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	private JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// define o estilo da janela para o estilo padr�o do sistema operacionall
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

	}

	public PrincipalForm(UsuarioDTO usuariodto) {
		this.usuariodto = usuariodto;
		initialize();
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		frmPrincipal = new JFrame();
		frmPrincipal.setMinimumSize(new Dimension(640, 400));
		frmPrincipal.setTitle("Principal");
		// icone da janela
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

		JButton btn_excluir = new JButton("Excluir");
		btn_excluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Quer realmente exluir?", "Excluir NC",
						JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					new NcsDAO().delete(
							Integer.parseInt(String.valueOf(table.getModel().getValueAt(table.getSelectedRow(), 0))));
					carregarTabela();
				}

			}
		});

		JButton btnNewButton_1 = new JButton("Alterar");

		CadastroNCs cadastroncs = new CadastroNCs(frmPrincipal, usuariodto);
		JButton btnNewButton_2 = new JButton("Inserir");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastroncs.setVisible(true);
			}
		});
		cadastroncs.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				carregarTabela();
			}
		});

		JScrollPane scrollPane = new JScrollPane();

		table = new JTable();
		scrollPane.setViewportView(table);
		carregarTabela();

		GroupLayout groupLayout = new GroupLayout(frmPrincipal.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
								groupLayout.createSequentialGroup().addGap(403).addComponent(btnNewButton_2).addGap(10)
										.addComponent(btnNewButton_1).addGap(10).addComponent(btn_excluir))
								.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(scrollPane,
										GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE)))
						.addContainerGap()));
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout.createSequentialGroup()
						.addContainerGap().addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
						.addGap(18).addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNewButton_2).addComponent(btnNewButton_1).addComponent(btn_excluir))
						.addGap(15)));
		frmPrincipal.getContentPane().setLayout(groupLayout);

		if (usuariodto.isAcesso() == false) {
			mnAdm.setVisible(false);
			btn_excluir.setEnabled(false);
		}
	}

	public void setVisible(boolean b) {
		frmPrincipal.setVisible(b);
	}

	// m�todos
	public void carregarTabela() {
		table.setModel(new NcsDAO().read(usuariodto.getEmpresa()));

		table.getModel().addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent arg0) {

				try {
					NcsDTO ncsdto = new NcsDTO();

					ncsdto.setId(
							Integer.parseInt(String.valueOf(table.getModel().getValueAt(table.getSelectedRow(), 0))));
					ncsdto.setTitulo((String) table.getModel().getValueAt(table.getSelectedRow(), 1));
					ncsdto.setDescricao((String) table.getModel().getValueAt(table.getSelectedRow(), 2));
					ncsdto.setResponsavel((String) table.getModel().getValueAt(table.getSelectedRow(), 3));
					ncsdto.setPrazo(table.getModel().getValueAt(table.getSelectedRow(), 4).toString());
					ncsdto.setUsuario((String) table.getModel().getValueAt(table.getSelectedRow(), 6));
					ncsdto.setStatus((String) table.getModel().getValueAt(table.getSelectedRow(), 7));
					ncsdto.setUsuarioEmpresa(usuariodto.getEmpresa());

					new NcsDAO().update(ncsdto);

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Problema ao alterar dados!" + " - " + e.getMessage());
					e.printStackTrace();
				}
			}
		});
	}

}
