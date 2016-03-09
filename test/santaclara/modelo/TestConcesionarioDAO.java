package santaclara.modelo;


import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import santaclara.dao.IConcesionarioDAO;
import santaclara.dao.impl.CamionDAO;
import santaclara.dao.impl.ConcesionarioDAO;
import santaclara.dao.impl.RutaDAO;
import santaclara.modelo.Concesionario;
public class TestConcesionarioDAO {

	@Test
	public void concesionariosTest() throws Exception {
		IConcesionarioDAO concesionarioDAO = new ConcesionarioDAO();
		assertNotNull(concesionarioDAO);
		assertNotNull(concesionarioDAO.getConcesionario(7));
		assertEquals(6,concesionarioDAO.getConcecionarios().size());
	}

	/*
	@Test
	public void addRemoveConcesionarioTest() throws Exception {
		IConcesionarioDAO concesionarioDAO = new ConcesionarioDAO();
		List<Concesionario> concesionarios = concesionarioDAO.getConcecionarios();
		
		Concesionario concesionario = new Concesionario();
		concesionario.setCedula("V-8796484");
		concesionario.setContrasena("1234");
		concesionario.setNombre("Pedro Perez");
		concesionario.setUsername("Vgowen");
		
		RutaDAO rutaDAO = new RutaDAO();
		concesionario.setRuta(rutaDAO.getRuta(2));
		
		CamionDAO camionDAO = new CamionDAO();
		concesionario.setCamion(camionDAO.getCamion(2));
		
		
		concesionarioDAO.guardar(concesionario);
		assertNotNull(concesionario.getId());
		assertNotEquals(2,concesionarioDAO.getConcecionarios().size());
		assertEquals(concesionarios.size()+1,concesionarioDAO.getConcecionarios().size());
		concesionarioDAO.eliminar(concesionario);
		assertEquals(concesionarios .size(),concesionarioDAO.getConcecionarios().size());
	}
*/	
}
