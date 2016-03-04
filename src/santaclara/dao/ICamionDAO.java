/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.dao;

import java.util.List;

import santaclara.modelo.Camion;

public interface ICamionDAO extends IGenericoDAO {

	public List<Camion>  getCamiones() throws Exception;

	public void	guardar(Camion camion) throws Exception;
	
	public void eliminar(Camion camion) throws   Exception;
	
	public Camion getCamion(Integer id) throws Exception;
	
}
