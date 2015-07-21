package santaclara.Servicio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import santaclara.modelo.Camion;
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
		
		for(Camion camion1 :camiones)
		{
			if(camion1.getPlaca().equals(camion.getPlaca())&&
					camion1.getId().equals(camion.getId())) 
			{
				if(camion1.getMarca().equals(camion.getMarca())&&
						camion1.getModelo().equals(camion.getModelo())&&
						camion1.getAno().equals(camion.getAno())&&
						camion1.getCapacidad().equals(camion.getCapacidad())&&
						camion1.getColor().equals(camion.getColor()))
				return "Camion Existente";
				break;//rompe el for para modificar
			}
		}
		
		camionDAO.guardar(camion);
		return "Operacion Exitosa";
				
		
	}
	
	public Camion getCamion(Integer id) throws IOException{
		return camionDAO.getCamion(id);
	}
	
	public void eliminar(Camion camion) throws IOException{
		camionDAO.eliminar(camion);
	}
	
}
