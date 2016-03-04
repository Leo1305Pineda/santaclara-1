/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.Servicio;

import java.util.List;
import santaclara.dao.impl.ProductoDAO;
import santaclara.dao.impl.EmpaqueProductoDAO;
import santaclara.modelo.Producto;
import santaclara.modelo.EmpaqueProducto;

public class ServicioEmpaqueProducto {

	private EmpaqueProductoDAO  empaqueProductoDAO = new EmpaqueProductoDAO();
	private ProductoDAO productoDAO = new ProductoDAO();
	
	public List<EmpaqueProducto>  getEmpaqueProductos() throws Exception
	{	
		return empaqueProductoDAO.getEmpaques();
	}
		
	public List<Producto> getProductos() throws Exception
	{
		return productoDAO.getProductos();	
	}
	
	public Producto getProducto(Integer id) throws Exception{
		return productoDAO.getProducto(id);
	}
	public EmpaqueProducto getEmpaqueProducto(Integer idEmpaqueProducto)throws Exception{
		
		for(EmpaqueProducto empaqueProducto : getEmpaqueProductos()){
			if(empaqueProducto.getId().equals(idEmpaqueProducto)){
				return empaqueProducto;
			}
		}
		return null; 
		
	}
	
	
	public void guardar(EmpaqueProducto empaqueProducto) throws Exception {
		// TODO Auto-generated method stub
			
			empaqueProductoDAO.guardar(empaqueProducto); 
	}
	
	public void eliminar(EmpaqueProducto empaqueProducto) throws Exception{
		empaqueProductoDAO.eliminar(empaqueProducto);
	}
	
	public void modificar(EmpaqueProducto empaqueProducto) throws Exception{
		empaqueProductoDAO.guardar(empaqueProducto);
	}
	
	public EmpaqueProducto buscar(Integer id)throws Exception{
		return empaqueProductoDAO.getEmpaqueProducto(id);
	}
}