package santaclara.modelo;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import santaclara.dao.IAlmacenDAO;
import santaclara.dao.impl.AlmacenDAO;

public class TestAlmacenDAO {

	@Test
	public void almacenTest() throws FileNotFoundException{
		IAlmacenDAO almacenDAO = new AlmacenDAO();
		assertNotNull(almacenDAO);
		assertEquals(2,almacenDAO.getAlmacenes().size());
	}
	
	@Test
	public void addRemoveAlmacenTest() throws IOException {
		IAlmacenDAO almacenDAO = new AlmacenDAO();
		List<Almacen> almacenes= almacenDAO.getAlmacenes();
		
		Almacen almacen = new Almacen();
		almacen.setUbicacion("Zoma industrial III");
		almacenDAO.guardar(almacen);
		assertNotNull(almacen.getId());
		assertNotEquals(1,almacenDAO.getAlmacenes().size());
		assertEquals(almacenes.size()+1,almacenDAO.getAlmacenes().size());
		System.out.println(almacen.getId());
		System.out.println(almacen.getUbicacion());
		almacenDAO.eliminar(almacen);
		assertEquals(almacenes.size(),almacenDAO.getAlmacenes().size());
	}

}
