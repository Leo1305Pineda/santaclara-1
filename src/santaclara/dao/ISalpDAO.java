/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.dao;

import java.util.List;

import santaclara.modelo.Salp;

public interface ISalpDAO extends IGenericoDAO {

	public List<Salp>  getSalps() throws Exception;

	public void	guardar(Salp salp) throws Exception;
	
	public void eliminar(Salp salp) throws   Exception;
	
	public Salp getSalp(Integer id) throws Exception;
		
}	
