package santaclara.modelo;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import org.junit.Test;
import santaclara.dao.impl.DomicilioComercioDAO;
import santaclara.modelo.DomicilioComercio; 
public class TestDomicilioComercialDAO {

	@Test
	public void domicilioComercioTest() throws FileNotFoundException {
		DomicilioComercioDAO domicilioComercialDAO = new DomicilioComercioDAO();
		assertNotNull(domicilioComercialDAO);
		assertEquals(2,domicilioComercialDAO.getDomicilioComercios().size());
	}

	@Test
	public void addRemoveDomicilioComercioTest() throws IOException {
		DomicilioComercioDAO domicilioComercioDAO = new DomicilioComercioDAO();
		List<DomicilioComercio> domicilioComercios= domicilioComercioDAO.getDomicilioComercios();
		
		DomicilioComercio domicilioComercio1 = new DomicilioComercio();
		domicilioComercio1.setId(null);
		domicilioComercio1.setRif("V-1204402");
		domicilioComercio1.setRazonsocial("Juana Petra");
		domicilioComercio1.setDireccion("aquiiiiiD ");
		domicilioComercio1.setRuta(null);
		domicilioComercio1.setTelefono("0913091313");
		domicilioComercio1.setTipo("C");
				
		domicilioComercioDAO.guardar(domicilioComercio1);
		assertNotNull(domicilioComercio1.getId());
		assertNotEquals(1,domicilioComercioDAO.getDomicilioComercios().size());
		assertEquals(domicilioComercios.size()+1,domicilioComercioDAO.getDomicilioComercios().size());

		domicilioComercioDAO.eliminar(domicilioComercio1);
		assertEquals(domicilioComercios.size(),domicilioComercioDAO.getDomicilioComercios().size());
	}

	
}
