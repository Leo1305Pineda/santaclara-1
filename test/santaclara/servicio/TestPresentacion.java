package santaclara.servicio;

import static org.junit.Assert.*;

import org.junit.Test;

import santaclara.Servicio.ServicioPresentacion;
import santaclara.dao.impl.PresentacionDAO;

public class TestPresentacion {

	@SuppressWarnings("unused")
	@Test
	public void test() throws Exception {

      ServicioPresentacion presentaciones = new ServicioPresentacion();
      PresentacionDAO presentacionDAO = new PresentacionDAO();
      
      assertNull(presentacionDAO);      
      assertNull(presentaciones.getPresentaciones());
      assertNotNull(presentaciones.getPresentaciones());
      assertNull(presentaciones.getPresentaciones());
	
	}

}
