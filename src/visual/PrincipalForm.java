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
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import controller.Administrador;
import controller.Funcionario;

import javax.swing.JButton;

import dao.NcsDAO;
import dao.UsuarioDAO;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PrincipalForm {

	private JFrame frmPrincipal;
	private UsuarioDTO usuariodto;
	private Funcionario funcionario;
	private JTable table;
	// toolkit
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	private JTextField txt_tituloEid;
	private JTextField txt_responsavel;
	private JTextField txt_usuario;
	private TableModel modelPadrao;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// define o estilo da janela para o estilo padrão do sistema operacionall
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
		initialize();
	}

	public PrincipalForm(UsuarioDTO usuariodto) {
		this.usuariodto = usuariodto;
		this.funcionario = new Funcionario(usuariodto);
		this.modelPadrao = new NcsDAO().read(usuariodto.getEmpresa());
		initialize();
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		frmPrincipal = new JFrame();
		frmPrincipal.setMinimumSize(new Dimension(640, 400));
		frmPrincipal.setTitle("Gerenciamento de NCs");
		// icone da janela
		frmPrincipal.setIconImage(toolkit.getImage(this.getClass().getResource("/logo.png")));
		frmPrincipal.setBounds(100, 100, 654, 480);
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
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logoff();
			}
		});
		mnNewMenu.add(mntmSair);

		JMenu mnAdm = new JMenu("Administrador");
		menuBar.add(mnAdm);

		JMenuItem mntmGerFuncionarios = new JMenuItem("Ger. Funcion\u00E1rios");
		mntmGerFuncionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ListaUsers(usuariodto).setVisible(true);
			}
		});
		mnAdm.add(mntmGerFuncionarios);

		JMenu mnNewMenu_1 = new JMenu("Sobre");
		mnNewMenu_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SobreForm().setVisible(true);
			}
		});
		menuBar.add(mnNewMenu_1);

		JButton btn_excluir = new JButton("Excluir");
		btn_excluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resposta = JOptionPane.showConfirmDialog(null, "Quer realmente exluir?", "Excluir NC",
						JOptionPane.YES_NO_OPTION);
				if (resposta == JOptionPane.YES_OPTION) {
					new Administrador(usuariodto).excluirNC(
							Integer.parseInt(String.valueOf(table.getModel().getValueAt(table.getSelectedRow(), 0))));
					// carregarTabela();
				}
			}
		});

		JButton btn_detalhes = new JButton("Detalhes");

		JButton btnNewButton_2 = new JButton("Inserir");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroNCs cadastroncs = new CadastroNCs(frmPrincipal, usuariodto);
				cadastroncs.setVisible(true);

				cadastroncs.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						// carregarTabela();
					}
				});
			}
		});

		JScrollPane scrollPane = new JScrollPane();

		table = new JTable();
		scrollPane.setViewportView(table);
		carregarTabela(new NcsDAO().read(funcionario.getEmpresa()));

		JLabel lblNewLabel = new JLabel("Procurar NC:");

		txt_tituloEid = new JTextField();
		txt_tituloEid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				/*TableRowSorter sorter = (TableRowSorter) table.getRowSorter();
				sorter.setRowFilter(javax.swing.RowFilter.regexFilter("(?i).*" + txt_tituloEid.getText() + ".*"));
				*/
				/*if (txt_tituloEid.getText().equals("")) {
					carregarTabela(modelPadrao);
				} else {
					NcsDAO ncsdao = new NcsDAO();
					carregarTabela(ncsdao.filteroTituloId(txt_tituloEid.getText(), funcionario.getEmpresa()));
				}*/
			}
		});
		txt_tituloEid.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Usu\u00E1rio:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);

		txt_responsavel = new JTextField();
		txt_responsavel.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Respons\u00E1vel:");

		JLabel lblNewLabel_2 = new JLabel("Status:");

		JComboBox<String> cb_status = new JComboBox<String>();

		JLabel lblNewLabel_2_1 = new JLabel("Prazo:");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.RIGHT);

		JComboBox<String> cb_prazo = new JComboBox<String>();
		cb_prazo.setModel(new DefaultComboBoxModel<String>(new String[] { "Nenhum", "Crescente", "Decrescente" }));

		JLabel lblNewLabel_2_1_1 = new JLabel("Cadastro:");

		JComboBox<String> cb_cadastro = new JComboBox<String>();
		cb_cadastro.setModel(new DefaultComboBoxModel<String>(new String[] { "Nenhum", "Crescente", "Decrescente" }));

		txt_usuario = new JTextField();
		txt_usuario.setColumns(10);

		GroupLayout groupLayout = new GroupLayout(frmPrincipal.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				groupLayout.createSequentialGroup().addContainerGap().addGroup(groupLayout
						.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup().addGroup(groupLayout
								.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
										.addComponent(lblNewLabel).addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(txt_tituloEid, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(txt_usuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.LEADING,
										groupLayout.createSequentialGroup().addGap(149).addComponent(lblNewLabel_1_1)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(
														txt_responsavel, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 47,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(cb_prazo, GroupLayout.PREFERRED_SIZE, 98,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
												.addComponent(lblNewLabel_2).addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(cb_status, GroupLayout.PREFERRED_SIZE, 98,
														GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup().addComponent(lblNewLabel_2_1_1)
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(cb_cadastro,
														GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup().addComponent(btnNewButton_2).addGap(10)
								.addComponent(btn_detalhes).addGap(10).addComponent(btn_excluir)))
						.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel)
										.addComponent(txt_tituloEid, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_1)
										.addComponent(txt_usuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(cb_prazo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_2).addComponent(cb_status, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup().addGap(4).addComponent(lblNewLabel_2_1)))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_1_1)
								.addComponent(txt_responsavel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_2_1_1).addComponent(cb_cadastro, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(btnNewButton_2)
								.addComponent(btn_detalhes).addComponent(btn_excluir))
						.addGap(15)));
		frmPrincipal.getContentPane().setLayout(groupLayout);

		/*
		 * if (usuariodto.getAcesso() < 2) { mnAdm.setVisible(false);
		 * btn_excluir.setEnabled(false); }
		 */
	}

	// métodos
	private void carregarTabela(TableModel model) {
		table.setModel(model);

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

					funcionario.alterarNC(ncsdto);

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Problema ao alterar dados!" + " - " + e.getMessage());
					e.printStackTrace();
				}
			}
		});

	}

	private void logoff() {
		frmPrincipal.dispose();
		new LoginForm().setVisible(true);
	}

	public void setVisible(boolean b) {
		frmPrincipal.setVisible(b);
	}
}
