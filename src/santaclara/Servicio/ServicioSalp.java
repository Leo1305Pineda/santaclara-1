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

import santaclara.modelo.Ruta;
import santaclara.modelo.Salp;
import santaclara.dao.impl.SalpDAO;

public class ServicioSalp {
	
	private SalpDAO salpDAO = new SalpDAO();
		
	public List<Salp> getSalps() throws Exception{
		// TODO Auto-generated method stub
		
		return salpDAO.getSalps();
	}

	public SalpDAO getSalpDAO() {
		 
		return salpDAO;
	}

	public void setSalpDAO(SalpDAO salpDAO) {
		this.salpDAO = salpDAO;
	}

	public Salp buscar(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return salpDAO.getSalp(id);
	}

	public void guardar(Salp salp) throws Exception {
		// TODO Auto-generated method stub
		
		salpDAO.guardar(salp);
	}
	
	public Salp getSalp(Integer id) throws Exception{
		return salpDAO.getSalp(id);
	}
	
	public void eliminar(Salp salp) throws Exception{
		salpDAO.eliminar(salp);
	}
	
	public List<Salp> getSalps(List<Ruta> rutas) throws Exception{
		// TODO Auto-generated method stub
		List<Salp> salps = new ArrayList<Salp>();
		for(Salp cliente : getSalps()){
			for(Ruta ruta : rutas){
				if (cliente.getRuta().getId().equals(ruta.getId()))
				{
					salps.add(cliente);
				}
			}
		}
		return salps;
	}
}
