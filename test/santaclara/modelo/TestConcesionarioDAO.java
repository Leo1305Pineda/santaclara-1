package santaclara.modelo;


import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import santaclara.dao.IConcesionarioDAO;
import santaclara.dao.impl.ConcesionarioDAO;
import santaclara.modelo.Concesionario;
public class TestConcesionarioDAO {

	@Test
	public void concesionariosTest() throws FileNotFoundException {
		IConcesionarioDAO concesionarioDAO = new ConcesionarioDAO();
		assertNotNull(concesionarioDAO);
		assertEquals(1,concesionarioDAO.getConcecionarios().size());
	}

	@Test
	public void addRemoveConcesionarioTest() throws IOException {
		IConcesionarioDAO concesionarioDAO = new ConcesionarioDAO();
		List<Concesionario> concesionarios = concesionarioDAO.getConcecionarios();
		
		Concesionario concesionario = new Concesionario();
		concesionario.setCedula("V-8796484");
		concesionario.setContrasena("1234");
		concesionario.setNombre("Pedro Perez");
		concesionario.setUsername("Vgowen");
		Ruta ruta = new Ruta();
		ruta.setId(1);
		Camion camion = new Camion();
		camion.setId(1);
		concesionario.setRuta(ruta);
		concesionario.setCamion(camion);
		
		concesionarioDAO.guardar(concesionario);
		assertNotNull(concesionario.getId());
		assertNotEquals(4,concesionarioDAO.getConcecionarios().size());
		assertEquals(concesionarios .size()+1,concesionarioDAO.getConcecionarios().size());

		concesionarioDAO.eliminar(concesionario);
		assertEquals(concesionarios .size(),concesionarioDAO.getConcecionarios().size());
	}
	
}
