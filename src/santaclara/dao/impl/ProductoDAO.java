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

import santaclara.dao.IProductoDAO;
import santaclara.modelo.Capacidad;
import santaclara.modelo.Presentacion;
import santaclara.modelo.Producto;
import santaclara.modelo.Sabor;

public  class ProductoDAO extends GenericoDAO implements IProductoDAO{
	
	public ProductoDAO(){
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
	public List<Producto> getProductos() throws Exception {
		// TODO Auto-generated method stub
		List<Producto> productos = new ArrayList<Producto>();
		
		ResultSet rSet = getConexion().getSelect(
				"SELECT p.id, p.nombre, p.precio, p.descuento, p.estadoiva, "
				+ " p.idcapacidad, p.idpresentacion, p.idsabor, c.volumen, pr.material, s.sabor"
				+ " FROM productos p, capacidades c, presentaciones pr, sabores s "
				+ " WHERE p.idcapacidad = c.id AND p.idpresentacion = pr.id AND p.idsabor = s.id "
				+ " ORDER BY p.id;"); 
		
		if(rSet==null || rSet.getFetchSize()!=0) return null;
		
			while(rSet.next())
			{
				Boolean estadoiva;
				if(rSet.getString("estadoiva").equals("gravado")) estadoiva = true;
				else  estadoiva = false;
				
				Producto producto = new Producto(
						rSet.getInt("id"),
						rSet.getString("nombre"),
						rSet.getDouble("precio"),
						rSet.getDouble("descuento"),
						estadoiva,
						new Capacidad(
								rSet.getInt("idcapacidad"), 
								rSet.getDouble("volumen")),
						new Presentacion( 
								rSet.getInt("idpresentacion"), 
								rSet.getString("material")),
						new Sabor(
								rSet.getInt("idsabor"), 
								rSet.getString("sabor")));
			productos.add(producto);
			}
		return productos;
	}
	
	@Override
	public void guardar(Producto producto) throws Exception {
		// TODO Auto-generated method stub
		if (producto.getId()==null){
			getConexion().ejecutar( 
					"INSERT INTO productos(nombre, precio, descuento, estadoiva, "
				+ "idcapacidad, idpresentacion,idsabor) "
					+ "VALUES ("
					+"'" +producto.getNombre()					+"', "
					+" " +producto.getPrecio()					+" , "
					+" " +producto.getDescuento()				+" , "
					+"'" +producto.getIva2Str()					+"', "
					+" " +producto.getCapacidad().getId()		+" , "
					+" " +producto.getPresentacion().getId()	+" , "
					+" " +producto.getSabor().getId()			+"  "
							+ ");");	
		}
		else{
			getConexion().ejecutar(
					" UPDATE productos SET  "
					+" nombre 			= '" +producto.getNombre() 				+"', "
					+" precio 			=  " +producto.getPrecio()				+" , "
					+" descuento 		=  " +producto.getDescuento()			+" , "
					+" estadoiva 		= '" +producto.getIva2Str() 			+"', "
					+" idcapacidad 		=  " +producto.getCapacidad().getId()	+" , "
					+" idpresentacion 	=  " +producto.getPresentacion().getId()+" , "
					+" idsabor 			=  " +producto.getSabor().getId() 		+"   "
					+" WHERE    id = " +producto.getId()						+"   "
					+ ";");
		}
	}

	@Override
	public void eliminar(Producto producto) throws Exception {
		// TODO Auto-generated method stub
		if(producto!=null) getConexion().ejecutar(
								" DELETE FROM productos "
								+" WHERE id = "+producto.getId() +" ;");
	}

	@Override
	public Producto getProducto(Integer id) throws Exception {
		// TODO Auto-generated method stub
		
		ResultSet rSet = getConexion().getSelect(
				"SELECT p.id, p.nombre, p.precio, p.descuento, p.estadoiva, "
						+ " p.idcapacidad, p.idpresentacion, p.idsabor, c.volumen, pr.material, s.sabor"
						+ " FROM productos p, capacidades c, presentaciones pr, sabores s "
						+ " WHERE p.idcapacidad = c.id AND p.idpresentacion = pr.id AND p.idsabor = s.id "
						+ "AND p.id ="+id+""
						+ ";");

		if(rSet == null || rSet.getFetchSize()!=0) return null;

		rSet.next();
		Boolean estadoiva = false;
		if(rSet.getString("estadoiva").equals("gravado")) estadoiva = true;
		return new Producto(
				rSet.getInt("id"),
				rSet.getString("nombre"),
				rSet.getDouble("precio"),
				rSet.getDouble("descuento"),
				estadoiva,
				new Capacidad(
						rSet.getInt("idcapacidad"), 
						rSet.getDouble("volumen")),
				new Presentacion( 
						rSet.getInt("idpresentacion"), 
						rSet.getString("material")),
				new Sabor(
						rSet.getInt("idsabor"), 
						rSet.getString("sabor")));
		
	}

	
}

	/*private String ruta = "archivos/productos.txt";
	
	@Override
	public List<Producto> getProductos() throws Exception {
		// TODO Auto-generated method stub 
		List<Producto> productos = new ArrayList<Producto>();
		File file = new File(ruta);
		Scanner scanner = new Scanner(file);
		while(scanner.hasNext())
		{
			 Producto producto = new Producto();
			 producto.setId(new Integer(scanner.skip("id:").nextLine().trim()));
			 //Asigna Capacidad
			 CapacidadDAO capacidadDAO = new CapacidadDAO();
			 producto.setCapacidad(
					 capacidadDAO.getCapacidad(
							 new Integer(scanner.skip("idCapacidad:").nextLine().trim())));
			//asigna Presentacion
			 PresentacionDAO presentacionDAO = new PresentacionDAO();
			 producto.setPresentacion(
					 presentacionDAO.getPresentacion(
							 new Integer(scanner.skip("idPresentacion:").nextLine().trim())));
			 //asigna Sabor
			 SaborDAO saborDAO = new SaborDAO();
			 producto.setSabor(
					 saborDAO.getSabor(
							 new Integer(scanner.skip("idSabor:").nextLine().trim())));
			 producto.setNombre(scanner.skip("nombre:").nextLine());
			 producto.setPrecio(new Double(scanner.skip("precio:").nextLine().trim()));
			 
			 producto.setDescuento(new Double(scanner.skip("descuento:").nextLine().trim()));
			 
			 String linea = new String(scanner.skip("estadoIva:").nextLine().trim());
			 
			 if(linea.equals("exento"))
			 producto.setIva(true);
			 else  if(linea.equals("gravado")) producto.setIva(false);
			 
			 productos.add(producto);
		}
		scanner.close();
		return productos;
	}

	@Override
	public void	guardar(Producto producto) throws Exception {
		// TODO Auto-generated method stub
		List<Producto> productos = getProductos();
		//buscar codigo el ultimo codigo Asignado 
		if(producto.getId() == null )
		{
			int i = 0;
			for(Producto producto1 : productos)
			{
				if(producto1.getId()> i )
				{
					i = producto1.getId();
				}
			}
			producto.setId(i+1);
			productos.add(producto);
		}
		else
		{
			for(Producto producto1 :productos)
			{
				if(producto1.getId().equals(producto.getId()))
				{
					/// vacio 
					producto1.setId(producto.getId());
					producto1.setCapacidad(producto.getCapacidad());
					producto1.setPresentacion(producto.getPresentacion());
					producto1.setSabor(producto.getSabor());
					producto1.setNombre(producto.getNombre());
					producto1.setPrecio(producto.getPrecio());
					producto1.setDescuento(producto.getDescuento());
					producto1.setIva(producto.getIva());
				}
			}
		}
		guardarTodo(productos);
	}

	@Override
	public void eliminar(Producto producto) throws Exception {
		// TODO Auto-generated method stub
		List<Producto> productos = getProductos();
		for(Producto producto1 :productos)
		{
			if(producto1.getId().equals(producto.getId()))
			{
				productos.remove(producto1);
				break;
			}
		}
		guardarTodo(productos);
		
	}

	@Override
	public Producto getProducto(Integer id) throws Exception {
		// TODO Auto-generated method stub
		List<Producto> productos = getProductos();
		for(Producto producto1 :productos)
		{
			if(producto1.getId().equals(id))
			{
				return producto1;
			}
		}
		return null;
    }

	
	public ProductoDAO(String ruta) {
		super();
		this.ruta = ruta;
	}

	public ProductoDAO( ) {
		super();  
	}
	
	public void guardarTodo(List<Producto> productos ) throws Exception
	{
		FileWriter fw = new FileWriter(ruta);
		for(Producto producto :productos)
		{
			fw.append("id:"+producto.getId().toString()+"\n");
	
			fw.append("idCapacidad:"+(producto.getCapacidad() == null 
					? "  ":producto.getCapacidad().getId())+"\n");
			
			fw.append("idPresentacion:"+(producto.getPresentacion() == null
					? "  ":producto.getPresentacion().getId())+"\n");
			
			fw.append("idSabor:"+(producto.getSabor() == null 
					? "  ":producto.getSabor().getId())+"\n");
			
			fw.append("nombre:"+producto.getNombre()+"\n");
			fw.append("precio:"+producto.getPrecio()+"\n");
			fw.append("descuento:"+producto.getPrecio()+"\n");
			if(producto.getIva()==null) fw.append("estadoIva:gravado\n");
			else if(producto.getIva().booleanValue()==true) fw.append("estadoIva:exento\n");
			else if(producto.getIva().booleanValue()==false) fw.append("estadoIva:gravado\n");
			
		}
		fw.close();
	}
	
	/*
 	La Estructura de los Archivos sera la Siguiente 
  	idProducto:1
	idCapacidad:1
	idPresentacion:2
	idSabor:3
	nombre:CocaCola
	precio:500
	descuento:0.0
	estadoIva:gravado

  * */
	


