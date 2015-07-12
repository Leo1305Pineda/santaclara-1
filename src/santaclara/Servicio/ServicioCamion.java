package santaclara.Servicio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import santaclara.modelo.Camion;
import santaclara.modelo.Producto;
import santaclara.dao.impl.CamionDAO;

public class ServicioCamion {
	
	private CamionDAO camionDAO = new CamionDAO();
	private List<Camion> camiones = new ArrayList<Camion>();
		
	public List<Camion> getCamiones() throws NumberFormatException, IOException{
		// TODO Auto-generated method stub
		
		return camionDAO.getCamiones();
	}

	public CamionDAO getCamionDAO() {
		 
		return camionDAO;
	}

	public void setCamionDAO(CamionDAO camionDAO) {
		this.camionDAO = camionDAO;
	}

	public Camion buscar(Integer id) throws IOException {
		// TODO Auto-generated method stub
		return camionDAO.getCamion(id);
	}

	public String guardar(Camion camion) throws IOException {
		// TODO Auto-generated method stub
		
		camiones = camionDAO.getCamiones();
		
		for(Camion Camion1 :camiones)
		{
			if(Camion1.getPlaca().equals(camion.getPlaca())&&
					!Camion1.getId().equals(camion.getId())) 
			{
				return "Producto Existente";
			}
		}
		
		camionDAO.guardar(camion);
		return "Operacion Exitosa";
				
		
	}
	
	public Camion getCamion(Integer id) throws IOException{
		return camionDAO.getCamion(id);
	}
	
	public void eliminar(Camion Camion) throws IOException{
		camionDAO.eliminar(Camion);
	}
	
}
