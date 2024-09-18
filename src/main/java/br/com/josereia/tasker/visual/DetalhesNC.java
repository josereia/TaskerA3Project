package br.com.josereia.tasker.visual;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatDarkLaf;

import br.com.josereia.tasker.dao.NcsDAO;
import br.com.josereia.tasker.dto.NcsDTO;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class DetalhesNC extends JDialog{
	private static final long serialVersionUID = 1L;
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	private JTextField prazo;
	private JTextField dt_cadastro;
	private JTextField status;
	private JTextField user;
	private JTextField reponsavel;
	private JTextField titulo;
	private NcsDTO ncsdto;
	private JTextArea descricao;
	
	public static void main(String[] args) {
		try {
			// define o estilo da janela para o estilo padrão do sistema operacional
			javax.swing.UIManager.setLookAndFeel(new FlatDarkLaf());
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
	
	public DetalhesNC(int id) {
		this.ncsdto = new NcsDAO().read(id);
		initialize();
	}
	
	private void initialize() {
		
		// icone da janela
		setIconImage(toolkit.getImage(this.getClass().getResource("/logo.png")));
		setResizable(false);
		setTitle("Detalhes da NC");
		setBounds(100, 100, 420, 380);
		// faz com que a janela inicie no centro da tela
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblNewLabel_1_1 = new JLabel("Título:");
		lblNewLabel_1_1.setBounds(36, 11, 55, 22);
		contentPanel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Descrição:");
		lblNewLabel_1_1_1_1.setBounds(36, 121, 55, 14);
		contentPanel.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("Responsável:");
		lblNewLabel_1_1_1_2.setBounds(10, 321, 70, 14);
		contentPanel.add(lblNewLabel_1_1_1_2);
		
		JLabel lblNewLabel_1_1_1_3 = new JLabel("Prazo:");
		lblNewLabel_1_1_1_3.setBounds(36, 67, 33, 14);
		contentPanel.add(lblNewLabel_1_1_1_3);
		
		JLabel lblNewLabel_1_1_1_4 = new JLabel("Data do Cadastro:");
		lblNewLabel_1_1_1_4.setBounds(181, 67, 89, 14);
		contentPanel.add(lblNewLabel_1_1_1_4);
		
		JLabel lblNewLabel_1_1_1_5 = new JLabel("Status:");
		lblNewLabel_1_1_1_5.setBounds(180, 95, 55, 14);
		contentPanel.add(lblNewLabel_1_1_1_5);
		
		JLabel lblNewLabel_1_1_1_2_1 = new JLabel("Usuário:");
		lblNewLabel_1_1_1_2_1.setBounds(230, 321, 40, 14);
		contentPanel.add(lblNewLabel_1_1_1_2_1);
		
		titulo = new JTextField();
		titulo.setBounds(85, 12, 271, 20);
		contentPanel.add(titulo);
		titulo.setColumns(10);
		
		reponsavel = new JTextField();
		reponsavel.setBounds(77, 318, 113, 20);
		contentPanel.add(reponsavel);
		reponsavel.setColumns(10);
		
		prazo = new JTextField();
		prazo.setBounds(73, 64, 86, 20);
		contentPanel.add(prazo);
		prazo.setColumns(10);
		
		dt_cadastro = new JTextField();
		dt_cadastro.setBounds(270, 64, 86, 20);
		contentPanel.add(dt_cadastro);
		dt_cadastro.setColumns(10);
		
		status = new JTextField();
		status.setBounds(217, 92, 139, 20);
		contentPanel.add(status);
		status.setColumns(10);
		
		user = new JTextField();
		user.setBounds(275, 318, 119, 20);
		contentPanel.add(user);
		user.setColumns(10);
		
		descricao = new JTextArea();
		descricao.setBounds(10, 139, 384, 171);
		contentPanel.add(descricao);
		
		carregarCampos();
	}
	
	private void carregarCampos() {
		titulo.setText(ncsdto.getTitulo());
		descricao.setText(ncsdto.getDescricao());
		reponsavel.setText(ncsdto.getResponsavel());
		prazo.setText(ncsdto.getPrazo());
		dt_cadastro.setText(ncsdto.getDataCadastro());
		user.setText(ncsdto.getUsuario());
		status.setText(ncsdto.getStatus());
	}
} 
