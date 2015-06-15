package santaclara.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import santaclara.modelo.Producto;

public interface IProductoDAO extends IGenericoDAO{
	
	public List<Producto>  getProductos() throws FileNotFoundException, NumberFormatException, IOException;

	public void	guardar(Producto producto) throws IOException;
	
	public void eliminar(Producto producto) throws   IOException;
	
	public Producto getProducto(Integer id) throws FileNotFoundException, NumberFormatException, IOException;

}