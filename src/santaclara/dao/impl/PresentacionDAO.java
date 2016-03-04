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

import santaclara.dao.IPresentacionDAO;
import santaclara.dbPostgresql.modelo.PostgreSql;
import santaclara.modelo.Presentacion;

public class PresentacionDAO extends GenericoDAO implements IPresentacionDAO{
	
	public List<Presentacion> getPresentaciones() throws Exception {
		// TODO Auto-generated method stub
		List<Presentacion> presentaciones = new ArrayList<Presentacion>();
		
		ResultSet rSet = new PostgreSql().getSelect(
				"SELECT id,material FROM presentaciones Order by id;"); 
		
		if(rSet==null) return null;
		
			while(rSet.next())presentaciones.add(
					new Presentacion(rSet.getInt("id"),rSet.getString("material"))); 
		return presentaciones;
	}
	
	@Override
	public void guardar(Presentacion presentacion) throws Exception {
		// TODO Auto-generated method stub
		if (presentacion.getId()==null){
			new PostgreSql().ejecutar( 
					" INSERT INTO presentaciones(material) "
					+ " VALUES ("
					+"'" +presentacion.getMaterial()+"'"
							+ ");");	
		}
		else{
			new PostgreSql().ejecutar(
					"UPDATE presentaciones SET"
					+" material ='" +presentacion.getMaterial() +"' "
					+" WHERE    id = " +presentacion.getId()+";");
		}
	}

	@Override
	public void eliminar(Presentacion presentacion) throws Exception {
		// TODO Auto-generated method stub
		if(presentacion!=null) new PostgreSql().ejecutar(
								"DELETE FROM presentaciones "
								+"WHERE id = "+presentacion.getId() +" ;");
	}

	@Override
	public Presentacion getPresentacion(Integer id) throws Exception {
		// TODO Auto-generated method stub
		
		ResultSet rSet = new PostgreSql().getSelect(
				"SELECT id,material FROM presentaciones "
				+ "WHERE id ="+id+" ;");

		if(rSet == null) return null;

		rSet.next();
		return new Presentacion(rSet.getInt("id"),rSet.getString("material"));
		
	}
	

	
	
	/*private String ruta = "archivos/presentaciones.txt";

	@Override
	public List<Presentacion> getPresentaciones() throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<Presentacion> presentaciones = new ArrayList<Presentacion>();
		File file = new File(ruta);
 		Scanner scanner = new Scanner(file);
		while(scanner.hasNext())
		{
			 Presentacion presentacion = new Presentacion();
			 presentacion.setId(new Integer(scanner.skip("id:").nextLine().trim()));
			 presentacion.setMaterial(scanner.skip("material:").nextLine().toString());
			 presentaciones.add(presentacion);
		}
		scanner.close();
		return presentaciones;
	}

	@Override
	public void guardar(Presentacion presentacion) throws IOException {
		// TODO Auto-generated method stub
		List<Presentacion> presentaciones = getPresentaciones();
		//buscar codigo el ultimo codigo Asignado 
		if(presentacion.getId() == null )
		{	
			int i = 0;
			for(Presentacion presentacion1 : presentaciones)
			{
				if(presentacion1.getId()> i )
				{
					i = presentacion1.getId();
				}
			}
			presentacion.setId(i+1);
			
			presentaciones.add(presentacion);
		}
		else
		{
			for(Presentacion presentacion1 :presentaciones)
			{
				if(presentacion1.getId().equals(presentacion.getId()))
				{ 
					presentacion1.setMaterial(presentacion.getMaterial());
					
				}
			}
		}
		guardarTodo(presentaciones);
	}

	@Override
	public void eliminar(Presentacion presentacion) throws IOException {
		// TODO Auto-generated method stub
		List<Presentacion> presentaciones = getPresentaciones();
		for(Presentacion presentacion1 :presentaciones)
		{
			if(presentacion1.getId().equals(presentacion.getId()))
			{
				presentaciones.remove(presentacion1);
				break;
			}
		} 
		guardarTodo(presentaciones);

	}
	
	public void guardarTodo(List<Presentacion> presentaciones ) throws IOException
	{
		FileWriter fw = new FileWriter(ruta);
		for(Presentacion presentacion :presentaciones)
		{
			fw.append("id:"+presentacion.getId().toString().trim()+"\n");
			fw.append("material:"+presentacion.getMaterial().toString()+"\n");
		}
		fw.close();
	}

	@Override
	public Presentacion getPresentacion(Integer id) throws IOException {
		// TODO Auto-generated method stub
		List<Presentacion> presentasiones = getPresentaciones();
		for (Presentacion presentacion1 : presentasiones)
		{
			if (presentacion1.getId().equals(id))
			{
				return presentacion1;
			}
		}
		return null;
	}*/
}
