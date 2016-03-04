/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.dao;

import java.util.List;

import santaclara.modelo.Producto;

public interface IProductoDAO extends IGenericoDAO{
	
	public List<Producto>  getProductos() throws Exception;

	public void	guardar(Producto producto) throws Exception;
	
	public void eliminar(Producto producto) throws   Exception;
	
	public Producto getProducto(Integer id) throws Exception;

}