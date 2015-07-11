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

import java.awt.Dimension;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Toolkit;


public class PrincipalUI {

	private JFrame frame;
	private JMenuBar menuBar = new JMenuBar();
	private ContPrincipal controlador;

	private 	JMenu mnCatalogo;
	private 	JMenuItem mntEmpaqueProductos;
	private 	JMenuItem mntClientes;
	private 	JMenuItem mntVendedores;
	private 	JMenuItem mntConcesionario;
	private 	JMenuItem mntRutas;
	private     JMenuItem mntPresentaciones;
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
		frame = new JFrame("Embotelladora Santa Clara");
		 Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        frame.setSize(1200,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
        frame.setVisible(true);
		frame.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 10));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		menuBar.setToolTipText("Manu");
		frame.setJMenuBar(menuBar);
		JMenu mnCatalogo = new JMenu("Catalogo");
		menuBar.add(mnCatalogo);
		
	    mntEmpaqueProductos = new JMenuItem("Empaque Productos");
		mnCatalogo.add(mntEmpaqueProductos);
		mntEmpaqueProductos.addActionListener(controlador.activarMenu());
		
		mntClientes = new JMenuItem("Clientes");
		mnCatalogo.add(mntClientes);
		mntClientes.addActionListener(controlador.activarMenu());
	    
		mntVendedores = new JMenuItem("Vendedores");
		mnCatalogo.add(mntVendedores);
		mntVendedores.addActionListener(controlador.activarMenu());
		
		mntConcesionario = new JMenuItem("Consesionarios");
		mnCatalogo.add(mntConcesionario);
		
		mntRutas = new JMenuItem("Ruta");
		mnCatalogo.add(mntRutas);
		mntRutas.addActionListener(controlador.activarMenu());
		
		mntPresentaciones = new JMenuItem("Presentaciones");
		mnCatalogo.add(mntPresentaciones);
		mntPresentaciones.addActionListener(controlador.activarMenu());
		
		mnFacturacion = new JMenu("Facturacion");
		menuBar.add(mnFacturacion);
		
		mnReportes = new JMenu("Reportes");
		menuBar.add(mnReportes);
		
		mnConsulta = new JMenu("Consulta");
		menuBar.add(mnConsulta);
		
		mnSalir = new JMenu("Salir");
		mntCerrar = new JMenuItem("cerrar sesión ");
 
	 
		mnSalir.add(mntCerrar);  
		mntCerrar.addActionListener(controlador.salirSesion());
		menuBar.add(mnSalir);

		menuBar.setVisible(false);;

		frame.setVisible(true); 
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

	public JMenu getMnCatalogo() {
		return mnCatalogo;
	}

	public void setMnCatalogo(JMenu mnCatalogo) {
		this.mnCatalogo = mnCatalogo;
	}

	public JMenuItem getMntEmpaqueProductos() {
		return mntEmpaqueProductos;
	}

	public void setMntEmpaqueProductos(JMenuItem mntProductos) {
		this.mntEmpaqueProductos = mntProductos;
	}

	public JMenuItem getMntClientes() {
		return mntClientes;
	}

	public void setMntClientes(JMenuItem mntClientes) {
		this.mntClientes = mntClientes;
	}

	public JMenuItem getMntConcesionario() {
		return mntConcesionario;
	}

	public void setMntConcesionario(JMenuItem mntConcesionario) {
		this.mntConcesionario = mntConcesionario;
	}

	public JMenu getMnFacturacion() {
		return mnFacturacion;
	}

	public void setMnFacturacion(JMenu mnFacturacion) {
		this.mnFacturacion = mnFacturacion;
	}

	public JMenu getMnReportes() {
		return mnReportes;
	}

	public void setMnReportes(JMenu mnReportes) {
		this.mnReportes = mnReportes;
	}

	public JMenu getMnConsulta() {
		return mnConsulta;
	}

	public void setMnConsulta(JMenu mnConsulta) {
		this.mnConsulta = mnConsulta;
	}

	public JMenu getMnSalir() {
		return mnSalir;
	}

	public void setMnSalir(JMenu mnSalir) {
		this.mnSalir = mnSalir;
	}

	public JMenuItem getMntCerrar() {
		return mntCerrar;
	}

	public void setMntCerrar(JMenuItem mntCerrar) {
		this.mntCerrar = mntCerrar;
	}

	public JMenuItem getMntRuta() {
		return mntRutas;
	}

	public void setMntRuta(JMenuItem mntRuta) {
		this.mntRutas = mntRuta;
	}

	public ContPrincipal getControlador() {
		return controlador;
	}

	public void setControlador(ContPrincipal controlador) {
		this.controlador = controlador;
	}

	public JMenuItem getMntRutas() {
		return mntRutas;
	}

	public void setMntRutas(JMenuItem mntRutas) {
		this.mntRutas = mntRutas;
	}

	public JMenuItem getMntPresentaciones() {
		return mntPresentaciones;
	}

	public void setMntPresentaciones(JMenuItem mntPresentaciones) {
		this.mntPresentaciones = mntPresentaciones;
	}
	
	

}
