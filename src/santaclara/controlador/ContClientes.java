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
import javax.swing.JTable;
 

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
	private List<Ruta> 		rutas  = new ArrayList<Ruta>();
	private ServicioSalp    servicioSalp;
	private ServicioDomicilioComercio servicioDomicilioComercio;
	
	
	public ContClientes(ContPrincipal contPrincipal) throws Exception {
		// TODO Auto-generated constructor stub
		servicioSalp = new ServicioSalp();
		servicioDomicilioComercio = new ServicioDomicilioComercio();
		setContPrincipal(contPrincipal);
		vista = new ClientesUI(this, servicioSalp.getSalps(), new ServicioRuta().getRutas());
		dibujar(vista,this);
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
				vista.activarNuevoCliente();
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
		System.out.println(Integer.valueOf(cadena.toString(),2));
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
						vista.activarBindingSalp(servicioSalp.getSalps());							
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
						vista.activarBindingDomicilioComercios(new ServicioDomicilioComercio().getDomicilioComercios());								
						JOptionPane.showMessageDialog(vista,"Operacion Exitosa");							
					}
					vista.LimpiarTxt();
					vista.repaint();
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
					if (vista.getTable().getSelectedRow()>=0)
					{
						Cliente cliente = (Cliente) vista.getClientes().get(vista.getTable().getSelectedRow());
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
								Cliente cliente = (Cliente) vista.getClientes().get(vista.getTable().getSelectedRow());
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
							System.out.print(e.getMessage());
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
			vista.activarBindingSalp( servicioSalp.getSalps());
		else 
			vista.activarBindingDomicilioComercios(
						servicioDomicilioComercio.getDomicilioComercios());
	}
	
	public ActionListener ActivarTipoUsuario() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					vista.LimpiarTxt();
						if(vista.getCmbTipoCliente().getSelectedItem().equals("Salp"))
						{
							vista.activarBindingSalp(new ServicioSalp().getSalps());
						}
						else if(vista.getCmbTipoCliente().getSelectedItem().equals("Domicilio")||
								vista.getCmbTipoCliente().getSelectedItem().equals("Comercial")) {
	
							if(rutas.isEmpty())
							{
								vista.activarBindingDomicilioComercios(new ServicioDomicilioComercio().getDomicilioComercios());
							}
							else
							{
								vista.activarBindingDomicilioComercios(new ServicioDomicilioComercio().getDomicilioComercios(rutas));	
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
					cliente = (Cliente) vista.getClientes().get(vista.getTable().getSelectedRow());
					vista.cargarCliente();
				}
			}
		};
	}

	public KeyAdapter mostrarCliente_keypress() {
		// TODO Auto-generated method stub
		return new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==38 || e.getKeyCode()==40 )
				{
					cliente = (Cliente) vista.getClientes().get(vista.getTable().getSelectedRow());
					vista.cargarCliente();
				}
			}
		};
	}  
	public void actualizarContCliente(Object objetContCachePresente,Object objetClassVista){
			if (objetContCachePresente instanceof ContRutas && objetClassVista != null)
			{
				this.cliente.setRuta((Ruta)objetClassVista);
				vista.cargarCliente();
			}
	}

	public void actualizarVista(){
		if (vista != null)
		{	
			dibujar(vista,this); 
		}
		vista.cargarCliente();
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
}
