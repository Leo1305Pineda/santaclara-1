package santaclara.modelo;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import santaclara.dao.ICamionDAO;
import santaclara.dao.impl.CamionDAO;

public class TestCamionDAO {

	@Test
	public void camionesTest() throws FileNotFoundException {
		ICamionDAO camionDAO = new CamionDAO();
		assertNotNull(camionDAO);
		assertEquals(4,camionDAO.getCamiones().size());
	}
	
	@Test
	public void addRemoveCamionTest() throws IOException {
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
		assertNotEquals(3,camionDAO.getCamiones().size());
		assertEquals(camiones.size()+1,camionDAO.getCamiones().size());

		camionDAO.eliminar(camion);
		assertEquals(camiones.size(),camionDAO.getCamiones().size());
	}

}
