package br.com.josereia.tasker.visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;

public class SobreForm extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	Toolkit toolkit = Toolkit.getDefaultToolkit();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			// define o estilo da janela para o estilo padrão do sistema operacional
			javax.swing.UIManager.setLookAndFeel(new FlatDarkLaf());
			SobreForm dialog = new SobreForm();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SobreForm() {
		// icone da janela
		setIconImage(toolkit.getImage(this.getClass().getResource("/logo.png")));
		setTitle("Sobre");
		setBounds(100, 100, 314, 310);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		// faz com que a janela inicie no centro da tela
		setLocationRelativeTo(null);

		JLabel lblNewLabel = new JLabel("O projeto:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 11, 72, 14);
		contentPanel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(
				"<html>O projeto tem como prop\u00F3sito criar o MVP de um programa em Java que organiza não-conformidades. <br>A ideia surgiu de um trabalho desenvolvido na UC Modelagem de Software - UNISOCIESC.</html>");
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setBounds(10, 36, 240, 100);
		contentPanel.add(lblNewLabel_1);

		JLabel lblContribuintes = new JLabel("Contribuintes:");
		lblContribuintes.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblContribuintes.setBounds(10, 132, 95, 14);
		contentPanel.add(lblContribuintes);

		JLabel lblNewLabel_2 = new JLabel("João Sereia");
		lblNewLabel_2.setBounds(10, 157, 120, 14);
		contentPanel.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Lucas Ziman");
		lblNewLabel_2_1.setBounds(10, 178, 120, 14);
		contentPanel.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_1_1 = new JLabel("Christopher Schmücker");
		lblNewLabel_2_1_1.setBounds(10, 198, 120, 14);
		contentPanel.add(lblNewLabel_2_1_1);

		JLabel lblNewLabel_2_1_1_1 = new JLabel("Gabriel Mohr");
		lblNewLabel_2_1_1_1.setBounds(10, 217, 120, 14);
		contentPanel.add(lblNewLabel_2_1_1_1);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
}
