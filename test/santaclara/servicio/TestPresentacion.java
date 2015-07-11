package santaclara.servicio;

import static org.junit.Assert.*;

import org.junit.Test;

import com.sun.org.omg.CORBA.ExceptionDescription;

import santaclara.Servicio.ServicioPresentaciones;
import santaclara.dao.impl.PresentacionDAO;

public class TestPresentacion {

	@Test
	public void test() throws Exception {

      ServicioPresentaciones presentaciones = new ServicioPresentaciones();
      PresentacionDAO presentacionDAO = new PresentacionDAO();
      
      assertNull(presentacionDAO);      
      assertNull(presentaciones.getPresentaciones());
      assertNotNull(presentaciones.getPresentaciones());
      assertNull(presentaciones.getPresentaciones());
	
	}

}
