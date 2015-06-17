package santaclara.vista;

import java.awt.EventQueue; 

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem; 
 

import javax.swing.JMenuBar;

import santaclara.controlador.ContPrincipal;
import santaclara.modelo.Concesionario;
import santaclara.modelo.JefeVenta;
import santaclara.modelo.Usuario;
import santaclara.modelo.Vendedor;


public class PrincipalUI {

	private JFrame frame;
	private JMenuBar menuBar = new JMenuBar();
	private ContPrincipal controlador;

	private 	JMenu mnCatalogo;
	private 	JMenuItem mntProductos;
	private 	JMenuItem mntClientes;
	private 	JMenuItem mntVendedores;
	private 	JMenuItem mntConcesionario;
	private 	JMenu mnFacturacion;
	private 	JMenu mnReportes;
	private 	JMenu mnConsulta;
	private 	JMenu mnSalir;
	private 	JMenuItem mntCerrar;

	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalUI window = new PrincipalUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param controlador 
	 */
	public PrincipalUI(ContPrincipal controlador) {
		this.controlador = controlador;
		initialize();
	}
	
	
	public PrincipalUI( ) {
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		menuBar.setToolTipText("Manu");
		menuBar.setBounds(12, 0, 492, 21);
		frame.setJMenuBar(menuBar);
		JMenu mnCatalogo = new JMenu("Catalogo");
		menuBar.add(mnCatalogo);
		
		JMenuItem mntProductos = new JMenuItem("Productos ");
		mnCatalogo.add(mntProductos);
		
		JMenuItem mntClientes = new JMenuItem("Clientes");
		mnCatalogo.add(mntClientes);
		
	    mntVendedores = new JMenuItem("Vendedores");
		mnCatalogo.add(mntVendedores);
		mntVendedores.addActionListener(controlador.activarMenu());
		
		
		JMenuItem mntConcesionario = new JMenuItem("Consesionarios");
		mnCatalogo.add(mntConcesionario);
		
		JMenu mnFacturacion = new JMenu("Facturacion");
		menuBar.add(mnFacturacion);
		
		JMenu mnReportes = new JMenu("Reportes");
		menuBar.add(mnReportes);
		
		JMenu mnConsulta = new JMenu("Consulta");
		menuBar.add(mnConsulta);
		
		JMenu mnSalir = new JMenu("Salir");
		JMenuItem mntCerrar = new JMenuItem("cerrar sesión ");
 
	 
		mnSalir.add(mntCerrar);  
		mntCerrar.addActionListener(controlador.salirSesion());
		menuBar.add(mnSalir);
		menuBar.setVisible(false);;

		
	}
	
	public void dibujarMenu(Usuario usuario )
	{ 
		menuBar.setVisible(true);
		
		if(usuario.getClass().equals(JefeVenta.class))
		{
			
			
		}
		if(usuario.getClass().equals(Vendedor.class))
		{
			
			
		}
		if(usuario.getClass().equals(Concesionario.class))
		{
			
			
		}

	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
 

	public JMenuBar getMenuBar() {
		return menuBar;
	}

	public void setMenuBar(JMenuBar menuBar) {
		this.menuBar = menuBar;
	}

	public JMenuItem getMntVendedores() {
		return mntVendedores;
	}

	public void setMntVendedores(JMenuItem mntVendedores) {
		this.mntVendedores = mntVendedores;
	}

	
	
	
}
