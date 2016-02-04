package santaclara.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import santaclara.controlador.ContClientes;
import santaclara.modelo.Cliente;
import santaclara.modelo.DomicilioComercio;
import santaclara.modelo.Ruta;
import santaclara.modelo.Salp; 
import santaclara.vista.herramientas.VistaGenericaUI;

import javax.swing.JCheckBox;

import net.miginfocom.swing.MigLayout;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.swingbinding.JComboBoxBinding;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import java.util.ArrayList;
import java.util.List;

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
	private JButton btnAtras;
	private JButton btnSalir;
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
	 
	@SuppressWarnings("rawtypes")
	private List  clientes = new ArrayList<Cliente>();
	private List<Ruta>   rutas = new ArrayList<Ruta>();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
 
	public ClientesUI(ContClientes contClientes, List salps, List<Ruta> rutas) {
		super();
		this.clientes = salps;
		this.rutas = rutas;
		this.contClientes = contClientes;
		
		
		dibujarPanelOpciones();
		dibujarPanelTabla();
 
		btnAtras = new JButton("Atras");
		btnAtras.addActionListener(contClientes.atras());
		btnAtras.setForeground(Color.WHITE);
		btnAtras.setBackground(Color.DARK_GRAY);
		btnAtras.setIcon(new ImageIcon("img/gestion/AtrasCurva.png"));
		getPnBotones().add(btnAtras);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(contClientes.salir());
		getPnBotones().add(btnSalir);
		
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
		
		
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(Color.DARK_GRAY);
		btnSalir.setIcon(new ImageIcon("img/gestion/SalirCurva.png"));
		getPnBotones().add(btnSalir);
		
		
		
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
		cmbTipoCliente.setBounds(114, 18, 200, 20);
		getPanelBuscar().add(cmbTipoCliente);
		
		
		pnCliente = new JPanel();
		//pnCliente.setBounds(12, 75, 726, 126);
		pnCliente.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)),"editar cliente", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnCliente.setBackground(Color.DARK_GRAY);
		pnCliente.setLayout(new MigLayout());
			
		lblRif = new JLabel("Rif:");
		lblRif.setForeground(Color.WHITE);
		lblRif.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		pnCliente.add(lblRif,"cell 0 0");
		
		txtRif = new JTextField("rif");
		txtRif.setColumns(10);
		pnCliente.add(txtRif,"cell 1 0");
		
		lblRazonSocial = new JLabel("Razon Social:");
		lblRazonSocial.setForeground(Color.WHITE);
		lblRazonSocial.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblRazonSocial.setBounds(200, 5, 100, 25);
		pnCliente.add(lblRazonSocial,"cell 1 0");
		
		txtRazonSocial = new JTextField("razon social");
		txtRazonSocial.setColumns(10);
		txtRazonSocial.setBounds(300, 10, 412, 16);
		pnCliente.add(txtRazonSocial,"cell 1 0");

		lblTelefono = new JLabel("Telefono:");
		lblTelefono.setForeground(Color.WHITE);
		lblTelefono.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblTelefono.setBounds(10, 30, 69, 25);
		pnCliente.add(lblTelefono,"cell 0 1");

		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(86, 35, 100, 16);
		pnCliente.add(txtTelefono,"cell 1 1");

		lblDireccion = new JLabel("Direccion:");
		lblDireccion.setForeground(Color.WHITE);
		lblDireccion.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblDireccion.setBounds(200, 30, 81, 16);
		pnCliente.add(lblDireccion,"cell 0 2");
		
		txtDireccion = new JTextArea();
		txtDireccion.setColumns(40);
		txtDireccion.setRows(3);
		//txtDireccion.setBounds(300, 35, 412, 16);
		pnCliente.add(txtDireccion,"cell 1 2");
		
		lblRuta = new JLabel("Ruta ");
		lblRuta.setForeground(Color.WHITE);
		lblRuta.setBounds(12, 17, 691, 15);
		pnCliente.add(lblRuta,"cell 0 3");
		
		cmbRutas = new JComboBox();
		cmbRutas.setSize(50,50);
		cmbRutas.setRenderer(new ListCellRenderer() {
 			@Override
			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				// TODO Auto-generated method stub
				Ruta ruta = (Ruta) value;
				JPanel pnRuta = new JPanel();
				//pnRuta.setMaximumSize(new Dimension(600,40));
				pnRuta.add(new JLabel(ruta.getNombre()));
				//pnRuta.add(new JTextField(ruta.getZona().getId().toString()));
				pnRuta.add(new JLabel(" zona: "+ruta.getZona().getDescripcion()));
				pnRuta.setLayout(new MigLayout());
				return  pnRuta;	
			}
		});
		pnCliente.add(cmbRutas,"cell 1 3");

		lblDiaVisita = new JLabel("Dia Visitas: ");
		lblDiaVisita.setForeground(Color.WHITE);
		lblDiaVisita.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblDiaVisita.setBounds(12, 90, 100, 25);
		pnCliente.add(lblDiaVisita,"cell 0 4");
		
		pnCheckDia = new JPanel();
		pnCheckDia.setBackground(Color.GRAY);
		pnCheckDia.setBounds(92, 95, 350, 25);
		pnCliente.add(pnCheckDia,"cell 1 4");
		pnCheckDia.setLayout(new GridLayout(1, 0, 0, 0));
		
		checkLune = new JCheckBox("Lun.");
		pnCheckDia.add(checkLune);
		
		checkMarte = new JCheckBox("Mar.");
		pnCheckDia.add(checkMarte);
		
		checkMiercole = new JCheckBox("Mié.");
		pnCheckDia.add(checkMiercole);
		
		checkJueve = new JCheckBox("Jue.");
		pnCheckDia.add(checkJueve);
		
		checkVierne = new JCheckBox("Vie.");
		pnCheckDia.add(checkVierne);
		
		checkSabado = new JCheckBox("Sáb.");
		pnCheckDia.add(checkSabado);


		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(contClientes.guardar());
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setBackground(Color.DARK_GRAY);
		pnCliente.add(btnGuardar,"cell 0 5");
		
		txtId = new JTextField();
		txtId.setColumns(10);
		txtId.setBounds(54, 9, 20, 16);
		txtId.setVisible(false);
		pnCliente.add(txtId,"cell 0 5");
		
	
		activarJComboBoxBindingRuta();
		activarBindingSalp(salps);
	
	}
	
	public void LimpiarTxt(){
	  txtId.setText("");
	 txtDireccion.setText("");
	 txtRazonSocial.setText("");
	 txtTelefono.setText("");
	 txtRif.setText("");
	 checkLune.setSelected(false);
	 checkMarte.setSelected(false);
	 checkMiercole.setSelected(false);
	 checkJueve.setSelected(false);
	 checkVierne.setSelected(false);
	 checkSabado.setSelected(false);
	}
  
	@SuppressWarnings({ "rawtypes", "unchecked" })
 
	public void activarBindingSalp(List<Salp>  salps) {
		// TODO Auto-generated method stub
		clientes = salps;
		getPnTabla().setVisible(true);
		setTable(new JTable());
		getScrollPanel().setViewportView(getTable());
		JTableBinding binClientes = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,salps,getTable());
		BeanProperty idCliente  = BeanProperty.create("id");
		BeanProperty rif = BeanProperty.create("rif");
		BeanProperty razonSocial = BeanProperty.create("razonsocial");
		BeanProperty direccion = BeanProperty.create("direccion");
		BeanProperty telefono = BeanProperty.create("telefono");
		BeanProperty rutaCliente = BeanProperty.create("ruta.nombre");
			
		binClientes.addColumnBinding(idCliente).setColumnClass(Integer.class).setColumnName("idCliente");
		binClientes.addColumnBinding(rif).setColumnClass(String.class).setColumnName("rif");
		binClientes.addColumnBinding(razonSocial).setColumnClass(String.class).setColumnName("Razon Social");
		binClientes.addColumnBinding(direccion).setColumnClass(String.class).setColumnName("Direccion");
		binClientes.addColumnBinding(telefono).setColumnClass(String.class).setColumnName("Telefono");
		binClientes.addColumnBinding(rutaCliente).setColumnClass(String.class).setColumnName("Ruta");
		binClientes.bind();
		getTable().addKeyListener(contClientes.mostrarCliente_keypress());
		getTable().addMouseListener(contClientes.mostrarCliente());
		
		//pnCliente.setVisible(false);
		remove(pnCliente);
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void activarBindingDomicilioComercios(List<DomicilioComercio>  domicilioComercios) {
		// TODO Auto-generated method stub
		getPnTabla().setVisible(true);
		setTable(new JTable());
		getScrollPanel().setViewportView(getTable());

		List<DomicilioComercio> aux = new ArrayList<DomicilioComercio>();
		
		for(Object obj : domicilioComercios)
		{	
			DomicilioComercio domicilioComercio = (DomicilioComercio) obj;
			if(domicilioComercio.getTipo().equals("C") && getCmbTipoCliente().getSelectedItem().equals("Comercial")) 
				aux.add(domicilioComercio);
			else if(domicilioComercio.getTipo().equals("D")  && getCmbTipoCliente().getSelectedItem().equals("Domicilio"))
				aux.add(domicilioComercio);
		}
		domicilioComercios = aux;
		this.clientes = domicilioComercios;
	
		JTableBinding binClientes = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,domicilioComercios,getTable());
		
		BeanProperty idCliente  = BeanProperty.create("id");
		BeanProperty rif = BeanProperty.create("rif");
		BeanProperty razonSocial = BeanProperty.create("razonsocial");
		BeanProperty direccion = BeanProperty.create("direccion");
		BeanProperty telefono = BeanProperty.create("telefono");
		BeanProperty rutaCliente = BeanProperty.create("ruta.nombre");

		binClientes.addColumnBinding(idCliente).setColumnClass(Integer.class).setColumnName("idCliente");
		binClientes.addColumnBinding(rif).setColumnClass(String.class).setColumnName("rif");
		binClientes.addColumnBinding(razonSocial).setColumnClass(String.class).setColumnName("Razon Social");
		binClientes.addColumnBinding(direccion).setColumnClass(String.class).setColumnName("Direccion");
		binClientes.addColumnBinding(telefono).setColumnClass(String.class).setColumnName("Telefono");
		binClientes.addColumnBinding(rutaCliente).setColumnClass(String.class).setColumnName("Ruta");
		binClientes.bind();
		
		getTable().addKeyListener(contClientes.mostrarCliente_keypress());
		getTable().addMouseListener(contClientes.mostrarCliente());
 

		remove(pnCliente);
		///pnCliente.setVisible(false);
 
}
	
	@SuppressWarnings("rawtypes")
	public void activarJComboBoxBindingRuta(){
		JComboBoxBinding jcomboRutas = SwingBindings.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ,rutas,cmbRutas);
	    jcomboRutas.bind();
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
	public JButton getBtnAtras() {
		return btnAtras;
	}

	public void setBtnAtras(JButton btnAtras) {
		this.btnAtras = btnAtras;
	}

	public JButton getBtnSalir() {
		return btnSalir;
	}

	public void setBtnSalir(JButton btnSalir) {
		this.btnSalir = btnSalir;
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
 
	@SuppressWarnings("rawtypes")
 
	public List getClientes() {
		return clientes;
	}
 
	@SuppressWarnings("rawtypes")
 
	public void setClientes(List clientes) {
		this.clientes = clientes;
	}

	public void activarNuevoCliente() {
		// TODO Auto-generated method stub
			// TODO Auto-generated method stub
			//txtId.setVisible(false);
		if(getCmbTipoCliente().getSelectedItem().equals("Salp"))
		{	
			pnCheckDia.setVisible(false);
			lblDiaVisita.setVisible(false);
		}
		else
		{
			pnCheckDia.setVisible(true);
			lblDiaVisita.setVisible(true);
		}
		getPnTabla().setVisible(false);
		add(pnCliente,BorderLayout.CENTER);
		btnGuardar.setVisible(true);
		pnCheckDia.setVisible(true);
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
			 ///setCkeckDiaVisita((new ServicioDomicilioComercio().getdDomicilioComercio(id)).getDiaVisita());	
		}
		else 
		{
			getPnCheckDia().setVisible(false);
			getLblDiaVisita().setVisible(false);
		}
	}

	
	public void cargarCliente() {
		// TODO Auto-generated method stub	
 			if (getTable().getSelectedRow() >= 0 )
			{
				Cliente cliente = (Cliente) clientes.get(getTable().getSelectedRow());
				LimpiarTxt();
				if(cliente.getId() != null)
				{
					getTxtId().setText(cliente.getId().toString());
				}
				getTxtDireccion().setText(cliente.getDireccion());
				getTxtRazonSocial().setText(cliente.getRazonsocial());
				getTxtRif().setText(cliente.getRif());
				getTxtTelefono().setText(cliente.getTelefono());
				if(cliente instanceof DomicilioComercio)
				{
					pnCheckDia.setVisible(true);
					//System.out.println( ((DomicilioComercio) cliente ).getId()+ "Aki ----->"+ ((DomicilioComercio) cliente ).getDiaVisita() + ((DomicilioComercio) cliente ).getRazonsocial());
					mostrarDiaVisita( ((DomicilioComercio) cliente ).getDiaVisita());
				}
				else
				{
					pnCheckDia.setVisible(false);
				}
				if(cliente.getRuta() != null && cliente.getRuta().getId() !=null )
				{
					getLblRuta().setText("Ruta:");
					for(int i = 0; i < rutas.size(); i++)
					{	
						Ruta ruta = rutas.get(i);
						if (ruta.getId().equals(cliente.getRuta().getId()))
						{
							cmbRutas.setSelectedIndex(i);
						}
					}
				}
				add(pnCliente,BorderLayout.SOUTH);
				btnGuardar.setVisible(false);
			}
	}
	
}
