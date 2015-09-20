package santaclara.servicio;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class pruebas {
	
	@Test
	public void test() throws Exception {
		 
		String fechaStr = new String("13/05/1991");
		
		System.out.println("fecha con el string = "+fechaStr);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
		System.out.println("fecha con el sdf.parse = "+sdf.parse(fechaStr));
		
		@SuppressWarnings("deprecation")
		Date fechaDate = new Date(fechaStr); 
	
		System.out.println("fecha con el Date = "+fechaDate);
		
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/mm/yyyy");
		
		System.out.println("fecha con el sdf.forma(fechaStr) = "+new SimpleDateFormat("mm/dd/yyyy").format(sdf.parse(fechaStr)));
		
		System.out.println("fecha con el sdf.forma(date) = "+sdf2.format(fechaDate));
		
		//java.sql.Date dateSql = new java.sql.Date(0);
		
	}
		
}
