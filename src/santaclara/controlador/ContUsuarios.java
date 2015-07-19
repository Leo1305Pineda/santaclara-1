package santaclara.controlador;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import santaclara.Servicio.ServicioCamion;
import santaclara.Servicio.ServicioRuta;
import santaclara.Servicio.ServicioUsuario;
import santaclara.Servicio.ServicioZona;
import santaclara.modelo.Usuario;

import santaclara.vista.UsuariosUI;

public class ContUsuarios extends ContGeneral implements IContGeneral{
	
	private ServicioUsuario servicioUsuario = new ServicioUsuario();
	private ServicioRuta servicioRuta = new ServicioRuta();
	private ServicioZona servicioZona = new ServicioZona();
	private ServicioCamion servicioCamion;
	private UsuariosUI vista ;
	
	public ContUsuarios(ContPrincipal contPrincipal) throws Exception {
		// TODO Auto-generated constructor stub
		setContPrincipal(contPrincipal);
		
		servicioUsuario = new ServicioUsuario();
		servicioRuta = new ServicioRuta();
		servicioZona = new ServicioZona();
		servicioCamion = new ServicioCamion();
		
		vista = new UsuariosUI(this, servicioUsuario.getUsuarios(),
						servicioRuta.getRutas(),servicioZona.getZonas(),
						servicioCamion.getCamiones());
		vista.activarBinding(servicioUsuario.getUsuarios());
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
					vista.activarNuevo();
					if (vista.getTable().getSelectedRow()>=0)
					{
						if(vista.getCmbTipoUsuario().getSelectedItem().equals("JefeVenta"))
						{
							
							new ContJefeVentas(vista).Modificar();;
								
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
						usuario = servicioUsuario.buscar(new Integer(vista.getTable().getValueAt(vista.getTable().getSelectedRow(),0).toString().trim()));
				
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
						} catch (NumberFormatException | IOException e1) {
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
						
						new ContJefeVentas(vista).Guardar();
							
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
				}
			}

			private void GuardarUsuario() throws HeadlessException, IOException {
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
								
						JOptionPane.showMessageDialog(vista,servicioUsuario.guardar(usuario));
						vista.getBinUsuarios().unbind();
						vista.getBinUsuarios().bind();
						vista.activarBinding(servicioUsuario.getUsuarios());
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
		// TODO Auto-generated method stubif (vista.getTable().getSelectedRow()>=0)
		
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (vista.getTable().getSelectedRow()>=0)
				{
				try {
						if(vista.getCmbTipoUsuario().getSelectedItem().equals("JefeVenta"))
						{
						
							new ContJefeVentas(vista).Eliminar();
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
												
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				else JOptionPane.showMessageDialog(vista,"Seleccione la fila");
			}
		};
	}
	
	void MostrarTabla() throws NumberFormatException, IOException{
		vista.getBinUsuarios().unbind();
		vista.getBinUsuarios().bind();				
		vista.activarBinding(servicioUsuario.getUsuarios());
		vista.quitarNuevo();
	}
	
	public ActionListener ActivarTipoUsuario() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(vista.getCmbTipoUsuario().getSelectedItem().equals("Todos")){
					try {
						vista.activarBinding(servicioUsuario.getUsuarios());
						vista.getBtnNuevo().setEnabled(false);
					} catch (NumberFormatException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(vista.getCmbTipoUsuario().getSelectedItem().equals("JefeVenta")){
					try {
						vista.activarBindingJefeVentas(servicioUsuario.getJefeVentas());
						vista.getBtnNuevo().setEnabled(true);
						vista.getPnRuta().setVisible(false);
						vista.getPnZona().setBounds(0, 90, 852, 63);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(vista.getCmbTipoUsuario().getSelectedItem().equals("Vendedor")){
					try {
						vista.activarBindingVendedores(servicioUsuario.getVendedores());
						vista.getBtnNuevo().setEnabled(true);
						vista.getPnRuta().setVisible(true);
						vista.getPnZona().setBounds(0, 227, 852, 63);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(vista.getCmbTipoUsuario().getSelectedItem().equals("Concesionario")){
					try {
						vista.activarBindingConcesionarios(servicioUsuario.getConcesionarios());
						vista.getBtnNuevo().setEnabled(true);
						vista.getPnRuta().setVisible(true);
						vista.getPnZona().setBounds(0, 227, 852, 63);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				vista.quitarNuevo();
			}
		};
	}
}
