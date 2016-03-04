/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.Servicio;

import java.util.ArrayList;
import java.util.List;

import santaclara.modelo.Camion;
import santaclara.dao.impl.CamionDAO;

public class ServicioCamion {
	
	private CamionDAO camionDAO = new CamionDAO();
	private List<Camion> camiones = new ArrayList<Camion>();
		
	public List<Camion> getCamiones() throws Exception{
		// TODO Auto-generated method stub
		
		return camionDAO.getCamiones();
	}

	public CamionDAO getCamionDAO() {
		 
		return camionDAO;
	}

	public void setCamionDAO(CamionDAO camionDAO) {
		this.camionDAO = camionDAO;
	}

	public Camion buscar(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return camionDAO.getCamion(id);
	}

	public String guardar(Camion camion) throws Exception {
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
	
	public Camion getCamion(Integer id) throws Exception{
		return camionDAO.getCamion(id);
	}
	
	public void eliminar(Camion camion) throws Exception{
		camionDAO.eliminar(camion);
	}
	
}
