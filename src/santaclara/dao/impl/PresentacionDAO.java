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
	
	private String ruta = "archivos/sabor.txt";

	@Override
	public List<Presentacion> getPresentaciones() throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<Presentacion> presentaciones = new ArrayList<Presentacion>();
		File file = new File(ruta);
 		Scanner scaner = new Scanner(file);
		while(scaner.hasNext())
		{
			 Presentacion presentacion = new Presentacion();
			 presentacion.setId(new Integer(scaner.skip("id:").nextLine()));
			 presentacion.setMaterial(scaner.skip("material:").nextLine());
			 
		}
		
		return presentaciones;
	}

	@Override
	public void guardar(Presentacion presentasion) throws IOException {
		// TODO Auto-generated method stub
		List<Presentacion> presentacion = getPresentaciones();
		//buscar codigo el ultimo codigo Asignado 
		if(presentasion.getId() == null )
		{
			int i = 0;
			for(Presentacion presentacion1 : presentacion)
			{
				if(presentacion1.getId()> i )
				{
					i = presentacion1.getId();
				}
			}
			presentasion.setId(i+1);
			presentacion.add(presentasion);
		}
		else
		{
			for(Presentacion presentacion1 :presentacion)
			{
				if(presentacion1.getId().equals(presentasion.getId()))
				{
					/// vacio 
					presentacion1.setMaterial(presentasion.getMaterial());
					
				}
			}
		}
		guardarTodo(presentacion);
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
		///guardar Todo 
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
}
