/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.Servicio;

import java.util.ArrayList;
import java.util.List;

import santaclara.dao.impl.AlmacenDAO;
import santaclara.dao.impl.ProductoAlmacenDAO;
import santaclara.modelo.Almacen;
import santaclara.modelo.ProductoAlmacen;

public class ServicioProductoAlmacen {

	private ProductoAlmacenDAO  productoAlmacenDAO = new ProductoAlmacenDAO();
	private AlmacenDAO almacenDAO = new AlmacenDAO();
	
	private List<ProductoAlmacen> productoAlmacenes;
	
	
	public List<ProductoAlmacen>  getProductoAlmacen() throws Exception
	{	
		return productoAlmacenDAO.getProductoAlmacenes();
	}
		
	public List<ProductoAlmacen> getProductoAlmacenes() throws Exception
	{
		return productoAlmacenDAO.getProductoAlmacenes();	
	}
	
	public List<Almacen> getAlmacenes() throws Exception
	{
		return almacenDAO.getAlmacenes();
	}
	
	public String guardar(ProductoAlmacen productAlmacen) throws Exception {
		// TODO Auto-generated method stub
		
			productoAlmacenes = productoAlmacenDAO.getProductoAlmacenes();
			
			for(ProductoAlmacen productoAlmacen1 :productoAlmacenes)
			{
					if(productoAlmacen1.getEmpaqueProducto().getId().equals(productAlmacen.getEmpaqueProducto().getId())&&
							productoAlmacen1.getAlmacen().getId().equals(productAlmacen.getAlmacen().getId()))
					{
						if(productoAlmacen1.getStock().equals(productAlmacen.getStock())&&
								productoAlmacen1.getStockMin().equals(productAlmacen.getStockMin())&&
								productoAlmacen1.getExistencia().equals(productAlmacen.getExistencia()))
							return "El Producto  Existente en el Almacen: ";
						
						else break;//rompe el for para modifacar
					}
			}
			if(productAlmacen.getStock()<=productAlmacen.getStockMin())return "El Stock no puede ser menor igual Al StockMin";
			if(productAlmacen.getStock().equals(0))return "El Stock no puede ser 0";
			if(productAlmacen.getStockMin().equals(0))return "El StockMin no puede ser 0";
			if(productAlmacen.getExistencia()>productAlmacen.getStock())return "La Existencia no puede ser Mayor Al Stock";
			if(productAlmacen.getExistencia()<productAlmacen.getStockMin())return "La Existencia no puede ser Menor Al StockMin";
			if(productAlmacen.getExistencia().equals(0))return "La Existencia no puede ser 0";
		
			productoAlmacenDAO.guardar(productAlmacen); 
			return "Operacion Exitosa ";
	}
	
	public void eliminar(ProductoAlmacen productoAlmacen) throws Exception{
		productoAlmacenDAO.eliminar(productoAlmacen);
	}
	
	public void modificar(ProductoAlmacen productoAlmacen) throws Exception{
		productoAlmacenDAO.guardar(productoAlmacen);
	}
	
	public ProductoAlmacen getProductoAlmacen(Integer idEmpaqueProducto,Integer idAlmacen)throws Exception{
		return productoAlmacenDAO.getProductoAlmacen(idEmpaqueProducto, idAlmacen);
	}
	
	public List<ProductoAlmacen> getProductoAlmacenes(Integer idAlmacen) throws Exception
	{
		List<ProductoAlmacen> productoAlmacensAux = new ArrayList<ProductoAlmacen>();
		for(ProductoAlmacen productoAlmacen : getProductoAlmacenes())
		{
			if(productoAlmacen.getAlmacen().getId().equals(idAlmacen))
			{
				productoAlmacensAux.add(productoAlmacen);
			}
		}
		return productoAlmacensAux;	
	}

}