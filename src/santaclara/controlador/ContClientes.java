package santaclara.controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import santaclara.Servicio.ServicioDomicilioComercio;
import santaclara.Servicio.ServicioCliente;
import santaclara.Servicio.ServicioSalp;
import santaclara.modelo.DomicilioComercio;
import santaclara.modelo.Cliente;
import santaclara.modelo.Ruta;
import santaclara.modelo.Salp;
import santaclara.vista.ClientesUI;

public class ContClientes extends ContGeneral implements IContGeneral{
	
	private ClientesUI vista ;
	private Cliente cliente = new Cliente();
	private List<Ruta> rutas  = new ArrayList<Ruta>();
	@SuppressWarnings("rawtypes")
	private JTableBinding binClientes;
	
	public ContClientes(Cliente cliente)throws Exception{
		setCliente(cliente);
	}
		
	public ContClientes(ContPrincipal contPrincipal) throws Exception {
		// TODO Auto-generated constructor stub
		setContPrincipal(contPrincipal);
		vista = new ClientesUI(this);
		dibujar(vista,this);
		
		activarBindingSalp(new ServicioSalp().getSalps());
		vista.getPnCheckDia().setVisible(false);
		vista.getLblDiaVisita().setVisible(false);
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
				vista.LimpiarTxt();
				cliente = new Cliente();
			}
		};
	}
	
	
	
	public void cargarCliente() {
		// TODO Auto-generated method stub	
		try {
			vista.LimpiarTxt();
			if(cliente.getId() != null)
			{
				vista.getTxtId().setText(cliente.getId().toString());
				vista.getTxtDireccion().setText(cliente.getDireccion());
				vista.getTxtRazonSocial().setText(cliente.getRazonsocial());
				vista.getTxtRif().setText(cliente.getRif());
				vista.getTxtTelefono().setText(cliente.getTelefono());
				mostrarDiaVisita(cliente.getId());
			}
			else
			{
				vista.getTxtDireccion().setText(cliente.getDireccion());
				vista.getTxtRazonSocial().setText(cliente.getRazonsocial());
				vista.getTxtRif().setText(cliente.getRif());
				vista.getTxtTelefono().setText(cliente.getTelefono());
				mostrarDiaVisita(cliente.getId());
			}
			
			if(cliente.getRuta()!=null && cliente.getRuta().getId()!=null)
			{
				vista.getLblRuta().setText("Ruta: ".concat(cliente.getRuta().getNombre()).concat(", Zona: ").concat(cliente.getRuta().getZona().getDescripcion()));
				vista.getBtnAbrirRuta().setBackground(Color.DARK_GRAY);
			}
			else
			{
				vista.getLblRuta().setText("Ruta: ");
				vista.getBtnAbrirRuta().setBackground(Color.YELLOW);
			}
				
			
		} catch (NumberFormatException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}
	}
	
	public void mostrarDiaVisita(Integer id) throws IOException{
		if (vista.getCmbTipoCliente().getSelectedItem().equals("Domicilio") || 
				vista.getCmbTipoCliente().getSelectedItem().equals("Comercial"))
		{
			vista.getPnCheckDia().setVisible(true);
			vista.getLblDiaVisita().setVisible(true);
			setCkeckDiaVisita((new ServicioDomicilioComercio().getdDomicilioComercio(id)).getDiaVisita());	
		}
		else 
		{
			vista.getPnCheckDia().setVisible(false);
			vista.getLblDiaVisita().setVisible(false);
		}
	}
	
	public void setCkeckDiaVisita(Integer numero){
		 Integer exp, digito;
	        Double binario;
	        
	        exp= new Integer(0);
	        binario= new Double(0);
	        while(numero!=0){
	                digito = numero % 2;           
	                binario = binario + digito * Math.pow(10, exp);
	               
	                switch (exp) {
					case 0:
						if (new Integer((int) (digito * Math.pow(10, exp))).equals(1)) //si se asigna el domingo;
						break;
					case 1:if (new Integer((int) (digito * Math.pow(10, exp))).equals(10)) vista.getCheckLune().setSelected(true);
					break;
					case 2:if (new Integer((int) (digito * Math.pow(10, exp))).equals(100)) vista.getCheckMarte().setSelected(true);
					break;
					case 3:if (new Integer((int) (digito * Math.pow(10, exp))).equals(1000)) vista.getCheckMiercole().setSelected(true);
					break;
					case 4:if (new Integer((int) (digito * Math.pow(10, exp))).equals(10000)) vista.getCheckJueve().setSelected(true);
					break;
					case 5:if (new Integer((int) (digito * Math.pow(10, exp))).equals(100000)) vista.getCheckVierne().setSelected(true);
					break;
					case 6:if (new Integer((int) (digito * Math.pow(10, exp))).equals(1000000)) vista.getCheckSabado().setSelected(true);
					break;

					default:
						break;
					}
	      
	                exp++;
	                numero = numero/2;
	        }
	}

	public Integer getCheckDiaVisita(Boolean getCheckDomingo,Boolean getCheckLune,
			Boolean getCheckMarte,Boolean getCheckMiercole,Boolean getCheckJueve,Boolean getCheckVierne,Boolean getCheckSabado){
		Integer decimal = new Integer(0);
		if(getCheckDomingo.booleanValue()) decimal = decimal + (int) Math.pow(2,0);
		if(getCheckLune.booleanValue()) decimal = decimal + (int) Math.pow(2,1);
		if(getCheckMarte.booleanValue()) decimal = decimal + (int) Math.pow(2,2);
		if(getCheckMiercole.booleanValue()) decimal = decimal + (int) Math.pow(2,3);
		if(getCheckJueve.booleanValue()) decimal = decimal + (int) Math.pow(2,4);
		if(getCheckVierne.booleanValue()) decimal = decimal + (int) Math.pow(2,5);
		if(getCheckSabado.booleanValue()) decimal = decimal + (int) Math.pow(2,6);
		return decimal;

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
						if (vista.getTxtId().getText().equals("")) salp.setId(null);
						else salp.setId(new Integer(vista.getTxtId().getText().toString()));
					
						salp.setDireccion(vista.getTxtDireccion().getText());
						salp.setRazonsocial(vista.getTxtRazonSocial().getText());
						salp.setRif(vista.getTxtRif().getText());
						salp.setTelefono(vista.getTxtTelefono().getText());
						salp.setRuta(cliente.getRuta());
						new ServicioSalp().guardar(salp);
						
						activarBindingSalp(new ServicioSalp().getSalps());							
						JOptionPane.showMessageDialog(vista,"Operacion Exitosa");
					}
					else
					{
						DomicilioComercio domicilioComercio = new DomicilioComercio();
						
						if (vista.getTxtId().getText().equals("")) domicilioComercio.setId(null);
						else domicilioComercio.setId(new Integer(vista.getTxtId().getText().toString()));
						
						domicilioComercio.setDireccion(vista.getTxtDireccion().getText());
						domicilioComercio.setRazonsocial(vista.getTxtRazonSocial().getText());
						domicilioComercio.setRif(vista.getTxtRif().getText());
						domicilioComercio.setTelefono(vista.getTxtTelefono().getText());
						domicilioComercio.setRuta(cliente.getRuta());
						
						if(vista.getCmbTipoCliente().getSelectedItem().equals("Domicilio"))
						{
							domicilioComercio.setTipo("D");
						}
						if(vista.getCmbTipoCliente().getSelectedItem().equals("Comercial"))
						{
							domicilioComercio.setTipo("C");
						}
						domicilioComercio.setDiaVisita(getCheckDiaVisita(false, vista.getCheckLune().isSelected(),
								vista.getCheckMarte().isSelected(), vista.getCheckMiercole().isSelected(),
								vista.getCheckJueve().isSelected(), vista.getCheckVierne().isSelected(),
								vista.getCheckSabado().isSelected()));
						new ServicioDomicilioComercio().guardar(domicilioComercio);
						
						activarBindingDomicilioComercios(new ServicioDomicilioComercio().getDomicilioComercios());								
						JOptionPane.showMessageDialog(vista,"Operacion Exitosa");							
					}
					vista.LimpiarTxt();
					cliente = new Cliente();
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
		if(vista.getLblRuta().getText().equals(""))
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
				JTable tabla1 = new JTable();
				tabla1 = vista.getTable();
				Boolean enc = false;
				for(int i = 0;i<tabla1.getRowCount();i++)
				{
					if (tabla1.getValueAt(i, 0).toString().trim().equals(vista.getTxtABuscar().getText().toString().trim())||
						tabla1.getValueAt(i, 1).toString().trim().equals(vista.getTxtABuscar().getText().toString().trim())||
						tabla1.getValueAt(i, 2).toString().trim().equals(vista.getTxtABuscar().getText().toString().trim())||
						tabla1.getValueAt(i, 3).toString().trim().equals(vista.getTxtABuscar().getText().toString().trim())||
						tabla1.getValueAt(i, 4).toString().trim().equals(vista.getTxtABuscar().getText().toString().trim()))
					{
						tabla1.setRowSelectionInterval(i,i);
						enc = true;
						break;
					}
				}
				if (!enc) JOptionPane.showMessageDialog(vista,"No Encontrado");
				vista.setTable(tabla1);
				vista.getTxtABuscar().setText("");
				
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
				try {
					if (vista.getTable().getSelectedRow()>=0)
					{
						Cliente cliente = new Cliente();
						cliente = new ServicioCliente().buscar(new Integer(vista.getTable().getValueAt(vista.getTable().getSelectedRow(),0).toString().trim()));
						ActivarAtras(cliente);
					}
					else ActivarAtras(null);
					
				} catch (NumberFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
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
								new ServicioSalp().eliminar(new ServicioSalp().buscar(
										new Integer(vista.getTable().getValueAt(
												vista.getTable().getSelectedRow(),0).toString())));
					
								new ServicioCliente().eliminar(new ServicioCliente().buscar(
										new Integer(vista.getTable().getValueAt(
												vista.getTable().getSelectedRow(),0).toString())));
								
								JOptionPane.showMessageDialog(vista,"Operacion Exitosa");
								MostrarTabla();
							}
							else if(vista.getCmbTipoCliente().getSelectedItem().equals("Domicilio")||
									vista.getCmbTipoCliente().getSelectedItem().equals("Comercial"))
							{
								new ServicioDomicilioComercio().eliminar(new ServicioDomicilioComercio().buscar(
										new Integer(vista.getTable().getValueAt(
												vista.getTable().getSelectedRow(),0).toString())));
					
								new ServicioCliente().eliminar(new ServicioCliente().buscar(
										new Integer(vista.getTable().getValueAt(
												vista.getTable().getSelectedRow(),0).toString())));
								JOptionPane.showMessageDialog(vista,"Operacion Exitosa");
								MostrarTabla();
							}
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
					else JOptionPane.showMessageDialog(vista,"Seleccione la fila");
				}
		};
	}
	
	void MostrarTabla() throws NumberFormatException, IOException{
			
		if(vista.getCmbTipoCliente().getSelectedItem().equals("Salp"))
		activarBindingSalp(new ServicioSalp().getSalps());
		else activarBindingDomicilioComercios(
				new ServicioDomicilioComercio().getDomicilioComercios());
	}
	
	public ActionListener ActivarTipoUsuario() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					vista.LimpiarTxt();
						if(vista.getCmbTipoCliente().getSelectedItem().equals("Salp")){
							activarBindingSalp(new ServicioSalp().getSalps());
						}
						else if(vista.getCmbTipoCliente().getSelectedItem().equals("Domicilio")||
								vista.getCmbTipoCliente().getSelectedItem().equals("Comercial")){
	
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
					try {
						cliente = new ServicioCliente().buscar(new Integer(vista.getTable().getValueAt(vista.getTable().getSelectedRow(),0).toString().trim()));
						cargarCliente();
					} catch (NumberFormatException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		};
	}
	
	public void actualizarContCliente(Object objetContCachePresente,Object objetClassVista){

			switch (objetContCachePresente.getClass().getName()) {
			case "santaclara.controlador.ContRutas":
				if (objetClassVista != null)
				{
					
						this.cliente.setRuta((Ruta)objetClassVista);
						
						cargarCliente();
				}
				break;
				default:
					break;
			}
			
	}

	public void actualizarVista(){
		if (vista != null)dibujar(vista,this);//Actualiza la vista
		if (cliente!=null)
		{
			cargarCliente();
		} 
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void activarBindingDomicilioComercios(List<DomicilioComercio> domicilioComercios) {
		// TODO Auto-generated method stub
		vista.getScrollPanel().setViewportView(vista.getTable());
		
		List<DomicilioComercio> aux = new ArrayList<DomicilioComercio>();
		
		for(DomicilioComercio domicilioComercio : domicilioComercios)
		{
			if(domicilioComercio.getTipo().equals("C") && vista.getCmbTipoCliente().getSelectedItem().equals("Comercial")) 
				aux.add(domicilioComercio);
			else if(domicilioComercio.getTipo().equals("D")  && vista.getCmbTipoCliente().getSelectedItem().equals("Domicilio"))
				aux.add(domicilioComercio);
		}
		
		domicilioComercios = aux;

		binClientes = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,domicilioComercios,vista.getTable());
		
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
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void activarBindingSalp(List<Salp> salps) {
		// TODO Auto-generated method stub
		vista.getScrollPanel().setViewportView(vista.getTable());
		
		binClientes = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,
    			salps,vista.getTable());
		
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

	}

	public KeyAdapter keyPressTable() {
		// TODO Auto-generated method stub
		return new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
					try {
						if(e.getKeyCode()==38)
						{
							Integer row = new Integer(vista.getTable().getSelectedRow()-1);
							if(row > 0)
							{
								cliente = new ServicioCliente().buscar(new Integer(vista.getTable().getValueAt(row,0).toString().trim()));
							}
						}
						if(e.getKeyCode()==40)
						{
							Integer row = new Integer(vista.getTable().getSelectedRow()+1);
							
							if(row < vista.getTable().getRowCount())
							{
								cliente = new ServicioCliente().buscar(new Integer(vista.getTable().getValueAt(row,0).toString().trim()));
							}
						}
						cargarCliente();
					} catch (NumberFormatException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				
			}
		};
	}

}
