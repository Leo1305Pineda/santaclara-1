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

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import santaclara.controlador.consultas.ContConsultadeMontoTotalporRefresco;
import santaclara.controlador.consultas.ContDetalleFacturaMesAlmacen;
import santaclara.controlador.consultas.ContListCantRefrecoPresentCapacFacturadoZona;
import santaclara.controlador.consultas.ContListCantRefrescoSaborVendidoAlmacen;
import santaclara.controlador.consultas.ContListClienteTipoZona; 
import santaclara.controlador.consultas.ContMontoFacturadoMesZonaTipoPago;
import santaclara.controlador.reportes.ContReportMontFacturadoAlmacen; 
import santaclara.controlador.reportes.ContReportMontFacturadoVendedor;
import santaclara.controlador.reportes.ContReporte;
import santaclara.dbPostgresql.controlador.ContAjusteBaseDatoSql;
import santaclara.modelo.DomicilioComercio;
import santaclara.modelo.Ruta;
import santaclara.modelo.Salp;
import santaclara.modelo.Zona;
import santaclara.thread.animacion.Animado;
import santaclara.vista.PrincipalUI;
import santaclara.vista.herramientas.VistaGenericaUI;

public  class ContPrincipal {

	private PrincipalUI  vista;
	private Stack<Object> cacheObjet = new Stack<Object>();
	private ContGeneral contGeneral  = new ContGeneral();
	private Animado animado ;
	
	public static void main(String[] args) {
	   ContPrincipal controlador = new  ContPrincipal();
	   controlador.ejecutar();   
	}

	public void ejecutar() {
		// TODO Auto-generated method stub
		vista = new PrincipalUI(this);
		try {
			 new ContIniciarSesion(this);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void mostrarComposite(){
		try {
		if (!cacheObjet.empty())
		{
			Object obtetContCache = cacheObjet.pop();
			Object obtetContCachePresente = obtetContCache;// el objetControlador presente
			
			List<Object> list = (List<Object>)contGeneral.asociar();
			String clase =list.get(0).getClass().getName().toString();
			if (clase == null) throw new Exception("No se Puede Asociar Las Tablas");  
			switch (clase) {
			case "santaclara.modelo.Zona": 
				ContZonas contZonas = new ContZonas(this);
				List<Zona> zonas = new ArrayList<Zona>();
				for(Object objectruta: list)zonas.add((Zona)objectruta);
				contZonas.activarBinding(zonas);
			break;
			case "santaclara.modelo.Ruta": 
				ContRutas contRutas;
				contRutas = new ContRutas(this);
				List<Ruta> rutas = new ArrayList<Ruta>();
				for(Object objectruta: list)rutas.add((Ruta)objectruta);
				contRutas.activarBinding(rutas);
			break;
			case "santaclara.modelo.DomicilioComercio": 
				ContClientes contClientes = new ContClientes(this);
				List<DomicilioComercio> domicilioComercios = new ArrayList<DomicilioComercio>();
				for(Object objectruta: list)domicilioComercios.add((DomicilioComercio)objectruta);
				contClientes.activarBindingDomicilioComercios(domicilioComercios);
			break;
			case"santaclara.modelo.Salp":
				ContClientes contClientes1 = new ContClientes(this);
				List<Salp> salps = new ArrayList<Salp>();
				for(Object objectruta: list)salps.add((Salp)objectruta);
				contClientes1.activarBindingSalp(salps);
			break;
			default:contGeneral.getControladores().clear();
				
			}
			cacheObjet.push(obtetContCachePresente);
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(new JPanel(),"No existe asociacion");
			contGeneral.getControladores().clear();
		}
	
	}
	
	public void agregarComposicion(){
		if (!cacheObjet.empty())
		{
			Object obtetContCache = cacheObjet.pop();
			Object obtetContCachePresente = obtetContCache;// el objetControlador presente
			
			contGeneral.addControlador((IContGeneral)obtetContCachePresente);
			
			cacheObjet.push(obtetContCachePresente);
			JOptionPane.showMessageDialog(vista, "Tabla Agregada");
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
		cerrarSecion();
	}
	
	public void dibujarMenu()
	{
		vista.getFrame().getContentPane().removeAll();
		vista.dibujarMenu();
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
		new ContIniciarSesion(ContPrincipal.this);
	}

	public ActionListener activarMenu() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				animado.detener();
				 
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
				else if(e.getSource().equals(vista.getMntCapturarLista())){
					agregarComposicion();
				}
				else if(e.getSource().equals(vista.getMntEjecutarCapturaLista())){
					mostrarComposite();
				}
			}
		};
	}
	
public void ActivarPostgreSqlAjustes(){
		
		try {
			
			new ContAjusteBaseDatoSql(ContPrincipal.this);
		}
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	public void ActivarReporte(){
		
		try {
			
			new ContReporte(ContPrincipal.this);
		}
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void ActivarConsulta(){
	
		try {
		
			new ContConsultadeMontoTotalporRefresco(ContPrincipal.this);
			}
			catch (Exception e1) {
				// 	TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
	
	public void ActivarConsultaDetalleFacturaMesAlmacen(){
		
		try {
			
			new ContDetalleFacturaMesAlmacen(ContPrincipal.this);
		}
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
public void ActivarListCantRefrescoSaborVendidoAlmacen(){
	
	try {
		
		new ContListCantRefrescoSaborVendidoAlmacen(ContPrincipal.this);
	}
	catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
}
public void ActivarListCantRefrescoPresentCapacFacturadoZona(){
	
	try {
		
		new ContListCantRefrecoPresentCapacFacturadoZona(ContPrincipal.this);
	}
	catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
}
public void ActivarListClienteZonaTipo(){
	
	try {
		
		new ContListClienteTipoZona(ContPrincipal.this);
	}
	catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
}
public void ActivarMontoFacturadoMesZonaTipoPago(){
	
	try {
		
		new ContMontoFacturadoMesZonaTipoPago(ContPrincipal.this);
	}
	catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
}

public void ActivarReportFacturadoAlmacen(){
	
	try {

		 new ContReportMontFacturadoAlmacen(ContPrincipal.this);
	}
	catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
}
	
	
public void ActivarReportFacturadoVendedor(){
		
		try {

			 new ContReportMontFacturadoVendedor(ContPrincipal.this);
		}
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void ActivarRutas(){
		// TODO Auto-generated method stub
		try {
			new ContRutas(ContPrincipal.this);
		}
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void ActivarVisitas(){
		// TODO Auto-generated method stub
		try {
			 new ContVisitas(ContPrincipal.this);
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
		
			//controlador = contClientes;
		}
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void ActivarVendedores() {
		// TODO Auto-generated method stub
		try {
			new ContUsuarios(ContPrincipal.this);
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
			new ContProductos(ContPrincipal.this);
		}
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		

	}
	
	public void ActivarPresentaciones() {
		// TODO Auto-generated method stub
		try {
			 new ContPresentaciones(ContPrincipal.this);
		}
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	
	public void ActivarCapacidades() {
		// TODO Auto-generated method stub
		try {
			 new ContCapacidades(ContPrincipal.this);
		}
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void ActivarSabores() {
		// TODO Auto-generated method stub
		try {
			 new ContSabores(ContPrincipal.this);
		}
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void ActivarEmpaqueProductos() {
		// TODO Auto-generated method stub
		try {
			new ContEmpaqueProductos(ContPrincipal.this);
		}
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void ActivarAlmacenes() {
		// TODO Auto-generated method stub
		try {
			new ContAlmacenes(ContPrincipal.this);
			 
		}
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void ActivarProductoAlmacenes() {
		// TODO Auto-generated method stub
		try {
			 new ContProductoAlmacenes(ContPrincipal.this);
		}
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void ActivarCamiones() {
		// TODO Auto-generated method stub
		try {
			 new ContCamiones(ContPrincipal.this);
		}
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void ActivarUsuarios() {
		// TODO Auto-generated method stub
		try {
			 new ContUsuarios(ContPrincipal.this);
		}
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void ActivarZonas() {
		// TODO Auto-generated method stub
		try {
			new ContZonas(ContPrincipal.this); 
		}
		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void ActivarCalendarios() {
		// TODO Auto-generated method stub
		try {
			 new ContCalendarios(ContPrincipal.this);
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
			//controlador = contPedidos;
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
			case "santaclara.dbPostgresql.controlador.ContPostgreSql":	ActivarPostgreSqlAjustes();
			break;
			default:
				break;
			}
		}
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

	public Stack<Object> getCacheObjet() {
		return cacheObjet;
	}

	public void setCacheObjet(Stack<Object> cacheObjet) {
		this.cacheObjet = cacheObjet;
	}

	public PrincipalUI getVista() {
		return vista;
	}

	public ContGeneral getContGeneral() {
		return contGeneral;
	}

	public void setContGeneral(ContGeneral contGeneral) {
		this.contGeneral = contGeneral;
	} 	
}
