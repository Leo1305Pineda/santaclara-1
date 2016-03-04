/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.Servicio;

import java.util.List;

import santaclara.dao.impl.RutaDAO;
import santaclara.dao.impl.ZonaDAO;
import santaclara.modelo.Ruta;
import santaclara.modelo.Zona;

public class ServicioRuta {
	
	private RutaDAO rutaDAO = new RutaDAO();
	private ZonaDAO zonaDAO = new ZonaDAO();
	
	public List<Ruta> getRutas() throws Exception{
		return rutaDAO.getRutas();
	}
	
	public List<Zona> getZonas() throws Exception{
		return zonaDAO.getZonas();
	}
	
	public void guardar(Ruta ruta) throws Exception {
		// TODO Auto-generated method stub
			rutaDAO.guardar(ruta);
	}
	
	public Ruta buscar(int id) throws Exception{
		
		return rutaDAO.getRuta(id);
	}
	
	public void eliminar (Ruta ruta)throws Exception{
		rutaDAO.eliminar(ruta);
	}
	
	public void modificar (Ruta ruta) throws Exception{
		rutaDAO.guardar(ruta);
	}
}
