package santaclara.modelo;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import org.junit.Test;
import santaclara.dao.impl.SaborDAO;
public class TestSaborDAO {

	@Test
	public void saboresTest() throws FileNotFoundException {
		SaborDAO saborDAO = new SaborDAO();
		assertNotNull(saborDAO);
		assertEquals(4,saborDAO.getSabores().size());
	}



}
