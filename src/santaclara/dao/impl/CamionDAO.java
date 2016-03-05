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

import santaclara.dao.ICamionDAO;
import santaclara.modelo.Camion;

public class CamionDAO  extends GenericoDAO implements ICamionDAO {
	
	public CamionDAO() {
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
	public List<Camion> getCamiones() throws Exception {
		// TODO Auto-generated method stub
		List<Camion> camiones = new ArrayList<Camion>();
		
		ResultSet rSet = getConexion().getSelect(
				" SELECT id, placa, color, capacidad, modelo, marca, ano "
				+" FROM camiones Order by id;"); 
		
		if(rSet==null || rSet.getFetchSize()!=0) return null;
		
			while(rSet.next())camiones.add(
					new Camion(
							rSet.getInt("id"), 
							rSet.getString("placa"), 
							rSet.getString("color"),
							rSet.getDouble("capacidad") , 
							rSet.getString("modelo"), 
							rSet.getString("marca"), 
							rSet.getString("ano"))); 
		return camiones;
	}
	
	@Override
	public void guardar(Camion camion) throws Exception {
		// TODO Auto-generated method stub
		try {
			if (camion.getId()==null){
				getConexion().ejecutar(
						" INSERT INTO camiones( placa, color, capacidad, modelo, marca, ano)   "
						+ " VALUES("
						+ " '"+ camion.getPlaca() 		+"', "
						+ " '"+ camion.getColor() 		+"', "
						+ "  "+ camion.getCapacidad() 	+" , "
						+ " '"+ camion.getModelo() 		+"', "
						+ " '"+ camion.getMarca() 		+"', "
						+ " '"+ camion.getAno() 		+"'  "
						+ ");");	
			}
			else{
				getConexion().ejecutar(
						"UPDATE camiones SET"
						+" placa 	= '" +camion.getPlaca() 	+"', "
						+" color 	= '" +camion.getColor() 	+"', "
						+" capacidad = " +camion.getCapacidad() +" , "
						+" modelo 	= '" +camion.getModelo() 	+"', "
						+" marca 	= '" +camion.getMarca() 	+"', "
						+" ano 		= '" +camion.getAno() 		+"' "
						+" WHERE id =  " +camion.getId()        +";");
			}

			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
			}

	@Override
	public void eliminar(Camion camion) throws Exception {
		// TODO Auto-generated method stub
		if(camion!=null) getConexion().ejecutar(
								" DELETE FROM camiones "
								+" WHERE id = "+camion.getId() +" ;");
	}

	@Override
	public Camion getCamion(Integer id) throws Exception {
		// TODO Auto-generated method stub
		
		ResultSet rSet = getConexion().getSelect(
				" SELECT id, placa, color, capacidad, modelo, marca, ano "
				+" FROM camiones WHERE id ="+id+";");

		if(rSet == null || rSet.getFetchSize()!=0) return null;

		rSet.next();
		return new Camion(
				rSet.getInt("id"), 
				rSet.getString("placa"), 
				rSet.getString("color"),
				rSet.getDouble("capacidad") , 
				rSet.getString("modelo"), 
				rSet.getString("marca"), 
				rSet.getString("ano"));
	}
	
	/*
	 
	 private String ruta = "archivos/camiones.txt";
	
	@Override
	public List<Camion> getCamiones() throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<Camion> camiones = new ArrayList<Camion>();
		File file = new File(ruta);
 		Scanner scaner = new Scanner(file);
		while(scaner.hasNext())
		{
			 Camion camion = new Camion();
			 camion.setId(new Integer(scaner.skip("id:").nextLine().trim()));
			 camion.setPlaca(scaner.skip("placa:").nextLine().trim());
			 camion.setColor(scaner.skip("color:").nextLine().trim());
			 camion.setCapacidad( new Double(scaner.skip("capacidad:").nextLine().trim()));
			 camion.setModelo(scaner.skip("modelo:").nextLine().trim());
			 camion.setMarca(scaner.skip("marca:").nextLine().trim());
			 camion.setAno(scaner.skip("ano:").nextLine().trim());
			 camiones.add(camion); 
		}
		scaner.close();
		return camiones;
	}

	@Override
	public void guardar(Camion camion) throws IOException {
		// TODO Auto-generated method stub
		List<Camion> camiones = getCamiones();
		//buscar codigo el ultimo codigo Asignado 
		if(camion.getId() == null )
		{
			int i = 0;
			for(Camion camion1: camiones)
			{
				if(camion1.getId()> i )
				{
					i = camion1.getId();
				}
			}
			camion.setId(i+1);
			camiones.add(camion);
		}
		else
		{
			for(Camion camion1 :camiones)
			{
				if(camion1.getId().equals(camion.getId()))
				{ 
					camion1.setMarca(camion.getMarca());
					camion1.setModelo(camion.getModelo());
					camion1.setAno(camion.getAno());
					camion1.setColor(camion.getColor());
					camion1.setCapacidad(camion.getCapacidad());
				}
			}
		}
		guardarTodo(camiones);
	}

	@Override
	public void eliminar(Camion camion) throws IOException {
		// TODO Auto-generated method stub
		List<Camion> camiones = getCamiones();
		for(Camion camion1 :camiones)
		{
			if(camion1.getId().equals(camion.getId()))
			{
				camiones.remove(camion1);
				break;
			}
		}
		guardarTodo(camiones);
	}

	@Override
	public Camion getCamion(Integer id) throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<Camion> camiones = getCamiones();
		
		for(Camion camion: camiones)
		{
			if(camion.getId().equals(id))
			{
				return camion;
			}
		}
		return null;
    	}

	
	
	public void guardarTodo(List<Camion> camiones) throws IOException
	{
		FileWriter fw = new FileWriter(ruta);
		for(Camion camion: camiones)
		{
			fw.append("id:"+camion.getId().toString()+"\n");
			fw.append("placa:"+camion.getPlaca().toString()+"\n");
			fw.append("color:"+camion.getColor()+"\n");
			fw.append("capacidad:"+camion.getCapacidad().toString()+"\n");
			fw.append("modelo:"+camion.getModelo()+"\n");
			fw.append("marca:"+camion.getMarca()+"\n");
			fw.append("ano:"+camion.getAno().toString()+"\n");
		}
		fw.close();
	}
	
	public void Mostrar() throws IOException {
		// TODO Auto-generated method stub
		List<Camion> camiones = getCamiones();
		System.out.println("Listar Todos los Camiones");
		for(Camion camion1 :camiones)
		{
			System.out.println("id: "+camion1.getId());
			System.out.println("placa: "+camion1.getPlaca());
			System.out.println("color: "+camion1.getColor());
			System.out.println("capacidad: "+camion1.getCapacidad());
			System.out.println("modelo: "+camion1.getModelo());
			System.out.println("marca: "+camion1.getMarca());
			System.out.println("ano: "+camion1.getAno()+"\n");
		}
	}
 
	 
	 * */
	
	/*
 *Estructura
id:11
placa:koej22
color:verde
capacidad:4500.0
modelo:350
marca:ford
ano:2015
*/
}
