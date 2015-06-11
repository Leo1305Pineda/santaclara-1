package santaclara.modelo;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import org.junit.Test;
import santaclara.dao.impl.RutaDAO;
public class TestRutaDAO {

	@Test
	public void saboresTest() throws FileNotFoundException {
		RutaDAO rutaDAO = new RutaDAO();
		assertNotNull(rutaDAO);
		assertEquals(5,rutaDAO.getRutas().size());
	}


}
