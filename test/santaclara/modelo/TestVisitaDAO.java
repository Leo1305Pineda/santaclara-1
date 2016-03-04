package santaclara.modelo;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Test;

import santaclara.dao.impl.VisitaDAO;


public class TestVisitaDAO {

	@Test
	public void visitasTest() throws Exception {
		VisitaDAO visitaDAO = new VisitaDAO();
		assertNotNull(visitaDAO);
		assertEquals(15,visitaDAO.getVisitas().size());
		
		
	}
	
	@Test
	public void addRemoveVisitaTest() throws Exception {
		VisitaDAO visitaDAO = new VisitaDAO(); 
		List<Visita> visitas = visitaDAO.getVisitas();


		assertNotNull(new VisitaDAO().getVisita(new SimpleDateFormat("dd/MM/yyyy").parse("06/06/2015"), 2, 4));
		
		Visita visita = new Visita();

		assertNotNull(visita);
		
		visita = visitaDAO.getVisita(new SimpleDateFormat("dd/MM/yyyy").parse("06/06/2015"), 2, 4);
		
		visitaDAO.guardar(visita); 
		assertNotNull(visita.getId());
		assertNotEquals(14,visitaDAO.getVisitas().size());
		assertEquals(visitas.size()+1,visitaDAO.getVisitas().size());

		visitaDAO.eliminar(visita);
		assertEquals(visitas.size(),visitaDAO.getVisitas().size());
	
	}


	

}
