package santaclara.dao.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import santaclara.dao.IPresentacionDAO;
import santaclara.modelo.Presentacion;

public class PresentacionDAO extends GenericoDAO implements IPresentacionDAO{
	
	private String ruta = "archivos/presentaciones.txt";

	@Override
	public List<Presentacion> getPresentaciones() throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<Presentacion> presentaciones = new ArrayList<Presentacion>();
		File file = new File(ruta);
 		Scanner scaner = new Scanner(file);
		while(scaner.hasNext())
		{
			 Presentacion presentacion = new Presentacion();
			 presentacion.setId(new Integer(scaner.skip("id:").nextLine().trim()));
			 presentacion.setMaterial(scaner.skip("material:").nextLine().trim());
			 presentaciones.add(presentacion);
		}
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
			fw.append("id:"+presentacion.getId().toString()+"\n");
			fw.append("material:"+presentacion.getMaterial()+"\n");
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
		return new Presentacion();
	}
}
