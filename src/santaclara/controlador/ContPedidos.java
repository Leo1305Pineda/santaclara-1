/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import santaclara.Servicio.ServicioDetalleFactura;
import santaclara.Servicio.ServicioDomicilioComercio;
import santaclara.Servicio.ServicioFactura;
import santaclara.Servicio.ServicioSalp;
import santaclara.modelo.Almacen;
import santaclara.modelo.Cliente;
import santaclara.modelo.DetalleFactura;
import santaclara.modelo.ProductoAlmacen;
import santaclara.modelo.Factura;
import santaclara.modelo.Usuario;
import santaclara.vista.PedidosUI;

public class ContPedidos extends ContGeneral implements IContGeneral{
	
	private static final double IVA = 0.12; 
	private PedidosUI vista;
	private Factura factura = new Factura(); 
	private List<DetalleFactura> detalleFacturas  = new ArrayList<DetalleFactura>();
	@SuppressWarnings("rawtypes")
	private JTableBinding binDetalleFactura;
	String inicio = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date());
	private ContMediador  mediador;
	
	
	public ContPedidos(Cliente cliente,	Usuario vendedor, Almacen almacen) {
		this.factura.setCliente(cliente);
		this.factura.setVendedor(vendedor);
		this.factura.setAlmacen(almacen);
		this.mediador = new ContMediador();

	}

	public ContPedidos(ContPrincipal contPrincipal) throws Exception {
		// TODO Auto-generated constructor stub
		this.mediador = new ContMediador();
		setContPrincipal(contPrincipal);
		vista = new PedidosUI(this);
		dibujar(vista,this);
		actualizarVista();
	}

	@Override
	public PedidosUI getVista() {
		// TODO Auto-generated method stub
		return vista;
	}
	
	public void limpiarlblCliente(){
		vista.getLblRif().setText("Rif:");
		vista.getLblRazonSocial().setText("Razon Social:");
		vista.getLblTelefono().setText("Telefono:");
		vista.getLblDireccion().setText("->");
		vista.getLblRuta().setText("Ruta:");
	}
	
	public void cargarClientelbl(Cliente cliente) throws Exception{
		
		if(cliente != null){
			if (cliente.getId()==null)
			{
				limpiarlblCliente();
				vista.getPnlCliente().setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 1, true), "Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
			}
			else
			{
				setPnlClienteTitle(cliente.getId());
				vista.getLblRif().setText("Rif: "+cliente.getRif());
				vista.getLblRazonSocial().setText("Nombre o Razon Social: "+cliente.getId()+"-> "+cliente.getRazonsocial());
				vista.getLblTelefono().setText("Telefono: "+cliente.getTelefono());
				vista.getLblDireccion().setText("-> "+cliente.getDireccion());
				vista.getLblRuta().setText("Ruta: "+cliente.getRutaNombre());
			}	
		}else limpiarlblCliente(); 
	}
	
	public void setPnlClienteTitle(Integer id) throws Exception{
	
		if (new ServicioDomicilioComercio().isbuscar(id).equals(false))
		{
			vista.getPnlCliente().setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 1, true), "Cliente Salp", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		}
		else
		{
			if(new ServicioDomicilioComercio().getdDomicilioComercio(id).getTipo().equals("C")) 
			{
				vista.getPnlCliente().setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 1, true), "Cliente Comercial", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
			}
			else vista.getPnlCliente().setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 1, true), "Cliente Domicilio", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
				
		}
	}
	
	public void limpiarlblVendedor(){
		vista.getLblNombreVendedor().setText("");
	}
	
	public void cargarVendedorlbl(Usuario vendedor){
		if(vendedor!=null){
			if (vendedor.getId() == null )
			{
				limpiarlblVendedor();
			}
			else
			{
				vista.getLblNombreVendedor().setText(vendedor.getId()+" --> ".concat(vendedor.getNombre()));
			}	
		}else limpiarlblVendedor();
	}
	
	public void limpiarlbAlmacen(){
	
			vista.getLblAlmacen().setText("Ubicacion:");
	}
	
	public void cargarAlmacenlbl(Almacen almacen){
		if(almacen != null)
		{
			if (almacen.getId() == null )
			{
				limpiarlbAlmacen();
			}
			else
				{
					vista.getLblAlmacen().setText(almacen.getId()+" --> ".concat(almacen.getUbicacion()));
				}
		}
		else limpiarlbAlmacen();
	}
	
	public void limpiarlblFactura(){
		vista.getTxtNumeroPedido().setText("Numero:      AutoGenerado");
		vista.getLblFecha().setText("Fecha:       "+new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		vista.getLblCondicion().setText("Condicion de Pago: ");
		vista.getLblSubtotalExento().setText("SUBTOTAL EXENTO:    ");
		vista.getLblSubtotalGravado().setText("SUBTOTAL GRAVADO:   ");
		vista.getLblDesc().setText("% DESCUENTO.:       ");
		vista.getLblIvaSobreBs().setText("I.V.A. SOBRE Bs.    ");
		vista.getLblIva().setText("I.V.A. 12.00  %     ");
		vista.getLblTotalAPagar().setText("TOTAL A PAGAR:      ");

	
	} 
	
	public void cargarFacturalbl(Factura factura) throws IOException{
		limpiarlblFactura();
	if(factura.getId()==null)
	{
		vista.getTxtNumeroPedido().setText("AutoGenerado");
	}
	else
	{
		vista.getTxtNumeroPedido().setText(factura.getId().toString());
	}
	vista.getLblFecha().setText("Fecha:       "+new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
	
	
	if(factura.getEstado()==null)
	{
		vista.getLblCondicion().setText("Condicion: PEDIDO");
	}
	else if(factura.getEstado()==true)
	{
		vista.getLblCondicion().setText("Condicion: CONTADO");
	}
	else
	{
		vista.getLblCondicion().setText("Condicion: CREDITO");
	}
	
	if(!detalleFacturas.isEmpty())
	{
		Integer countCajaPorFactura = new Integer(0);
		Double subTotalExento = new Double(0);
		Double subTotalGravado = new Double(0);
		Double desc = new Double(0);
		Double totalAPagar = new Double(0);
			
			for (DetalleFactura detalleFactura : detalleFacturas)
			{
				if(detalleFactura != null)
				{
					if(detalleFactura.getIva()==0.0)
					{
						subTotalExento = subTotalExento + (detalleFactura.getTotal());
					}
					else
					{
						subTotalGravado = subTotalGravado + (detalleFactura.getTotal());
					}
					countCajaPorFactura = countCajaPorFactura + detalleFactura.getCantidad();
				}
			}
	
			if(countCajaPorFactura >=10 && countCajaPorFactura <=20)
			{
				desc = 5.0;
			}
			else if(countCajaPorFactura >=21 && countCajaPorFactura <=35)
			{
				desc = 10.0;
			}
			else if(countCajaPorFactura >=35)
			{
				desc = 15.0;
			}
			else 
			{
				desc = 0.00;
			}
			
		totalAPagar =  	(subTotalGravado * IVA) + 
						(subTotalExento + subTotalGravado);
		
		factura.setSubTotalExento(subTotalExento);
		factura.setSubTotalGravado(subTotalGravado);
		factura.setDescuento(desc);
		factura.setIvaSobreBs(subTotalGravado);
		factura.setIva(subTotalGravado * IVA);
		factura.setTotalAPagar(totalAPagar-((subTotalExento + subTotalGravado) * desc/100));

		vista.getLblSubtotalExento().setText("SUBTOTAL EXENTO:      "+factura.getSubTotalExento());
		vista.getLblSubtotalGravado().setText("SUBTOTAL GRAVADO:    "+factura.getSubTotalGravado());
		vista.getLblDesc().setText(desc+"% DESCUENTO.:       "+
		(factura.getSubTotalExento()+factura.getSubTotalGravado())*factura.getDescuento()/100);
		vista.getLblIvaSobreBs().setText("I.V.A. SOBRE Bs.         "+factura.getIvaSobreBs());
		vista.getLblIva().setText("I.V.A. 12.00  %             "+factura.getIva());
		vista.getLblTotalAPagar().setText("TOTAL A PAGAR:        "+factura.getTotalAPagar());

	}
		
	}
	
	public ActionListener actionCliente(){
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				mediador.cargarCliente(ContPedidos.this);

			}
		};
	}
	
	public ActionListener actionVendedor(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				mediador.cargarVendedor(ContPedidos.this);
				
			}
		};
	}
	
	public ActionListener actionAlmacen(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mediador.cargarAlmacen(ContPedidos.this);
			}
		};
	}
	
	public ActionListener actionGuardarPedido(){

		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e){
				// TODO Auto-generated method stub
				try {
					validarFactura();
					validarDetalle();
					factura.setEstado(null);
					if (factura.getId()==null)
					{
						new ServicioFactura().guardar(factura);//guarda la factura
						factura.setId(new ServicioFactura().ultimaFactura());
						for(DetalleFactura detalleFactura : detalleFacturas) detalleFactura.setFactura(factura);
						new ServicioDetalleFactura().guardar(detalleFacturas);//guarda su detalle
						JOptionPane.showMessageDialog(vista,"Guardado el Pedido Exitosamente Ya PuedeGenerar la Factura");
						vista.setEnabled(true);
						actualizarVista();
					}else JOptionPane.showMessageDialog(vista,"El Pedido Existe Cree un Nuevo Pedido");
					
				} catch (Exception exe) {
					// TODO Auto-generated catch block
					if(exe.getMessage()==null)exe.printStackTrace();
						Integer opt = new Integer(0);
						switch (exe.getMessage()) {
						case "Almacen":
							opt = new Integer(	JOptionPane.showConfirmDialog(vista,"Cargar la Informacion del "+exe.getMessage()));
							if (opt==0)
							{
								vista.getBtnAlmacen().doClick();
							}
							break;
						case "Cliente":
							opt = new Integer(	JOptionPane.showConfirmDialog(vista,"Cargar la Informacion del "+exe.getMessage()));
							if (opt==0)
							{
								vista.getBtnCliente().doClick();
							}
							break;
						case "Vendedor":
							opt = new Integer(	JOptionPane.showConfirmDialog(vista,"Cargar la Informacion del "+exe.getMessage()));
							if (opt==0)
							{
								vista.getBtnVendedor().doClick();
							}
							break;
						case "Detalle":
							opt = new Integer(	JOptionPane.showConfirmDialog(vista,"Cargar la Informacion del "+exe.getMessage()));
							if (opt==0)
							{
								vista.getBtnProducto().doClick();
							}
						break;

						default:
							break;
						}
					}
						
			}
		};	
	}
	protected void validarDetalle()throws Exception{
		
			if (detalleFacturas.isEmpty())
			{
				throw new Exception("Detalle");
			}
	}
	
	protected void validarFactura() throws Exception {
		// TODO Auto-generated method stub
				if (factura.getAlmacen() == null) 
				{
					throw new Exception("Almacen");
				}
				if (factura.getVendedor() == null)
				{
					throw new Exception("Vendedor");
				}
				if (factura.getCliente() == null)
				{
					throw new Exception("Cliente");
				}
	}

	public ActionListener actionBuscarPedido(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
 
				try {
					if(factura==null)
					{
						JOptionPane.showMessageDialog(new JPanel(), "No Encontrado");
						vista.getTxtNumeroPedido().setText("");
					}
					else
					{
						factura =  new ServicioFactura().getFactura(new Integer(vista.getTxtNumeroPedido().getText()));
						Date fechaBusqueda = factura.getFecha();
						detalleFacturas = new ServicioDetalleFactura().getDetalleFacturas(factura);
						actualizarVista();
						vista.getLblFecha().setText("Fecha:       "+new SimpleDateFormat("dd/MM/yyyy").format(fechaBusqueda));
						JOptionPane.showMessageDialog(new JPanel(), "Encontrado");
					}
					
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
						
			} 
		};
	}
	
	public ActionListener actionGenerarFactura(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					validarFactura();
					validarDetalle();
					Factura facturaAux =new ServicioFactura().getFactura(factura.getId()); 
 
					//si no existe la Factura 
					if(facturaAux!=null) 
					{	
						//y ademas no se a Facturado estado null o en Pedido
						if(facturaAux.getEstado()==null)
						{
							Integer opt = new Integer(JOptionPane.showInputDialog(vista,"Ingrese la Condicion de Pago \n"
									+ "1 = Contado -- ó -- 2 = Credito","Seleccione la Opcion",2));
							if (opt.equals(1))
							{
								factura.setEstado(true);
							}
							else if(opt.equals(2))
							{
								if((new ServicioSalp().getSalp(factura.getCliente().getId()))!=null)
								{
									//se valida si opta por el credito
									if (new ServicioFactura().isCredito(factura)) factura.setEstado(false);
									else throw new Exception("noCredito");
								}
								else throw new Exception("Cliente Salp");
							}
							else 
							{
								factura.setEstado(null);
								throw new Exception("Opcion invalida ");
							}
					
							new ServicioFactura().guardar(factura);
							new ServicioDetalleFactura().guardar(detalleFacturas);
							JOptionPane.showMessageDialog(vista,"Generada la Factura Exitosamente");
							vista.getBtnGuardarFactura().setEnabled(true);
						}
						else JOptionPane.showMessageDialog(new JPanel(), "Factura Existente");
						}
						else throw new Exception("Guardar Pedido");
 

				} catch (Exception exe) {
					// TODO Auto-generated catch block
					Integer opt = new Integer(0);
					switch (exe.getMessage()) {
					case "Guardar Pedido":
						opt = new Integer(JOptionPane.showConfirmDialog(vista, exe.getMessage(),"Seleccione la Opcion",2));
						if(opt==0){
					          vista.getBtnGuardar().doClick();	
					          vista.getBtnGuardarFactura().setBackground(Color.GREEN);
						}
						break;
					case "Opcion invalida": 
						opt = new Integer(JOptionPane.showConfirmDialog(vista, exe.getMessage().concat(
								" Desea Continuar"),"Seleccione la Opcion",2));
						if(opt==0){
							vista.getBtnGuardarFactura().doClick();
						}
						break;
					case "Cliente Salp":
						opt = new Integer(JOptionPane.showConfirmDialog(vista,"Los Credito se le asignan solo a los "+ exe.getMessage().concat(
								" Desea Continuar"),"Seleccione la Opcion",2));
						if(opt==0){
							vista.getBtnGuardarFactura().doClick();
						}
						break;
					case "noCredito":
						opt = new Integer(JOptionPane.showConfirmDialog(vista,"el Cliente no puede obtar por el credito \n "
								+ "dicho cliente no posee factura en los ultimos 6 meses que superen los 800 mil Bolivares","Seleccione la Opcion",2));
						if(opt==0){
							vista.getBtnGuardarFactura().doClick();
						}
						break;
					case "Almacen":
						opt = new Integer(	JOptionPane.showConfirmDialog(vista,"Cargar la Informacion del "+exe.getMessage()));
						if (opt==0)
						{
							vista.getBtnAlmacen().doClick();
						}
						break;
					case "Cliente":
						opt = new Integer(	JOptionPane.showConfirmDialog(vista,"Cargar la Informacion del "+exe.getMessage()));
						if (opt==0)
						{
							vista.getBtnCliente().doClick();
						}
						break;
					case "Vendedor":
						opt = new Integer(	JOptionPane.showConfirmDialog(vista,"Cargar la Informacion del "+exe.getMessage()));
						if (opt==0)
						{
							vista.getBtnVendedor().doClick();
						}
						break;
					case "Detalle":
						opt = new Integer(	JOptionPane.showConfirmDialog(vista,"Cargar la Informacion del "+exe.getMessage()));
						if (opt==0)
						{
							vista.getBtnProducto().doClick();
						}
					break;
						
					default:exe.printStackTrace();
						break;
					}
				}
				
			}
		};
	}
	
	public ActionListener actionAtras(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ActivarAtras(null);
			}
		};
	}
	
	public ActionListener actionSalir(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				quitarVista();
			}
		};
		
	}
	
	public ActionListener actionProducto(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				mediador.cargarProducto(ContPedidos.this);
			}
		};
	}
	
	public ActionListener actionQuitarProductoDetalle(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					if(factura.getEstado()!=null) throw new Exception("Aviso"); 
					if(vista.getTable().getSelectedRow()>=0 && !detalleFacturas.isEmpty())
					{
						for(DetalleFactura detalleFactura1 :  detalleFacturas){
							if (detalleFactura1.getEmpaqueProducto().getId().equals(
									new Integer(vista.getTable().getValueAt(vista.getTable().getSelectedRow(),0).toString().trim())))
							{
								detalleFacturas.remove(detalleFactura1);
								if(factura.getId()!=null)
								{
									new ServicioDetalleFactura().eliminar(detalleFactura1);
									new ServicioFactura().guardar(factura);
								}
								break;
							}
						}
						actualizarVista();
					}
					else
					{
						JOptionPane.showMessageDialog(vista,"Seleccione la fila en la tabla");
					}
			
				} catch (Exception exe) {
					// TODO Auto-generated catch block
					Integer opt = new Integer(0);
					switch (exe.getMessage()) {
					case "Aviso":
						opt = new Integer(	JOptionPane.showConfirmDialog(vista,"Operacion Invalida\n"
								+ "El Pedido ya esta Facturado\n"
								+ "Desea generar un nuevo Pedido ",exe.getMessage(),2));
					if (opt==0)
					{
						factura.setId(null);
						for(DetalleFactura detalleFactura : detalleFacturas) detalleFactura.setFactura(factura);
						vista.getBtnGuardar().doClick();
					}	
						break;

					default:
						break;
					}
					exe.printStackTrace();
				}
		
			}
		};
	}
	
	public ActionListener actionNuevo(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
 
				try {
					factura = new Factura();
					vista.getBtnGuardarFactura().setEnabled(false);
					vista.getBtnGuardar().setEnabled(false);
					actualizarVista();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
	}

	public void actualizarVista() throws Exception{	
		if (factura.getAlmacen()!=null) cargarAlmacenlbl(factura.getAlmacen());
		else cargarAlmacenlbl(null);
		if (factura.getCliente()!=null) cargarClientelbl(factura.getCliente());
		else cargarClientelbl(null);
		if (factura.getVendedor()!=null) cargarVendedorlbl(factura.getVendedor());
		else cargarVendedorlbl(null);
		
		if (factura !=null)
		{
			factura.setFecha(new Date());
			cargarFacturalbl(factura);
		}
		else cargarFacturalbl(null);
		
		if (detalleFacturas.size()>=1){
		activarBinDetalleFactura(detalleFacturas);
		}

		if(factura.getAlmacen() != null && 
			factura.getVendedor() != null &&
			factura.getCliente() != null &&
			detalleFacturas.isEmpty()==false)
		{
			vista.getBtnGuardar().setBackground(Color.GREEN);;
		}
		else vista.getBtnGuardar().setBackground(Color.DARK_GRAY);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked"})
	public void activarBinDetalleFactura(List<DetalleFactura> detalleFacturas){
		
		vista.getScrollPane().setViewportView(vista.getTable());
		
		binDetalleFactura = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,
    			detalleFacturas,vista.getTable());
		
		BeanProperty codigoProducto = BeanProperty.create("codigoStr");
		BeanProperty descripcionProducto = BeanProperty.create("descripcion");
		BeanProperty cantidadxEmpaque = BeanProperty.create("unidadesStr");
	    BeanProperty cantidad = BeanProperty.create("cantidad");
	    BeanProperty precio = BeanProperty.create("precio");
	   // BeanProperty descuento = BeanProperty.create("descuentoStr");
	    BeanProperty iva = BeanProperty.create("ivaStr");
	    BeanProperty total = BeanProperty.create("total");
	    
	    binDetalleFactura.addColumnBinding(codigoProducto).setColumnClass(String.class).setColumnName("CODIGO");
	    binDetalleFactura.addColumnBinding(descripcionProducto).setColumnClass(String.class).setColumnName("DESCRIPCION");
	    binDetalleFactura.addColumnBinding(cantidadxEmpaque).setColumnClass(String.class).setColumnName("UNIDAD");
	    binDetalleFactura.addColumnBinding(cantidad).setColumnClass(String.class).setColumnName("CANTIDAD");
	    binDetalleFactura.addColumnBinding(precio).setColumnClass(String.class).setColumnName("PRECIO");
	    //binDetalleFactura.addColumnBinding(descuento).setColumnClass(String.class).setColumnName("% DESC");
	    binDetalleFactura.addColumnBinding(total).setColumnClass(String.class).setColumnName("TOTAL");
	    binDetalleFactura.addColumnBinding(iva).setColumnClass(String.class).setColumnName("% I.V.A. ");

	    binDetalleFactura.bind();

	}
	
	public void cargarProducto(ProductoAlmacen producto){
		//aca se desarrollara el proceso que carga el detalle el producto
		
	}
	
	public Factura getFactura() {
		return factura;
	}

	public Cliente getCliente() {
		return factura.getCliente();
	}

	public void setCliente(Cliente cliente) throws Exception {
		this.factura.setCliente(cliente);
		actualizarVista();
	}

	public Usuario getVendedor() {
		return factura.getVendedor();
	}

	public void setVendedor(Usuario vendedor) throws Exception {
		this.factura.setVendedor(vendedor);;
		actualizarVista();
	}

	public Almacen getAlmacen() {
		return factura.getAlmacen();
	}

	public void setAlmacen(Almacen almacen) throws Exception {
		this.factura.setAlmacen(almacen);
		actualizarVista();
	}

	public List<DetalleFactura> getDetalleFactura() {
		return detalleFacturas;
	}
	public void setDetalleFacturas(List<DetalleFactura> detalleFacturas){
		this.detalleFacturas = detalleFacturas;
	}
	
	public void setProductoAlDetalleFactura(ProductoAlmacen producto) throws Exception {
		
		try {
		
			if(producto == null) throw new Exception("Producto");
		
			DetalleFactura detalleFactura = new DetalleFactura();
			detalleFactura.setFactura(getFactura());
			detalleFactura.setEmpaqueProducto(producto.getEmpaqueProducto());
			
			Integer cantidad = new Integer(0);
			
				cantidad = new Integer(JOptionPane.showInputDialog(vista, "Ingrese la Cantidad de Producto a Pedir \n"
						+ "Numero en Existencia = "+producto.getExistencia()));
		
				if (cantidad <= 0 ) 
				{
					throw new Exception("Cantidad<");
				}
				else if (cantidad > producto.getExistencia())
				{
					throw new Exception("Cantidad>Existencia");
				}
				detalleFactura.setCantidad(cantidad);
			
				
			detalleFactura.setPrecio(producto.getEmpaqueProducto().getPrecioEmpaque());
			detalleFactura.setDescuento(producto.getEmpaqueProducto().getProducto().getDescuento());
			detalleFactura.setTotal(detalleFactura.getPrecio()*detalleFactura.getCantidad());
			
			if(producto.getEmpaqueProducto().getProducto().getIva()==true)
			{
				detalleFactura.setIva(0.000);
			}
			else if(producto.getEmpaqueProducto().getProducto().getIva()==false)
			{
				detalleFactura.setIva(12.000);
			}
			
			for(DetalleFactura detalleFactura2 : detalleFacturas)
			{
				if(detalleFactura2.getEmpaqueProducto().getId().equals(detalleFactura.getEmpaqueProducto().getId()))
				{
					detalleFacturas.remove(detalleFactura2);
					break;
				}
			}			
		detalleFacturas.add(detalleFactura);
		
		actualizarVista();
	
		} catch (Exception exe) {
			// TODO: handle exception
			switch (exe.getMessage()) {
			case "Cantidad<": JOptionPane.showMessageDialog(vista,"Asigne una Cantidad Mayo O igual a 1");
			break;
			case "Cantidad>Existencia": JOptionPane.showMessageDialog(vista,"Asigne una Cantidad Menor O igual al Numero en Existencia");
			break;
			case "Producto": actualizarVista();
			break;
 
			case"null":
			break;
			
			default:
				exe.printStackTrace();
 
				break;
			}
		}
	}

	public void setVista(PedidosUI vista) {
		this.vista = vista;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	
	public void cargarVistaPrevia(Almacen almacen,Usuario vendedor,Cliente cliente,
			Factura factura,List<DetalleFactura> detalleFacturas){
		
		try {
			setAlmacen(almacen);
			setVendedor(vendedor);
			setCliente(cliente);
			setFactura(factura);
			setDetalleFacturas(detalleFacturas);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void actualizarContPedido(Object objetContCachePresente,Object objetClassVista){
		try {
			switch (objetContCachePresente.getClass().getName()) {
			case "santaclara.controlador.ContAlmacenes":
				if (objetClassVista == null)setAlmacen(null);
				else setAlmacen((Almacen)objetClassVista);
				break;
			case "santaclara.controlador.ContClientes":
				if (objetClassVista == null)	setCliente(null);
				else 	setCliente((Cliente)objetClassVista);
				break;
			case "santaclara.controlador.ContUsuarios":
				if (objetClassVista == null) setVendedor(null);
				else
				{
					Usuario vendedor = (Usuario)objetClassVista;
					setVendedor(vendedor); 
				}
				break;
			case "santaclara.controlador.ContProductoAlmacenes":
				if (objetClassVista == null) setProductoAlDetalleFactura(null);
				else
				{
					ProductoAlmacen productoAlmacen = (ProductoAlmacen)objetClassVista; 
					setProductoAlDetalleFactura(productoAlmacen);
				}
				break;

			default:
				break;
		}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	} 

	public ActionListener actionLimpiar() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				factura.setAlmacen(null);
				factura.setCliente(null);
				factura.setVendedor(null);
				
				limpiarlbAlmacen();
				limpiarlblCliente();
				limpiarlblFactura();
				limpiarlblVendedor();
				detalleFacturas.clear();
				try {
					actualizarVista();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
	} 
	@Override
	public String asociar() {
		// TODO Auto-generated method stub
		return inicio;
	}

}