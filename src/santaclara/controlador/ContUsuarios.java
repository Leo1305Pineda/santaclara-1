/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.controlador;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;

import santaclara.Servicio.ServicioCamion;
import santaclara.Servicio.ServicioConcesionario;
import santaclara.Servicio.ServicioDomicilioComercio;
import santaclara.Servicio.ServicioJefeVenta;
import santaclara.Servicio.ServicioRuta;
import santaclara.Servicio.ServicioSalp;
import santaclara.Servicio.ServicioUsuario;
import santaclara.Servicio.ServicioVendedor;
import santaclara.Servicio.ServicioZona;
import santaclara.modelo.Concesionario;
import santaclara.modelo.DomicilioComercio;
import santaclara.modelo.Ruta;
import santaclara.modelo.Salp;
import santaclara.modelo.Usuario;
import santaclara.vista.UsuariosUI;

public class ContUsuarios extends ContGeneral implements IContGeneral{
	
	private ServicioUsuario servicioUsuario = new ServicioUsuario();
	private ServicioRuta servicioRuta = new ServicioRuta();
	private ServicioZona servicioZona = new ServicioZona();
	private ServicioCamion servicioCamion = new ServicioCamion();
	private UsuariosUI vista ;

	private List<Usuario> usuarios = new ArrayList<Usuario>();
	
	private ContMediador mediador = new ContMediador();

	public ContUsuarios(ContPrincipal contPrincipal) throws Exception {
		// TODO Auto-generated constructor stub
		setContPrincipal(contPrincipal);
		vista = new UsuariosUI(this, usuarios,
						servicioRuta.getRutas(),servicioZona.getZonas(),
						servicioCamion.getCamiones());
		vista.activarBinding(servicioUsuario.getUsuarios());
		dibujar(vista,this);
		vista.quitarNuevo();
	}

	@Override
	public JPanel getVista() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public UsuariosUI getVistaUsuario(){
		return vista;
	}
	
	public ActionListener nuevo(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					vista.activarNuevo();
					vista.LimpiarTxt();				
					cargarRutas();
					vista.getPnTabla().setVisible(false);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		};
	}
	
	public ActionListener abrirRuta() {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					new ContRutas(getContPrincipal());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
	}
	
	public ActionListener abrirZonas(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					new ContZonas(getContPrincipal());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
	}
	public ActionListener abrirCamiones(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					new ContCamiones(getContPrincipal());
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
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
					vista.activarNuevo();
					try {
						cargarRutas();
					
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (vista.getTable().getSelectedRow()>=0)
					{
						if(vista.getCmbTipoUsuario().getSelectedItem().equals("JefeVenta"))
						{
							//	new ContJefeVentas(vista).Modificar();;
						}
						else if(vista.getCmbTipoUsuario().getSelectedItem().equals("Vendedor"))
						{
							new ContVendedores(vista).Modificar();
						}
						else if(vista.getCmbTipoUsuario().getSelectedItem().equals("Concesionario"))
						{
							new ContConcesionarios(vista).Modificar();;
						}else Modificar();		
					}
				}
				else JOptionPane.showMessageDialog(vista,"Seleccione la fila");
			}		

			private void Modificar() {
				// TODO Auto-generated method stub
				try {	
						Usuario usuario  = new Usuario();
						usuario = (Usuario) usuarios.get(vista.getTable().getSelectedRow());
				
						if (usuario != null)
						{
							vista.activarNuevo();
							vista.getScrollPanel().setVisible(false);
			
							vista.getTxtId().setText(usuario.getId().toString());
							vista.getTxtNombre().setText(usuario.getNombre());
							vista.getTxtCedula().setText(usuario.getCedula());
							vista.getTxtContrasena().setText(usuario.getContrasena());
							vista.getTxtUserName().setText(usuario.getUsername());
							vista.getTxtReContrasena().setText(usuario.getContrasena());//Temporal 
			
						}else JOptionPane.showMessageDialog(vista,"Seleccione la fila");
						} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						}
					}		
			};
		}
	
	public ActionListener guardar() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					if(vista.getCmbTipoUsuario().getSelectedItem().equals("JefeVenta"))
					{
						
						///new ContJefeVentas(vista).Guardar();
							
					}
					else if(vista.getCmbTipoUsuario().getSelectedItem().equals("Vendedor"))
					{
						new ContVendedores(vista).Guardar();
					}
					else if(vista.getCmbTipoUsuario().getSelectedItem().equals("Concesionario"))
					{
						new ContConcesionarios(vista).Guardar();
					}else
					{
							GuardarUsuario();
					}
				} catch (HeadlessException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			private void GuardarUsuario() throws Exception {
				// TODO Auto-generated method stub
					Usuario usuario = new Usuario(); 
					if (vista.getTxtId().getText().equals("")) JOptionPane.showMessageDialog(vista,"Seleccione el Tipo de Usuario");
					else 
					{
						usuario.setId(new Integer(vista.getTxtId().getText().toString().trim()));			
						usuario.setCedula(vista.getTxtCedula().getText().toString());
						usuario.setNombre(vista.getTxtNombre().getText().toString());
						usuario.setUsername(vista.getTxtUserName().getText().toString());
						usuario.setContrasena(vista.getTxtContrasena().getText().toString());
								
						servicioUsuario.guardar(usuario);
						
						vista.activarBinding(servicioUsuario.getUsuarios());
						JOptionPane.showMessageDialog(vista,"Operacion Exitosa");
						vista.quitarNuevo();
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
						tabla1.getValueAt(i, 1).toString().trim().equals(vista.getTxtABuscar().getText().toString().trim()))
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
	
	public void setVista(UsuariosUI vista) {
		this.vista = vista;
	}

	public void retornarUsuario(Usuario usuario)
	{
		mediador.regresarUsuario(ContUsuarios.this,usuario);
	}
	
	public ActionListener atras() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					if (vista.getTable().getSelectedRow()>=0)
					{
						Usuario usuario  = new Usuario();
						usuario = (Usuario) vista.getUsuarios().get(vista.getTable().getSelectedRow());
						retornarUsuario(usuario);//ActivarAtras(usuario);
					}
 					else 
 					{
 						retornarUsuario(null);//ActivarAtras(null);
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
		// TODO Auto-generated method stubif (vista.getTable().getSelectedRow()>=0)
		
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					if (vista.getTable().getSelectedRow()>=0)
					{			
						if(vista.getCmbTipoUsuario().getSelectedItem().equals("JefeVenta"))
						{
						
							//new ContJefeVentas(vista).Eliminar();
						}
						else if(vista.getCmbTipoUsuario().getSelectedItem().equals("Vendedor"))
						{
							new ContVendedores(vista).Eliminar();
						}
						else if(vista.getCmbTipoUsuario().getSelectedItem().equals("Concesionario"))
						{
							new ContConcesionarios(vista).Eliminar();
						}
						else JOptionPane.showMessageDialog(vista,"Seleccione el tipo de Usuario");
												
					}
				else JOptionPane.showMessageDialog(vista,"Seleccione la fila");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
	}
	
	public ActionListener ActivarTipoUsuario() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					// TODO Auto-generated method stub
					if(vista.getCmbTipoUsuario().getSelectedItem().equals("Todos"))
					{	
						vista.activarBinding(servicioUsuario.getUsuarios());
						vista.getBtnNuevo().setEnabled(false);
					}
					else if(vista.getCmbTipoUsuario().getSelectedItem().equals("JefeVenta"))
					{	
						vista.activarBindingJefeVentas(new ServicioJefeVenta().getJefeVentas());
						vista.getBtnNuevo().setEnabled(true);
						vista.getPnRuta().setVisible(false);
						vista.getPnZona().setBounds(0, 90, 852, 63);	
					}
					else if(vista.getCmbTipoUsuario().getSelectedItem().equals("Vendedor"))
					{
						vista.activarBindingVendedores(new ServicioVendedor().getVendedores());
						vista.getBtnNuevo().setEnabled(true);
						vista.getPnRuta().setVisible(true);
						vista.getPnZona().setBounds(0, 227, 852, 63);		
					}
					else if(vista.getCmbTipoUsuario().getSelectedItem().equals("Concesionario"))
					{
						vista.activarBindingConcesionarios(new ServicioConcesionario().getConcecionarios());
						vista.getBtnNuevo().setEnabled(true);
						vista.getPnRuta().setVisible(true);
						vista.getPnZona().setBounds(0, 227, 852, 63);
					}
					vista.quitarNuevo();
		
					
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		};
	}
	
	public void cargarRutas() throws Exception{
		
		if(vista.getCmbTipoUsuario().getSelectedItem().equals("Vendedor"))
		{	
			List<Ruta> rutas = new ServicioRuta().getRutas();// todas las rutas
			List<Ruta> rutaSalps = new ArrayList<Ruta>();//solo las del cliente salp
		
			List<Salp> clientes = new ServicioSalp().getSalps();
			
			for(Ruta ruta: rutas)
			{
				for(Salp cliente :clientes)
				{
					if(ruta.getId().equals(cliente.getRuta().getId()))
					{
						// cargo las ruta donde los cliente son solo salp
						rutaSalps.add(cliente.getRuta()); 
						break;
					}
				}
			}
			vista.setRutas(rutaSalps);
			vista.activarJComboBoxBindingRuta();
		}
		else if(vista.getCmbTipoUsuario().getSelectedItem().equals("Concesionario"))
		{
			List<Ruta> rutas = new ServicioRuta().getRutas();
			List<Ruta> rutaDomicilioComercios = new ArrayList<Ruta>();
		
			List<DomicilioComercio> clientes = new ServicioDomicilioComercio().getDomicilioComercios();
			for(Ruta ruta: rutas)
			{
				for(DomicilioComercio cliente :clientes)
				{
					if(ruta.getId().equals(cliente.getRuta().getId()))
					{
						rutaDomicilioComercios.add(cliente.getRuta());
						break;
					}
				}
			}	
			vista.setRutas(rutaDomicilioComercios);
			vista.activarJComboBoxBindingRuta();
		}
	}
	@SuppressWarnings("rawtypes")
	public ListCellRenderer rendererComboRuta(){
		return new ListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				// TODO Auto-generated method stub
				Ruta ruta = (Ruta)value;
				if(!ruta.equals(null))
				{
					JPanel pnRuta = new JPanel();
					JTextField texto = new JTextField();
					
						try {
							for(Concesionario concesionario: new ServicioConcesionario().getConcecionarios()){
								if(concesionario.getRuta().getId().equals(ruta.getId()))
								{
									texto.setBackground(Color.cyan);
									break;
								}
								else
									texto.setBackground(Color.yellow);
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
					}
						
					texto.setText(ruta.getId().toString());
					pnRuta.add(texto);
					
					pnRuta.add(new JTextField(ruta.getNombre()));
					pnRuta.add(new JTextField(ruta.getZona().getId().toString()));
					pnRuta.add(new JTextField(ruta.getZona().getDescripcion()));
				
					pnRuta.setLayout(new GridLayout(1, 0, 0, 0));
					return pnRuta;
				}
				return null;
				}
			};
	}

	public ServicioUsuario getServicioUsuario() {
		return servicioUsuario;
	}

	public void setServicioUsuario(ServicioUsuario servicioUsuario) {
		this.servicioUsuario = servicioUsuario;
	}

	public ServicioRuta getServicioRuta() {
		return servicioRuta;
	}

	public void setServicioRuta(ServicioRuta servicioRuta) {
		this.servicioRuta = servicioRuta;
	}

	public ServicioZona getServicioZona() {
		return servicioZona;
	}

	public void setServicioZona(ServicioZona servicioZona) {
		this.servicioZona = servicioZona;
	}

	public ServicioCamion getServicioCamion() {
		return servicioCamion;
	}

	public void setServicioCamion(ServicioCamion servicioCamion) {
		this.servicioCamion = servicioCamion;
	}
	
	@Override
	public Object asociar() {
		// TODO Auto-generated method stub
		return null;
	}

}
