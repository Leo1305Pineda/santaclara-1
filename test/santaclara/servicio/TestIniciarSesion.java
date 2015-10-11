package santaclara.servicio;

import static org.junit.Assert.*;

import org.junit.Test;

import santaclara.Servicio.ServicioIniciarSesion;

public class TestIniciarSesion {

	@Test
	public void test() throws Exception {

      ServicioIniciarSesion iniciarSesion = new ServicioIniciarSesion();
      
      assertNull(iniciarSesion.iniciarSesion("fghghg","jfhjh"));
      assertNotNull(iniciarSesion.iniciarSesion("Rhonal","1234"));
      assertNull(iniciarSesion.iniciarSesion("Rhonal","12dsdsd"));
      
	
	
	}

}
