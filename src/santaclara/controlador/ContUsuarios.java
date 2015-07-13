package santaclara.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import santaclara.Servicio.ServicioConcesionarioRuta;
import santaclara.Servicio.ServicioUsuario;
import santaclara.modelo.ConcesionarioRuta;
import santaclara.modelo.Usuario;
import santaclara.vista.UsuariosUI;

public class ContUsuarios extends ContGeneral implements IContGeneral{
	
	private ServicioUsuario servicioUsuario;
	private UsuariosUI vista;
	
	public ContUsuarios(ContPrincipal contPrincipal) throws Exception {
		// TODO Auto-generated constructor stub
		setContPrincipal(contPrincipal);
		servicioUsuario = new ServicioUsuario();
		vista = new UsuariosUI(this, servicioUsuario.getUsuarios());
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
				vista.activarNuevoCamion();
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
					Usuario usuario  = new Usuario();
					try {
						usuario = servicioUsuario.buscar(new Integer(vista.getTable().getValueAt(vista.getTable().getSelectedRow(),0).toString().trim()));
					} catch (NumberFormatException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (usuario != null)
					{
						vista.activarNuevoCamion();
						vista.getScrollPanel().setVisible(false);
						
						vista.getTxtId().setText(usuario.getId().toString());
						vista.getTxtNombre().setText(usuario.getNombre());
						vista.getTxtCedula().setText(usuario.getCedula());
						vista.getTxtContrasena().setText(usuario.getContrasena());
						vista.getTxtUserName().setText(usuario.getUsername());
						vista.getTxtReContrasena().setText(usuario.getContrasena());//Temporal 
						
					}
				}
				else
				{
					JOptionPane.showMessageDialog(vista,"Seleccione el Producto");
				}
		}
		};
	}
	public String ValidarTxt(){
		if (vista.getTxtCedula().getText().equals("")) return "Campos Vacios:Cedula";
		if (vista.getTxtNombre().getText().equals("")) return "Campos Vacios:Nombre";
		if (vista.getTxtUserName().getText().equals("")) return "Campos Vacios:UserName";
		if (vista.getTxtContrasena().getText().equals("")) return "Campos Vacios:Contraseña";
		if (vista.getTxtReContrasena().getText().equals(vista.getTxtContrasena())) return "Campos Vacios:Contraseña invalida";
		
		return "";
	}

	public ActionListener guardar() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Usuario usuarios = new Usuario();
				
				if (vista.getTxtId().getText().equals("")) usuarios.setId(null);
					else usuarios.setId(new Integer(vista.getTxtId().getText().toString())); 
				
				if (ValidarTxt()=="")
				{
							try {
									usuarios.setCedula(vista.getTxtCedula().getText().toString());
									usuarios.setNombre(vista.getTxtNombre().getText().toString());
									usuarios.setUsername(vista.getTxtUserName().getText().toString());
									usuarios.setContrasena(vista.getTxtContrasena().getText().toString());
								
									
									JOptionPane.showMessageDialog(vista,servicioUsuario.guardar(usuarios));
									// agregarlo a la lista
									vista.getCamiones().add(usuarios);
									
									vista.getBinUsuarios().unbind();
									vista.getBinUsuarios().bind();
									vista.activarBinding(servicioUsuario.getUsuarios());
									vista.quitarNuevo();
									vista.getScrollPanel().setVisible(true);
									
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										JOptionPane.showConfirmDialog(null,e1.getMessage());
										e1.printStackTrace();
									}
				}
				else JOptionPane.showMessageDialog(vista,ValidarTxt());
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
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
						Usuario usuario;
						usuario = servicioUsuario.getUsuario(new Integer(vista.getTable().getValueAt(vista.getTable().getSelectedRow(),0).toString()));
						
						ServicioConcesionarioRuta servicioConcesionarioRuta = new ServicioConcesionarioRuta();
						
						List<ConcesionarioRuta> concesionarioRutas = servicioConcesionarioRuta.getConcecionarioRutas();
						Boolean enc = new Boolean(false);
						for(ConcesionarioRuta concesionarioRuta: concesionarioRutas)
						{
							if(concesionarioRuta.getConcesionario().getId().equals(usuario.getId()))
								{
									enc=true;
									break;
								}
						}
						if(enc==false)
						{
							servicioUsuario.eliminar(usuario);
						
							vista.getBinUsuarios().unbind();
							vista.getBinUsuarios().bind();				
							vista.activarBinding(servicioUsuario.getUsuarios());
							JOptionPane.showMessageDialog(vista,"Operacion Exitosa ");
							vista.quitarNuevo();
						}
						else JOptionPane.showMessageDialog(vista,"Operacion Fallida\n"+
								" Objeto Existente en otra Clase? \n Elimine la relacion Exixtente en: Vendedor Concesionario");
						
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
	}
}
