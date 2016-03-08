/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.controlador;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import santaclara.Servicio.ServicioIniciarSesion;
import santaclara.dbPostgresql.controlador.ContAjusteBaseDatoSql;
import santaclara.modelo.Usuario;
import santaclara.thread.animacion.Animado;
import santaclara.vista.IniciarSesionUI;

public class ContIniciarSesion extends ContGeneral implements IContGeneral     {
	
	private IniciarSesionUI 		vista;
    private ServicioIniciarSesion   servicio= new ServicioIniciarSesion();
    private Animado animado;
    String inicio = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date());
	public ActionListener iniciarsesion( )
	{	
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {	
					
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
						Usuario usuario = servicio.iniciarSesion(username, contrasena);
						if(usuario == null)
						{
						   JOptionPane.showMessageDialog(vista.getTxtUsuario(),"usuario no existe ","error Iniciar Sesion", JOptionPane.INFORMATION_MESSAGE);
						}
						else
						{
							getContPrincipal().dibujarMenu();
							
							animado.detener();//finaliza la amimacion de usuario
							
							/******animacion principal  **/
							
							 getContPrincipal().activarAnimacionSantaclara(vista);
							 getContPrincipal().getContGeneral().setUsuario(usuario);
						}
					} 
										
					} catch (Exception e1) {
					// TODO Auto-generated catch block
						e1.printStackTrace();
						JOptionPane.showMessageDialog(new JPanel(),
								"Problema para establecer la conexion con la base de dato");
						try {
							new ContAjusteBaseDatoSql(getContPrincipal());
						} catch (Exception e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
					}
				}
		};
	}
	
	public ContIniciarSesion(ContPrincipal contPrincipal){
		super();
		setContPrincipal(contPrincipal);
		vista = new IniciarSesionUI(this);
		dibujar(vista,ContIniciarSesion.this);
		
		JLabel lblImagen = new JLabel();
		lblImagen.setBounds(100, 200, 150, 150);
		animado = new Animado("usuario",lblImagen,500);
		animado.star();
		dibujarImagen(vista.getPnlInisioSecion(),lblImagen,BorderLayout.EAST);
		
	}

	public ContIniciarSesion(){
	
		vista = new IniciarSesionUI();
		dibujar(vista,this);
		
	}

	
	@Override
	public JPanel getVista() {
		// TODO Auto-generated method stub
		return vista;
	}

	@Override
	public String asociar() {
		// TODO Auto-generated method stub
		return inicio;
	}

}
