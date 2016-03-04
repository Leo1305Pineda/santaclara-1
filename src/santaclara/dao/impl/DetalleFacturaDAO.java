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
import santaclara.dbPostgresql.modelo.PostgreSql;
import santaclara.modelo.DetalleFactura;

public class DetalleFacturaDAO extends GenericoDAO implements IDetalleFacturaDAO{
	
	@Override
	public List<DetalleFactura> getDetalles() throws Exception {
		// TODO Auto-generated method stub
		List<DetalleFactura> detalleFacturas = new ArrayList<DetalleFactura>();
		
		ResultSet rSet = new PostgreSql().getSelect(
				" SELECT  idfactura, idempaqueproducto,"
				+ " cantidad, precio, descuento, iva, total "
				+ " FROM detalleFacturas Order by idfactura;"); 
		
		if(rSet==null) return null;
		
			while(rSet.next())detalleFacturas.add(
					new DetalleFactura(
							new FacturaDAO().getFactura(rSet.getInt("idfactura")),
							new EmpaqueProductoDAO().getEmpaqueProducto(rSet.getInt("idempaqueproducto")),
							rSet.getInt("cantidad"), 
							rSet.getDouble("precio"), 
							rSet.getDouble("descuento"),
							rSet.getDouble("iva"),
							rSet.getDouble("total"))); 
		return detalleFacturas;
	}
	
	@Override
	public void guardar(List<DetalleFactura> detalleFacturas) throws Exception {
		// TODO Auto-generated method stub
			if (!detalleFacturas.isEmpty()){
				for(DetalleFactura detalleFactura: detalleFacturas)
				{	
					new PostgreSql().ejecutar( 
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
		if(detalleFactura!=null) new PostgreSql().ejecutar(
				 "DELETE FROM detallefacturas "
				 +" WHERE          "
				 +" idfactura   = "+detalleFactura.getFactura().getId()					
				 +" AND               "
				 +" idempaqueproducto = "+detalleFactura.getEmpaqueProducto().getId() 	+ " ; ");
	}

	@Override
	public DetalleFactura getDetalleFactura(Integer idFactura,Integer idProducto) throws Exception {
		// TODO Auto-generated method stub
		
		ResultSet rSet = new PostgreSql().getSelect(
				" SELECT  idfactura, idempaqueproducto,"
				+ " cantidad, precio, descuento, iva, total "
				+ " FROM detalleFacturas "
				+ " WHERE idfactura = " +idFactura + ""
				+ " AND "
				+ " idempaqueproducto = " +idProducto
				+ ";"); 
		
		if(rSet==null) return null;
		rSet.next();
		return	new DetalleFactura(
							new FacturaDAO().getFactura(rSet.getInt("idfactura")),
							new EmpaqueProductoDAO().getEmpaqueProducto(rSet.getInt("idempaqueproducto")),
							rSet.getInt("cantidad"), 
							rSet.getDouble("precio"), 
							rSet.getDouble("descuento"),
							rSet.getDouble("iva"),
							rSet.getDouble("total")); 		
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
		
		ResultSet rSet = new PostgreSql().getSelect(
				"SELECT d.* FROM detalleFacturas d , facturas f "
				+ " WHERE d.idfactura = f.id AND f.estado = ' " + condicion +"' "
				+ ";"); 
		
		if(rSet==null) return null;
		
			while(rSet.next())detalleFacturas.add(
					new DetalleFactura(
							new FacturaDAO().getFactura(rSet.getInt("idfactura")),
							new EmpaqueProductoDAO().getEmpaqueProducto(rSet.getInt("idempaqueproducto")),
							rSet.getInt("cantidad"), 
							rSet.getDouble("precio"), 
							rSet.getDouble("descuento"),
							rSet.getDouble("iva"),
							rSet.getDouble("total"))); 
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
