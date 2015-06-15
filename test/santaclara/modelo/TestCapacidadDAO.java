package santaclara.modelo;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import santaclara.dao.impl.CapacidadDAO;

public class TestCapacidadDAO {

	@Test
	public void capacidadTest() throws FileNotFoundException {
		CapacidadDAO capacidadDAO = new CapacidadDAO();
		assertNotNull(capacidadDAO);
		assertEquals(4,capacidadDAO.getCapacidades().size());
	}
	@Test
	public void addRemoveCapacidadTest() throws IOException {
		CapacidadDAO capacidadDAO = new CapacidadDAO();
		List<Capacidad> capacidades= capacidadDAO.getCapacidades();
		
		Capacidad capacidad1 = new Capacidad(null,new Double(500));
		
		capacidadDAO.guardar(capacidad1);
		
		assertNotNull(capacidad1.getId());
		//System.out.println(sabores.size()+"="+soborDAO.getSabores().size());
		assertNotEquals(capacidades.size(),capacidadDAO.getCapacidades().size());
		assertEquals(capacidades.size()+1,capacidadDAO.getCapacidades().size());

		capacidadDAO.eliminar(capacidad1);
		assertEquals(capacidades.size(),capacidadDAO.getCapacidades().size());
	}



}
