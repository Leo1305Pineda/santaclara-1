package santaclara.modelo;


import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import santaclara.dao.impl.JefeVentaDAO;
public class TestJefeVentaDAO {

	@Test
	public void JefeVentaTest() throws Exception {
		JefeVentaDAO jefeVentaDAO = new JefeVentaDAO();
		assertNotNull(jefeVentaDAO);
		assertEquals(3,jefeVentaDAO.getJefeVentas().size());
	}

	@Test
	public void addRemoveConcesionarioTest() throws IOException {
		/*IConcesionarioDAO concesionarioDAO = new ConcesionarioDAO();
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
	*/
		
		
	}
}
