package arduinoLucespkg;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.JTabbedPane;
import javax.swing.JToggleButton;
import javax.swing.JComponent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class SwingMain extends JFrame {
	private JPanel contentPane;
	private ButtonGroup grupo = new ButtonGroup();

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
	
	public void addNewRow(String modo1, String modo2, String timeStamp){
		System.out.println("PC:"+ timeStamp +" Modo de:" + modo1 + " a:"+ modo2);
	}
	
	public void addNewData(int valorDetectado, int umbralSuperior, int umbralInferior, String timeStamp){
		System.out.println("PC:"+ timeStamp +" Valores recibidos: Detectado:" + valorDetectado
				+" Superior:"+umbralSuperior+" Inferior:"+umbralInferior);
	}

	/**
	 * Create the frame.
	 */
	public SwingMain(Controller controlador) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 319, 283, 0 };
		gbl_contentPane.rowHeights = new int[] { 34, 190, 36, 171, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.insets = new Insets(0, 0, 5, 5);
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 1;
		contentPane.add(tabbedPane, gbc_tabbedPane);

		JComponent panel1 = (JPanel) makeTextPanel("Panel Alejandro");
		tabbedPane.addTab("Visual", panel1);

		JComponent panel2 = (JPanel) makeTextPanel("Panel Fernando");
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
		gbl_panel.columnWidths = new int[] { 161, 176, 94, 55, 0 };
		gbl_panel.rowHeights = new int[] { 45, 23, 23, 23, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JToggleButton btnCarretera = new JToggleButton("Carretera");
		GridBagConstraints gbc_btnCarretera = new GridBagConstraints();
		gbc_btnCarretera.anchor = GridBagConstraints.NORTH;
		gbc_btnCarretera.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCarretera.insets = new Insets(0, 0, 5, 0);
		gbc_btnCarretera.gridx = 3;
		gbc_btnCarretera.gridy = 1;
		panel.add(btnCarretera, gbc_btnCarretera);
		btnCarretera.setEnabled(false);
		

		JToggleButton btnAuto = new JToggleButton("AUTO");
		GridBagConstraints gbc_btnAuto = new GridBagConstraints();
		gbc_btnAuto.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnAuto.insets = new Insets(0, 0, 5, 5);
		gbc_btnAuto.gridx = 0;
		gbc_btnAuto.gridy = 2;
		panel.add(btnAuto, gbc_btnAuto);
		

		JToggleButton btnPosicion = new JToggleButton("Posici\u00F3n");
		GridBagConstraints gbc_btnPosicion = new GridBagConstraints();
		gbc_btnPosicion.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnPosicion.insets = new Insets(0, 0, 5, 5);
		gbc_btnPosicion.gridx = 1;
		gbc_btnPosicion.gridy = 2;
		panel.add(btnPosicion, gbc_btnPosicion);
		btnPosicion.setEnabled(false);
		

		JToggleButton btnOff = new JToggleButton("OFF");
		GridBagConstraints gbc_btnOff = new GridBagConstraints();
		gbc_btnOff.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnOff.insets = new Insets(0, 0, 0, 5);
		gbc_btnOff.gridx = 0;
		gbc_btnOff.gridy = 3;
		panel.add(btnOff, gbc_btnOff);
		btnOff.setSelected(true);
		

		JToggleButton btnCruce = new JToggleButton("Cruce");
		GridBagConstraints gbc_btnCruce = new GridBagConstraints();
		gbc_btnCruce.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnCruce.insets = new Insets(0, 0, 0, 5);
		gbc_btnCruce.gridx = 1;
		gbc_btnCruce.gridy = 3;
		panel.add(btnCruce, gbc_btnCruce);
		btnCruce.setEnabled(false);
		

		JButton btnRafagas = new JButton("R\u00E1fagas");
		GridBagConstraints gbc_btnRfagas = new GridBagConstraints();
		gbc_btnRfagas.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnRfagas.gridx = 3;
		gbc_btnRfagas.gridy = 3;
		panel.add(btnRafagas, gbc_btnRfagas);
		
		//Acciones de los botones
		btnOff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Notificar al controlador el cambio
				controlador.actionFor(Estados.OFF);
				
				// Modificar la pantalla para adaptar al nuevo estado
				btnAuto.setEnabled(true);
				btnPosicion.setEnabled(false);
				btnCruce.setEnabled(false);
				btnCarretera.setEnabled(false);
				btnRafagas.setEnabled(true);
			}
		});
		
		btnAuto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Notificar al controlador el cambio
				controlador.actionFor(Estados.AUTO);
				
				// Modificar la pantalla para adaptar al nuevo estado
				btnOff.setEnabled(true);
				btnPosicion.setEnabled(true);
				btnCruce.setEnabled(false);
				btnCarretera.setEnabled(false);
				btnRafagas.setEnabled(true);
			}
		});
		
		btnPosicion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Notificar al controlador el cambio
				controlador.actionFor(Estados.POSICION);
				
				// Modificar la pantalla para adaptar al nuevo estado
				btnOff.setEnabled(false);
				btnAuto.setEnabled(true);
				btnCruce.setEnabled(true);
				btnCarretera.setEnabled(false);
				btnRafagas.setEnabled(true);
			}
		});
		
		btnCruce.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Notificar al controlador el cambio
				controlador.actionFor(Estados.CRUCE);
				
				// Modificar la pantalla para adaptar al nuevo estado
				btnOff.setEnabled(false);
				btnAuto.setEnabled(false);
				btnPosicion.setEnabled(true);
				btnCarretera.setEnabled(true);
				btnRafagas.setEnabled(true);
			}
		});
		
		btnCarretera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Notificar al controlador el cambio
				controlador.actionFor(Estados.CARRETERA);
				
				// Modificar la pantalla para adaptar al nuevo estado
				btnOff.setEnabled(false);
				btnAuto.setEnabled(false);
				btnPosicion.setEnabled(false);
				btnCruce.setEnabled(true);
				btnRafagas.setEnabled(false);
				
			}
		});
		
		btnRafagas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.actionFor(Estados.RAFAGA);
			}
		});
		
		grupo.add(btnOff);
		grupo.add(btnAuto);
		grupo.add(btnPosicion);
		grupo.add(btnCruce);
		grupo.add(btnCarretera);
	}
}
