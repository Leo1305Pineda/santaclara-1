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
import santaclara.dao.IZonaDAO; 
import santaclara.dbPostgresql.modelo.PostgreSql;
import santaclara.modelo.Zona;

public class ZonaDAO extends GenericoDAO implements IZonaDAO  {

	@Override
	public List<Zona> getZonas() throws Exception {
		// TODO Auto-generated method stub
		List<Zona> zonas = new ArrayList<Zona>();
		
		ResultSet rSet = new PostgreSql().getSelect(
				"SELECT id,descripcion FROM zonas Order by id;"); 
		
		if(rSet==null) return null;
		
			while(rSet.next())zonas.add(
					new Zona(rSet.getInt("id"),rSet.getString("descripcion"))); 
		return zonas;
	}
	
	@Override
	public void guardar(Zona zona) throws Exception {
		// TODO Auto-generated method stub
		if (zona.getId()==null){
			new PostgreSql().ejecutar( 
					"INSERT INTO zonas(descripcion) "
					+ "VALUES ("
					+"'" +zona.getDescripcion()+"'"
							+ ");");	
		}
		else{
			new PostgreSql().ejecutar(
					"UPDATE zonas SET"
					+" descripcion ='" +zona.getDescripcion() +"' "
					+" WHERE    id = " +zona.getId()+";");
		}
	}

	@Override
	public void eliminar(Zona zona) throws Exception {
		// TODO Auto-generated method stub
		if(zona!=null) new PostgreSql().ejecutar(
								"DELETE FROM zonas "
								+"WHERE id = "+zona.getId() +" ;");
	}

	@Override
	public Zona getZona(Integer id) throws Exception {
		// TODO Auto-generated method stub
		
		ResultSet rSet = new PostgreSql().getSelect(
				"SELECT id,descripcion FROM zonas "
				+ "WHERE id ="+id+" ;");

		if(rSet == null) return null;

		rSet.next();
		return new Zona(rSet.getInt("id"),rSet.getString("descripcion"));
		
	}

	/**	
	private String ruta = "archivos/zonas.txt";
	
	@Override
	public List<Zona> getZonas() throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<Zona> zonas = new ArrayList<Zona>();
		File file = new File(ruta);
 		Scanner scanner = new Scanner(file);
		while(scanner.hasNext())
		{
			Zona zona = new Zona();
			zona.setId(new Integer(scanner.skip("id:").nextLine().toString().trim()));
			zona.setDescripcion(scanner.skip("descripcion:").nextLine().toString());
			zonas.add(zona); 
		}
		scanner.close();
		return zonas;
	}

	@Override
	public Zona getZona(Integer id) throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<Zona> zonas = getZonas();
		
		for(Zona zona: zonas)
		{	
			if(zona.getId().equals(id))
			{
				return zona;
			}
		}
		
		return null;
	}

	@Override
	public void guardar(Zona zona) throws IOException {
		// TODO Auto-generated method stub
		List<Zona> zonas = getZonas();
		//buscar codigo el ultimo codigo Asignado 
		if(zona.getId() == null )
		{
			int i = 0;
			for(Zona zona1 : zonas)
			{
				if(zona1.getId()> i )
				{
					i = zona1.getId();
				}
			}
			zona.setId(i+1);
			zonas.add(zona);
		}
		else
		{
			for(Zona zona1 :zonas)
			{
				if(zona1.getId().equals(zona.getId()))
				{ 
					zona1.setDescripcion(zona.getDescripcion());
				}
			}
		}
		guardarTodo(zonas);

	}

	@Override
	public void eliminar(Zona zona) throws IOException {
		// TODO Auto-generated method stub
		List<Zona> zonas = getZonas();
		for(Zona zona1 :zonas)
		{
			if(zona1.getId().equals(zona.getId()))
			{
				zonas.remove(zona1);
				break;
			}
		}
		guardarTodo(zonas);
	}
	
	public void guardarTodo(List<Zona> zonas) throws IOException
	{
		FileWriter fw = new FileWriter(ruta);
		for(Zona zona1 :zonas)
		{
			fw.append("id:"+zona1.getId().toString()+"\n");
			fw.append("descripcion:"+zona1.getDescripcion().toString()+"\n");
		}
		fw.close();
	}
	
/*Estructura
 * id:0
descripcion:centro
*/ 
}
