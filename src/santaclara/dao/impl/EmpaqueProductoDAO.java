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

import santaclara.dao.IEmpaqueProductoDAO;
import santaclara.dbPostgresql.modelo.PostgreSql;
import santaclara.modelo.EmpaqueProducto;

public  class EmpaqueProductoDAO extends GenericoDAO implements IEmpaqueProductoDAO{
	
	@Override
	public List<EmpaqueProducto> getEmpaques() throws Exception {
		// TODO Auto-generated method stub
		List<EmpaqueProducto> empaqueProductos = new ArrayList<EmpaqueProducto>();
		
		ResultSet rSet = new PostgreSql().getSelect(
				"SELECT id,idproducto,cantidad FROM empaqueproductos Order by id;"); 
		
		if(rSet==null) return null;
		
			while(rSet.next())empaqueProductos.add(
					new EmpaqueProducto(
							rSet.getInt("id"),
							new ProductoDAO().getProducto(rSet.getInt("idproducto")),
							rSet.getInt("cantidad"))); 
		return empaqueProductos;
	}
	
	@Override
	public void guardar(EmpaqueProducto empaqueProducto) throws Exception {
		// TODO Auto-generated method stub
		if (empaqueProducto.getId()==null){
			new PostgreSql().ejecutar( 
					" INSERT INTO empaqueproductos(idproducto,cantidad) "
					+" VALUES ("
					+" " +empaqueProducto.getProducto().getId()		+", "
					+" " +empaqueProducto.getCantidad()	+"  "
					+");");	
		}
		else{
			new PostgreSql().ejecutar(
					"UPDATE empaqueproductos SET"
					+" idproducto = " +empaqueProducto.getProducto().getId() +", "
					+" cantidad   = " +empaqueProducto.getCantidad() 		 +" "
					+" WHERE   id = " +empaqueProducto.getId()+" "
					+ ";");
		}
	}

	@Override
	public void eliminar(EmpaqueProducto empaqueProducto) throws Exception {
		// TODO Auto-generated method stub
		if(empaqueProducto!=null) new PostgreSql().ejecutar(
								"DELETE FROM empaqueproductos "
								+"WHERE id = "+empaqueProducto.getId() +" ;");
	}

	@Override
	public EmpaqueProducto getEmpaqueProducto(Integer id) throws Exception {
		// TODO Auto-generated method stub
		
		ResultSet rSet = new PostgreSql().getSelect(
				" SELECT id,idproducto,cantidad FROM empaqueproductos "
				+" WHERE id ="+id+" "
				+";");

		if(rSet == null) return null;

		rSet.next();
		return new EmpaqueProducto(
				rSet.getInt("id"),
				new ProductoDAO().getProducto(rSet.getInt("idproducto")),
				rSet.getInt("cantidad"));
		
	}

	@Override
	public Boolean getEmpaqueProducto(EmpaqueProducto empaqueProducto)
			throws Exception {
		// TODO Auto-generated method stub
		
		ResultSet rSet = new PostgreSql().getSelect(
				" SELECT ("
				+" CASE WHEN idproducto = "+empaqueProducto.getProducto().getId()
				+" AND cantidad = "+empaqueProducto.getCantidad() +" THEN 'ENCONTRADO' "
				+" ELSE 'NO ENCONTRADO'  "
				+ "END"
				+ ") AS valor  "
				+" FROM empaqueproductos "
				+";");
		while(rSet.next()) if (rSet.getString("valor").equals("ENCONTRADO")) return true;		
		 return false;
	}
	
	/*private String ruta = "archivos/empaqueProductos.txt";

	public List<EmpaqueProducto> getEmpaques() throws Exception {
		// TODO Auto-generated method stub
		List<EmpaqueProducto> empaqueProductos = new ArrayList<EmpaqueProducto>();
		File file = new File(ruta);
		Scanner scanner = new Scanner(file);
		while(scanner.hasNext())
		{
			 EmpaqueProducto empaqueProducto = new EmpaqueProducto();
			 empaqueProducto.setId(new Integer(scanner.skip("id:").nextLine().toString().trim()));
			 //Asigna Producto
			 ProductoDAO productoDAO = new ProductoDAO();
			 empaqueProducto.setProducto(
					 productoDAO.getProducto(
							 new Integer(scanner.skip("idProducto:").nextLine().trim())));
			 empaqueProducto.setCantidad(new Integer(scanner.skip("cantidad:").nextLine().trim()));
			 
			 empaqueProductos.add(empaqueProducto); 
		}
		scanner.close();
		return empaqueProductos;
	
	}

	public void	guardar(EmpaqueProducto EmpaqueProducto) throws Exception {
		// TODO Auto-generated method stub
		List<EmpaqueProducto> empaques = getEmpaques();
		//buscar codigo el ultimo codigo Asignado 
		if(EmpaqueProducto.getId() == null )
		{
			int i = 0;
			for(EmpaqueProducto empaque1 : empaques)
			{
				if(empaque1.getId()> i )
				{
					i = empaque1.getId();
				}
			}
			EmpaqueProducto.setId(i+1);
			empaques.add(EmpaqueProducto);
		}
		else
		{
			for(EmpaqueProducto empaque1 :empaques)
			{
				if(empaque1.getId().equals(EmpaqueProducto.getId()))
				{
		 
					empaque1.setId(EmpaqueProducto.getId());
					empaque1.setProducto(EmpaqueProducto.getProducto());
					empaque1.setCantidad(EmpaqueProducto.getCantidad());
				}
			}
		}
		guardarTodo(empaques);
	}

	@Override
	public void eliminar(EmpaqueProducto empaque) throws Exception {
		// TODO Auto-generated method stub
		List<EmpaqueProducto> empaques = getEmpaques();
		for(EmpaqueProducto empaque1 :empaques)
		{
			if(empaque1.getId().equals(empaque.getId()))
			{
				empaques.remove(empaque1);
				break;
			}
		} 
		guardarTodo(empaques);
		
	}
	
	public EmpaqueProductoDAO(String ruta) {
		super();
		this.ruta = ruta;
	}

	public EmpaqueProductoDAO( ) {
		super();  
	}
	

	public void guardarTodo(List<EmpaqueProducto> productos ) throws Exception
	{
		FileWriter fw = new FileWriter(ruta);
		for(EmpaqueProducto producto :productos)
		{
			fw.append("id:"+producto.getId().toString()+"\n");
			fw.append("idProducto:"+(producto.getProducto() == null 
					? "  ":producto.getProducto().getId())+"\n");
			fw.append("cantidad:"+producto.getCantidad()+"\n");
		}
		fw.close();
	}

	@Override
	public EmpaqueProducto getEmpaqueProducto(Integer id) throws Exception {
		// TODO Auto-generated method stub
		List<EmpaqueProducto> Empaques = getEmpaques();
		for(EmpaqueProducto Empaqueproducto1 : Empaques)
		{
			if(Empaqueproducto1.getId().equals(id))
			{
				return Empaqueproducto1;
			}
		}
		return null;
	}
*/	
/* 	La Estructura de los Archivos sera la Siguiente 
  	id:1
	idProducto:1
	cantidad:10000  
  * */
}
