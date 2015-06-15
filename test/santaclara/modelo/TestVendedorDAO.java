package santaclara.modelo;


import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import santaclara.dao.impl.VendedorDAO;
import santaclara.modelo.Vendedor;
public class TestVendedorDAO {

	@Test
	public void vendedoresTest() throws FileNotFoundException {
		VendedorDAO vendedorDAO = new VendedorDAO();
		assertNotNull(vendedorDAO);
		assertEquals(3,vendedorDAO.getVendedores().size());
	}

	@Test
	public void addRemoveVendedorTest() throws IOException {
		VendedorDAO vendedorDAO = new VendedorDAO();
		List<Vendedor> vendedores= vendedorDAO.getVendedores();
		Vendedor vendedor1 = new Vendedor();
		vendedor1.setCedula("V-81233484");
		vendedor1.setContrasena("1234");
		vendedor1.setNombre("Mario Torres");
		vendedor1.setUsername("Vgowen");
		vendedor1.setRutas(null);
		
		vendedorDAO.guardar(vendedor1);
		assertNotNull(vendedor1.getId());
		assertNotEquals(2,vendedorDAO.getVendedores().size());
		assertEquals(vendedores.size()+1,vendedorDAO.getVendedores().size());

		vendedorDAO.eliminar(vendedor1);
		assertEquals(vendedores.size(),vendedorDAO.getVendedores().size());
	
				
		
	}
	
}
