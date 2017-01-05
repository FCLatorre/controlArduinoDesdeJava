package arduinoLucespkg;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.zu.ardulink.Link;
import org.zu.ardulink.protocol.IProtocol;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.JTabbedPane;
import javax.swing.JComponent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class SwingMain extends JFrame {
	private int count=0;
	private JPanel contentPane;
	private Link link;
	private enum Botones {OFF, AUTO, POSICION, CRUCE, RAFAGAS, CARRETERA};

	/**
	 * Launch the application.
	 */

	protected Component makeTextPanel(String text) {
	    JPanel panel = new JPanel(false);
	    JLabel filler = new JLabel(text);
	    filler.setHorizontalAlignment(JLabel.CENTER);
	    panel.setLayout(new GridLayout(1, 1));
	    panel.add(filler);
	    return panel;
	}
	/**
	 * Create the frame.
	 */
	public SwingMain(Link link) {
		this.link=link;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{319, 283, 0};
		gbl_contentPane.rowHeights = new int[]{34, 190, 36, 171, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.insets = new Insets(0, 0, 5, 5);
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 1;
		contentPane.add(tabbedPane, gbc_tabbedPane);
		
		JComponent panel1 = (JPanel)makeTextPanel("Panel Alejnadro");
        tabbedPane.addTab("Visual", panel1);
        
        JComponent panel2 = (JPanel)makeTextPanel("Panel Fernando");
        tabbedPane.addTab("Gráfica", panel2);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 3;
		contentPane.add(panel_1, gbc_panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 3;
		contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{161, 176, 94, 55, 0};
		gbl_panel.rowHeights = new int[]{45, 23, 23, 23, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton btnLargas = new JButton("Largas");
		GridBagConstraints gbc_btnLargas = new GridBagConstraints();
		gbc_btnLargas.anchor = GridBagConstraints.NORTH;
		gbc_btnLargas.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLargas.insets = new Insets(0, 0, 5, 0);
		gbc_btnLargas.gridx = 3;
		gbc_btnLargas.gridy = 1;
		panel.add(btnLargas, gbc_btnLargas);
		//btnLargas.addActionListener(retrieveActionListener(this.link));
		btnLargas.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e){
				System.out.println("Largas pulsadas");
				link.sendPowerPinSwitch(13, IProtocol.HIGH);
			}
		});
		
		JButton btnAuto = new JButton("AUTO");
		GridBagConstraints gbc_btnAuto = new GridBagConstraints();
		gbc_btnAuto.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnAuto.insets = new Insets(0, 0, 5, 5);
		gbc_btnAuto.gridx = 0;
		gbc_btnAuto.gridy = 2;
		panel.add(btnAuto, gbc_btnAuto);
		btnAuto.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e){
				System.out.println("AUTO pulsadas");
				link.sendPowerPinSwitch(13, IProtocol.HIGH);
				while(true){
					//lee el sensor
					//si sensor > umbral
						//enciende posicion y cruce
					//else
						//apaga
				}
			}
		});
		
		JButton btnPosicion = new JButton("Posici\u00F3n");
		GridBagConstraints gbc_btnPosicion = new GridBagConstraints();
		gbc_btnPosicion.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnPosicion.insets = new Insets(0, 0, 5, 5);
		gbc_btnPosicion.gridx = 1;
		gbc_btnPosicion.gridy = 2;
		panel.add(btnPosicion, gbc_btnPosicion);
		btnPosicion.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e){
				System.out.println("Posición pulsadas");
				link.sendPowerPinSwitch(13, IProtocol.HIGH);
			}
		});
		
		JButton btnOff = new JButton("OFF");
		GridBagConstraints gbc_btnOff = new GridBagConstraints();
		gbc_btnOff.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnOff.insets = new Insets(0, 0, 0, 5);
		gbc_btnOff.gridx = 0;
		gbc_btnOff.gridy = 3;
		panel.add(btnOff, gbc_btnOff);
		btnOff.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e){
				System.out.println("OFF pulsadas");
				link.sendPowerPinSwitch(13, IProtocol.LOW);
			}
		});
		
		JButton btnCruce = new JButton("Cruce");
		GridBagConstraints gbc_btnCruce = new GridBagConstraints();
		gbc_btnCruce.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnCruce.insets = new Insets(0, 0, 0, 5);
		gbc_btnCruce.gridx = 1;
		gbc_btnCruce.gridy = 3;
		panel.add(btnCruce, gbc_btnCruce);
		btnCruce.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e){
				System.out.println("Cruce pulsadas");
				link.sendPowerPinSwitch(13, IProtocol.HIGH);
			}
		});
		
		JButton btnRfagas = new JButton("R\u00E1fagas");
		GridBagConstraints gbc_btnRfagas = new GridBagConstraints();
		gbc_btnRfagas.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnRfagas.gridx = 3;
		gbc_btnRfagas.gridy = 3;
		panel.add(btnRfagas, gbc_btnRfagas);
		btnRfagas.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e){
				System.out.println("Rafagas pulsadas");
				link.sendPowerPinSwitch(13, IProtocol.HIGH);
			}
		});
	}
}
