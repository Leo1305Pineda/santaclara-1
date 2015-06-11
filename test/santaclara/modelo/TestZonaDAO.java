package santaclara.modelo;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

import santaclara.dao.impl.ZonaDAO;

public class TestZonaDAO {

	@Test
	public void saboresTest() throws FileNotFoundException {
		ZonaDAO zonaDAO = new ZonaDAO();
		assertNotNull(zonaDAO);
		assertEquals(5,zonaDAO.getZonas().size());
	}



}
