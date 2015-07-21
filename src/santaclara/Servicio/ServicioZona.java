package santaclara.Servicio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import santaclara.modelo.Zona;
import santaclara.dao.impl.ZonaDAO;

public class ServicioZona {
	
	private ZonaDAO zonaDAO = new ZonaDAO();
	private List<Zona> zonas = new ArrayList<Zona>();
		
	public List<Zona> getZonas() throws NumberFormatException, IOException{
		// TODO Auto-generated method stub
		
		return zonaDAO.getZonas();
	}

	public ZonaDAO getZonaDAO() {
		 
		return zonaDAO;
	}

	public void setZonaDAO(ZonaDAO zonaDAO) {
		this.zonaDAO = zonaDAO;
	}

	public Zona buscar(Integer id) throws IOException {
		// TODO Auto-generated method stub
		return zonaDAO.getZona(id);
	}

	public String guardar(Zona zona) throws IOException {
		// TODO Auto-generated method stub
		
		zonas = zonaDAO.getZonas();
		
		for(Zona zona1 :zonas)
		{
			if(zona1.getDescripcion().equals(zona.getDescripcion())&&
					!zona1.getId().equals(zona.getId())) 
			{
				return "Zona Existente";
			}
		}
		
		zonaDAO.guardar(zona);
		return "Operacion Exitosa";
				
		
	}
	
	public Zona getZona(Integer id) throws IOException{
		return zonaDAO.getZona(id);
	}
	
	public void eliminar(Zona zona) throws IOException{
		zonaDAO.eliminar(zona);
	}
	
}
