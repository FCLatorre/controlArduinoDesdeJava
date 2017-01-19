package arduinoLucespkg;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Dimension;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Cursor;
import javax.swing.SwingConstants;

public class Vista extends JFrame {
	private JPanel contentPane;
	private ButtonGroup grupo = new ButtonGroup();
	private Grafica grafica;
	private JTable tabla;

	public void setGrafica(Grafica grafica){
		this.grafica = grafica;
	}

	public Grafica getGrafica(){
		return this.grafica;
	}

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

	public void addNewRow(String timeStamp, String mensaje, String modo1, String modo2){
		DefaultTableModel model = (DefaultTableModel) this.tabla.getModel();
		model.addRow(new Object[]{timeStamp, mensaje, modo1, modo2});
	}

	public void addNewData(int valorDetectado, int umbralSuperior, int umbralInferior, long segundos){
		this.getGrafica().addNewData(valorDetectado, umbralSuperior, umbralInferior, segundos);
	}

	/**
	 * Create the frame.
	 */
	public Vista(Controlador controlador) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 493, 283, 0 };
		gbl_contentPane.rowHeights = new int[] { 224, 33, 194, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 1.0, 0.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.insets = new Insets(0, 0, 5, 5);
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		contentPane.add(tabbedPane, gbc_tabbedPane);

		this.tabla = new JTable(new DefaultTableModel(null, new Object[]{"Hora", "Evento", "Modo Anterior", "Modo Actual"}));
		this.tabla.getColumnModel().getColumn(0).setPreferredWidth(40);
		this.tabla.getColumnModel().getColumn(1).setPreferredWidth(300);
		this.tabla.getColumnModel().getColumn(2).setPreferredWidth(40);
		this.tabla.getColumnModel().getColumn(3).setPreferredWidth(40);

		JScrollPane scrollPane = new JScrollPane(this.tabla);

		tabbedPane.addTab("Visual", scrollPane);

		this.grafica = new Grafica();

		tabbedPane.addTab("Gráfica", this.getGrafica().getGrafica());

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 123, 144, 68, 55, 0 };
		gbl_panel.rowHeights = new int[] { 0, 45, 23, 23, 23, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel contenedorImagen = new JLabel();
		GridBagConstraints gbc_contenedorImagen = new GridBagConstraints();
		gbc_contenedorImagen.insets = new Insets(0, 0, 5, 5);
		gbc_contenedorImagen.gridx = 2;
		gbc_contenedorImagen.gridy = 0;
		panel.add(contenedorImagen, gbc_contenedorImagen);
		contenedorImagen.setPreferredSize(new Dimension(250, 80));
		contenedorImagen.setSize(new Dimension(50, 50));
		contenedorImagen.setVerticalAlignment(SwingConstants.BOTTOM);
		try {
			Image img = ImageIO.read(getClass().getResource("imagenes/logo.png"));
			img.getScaledInstance(200, 80, DISPOSE_ON_CLOSE);
			contenedorImagen.setIcon(new ImageIcon(img));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		JToggleButton btnCarretera = new JToggleButton();
		btnCarretera.setPreferredSize(new Dimension(80, 30));
		GridBagConstraints gbc_btnCarretera = new GridBagConstraints();
		gbc_btnCarretera.anchor = GridBagConstraints.NORTH;
		gbc_btnCarretera.insets = new Insets(0, 0, 5, 5);
		gbc_btnCarretera.gridx = 2;
		gbc_btnCarretera.gridy = 2;
		panel.add(btnCarretera, gbc_btnCarretera);
		btnCarretera.setEnabled(false);
		try {
			Image img = ImageIO.read(getClass().getResource("imagenes/carretera.png"));
			btnCarretera.setIcon(new ImageIcon(img));
		} catch (Exception ex) {
			System.out.println(ex);
		}

		

		JToggleButton btnAuto = new JToggleButton();
		btnAuto.setPreferredSize(new Dimension(80, 30));
		GridBagConstraints gbc_btnAuto = new GridBagConstraints();
		gbc_btnAuto.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnAuto.insets = new Insets(0, 0, 5, 5);
		gbc_btnAuto.gridx = 0;
		gbc_btnAuto.gridy = 3;
		panel.add(btnAuto, gbc_btnAuto);
		try {
			Image img = ImageIO.read(getClass().getResource("imagenes/auto.png"));
			btnAuto.setIcon(new ImageIcon(img));
		} catch (Exception ex) {
			System.out.println(ex);
		}


		JToggleButton btnPosicion = new JToggleButton();
		btnPosicion.setPreferredSize(new Dimension(80, 30));
		GridBagConstraints gbc_btnPosicion = new GridBagConstraints();
		gbc_btnPosicion.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnPosicion.insets = new Insets(0, 0, 5, 5);
		gbc_btnPosicion.gridx = 1;
		gbc_btnPosicion.gridy = 3;
		panel.add(btnPosicion, gbc_btnPosicion);
		btnPosicion.setEnabled(false);
		try {
			Image img = ImageIO.read(getClass().getResource("imagenes/posicion.png"));
			btnPosicion.setIcon(new ImageIcon(img));
		} catch (Exception ex) {
			System.out.println(ex);
		}

		JToggleButton btnOff = new JToggleButton();
		btnOff.setPreferredSize(new Dimension(80, 30));
		GridBagConstraints gbc_btnOff = new GridBagConstraints();
		gbc_btnOff.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnOff.insets = new Insets(0, 0, 0, 5);
		gbc_btnOff.gridx = 0;
		gbc_btnOff.gridy = 4;
		panel.add(btnOff, gbc_btnOff);
		btnOff.setSelected(true);
		try {
			Image img = ImageIO.read(getClass().getResource("imagenes/off.png"));
			btnOff.setIcon(new ImageIcon(img));
		} catch (Exception ex) {
			System.out.println(ex);
		}


		JToggleButton btnCruce = new JToggleButton();
		btnCruce.setPreferredSize(new Dimension(80, 30));
		GridBagConstraints gbc_btnCruce = new GridBagConstraints();
		gbc_btnCruce.anchor = GridBagConstraints.NORTH;
		gbc_btnCruce.insets = new Insets(0, 0, 0, 5);
		gbc_btnCruce.gridx = 1;
		gbc_btnCruce.gridy = 4;
		panel.add(btnCruce, gbc_btnCruce);
		btnCruce.setEnabled(false);
		try {
			Image img = ImageIO.read(getClass().getResource("imagenes/cruce.png"));
			btnCruce.setIcon(new ImageIcon(img));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		
		JButton btnRafagas = new JButton();
		btnRafagas.setPreferredSize(new Dimension(80, 30));
		GridBagConstraints gbc_btnRfagas = new GridBagConstraints();
		gbc_btnRfagas.insets = new Insets(0, 0, 0, 5);
		gbc_btnRfagas.anchor = GridBagConstraints.NORTH;
		gbc_btnRfagas.gridx = 2;
		gbc_btnRfagas.gridy = 4;
		panel.add(btnRafagas, gbc_btnRfagas);
		try {
			Image img = ImageIO.read(getClass().getResource("imagenes/rafagas.png"));
			btnRafagas.setIcon(new ImageIcon(img));
		} catch (Exception ex) {
			System.out.println(ex);
		}

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
		
		grupo.add(btnCarretera);
		grupo.add(btnOff);
		grupo.add(btnAuto);
		grupo.add(btnPosicion);
		grupo.add(btnCruce);


		

		btnRafagas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.actionFor(Estados.RAFAGA);
			}
		});

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.anchor = GridBagConstraints.NORTH;
		gbc_panel_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 2;
		contentPane.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{493, 0};
		gbl_panel_1.rowHeights = new int[]{156, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);

		JPanel panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(200, 200));
		panel_3.setAlignmentX(Component.RIGHT_ALIGNMENT);
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 0;
		panel_1.add(panel_3, gbc_panel_3);
		panel_3.setLayout(null);

		JSpinner inputUmbralSuperior = new JSpinner();
		inputUmbralSuperior.setBounds(252, 41, 60, 20);
		panel_3.add(inputUmbralSuperior);
		inputUmbralSuperior.setModel(new SpinnerNumberModel(600, 0, 1023, 1));
		inputUmbralSuperior.setPreferredSize(new Dimension(60, 20));

		JSpinner inputUmbralInferior = new JSpinner();
		inputUmbralInferior.setBounds(252, 72, 60, 20);
		panel_3.add(inputUmbralInferior);
		inputUmbralInferior.setModel(new SpinnerNumberModel(400, 0, 1023, 1));
		inputUmbralInferior.setPreferredSize(new Dimension(60, 20));

		JSpinner inputTiempoRafagas = new JSpinner();
		inputTiempoRafagas.setBounds(252, 103, 60, 20);
		panel_3.add(inputTiempoRafagas);
		inputTiempoRafagas.setModel(new SpinnerNumberModel(2, 1, 5, 1));
		inputTiempoRafagas.setPreferredSize(new Dimension(60, 20));

		JLabel label = new JLabel("Umbral inferior (de 0 a 1023)");
		label.setLabelFor(inputUmbralInferior);
		label.setBounds(45, 75, 178, 14);
		panel_3.add(label);

		JLabel label_1 = new JLabel("Tiempo ráfagas (s)");
		label_1.setLabelFor(inputTiempoRafagas);
		label_1.setBounds(102, 106, 121, 14);
		panel_3.add(label_1);

		JLabel lblUmbralSuperior = new JLabel("Umbral superior (de 0 a 1023)");
		lblUmbralSuperior.setLabelFor(inputUmbralSuperior);
		lblUmbralSuperior.setBounds(45, 44, 178, 14);
		panel_3.add(lblUmbralSuperior);

		JButton btnMandarConfiguracion = new JButton("Mandar Configuraci\u00F3n");
		btnMandarConfiguracion.setBounds(322, 71, 161, 23);
		panel_3.add(btnMandarConfiguracion);

		JLabel lblConfiguracin = new JLabel("Configuraci\u00F3n");
		lblConfiguracin.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblConfiguracin.setBounds(136, 0, 131, 33);
		panel_3.add(lblConfiguracin);

		JLabel label_2 = new JLabel("[?]");
		label_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label_2.setToolTipText("Hace referencia al valor de luz ambiente por encima del cual hay visibilidad suficiente y por tanto se deben apagar las luces. Se mide entre 0  y 1023.");
		label_2.setBounds(229, 44, 23, 14);
		panel_3.add(label_2);

		JLabel label_3 = new JLabel("[?]");
		label_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label_3.setToolTipText("Hace referencia al valor de luz ambiente por debajo del cual no hay visibilidad suficiente y por tanto se deben encender las luces. Se mide entre 0  y 1023.");
		label_3.setBounds(229, 75, 23, 14);
		panel_3.add(label_3);

		JLabel label_4 = new JLabel("[?]");
		label_4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label_4.setToolTipText("Hace referencia al tiempo que permanecen las luces de carretera encendidas al realizar una r\u00E1faga. Es un valor comprendido entre 1  y 5 segundos");
		label_4.setBounds(229, 106, 23, 14);
		panel_3.add(label_4);

		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.anchor = GridBagConstraints.NORTH;
		gbc_panel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 2;
		contentPane.add(panel_2, gbc_panel_2);

		JLabel contenedorImagen1 = new JLabel();
		contenedorImagen1.setPreferredSize(new Dimension(400, 200));
		contenedorImagen1.setVerticalAlignment(SwingConstants.BOTTOM);
		Image img;
		try {
			img = ImageIO.read(getClass().getResource("imagenes/ayuda.png"));
			img.getScaledInstance(400, 200, DISPOSE_ON_CLOSE);
			contenedorImagen1.setIcon(new ImageIcon(img));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		panel_2.add(contenedorImagen1);

		btnMandarConfiguracion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Notificar al controlador el cambio
				int umbralSuperior =  (Integer) inputUmbralSuperior.getValue();
				int umbralInferior =  (Integer) inputUmbralInferior.getValue();
				int tiempoRafagas = (Integer) inputTiempoRafagas.getValue();
				if(umbralInferior<umbralSuperior){
					controlador.modificarConfiguracion(umbralInferior, umbralSuperior, tiempoRafagas);
				} else {
					//ERROR
				}
			}
		});
	}
}
