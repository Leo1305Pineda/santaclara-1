package santaclara.modelo;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import santaclara.dao.impl.SaborDAO;

public class TestSaborDAO {

	@Test
	public void saboresTest() throws Exception {
		SaborDAO saborDAO = new SaborDAO();
		assertNotNull(saborDAO);
		assertEquals(4,saborDAO.getSabores().size());
	}
	@Test
	public void addRemoveSaborTest() throws Exception {
		SaborDAO soborDAO = new SaborDAO();
		List<Sabor> sabores= soborDAO.getSabores();
		
		Sabor sabor1 = new Sabor(null,"Fresa");
		
		soborDAO.guardar(sabor1);
		
		assertNotNull(sabor1.getId());
		//System.out.println(sabores.size()+"="+soborDAO.getSabores().size());
		assertNotEquals(sabores.size(),soborDAO.getSabores().size());
		assertEquals(sabores.size()+1,soborDAO.getSabores().size());

		soborDAO.eliminar(sabor1);
		assertEquals(sabores.size(),soborDAO.getSabores().size());
	}



}
