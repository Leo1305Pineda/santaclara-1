package santaclara.modelo;


import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import santaclara.dao.impl.CamionDAO;
import santaclara.dao.impl.ConcesionarioRutaDAO;
import santaclara.dao.impl.RutaDAO;
public class TestConcesionarioRutaDAO {

	@Test
	public void concesionarioRutasTest() throws FileNotFoundException {
		ConcesionarioRutaDAO concesionarioRutaDAO = new ConcesionarioRutaDAO();
		assertNotNull(concesionarioRutaDAO);
		assertEquals(2,concesionarioRutaDAO.getConcesionarioRutas().size());
	}

	@Test
	public void addRemoveConcesionarioRutaTest() throws IOException {
		ConcesionarioRutaDAO concesionarioRutaDAO = new ConcesionarioRutaDAO();
		List<ConcesionarioRuta> concesionarioRutas = concesionarioRutaDAO.getConcesionarioRutas();
		
		ConcesionarioRuta concesionarioRuta = new ConcesionarioRuta();
		
		RutaDAO rutaDAO = new RutaDAO();
		Cliente cliente = new Cliente(2,"V-15432675","bskdd","rwergdrfidj","736366326",rutaDAO.getRuta(1));
		concesionarioRuta.setCliente(cliente);
		
		CamionDAO camionDAO = new CamionDAO();
		Concesionario concesionario = new Concesionario(1,"jose","78363","hdgkhd","23234",camionDAO.getCamion(1),rutaDAO.getRuta(1));
		concesionarioRuta.setConcesionario(concesionario);
		
		concesionarioRuta.setRuta(rutaDAO.getRuta(1));
		
		concesionarioRuta.setDias(",1,2,3,");
			
		concesionarioRutaDAO.guardar(concesionarioRuta);
		//assertNotNull(concesionarioRuta.getId());
		assertNotEquals(1,concesionarioRutaDAO.getConcesionarioRutas().size());
		assertEquals(concesionarioRutas.size()+1,concesionarioRutaDAO.getConcesionarioRutas().size());

		concesionarioRutaDAO.eliminar(concesionarioRuta);
		assertEquals(concesionarioRutas .size(),concesionarioRutaDAO.getConcesionarioRutas().size());
	}
}
