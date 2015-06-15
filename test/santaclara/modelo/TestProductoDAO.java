package santaclara.modelo;

import static org.junit.Assert.*;
import java.io.IOException;
import java.util.List;
import org.junit.Test;
import santaclara.dao.impl.CapacidadDAO;
import santaclara.dao.impl.PresentacionDAO;
import santaclara.dao.impl.ProductoDAO;
import santaclara.dao.impl.SaborDAO;

public class TestProductoDAO {

	@Test
	public void ProductoTest() throws NumberFormatException, IOException {
		ProductoDAO productoDAO = new ProductoDAO();
		assertNotNull(productoDAO);
		assertEquals(4,productoDAO.getProductos().size());
	}
	@Test
	public void addRemoveSaborTest() throws IOException {
		ProductoDAO productoDAO = new ProductoDAO();
		List<Producto> productos= productoDAO.getProductos();
		
		CapacidadDAO capacidadDAO = new CapacidadDAO();
		PresentacionDAO presentacionDAO = new PresentacionDAO();
		SaborDAO saborDAO = new SaborDAO();
		
		Producto producto1 = new Producto(null,
				capacidadDAO.getCapacidad(1),
				presentacionDAO.getPresentacion(1),
				saborDAO.getSabor(1),
				"Duldss",242432.0);
		
		productoDAO.guardar(producto1);
		
		assertNotNull(producto1.getId());
		
		assertNotEquals(3,productoDAO.getProductos().size());
		assertEquals(productos.size()+1,productoDAO.getProductos().size());

		productoDAO.eliminar(producto1);
		assertEquals(productos.size(),productoDAO.getProductos().size());
	}



}
