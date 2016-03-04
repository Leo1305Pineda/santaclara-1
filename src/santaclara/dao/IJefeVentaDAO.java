/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.dao;

import java.util.List;

import santaclara.modelo.JefeVenta;

public interface IJefeVentaDAO extends IGenericoDAO{

	public List<JefeVenta>  getJefeVentas() throws Exception;

	public void	guardar(JefeVenta jefeVenta) throws Exception;
	
	public void eliminar(JefeVenta jefeVenta) throws   Exception;
	
	public JefeVenta getJefeVenta(Integer id) throws Exception;

}
