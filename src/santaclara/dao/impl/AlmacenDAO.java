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

import santaclara.dao.IAlmacenDAO;
import santaclara.modelo.Almacen;

public class AlmacenDAO extends GenericoDAO implements IAlmacenDAO {

	private Almacen almacen;
	private ResultSet rSet;
	
	public AlmacenDAO( ){
		super();  
		try {
			activarConexionBaseDato();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Almacen> getAlmacenes() throws Exception {
		// TODO Auto-generated method stub
		List<Almacen> almacenes = new ArrayList<Almacen>();
	
		rSet = getConexion().getSelect("Select id,ubicacion From almacenes ORDER BY id"); 
		
		if(rSet!=null || rSet.getFetchSize()!=0)
		{
			while(rSet.next())
			{
				almacen = new Almacen(rSet.getInt("id"),rSet.getString("ubicacion"));
				almacenes.add(almacen); 
			}
		}
		return almacenes;	
	}
	
	@Override
	public void guardar(Almacen almacen) throws Exception {
		// TODO Auto-generated method stub
		if (almacen.getId()==null){
			getConexion().ejecutar( 
					"INSERT INTO almacenes(ubicacion)"
					+ " VALUES ("
					+ "'" +almacen.getUbicacion()+ "');");	
		}
		else{
			getConexion().ejecutar(
					"UPDATE almacenes SET"
					+" ubicacion   = " + "'" +almacen.getUbicacion() +"'"
					+" WHERE id    = " +almacen.getId()              +";");
		}

	}

	@Override
	public void eliminar(Almacen almacen) throws Exception {
		// TODO Auto-generated method stub
		if(almacen!=null) getConexion().ejecutar(
								"DELETE FROM almacenes "
								+"WHERE id = " +almacen.getId()+";");
	}

	@Override
	public Almacen getAlmacen(Integer id) throws Exception {
		// TODO Auto-generated method stub
		rSet = getConexion().getSelect(
								"SELECT id,ubicacion FROM almacenes "
								+ "WHERE id = "+id +" ;"); 
		
		if(rSet == null || rSet.getFetchSize()!=0) return null;
		
		rSet.next();
		return new Almacen(rSet.getInt(1),rSet.getString(2));
		
	}

	/*****   Para el manejo con txt	
	 @Override	
	private String ruta = "archivos/almacenes.txt";
	
	public List<Almacen> getAlmacenes() throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<Almacen> almacenes = new ArrayList<Almacen>();
	
		File file = new File(ruta);
 		Scanner scaner = new Scanner(file);
		while(scaner.hasNext())
		{
			Almacen almacen = new Almacen();
			almacen.setId(new Integer(scaner.skip("id:").nextLine()));
			almacen.setUbicacion(scaner.skip("ubicacion:").nextLine());
			almacenes.add(almacen); 
		}
		scaner.close();
		return almacenes;
	}
	
@Override
	public void guardar(Almacen almacen) throws Exception {
		// TODO Auto-generated method stub
		List<Almacen> almacenes = getAlmacenes();
		//buscar codigo el ultimo codigo Asignado 
		if(almacen.getId() == null )
		{
			int i = 0;
			for(Almacen almacen1 : almacenes)
			{
				if(almacen1.getId()> i )
				{
					i = almacen1.getId();
				}
			}
			almacen.setId(i+1);
			almacenes.add(almacen);
		}
		else
		{
			for(Almacen almacen1 :almacenes)
			{
				if(almacen1.getId().equals(almacen.getId()))
				{ 
					almacen1.setUbicacion(almacen.getUbicacion());
				}
			}
		}
		guardarTodo(almacenes);

	}
	
	@Override
	public void eliminar(Almacen almacen) throws Exception {
		// TODO Auto-generated method stub
		List<Almacen> almacenes = getAlmacenes();
		for(Almacen almacene1 :almacenes)
		{
			if(almacene1.getId().equals(almacen.getId()))
			{
				almacenes.remove(almacene1);
				break;
			}
		} 
		guardarTodo(almacenes);
	}

@Override
	public void eliminar(Almacen almacen) throws Exception {
		// TODO Auto-generated method stub
		List<Almacen> almacenes = getAlmacenes();
		for(Almacen almacene1 :almacenes)
		{
			if(almacene1.getId().equals(almacen.getId()))
			{
				almacenes.remove(almacene1);
				break;
			}
		} 
		guardarTodo(almacenes);
	}
	
	@Override
	public Almacen getAlmacen(Integer id) throws Exception {
		// TODO Auto-generated method stub
		List<Almacen> almacenes = getAlmacenes();
		for(Almacen almacene1 :almacenes)
		{
			if(almacene1.getId().equals(id))
			{
				return almacene1;
			}
		}
		return null;
	}
	
	public void guardarTodo(List<Almacen> almacenes) throws IOException
	{
		FileWriter fw = new FileWriter(ruta);
		for(Almacen almacen :almacenes)
		{
			fw.append("id:"+almacen.getId().toString()+"\n");
			fw.append("ubicacion:"+almacen.getUbicacion().toString()+"\n");
		}
		fw.close();
	}

*/
	
	
	
	/*Estructura
	 * id:1
	 * ubicacion:Zoma industrial I
	 * */
}
