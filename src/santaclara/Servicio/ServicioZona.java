/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.Servicio;

import java.util.List;

import santaclara.modelo.Zona;
import santaclara.dao.impl.ZonaDAO;

public class ServicioZona {
	
	private ZonaDAO zonaDAO = new ZonaDAO();
		
	public List<Zona> getZonas() throws Exception{
		// TODO Auto-generated method stub
		
		return zonaDAO.getZonas();
	}

	public ZonaDAO getZonaDAO() {
		 
		return zonaDAO;
	}

	public void setZonaDAO(ZonaDAO zonaDAO) {
		this.zonaDAO = zonaDAO;
	}

	public Zona buscar(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return zonaDAO.getZona(id);
	}

	public void guardar(Zona zona) throws Exception {
		// TODO Auto-generated method stub
		zonaDAO.guardar(zona);
	}
	
	public Zona getZona(Integer id) throws Exception{
		return zonaDAO.getZona(id);
	}
	
	public Zona getZona(String nombre)throws Exception{
		return zonaDAO.getZona(nombre);
	}
	
	public void eliminar(Zona zona) throws Exception{
		zonaDAO.eliminar(zona);
	}
	
}
