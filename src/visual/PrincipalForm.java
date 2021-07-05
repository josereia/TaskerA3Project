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

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.RowFilter;
import javax.swing.JTextField;
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
	private TableRowSorter<TableModel> rowSorter;

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

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Sobre");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SobreForm().setVisible(true);
			}
		});
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

		JButton btn_excluir = new JButton("Excluir");
		btn_excluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resposta = JOptionPane.showConfirmDialog(null, "Quer realmente exluir?", "Excluir NC",
						JOptionPane.YES_NO_OPTION);
				if (resposta == JOptionPane.YES_OPTION) {
					new Administrador(usuariodto).excluirNC(
							Integer.parseInt(String.valueOf(table.getModel().getValueAt(table.getSelectedRow(), 0))));
					carregarTabela();
				}
			}
		});

		JButton btn_detalhes = new JButton("Detalhes");
		btn_detalhes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DetalhesNC(Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 0).toString())).setVisible(true);
			}
		});

		JButton btnNewButton_2 = new JButton("Inserir");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroNCs cadastroncs = new CadastroNCs(frmPrincipal, usuariodto);
				cadastroncs.setVisible(true);

				cadastroncs.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						carregarTabela();
					}
				});
			}
		});

		JScrollPane scrollPane = new JScrollPane();

		table = new JTable();
		scrollPane.setViewportView(table);
		carregarTabela();

		JLabel lblNewLabel = new JLabel("Filtro:");

		txt_tituloEid = new JTextField();
		txt_tituloEid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String text = txt_tituloEid.getText();

				if (text.trim().length() == 0) {
					rowSorter.setRowFilter(null);
					carregarTabela();
				} else {
					rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				}
			}
		});
		txt_tituloEid.setColumns(10);

		GroupLayout groupLayout = new GroupLayout(frmPrincipal.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGap(397)
							.addComponent(btnNewButton_2)
							.addGap(10)
							.addComponent(btn_detalhes)
							.addGap(10)
							.addComponent(btn_excluir))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txt_tituloEid, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(txt_tituloEid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_2)
						.addComponent(btn_detalhes)
						.addComponent(btn_excluir))
					.addContainerGap())
		);
		frmPrincipal.getContentPane().setLayout(groupLayout);

		
		if(usuariodto.getAcesso() == 1) {
			mnAdm.setVisible(false);
		}
	}

	// métodos
	private void carregarTabela() {
		table.setModel(new NcsDAO().read(funcionario.getEmpresa()));

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

		rowSorter = new TableRowSorter<>(table.getModel());
		table.setRowSorter(rowSorter);
	}

	private void logoff() {
		frmPrincipal.dispose();
		new LoginForm().setVisible(true);
	}
	
	public void setVisible(boolean b) {
		frmPrincipal.setVisible(b);
	}
}
