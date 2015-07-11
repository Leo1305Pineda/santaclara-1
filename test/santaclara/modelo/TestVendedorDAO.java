package santaclara.modelo;


import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import santaclara.dao.impl.RutaDAO;
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
		vendedor1.setCedula("V-81484");
		vendedor1.setContrasena("1234");
		vendedor1.setNombre("Mario Torres");
		vendedor1.setUsername("Vgowen");
		
		List<Ruta> r = new ArrayList<Ruta>();
		RutaDAO rutaDAO = new RutaDAO();
		r.add(rutaDAO.getRuta(4));
		r.add(rutaDAO.getRuta(1));

		vendedor1.setRutas(r);
		vendedorDAO.guardar(vendedor1);
		
		System.out.println(vendedor1.getCedula()+" \n rutas: "+vendedor1.getRutas().get(0).getId()+"id: "+vendedor1.getRutas().get(0).getNombre());
		
		assertNotNull(vendedor1.getId());
		assertNotEquals(2,vendedorDAO.getVendedores().size());
		assertEquals(vendedores.size()+1,vendedorDAO.getVendedores().size());

		vendedorDAO.eliminar(vendedor1);
		assertEquals(vendedores.size(),vendedorDAO.getVendedores().size());
	
				
		
	}
	
}
