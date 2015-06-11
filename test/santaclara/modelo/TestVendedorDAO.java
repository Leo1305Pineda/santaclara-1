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
		assertEquals(4,vendedorDAO.getVendedores().size());
	}

	@Test
	public void addRemoveVendedorTest() throws IOException {
		VendedorDAO vendedorDAO = new VendedorDAO();
		List<Vendedor> vendedores= vendedorDAO.getVendedores();
		
		Vendedor vendedores1 = new Vendedor();
		vendedores1.setCedula("V-8796484");
		vendedores1.setContrasena("1234");
		vendedores1.setNombre("Pedro Perez");
		vendedores1.setUsername("Vgowen");
		
		vendedorDAO.guardar(vendedores1);
		assertNotNull(vendedores1.getId());
		assertNotEquals(3,vendedorDAO.getVendedores().size());
		assertEquals(vendedores.size()+1,vendedorDAO.getVendedores().size());

		vendedorDAO.eliminar(vendedores1);
		assertEquals(vendedores.size(),vendedorDAO.getVendedores().size());
	}
	
}
