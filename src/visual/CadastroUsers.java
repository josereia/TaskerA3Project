package visual;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.EmptyBorder;

import dao.UsuarioDAO;
import dto.UsuarioDTO;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class CadastroUsers extends JDialog{
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txt_nome;
	private JTextField txt_sobrenome;
	private JTextField txt_email;
	private JTextField txt_login;
	private JPasswordField txt_senha;
	private JTextField txt_empresa;
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	
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
	 * @param actionListener 
	 * @param usuariodto2 
	 * @param frmPrincipal 
	 */
	
	public CadastroUsers() {
		initialize();
	}
	
	private void initialize() {
		
		// icone da janela
		setIconImage(toolkit.getImage(this.getClass().getResource("/logo.png")));
		setResizable(false);
		setTitle("Cadastrar NC");
		setBounds(100, 100, 410, 378);
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
		lblNewLabel_2.setBounds(0, 0, 157, 316);
		contentPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1_1 = new JLabel("Cadastro de Usu\u00E1rios");
		lblNewLabel_1_1.setBounds(212, 0, 108, 26);
		contentPanel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1 = new JLabel("Nome:*");
		lblNewLabel_1.setBounds(167, 37, 215, 14);
		contentPanel.add(lblNewLabel_1);
		
		txt_nome = new JTextField();
		txt_nome.setColumns(10);
		txt_nome.setBounds(167, 51, 215, 20);
		contentPanel.add(txt_nome);
		
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Sobrenome:*");
		lblNewLabel_1_1_1_1.setBounds(167, 82, 215, 14);
		contentPanel.add(lblNewLabel_1_1_1_1);
		
		txt_sobrenome = new JTextField();
		txt_sobrenome.setColumns(10);
		txt_sobrenome.setBounds(167, 96, 215, 20);
		contentPanel.add(txt_sobrenome);
		
		
		JLabel lblNewLabel_1_1_1 = new JLabel("E-mail:*");
		lblNewLabel_1_1_1.setBounds(167, 127, 215, 14);
		contentPanel.add(lblNewLabel_1_1_1);
		
		txt_email = new JTextField();
		txt_email.setColumns(10);
		txt_email.setBounds(167, 141, 215, 20);
		contentPanel.add(txt_email);
		
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("Login:* ");
		lblNewLabel_1_1_1_2.setBounds(167, 172, 215, 14);
		contentPanel.add(lblNewLabel_1_1_1_2);
		
		txt_login = new JTextField();
		txt_login.setColumns(10);
		txt_login.setBounds(167, 186, 215, 20);
		contentPanel.add(txt_login);
		
		
		JLabel lblNewLabel_1_1_1_3 = new JLabel("Senha:*");
		lblNewLabel_1_1_1_3.setBounds(167, 217, 215, 14);
		contentPanel.add(lblNewLabel_1_1_1_3);
		
		txt_senha = new JPasswordField();
		txt_senha.setBounds(167, 231, 215, 20);
		contentPanel.add(txt_senha);
		
		
		JLabel lblNewLabel_1_1_1_2_1 = new JLabel("Empresa:* ");
		lblNewLabel_1_1_1_2_1.setBounds(167, 262, 215, 14);
		contentPanel.add(lblNewLabel_1_1_1_2_1);
		
		txt_empresa = new JTextField();
		txt_empresa.setColumns(10);
		txt_empresa.setBounds(167, 276, 215, 20);
		contentPanel.add(txt_empresa);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Cadastrar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cadastraru();
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	public void cadastraru() {
		UsuarioDTO usuariodto = new UsuarioDTO();
		usuariodto.setNome(txt_nome.getText());
		usuariodto.setSobrenome(txt_sobrenome.getText());
		usuariodto.setEmail(txt_email.getText());
		usuariodto.setLogin(txt_login.getText());
		usuariodto.setSenha(String.valueOf(txt_senha.getPassword()));
		usuariodto.setEmpresa(txt_empresa.getText());
		new UsuarioDAO().create(usuariodto);
	}
} 
