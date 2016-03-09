/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import santaclara.Servicio.ServicioCamion;
import santaclara.Servicio.ServicioUsuario;
import santaclara.modelo.Usuario;
import santaclara.vista.UsuariosUI;

public class ContUsuarios extends ContGeneral implements IContGeneral{
	
	private ServicioUsuario servicioUsuario = new ServicioUsuario();	private ServicioCamion servicioCamion;
	private UsuariosUI vista;
	
	private ContMediador mediador = new ContMediador();

	public ContUsuarios(ContPrincipal contPrincipal) throws Exception {
		// TODO Auto-generated constructor stub
		setContPrincipal(contPrincipal);		
		servicioUsuario = new ServicioUsuario();
		servicioCamion = new ServicioCamion();
		vista = new UsuariosUI(this, servicioUsuario.getUsuarios());
		dibujar(vista,this);
	}

	@Override
	public JPanel getVista() {
		// TODO Auto-generated method stub
		return vista;
	}
	
	public UsuariosUI getVistaUsuario(){
		return vista;
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

	void MostrarTabla() throws Exception{
		vista.getBinUsuarios().unbind();
		vista.getBinUsuarios().bind();				
		vista.activarBinding(servicioUsuario.getUsuarios());
	}
	
	public ActionListener ActivarTipoUsuario() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					if(vista.getCmbTipoUsuario().getSelectedItem().equals("Todos")){
						vista.activarBinding(servicioUsuario.getUsuarios());
					}
					else if(vista.getCmbTipoUsuario().getSelectedItem().equals("JefeVenta"))
					{
						vista.activarBindingJefeVentas(servicioUsuario.getJefeVentas());
					}
					else if(vista.getCmbTipoUsuario().getSelectedItem().equals("Vendedor"))
					{
						vista.activarBindingVendedores(servicioUsuario.getVendedores());
					}
					else if(vista.getCmbTipoUsuario().getSelectedItem().equals("Concesionario")){
						vista.activarBindingConcesionarios(servicioUsuario.getConcesionarios());
					}
				} catch (Exception e)
				{
					e.printStackTrace();
					JOptionPane.showConfirmDialog(null,e.getMessage());
				}
			}
		};
	}

	public ServicioUsuario getServicioUsuario() {
		return servicioUsuario;
	}

	public void setServicioUsuario(ServicioUsuario servicioUsuario) {
		this.servicioUsuario = servicioUsuario;
	}


	public ServicioCamion getServicioCamion() {
		return servicioCamion;
	}

	public void setServicioCamion(ServicioCamion servicioCamion) {
		this.servicioCamion = servicioCamion;
	}

}
