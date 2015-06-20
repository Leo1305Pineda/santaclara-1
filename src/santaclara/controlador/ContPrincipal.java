package santaclara.controlador;


import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JPanel;

import santaclara.modelo.Usuario;
import santaclara.vista.PrincipalUI;

public  class ContPrincipal implements IContGeneral {
	
	private PrincipalUI  vista ;
	private IContGeneral controlador;
	private Usuario		 usuario;
	
	
	public static void main(String[] args) {
	   ContPrincipal controlador = new  ContPrincipal();
	   controlador.ejecutar();   
	   	   
	}

	private void ejecutar() {
		// TODO Auto-generated method stub
		vista = new PrincipalUI(this);
		// iniciar session
	//	
		try {
			setControlador(new ContIniciarSesion(this));
			//setControlador(new ContProductos(ContPrincipal.this));
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	void agregarPanel(JPanel panel)
	{
		vista.getFrame().getContentPane().removeAll();
		vista.getFrame().getContentPane().add(panel);
		vista.getFrame().repaint();
	
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	public void dibujarMenu()
	{
		//dado el usuario 
		// limpiar contenedor 
		vista.getFrame().getContentPane().removeAll();
		vista.dibujarMenu(usuario);
		
	}

	public IContGeneral getControlador() {
		return controlador;
	}

	public void setControlador(IContGeneral controlador) {
		this.controlador = controlador;
	}

	@Override
	public JPanel getVista() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/************ Salir Session *************/
	public ActionListener salirSesion() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				vista.getMenuBar().setVisible(false);
				usuario = null;
				controlador = new ContIniciarSesion(ContPrincipal.this); 
				
			}
		};
	}

	public ActionListener activarMenu() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource().equals(vista.getMntProductos()))
				{
					try {
						controlador = new ContProductos(ContPrincipal.this);
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}				
				}
			}
		};
	}
	
	///Eventos para l 
  
}





