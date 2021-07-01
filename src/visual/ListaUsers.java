package visual;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class ListaUsers {

	private JFrame frame;
	// toolkit
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	private JTable table;
	private JTextField textField;

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
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ListaUsers() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 600, 350);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// icone da janela
		frame.setIconImage(toolkit.getImage(this.getClass().getResource("/logo.png")));
		// faz com que a janela inicie no centro da tela
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 44, 564, 222);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JButton btnCadastrar = new JButton("Cadastrar+");
		btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCadastrar.setBounds(477, 277, 97, 23);
		frame.getContentPane().add(btnCadastrar);

		JButton btnExcluir = new JButton("Excluir-");
		btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExcluir.setBounds(374, 277, 97, 23);
		frame.getContentPane().add(btnExcluir);

		textField = new JTextField();
		textField.setBounds(413, 11, 161, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Filtrar:");
		lblNewLabel.setBounds(374, 14, 46, 14);
		frame.getContentPane().add(lblNewLabel);
	}

}
