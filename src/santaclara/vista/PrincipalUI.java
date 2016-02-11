package santaclara.vista;

import java.awt.EventQueue; 

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem; 
 
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import santaclara.controlador.ContPrincipal;
import santaclara.modelo.Usuario;
import santaclara.vista.herramientas.VistaGenericaUI; 


public class PrincipalUI {

	private JFrame frame;
	private JMenuBar menuBar = new JMenuBar();
	private ContPrincipal controlador;

	private 	JMenu mnCatalogo;
	private 	JMenu mnDetalleFacturacion;
	private 	JMenu mnDomicilioComerciales;
	private 	JMenu mnFacturacion;
	private 	JMenu mnReportes;
	private 	JMenu mnConsulta;
	private 	JMenu mnSalir;
	private 	JMenuItem mntCerrar;
	
	private     JMenuItem mntAlmacen;
	private     JMenuItem mntCamiones;
	private     JMenuItem mntCapacidades;
	private 	JMenuItem mntClientes;
	private 	JMenuItem mntConcesionarios;
	private 	JMenuItem mntConcesionarioRutas;
	private 	JMenuItem mntEmpaqueProductos;
	private 	JMenuItem mntJefeVenta;
	private 	JMenuItem mntPresentaciones;
	private 	JMenuItem mntProductos;
	private 	JMenuItem mntProductoAlmacenes;
	private 	JMenuItem mntRutas;
	private 	JMenuItem mntSabores;
	private 	JMenuItem mntUsuarios;
	private 	JMenuItem mntVendedores;
	private 	JMenuItem mntVisitas;
	private 	JMenuItem mntZonas;
	private 	JMenuItem mntCalendarios;
	private     JMenuItem mntPedidos;
	private     JMenuItem mntReportMontFacturaAlmacen;

	private     JMenuItem mntReportMontFacturaVendedor;
	private     JMenuItem mntConsultaDetalleFacturaMesAlmacen;
	private 	JMenuItem mntListCantRefrescoSaborVendidoAlmacen;
	private 	JMenuItem mntListCantRefrescoPresentCapacFacturadoZona;
	private		JMenuItem mntListClienteZonaTipo;
	private		JMenuItem mntMontoFacturadoMesZonaTipoPago;
 
	
	
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
		frame.setBounds(VistaGenericaUI.getMargenX(),VistaGenericaUI.getMargenY(),VistaGenericaUI.getWidthPantalla(),VistaGenericaUI.getHeightPantalla());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		  
		JPanel panel = new JPanel();
		frame.setContentPane(panel);
		frame.setVisible(true);
  
		menuBar.setToolTipText("Menu");
		frame.setJMenuBar(menuBar);
		
		mnCatalogo = new JMenu("Catalogo");
		menuBar.add(mnCatalogo);
		
		mnFacturacion = new JMenu("Facturacion");
		menuBar.add(mnFacturacion);

		mnReportes = new JMenu("Reportes");
		menuBar.add(mnReportes);
		
		mnConsulta = new JMenu("Consulta");
		menuBar.add(mnConsulta);

		mntCerrar = new JMenuItem("cerrar sesión ");
		System.out.println(controlador);
		mntCerrar.addActionListener(controlador.salirSesion());

		mnSalir = new JMenu("Salir");
		mnSalir.add(mntCerrar);  
		
		menuBar.add(mnSalir);

		/***************************** Botones para catalogo **********************************/
		
		mntAlmacen = new JMenuItem("Almacenes");
		mntAlmacen.addActionListener(controlador.activarMenu());
		mnCatalogo.add(mntAlmacen);
		mnCatalogo.add(new JSeparator());


		mntAlmacen = new JMenuItem("Almacenes");
		mntAlmacen.addActionListener(controlador.activarMenu());
		mnCatalogo.add(mntAlmacen);
		mnCatalogo.add(new JSeparator());

	
		mntCalendarios = new JMenuItem("Calendario");
		mnCatalogo.add(mntCalendarios);
		mntCalendarios.addActionListener(controlador.activarMenu());

		
		mntConcesionarios = new JMenuItem("Concesionarios");
		mnCatalogo.add(mntConcesionarios);
		mntConcesionarios.addActionListener(controlador.activarMenu());

		/*
		mntConcesionarioRutas = new JMenuItem("ConcesionarioRutas");
		mnCatalogo.add(mntConcesionarioRutas);
		mntConcesionarioRutas.addActionListener(controlador.activarMenu());
		*/
		
	    
		/*
		mntJefeVenta = new JMenuItem("Jefe Ventas");
		mnCatalogo.add(mntJefeVenta);
		mntJefeVenta.addActionListener(controlador.activarMenu());
		*/ 
		
		/***************************** Botones para Reporte **********************************/
		
		mntReportMontFacturaAlmacen = new JMenuItem("Monto Total Facturado por Almacenes");
		mnReportes.add(mntReportMontFacturaAlmacen);
		mntReportMontFacturaAlmacen.addActionListener(controlador.activarMenu());
		
		mntReportMontFacturaVendedor = new JMenuItem("Monto Total Facturado por Vendedores");
		mnReportes.add(mntReportMontFacturaVendedor);
		mntReportMontFacturaVendedor.addActionListener(controlador.activarMenu());
		
		/***************************** Botones para Consultar **********************************/
		
		mntConsultaDetalleFacturaMesAlmacen = new JMenuItem("Detalle Facturado mensualmente por Almacen");
		mnConsulta.add(mntConsultaDetalleFacturaMesAlmacen);
		mntConsultaDetalleFacturaMesAlmacen.addActionListener(controlador.activarMenu());

		mntListCantRefrescoSaborVendidoAlmacen = new JMenuItem("Listado Cantidad de refresco por Sabor Vendido en Cada Almacen");
		mnConsulta.add(mntListCantRefrescoSaborVendidoAlmacen);
		mntListCantRefrescoSaborVendidoAlmacen.addActionListener(controlador.activarMenu());

		mntListCantRefrescoPresentCapacFacturadoZona = new JMenuItem("Listado Cantidad de Refresco por Presentacion y Capacidad Facturado por Zona");
		mnConsulta.add(mntListCantRefrescoPresentCapacFacturadoZona);
		mntListCantRefrescoPresentCapacFacturadoZona.addActionListener(controlador.activarMenu());

		mntListClienteZonaTipo = new JMenuItem("Listado Detalle de Cliente por Zona y por Tipo");
		mnConsulta.add(mntListClienteZonaTipo);
		mntListClienteZonaTipo.addActionListener(controlador.activarMenu());
		
		mntMontoFacturadoMesZonaTipoPago = new JMenuItem("Monto total Facturado Mensualmente por almacen y por Tipo de Pago");
		mnConsulta.add(mntMontoFacturadoMesZonaTipoPago);
		mntMontoFacturadoMesZonaTipoPago.addActionListener(controlador.activarMenu());
		
		mntProductos = new JMenuItem("Productos");
		mntProductos.addActionListener(controlador.activarMenu());
		mnCatalogo.add(mntProductos);


		mntSabores = new JMenuItem("Sabores");
		mntSabores.addActionListener(controlador.activarMenu());
		mnCatalogo.add(mntSabores);
		
		mntEmpaqueProductos = new JMenuItem("Empaque Productos");
		mntEmpaqueProductos.addActionListener(controlador.activarMenu());
		mnCatalogo.add(mntEmpaqueProductos);

		mntPresentaciones = new JMenuItem("Presentaciones");
		mntPresentaciones.addActionListener(controlador.activarMenu());
		mnCatalogo.add(mntPresentaciones);
 
		mntCapacidades = new JMenuItem("Capacidades");
		mnCatalogo.add(mntCapacidades);
		mntCapacidades.addActionListener(controlador.activarMenu());

		/*
		mntProductoAlmacenes = new JMenuItem("Producto por Almacenes");
		mnCatalogo.add(mntProductoAlmacenes);
		mntProductoAlmacenes.addActionListener(controlador.activarMenu());
		*/
		mnCatalogo.add(new JSeparator());

		mntRutas = new JMenuItem("Ruta");
		mntRutas.addActionListener(controlador.activarMenu());
		mnCatalogo.add(mntRutas);
		
		mntZonas = new JMenuItem("Zonas");
		mntZonas.addActionListener(controlador.activarMenu());
		mnCatalogo.add(mntZonas);

		mntClientes = new JMenuItem("Clientes ");
		mntClientes.addActionListener(controlador.activarMenu());
		mnCatalogo.add(mntClientes);
		
		mnCatalogo.add(new JSeparator());

		mntUsuarios = new JMenuItem("Usuarios ('Jefes de Ventas','Vendedores','Concecionaros')");
		mnCatalogo.add(mntUsuarios);
		mntUsuarios.addActionListener(controlador.activarMenu());

		mntCamiones = new JMenuItem("Camiones");
		mnCatalogo.add(mntCamiones);
		mntCamiones.addActionListener(controlador.activarMenu());


		/*
		mntVendedores = new JMenuItem("Vendedores");
		mnCatalogo.add(mntVendedores);
		mntVendedores.addActionListener(controlador.activarMenu());
		
		mntVisitas = new JMenuItem("Visitas");
		mnCatalogo.add(mntVisitas);
		mntVisitas.addActionListener(controlador.activarMenu());
		*/
		
		mntPedidos = new JMenuItem("Pedidos");
		mntPedidos.addActionListener(controlador.activarMenu());
		mnFacturacion.add(mntPedidos);
		/*
		mntConcesionarios = new JMenuItem("Consesionarios");
		mnCatalogo.add(mntConcesionarios);
		*/ 
		/****************** btn cerrar session  ******/
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
		return mntConcesionarios;
	}

	public void setMntConcesionario(JMenuItem mntConcesionario) {
		this.mntConcesionarios = mntConcesionario;
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

	public JMenuItem getMntAlmacen() {
		return mntAlmacen;
	}

	public void setMntAlmacen(JMenuItem mntAlmacen) {
		this.mntAlmacen = mntAlmacen;
	}

	public JMenuItem getMntCamiones() {
		return mntCamiones;
	}

	public void setMntCamiones(JMenuItem mntCamiones) {
		this.mntCamiones = mntCamiones;
	}

	public JMenuItem getMntCapacidades() {
		return mntCapacidades;
	}

	public void setMntCapacidades(JMenuItem mntCapacidades) {
		this.mntCapacidades = mntCapacidades;
	}

	public JMenuItem getMntConcesionarios() {
		return mntConcesionarios;
	}

	public void setMntConcesionarios(JMenuItem mntConcesionarios) {
		this.mntConcesionarios = mntConcesionarios;
	}

	public JMenuItem getMntConcesionarioRutas() {
		return mntConcesionarioRutas;
	}

	public void setMntConcesionarioRutas(JMenuItem mntConcesionarioRutas) {
		this.mntConcesionarioRutas = mntConcesionarioRutas;
	}

	public JMenu getMnDetalleFacturacion() {
		return mnDetalleFacturacion;
	}

	public void setMnDetalleFacturacion(JMenu mnDetalleFacturacion) {
		this.mnDetalleFacturacion = mnDetalleFacturacion;
	}

	public JMenu getMnDomicilioComerciales() {
		return mnDomicilioComerciales;
	}

	public void setMnDomicilioComerciales(JMenu mnDomicilioComerciales) {
		this.mnDomicilioComerciales = mnDomicilioComerciales;
	}

	public JMenuItem getMntJefeVenta() {
		return mntJefeVenta;
	}

	public void setMntJefeVenta(JMenuItem mntJefeVenta) {
		this.mntJefeVenta = mntJefeVenta;
	}

	public JMenuItem getMntProductos() {
		return mntProductos;
	}

	public void setMntProductos(JMenuItem mntProductos) {
		this.mntProductos = mntProductos;
	}

	public JMenuItem getMntProductoAlmacenes() {
		return mntProductoAlmacenes;
	}

	public void setMntProductoAlmacenes(JMenuItem mntProductoAlmacenes) {
		this.mntProductoAlmacenes = mntProductoAlmacenes;
	}

	public JMenuItem getMntSabores() {
		return mntSabores;
	}

	public void setMntSabores(JMenuItem mntSabores) {
		this.mntSabores = mntSabores;
	}

 
	public JMenuItem getMntUsuarios() {
		return mntUsuarios;
	}

	public void setMntUsuarios(JMenuItem mntUsuarios) {
		this.mntUsuarios = mntUsuarios;
	}

	public JMenuItem getMntVisitas() {
		return mntVisitas;
	}

	public void setMntVisitas(JMenuItem mntVisitas) {
		this.mntVisitas = mntVisitas;
	}

	public JMenuItem getMntZonas() {
		return mntZonas;
	}

	public void setMntZonas(JMenuItem mntZonas) {
		this.mntZonas = mntZonas;
	}

	public JMenuItem getMntCalendarios() {
		return mntCalendarios;
	}

	public void setMntCalendarios(JMenuItem mntCalendarios) {
		this.mntCalendarios = mntCalendarios;
	}

	public JMenuItem getMntPedidos() {
		return mntPedidos;
	}

	public void setMntPedidos(JMenuItem mntPedido) {
		this.mntPedidos = mntPedido;
	}

	public JMenuItem getMntReportMontFacturaAlmacen() {
		return mntReportMontFacturaAlmacen;
	}

	public void setMntReportMontFacturaAlmacen(JMenuItem mntReportMontFacturaAlmacen) {
		this.mntReportMontFacturaAlmacen = mntReportMontFacturaAlmacen;
	}
 
	public JMenuItem getMntReportMontFacturaVendedor() {
		return mntReportMontFacturaVendedor;
	}

	public void setMntReportMontFacturaVendedor(
			JMenuItem mntReportMontFacturaVendedor) {
		this.mntReportMontFacturaVendedor = mntReportMontFacturaVendedor;
	}

	public JMenuItem getMntConsultaDetalleFacturaMesAlmacen() {
		return mntConsultaDetalleFacturaMesAlmacen;
	}

	public void setMntConsultaDetalleFacturaMesAlmacen(
			JMenuItem mntConsultaDetalleFacturaMesAlmacen) {
		this.mntConsultaDetalleFacturaMesAlmacen = mntConsultaDetalleFacturaMesAlmacen;
	}

	public JMenuItem getMntListCantRefrescoSaborVendidoAlmacen() {
		return mntListCantRefrescoSaborVendidoAlmacen;
	}

	public void setMntListCantRefrescoSaborVendidoAlmacen(
			JMenuItem mntListCantRefrescoSaborVendidoAlmacen) {
		this.mntListCantRefrescoSaborVendidoAlmacen = mntListCantRefrescoSaborVendidoAlmacen;
	}

	public JMenuItem getMntListCantRefrescoPresentCapacFacturadoZona() {
		return mntListCantRefrescoPresentCapacFacturadoZona;
	}

	public void setMntListCantRefrescoPresentCapacFacturadoZona(
			JMenuItem mntListCantRefrescoPresentCapacFacturadoZona) {
		this.mntListCantRefrescoPresentCapacFacturadoZona = mntListCantRefrescoPresentCapacFacturadoZona;
	}

	public JMenuItem getMntListClienteZonaTipo() {
		return mntListClienteZonaTipo;
	}

	public void setMntListClienteZonaTipo(JMenuItem mntListClienteZonaTipo) {
		this.mntListClienteZonaTipo = mntListClienteZonaTipo;
	}

	public JMenuItem getMntMontoFacturadoMesZonaTipoPago() {
		return mntMontoFacturadoMesZonaTipoPago;
	}

	public void setMntMontoFacturadoMesZonaTipoPago(
			JMenuItem mntMontoFacturadoMesZonaTipoPago) {
		this.mntMontoFacturadoMesZonaTipoPago = mntMontoFacturadoMesZonaTipoPago;
	} 
	
	
}
