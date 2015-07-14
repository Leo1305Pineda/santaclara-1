package santaclara.modelo;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import santaclara.dao.impl.ProductoDAO;
import santaclara.dao.impl.EmpaqueProductoDAO;


public class TestEmpaqueProductoDAO {

	@Test
	public void EmpaqueProductoTest() throws NumberFormatException, IOException {
		EmpaqueProductoDAO empaqueProductoDAO = new EmpaqueProductoDAO();
		assertNotNull(empaqueProductoDAO);
		assertEquals(6,empaqueProductoDAO.getEmpaques().size());
	}
	@Test
	public void addRemoveSaborTest() throws IOException {
		EmpaqueProductoDAO empaqueProductoDAO = new EmpaqueProductoDAO();
		List<EmpaqueProducto> empaqueProductos = empaqueProductoDAO.getEmpaques();
		
		ProductoDAO pruductoDAO = new ProductoDAO();
		
		EmpaqueProducto empaqueProducto1 = new EmpaqueProducto(null,
				pruductoDAO.getProducto(1),13);
		
		empaqueProductoDAO.guardar(empaqueProducto1);
		
		assertNotNull(empaqueProducto1.getId());
		
		assertNotEquals(3,empaqueProductoDAO.getEmpaques().size());
		assertEquals(empaqueProductos.size()+1,empaqueProductoDAO.getEmpaques().size());

		empaqueProductoDAO.eliminar(empaqueProducto1);
		assertEquals(empaqueProductos.size(),empaqueProductoDAO.getEmpaques().size());
	}



}
