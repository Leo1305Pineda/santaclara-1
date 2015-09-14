package santaclara.modelo;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import santaclara.dao.IRutaDAO;
import santaclara.dao.impl.RutaDAO;
import santaclara.dao.impl.ZonaDAO;
public class TestRutaDAO {

	@Test
	public void RutasTest() throws FileNotFoundException {
		RutaDAO rutaDAO = new RutaDAO();
		assertNotNull(rutaDAO);
		assertEquals(7,rutaDAO.getRutas().size());
	}
	@Test
	public void addRemoveRutaTest() throws IOException {
		IRutaDAO rutaDAO = new RutaDAO();
		List<Ruta> almacenes= rutaDAO.getRutas();
		
		Ruta ruta = new Ruta();
		ruta.setNombre("Nueva");
		
		ZonaDAO zonaDAO= new ZonaDAO();
		ruta.setZona(zonaDAO.getZona(3));
		
		rutaDAO.guardar(ruta);
		
		assertNotNull(ruta.getId());
		assertNotEquals(4,rutaDAO.getRutas().size());
		assertEquals(almacenes.size()+1,rutaDAO.getRutas().size());
		rutaDAO.eliminar(ruta);
		assertEquals(almacenes.size(),rutaDAO.getRutas().size());
		rutaDAO.Mostrar();
	
	}
}