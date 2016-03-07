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
import java.util.Stack; 
import javax.swing.JOptionPane;
import javax.swing.JPanel;
 
import santaclara.controlador.consultas.ContConsulta;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.swing.JLabel;

import santaclara.controlador.consultas.ContConsultadeMontoTotalporRefresco;

import santaclara.controlador.consultas.ContDetalleFacturaMesAlmacen;
import santaclara.controlador.consultas.ContListCantRefrecoPresentCapacFacturadoZona;
import santaclara.controlador.consultas.ContListCantRefrescoSaborVendidoAlmacen;
import santaclara.controlador.consultas.ContListClienteTipoZona; 
import santaclara.controlador.reportes.ContReportMontFacturadoAlmacen; 
import santaclara.controlador.reportes.ContReporte;
import santaclara.modelo.Camion;
import santaclara.dbPostgresql.controlador.ContAjusteBaseDatoSql;
import santaclara.modelo.Usuario;
import santaclara.thread.animacion.Animado;
import santaclara.vista.PrincipalUI;
import santaclara.vista.herramientas.VistaGenericaUI;

public  class ContPrincipal {
	
	private PrincipalUI  vista;
	private IContGeneral controlador;
	private Usuario		 usuario;
	private Stack<String> cache = new Stack<String>();
	private Stack<Object> cacheObjet = new Stack<Object>();
	private Boolean editorActivo = new Boolean(false); 
	
	private ContAlmacenes 	contAnimaciones ;
	
	private Animado animado ;
	
	public static void main(String[] args) {
	   ContPrincipal controlador = new  ContPrincipal();
	   controlador.ejecutar();   
	   
	}

	public void ejecutar() {
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
	

	void agregarPanel(JPanel panel)
	{
		//vista.getFrame().getContentPane().removeAll();
		//vista.getFrame().getContentPane().add(panel);
		//vista.getFrame().getContentPane().removeAll();
		vista.getFrame().setContentPane(panel);
		vista.getFrame().resize(VistaGenericaUI.getWidthPantalla(),VistaGenericaUI.getHeightPantalla());
		vista.getFrame().repaint();
	}
	
	void quitarPanel(){
	//	vista.getFrame().getContentPane().removeAll();
	//	vista.getFrame().add(new JPanel());	
		cerrarSecion();
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
				cerrarSecion(); 
			}
		};
	}
	
	public void cerrarSecion(){
		vista.getMenuBar().setVisible(false);
		usuario = null;
		controlador = new ContIniciarSesion(ContPrincipal.this);
	}

	public ActionListener activarMenu() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try{
					animado.detener();
					if(e.getSource().equals(vista.getMntAlmacen()))
					{
							ActivarAlmacenes();
					}
					else if(e.getSource().equals(vista.getMntCamiones()))
					{
						ActivarCamiones(null,null,null);
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
					else if(e.getSource().equals(vista.getMntReporte())){
						ActivarReporte();
					}
					else if(e.getSource().equals(vista.getMntConsulta())){
						ActivarConsulta();
					}
					else if(e.getSource().equals(vista.getMntPostgreSqlAjustes())){
						ActivarPostgreSqlAjustes();
					}
					else if(e.getSource().equals(vista.getMntPgAdmin3())){
						ejecutarComando("pgadmin3");
					}
			}
			catch(Exception e1)
			{
				JOptionPane.showConfirmDialog(null,e1.getMessage());
				e1.printStackTrace();
			}

			}
 
		};
	}
 
	
	public void ActivarPostgreSqlAjustes(){
		try {
			controlador = new ContAjusteBaseDatoSql(ContPrincipal.this);
		}
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	public void ActivarReporte(){
		
		try {
			
			controlador = new ContReporte(ContPrincipal.this);
		}
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void ActivarConsulta(){
	
		try {
		
			controlador = new ContConsultadeMontoTotalporRefresco(ContPrincipal.this);
			}
			catch (Exception e1) {
				// 	TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
  
	private void ActivarConcenionario(ContGeneral contLlamador, ContGeneral contllamado, Object mensaje) throws Exception {
		// TODO Auto-generated method stub

		if(contLlamador != null)
		{
			//controlador = new ContCamiones(ContPrincipal.this);
			contLlamador.dibujar(contLlamador.getVista(),contLlamador);
			if(contLlamador instanceof ContConcesionarios )
			{
				((ContConcesionarios) contLlamador).setCamion((Camion)mensaje);
			}
		}
		else // no ah sido invocado 
			controlador = new ContConcesionarios(ContPrincipal.this);
		
		/*controlador = new ContConcesionarios(ContPrincipal.this);
		((ContConcesionarios) controlador).setCamion();
		*/
	}

	public void ActivarReportFacturadoAlmacen()  throws Exception {
		controlador = new ContReportMontFacturadoAlmacen(ContPrincipal.this);
	}
	

	public void ActivarJefeVenta()  throws Exception {
		// TODO Auto-generated method stub
		controlador = new ContJefeVentas(ContPrincipal.this);
	}
	
	
	public void ActivarRutas()   throws Exception {
			controlador = new ContRutas(ContPrincipal.this);
	}
	public void ActivarVisitas()  throws Exception {
		// TODO Auto-generated method stub
			controlador = new ContVisitas(ContPrincipal.this);
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
	
	public void ActivarVendedores()   throws Exception {
		// TODO Auto-generated method stub
			controlador = new ContVendedores(ContPrincipal.this);
	}

	public void setVista(PrincipalUI vista) {
		this.vista = vista;
	}

	public void ActivarProductos()   throws Exception {
		// TODO Auto-generated method stub
			controlador = new ContProductos(ContPrincipal.this);

	}
	 

	
	public void ActivarPresentaciones()   throws Exception {
			controlador = new ContPresentaciones(ContPrincipal.this);
	}
	
	public void ActivarCapacidades()   throws Exception {
		// TODO Auto-generated method stub
			controlador = new ContCapacidades(ContPrincipal.this);
	}
	
	public void ActivarSabores()   throws Exception {
		// TODO Auto-generated method stub
			controlador = new ContSabores(ContPrincipal.this);
	}
	
	public void ActivarEmpaqueProductos()   throws Exception {
		// TODO Auto-generated method stub
			controlador = new ContEmpaqueProductos(ContPrincipal.this);
	
	}
	
	public void ActivarAlmacenes() throws Exception {
		// TODO Auto-generated method stub
			controlador = new ContAlmacenes(ContPrincipal.this);
	}
	
	public void ActivarProductoAlmacenes()  throws Exception {
		// TODO Auto-generated method stub
			controlador = new ContProductoAlmacenes(ContPrincipal.this);
	}
	
	/* 
	 *  contCamiones : controlador de Camiones
	 *  contLlamador : si llamo a otro controlador este seria a quien el controlador llamo 
	 *  Object objectClassVist
	 */
	public void ActivarCamiones(ContGeneral contLlamador,ContGeneral contCamiones, Object mensaje) throws Exception  {
		
		if(contLlamador != null)
		{
			//controlador = new ContCamiones(ContPrincipal.this);
			contLlamador.dibujar(contCamiones.getVista(),contCamiones);
			if(contLlamador instanceof ContConcesionarios )
			{
				((ContConcesionarios) contLlamador).setCamion((Camion)mensaje);
			}
		}
		else // no ah sido invocado 
			controlador = new ContCamiones(ContPrincipal.this);
	}
	
	public void ActivarUsuarios()  throws Exception {
			controlador = new ContUsuarios(ContPrincipal.this);
	}

	public void ActivarZonas()  throws Exception {
		// TODO Auto-generated method stub
			controlador = new ContZonas(ContPrincipal.this);
	}
	
	public void ActivarCalendarios()  throws Exception {
		// TODO Auto-generated method stub
			controlador = new ContCalendarios(ContPrincipal.this);
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
	
	public void ActivarAtras(Object mensaje) throws Exception{
		// TODO Auto-generated method stub
		if (!cacheObjet.empty())
		{ 
			
			Object obtetContCachePresente = cacheObjet.pop(); ;// el objetControlador presente
			Object obtetContCache = cacheObjet.pop(); // llamador
			switch (obtetContCache.getClass().getName()) 
			{
			case "santaclara.ContIniciarSesion": 
				cacheObjet.push(new ContIniciarSesion(this));
				break;
			case "santaclara.controlador.ContProductos":
				ActivarProductos();
				break;
			case "santaclara.controlador.ContClientes":
				
				break;
			case "santaclara.controlador.ContRutas":
				ActivarRutas();
				break;
			case "santaclara.controlador.ContCapacidades":		
				ActivarCapacidades();
				break;
			case "santaclara.controlador.ContSabores":			
				ActivarSabores();
				break;
			case "santaclara.controlador.ContEmpaqueProductos":	
				ActivarEmpaqueProductos();
				break;
			case "santaclara.controlador.ContAlmacenes":	
				ActivarAlmacenes();
				break;
			case "santaclara.controlador.ContProductoAlmacenes":	
				ActivarProductoAlmacenes();
				break;
			case "santaclara.controlador.ContConcesionarios":
				ActivarConcenionario((ContGeneral)obtetContCache,(ContGeneral)obtetContCachePresente,mensaje);
				break;
			case "santaclara.controlador.ContPresentaciones":
					ActivarPresentaciones();
			break;
			case "santaclara.controlador.ContVendedores":		
				ActivarVendedores();
			break;
			case "santaclara.controlador.ContCamiones":	
				ActivarCamiones((ContGeneral)obtetContCache,(ContGeneral)obtetContCachePresente,mensaje);
			break;
			case "santaclara.controlador.ContUsuarios": 
				ActivarUsuarios();
			break;
			case "santaclara.controlador.ContZonas":	
				ActivarZonas();
			break;
			case "santaclara.controlador.ContVisitas":
				ActivarVisitas();
			break;
			case "santaclara.controlador.ContCalendario":	
				ActivarCalendarios();
			break;
			case "santaclara.controlador.ContPedidos":	
				ActivarPedidos(obtetContCache,obtetContCachePresente,mensaje);
			break;
			case "santaclara.controlador.reportes.ContReportMontFacturadoAlmacen":	
				ActivarReportFacturadoAlmacen();
			break;
			case "santaclara.controlador.reportes.ContReportMontFacturadoVendedor":
				ActivarReportFacturadoVendedor();
			break;
			case "santaclara.controlador.cansultas.ContDetalleFacturaMesAlmacen":	
				ActivarConsultaDetalleFacturaMesAlmacen();
			break;
			case "santaclara.controlador.cansultas.ContListCantRefrescoSaborVendidoAlmacen":	
				ActivarListCantRefrescoSaborVendidoAlmacen();;
			break;
			case "santaclara.controlador.cansultas.ContMontoFacturadoMesZonaTipoPago":	
				ActivarMontoFacturadoMesZonaTipoPago();;
			break;
			case "santaclara.dbPostgresql.controlador.ContPostgreSql":	
				ActivarPostgreSqlAjustes();
			break;
			default:
				break;
			}
		}
	}
	
 	private void ActivarMontoFacturadoMesZonaTipoPago() {
		// TODO Auto-generated method stub
		
	}

	private void ActivarReportFacturadoVendedor() {
		// TODO Auto-generated method stub
		
	}

	public void ejecutarComando(String comando){
		try{
			Runtime runtime = Runtime.getRuntime();
			Process process;

			process = runtime.exec(comando);
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
	
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void dibujarImagen(JPanel panel,JLabel lblImagen,Object ubicacion){
		if(ubicacion.equals("")) panel.add(lblImagen);
		else panel.add(lblImagen,ubicacion);
		
		vista.getFrame().repaint();
	}

	
	public void activarAnimacionSantaclara(JPanel vista){
		
		JLabel lblImagen = new JLabel();
		lblImagen.setForeground(Color.WHITE);
		animado = new Animado("animacionSantaclara",lblImagen,500);
		animado.star();
		dibujarImagen(vista,lblImagen,BorderLayout.SOUTH);
		
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

	public ContAlmacenes getContAnimaciones() {
		return contAnimaciones;
	}

	public void setContAnimaciones(ContAlmacenes contAnimaciones) {
		this.contAnimaciones = contAnimaciones;
	}
 

	public PrincipalUI getVista() {
		return vista;
	}  
}
