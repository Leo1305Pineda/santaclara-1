/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.dao;

import java.util.List;

import santaclara.modelo.Sabor;

public interface ISaborDAO extends IGenericoDAO{
	
	public List<Sabor>  getSabores() throws Exception;

	public void	guardar(Sabor sabor) throws Exception;
	
	public void eliminar(Sabor sabor) throws   Exception;
	
	public Sabor getSabor(Integer id) throws Exception;
	
}
