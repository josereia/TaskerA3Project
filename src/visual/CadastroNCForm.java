package visual;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;

import dto.NcsDTO;
import dto.UsuarioDTO;

import javax.swing.JTextField;

import dao.NcsDAO;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastroNCForm {

    private JFrame CadastroNCForm;
    private UsuarioDTO usuariodto;
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    private JPanel panel;
    private JTextField Titulo;
    private JTextField Prazo;
    private JTextField Responsavel;
    private JTextField Descricao;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // define o estilo da janela para o estilo padrão do sistema operacional
                    javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
                    CadastroNCForm window = new CadastroNCForm();
                    window.CadastroNCForm.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    /**
     * Create the application.
     */
    public CadastroNCForm() {
        initialize();
    }
    public CadastroNCForm(UsuarioDTO usuariodto) {
        if (usuariodto == null) {
            new LoginForm().setVisible(true);
        } else {
            this.usuariodto = usuariodto;
            initialize();
        }
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        CadastroNCForm = new JFrame();
        CadastroNCForm.setBounds(100, 100, 600, 400);
        CadastroNCForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //icone da janela
        CadastroNCForm.setIconImage(toolkit.getImage(this.getClass().getResource("/logo.png")));
        // faz com que a janela inicie no centro da tela
        CadastroNCForm.setLocationRelativeTo(null);
        
        panel = new JPanel();
        panel.setLayout(null);
        CadastroNCForm.getContentPane().add(panel, BorderLayout.CENTER);
        
        Titulo = new JTextField();
        Titulo.setColumns(10);
        Titulo.setBounds(200, 56, 200, 20);
        panel.add(Titulo);
        
        //Nao consegui fazer a text box funcionar, caso alguem saiba fique a vontade para trocar
        Descricao = new JTextField();
        Descricao.setColumns(10);
        Descricao.setBounds(165, 193, 270, 29);
        panel.add(Descricao);
        
        Prazo = new JTextField();
        Prazo.setColumns(10);
        Prazo.setBounds(200, 140, 200, 20);
        panel.add(Prazo);
        
        Responsavel = new JTextField();
        Responsavel.setColumns(10);
        Responsavel.setBounds(200, 98, 200, 20);
        panel.add(Responsavel);
        
        JLabel lblNewLabel_1 = new JLabel("T\u00EDtulo:*");
        lblNewLabel_1.setBounds(200, 43, 69, 14);
        panel.add(lblNewLabel_1);
        
        JLabel lblNewLabel_1_1 = new JLabel("Descri\u00E7\u00E3o:* ");
        lblNewLabel_1_1.setBounds(200, 180, 69, 14);
        panel.add(lblNewLabel_1_1);
        
        JLabel lblNewLabel_1_1_1 = new JLabel("Prazo: ");
        lblNewLabel_1_1_1.setBounds(200, 127, 69, 14);
        panel.add(lblNewLabel_1_1_1);
        
        JLabel lblNewLabel_1_1_1_1 = new JLabel("Respons\u00E1vel:  ");
        lblNewLabel_1_1_1_1.setBounds(200, 83, 89, 14);
        panel.add(lblNewLabel_1_1_1_1);
        
        JButton btnNewButton = new JButton("Enviar");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		cadastrar();
        	}
        });
        btnNewButton.setBounds(240, 280, 140, 23);
        panel.add(btnNewButton);
        
        
    }
    public void setVisible(boolean b) {
        // TODO Auto-generated method stub
        CadastroNCForm.setVisible(b);
    }
    
    public void cadastrar() {
    	NcsDTO ncsdto = new NcsDTO();
    	ncsdto.setTitulo(Titulo.getText());
    	ncsdto.setDescricao(Descricao.getText());
    	ncsdto.setResponsavel(Responsavel.getText());
    	ncsdto.setPrazo(Prazo.getText());
    	ncsdto.setId(usuariodto.getId());
    	new NcsDAO().create(ncsdto);
    }
}