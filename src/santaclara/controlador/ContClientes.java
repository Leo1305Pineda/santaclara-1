package santaclara.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.swingbinding.JComboBoxBinding;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import santaclara.Servicio.ServicioDomicilioComercio;
import santaclara.Servicio.ServicioCliente;
import santaclara.Servicio.ServicioRuta;
import santaclara.Servicio.ServicioSalp;
import santaclara.modelo.DomicilioComercio;
import santaclara.modelo.Cliente;
import santaclara.modelo.Ruta;
import santaclara.modelo.Salp;
import santaclara.vista.ClientesUI;

public class ContClientes extends ContGeneral implements IContGeneral{
	
	private ClientesUI 		vista;
	private Cliente 		cliente = new Cliente();
	private List<Ruta> 		rutas  = new ServicioRuta().getRutas();
	private ServicioSalp    servicioSalp = new ServicioSalp();
	private ServicioDomicilioComercio servicioDomicilioComercio = new ServicioDomicilioComercio();
	
	@SuppressWarnings("rawtypes")
	private List  clientes = new ArrayList<Cliente>();
	
	public ContClientes(ContPrincipal contPrincipal) throws Exception {
		// TODO Auto-generated constructor stub
		setContPrincipal(contPrincipal);
		vista = new ClientesUI(this);
		dibujar(vista,this);
		activarBindingSalp(servicioSalp.getSalps());
		activarJComboBoxBindingRuta();
	}

	@Override
	public ClientesUI getVista() {
		// TODO Auto-generated method stub
		return vista;
	}
	
	public ActionListener nuevo(){
		return new ActionListener() {
			 
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cliente = new Cliente();
				cargarCliente(cliente);
				vista.getTable().clearSelection();
			}
		};
	}	
	/*
	 * forma binaria  0000000  7 dias de la semana lunes-sabado 
	 * */
	public Integer getCheckDiaVisita(Boolean lunes,Boolean martes,Boolean miercoles,Boolean jueves,Boolean viernes,Boolean sabado){
		StringBuilder cadena = new StringBuilder();
		cadena.append( ( lunes ? "1":"0") );
		cadena.append( ( martes ? "1":"0") );
		cadena.append( ( miercoles ? "1":"0") );
		cadena.append( ( jueves ? "1":"0") );
		cadena.append( ( viernes ? "1":"0") );
		cadena.append( ( sabado ? "1":"0") );
	//	System.out.println(Integer.valueOf(cadena.toString(),2));
		return Integer.valueOf(cadena.toString(),2);
	}
	
	public ActionListener guardar() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					validar();
					if(vista.getCmbTipoCliente().getSelectedItem().equals("Salp"))
					{	
						Salp salp = new Salp();
						if (vista.getTxtId().getText().equals("")) 
								salp.setId(null);
						else 
							salp.setId(new Integer(vista.getTxtId().getText().toString()));
					
						salp.setDireccion(vista.getTxtDireccion().getText());
						salp.setRazonsocial(vista.getTxtRazonSocial().getText());
						salp.setRif(vista.getTxtRif().getText());
						salp.setTelefono(vista.getTxtTelefono().getText());
						salp.setRuta((Ruta)vista.getCmbRutas().getSelectedItem());
						servicioSalp.guardar(salp);
						activarBindingSalp(servicioSalp.getSalps());							
						JOptionPane.showMessageDialog(vista,"Operacion Exitosa");
					}
					else
					{
						DomicilioComercio domicilioComercio = new DomicilioComercio();
						
						if (vista.getTxtId().getText().equals(""))
							domicilioComercio.setId(null);
						else 
							domicilioComercio.setId(new Integer(vista.getTxtId().getText().toString()));
						
						domicilioComercio.setDireccion(vista.getTxtDireccion().getText());
						domicilioComercio.setRazonsocial(vista.getTxtRazonSocial().getText());
						domicilioComercio.setRif(vista.getTxtRif().getText());
						domicilioComercio.setTelefono(vista.getTxtTelefono().getText());
						domicilioComercio.setRuta((Ruta)vista.getCmbRutas().getSelectedItem());
						
						if(vista.getCmbTipoCliente().getSelectedItem().equals("Domicilio"))
						{
							domicilioComercio.setTipo("D");
						}

						if(vista.getCmbTipoCliente().getSelectedItem().equals("Comercial"))
						{
							domicilioComercio.setTipo("C");
						}
						domicilioComercio.setDiaVisita(getCheckDiaVisita(vista.getCheckLune().isSelected(),
								vista.getCheckMarte().isSelected(), vista.getCheckMiercole().isSelected(),
								vista.getCheckJueve().isSelected(), vista.getCheckVierne().isSelected(),
								vista.getCheckSabado().isSelected()));
						servicioDomicilioComercio.guardar(domicilioComercio);
						activarBindingDomicilioComercios(new ServicioDomicilioComercio().getDomicilioComercios());								
						JOptionPane.showMessageDialog(vista,"Operacion Exitosa");							
					}
					vista.getBtnEliminar().setVisible(true);
					vista.getTxtABuscar().setVisible(true);
					vista.getBtnABuscar().setVisible(true);
					vista.remove(vista.getPnCliente());
					vista.repaint();
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(vista,e.getMessage());
				}
			}


		};
	}

	private void validar() throws Exception {
		// TODO Auto-generated method stub
		 
		if(vista.getTxtRif().getText().trim().length() == 0)
		{
			throw new Exception("Ingrese el Rif del Cliente");
		}	
		
		//Validar unicidad RIF
		Cliente cliente = new ServicioCliente().getCliente(vista.getTxtRif().getText().trim());
		if ( cliente != null && cliente.getId() == null) 
		{
			throw new Exception("Este RIF se Encuentra Asignado a otro Cliente");
		}
		
		if(vista.getTxtDireccion().getText().trim().length()<= 5)
		{
			throw new Exception("Debe Ingresar una Dirección mas larga. ");
		}
		if(vista.getTxtRazonSocial().getText().trim().length() == 0)
		{			
			throw new Exception("Ingrese la Razon Social del Cliente ");
		}
		if(vista.getTxtTelefono().getText().trim().length() == 0)
		{
			throw new Exception("Ingrese el telefono del Cliente ");
		}
		if(vista.getCmbRutas().getSelectedItem()  == null )
		{
			throw new Exception(" Selecione la ruta ");
		}

	}

	public ActionListener AbrirRutas() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					
					if (vista.getTxtId().getText().equals("")) cliente.setId(null);
					else cliente.setId(new Integer(vista.getTxtId().getText().toString()));
					
					cliente.setDireccion(vista.getTxtDireccion().getText());
					cliente.setRazonsocial(vista.getTxtRazonSocial().getText());
					cliente.setRif(vista.getTxtRif().getText());
					cliente.setTelefono(vista.getTxtTelefono().getText());
						
						new ContRutas(getContPrincipal());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
	}
	public ActionListener buscar() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				vista.setTable(buscar(vista.getTable(),vista.getTxtABuscar().getText().toString().trim()));
				Integer fila = new Integer(vista.getTable().getSelectedRow());
				if(fila>=0)
				{
					cargarCliente((Cliente)clientes.get(fila));
				}
				else 
				{
					JOptionPane.showMessageDialog(new JPanel(),"No Encontrado");
					cargarCliente(new Cliente());
				}
			}
		};
	}
	
	public void setVista(ClientesUI vista) {
		this.vista = vista;
	}

	public ActionListener atras() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					if (vista.getTable().getSelectedRow()>=0)
					{
						Cliente cliente = (Cliente) clientes.get(vista.getTable().getSelectedRow());
						//cliente = new ServicioCliente().buscar(new Integer(vista.getTable().getValueAt(vista.getTable().getSelectedRow(),0).toString().trim()));
						ActivarAtras(cliente);
					}
					else 
						ActivarAtras(null);
			}
		};
	}
	
	public ActionListener salir(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				quitarVista();
			}
		};
		
	}

	public ActionListener eliminar() {
		// TODO Auto-generated method 
		
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (vista.getTable().getSelectedRow()>=0)
				{
					try {				
							if(vista.getCmbTipoCliente().getSelectedItem().equals("Salp"))
							{	
								Cliente cliente = (Cliente) clientes.get(vista.getTable().getSelectedRow());
								new ServicioSalp().eliminar((Salp)cliente);
								new ServicioCliente().eliminar(cliente);
								JOptionPane.showMessageDialog(vista,"Operacion Exitosa");
								MostrarTabla();
							}
							else if(vista.getCmbTipoCliente().getSelectedItem().equals("Domicilio")|| vista.getCmbTipoCliente().getSelectedItem().equals("Comercial"))
							{
								new ServicioDomicilioComercio().eliminar((DomicilioComercio)cliente);
								new ServicioCliente().eliminar(cliente);
								JOptionPane.showMessageDialog(vista,"Operacion Exitosa");
								MostrarTabla();
							}
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
							JOptionPane.showMessageDialog(vista,e.getMessage());
						}
					}
					else 
						JOptionPane.showMessageDialog(vista,"Seleccione la fila");
				}
		};
	}
	
	void MostrarTabla() throws NumberFormatException, IOException{
			
		if(vista.getCmbTipoCliente().getSelectedItem().equals("Salp"))
			activarBindingSalp( servicioSalp.getSalps());
		else 
			activarBindingDomicilioComercios(
						servicioDomicilioComercio.getDomicilioComercios());
	}
	
	public ActionListener ActivarTipoUsuario() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {		
						vista.getBtnEliminar().setVisible(true);
						vista.getTxtABuscar().setVisible(true);
						vista.getBtnABuscar().setVisible(true);
						if(vista.getCmbTipoCliente().getSelectedItem().equals("Salp"))
						{
							activarBindingSalp(new ServicioSalp().getSalps());
						}
						else if(vista.getCmbTipoCliente().getSelectedItem().equals("Domicilio")||
								vista.getCmbTipoCliente().getSelectedItem().equals("Comercial")) {
	
							if(rutas.isEmpty())
							{
								activarBindingDomicilioComercios(new ServicioDomicilioComercio().getDomicilioComercios());
							}
							else
							{
								activarBindingDomicilioComercios(new ServicioDomicilioComercio().getDomicilioComercios(rutas));	
							}
						}
					} catch (NumberFormatException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						}
			}
		};
	}

	public List<Ruta> getRutas() {
		return rutas;
	}

	public void setRutas(List<Ruta> rutas) {
		this.rutas = rutas;
	}

	public MouseAdapter mostrarCliente() {
		// TODO Auto-generated method stub
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evento) {
				if (evento.getClickCount()==1)
				{
					cliente = (Cliente) clientes.get(vista.getTable().getSelectedRow());
					cargarCliente(cliente);
				}
			}
		};
	}

	public KeyAdapter mostrarCliente_keypress() {
		// TODO Auto-generated method stub
		return new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				Integer fila = new Integer(vista.getTable().getSelectedRow());
				Integer contFila = clientes.size();
				 
				if(e.getKeyCode()==38 )
				{
					if(fila<=0) cargarCliente((Cliente) clientes.get(0));
					else cargarCliente((Cliente)clientes.get(fila-1));
				}
				else if(e.getKeyCode()==40 )
				{
					if(fila+1>=contFila) cargarCliente((Cliente)clientes.get(contFila-1));
					else cargarCliente((Cliente)clientes.get(fila+1));
				}

			}
		};
	}
	
	public void actualizarContCliente(Object objetContCachePresente,Object objetClassVista){
			if (objetContCachePresente instanceof ContRutas && objetClassVista != null)
			{
				this.cliente.setRuta((Ruta)objetClassVista);
				cargarCliente(cliente);
			}
	}

	public void actualizarVista(){
		if (vista != null)
		{	
			dibujar(vista,this); 
		}
		cargarCliente(cliente);
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void cargarCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		vista.remove(vista.getPnCliente());
		vista.dibujarPanelCliente();
		activarJComboBoxBindingRuta();
 			if (vista.getTable().getSelectedRow() >= 0 )
			{
				if(cliente.getId() != null)
				{
					vista.getTxtId().setText(cliente.getId().toString());
				}
				vista.getTxtDireccion().setText(cliente.getDireccion());
				vista.getTxtRazonSocial().setText(cliente.getRazonsocial());
				vista.getTxtRif().setText(cliente.getRif());
				vista.getTxtTelefono().setText(cliente.getTelefono());
				if(cliente instanceof DomicilioComercio)
				{
					vista.getPnCheckDia().setVisible(true);
					vista.mostrarDiaVisita( ((DomicilioComercio) cliente ).getDiaVisita());
				}
				else
				{
					vista.getPnCheckDia().setVisible(false);
				}
				if(cliente.getRuta() != null && cliente.getRuta().getId() !=null )
				{
					vista.getLblRuta().setText("Ruta:");
					for(int i = 0; i < rutas.size(); i++)
					{	
						Ruta ruta = rutas.get(i);
						if (ruta.getId().equals(cliente.getRuta().getId()))
						{
							vista.getCmbRutas().setSelectedIndex(i);
						}
					}
				}
			}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	 
	public void activarBindingSalp(List<Salp>  salps) {
		// TODO Auto-generated method stub
		clientes = salps;
		vista.getPnTabla().setVisible(true);
		vista.setTable(new JTable());
		vista.getScrollPanel().setViewportView(vista.getTable());
		JTableBinding binClientes = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,salps,vista.getTable());
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
		vista.getTable().addKeyListener(mostrarCliente_keypress());
		vista.getTable().addMouseListener(mostrarCliente());
		
		vista.remove(vista.getPnCliente());
		vista.repaint();
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void activarBindingDomicilioComercios(List<DomicilioComercio>  domicilioComercios) {
		// TODO Auto-generated method stub
		clientes = domicilioComercios;
		vista.getPnTabla().setVisible(true);
		vista.setTable(new JTable());
		vista.getScrollPanel().setViewportView(vista.getTable());

		List<DomicilioComercio> aux = new ArrayList<DomicilioComercio>();
		
		for(Object obj : domicilioComercios)
		{	
			DomicilioComercio domicilioComercio = (DomicilioComercio) obj;
			if(domicilioComercio.getTipo().equals("C") && vista.getCmbTipoCliente().getSelectedItem().equals("Comercial")) 
				aux.add(domicilioComercio);
			else if(domicilioComercio.getTipo().equals("D")  && vista.getCmbTipoCliente().getSelectedItem().equals("Domicilio"))
				aux.add(domicilioComercio);
		}
		domicilioComercios = aux;
		this.clientes = domicilioComercios;
	
		JTableBinding binClientes = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,domicilioComercios,vista.getTable());
		
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
		
		vista.getTable().addKeyListener(mostrarCliente_keypress());
		vista.getTable().addMouseListener(mostrarCliente());
 
		vista.remove(vista.getPnCliente());
		vista.repaint();
}
	
	@SuppressWarnings("rawtypes")
	public void activarJComboBoxBindingRuta(){
		JComboBoxBinding jcomboRutas = SwingBindings.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ,rutas,vista.getCmbRutas());
	    jcomboRutas.bind();
	}

	
}
