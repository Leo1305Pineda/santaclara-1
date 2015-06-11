package santaclara.dao.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import santaclara.dao.IRutaDAO;
import santaclara.modelo.Ruta;
import santaclara.modelo.Zona;

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
			Zona zona = new Zona(); 
			zona.setId(new Integer(scaner.skip("zona:").nextLine().trim()));
			ruta.setZona(zona);
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
		return null;
	}
  
}
