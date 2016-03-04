/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.dao;

import java.util.List;
import santaclara.modelo.Ruta;

public interface IRutaDAO extends IGenericoDAO {

	public List<Ruta>  getRutas() throws Exception;
	
	public void	guardar(Ruta ruta) throws Exception;
	
	public void eliminar(Ruta ruta) throws   Exception;
	
	public Ruta getRuta(Integer id) throws Exception;
}
