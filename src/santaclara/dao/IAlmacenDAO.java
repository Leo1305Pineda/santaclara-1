/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.dao;

import java.util.List;

import santaclara.modelo.Almacen;

public interface IAlmacenDAO extends IGenericoDAO{
	
	public List<Almacen>  getAlmacenes() throws Exception;

	public void	guardar(Almacen almacen) throws Exception;
	
	public void eliminar(Almacen almacen) throws   Exception;
	
	public Almacen getAlmacen(Integer id) throws Exception;

}