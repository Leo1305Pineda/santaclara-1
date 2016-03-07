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

import santaclara.dao.ISaborDAO;
import santaclara.modelo.Sabor;

public class SaborDAO extends GenericoDAO implements ISaborDAO{
	
	public SaborDAO(){
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
	public List<Sabor> getSabores() throws Exception {
		// TODO Auto-generated method stub
		List<Sabor> sabores = new ArrayList<Sabor>();
		
		ResultSet rSet = getConexion().getSelect(
				"SELECT id,sabor FROM sabores Order by id;"); 
		
		if(rSet==null || rSet.getFetchSize()!=0) return null;
		
			while(rSet.next())sabores.add(
					new Sabor (rSet.getInt("id"),rSet.getString("sabor"))); 
		return sabores;
	}
	
	@Override
	public void guardar(Sabor sabor) throws Exception {
		// TODO Auto-generated method stub
		
			if (sabor.getId()==null){
				getConexion().ejecutar( 
						"INSERT INTO sabores(sabor) "
						+ "VALUES ("
						+"'" +sabor.getSabor()+"');");	
			}
			else{
				getConexion().ejecutar(
						"UPDATE sabores SET     "
						+" sabor ='" +sabor.getSabor() +"' "
						+" WHERE    id = " +sabor.getId()+";");
			}
	}

	@Override
	public void eliminar(Sabor sabor) throws Exception {
		// TODO Auto-generated method stub
		if(sabor!=null) getConexion().ejecutar(
								"DELETE FROM sabores "
								+"WHERE id = "+sabor.getId() +" ;");
	}

	@Override
	public Sabor getSabor(Integer id) throws Exception {
		// TODO Auto-generated method stub
		
		ResultSet rSet = getConexion().getSelect(
				"SELECT id,sabor FROM sabores "
				+ "WHERE id ="+id+" ;");

		if(rSet == null || rSet.getFetchSize()!=0) return null;

		rSet.next();
		return new Sabor(rSet.getInt("id"),rSet.getString("sabor"));
		
	}
}
	
	/*
	private String ruta = "archivos/sabores.txt";

	@Override
	public List<Sabor> getSabores() throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<Sabor> sabores = new ArrayList<Sabor>();
		File file = new File(ruta);
 		Scanner scanner = new Scanner(file);
		while(scanner.hasNext())
		{
			 Sabor sabor = new Sabor();
			 sabor.setId(new Integer(scanner.skip("id:").nextLine().trim()));
			 sabor.setSabor(scanner.skip("sabor:").nextLine().trim());
			 sabores.add(sabor);
		}
		scanner.close();
		return sabores;
	}

	@Override
	public void guardar(Sabor sabor) throws IOException {
		// TODO Auto-generated method stub
		List<Sabor> sabores = getSabores();
		//buscar codigo el ultimo codigo Asignado 
		if(sabor.getId() == null )
		{
			int i = 0;
			for(Sabor sabor1 : sabores)
			{
				if(sabor1.getId()> i )
				{
					i = sabor1.getId();
				}
			}
			sabor.setId(i+1);
			sabores.add(sabor);
		}
		else
		{
			for(Sabor sabor1 :sabores)
			{
				if(sabor1.getId().equals(sabor.getId()))
				{ 
					sabor1.setSabor(sabor.getSabor());
					
				}
			}
		}
		guardarTodo(sabores);
	}

	@Override
	public void eliminar(Sabor sabor) throws IOException {
		// TODO Auto-generated method stub
		List<Sabor> sabores = getSabores();
		for(Sabor sabor1 :sabores)
		{
			if(sabor1.getId().equals(sabor.getId()))
			{
				sabores.remove(sabor1);
				break;
			}
		}
		guardarTodo(sabores);

	}
	@Override
	public Sabor getSabor(Integer id) throws IOException {
		// TODO Auto-generated method stub
		List<Sabor> sabores = getSabores();
		for(Sabor sabor1 : sabores)
		{
			if (sabor1.getId().equals(id))
			{
				return sabor1;
			}
		}
		return null;
	}
	
	public void guardarTodo(List<Sabor> sabores ) throws IOException
	{
		FileWriter fw = new FileWriter(ruta);
		for(Sabor sabor :sabores)
		{
			fw.append("id:"+sabor.getId().toString()+"\n");
			fw.append("sabor:"+sabor.getSabor()+"\n");
		}
		fw.close();
	}
	/*Estructura
 	id:  0  
	sabor: AguaUva 

	 * */


