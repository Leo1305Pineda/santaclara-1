package santaclara.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import santaclara.Servicio.ServicioIniciarSesion;
import santaclara.modelo.Usuario;
import santaclara.vista.IniciarSesionUI;

public class ContIniciarSesion extends ContGeneral implements IContGeneral     {
	
	
	private IniciarSesionUI 		vista;
    private ServicioIniciarSesion   servicio= new ServicioIniciarSesion();
	
	public ActionListener iniciarsesion( )
	{	
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				String username = vista.getTxtUsuario().getText();
				String contrasena = vista.getPassword().getText();

				if(username.trim().length()==  0)
				{
				   JOptionPane.showMessageDialog(vista.getTxtUsuario(),"error Iniciar Sesion","Usuario no puede ser vacio", JOptionPane.INFORMATION_MESSAGE);				
				}
				else if(contrasena.trim().length()==  0)
				{
				   JOptionPane.showMessageDialog(vista.getTxtUsuario(),"contraseña no puede ser vacio","error Iniciar Sesion", JOptionPane.INFORMATION_MESSAGE);				

				}
				else
				{
					try {
						Usuario usuario = servicio.iniciarSesion(username, contrasena);
						if(usuario == null)
						{
						   JOptionPane.showMessageDialog(vista.getTxtUsuario(),"usuario no existe","error Iniciar Sesion", JOptionPane.INFORMATION_MESSAGE);				
						}
						else
						{
							getContPrincipal().setUsuario(usuario);
							getContPrincipal().dibujarMenu();
						}
					} 
					catch (Exception e1) {
						// TODO: handle exception
						e1.printStackTrace();
					   JOptionPane.showMessageDialog(null,e1.getMessage(),"error",JOptionPane.ERROR_MESSAGE);				
					}
				}
			}
		};
	}


	public ContIniciarSesion(ContPrincipal contPrincipal) {
		super();
		setContPrincipal(contPrincipal);
		vista = new IniciarSesionUI(this);
		dibujar(vista,ContIniciarSesion.this);
	}


	@Override
	public JPanel getVista() {
		// TODO Auto-generated method stub
		return vista;
	}
	

}
