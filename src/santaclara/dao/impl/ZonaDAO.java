package santaclara.dao.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import santaclara.dao.IZonaDAO; 
import santaclara.modelo.Zona;

public class ZonaDAO extends GenericoDAO implements IZonaDAO  {

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
