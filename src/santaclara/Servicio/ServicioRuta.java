package santaclara.Servicio;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import santaclara.dao.impl.RutaDAO;
import santaclara.dao.impl.ZonaDAO;
import santaclara.modelo.Ruta;
import santaclara.modelo.Zona;

public class ServicioRuta {
	
	private RutaDAO rutaDAO = new RutaDAO();
	private ZonaDAO zonaDAO = new ZonaDAO();
	
	public List<Ruta> getRutas() throws FileNotFoundException{
		return rutaDAO.getRutas();
	}
	
	public List<Zona> getZonas() throws IOException{
		return zonaDAO.getZonas();
	}
	
	public Boolean guardar(Ruta ruta) throws IOException {
		// TODO Auto-generated method stub
		// if el es nuevo que no este otro con el mismo nombre 
		if (!this.rutaDAO.getRuta(ruta))//mientras que el nombre no se repita 
		{
			rutaDAO.guardar(ruta);
			return true;//Guardo 
		}
		return false;//no Guardo
	}
	
	public Ruta buscar(int id) throws IOException{
		
		return rutaDAO.getRuta(id);
	}
	
	public void eliminar (Ruta ruta)throws IOException{
		rutaDAO.eliminar(ruta);
	}
	
	public void modificar (Ruta ruta) throws IOException{
		rutaDAO.guardar(ruta);
	}
}
