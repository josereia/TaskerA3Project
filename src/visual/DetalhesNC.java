package visual;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class DetalhesNC extends JDialog{
	private static final long serialVersionUID = 1L;
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	private JTextField desc;
	private JTextField prazo;
	private JTextField dt_cadastro;
	private JTextField status;
	private JTextField user;
	private JTextField reponsavel;
	private JTextField titulo;
	
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
	
	public DetalhesNC() {
		initialize();
	}
	
	private void initialize() {
		
		// icone da janela
		setIconImage(toolkit.getImage(this.getClass().getResource("/logo.png")));
		setResizable(false);
		setTitle("Detalhes da NC");
		setBounds(100, 100, 410, 378);
		// faz com que a janela inicie no centro da tela
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblNewLabel_1_1 = new JLabel("Detalhes:");
		lblNewLabel_1_1.setBounds(36, 38, 55, 22);
		contentPanel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Descri\u00E7\u00E3o:");
		lblNewLabel_1_1_1_1.setBounds(36, 121, 55, 14);
		contentPanel.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("Respons\u00E1vel:");
		lblNewLabel_1_1_1_2.setBounds(36, 301, 70, 14);
		contentPanel.add(lblNewLabel_1_1_1_2);
		
		JLabel lblNewLabel_1_1_1_3 = new JLabel("Prazo:");
		lblNewLabel_1_1_1_3.setBounds(36, 71, 33, 14);
		contentPanel.add(lblNewLabel_1_1_1_3);
		
		JLabel lblNewLabel_1_1_1_4 = new JLabel("Data do Cadastro:");
		lblNewLabel_1_1_1_4.setBounds(181, 71, 89, 14);
		contentPanel.add(lblNewLabel_1_1_1_4);
		
		JLabel lblNewLabel_1_1_1_5 = new JLabel("Status:");
		lblNewLabel_1_1_1_5.setBounds(193, 121, 55, 14);
		contentPanel.add(lblNewLabel_1_1_1_5);
		
		JLabel lblNewLabel_1_1_1_2_1 = new JLabel("Usu\u00E1rio:");
		lblNewLabel_1_1_1_2_1.setBounds(224, 301, 40, 14);
		contentPanel.add(lblNewLabel_1_1_1_2_1);
		
		titulo = new JTextField();
		titulo.setBounds(85, 39, 271, 20);
		contentPanel.add(titulo);
		titulo.setColumns(10);
		
		desc = new JTextField();
		desc.setBounds(36, 147, 347, 143);
		contentPanel.add(desc);
		desc.setColumns(10);
		
		reponsavel = new JTextField();
		reponsavel.setBounds(101, 298, 113, 20);
		contentPanel.add(reponsavel);
		reponsavel.setColumns(10);
		
		prazo = new JTextField();
		prazo.setBounds(67, 71, 86, 20);
		contentPanel.add(prazo);
		prazo.setColumns(10);
		
		dt_cadastro = new JTextField();
		dt_cadastro.setBounds(270, 68, 86, 20);
		contentPanel.add(dt_cadastro);
		dt_cadastro.setColumns(10);
		
		status = new JTextField();
		status.setBounds(230, 118, 139, 20);
		contentPanel.add(status);
		status.setColumns(10);
		
		user = new JTextField();
		user.setBounds(264, 298, 119, 20);
		contentPanel.add(user);
		user.setColumns(10);
		
		
	}
} 
