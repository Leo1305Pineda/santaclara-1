package santaclara.modelo;

import static org.junit.Assert.*;
import java.io.IOException;
import java.util.List;
import org.junit.Test;
import santaclara.dao.impl.AlmacenDAO;
import santaclara.dao.impl.EmpaqueProductoDAO;
import santaclara.dao.impl.ProductoAlmacenDAO;

public class TestProductoAlmacenDAO {

	@Test
	public void ProductoAlmacenDAOTest() throws NumberFormatException, IOException {
		ProductoAlmacenDAO productoAlmacenDAO = new ProductoAlmacenDAO();
		assertNotNull(productoAlmacenDAO);
		assertEquals(3,productoAlmacenDAO.getProductoAlmacenes().size());
	}
	@Test
	public void addRemoveAlmacenProductoTest() throws IOException {
		ProductoAlmacenDAO productoAlmacenDAO = new ProductoAlmacenDAO();
		List<ProductoAlmacen> empaqueProductos = productoAlmacenDAO.getProductoAlmacenes();
		
		EmpaqueProductoDAO productoEmpaqueDAO = new EmpaqueProductoDAO();
		AlmacenDAO almacenDAO = new AlmacenDAO();
		
		ProductoAlmacen productoAlmacen1 = new ProductoAlmacen(
				productoEmpaqueDAO.getEmpaqueProducto(4),
				almacenDAO.getAlmacen(2),
				500,70,500);
		
		productoAlmacenDAO.guardar(productoAlmacen1);
		
		assertNotNull(productoAlmacen1);
		
		assertNotEquals(2,productoAlmacenDAO.getProductoAlmacenes().size());
		assertEquals(empaqueProductos.size(),productoAlmacenDAO.getProductoAlmacenes().size());

		productoAlmacenDAO.eliminar(productoAlmacen1);
		assertEquals(empaqueProductos.size(),productoAlmacenDAO.getProductoAlmacenes().size());
	}



}
