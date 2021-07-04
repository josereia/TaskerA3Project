package visual;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;

import dao.NcsDAO;
import dao.UsuarioDAO;
import dto.NcsDTO;
import dto.UsuarioDTO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class ListaUsers {

	private JFrame frmGerenciamentoDeUsurios;
	// toolkit
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	private JTable table;
	private JTextField textField;
	private UsuarioDTO usuariodto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// define o estilo da janela para o estilo padrão do sistema operacionall
					javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
					ListaUsers window = new ListaUsers();
					window.frmGerenciamentoDeUsurios.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param usuariodto2 
	 */
	public ListaUsers() {
		initialize();
	}
	public ListaUsers(UsuarioDTO usuariodto) {
		this.usuariodto = usuariodto;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frmGerenciamentoDeUsurios = new JFrame();
		frmGerenciamentoDeUsurios.setTitle("Gerenciamento de funcion\u00E1rios");
		frmGerenciamentoDeUsurios.setBounds(100, 100, 600, 350);
		frmGerenciamentoDeUsurios.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// icone da janela
		frmGerenciamentoDeUsurios.setIconImage(toolkit.getImage(this.getClass().getResource("/logo.png")));
		// faz com que a janela inicie no centro da tela
		frmGerenciamentoDeUsurios.setLocationRelativeTo(null);

		JScrollPane scrollPane = new JScrollPane();

		table = new JTable();
		scrollPane.setViewportView(table);
		carregarTabela();

		CadastroUsers cadastrousers = new CadastroUsers(frmGerenciamentoDeUsurios, usuariodto);
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrousers.setVisible(true);
			}
		});
		cadastrousers.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				carregarTabela();
			}
		});
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 11));

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 11));

		textField = new JTextField();
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Filtrar:");
		GroupLayout groupLayout = new GroupLayout(frmGerenciamentoDeUsurios.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(374)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(39)
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)))
					.addGap(10))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(374, Short.MAX_VALUE)
					.addComponent(btnCadastrar)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNewLabel)))
					.addGap(13)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnExcluir)
						.addComponent(btnCadastrar))
					.addContainerGap())
		);
		frmGerenciamentoDeUsurios.getContentPane().setLayout(groupLayout);
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		frmGerenciamentoDeUsurios.setVisible(b);
	}
	
	public void carregarTabela() {
		table.setModel(new UsuarioDAO().read(usuariodto.getEmpresa()));
		
		table.getModel().addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent arg0) {

				try {
					UsuarioDTO objusuariodto = new UsuarioDTO();

					objusuariodto.setIdUsuario(
							Integer.parseInt(String.valueOf(table.getModel().getValueAt(table.getSelectedRow(), 0))));
					objusuariodto.setNome((String) table.getModel().getValueAt(table.getSelectedRow(), 1));
					objusuariodto.setSobrenome((String) table.getModel().getValueAt(table.getSelectedRow(), 2));
					objusuariodto.setEmail((String) table.getModel().getValueAt(table.getSelectedRow(), 3));
					objusuariodto.setLogin((String) table.getModel().getValueAt(table.getSelectedRow(), 4));
					objusuariodto.setSenha((String) table.getModel().getValueAt(table.getSelectedRow(), 5));
					objusuariodto.setEmpresa((String) table.getModel().getValueAt(table.getSelectedRow(), 6));
					objusuariodto.setAcesso(new UsuarioDAO().readNivelAcesso((String) table.getModel().getValueAt(table.getSelectedRow(), 7)));

					new UsuarioDAO().update(objusuariodto);

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Problema ao alterar dados!" + " - " + e.getMessage());
					e.printStackTrace();
				}
			}
		});
	}

}
