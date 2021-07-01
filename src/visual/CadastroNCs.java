package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Funcionario;
import dto.NcsDTO;
import dto.UsuarioDTO;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastroNCs extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();

	private UsuarioDTO usuariodto;
	private Toolkit toolkit = Toolkit.getDefaultToolkit();
	private JTextField txt_titulo;
	private JTextField txt_responsavel;
	private JTextField txt_prazo;
	private JTextArea txt_descricao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			// define o estilo da janela para o estilo padrão do sistema operacional
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
			CadastroNCs dialog = new CadastroNCs();
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
	public CadastroNCs() {
		initialize();
	}

	public CadastroNCs(JFrame frmPrincipal, UsuarioDTO usuariodto) {
		super(frmPrincipal, true);
		this.usuariodto = usuariodto;
		initialize();
	}

	private void initialize() {
		// icone da janela
		setIconImage(toolkit.getImage(this.getClass().getResource("/logo.png")));
		setResizable(false);
		setTitle("Cadastrar NC");
		setBounds(100, 100, 410, 410);
		// faz com que a janela inicie no centro da tela
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("T\u00EDtulo:*");
		lblNewLabel_1.setBounds(27, 24, 69, 14);
		contentPanel.add(lblNewLabel_1);

		txt_titulo = new JTextField();
		txt_titulo.setColumns(10);
		txt_titulo.setBounds(27, 38, 270, 20);
		contentPanel.add(txt_titulo);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Respons\u00E1vel:*");
		lblNewLabel_1_1_1_1.setBounds(27, 69, 89, 14);
		contentPanel.add(lblNewLabel_1_1_1_1);

		txt_responsavel = new JTextField();
		txt_responsavel.setColumns(10);
		txt_responsavel.setBounds(27, 83, 270, 20);
		contentPanel.add(txt_responsavel);

		JLabel lblNewLabel_1_1_1 = new JLabel("Prazo: ");
		lblNewLabel_1_1_1.setBounds(27, 114, 69, 14);
		contentPanel.add(lblNewLabel_1_1_1);

		txt_prazo = new JTextField();
		txt_prazo.setColumns(10);
		txt_prazo.setBounds(27, 128, 134, 20);
		contentPanel.add(txt_prazo);

		JLabel lblNewLabel_1_1 = new JLabel("Descri\u00E7\u00E3o:* ");
		lblNewLabel_1_1.setBounds(27, 159, 69, 14);
		contentPanel.add(lblNewLabel_1_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 174, 350, 163);
		contentPanel.add(scrollPane);

		txt_descricao = new JTextArea();
		scrollPane.setViewportView(txt_descricao);
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
	}

	public void cadastrar() {
		NcsDTO ncsdto = new NcsDTO();
		ncsdto.setTitulo(txt_titulo.getText());
		ncsdto.setDescricao(txt_descricao.getText());
		ncsdto.setResponsavel(txt_responsavel.getText());
		ncsdto.setPrazo(txt_prazo.getText());
		ncsdto.setUsuario(usuariodto.getNome());
		ncsdto.setUsuarioEmpresa(usuariodto.getEmpresa());
		
		new Funcionario().cadastrarNC(ncsdto);
	}
}
