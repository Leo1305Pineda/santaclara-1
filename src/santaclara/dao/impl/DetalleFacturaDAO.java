/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import santaclara.dao.IDetalleFacturaDAO;
import santaclara.modelo.Almacen;
import santaclara.modelo.Capacidad;
import santaclara.modelo.Cliente;
import santaclara.modelo.DetalleFactura;
import santaclara.modelo.EmpaqueProducto;
import santaclara.modelo.Factura;
import santaclara.modelo.Presentacion;
import santaclara.modelo.Producto;
import santaclara.modelo.Ruta;
import santaclara.modelo.Sabor;
import santaclara.modelo.Usuario;
import santaclara.modelo.Zona;

public class DetalleFacturaDAO extends GenericoDAO implements IDetalleFacturaDAO{
	
	public DetalleFacturaDAO() {
		super();
		// TODO Auto-generated constructor stub
		try {
			activarConexionBaseDato();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<DetalleFactura> getDetalles() throws Exception {
		// TODO Auto-generated method stub
		List<DetalleFactura> detalleFacturas = new ArrayList<DetalleFactura>();
		
		ResultSet rSet = getConexion().getSelect(
				" SELECT  "
				+ " d.idfactura, d.idempaqueproducto, d.cantidad as cantidadvendida, d.precio as precioempaque, d.descuento as descuentoempaque, d.iva as ivaempaque, d.total,f.estado, f.fecha, f.idcliente, f.idvendedor, f.idalmacen, f.subtotalexento, f.subtotalgravado, f.descuento, f.ivasobrebs, f.iva as ivafactura, f.totalapagar, "
				+ " e.idproducto, e.cantidad as cantidadproducto,p.nombre as nombreProducto, p.precio as precioproducto, p.descuento as descuentoproducto, p.estadoiva, p.idcapacidad, p.idpresentacion, p.idsabor, "
				+ " pr.material, "
				+ " ca.volumen, "
				+ " sa.sabor, "
				+ " z.descripcion as descripcionzona, "
				+ " r.nombre as nombreruta, r.idzona, "
				+ " c.id, c.rif, c.razonsocial, c.direccion, c.telefono, c.idruta, "
				+ " u.username, u.cedula, u.nombre as nombreusuario, u.contrasena, "
				+ " a.ubicacion "
				+ " FROM detallefacturas d, empaqueproductos e, facturas f,clientes c,usuarios u,almacenes a,productos p, presentaciones pr, capacidades ca, sabores sa, rutas r,zonas z "
				+ " WHERE d.idfactura = f.id "
				+ " AND d.idempaqueproducto = e.id  "
				+ " AND e.idproducto = p.id  "
				+ " AND p.idpresentacion = pr.id  "
				+ " AND p.idcapacidad = ca.id  "
				+ " AND p.idsabor = sa.id  "
				+ " AND f.idcliente = c.id "
				+ " AND c.idruta = r.id "
				+ " AND r.idzona = z.id "
				+ " AND f.idvendedor = u.id "
				+ " AND f.idalmacen = a.id "
				+ ";"); 
		if(rSet == null || rSet.getFetchSize()!=0) return null;
		
		while(rSet.next())
		{
			Boolean estado = null;
			 switch (rSet.getString("estado")) {
			 case "Facturado":estado = true;	
			 break;
			 case "Pendiente":estado = false;
			 break;
			 case "Pedido":estado = null;
			 break;
			 default:
			 break;
			 }
			 Boolean estadoiva = false;
				if(rSet.getString("estadoiva").equals("gravado")) estadoiva = true;
			
			Sabor sabor = new Sabor(rSet.getInt("idsabor"), rSet.getString("sabor")); 
			
			Capacidad capacidad = new Capacidad(rSet.getInt("idcapacidad"), rSet.getDouble("volumen"));
			
			Presentacion presentacion = new Presentacion(rSet.getInt("idpresentacion"), rSet.getString("material"));
			
			Producto producto = new Producto(rSet.getInt("idproducto"), rSet.getString("nombreproducto"), rSet.getDouble("precioproducto"), rSet.getDouble("descuentoproducto"), estadoiva, capacidad, presentacion, sabor);
			
			EmpaqueProducto empaqueProducto = new EmpaqueProducto(rSet.getInt("idempaqueProducto"), producto, rSet.getInt("cantidadproducto"));
			
			Almacen almacen = new Almacen(rSet.getInt("idalmacen"), rSet.getString("ubicacion"));
			
			Usuario vendedor = new Usuario(rSet.getInt("idvendedor"), rSet.getString("username"), rSet.getString("cedula"), rSet.getString("nombreusuario"), rSet.getString("contrasena"));
			
			Zona zona = new Zona(rSet.getInt("idzona"), rSet.getString("descripcionzona"));
			
			Ruta ruta = new Ruta(rSet.getInt("idruta"), rSet.getString("nombreruta"), zona);
			
			Cliente cliente = new Cliente(rSet.getInt("idcliente"), rSet.getString("rif"), rSet.getString("razonsocial"), rSet.getString("direccion"), rSet.getString("telefono"), ruta);
			
			Factura factura = new Factura(rSet.getInt("idfactura"),rSet.getDate("fecha"), cliente, vendedor, almacen, estado, rSet.getDouble("subTotalExento"), rSet.getDouble("subTotalGravado"),rSet.getDouble("descuentoempaque"), rSet.getDouble("ivaSobreBs"), rSet.getDouble("ivafactura"), rSet.getDouble("totalAPagar"));
			
			DetalleFactura detalleFactura = new DetalleFactura(factura, empaqueProducto, rSet.getInt("cantidadvendida"), rSet.getDouble("precioempaque"), rSet.getDouble("descuentoempaque"),rSet.getDouble("ivaempaque"), rSet.getDouble("total"));
			
			detalleFacturas.add(detalleFactura);			
			
		}
		return detalleFacturas;
	}
	
	@Override
	public void guardar(List<DetalleFactura> detalleFacturas) throws Exception {
		// TODO Auto-generated method stub
			if (!detalleFacturas.isEmpty()){
				for(DetalleFactura detalleFactura: detalleFacturas)
				{	
					getConexion().ejecutar( 
							"BEGIN;"
							+ "DELETE FROM detallefacturas "
							+ "WHERE          "
							+ " idfactura   = "+detalleFactura.getFactura().getId()				+ "   "
							+ "AND               "
							+ "idempaqueproducto = "+detalleFactura.getEmpaqueProducto().getId()+ "  "
							+ ""
							+ "INSERT INTO detallefacturas(idfactura, idempaqueproducto, cantidad, precio, descuento, iva, total) "
							+" VALUES ("
							+" " +detalleFactura.getFactura().getId()	  		+ " , "
							+" " +detalleFactura.getEmpaqueProducto().getId()	+ " , "
							+" " +detalleFactura.getCantidad()     				+ " , "
							+" " +detalleFactura.getPrecio()			 		+ " , "
							+" " +detalleFactura.getDescuento()			 		+ " , "
							+" " +detalleFactura.getIva()				 		+ " , "
							+" " +detalleFactura.getTotal()				 		+ "   "
							+ ");"
							+ "       "
							+ "COMMIT;"
							);	
				}	
			}
		}

	@Override
	public void eliminar(DetalleFactura detalleFactura) throws Exception {
		// TODO Auto-generated method stub
		if(detalleFactura!=null) getConexion().ejecutar(
				 "DELETE FROM detallefacturas "
				 +" WHERE          "
				 +" idfactura   = "+detalleFactura.getFactura().getId()					
				 +" AND               "
				 +" idempaqueproducto = "+detalleFactura.getEmpaqueProducto().getId() 	+ " ; ");
	}

	@Override
	public DetalleFactura getDetalleFactura(Integer idFactura,Integer idProducto) throws Exception {
		// TODO Auto-generated method stub
		for(DetalleFactura detalleFactura : getDetalles())
		{
			if(detalleFactura.getFactura().getId().equals(idFactura)&& 
					detalleFactura.getEmpaqueProducto().getId().equals(idProducto))return detalleFactura;
		}
		 return null;
	}
	
	public List<DetalleFactura> getDetalleFacturados() throws Exception{
		// TODO Auto-generated method stub
		return confGetDetalle("Facturado");
	}

	public List<DetalleFactura> getDetallePedidos() throws Exception {
	// TODO Auto-generated method stub
		return confGetDetalle("Pedido");
	}

	public List<DetalleFactura> getDetallePendientes() throws Exception {
	// TODO Auto-generated method stub
		return confGetDetalle("Pendiente");
	}

	private List<DetalleFactura> confGetDetalle(String condicion) throws Exception{
		// TODO Auto-generated method stub
		List<DetalleFactura> detalleFacturas = new ArrayList<DetalleFactura>();
		for(DetalleFactura detalleFactura : getDetalles())
		{
			if(detalleFactura.getFactura().getCondicionStr().equals(condicion))
				detalleFacturas.add(detalleFactura);
		}
		 return detalleFacturas;
	}
	
	/*
	 private String ruta = "archivos/detalleFacturas.txt";
	private List<Factura> facturas;
	@Override
	public List<DetalleFactura> getDetalles() throws NumberFormatException, Exception {
		// TODO Auto-generated method stub
		facturas = new FacturaDAO().getPedidoFacturados();
		
		List<DetalleFactura> detalleFacturas = new ArrayList<DetalleFactura>();		
		File file = new File(ruta);
 		Scanner scanner = new Scanner(file);
 		String linea = new String();
		while(scanner.hasNext())
		{
			DetalleFactura detalleFactura = new DetalleFactura();
 
			linea = scanner.skip("idFactura:").nextLine().trim();
			
			 if(linea.trim().length() == 0)	detalleFactura.setFactura(null);
			 else	detalleFactura.setFactura(getFactura(new Integer(linea)));
			 
			 linea = scanner.skip("idEmpaqueProducto:").nextLine().trim();
			 if(linea.trim().length() == 0)	detalleFactura.setEmpaqueProducto(null);
			 else	detalleFactura.setEmpaqueProducto(new ServicioEmpaqueProducto().getEmpaqueProducto(new Integer(linea)));
			 
			 detalleFactura.setCantidad(new Integer(scanner.skip("cantidad:").nextLine().trim()));
			 detalleFactura.setPrecio(new Double(scanner.skip("precio:").nextLine().trim()));
			 detalleFactura.setPrecio(new Double(scanner.skip("desc:").nextLine().trim()));
			 detalleFactura.setIva(new Double(scanner.skip("iva:").nextLine().trim()));
			 detalleFactura.setTotal(new Double(scanner.skip("total:").nextLine().trim()));
			 
			 detalleFacturas.add(detalleFactura); 
		}
		scanner.close();
		return detalleFacturas;

	}
	 
	public List<DetalleFactura> getDetalleFacturados() throws Exception{
			// TODO Auto-generated method stub
		
		return confGetDetalle(true);
		}
	
	public List<DetalleFactura> getDetallePedidos() throws Exception {
		// TODO Auto-generated method stub
		
		return confGetDetalle(null);
	}

	public List<DetalleFactura> getDetallePendientes() throws Exception {
		// TODO Auto-generated method stub
		
		return confGetDetalle(false);
	}

	public List<DetalleFactura> confGetDetalle(Boolean condicion) throws Exception{
		
		if(condicion == true) facturas = new FacturaDAO().getPedidoFacturados();
		else if (condicion == false) facturas = new FacturaDAO().getPedidoPendientes();
		else facturas = new FacturaDAO().getPedidos();
		
		List<DetalleFactura> detalleFacturas = new ArrayList<DetalleFactura>();		
		File file = new File(ruta);
 		Scanner scaner = new Scanner(file);
 		String linea = new String();
		while(scaner.hasNext())
		{
			DetalleFactura detalleFactura = new DetalleFactura();
			
			linea = scaner.skip("idFactura:").nextLine().trim();
			 if(linea.trim().length() == 0)	detalleFactura.setFactura(null);
			 else	detalleFactura.setFactura(getFactura(new Integer(linea)));
			
			 if(detalleFactura.getFactura()!=null && detalleFactura.getFactura().getEstado()==condicion)
			 {
				 linea = scaner.skip("idEmpaqueProducto:").nextLine().trim();
				 if(linea.trim().length() == 0) detalleFactura.setEmpaqueProducto(null);
				 else detalleFactura.setEmpaqueProducto(new ServicioEmpaqueProducto().getEmpaqueProducto(new Integer(linea)));

				 detalleFactura.setCantidad(new Integer(scaner.skip("cantidad:").nextLine().trim()));
				 detalleFactura.setPrecio(new Double(scaner.skip("precio:").nextLine().trim()));
				 detalleFactura.setPrecio(new Double(scaner.skip("desc:").nextLine().trim()));
				 detalleFactura.setIva(new Double(scaner.skip("iva:").nextLine().trim()));
				 detalleFactura.setTotal(new Double(scaner.skip("total:").nextLine().trim()));
				 
				 detalleFacturas.add(detalleFactura); 
			 }
			 else for(int i=0 ; i<6 ; i++) scaner.nextLine();//desecha el resto
			 
		}
		scaner.close();
		return detalleFacturas;

	}
	
	public Factura getFactura(Integer id) throws FileNotFoundException {
		// TODO Auto-generated method stub
	
			for(Factura factura1 :facturas)
			{
				if(factura1.getId().equals(id))
				{
					return factura1;
				}
			}
	
		return null;

	}
	
	@Override
	//Guarda y Permite modificarel pedido
	public void guardar(List<DetalleFactura> detalleFacturas) throws Exception {
		// TODO Auto-generated method stub
		
		List<DetalleFactura> detalleFacturasAux = getDetalles();
		
		for(DetalleFactura detalleFactura : detalleFacturas) detalleFacturasAux.add(detalleFactura); 
	
		guardarTodo(detalleFacturasAux);
 
	}

	@Override
	public void eliminar(DetalleFactura detalleFactura) throws Exception {
		// TODO Auto-generated method stub
		List<DetalleFactura> detalleFacturas = getDetallePedidos();
		for(DetalleFactura detalleFactura1 :detalleFacturas)
		{
			if(detalleFactura1.getFactura().getId().equals(detalleFactura.getFactura().getId()) 
						&& detalleFactura1.getEmpaqueProducto().getId().equals(detalleFactura.getEmpaqueProducto().getId()) )
			{
				detalleFacturas.remove(detalleFactura1);
				break;
			}
		}
		guardarTodo(detalleFacturas);
	
	}

	@Override
	public DetalleFactura getDetalleFactura(Integer idFactura,Integer idProducto) throws Exception{
		// TODO Auto-generated method stub
		List<DetalleFactura> detalleFacturas = getDetalles();
		for(DetalleFactura detalleFactura1 :detalleFacturas)
		{
			if(detalleFactura1.getFactura().getId().equals(idFactura) 
						&& detalleFactura1.getEmpaqueProducto().getId().equals(idProducto) )
			{
				return detalleFactura1;
			}
		}
		return null;
	}
	public void guardarTodo(List<DetalleFactura> detalleFacturas ) throws IOException
	{
		FileWriter fw = new FileWriter(ruta);
		for(DetalleFactura detalleFactura :detalleFacturas)
		{
			fw.append("idFactura:"+(detalleFactura.getFactura() == null ? "  ":detalleFactura.getFactura().getId())+"\n");
			fw.append("idEmpaqueProducto:"+(detalleFactura.getEmpaqueProducto() == null ? "  ":detalleFactura.getEmpaqueProducto().getId())+"\n");
			fw.append("cantidad:"+detalleFactura.getCantidad().toString()+"\n");
			fw.append("precio:"+detalleFactura.getPrecio().toString()+"\n");
			fw.append("desc:"+detalleFactura.getPrecio().toString()+"\n");
			fw.append("iva:"+detalleFactura.getIva().toString()+"\n");
			fw.append("total:"+detalleFactura.getTotal().toString()+"\n");
		}
		fw.close();
	}

	 * */	
/*Estructura
 * idFactura:1
idProducto:1
cantidad:73500
precio:100000
desc:0.0
iva:0.12
total:7350000000
*/
}
