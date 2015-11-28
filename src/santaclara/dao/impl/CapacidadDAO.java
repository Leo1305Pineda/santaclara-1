package santaclara.dao.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import santaclara.dao.ICapacidadDAO;
import santaclara.modelo.Capacidad;

public class CapacidadDAO extends GenericoDAO implements ICapacidadDAO{
	
	private String ruta = "archivos/capacidades.txt";

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
}
