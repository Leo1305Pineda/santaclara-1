package santaclara.controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import santaclara.Servicio.ServicioConcesionario;
import santaclara.Servicio.ServicioDetalleFactura;
import santaclara.Servicio.ServicioDomicilioComercio;
import santaclara.Servicio.ServicioFactura;
import santaclara.Servicio.ServicioProductoAlmacen;
import santaclara.Servicio.ServicioSalp;
import santaclara.Servicio.ServicioVendedor;
import santaclara.modelo.Almacen;
import santaclara.modelo.Cliente;
import santaclara.modelo.Concesionario;
import santaclara.modelo.DetalleFactura;
import santaclara.modelo.DomicilioComercio;
import santaclara.modelo.ProductoAlmacen;
import santaclara.modelo.Factura;
import santaclara.modelo.Usuario;
import santaclara.modelo.Vendedor;
import santaclara.vista.PedidosUI;

public class ContPedidos extends ContGeneral implements IContGeneral{
	
	private static final double IVA = 0.12; 
	private PedidosUI vista;
	private Factura factura = new Factura(); 
	private List<DetalleFactura> detalleFacturas  = new ArrayList<DetalleFactura>();
	@SuppressWarnings("rawtypes")
	private JTableBinding binDetalleFactura;

	public ContPedidos(Cliente cliente,
			Usuario vendedor, Almacen almacen) {
		this.factura.setCliente(cliente);
		this.factura.setVendedor(vendedor);
		this.factura.setAlmacen(almacen);
	}

	public ContPedidos(ContPrincipal contPrincipal) throws IOException {
		// TODO Auto-generated constructor stub
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
	
	public void cargarClientelbl(Cliente cliente){
		
		if (cliente.getId()==null)
		{
			limpiarlblCliente();
		}
		else
		{
			vista.getLblRif().setText("Rif: "+cliente.getRif());
			vista.getLblRazonSocial().setText("Razon Social: "+cliente.getRazonsocial());
			vista.getLblTelefono().setText("Telefono: "+cliente.getTelefono());
			vista.getLblDireccion().setText("-> "+cliente.getDireccion());
			vista.getLblRuta().setText("Ruta: "+cliente.getRutaNombre());
		}
	}
	
	public void limpiarlblVendedor(){
		vista.getLblNombreVendedor().setText("");
	}
	
	public void cargarVendedorlbl(Usuario vendedor){
		if (vendedor.getId() == null )
		{
			limpiarlblVendedor();
		}
		else
		{
			vista.getLblNombreVendedor().setText(vendedor.getId()+" --> ".concat(vendedor.getNombre()));
		}
	}
	
	public void limpiarlbAlmacen(){
	
			vista.getLblAlmacen().setText("");
	}
	
	public void cargarAlmacenlbl(Almacen almacen){
		if (almacen.getId() == null )
		{
			limpiarlbAlmacen();
		}
		else
			{
				vista.getLblAlmacen().setText(almacen.getId()+" --> ".concat(almacen.getUbicacion()));
			}
	}
	
	public void limpiarlblFactura(){
		vista.getLblNumeroPedido().setText("Numero:      AutoGenerado");
		vista.getLblFecha().setText("Fecha:       "+new Date());
		vista.getLblCondicion().setText("Condicion: ");
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
		vista.getLblNumeroPedido().setText("Numero:      AutoGenerado");
	}
	else
	{
		vista.getLblNumeroPedido().setText("Numero:"+factura.getId().toString());
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
	
	totalAPagar = (subTotalExento + subTotalGravado) * desc + 
					(subTotalExento + subTotalGravado) * IVA + 
						(subTotalExento + subTotalGravado);
	

	vista.getLblSubtotalExento().setText("SUBTOTAL EXENTO:    "+subTotalExento);
	vista.getLblSubtotalGravado().setText("SUBTOTAL GRAVADO:  "+subTotalGravado);
	vista.getLblDesc().setText(desc+"% DESCUENTO.:  "+((subTotalExento + subTotalGravado) * desc));
	vista.getLblIvaSobreBs().setText("I.V.A. SOBRE Bs.    "+(subTotalExento + subTotalGravado));
	vista.getLblIva().setText("I.V.A. 12.00  %     "+(subTotalExento + subTotalGravado) * IVA);
	vista.getLblTotalAPagar().setText("TOTAL A PAGAR:      "+totalAPagar);
	
	factura.setTotal(totalAPagar);
	factura.setIva((subTotalExento + subTotalGravado) * IVA);
	factura.setSaldo(subTotalExento);
	factura.setDescuento(desc);
	
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void activarBinding(List<DetalleFactura> detalleFacturas ){
		vista.getScrollPane().setViewportView(vista.getTable());
		JTableBinding binVistas = vista.getBinPedidos();
		
		 binVistas= SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,
    		detalleFacturas,vista.getTable());
	  
		BeanProperty nombreProducto  = BeanProperty.create("producto.nombre");
		BeanProperty cantidad  = BeanProperty.create("cantidad");
		BeanProperty precio = BeanProperty.create("precio");
		BeanProperty iva = BeanProperty.create("iva");
		BeanProperty total = BeanProperty.create("total");
	    
	    binVistas.addColumnBinding(nombreProducto).setColumnClass(String.class).setColumnName("Producto");
	    binVistas.addColumnBinding(cantidad).setColumnClass(String.class).setColumnName("Cantidad");
	    binVistas.addColumnBinding(precio).setColumnClass(String.class).setColumnName("precio");
	
	    binVistas.addColumnBinding(iva).setColumnClass(String.class).setColumnName("iva");
	    binVistas.addColumnBinding(total).setColumnClass(String.class).setColumnName("total");
	    
	    binVistas.bind();

	}
	public void cargarDetalleFarcturalbl() throws NumberFormatException, IOException{
		
		activarBinding(new ServicioDetalleFactura().getDetalleFacturas(this.factura));
		
		}
	
	public ActionListener actionCliente(){
		return new ActionListener() {
			
			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					ContClientes contClientes = new  ContClientes(getContPrincipal());
					contClientes.getVista().getComboRutas().setEnabled(true);
					if(factura.getVendedor()!=null)
					{
						if((new ServicioVendedor().getVendedor(factura.getVendedor().getId()))!=null)
						{
							Vendedor vendedor1 = new ServicioVendedor().getVendedor(factura.getVendedor().getId());
							contClientes.getVista().getCmbTipoCliente().setModel(
									new DefaultComboBoxModel(
											new String[] {"Salp"}));
							contClientes.getVista().getCmbTipoCliente().setEnabled(true);
							contClientes.getVista().setRutas(vendedor1.getRutas());
							contClientes.getVista().activarBindingSalp(
									new ServicioSalp().getSalps(vendedor1.getRutas()));
						}
						else if((new ServicioConcesionario().getConcesionario(factura.getVendedor().getId()))!=null)
						{
							Concesionario vendedor1 = new ServicioConcesionario().getConcesionario(factura.getVendedor().getId());
							contClientes.getVista().getCmbTipoCliente().setModel(
									new DefaultComboBoxModel(
											new String[] {"Domicilio","Comercial"}));
							contClientes.getVista().getCmbTipoCliente().setEnabled(true);
							contClientes.getVista().getRutas().add(vendedor1.getRuta());
							List<DomicilioComercio> domicilioComercios =new ServicioDomicilioComercio().getDomicilioComercios(vendedor1.getRuta());
							if (domicilioComercios.isEmpty())
							{
								contClientes.getVista().getComboRutas().setSelectedItem(vendedor1.getRuta());
								contClientes.getVista().activarBindingDomicilioComercios(domicilioComercios);
								throw new Exception("ruta null");
							}
							else
							{
								contClientes.getVista().activarBindingDomicilioComercios(domicilioComercios);
							}
									
						}
					}
				} catch (Exception exe) {
					// TODO Auto-generated catch block
					switch (exe.getMessage()) {
					case "ruta null":JOptionPane.showMessageDialog(vista, "No existen Cliente en la ruta del Vendedor \n "
							+ "Cree un nuevo Cliente", "..:: Aviso ::.." ,2, new ImageIcon("img/gestion/group.png"));
						break;

					default:
						break;
					}
					
				}
			}
		};
	}
	
	public ActionListener actionVendedor(){
		return new ActionListener() {
			
			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					ContUsuarios contUsuarios = new ContUsuarios(getContPrincipal());
					if(factura.getCliente() !=null)
					{
						if ((new ServicioSalp().getSalp(factura.getCliente().getId()))!=null)
						{
							contUsuarios.getVistaUsuario().getCmbTipoUsuario().setModel(
									new DefaultComboBoxModel(
											new String[] {"Vendedor"}));
							contUsuarios.getVistaUsuario().getCmbTipoUsuario().setEnabled(true);;
							contUsuarios.getVistaUsuario().activarBindingVendedores(
									new ServicioVendedor().getVendedores(factura.getCliente().getRuta().getId()));
						}
						else if (new ServicioDomicilioComercio().getdDomicilioComercio(factura.getCliente().getId())!=null)
						{
							contUsuarios.getVistaUsuario().getCmbTipoUsuario().setModel(
									new DefaultComboBoxModel(
											new String[] {"Concesionario"}));
							contUsuarios.getVistaUsuario().getCmbTipoUsuario().setEnabled(true);
							contUsuarios.getVistaUsuario().activarBindingConcesionarios(
									new ServicioConcesionario().getConcecionarios(factura.getCliente().getRuta().getId()));
						}					
					}
					else
					{
						contUsuarios.getVistaUsuario().getCmbTipoUsuario().setModel(
								new DefaultComboBoxModel(
										new String[] {"Vendedor", "Concesionario"}));
						contUsuarios.getVistaUsuario().activarBindingVendedores(
								new ServicioVendedor().getVendedores());
					}
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		};
	}
	
	public ActionListener actionAlmacen(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					new ContAlmacenes(getContPrincipal());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
							factura.setEstado(null);
							if(factura.getAlmacen() != null && 
									factura.getVendedor() != null &&
									factura.getCliente() != null &&
									detalleFacturas.isEmpty()==false)
							{
								new ServicioFactura().guardar(factura);//guarda la factura
								
								new ServicioDetalleFactura().guardar(detalleFacturas);//guarda su detalle
								detalleFacturas = new ServicioDetalleFactura().getDetalleFacturas(factura);
								
								if (factura.getId()==null)
								{
									factura.setId(new ServicioFactura().ultimaFactura()+1);  
								}
								vista.getLblNumeroPedido().setText("Numero: "+factura.getId());	
								
								JOptionPane.showMessageDialog(vista,"Guardado el Pedido Exitosamente");
								actualizarVista();
							}
							
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			}
		};	
	}
	protected void validarFactura() throws Exception {
		// TODO Auto-generated method stub
		try {
				if (factura.getAlmacen() == null) {
					throw new Exception("Almacen");
				}
				if (factura.getVendedor() == null)
				{
					throw new Exception("Vendedor");
				}
				if (factura.getCliente() == null){
					throw new Exception("Cliente");
				}
				if (detalleFacturas.isEmpty()){
					throw new Exception("Detalle");
				}
				

			} catch (Exception exe) {
				// TODO Auto-generated catch block
				Integer opt = new Integer(	JOptionPane.showConfirmDialog(vista,"Cargar la Informacion del "+exe.getMessage()));
				if (opt==0)
				{
					switch (exe.getMessage()) {
					case "Almacen":vista.getBtnAlmacen().doClick();
						break;
					case "Cliente":vista.getBtnCliente().doClick();
						break;
					case "Vendedor":vista.getBtnVendedor().doClick();
						break;
					case "Detalle":vista.getBtnProducto().doClick();
					break;

					default:
						break;
					}

				}

				}
					}

	public ActionListener actionBuscarPedido(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				 try {
					validarFactura();
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
					if(factura.getAlmacen() != null && 
							factura.getVendedor() != null &&
							factura.getCliente() != null &&
							detalleFacturas.isEmpty()==false)
					{
						Factura facturaAux =new ServicioFactura().getFactura(factura.getId()); 
						//Existe la Factura 
						if(facturaAux!=null)
						{	//y ademas no se a Facturado estado null o en Pedido
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
								
								actualizarVista();
								JOptionPane.showMessageDialog(vista,"Generada la Factura Exitosamente");
							}
							else
							{
								Integer opt = new Integer(JOptionPane.showConfirmDialog(vista,"La Factura ya Existe \n "
										+ "no se puede Eliminar ni Modificar desea Generar una nueva Factura \n"+
										"Crear una Nueva Factura \n "	,"Seleccione la Opcion",2));
								if (opt.equals(0))
								{
									factura.setId(null);
									new ServicioFactura().guardar(factura);
									factura.setId(new ServicioFactura().ultimaFactura()+1);
				
									for(DetalleFactura detalleFactura : detalleFacturas)
									{ 
										detalleFactura.getFactura().setId(factura.getId());
									}
									new ServicioDetalleFactura().guardar(detalleFacturas);
									
									JOptionPane.showMessageDialog(vista,"Generada la Nueva Factura Exitosamente");
								}
							}
						}
						else throw new Exception("Guardar Pedido");
					}
				} catch (Exception exe) {
					// TODO Auto-generated catch block
					switch (exe.getMessage()) {
					case "Guardar Pedido":
						Integer opt = new Integer(JOptionPane.showConfirmDialog(vista, exe.getMessage(),"Seleccione la Opcion",2));
						if(opt==0){
					          vista.getBtnGuardar().doClick();	
					          vista.getBtnGuardarFactura().setBackground(Color.GREEN);
						}
						break;
					case "Opcion invalida": 
						Integer opt2 = new Integer(JOptionPane.showConfirmDialog(vista, exe.getMessage().concat(
								"Desea Continuar"),"Seleccione la Opcion",2));
						if(opt2==0){
							vista.getBtnGuardarFactura().doClick();
						}
						break;
					case "Cliente Salp":
						Integer opt3 = new Integer(JOptionPane.showConfirmDialog(vista,"Los Credito se le asignan solo a los "+ exe.getMessage().concat(
								"Desea Continuar"),"Seleccione la Opcion",2));
						if(opt3==0){
							vista.getBtnGuardarFactura().doClick();
						}
						break;
					case "noCredito":
						Integer opt4 = new Integer(JOptionPane.showConfirmDialog(vista,"el Cliente Salp no puede obtar por el credito".concat(
								"Desea Continuar"),"Seleccione la Opcion",2));
						if(opt4==0){
							vista.getBtnGuardarFactura().doClick();
						}
						break;
					default:
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
				qutarVista();
			}
		};
		
	}
	
	public ActionListener actionProducto(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					
					if(factura.getAlmacen() == null ) 
					{
						throw new Exception("Almacen");
					}
					else 
					{
						ContProductoAlmacenes contProductoAlmacenes = new ContProductoAlmacenes(getContPrincipal());
						contProductoAlmacenes.getVista().activarBinding(
								new ServicioProductoAlmacen().getProductoAlmacenes(factura.getAlmacen().getId()));
					}
					
				} catch (Exception exe) {
					// TODO Auto-generated catch block
					Integer opt = new Integer(	JOptionPane.showConfirmDialog(vista,"Cargar la Informacion del "+exe.getMessage()));
					if (opt==0)
					{
						switch (exe.getMessage()) {
						case "Almacen":vista.getBtnAlmacen().doClick();
							break;
						default:
							break;
						}

					}
					
				}
			}
		};
	}
	
	public ActionListener actionQuitarProductoDetalle(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					if(vista.getTable().getSelectedRow()>=0 && !detalleFacturas.isEmpty())
					{
						for(DetalleFactura detalleFactura1 :  detalleFacturas){
							if (detalleFactura1.getProducto().getId().equals(
									new Integer(vista.getTable().getValueAt(vista.getTable().getSelectedRow(),0).toString().trim())))
							{
								detalleFacturas.remove(detalleFactura1);
								if(factura.getId()!=null)
								{
									new ServicioDetalleFactura().eliminar(detalleFactura1);
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
			
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		
			}
		};
	}
	
	public ActionListener actionNuevo(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				limpiarlbAlmacen();
				limpiarlblCliente();
				limpiarlblFactura();
				limpiarlblVendedor();
				detalleFacturas.clear();
			}
		};
	}

	public void actualizarVista() throws IOException{
		if (vista != null)dibujar(vista,this);//Actualiza la vista
		if (factura.getAlmacen()!=null) cargarAlmacenlbl(factura.getAlmacen());
		if (factura.getCliente()!=null) cargarClientelbl(factura.getCliente());
		if (factura.getVendedor()!=null) cargarVendedorlbl(factura.getVendedor());
		
		if (factura !=null)
		{
			factura.setFecha(new Date());
			cargarFacturalbl(factura);
		}
		
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
		else vista.getBtnGuardar().setBackground(Color.gray);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked"})
	public void activarBinDetalleFactura(List<DetalleFactura> detalleFacturas){
		
		vista.getScrollPane().setViewportView(vista.getTable());
		
		binDetalleFactura = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,
    			detalleFacturas,vista.getTable());
		
		BeanProperty codigoProducto = BeanProperty.create("producto.id");
		BeanProperty descripcionProducto = BeanProperty.create("producto.descripcion");
	    BeanProperty cantidad = BeanProperty.create("cantidad");
	    BeanProperty precio = BeanProperty.create("precio");
	    BeanProperty descuento = BeanProperty.create("descuentoStr");
	    BeanProperty iva = BeanProperty.create("iva");
	    BeanProperty total = BeanProperty.create("total");
	    
	    binDetalleFactura.addColumnBinding(codigoProducto).setColumnClass(String.class).setColumnName("Codigo");
	    binDetalleFactura.addColumnBinding(descripcionProducto).setColumnClass(String.class).setColumnName("Descripcion");
	    binDetalleFactura.addColumnBinding(cantidad).setColumnClass(String.class).setColumnName("Cantidad");
	    binDetalleFactura.addColumnBinding(precio).setColumnClass(String.class).setColumnName("precio");
	    binDetalleFactura.addColumnBinding(descuento).setColumnClass(String.class).setColumnName("% Desc");
	    binDetalleFactura.addColumnBinding(total).setColumnClass(String.class).setColumnName("total");
	    binDetalleFactura.addColumnBinding(iva).setColumnClass(String.class).setColumnName("%iva");

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

	public void setCliente(Cliente cliente) throws IOException {
		this.factura.setCliente(cliente);
		actualizarVista();
	}

	public Usuario getVendedor() {
		return factura.getVendedor();
	}

	public void setVendedor(Usuario vendedor) throws IOException {
		this.factura.setVendedor(vendedor);;
		actualizarVista();
	}

	public Almacen getAlmacen() {
		return factura.getAlmacen();
	}

	public void setAlmacen(Almacen almacen) throws IOException {
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
		
		if(producto != null)
		{
			DetalleFactura detalleFactura = new DetalleFactura();
			detalleFactura.setFactura(getFactura());
			detalleFactura.setProducto(producto.getEmpaqueProducto().getProducto());
			
			Integer cantidad = new Integer(0);
			try {
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
				
			} catch (Exception exe) {
				// TODO: handle exception
				switch (exe.getMessage()) {
				case "Cantidad<": JOptionPane.showMessageDialog(vista,"Asigne una Cantidad Mayo O igual a 1");
					break;
				case "Cantidad>Existencia": JOptionPane.showMessageDialog(vista,"Asigne una Cantidad Menor O igual al Numero en Existencia");
					break;

				default:
					exe.printStackTrace();
					JOptionPane.showMessageDialog(vista,exe.getMessage());
					break;
				}
				
			}
			detalleFactura.setPrecio(producto.getEmpaqueProducto().getPrecioEmpaque());
			detalleFactura.setDescuento(producto.getEmpaqueProducto().getProducto().getDescuento());
			detalleFactura.setTotal(detalleFactura.getPrecio()*cantidad);
			
			if(producto.getEmpaqueProducto().getProducto().getIva()==true)
			{
				detalleFactura.setIva(0.000);
			}
			else if(producto.getEmpaqueProducto().getProducto().getIva()==false)
			{
				detalleFactura.setIva(12.000);
			}
			
			Boolean enc = new Boolean(false);
			for(DetalleFactura detalleFactura2 : detalleFacturas)
			{
				if(detalleFactura2.getProducto().getId().equals(detalleFactura.getProducto().getId()))
				{
					detalleFactura2.setCantidad(detalleFactura.getCantidad());
					enc=true;
					break;
				}
			}
			if(enc==false)
			{
				this.detalleFacturas.add(detalleFactura);
			}
		}
		actualizarVista();
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
		} catch (IOException e) {
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
}