/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.dao;

import java.util.List;

import santaclara.modelo.Zona;

public interface IZonaDAO extends IGenericoDAO {

	public List<Zona>  getZonas() throws Exception;
	
	public void	guardar(Zona zona) throws Exception;
	
	public void eliminar(Zona zona) throws   Exception;
	
	public Zona getZona(Integer id) throws Exception;
}
