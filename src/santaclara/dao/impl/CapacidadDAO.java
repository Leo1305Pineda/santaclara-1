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

import santaclara.dao.ICapacidadDAO;
import santaclara.dbPostgresql.modelo.PostgreSql;
import santaclara.modelo.Capacidad;

public class CapacidadDAO extends GenericoDAO implements ICapacidadDAO{
	
	@Override
	public List<Capacidad> getCapacidades() throws Exception {
		
		List<Capacidad> capacidades = new ArrayList<Capacidad>();
		
		ResultSet rSet = new PostgreSql().getSelect(
				"SELECT id,volumen FROM capacidades Order by id;"); 
		
		if(rSet==null) return null;
		
			while(rSet.next())capacidades.add(
					new Capacidad(rSet.getInt("id"),rSet.getDouble("volumen"))); 
		return capacidades;
	}
	
	@Override
	public void guardar(Capacidad capacidad) throws Exception {
		// TODO Auto-generated method stub
		if (capacidad.getId()==null){
			new PostgreSql().ejecutar( 
					"INSERT INTO capacidades(volumen) "
					+ "VALUES ("
					+" " +capacidad.getVolumen()+" );");	
		}
		else{
			new PostgreSql().ejecutar(
					"UPDATE capacidades SET"
					+" volumen = " +capacidad.getVolumen() +"  "
					+" WHERE    id = " +capacidad.getId()+";");
		}
	}

	@Override
	public void eliminar(Capacidad capacidad) throws Exception {
		// TODO Auto-generated method stub
		if(capacidad!=null) new PostgreSql().ejecutar(
								"DELETE FROM capacidades "
								+"WHERE id = "+capacidad.getId() +" ;");
	}

	@Override
	public Capacidad getCapacidad(Integer id) throws Exception {
		// TODO Auto-generated method stub
		
		ResultSet rSet = new PostgreSql().getSelect(
				"SELECT id,volumen FROM capacidades "
				+ "WHERE id ="+id+" ;");

		if(rSet == null) return null;

		rSet.next();
		return new Capacidad(rSet.getInt("id"),rSet.getDouble("volumen"));
		
	}
	
}
	
	/* private String ruta = "archivos/capacidades.txt";

	@Override
	public List<Capacidad> getCapacidades() throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<Capacidad> capacidades = new ArrayList<Capacidad>();
		File file = new File(ruta);
 		Scanner scanner = new Scanner(file);
		while(scanner.hasNext())
		{
			 Capacidad capacidad = new Capacidad();
			 capacidad.setId(new Integer(scanner.skip("id:").nextLine().trim()));
			 capacidad.setVolumen(new Double(scanner.skip("volumen:").nextLine().trim()));
			 capacidades.add(capacidad);
		}
		scanner.close();
		return capacidades;
	}

	@Override
	public void guardar(Capacidad capacidad) throws IOException {
		// TODO Auto-generated method stub
		List<Capacidad> capacidades = getCapacidades();
		//buscar codigo el ultimo codigo Asignado 
		if(capacidad.getId() == null )
		{
			int i = 0;
			for(Capacidad capacidad1 : capacidades)
			{
				if(capacidad1.getId()> i )
				{
					i = capacidad1.getId();
				}
			}
			capacidad.setId(i+1);
			capacidades.add(capacidad);
		}
		else
		{
			for(Capacidad capacidad1 :capacidades)
			{
				if(capacidad1.getId().equals(capacidad.getId()))
				{ 
					capacidad1.setVolumen(capacidad.getVolumen());
					
				}
			}
		}
		guardarTodo(capacidades);
	}

	@Override
	public void eliminar(Capacidad capacidad) throws IOException {
		// TODO Auto-generated method stub
		List<Capacidad> capacidades = getCapacidades();
		for(Capacidad capacidad1 :capacidades)
		{
			if(capacidad1.getId().equals(capacidad.getId()))
			{
				capacidades.remove(capacidad1);
				break;
			}
		}
		guardarTodo(capacidades);

	}
	
	public void guardarTodo(List<Capacidad> capacidades ) throws IOException
	{
		FileWriter fw = new FileWriter(ruta);
		for(Capacidad capacidad : capacidades)
		{
			fw.append("id:"+capacidad.getId().toString()+"\n");
			fw.append("volumen:"+capacidad.getVolumen().toString()+"\n");
		}
		fw.close();
	}
	@Override
	public Capacidad getCapacidad(Integer id) throws IOException {
		// TODO Auto-generated method stub
		List<Capacidad> capacidades = getCapacidades();
		for(Capacidad capacidad1 : capacidades)
		{
			if (capacidad1.getId().equals(id))
			{
				return capacidad1;
			}
		}
		return null;
	}
	/*Estructura
	 * id:0
	 * volumen:355   
	 */

