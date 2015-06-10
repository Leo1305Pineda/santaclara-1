package santaclara.dao.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import santaclara.dao.ICamionDAO;
import santaclara.modelo.Camion;

public class CamionDAO  extends GenericoDAO implements ICamionDAO {

	

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
			 camion.setId(new Integer(scaner.skip("id:").nextLine()));
			 camion.setPlaca(scaner.skip("placa:").nextLine());
			 camion.setColor(scaner.skip("color:").nextLine());
			 camion.setCapacidad( new Double(scaner.skip("capacidad:").nextLine()));
			 camion.setModelo(scaner.skip("modelo:").nextLine());
			 camion.setMarca(scaner.skip("marca:").nextLine());
			 camion.setAno(scaner.skip("ano:").nextLine());
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
					/// vacio 
					camion1.setId(camion.getId());
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
			if(camion1.getId().equals(camion1.getId()))
			{
				camiones.remove(camion1);
				break;
			}
		}
		///guardar Todo 
		guardarTodo(camiones);
	}

	@Override
	public Camion getCamiones(Integer id) throws FileNotFoundException {
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
			fw.append("ano:"+camion.getAno()+"\n");
		}
		fw.close();
	}
}
