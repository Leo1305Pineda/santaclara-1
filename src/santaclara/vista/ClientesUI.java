/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;

import santaclara.controlador.ContClientes;
import santaclara.modelo.Ruta; 
import santaclara.vista.herramientas.VistaGenericaUI;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class ClientesUI extends VistaGenericaUI {
	
	private JPanel pnCliente;
	private JPanel pnCheckDia;
	
	private JLabel lblTipoCliente;
	private JLabel lblRazonSocial;
	private JLabel lblTelefono;
	private JLabel lblDireccion;
	private JLabel lblRif;
	private JLabel lblDiaVisita;
	private JLabel lblRuta;
	
	private JTextField txtRazonSocial;
	private JTextField txtABuscar;
	private JTextField txtTelefono;
	private JTextArea txtDireccion;
	private JTextField txtRif;
	private JTextField txtId;
	
	private JButton btnNuevo;
	private JButton btnABuscar;
	private JButton btnEliminar;
	private JButton btnGuardar; 
	private JButton btnAbrirRuta;
	
	private JCheckBox checkLune;
	private JCheckBox checkMarte;
	private JCheckBox checkMiercole;
	private JCheckBox checkJueve; 
	private JCheckBox checkVierne;
	private JCheckBox checkSabado;

	private JComboBox<String> cmbTipoCliente ;
	private JComboBox<Ruta>  cmbRutas;
	private ContClientes  contClientes;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ClientesUI(ContClientes contClientes) {
		super();
		this.contClientes = contClientes;
		
		dibujarPanelOpciones();
		dibujarPanelTabla();
		dibujarBotonAtras();
		getBtnAtras().addActionListener(contClientes.atras());
		dibujarPanelCliente();
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setBackground(Color.DARK_GRAY);
		btnEliminar.setIcon(new ImageIcon("img/gestion/cancel.png"));
		btnEliminar.addActionListener(contClientes.eliminar());
		getPnBotones().add(btnEliminar);

		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(contClientes.nuevo());
		btnNuevo.setForeground(Color.WHITE);
		btnNuevo.setBackground(Color.DARK_GRAY);
		btnNuevo.setIcon(new ImageIcon("img/gestion/add.png"));
		getPnBotones().add(btnNuevo);
		
		btnAbrirRuta = new JButton("Ruta");
		btnAbrirRuta.addActionListener(contClientes.AbrirRutas());
		btnAbrirRuta.setForeground(Color.WHITE);
		btnAbrirRuta.setBackground(Color.DARK_GRAY);
		getPnBotones().add(btnAbrirRuta);
		
		dibujarBotonSalir();
		getBtnSalir().addActionListener(contClientes.salir());
		
		txtABuscar = new JTextField();
		txtABuscar.setForeground(Color.WHITE);
		txtABuscar.setBackground(new Color(64, 64, 64));
		txtABuscar.setColumns(10);
		getPanelBuscar().add(txtABuscar);

		btnABuscar = new JButton("");
		btnABuscar.addActionListener(contClientes.buscar());
		btnABuscar.setVerticalAlignment(SwingConstants.TOP);
		btnABuscar.setBackground(Color.DARK_GRAY);
		btnABuscar.setIcon(new ImageIcon("img/gestion/buscar.png"));
		getPanelBuscar().add(btnABuscar, "cell 0 0");
		
		lblTipoCliente = new JLabel("Tipo Cliente:");
		lblTipoCliente.setForeground(Color.WHITE);
		lblTipoCliente.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblTipoCliente.setBounds(12, 18, 113, 25);
		getPanelBuscar().add(lblTipoCliente);
		
		cmbTipoCliente = new JComboBox();
		cmbTipoCliente.setModel(new DefaultComboBoxModel(new String[] {"Salp", "Domicilio", "Comercial"}));
		cmbTipoCliente.addActionListener(contClientes.ActivarTipoUsuario());
		getPanelBuscar().add(cmbTipoCliente);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void dibujarPanelCliente(){
		pnCliente = new JPanel();
		pnCliente.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)),"Editar cliente", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnCliente.setBackground(Color.DARK_GRAY);
		pnCliente.setLayout(new MigLayout());
			
		lblRif = new JLabel("Rif:");
		lblRif.setForeground(Color.WHITE);
		lblRif.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		pnCliente.add(lblRif,"cell 0 0");
		
		txtRif = new JTextField();
		txtRif.setColumns(10);
		pnCliente.add(txtRif,"cell 1 0");
		
		lblRazonSocial = new JLabel("Razon Social:");
		lblRazonSocial.setForeground(Color.WHITE);
		lblRazonSocial.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		pnCliente.add(lblRazonSocial,"cell 0 1");
		
		txtRazonSocial = new JTextField();
		txtRazonSocial.setColumns(50);
		txtRazonSocial.setBounds(300, 10, 412, 16);
		pnCliente.add(txtRazonSocial,"cell 1 1");

		lblTelefono = new JLabel("Telefono:");
		lblTelefono.setForeground(Color.WHITE);
		lblTelefono.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		pnCliente.add(lblTelefono,"cell 0 2");

		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		pnCliente.add(txtTelefono,"cell 1 2");

		lblDireccion = new JLabel("Direccion:");
		lblDireccion.setForeground(Color.WHITE);
		lblDireccion.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		pnCliente.add(lblDireccion,"cell 0 3");
		
		txtDireccion = new JTextArea();
		txtDireccion.setColumns(50);
		txtDireccion.setRows(3);
		pnCliente.add(txtDireccion,"cell 1 3");
		
		lblRuta = new JLabel("Ruta ");
		lblRuta.setForeground(Color.WHITE);
		pnCliente.add(lblRuta,"cell 0 4");
		
		cmbRutas = new JComboBox();
		cmbRutas.setRenderer(new ListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				// TODO Auto-generated method stub
				Ruta ruta = (Ruta) value;
				JPanel pnRuta = new JPanel();
				pnRuta.add(new JLabel(ruta.getNombre()));
				pnRuta.add(new JLabel(" zona: "+ruta.getZona().getDescripcion()));
				pnRuta.setLayout(new MigLayout());
				return  pnRuta;	
			}
		});
		pnCliente.add(cmbRutas,"cell 1 4");

		lblDiaVisita = new JLabel("Dia Visitas: ");
		lblDiaVisita.setForeground(Color.WHITE);
		lblDiaVisita.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblDiaVisita.setVisible(false);
		pnCliente.add(lblDiaVisita,"cell 0 5");
		
		pnCheckDia = new JPanel();
		pnCheckDia.setBackground(Color.GRAY);
		pnCliente.add(pnCheckDia,"cell 1 5");
		pnCheckDia.setLayout(new GridLayout(1, 0, 0, 0));
		pnCheckDia.setVisible(false);
		
		checkLune = new JCheckBox("Lun.");
		checkLune.setSelected(false);
		pnCheckDia.add(checkLune);
		
		checkMarte = new JCheckBox("Mar.");
		checkMarte.setSelected(false);
		pnCheckDia.add(checkMarte);
		
		checkMiercole = new JCheckBox("Mié.");
		checkMiercole.setSelected(false);
		pnCheckDia.add(checkMiercole);
		
		checkJueve = new JCheckBox("Jue.");
		checkJueve.setSelected(false);
		pnCheckDia.add(checkJueve);
		
		checkVierne = new JCheckBox("Vie.");
		checkVierne.setSelected(false);
		pnCheckDia.add(checkVierne);
		
		checkSabado = new JCheckBox("Sáb.");
		checkSabado.setSelected(false);
		pnCheckDia.add(checkSabado);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setIcon(new ImageIcon("img/gestion/bien.png"));
		btnGuardar.addActionListener(contClientes.guardar());
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setBackground(Color.DARK_GRAY);
		pnCliente.add(btnGuardar,"cell 0 6");
		
		txtId = new JTextField();
		
		JButton		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon("img/gestion/cancel.png"));
		btnCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});;
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBackground(Color.DARK_GRAY);
		pnCliente.add(btnCancelar);
		
		add(pnCliente,BorderLayout.SOUTH);
		btnCancelar.setVisible(false);
		repaint();
	}
	
	public JPanel getPnCliente() {
		return pnCliente;
	}

	public void setPnCliente(JPanel pnCliente) {
		this.pnCliente = pnCliente;
	}

	public JLabel getLblTipoCliente() {
		return lblTipoCliente;
	}

	public void setLblTipoCliente(JLabel lblTipoCliente) {
		this.lblTipoCliente = lblTipoCliente;
	}

	public JLabel getLblRazonSocial() {
		return lblRazonSocial;
	}

	public void setLblRazonSocial(JLabel lblRazonSocial) {
		this.lblRazonSocial = lblRazonSocial;
	}

	public JLabel getLblTelefono() {
		return lblTelefono;
	}

	public void setLblTelefono(JLabel lblTelefono) {
		this.lblTelefono = lblTelefono;
	}

	public JLabel getLblDireccion() {
		return lblDireccion;
	}

	public void setLblDireccion(JLabel lblDireccion) {
		this.lblDireccion = lblDireccion;
	}

	public JTextField getTxtRazonSocial() {
		return txtRazonSocial;
	}

	public void setTxtRazonSocial(JTextField txtRazonSocial) {
		this.txtRazonSocial = txtRazonSocial;
	}

	public JTextField getTxtABuscar() {
		return txtABuscar;
	}

	public void setTxtABuscar(JTextField txtABuscar) {
		this.txtABuscar = txtABuscar;
	}

	public JTextField getTxtTelefono() {
		return txtTelefono;
	}

	public void setTxtTelefono(JTextField txtTelefono) {
		this.txtTelefono = txtTelefono;
	}

	public JTextArea getTxtDireccion() {
		return txtDireccion;
	}

	public void setTxtDireccion(JTextArea txtDireccion) {
		this.txtDireccion = txtDireccion;
	}

	public JButton getBtnNuevo() {
		return btnNuevo;
	}

	public void setBtnNuevo(JButton btnNuevo) {
		this.btnNuevo = btnNuevo;
	}

	public JButton getBtnABuscar() {
		return btnABuscar;
	}

	public void setBtnABuscar(JButton btnABuscar) {
		this.btnABuscar = btnABuscar;
	}

	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	public void setBtnEliminar(JButton btnEliminar) {
		this.btnEliminar = btnEliminar;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(JButton btnGuardar) {
		this.btnGuardar = btnGuardar;
	}

	public JButton getBtnAbrirRuta() {
		return btnAbrirRuta;
	}

	public void setBtnAbrirRuta(JButton btnAbrirRuta) {
		this.btnAbrirRuta = btnAbrirRuta;
	}


	public JComboBox<String> getCmbTipoCliente() {
		return cmbTipoCliente;
	}

	public void setCmbTipoCliente(JComboBox<String> cmbTipoCliente) {
		this.cmbTipoCliente = cmbTipoCliente;
	}

	public JTextField getTxtRif() {
		return txtRif;
	}

	public void setTxtRif(String txtRif) {
		this.txtRif.setText(txtRif);
	}	

	public JTextField getTxtId() {
		return txtId;
	}

	public void setTxtId(JTextField txtId) {
		this.txtId = txtId;
	}

	public void setTxtRif(JTextField txtRif) {
		this.txtRif = txtRif;
	}
	
	public JPanel getPnCheckDia() {
		return pnCheckDia;
	}

	public void setPnCheckDia(JPanel pnCheckDia) {
		this.pnCheckDia = pnCheckDia;
	}

	public JLabel getLblRif() {
		return lblRif;
	}

	public void setLblRif(JLabel lblRif) {
		this.lblRif = lblRif;
	}

	public JLabel getLblDiaVisita() {
		return lblDiaVisita;
	}

	public void setLblDiaVisita(JLabel lblDiaVisita) {
		this.lblDiaVisita = lblDiaVisita;
	}

	public JCheckBox getCheckLune() {
		return checkLune;
	}

	public void setCheckLune(JCheckBox checkLune) {
		this.checkLune = checkLune;
	}

	public JCheckBox getCheckMarte() {
		return checkMarte;
	}

	public void setCheckMarte(JCheckBox checkMarte) {
		this.checkMarte = checkMarte;
	}

	public JCheckBox getCheckMiercole() {
		return checkMiercole;
	}

	public void setCheckMiercole(JCheckBox checkMiercole) {
		this.checkMiercole = checkMiercole;
	}

	public JCheckBox getCheckJueve() {
		return checkJueve;
	}

	public void setCheckJueve(JCheckBox checkJueve) {
		this.checkJueve = checkJueve;
	}

	public JCheckBox getCheckVierne() {
		return checkVierne;
	}

	public void setCheckVierne(JCheckBox checkVierne) {
		this.checkVierne = checkVierne;
	}

	public JCheckBox getCheckSabado() {
		return checkSabado;
	}

	public void setCheckSabado(JCheckBox checkSabado) {
		this.checkSabado = checkSabado;
	}


	public JLabel getLblRuta() {
		return lblRuta;
	}

	public void setLblRuta(JLabel lblRuta) {
		this.lblRuta = lblRuta;
	}

	public JComboBox<Ruta> getCmbRutas() {
		return cmbRutas;
	}

	public void setCmbRutas(JComboBox<Ruta> cmbRutas) {
		this.cmbRutas = cmbRutas;
	}
 	
	@SuppressWarnings("static-access")
 
	public void setCkeckDiaVisita(Integer numero){

		String cadena = Integer.toBinaryString(numero);
		cadena = cadena.format("000000%s",cadena);
		cadena = cadena.substring(cadena.length()-6,cadena.length());

		if(cadena.charAt(0) == '1')
		{
			getCheckLune().setSelected(true);
		}		
		if(cadena.charAt(1) == '1')
		{
			getCheckMarte().setSelected(true);
		}		
		if(cadena.charAt(2) == '1')
		{
			getCheckMiercole().setSelected(true);
		}		
		if(cadena.charAt(3) == '1')
		{
			getCheckJueve().setSelected(true);
		}		
		if(cadena.charAt(4) == '1')
		{
			getCheckVierne().setSelected(true);
		}		
		if(cadena.charAt(5) == '1')
		{
			getCheckSabado().setSelected(true);
		}	
	}
	
	public void mostrarDiaVisita(Integer dias) {
		if (getCmbTipoCliente().getSelectedItem().equals("Domicilio") || getCmbTipoCliente().getSelectedItem().equals("Comercial"))
		{
			 getPnCheckDia().setVisible(true);
			 getLblDiaVisita().setVisible(true);
			 setCkeckDiaVisita(dias);		
		}
		else 
		{
			getPnCheckDia().setVisible(false);
			getLblDiaVisita().setVisible(false);
		}
	}
	
}
