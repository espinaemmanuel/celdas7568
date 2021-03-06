/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.uba.fi.celdas7568.ciudad.gui;

import ar.uba.fi.celdas7568.ciudad.Agente;
import ar.uba.fi.celdas7568.ciudad.AtributoPersonalidad;
import ar.uba.fi.celdas7568.ciudad.Ciudad;
import ar.uba.fi.celdas7568.ciudad.ObservadorModeloGrilla;
import ar.uba.fi.celdas7568.ciudad.Personalidad;
import ar.uba.fi.celdas7568.db.CiudadLoaderJson;
import ar.uba.fi.celdas7568.db.OrigenAgentes;

import com.google.common.collect.Maps;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.logging.Logger;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTable;

/**
 * 
 * @author Nico
 */
public class VentanaPrincipal extends javax.swing.JFrame {

	private final static Logger logger = Logger
			.getLogger(VentanaPrincipal.class.getName());

	private ModeloSimulador modelo;
	private Map<JRadioButtonMenuItem, AtributoPersonalidad> mapaMenuAtributo = Maps.newHashMap();
	private Grilla grilla;

	/**
	 * Creates new form VentanaPrincipal
	 * 
	 * @param modelo
	 */

	public VentanaPrincipal(ModeloSimulador modelo) {
		this.modelo = modelo;
		this.grilla = new Grilla(300, 300, this.modelo.getCiudad());

		initComponents();

		if (this.modelo.getCiudad() != null) {
			ciudadCargada();
		}

	}

	public void ciudadCargada() {
		labelStatusCiudad.setForeground(Color.green);
		labelStatusCiudad.setText("Ciudad seleccionada: "
				+ this.modelo.getCiudad().getNombre());

		repaint();

	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jPanel1 = this.grilla;
		jLabel1 = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTable1 = new javax.swing.JTable();
		jLabel2 = new javax.swing.JLabel();
		jComboBox1 = new javax.swing.JComboBox();
		labelStatusCiudad = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jButton2 = new javax.swing.JButton();
		jButton3 = new javax.swing.JButton();
		jLabel5 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		jButton4 = new javax.swing.JButton();

		JMenuBar menuBar = new JMenuBar();
		JMenu menuVisualizacion = new JMenu("Visualización");
		menuBar.add(menuVisualizacion);

		ButtonGroup groupAtributos = new ButtonGroup();

		ItemListener itemListener = new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				atributoPersonalidadCambiado(e);
			}
		};

		for (AtributoPersonalidad at : AtributoPersonalidad.values()) {
			String atributo = at.toString().toLowerCase();
			atributo = Character.toUpperCase(atributo.charAt(0))
					+ atributo.substring(1);

			JRadioButtonMenuItem item = new JRadioButtonMenuItem(atributo);
			item.addItemListener(itemListener);
			groupAtributos.add(item);
			menuVisualizacion.add(item);
			
			mapaMenuAtributo.put(item, at);
		}
		this.setJMenuBar(menuBar);

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 478,
				Short.MAX_VALUE));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 398,
				Short.MAX_VALUE));

		jLabel1.setText("Ciudad");

		jTable1.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] { { "Cultura", null }, { "Diversion", null },
						{ "Seguridad", null }, { "Educacion", null },
						{ "Naturaleza", null }, { "Populoso", null },
						{ "Familiar", null }, { "Tranquilo", null },
						{ "Transporte", null }, { "BarrioExclusivo", null },
						{ "Costo", null } }, new String[] { "Parametro",
						"Valor" }) {
			boolean[] canEdit = new boolean[] { false, true };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		jTable1.setEnabled(false);
		jScrollPane1.setViewportView(jTable1);

		jLabel2.setText("Origen de agentes");

		jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(this.modelo
				.getOrigenesAgentes()));
		jComboBox1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jComboBox1ActionPerformed(evt);
			}
		});
		jComboBox1.setSelectedIndex(0);

		labelStatusCiudad.setForeground(new java.awt.Color(255, 0, 0));
		labelStatusCiudad.setText("Ciudad aún no cargada");

		jLabel4.setText("Control");

		jButton2.setText(">");
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

		jButton3.setText("> x 100");
		jButton3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton3ActionPerformed(evt);
			}
		});

		jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
		jLabel5.setText("Información de Agente");

		jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
		jLabel6.setText("Información de Ciudad");

		jLabel7.setText("Editar Personalidad");

		jButton4.setText("Cargar Ciudad");
		jButton4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton4ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(
														jPanel1,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel4)
												.addComponent(jLabel1)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		jButton2)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																.addComponent(
																		jButton3)))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jLabel5)
												.addComponent(jLabel2)
												.addComponent(
														jScrollPane1,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(labelStatusCiudad)
												.addComponent(
														jComboBox1,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														226,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel6)
												.addComponent(jButton4))
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
				.addGroup(
						layout.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(
										javax.swing.GroupLayout.Alignment.TRAILING,
										layout.createSequentialGroup()
												.addContainerGap(508,
														Short.MAX_VALUE)
												.addComponent(jLabel7)
												.addGap(360, 360, 360))));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(jLabel5)
								.addGap(7, 7, 7)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel2)
												.addComponent(jLabel4))
								.addGap(4, 4, 4)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														jComboBox1,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jButton2)
												.addComponent(jButton3))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(jLabel1)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		jScrollPane1,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		211,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		jLabel6)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		labelStatusCiudad)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		jButton4))
												.addComponent(
														jPanel1,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addContainerGap())
				.addGroup(
						layout.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(
										layout.createSequentialGroup()
												.addGap(89, 89, 89)
												.addComponent(jLabel7)
												.addContainerGap(410,
														Short.MAX_VALUE))));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	protected void atributoPersonalidadCambiado(ItemEvent e) {
		AtributoPersonalidad atributo = mapaMenuAtributo.get(e.getItem());
		logger.info("Atributo de personalidad a mostrar cambiado: " + atributo.toString());
		this.modelo.getCiudad().getVisualizacionZona().setVistaActual(atributo);
		grilla.repaint();
	}

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		Agente agente = this.modelo.getAgenteActual();
		this.modelo.avanzarAgente();
		this.modelo.getCiudad().agregarAgente(agente);

		this.cargarPersonalidadEnTabla(this.modelo.getAgenteActual()
				.getPersonalidad());
	}

	private void jComboActionPerformed(JComboBox j, JTable table1,
			java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton2ActionPerformed
		logger.info("Cambia origen agentes a " + j.getSelectedItem());
		table1.getModel().setValueAt("Sanga", 1, 1);
	}// GEN-LAST:event_jButton2ActionPerformed

	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton3ActionPerformed
		for (int i = 0; i < 100; i++) {
			Agente agente = this.modelo.getAgenteActual();
			this.modelo.avanzarAgente();
			this.modelo.getCiudad().agregarAgente(agente);

		}
	}// GEN-LAST:event_jButton3ActionPerformed

	private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jComboBox1ActionPerformed
		OrigenAgentes origen = (OrigenAgentes) jComboBox1.getSelectedItem();
		this.modelo.setOrigenAgenteActual(origen);
		logger.info("Cambio origen de agentes a " + origen);

		this.modelo.avanzarAgente();
		Agente nuevoAgente = this.modelo.getAgenteActual();

		jTable1.setEnabled(true);

		Personalidad p = nuevoAgente.getPersonalidad();
		this.cargarPersonalidadEnTabla(p);
	}// GEN-LAST:event_jComboBox1ActionPerformed

	private void cargarPersonalidadEnTabla(Personalidad p) {
		jTable1.getModel()
				.setValueAt(p.get(AtributoPersonalidad.CULTURA), 0, 1);
		jTable1.getModel().setValueAt(p.get(AtributoPersonalidad.DIVERSION), 1,
				1);
		jTable1.getModel().setValueAt(p.get(AtributoPersonalidad.SEGURIDAD), 2,
				1);
		jTable1.getModel().setValueAt(p.get(AtributoPersonalidad.EDUCACION), 3,
				1);
		jTable1.getModel().setValueAt(p.get(AtributoPersonalidad.NATURALEZA),
				4, 1);
		jTable1.getModel().setValueAt(p.get(AtributoPersonalidad.POPULOSO), 5,
				1);
		jTable1.getModel().setValueAt(p.get(AtributoPersonalidad.FAMILIAR), 6,
				1);
		jTable1.getModel().setValueAt(p.get(AtributoPersonalidad.TRANQUILO), 7,
				1);
		jTable1.getModel().setValueAt(p.get(AtributoPersonalidad.TRANSPORTE),
				8, 1);
		jTable1.getModel().setValueAt(p.get(AtributoPersonalidad.EXCLUSIVIDAD),
				9, 1);
		jTable1.getModel().setValueAt(p.get(AtributoPersonalidad.COSTO), 10, 1);
		repaint();
	}

	private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton4ActionPerformed
		// BOTON CARGA CIUDAD
		JFileChooser jc = new JFileChooser();
		jc.setCurrentDirectory(new File("./ciudades"));
		int returnVal = jc.showOpenDialog(this);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			try {
				Ciudad ciudad = CiudadLoaderJson.load(new File(jc
						.getSelectedFile().getPath()));
				this.modelo.setCiudad(ciudad);

				this.ciudadCargada();

			} catch (JsonSyntaxException ex) {
				errMsg("Error de Sintaxis del Archivo");
			} catch (JsonIOException ex) {
				errMsg("Error al leer del Archivo");
			} catch (FileNotFoundException ex) {
				errMsg("El archivo no fue encontrado");
			}
		}
		repaint();
	}// GEN-LAST:event_jButton4ActionPerformed

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void launch(final ModeloSimulador modelo) {

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger
					.getLogger(VentanaPrincipal.class.getName()).log(
							java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger
					.getLogger(VentanaPrincipal.class.getName()).log(
							java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger
					.getLogger(VentanaPrincipal.class.getName()).log(
							java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger
					.getLogger(VentanaPrincipal.class.getName()).log(
							java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/*
		 * Create and display the form
		 */
		java.awt.EventQueue.invokeLater(new Runnable() {

			public void run() {
				VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(modelo);
				ventanaPrincipal.setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JButton jButton4;
	private javax.swing.JComboBox jComboBox1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable jTable1;
	private javax.swing.JLabel labelStatusCiudad;

	// End of variables declaration//GEN-END:variables

	private void errMsg(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Error (!)",
				JOptionPane.ERROR_MESSAGE);
	}
}
