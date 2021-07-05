package visual;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.EmptyBorder;

import controller.Administrador;
import dao.EmpresaDAO;
import dao.UsuarioDAO;
import dto.EmpresaDTO;
import dto.UsuarioDTO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.Color;

public class CadastroUsers extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txt_nome;
	private JTextField txt_sobrenome;
	private JTextField txt_email;
	private JTextField txt_login;
	private JPasswordField txt_senha;
	private JTextField txt_empresa;
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	private JTextField textField;
	private JComboBox<String> comboBox;
	private Administrador administrador;

	public static void main(String[] args) {
		try {
			// define o estilo da janela para o estilo padrão do sistema operacional
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
			CadastroUsers dialog = new CadastroUsers();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * 
	 * @param actionListener
	 * @param usuariodto2
	 * @param frmPrincipal
	 */

	public CadastroUsers() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		initialize(false);
	}

	public CadastroUsers(JFrame principal, Administrador administrador) {
		super(principal, true);
		this.administrador = administrador;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		initialize(true);
	}

	private void initialize(boolean hide) {
		// icone da janela
		setIconImage(toolkit.getImage(this.getClass().getResource("/logo.png")));
		setResizable(false);
		setTitle("Cadastrar Funcion\u00E1rio");
		setBounds(100, 100, 670, 362);
		// faz com que a janela inicie no centro da tela
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setInheritsPopupMenu(false);
		lblNewLabel_2.setIconTextGap(0);
		lblNewLabel_2.setIcon(new ImageIcon(toolkit.getImage(this.getClass().getResource("/loginImg.png"))));
		lblNewLabel_2.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setAlignmentY(0.0f);
		lblNewLabel_2.setBounds(0, 0, 180, 291);
		contentPanel.add(lblNewLabel_2);

		JLabel lblNewLabel_1_1 = new JLabel("Usu\u00E1rio");
		lblNewLabel_1_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(216, 0, 108, 26);
		contentPanel.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1 = new JLabel("Nome:*");
		lblNewLabel_1.setBounds(190, 37, 215, 14);
		contentPanel.add(lblNewLabel_1);

		txt_nome = new JTextField();
		txt_nome.setColumns(10);
		txt_nome.setBounds(190, 51, 215, 20);
		contentPanel.add(txt_nome);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Sobrenome:*");
		lblNewLabel_1_1_1_1.setBounds(190, 82, 215, 14);
		contentPanel.add(lblNewLabel_1_1_1_1);

		txt_sobrenome = new JTextField();
		txt_sobrenome.setColumns(10);
		txt_sobrenome.setBounds(190, 96, 215, 20);
		contentPanel.add(txt_sobrenome);

		JLabel lblNewLabel_1_1_1 = new JLabel("E-mail:*");
		lblNewLabel_1_1_1.setBounds(190, 127, 215, 14);
		contentPanel.add(lblNewLabel_1_1_1);

		txt_email = new JTextField();
		txt_email.setColumns(10);
		txt_email.setBounds(190, 141, 215, 20);
		contentPanel.add(txt_email);

		JLabel lblNewLabel_1_1_1_2 = new JLabel("Login:*");
		lblNewLabel_1_1_1_2.setBounds(190, 172, 215, 14);
		contentPanel.add(lblNewLabel_1_1_1_2);

		txt_login = new JTextField();
		txt_login.setColumns(10);
		txt_login.setBounds(190, 186, 215, 20);
		contentPanel.add(txt_login);

		JLabel lblNewLabel_1_1_1_3 = new JLabel("Senha:*");
		lblNewLabel_1_1_1_3.setBounds(190, 217, 215, 14);
		contentPanel.add(lblNewLabel_1_1_1_3);

		txt_senha = new JPasswordField();
		txt_senha.setBounds(190, 231, 215, 20);
		contentPanel.add(txt_senha);

		JLabel lblNewLabel_1_1_1_2_1 = new JLabel("Nome comercial:*");

		lblNewLabel_1_1_1_2_1.setBounds(427, 107, 215, 14);
		contentPanel.add(lblNewLabel_1_1_1_2_1);

		txt_empresa = new JTextField();
		txt_empresa.setColumns(10);
		txt_empresa.setBounds(427, 121, 215, 20);
		contentPanel.add(txt_empresa);

		JLabel lblNewLabel_1_1_1_2_1_1 = new JLabel("CNPJ:*");
		lblNewLabel_1_1_1_2_1_1.setBounds(427, 152, 215, 14);
		contentPanel.add(lblNewLabel_1_1_1_2_1_1);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(427, 166, 215, 20);
		contentPanel.add(textField);

		comboBox = new JComboBox<String>();
		for (String acesso : new UsuarioDAO().listNiveisAcesso()) {
			comboBox.addItem(acesso);
		}
		comboBox.setBounds(279, 262, 126, 22);
		contentPanel.add(comboBox);

		JPanel panel = new JPanel();

		panel.setBackground(Color.BLACK);
		panel.setBounds(415, 51, 1, 233);
		contentPanel.add(panel);

		JLabel lblNewLabel = new JLabel("N\u00EDvel de acesso:*");
		lblNewLabel.setBounds(190, 262, 95, 14);
		contentPanel.add(lblNewLabel);

		JLabel lblNewLabel_1_1_2 = new JLabel("Empresa");

		lblNewLabel_1_1_2.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_2.setBounds(476, 48, 108, 26);
		contentPanel.add(lblNewLabel_1_1_2);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Cadastrar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cadastrar();
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}

		if (hide) {
			setBounds(100, 100, 440, 362);
			panel.setVisible(false);
			lblNewLabel_1_1_2.setVisible(false);
			txt_empresa.setVisible(false);
			textField.setVisible(false);
			lblNewLabel_1_1_1_2_1.setVisible(false);
			lblNewLabel_1_1_1_2_1_1.setVisible(false);
		}
	}

	public void cadastrar() {
		if(txt_nome.getText().isEmpty() || txt_sobrenome.getText().isEmpty() || txt_email.getText().isEmpty() || txt_login.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Nenhum campo pode estar vazio!");
		}else {
			if (administrador != null) {
				UsuarioDTO objusuariodto = new UsuarioDTO();

				objusuariodto.setNome(txt_nome.getText());
				objusuariodto.setSobrenome(txt_sobrenome.getText());
				objusuariodto.setEmail(txt_email.getText());
				objusuariodto.setLogin(txt_login.getText());
				objusuariodto.setSenha(String.valueOf(txt_senha.getPassword()));
				objusuariodto.setAcesso(comboBox.getSelectedIndex() + 1);

				administrador.cadastrarUsuario(objusuariodto);
			} else {
				EmpresaDTO empresadto = new EmpresaDTO();
				UsuarioDTO objusuariodto = new UsuarioDTO();

				empresadto.setNomeFantasia(txt_empresa.getText());
				empresadto.setCnpj(textField.getText());

				objusuariodto.setNome(txt_nome.getText());
				objusuariodto.setSobrenome(txt_sobrenome.getText());
				objusuariodto.setEmail(txt_email.getText());
				objusuariodto.setLogin(txt_login.getText());
				objusuariodto.setSenha(String.valueOf(txt_senha.getPassword()));
				objusuariodto.setAcesso(comboBox.getSelectedIndex() + 1);
				objusuariodto.setEmpresa(empresadto.getNomeFantasia());

				new EmpresaDAO().create(empresadto);
				new UsuarioDAO().create(objusuariodto);
			}
		}
		
		
	}
}
