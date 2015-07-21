package santaclara.controlador;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import santaclara.Servicio.ServicioDomicilioComercio;
import santaclara.Servicio.ServicioRuta;
import santaclara.Servicio.ServicioCliente;
import santaclara.Servicio.ServicioSalp;
import santaclara.modelo.DomicilioComercio;
import santaclara.modelo.Cliente;
import santaclara.modelo.Ruta;
import santaclara.modelo.Salp;
import santaclara.vista.ClientesUI;

public class ContClientes extends ContGeneral implements IContGeneral{
	
	private ServicioCliente servicioCliente = new ServicioCliente();
	private ServicioRuta servicioRuta = new ServicioRuta();
	private ClientesUI vista ;
	
	public ContClientes(ContPrincipal contPrincipal) throws Exception {
		// TODO Auto-generated constructor stub
		setContPrincipal(contPrincipal);
		
		servicioCliente = new ServicioCliente();
		servicioRuta = new ServicioRuta();
		
		vista = new ClientesUI(this, servicioCliente.getClientes(),
						servicioRuta.getRutas());
		vista.activarBindingSalp(new ServicioSalp().getSalps());
		dibujar(vista);
		vista.quitarNuevo();
	}

	@Override
	public JPanel getVista() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ActionListener nuevo(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				vista.activarNuevo();
				vista.LimpiarTxt();
				vista.getPnTabla().setVisible(false);
			}
		};
	}
	
	
	
	public ActionListener modificar() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (vista.getTable().getSelectedRow()>=0)
				{
					if (vista.getTable().getSelectedRow()>=0)
					{	
							Cliente cliente  = new Cliente();
							try {
								cliente = new ServicioCliente().buscar(new Integer(vista.getTable().getValueAt(vista.getTable().getSelectedRow(),0).toString().trim()));
							} catch (NumberFormatException | IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							if (cliente != null)
							{
								vista.activarNuevo();
								
								vista.getTxtId().setText(cliente.getId().toString());
								vista.getTxtDireccion().setText(cliente.getDireccion());
								vista.getTxtRazonSocial().setText(cliente.getRazonsocial());
								vista.getTxtRif().setText(cliente.getRif());
								vista.getTxtTelefono().setText(cliente.getTelefono());
								setSelectedValue(vista.getComboRutas(), cliente.getRuta().getId());
							}
					}
				}
				else JOptionPane.showMessageDialog(vista,"Seleccione la fila");
			}
				
			};
		}
	@SuppressWarnings("rawtypes")
	public void setSelectedValue(JComboBox comboBox,Integer id)
    {	
        for (int i = 0; i < comboBox.getItemCount(); i++)
        {
        	comboBox.setSelectedIndex(i);
        	Boolean enc=false;
        	switch (comboBox.getSelectedItem().getClass().getName().toString()) {
			case "santaclara.modelo.Ruta":
				enc = (((Ruta)comboBox.getSelectedItem()).getId().equals(id)); 
					break;
			default:
				break;
			}
        	if (enc) break;
        }
    }
	
	public ActionListener guardar() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					if(vista.getCmbTipoUsuario().getSelectedItem().equals("Salp"))
					{	
						Salp salp = new Salp();
						
						if (vista.getTxtId().getText().equals("")) salp.setId(null);
						else salp.setId(new Integer(vista.getTxtId().getText().toString()));
						
						salp.setDireccion(vista.getTxtDireccion().getText());
						salp.setRazonsocial(vista.getTxtRazonSocial().getText());
						salp.setRif(vista.getTxtRif().getText());
						salp.setTelefono(vista.getTxtTelefono().getText());
						salp.setRuta((Ruta) vista.getComboRutas().getSelectedItem());
						
						try {
							
							JOptionPane.showMessageDialog(vista,
									new ServicioSalp().guardar(salp));
							vista.getBinClientes().unbind();
							vista.getBinClientes().bind();
							vista.activarBindingSalp(new ServicioSalp().getSalps());
							vista.quitarNuevo();
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
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
						domicilioComercio.setRuta((Ruta) vista.getComboRutas().getSelectedItem());
						
						if(vista.getCmbTipoUsuario().getSelectedItem().equals("Domicilio"))
							domicilioComercio.setTipo("D");
						if(vista.getCmbTipoUsuario().getSelectedItem().equals("Comercial"))
							domicilioComercio.setTipo("C");
							try {
								JOptionPane.showMessageDialog(vista,
										new ServicioDomicilioComercio().guardar(domicilioComercio));
								
								vista.getBinClientes().unbind();
								vista.getBinClientes().bind();
								vista.activarBindingDomicilioComercios(new ServicioDomicilioComercio().getDomicilioComercios());
								vista.quitarNuevo();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					}
				} catch (HeadlessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		};
	}
	public ActionListener AbrirRutas() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
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
				ActivarAtras();
			}
		};
	}
	
	public ActionListener salir(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				qutarVista();
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
							if(vista.getCmbTipoUsuario().getSelectedItem().equals("Salp"))
							{	
								new ServicioSalp().eliminar(new ServicioSalp().buscar(
										new Integer(vista.getTable().getValueAt(
												vista.getTable().getSelectedRow(),0).toString())));
					
								servicioCliente.eliminar(new ServicioCliente().buscar(
										new Integer(vista.getTable().getValueAt(
												vista.getTable().getSelectedRow(),0).toString())));
								
								JOptionPane.showMessageDialog(vista,"Operacion Exitosa");
								MostrarTabla();
							}
							else if(vista.getCmbTipoUsuario().getSelectedItem().equals("Domicilio")||
									vista.getCmbTipoUsuario().getSelectedItem().equals("Comercial"))
							{
								new ServicioDomicilioComercio().eliminar(new ServicioDomicilioComercio().buscar(
										new Integer(vista.getTable().getValueAt(
												vista.getTable().getSelectedRow(),0).toString())));
					
								servicioCliente.eliminar(new ServicioCliente().buscar(
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
		vista.getBinClientes().unbind();
		vista.getBinClientes().bind();		
		if(vista.getCmbTipoUsuario().getSelectedItem().equals("Salp"))
		vista.activarBindingSalp(new ServicioSalp().getSalps());
		else vista.activarBindingDomicilioComercios(
				new ServicioDomicilioComercio().getDomicilioComercios());
		vista.quitarNuevo();
	}
	
	public ActionListener ActivarTipoUsuario() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
						if(vista.getCmbTipoUsuario().getSelectedItem().equals("Salp")){
							vista.activarBindingSalp(new ServicioSalp().getSalps());
						}
						else if(vista.getCmbTipoUsuario().getSelectedItem().equals("Domicilio")||
								vista.getCmbTipoUsuario().getSelectedItem().equals("Comercial")){
	
							vista.activarBindingDomicilioComercios(new ServicioDomicilioComercio().getDomicilioComercios());
	
						}
					} catch (NumberFormatException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						}
				vista.quitarNuevo();
			}
		};
	}
}
