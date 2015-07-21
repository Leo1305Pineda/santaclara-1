package santaclara.modelo;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import santaclara.dao.impl.PresentacionDAO;

public class TestPresentacionDAO {

	@Test
	public void presentacionTest() throws FileNotFoundException {
		PresentacionDAO presentacionDAO = new PresentacionDAO();
		assertNotNull(presentacionDAO);
		//assertNull(presentacionDAO.getPresentaciones());
		assertEquals(3,presentacionDAO.getPresentaciones().size());
	}
	@Test
	public void addRemovePresentacionTest() throws IOException {
		PresentacionDAO presentacionDAO = new PresentacionDAO();
		List<Presentacion> presentaciones= presentacionDAO.getPresentaciones();
		
		Presentacion presentacion1 = new Presentacion(null,"Carton");
		
		presentacionDAO.guardar(presentacion1);
		
		assertNotNull(presentacion1.getId());
		assertNotEquals(presentaciones.size(),presentacionDAO.getPresentaciones().size());
		assertEquals(presentaciones.size()+1,presentacionDAO.getPresentaciones().size());

		presentacionDAO.eliminar(presentacion1);
		assertEquals(presentaciones.size(),presentacionDAO.getPresentaciones().size());
	}



}
