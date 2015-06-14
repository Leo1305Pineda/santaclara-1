package santaclara.dao.impl;

import java.io.File;
import java.io.FileNotFoundException;
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
 		Scanner scaner = new Scanner(file);
		while(scaner.hasNext())
		{
			Zona zona = new Zona();
			zona.setId(new Integer(scaner.skip("id:").nextLine().trim()));
			
			//JefeVentaDAO jefeVentaDAO = new JefeVentaDAO();
			//JefeVenta jefeVenta = new JefeVenta(); 
			
			//jefeVenta = jefeVentaDAO.getJefeVenta(new Integer(scaner.skip("jefeventa:").nextLine().trim()));
			
			//zona.setJefeVenta(jefeVenta);
			zona.setDescripcion(scaner.skip("descripcion:").nextLine());
			zonas.add(zona); 
		}
		scaner.close();
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
		
	}

	@Override
	public void eliminar(Zona zona) throws IOException {
		// TODO Auto-generated method stub
		
	}
	
	
/*Estructura
 * id:0
descripcion:centro
*/ 
	
}
