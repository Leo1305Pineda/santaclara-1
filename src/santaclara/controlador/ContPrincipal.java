package santaclara.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.JPanel;

import santaclara.modelo.Usuario;
import santaclara.vista.PrincipalUI;

public  class ContPrincipal implements IContGeneral {
	
	private PrincipalUI  vista ;
	private IContGeneral controlador;
	private Usuario		 usuario;
	private Stack<String> cache = new Stack<String>();
	
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
	
	void quitarPanel(){
		vista.getFrame().getContentPane().removeAll();
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
						ActivarProductos();
					}
				else if(e.getSource().equals(vista.getMntVendedores()))
				{
					ActivarVendedores();
				}
				else if(e.getSource().equals(vista.getMntClientes()))
				{
					ActivarClientes();
				}
				else if(e.getSource().equals(vista.getMntRuta()))
				{
					ActivarRutas();
				}
				else if(e.getSource().equals(vista.getMntPresentaciones()))
				{
					ActivarPresentaciones();
				}
			}
		};
	}
	public void ActivarRutas(){
		// TODO Auto-generated method stub
		try {
			controlador = new ContRutas(ContPrincipal.this);
		}
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void ActivarClientes() {
		// TODO Auto-generated method stub
		try {
			controlador = new ContClientes(ContPrincipal.this);
		}
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void ActivarVendedores() {
		// TODO Auto-generated method stub
		try {
			controlador = new ContVendedores(ContPrincipal.this);
		}
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void setVista(PrincipalUI vista) {
		this.vista = vista;
	}

	public void ActivarProductos() {
		// TODO Auto-generated method stub
		try {
			controlador = new ContProductos(ContPrincipal.this);
		}
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		

	}
	
	public void ActivarPresentaciones() {
		// TODO Auto-generated method stub
		try {
			controlador = new ContPresentaciones(ContPrincipal.this);
		}
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	
	public void ActivarCapacidades() {
		// TODO Auto-generated method stub
		try {
			controlador = new ContCapacidades(ContPrincipal.this);
		}
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void ActivarSabores() {
		// TODO Auto-generated method stub
		try {
			controlador = new ContSabores(ContPrincipal.this);
		}
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void ActivarAtras(){
		// TODO Auto-generated method stub
		if (!cache.empty())
		{
			cache.pop();
			switch (cache.pop()) {
			case "santaclara.IniciarSesionUI": cache.push("santaclara.IniciarSesionUI");
			break;
			case "santaclara.vista.ProductosUI": 		ActivarProductos();
				break;
			case "santaclara.vista.PresentacionesUI": 	ActivarPresentaciones();
			break;
			case "santaclara.vista.VendedoresUI":		ActivarVendedores();
			break;
			case "santaclara.vista.ClientesUI":			ActivarClientes();
			break;
			case "santaclara.vista.RutaUI":				ActivarRutas();
			break;
			case "santaclara.vista.CapacidadesUI":		ActivarCapacidades();
			break;
			case "santaclara.vista.SaboresUI":		ActivarSabores();
			break;

			default:
				break;
			}
		}
	}
	
	public Stack<String> getCache() {
		return cache;
	}

	public void setCache(Stack<String> cache) {
		this.cache = cache;
	}

}





