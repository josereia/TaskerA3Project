package visual;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;

import dto.UsuarioDTO;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class CadastroNCForm {

    private JFrame CadastroNCForm;
    private UsuarioDTO usuariodto;
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    private JPanel panel;
    private JTextField Titulo;
    private JTextField Prazo;
    private JTextField Responsavel;

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
        
        Prazo = new JTextField();
        Prazo.setColumns(10);
        Prazo.setBounds(200, 249, 200, 20);
        panel.add(Prazo);
        
        JLabel lblNewLabel_1 = new JLabel("T\u00EDtulo:*");
        lblNewLabel_1.setBounds(200, 43, 69, 14);
        panel.add(lblNewLabel_1);
        
        JLabel lblNewLabel_1_1 = new JLabel("Descri\u00E7\u00E3o:* ");
        lblNewLabel_1_1.setBounds(200, 87, 69, 14);
        panel.add(lblNewLabel_1_1);
        
        JLabel lblNewLabel_1_1_1 = new JLabel("Prazo: ");
        lblNewLabel_1_1_1.setBounds(200, 235, 69, 14);
        panel.add(lblNewLabel_1_1_1);
        
        JButton btnNewButton = new JButton("Enviar");
        btnNewButton.setBounds(240, 280, 140, 23);
        panel.add(btnNewButton);
        
        Responsavel = new JTextField();
        Responsavel.setColumns(10);
        Responsavel.setBounds(200, 204, 200, 20);
        panel.add(Responsavel);
        
        JLabel lblNewLabel_1_1_1_1 = new JLabel("Respons\u00E1vel:  ");
        lblNewLabel_1_1_1_1.setBounds(200, 191, 89, 14);
        panel.add(lblNewLabel_1_1_1_1);
        
        JTextArea textArea = new JTextArea();
        textArea.setBorder(UIManager.getBorder("TextField.border"));
        textArea.setBounds(200, 102, 200, 78);
        panel.add(textArea);
    }

    public void setVisible(boolean b) {
        // TODO Auto-generated method stub
        CadastroNCForm.setVisible(b);
    }
}