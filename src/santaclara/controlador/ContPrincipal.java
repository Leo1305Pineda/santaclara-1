package santaclara.controlador;
 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.JPanel;

import santaclara.controlador.consultas.ContDetalleFacturaMesAlmacen;
import santaclara.controlador.consultas.ContListCantRefrecoPresentCapacFacturadoZona;
import santaclara.controlador.consultas.ContListCantRefrescoSaborVendidoAlmacen;
import santaclara.controlador.consultas.ContListClienteTipoZona;
import santaclara.controlador.consultas.ContMontoFacturadoMesZonaTipoPago;
import santaclara.controlador.reportes.ContReportMontFacturadoAlmacen;
import santaclara.controlador.reportes.ContReportMontFacturadoVendedor;
import santaclara.modelo.Usuario;
import santaclara.vista.PrincipalUI;
import santaclara.vista.herramientas.VistaGenericaUI;

public  class ContPrincipal {
	
	private PrincipalUI  vista;
	private IContGeneral controlador;
	private Usuario		 usuario;
	private Stack<String> cache = new Stack<String>();
	private Stack<Object> cacheObjet = new Stack<Object>();
	private Boolean editorActivo = new Boolean(false);
	
	public static void main(String[] args) {
	   ContPrincipal controlador = new  ContPrincipal();
	   controlador.ejecutar();   
	   
	}

	private void ejecutar() {
		// TODO Auto-generated method stub
		vista = new PrincipalUI(this);
		try {
			setControlador(new ContIniciarSesion(this));
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@SuppressWarnings("deprecation")
	void agregarPanel(JPanel panel)
	{
		vista.getFrame().setContentPane(panel);
		vista.getFrame().resize(VistaGenericaUI.getWidthPantalla(),VistaGenericaUI.getHeightPantalla());
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
		vista.getFrame().getContentPane().removeAll();
		vista.dibujarMenu(usuario);
	}
	public IContGeneral getControlador() {
		return controlador;
	}

	public void setControlador(IContGeneral controlador) {
		this.controlador = controlador;
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
				if(e.getSource().equals(vista.getMntAlmacen()))
				{
						ActivarAlmacenes();
				}
				else if(e.getSource().equals(vista.getMntCamiones()))
				{
					ActivarCamiones();
				}
				else if(e.getSource().equals(vista.getMntCapacidades()))
				{
					ActivarCapacidades();
				}
				else if(e.getSource().equals(vista.getMntClientes()))
				{
					ActivarClientes(null,"",null);
				}
				else if(e.getSource().equals(vista.getMntConcesionario()))
				{
					ActivarUsuarios();
				}
				else if(e.getSource().equals(vista.getMntEmpaqueProductos()))
				{
					ActivarEmpaqueProductos();
				}
				else if(e.getSource().equals(vista.getMntJefeVenta()))
				{
					ActivarUsuarios();
				}
				else if(e.getSource().equals(vista.getMntPresentaciones()))
				{
					ActivarPresentaciones();
				}
				else if(e.getSource().equals(vista.getMntProductos()))
				{
					ActivarProductos();
				}
				else if(e.getSource().equals(vista.getMntProductoAlmacenes()))
				{
					ActivarProductoAlmacenes();
				}
				else if(e.getSource().equals(vista.getMntRuta()))
				{
					ActivarRutas();
				}
				else if(e.getSource().equals(vista.getMntSabores()))
				{
					ActivarSabores();
				}
				else if(e.getSource().equals(vista.getMntClientes()))
				{
					ActivarClientes(null,"",null);
				}
				else if(e.getSource().equals(vista.getMntUsuarios()))
				{
					ActivarUsuarios();
				}
				else if(e.getSource().equals(vista.getMntVendedores()))
				{
					ActivarUsuarios();
				}
				else if(e.getSource().equals(vista.getMntVisitas()))
				{
					ActivarVisitas();
				}
				else if(e.getSource().equals(vista.getMntZonas()))
				{
					ActivarZonas();
				}
				else if(e.getSource().equals(vista.getMntCalendarios()))
				{
					ActivarCalendarios();
				}
				else if(e.getSource().equals(vista.getMntPedidos()))
				{
					ActivarPedidos(null,"",null);
				}
				else if(e.getSource().equals(vista.getMntReportMontFacturaAlmacen())){
					ActivarReportFacturadoAlmacen();
				}
				else if(e.getSource().equals(vista.getMntReportMontFacturaVendedor())){
					ActivarReportFacturadoVendedor();
				}
				else if(e.getSource().equals(vista.getMntConsultaDetalleFacturaMesAlmacen())){
					ActivarConsultaDetalleFacturaMesAlmacen();
				}
				else if(e.getSource().equals(vista.getMntListCantRefrescoSaborVendidoAlmacen())){
					ActivarListCantRefrescoSaborVendidoAlmacen();
				}
				else if(e.getSource().equals(vista.getMntListCantRefrescoPresentCapacFacturadoZona())){
					ActivarListCantRefrescoPresentCapacFacturadoZona();
				}
				else if(e.getSource().equals(vista.getMntListClienteZonaTipo())){
					ActivarListClienteZonaTipo();
				}
				else if(e.getSource().equals(vista.getMntMontoFacturadoMesZonaTipoPago())){
					ActivarMontoFacturadoMesZonaTipoPago();
				}
			}
		};
	}
	 
public void ActivarConsultaDetalleFacturaMesAlmacen(){
		
		try {
			
			controlador = new ContDetalleFacturaMesAlmacen(ContPrincipal.this);
		}
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
public void ActivarListCantRefrescoSaborVendidoAlmacen(){
	
	try {
		
		controlador = new ContListCantRefrescoSaborVendidoAlmacen(ContPrincipal.this);
	}
	catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
}
public void ActivarListCantRefrescoPresentCapacFacturadoZona(){
	
	try {
		
		controlador = new ContListCantRefrecoPresentCapacFacturadoZona(ContPrincipal.this);
	}
	catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
}
public void ActivarListClienteZonaTipo(){
	
	try {
		
		controlador = new ContListClienteTipoZona(ContPrincipal.this);
	}
	catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
}
public void ActivarMontoFacturadoMesZonaTipoPago(){
	
	try {
		
		controlador = new ContMontoFacturadoMesZonaTipoPago(ContPrincipal.this);
	}
	catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
}

public void ActivarReportFacturadoAlmacen(){
	
	try {

		controlador = new ContReportMontFacturadoAlmacen(ContPrincipal.this);
	}
	catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
}
	
	
public void ActivarReportFacturadoVendedor(){
		
		try {

			controlador = new ContReportMontFacturadoVendedor(ContPrincipal.this);
		}
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
	public void ActivarVisitas(){
		// TODO Auto-generated method stub
		try {
			controlador = new ContVisitas(ContPrincipal.this);
		}
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void ActivarClientes(Object objetContCache,Object objetContCachePresente,Object objetClassVista) {
		// TODO Auto-generated method stub
		try {
			ContClientes contClientes = new ContClientes(ContPrincipal.this);
			
			if(objetContCache!=null)
			{	
				contClientes.setCliente((((ContClientes)objetContCache).getCliente()));
				contClientes.actualizarVista();
			} 
		 	if(objetContCachePresente!=null)
		 	{		
		 		contClientes.actualizarContCliente(objetContCachePresente,objetClassVista);
			}
		
			controlador = contClientes;
		}
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void ActivarVendedores() {
		// TODO Auto-generated method stub
		try {
			controlador = new ContUsuarios(ContPrincipal.this);
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
	public void ActivarEmpaqueProductos() {
		// TODO Auto-generated method stub
		try {
			controlador = new ContEmpaqueProductos(ContPrincipal.this);
		}
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void ActivarAlmacenes() {
		// TODO Auto-generated method stub
		try {
			controlador = new ContAlmacenes(ContPrincipal.this);
		}
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void ActivarProductoAlmacenes() {
		// TODO Auto-generated method stub
		try {
			controlador = new ContProductoAlmacenes(ContPrincipal.this);
		}
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void ActivarCamiones() {
		// TODO Auto-generated method stub
		try {
			controlador = new ContCamiones(ContPrincipal.this);
		}
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void ActivarUsuarios() {
		// TODO Auto-generated method stub
		try {
			controlador = new ContUsuarios(ContPrincipal.this);
		}
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void ActivarZonas() {
		// TODO Auto-generated method stub
		try {
			controlador = new ContZonas(ContPrincipal.this);
		}
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void ActivarCalendarios() {
		// TODO Auto-generated method stub
		try {
			controlador = new ContCalendarios(ContPrincipal.this);
		}
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void ActivarPedidos(Object objetContCache,Object objetContCachePresente,Object objetClassVista) {
		// TODO Auto-generated method stub
		try {
			ContPedidos contPedidos = new ContPedidos(ContPrincipal.this);
			if(objetContCache!=null)
			{	
				contPedidos.cargarVistaPrevia(((ContPedidos)objetContCache).getAlmacen(),
						((ContPedidos)objetContCache).getVendedor(), ((ContPedidos)objetContCache).getCliente(), 
						((ContPedidos)objetContCache).getFactura(), ((ContPedidos)objetContCache).getDetalleFactura());
			} 
		 	if(objetContCachePresente!=null)
		 	{		
		 		contPedidos.actualizarContPedido(objetContCachePresente,objetClassVista);
			}
			controlador = contPedidos;
		}
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void ActivarAtras(Object objectClassVista){
		// TODO Auto-generated method stub
		if (!cacheObjet.empty())
		{
			Object obtetContCache = cacheObjet.pop();
			Object obtetContCachePresente = obtetContCache;// el objetControlador presente
			obtetContCache = cacheObjet.pop();
			
			switch (obtetContCache.getClass().getName()) {
			case "santaclara.ContIniciarSesion": cacheObjet.push(
							new ContIniciarSesion(this));
			break;
			case "santaclara.controlador.ContProductos": 		ActivarProductos();
				break;
			case "santaclara.controlador.ContPresentaciones": 	ActivarPresentaciones();
			break;
			case "santaclara.controlador.ContVendedores":		ActivarVendedores();
			break;
			case "santaclara.controlador.ContClientes":			ActivarClientes(obtetContCache,obtetContCachePresente,objectClassVista);
			break;
			case "santaclara.controlador.ContRutas":				ActivarRutas();
			break;
			case "santaclara.controlador.ContCapacidades":		ActivarCapacidades();
			break;
			case "santaclara.controlador.ContSabores":			ActivarSabores();
			break;
			case "santaclara.controlador.ContEmpaqueProductos":	ActivarEmpaqueProductos();
			break;
			case "santaclara.controlador.ContAlmacenes":	ActivarAlmacenes();
			break;
			case "santaclara.controlador.ContProductoAlmacenes":	ActivarProductoAlmacenes();
			break;
			case "santaclara.controlador.ContCamiones":	ActivarCamiones();
			break;
			case "santaclara.controlador.ContUsuarios":	ActivarUsuarios();
			break;
			case "santaclara.controlador.ContZonas":	ActivarZonas();
			break;
			case "santaclara.controlador.ContVisitas":	ActivarVisitas();
			break;
			case "santaclara.controlador.ContCalendario":	ActivarCalendarios();
			break;
			case "santaclara.controlador.ContPedidos":	ActivarPedidos(obtetContCache,obtetContCachePresente,objectClassVista);
			break;
			case "santaclara.controlador.reportes.ContReportMontFacturadoAlmacen":	ActivarReportFacturadoAlmacen();
			break;
			case "santaclara.controlador.reportes.ContReportMontFacturadoVendedor":	ActivarReportFacturadoVendedor();
			break;
			case "santaclara.controlador.cansultas.ContDetalleFacturaMesAlmacen":	ActivarConsultaDetalleFacturaMesAlmacen();
			break;
			case "santaclara.controlador.cansultas.ContListCantRefrescoSaborVendidoAlmacen":	ActivarListCantRefrescoSaborVendidoAlmacen();;
			break;
			case "santaclara.controlador.cansultas.ContMontoFacturadoMesZonaTipoPago":	ActivarMontoFacturadoMesZonaTipoPago();;
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

	public Boolean getEditorActivo() {
		return editorActivo;
	}

	public void setEditorActivo(Boolean editorActivo) {
		this.editorActivo = editorActivo;
	}

	public Stack<Object> getCacheObjet() {
		return cacheObjet;
	}

	public void setCacheObjet(Stack<Object> cacheObjet) {
		this.cacheObjet = cacheObjet;
	}

	
}
