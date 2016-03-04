package santaclara.modelo;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import santaclara.dao.ICamionDAO;
import santaclara.dao.impl.CamionDAO;

public class TestCamionDAO {

	@Test
	public void camionesTest() throws Exception {
		ICamionDAO camionDAO = new CamionDAO();
		assertNotNull(camionDAO);
		assertEquals(6,camionDAO.getCamiones().size());
	}
	
	@Test
	public void addRemoveCamionTest() throws Exception {
		ICamionDAO camionDAO = new CamionDAO();
		List<Camion> camiones= camionDAO.getCamiones();
		
		Camion camion = new Camion();
		camion.setMarca("ford");
		camion.setModelo("350");
		camion.setCapacidad(4500.);
		camion.setColor("verde");
		camion.setAno("2015");
		camion.setPlaca("koej22");
		
		camionDAO.guardar(camion);
		assertNotNull(camion.getId());
		assertNotEquals(5,camionDAO.getCamiones().size());
		assertEquals(camiones.size()+1,camionDAO.getCamiones().size());

		camionDAO.eliminar(camion);
		assertEquals(camiones.size(),camionDAO.getCamiones().size());
	}

}
