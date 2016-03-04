/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.dao;

import java.util.List;

import santaclara.modelo.ProductoAlmacen;

public interface IProductoAlmacenDAO extends IGenericoDAO{
	
	public List<ProductoAlmacen>  getProductoAlmacenes() throws Exception;

	public void	guardar(ProductoAlmacen productoAlmacen) throws Exception;
	
	public void eliminar(ProductoAlmacen productoAlmacen) throws   Exception;
	
	public ProductoAlmacen getProductoAlmacen(Integer idProducto,Integer idAlmacen) throws Exception;

}