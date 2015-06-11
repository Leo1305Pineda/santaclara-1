package santaclara.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import santaclara.modelo.ProductoAlmacen;

public interface IProductoAlmacenDAO extends IGenericoDAO{
	
	public List<ProductoAlmacen>  getProductoAlmacenes() throws FileNotFoundException, NumberFormatException, IOException;

	public void	guardar(ProductoAlmacen productoAlmacen) throws IOException;
	
	public void eliminar(ProductoAlmacen productoAlmacen) throws   IOException;
	
	public ProductoAlmacen getProductoAlmacen(Integer idProducto,Integer idAlmacen) throws FileNotFoundException, NumberFormatException, IOException;

}