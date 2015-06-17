package santaclara.dao.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import santaclara.dao.IRutaDAO;
import santaclara.modelo.Ruta;

public class RutaDAO extends GenericoDAO implements IRutaDAO  {

	private String ruta = "archivos/rutas.txt";
	
	@Override
	public List<Ruta> getRutas() throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<Ruta> rutas = new ArrayList<Ruta>();
		File file = new File(ruta);
 		Scanner scaner = new Scanner(file);
		while(scaner.hasNext())
		{
			Ruta ruta = new Ruta();
			ruta.setId(new Integer(scaner.skip("id:").nextLine().trim()));
			
			//guardo demas los datos de la Zona 
			ZonaDAO zonaDAO = new ZonaDAO();
			ruta.setZona(
				zonaDAO.getZona(
					new Integer(scaner.skip("zona:").nextLine().trim())));
			
			ruta.setNombre(scaner.skip("nombre:").nextLine());
			rutas.add(ruta); 
		}
		scaner.close();
		return rutas;
	}

	@Override
	public Ruta getRuta(Integer id) throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<Ruta> rutas= getRutas();
		
		for(Ruta ruta: rutas)
		{	
			if(ruta.getId().equals(id))
			{
				return ruta;
			}
		}
		return new Ruta();
	}

	@Override
	public void guardar(Ruta ruta) throws IOException {
		// TODO Auto-generated method stub
		List<Ruta> rutas = getRutas();
		//buscar codigo el ultimo codigo Asignado 
		if(ruta.getId() == null )
		{
			int i = 0;
			for(Ruta ruta1 : rutas)
			{
				if(ruta1.getId()> i )
				{
					i = ruta1.getId();
				}
			}
			ruta.setId(i+1);
			rutas.add(ruta);
		}
		else
		{
			for(Ruta ruta1 :rutas)
			{
				if(ruta1.getId().equals(ruta.getId()))
				{ 
					ruta1.setNombre(ruta.getNombre());
					ruta1.setZona(ruta.getZona());
				}
			}
		}
		guardarTodo(rutas);
	}

	@Override
	public void eliminar(Ruta ruta) throws IOException {
		// TODO Auto-generated method stub
		List<Ruta> rutas = getRutas();
		for(Ruta ruta1 :rutas)
		{
			if(ruta1.getId().equals(ruta.getId()))
			{
				rutas.remove(ruta1);
				break;
			}
		}
		guardarTodo(rutas);
	}
	public void guardarTodo(List<Ruta> rutas) throws IOException
	{
		FileWriter fw = new FileWriter(ruta);
		for(Ruta ruta1 :rutas)
		{
			fw.append("id:"+ruta1.getId().toString()+"\n");
			fw.append("zona:"+(ruta1.getZona() == null 
					? " ": ruta1.getId().toString())+"\n");
			fw.append("nombre:"+ruta1.getNombre().toString()+"\n");
			
		}
		fw.close();
	}

	@Override
	public void Mostrar() throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Listado de Rutas");
		List<Ruta> rutas = getRutas();
		for (Ruta ruta1 : rutas)
		{
			System.out.println("id: "+ruta1.getId());
			System.out.println("zona: "+ruta1.getZona().getId());
			System.out.println("nombre: "+ruta1.getNombre()+"\n");
		}
	}
	
	
/*Estructura
id:0
zona:1
nombre:El jebe

 * */
}
