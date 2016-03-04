/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.dao;

import java.util.List;

import santaclara.modelo.EmpaqueProducto;

public interface IEmpaqueProductoDAO extends IGenericoDAO{
	
	public List<EmpaqueProducto>  getEmpaques() throws Exception;

	public void	guardar(EmpaqueProducto producto) throws Exception;
	
	public void eliminar(EmpaqueProducto producto) throws   Exception;
	
	public  Boolean getEmpaqueProducto(EmpaqueProducto empaqueProducto) throws   Exception;
	
	public EmpaqueProducto getEmpaqueProducto(Integer id) throws   Exception;
}