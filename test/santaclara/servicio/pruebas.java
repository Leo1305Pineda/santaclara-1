package santaclara.servicio;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

public class pruebas {
	
	@SuppressWarnings("deprecation")
	@Test
	public void test() throws Exception {
		 
		/*String fechaStr = new String("13/05/1991");
		
		System.out.println("fecha con el string = "+fechaStr);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
		System.out.println("fecha con el sdf.parse = "+sdf.parse(fechaStr));
		
		@SuppressWarnings("deprecation")
		Date fechaDate = new Date(fechaStr); 
	
		System.out.println("fecha con el Date = "+fechaDate);
		
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/mm/yyyy");
		
		System.out.println("fecha con el sdf.forma(fechaStr) = "+new SimpleDateFormat("mm/dd/yyyy").format(sdf.parse(fechaStr)));
		
		System.out.println("fecha con el sdf.forma(date) = "+sdf2.format(fechaDate));
		
		//java.sql.Date dateSql = new java.sql.Date(0);*/
		
		
		
	//	setCkeckDiaVisita(getCheckDiaVisita(false,true,false,true,false,true,false));

		
		Date fecha = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		fecha = sdf.parse("26/09/2015");
		
		System.out.println(fecha.getDay());
		
		System.out.println(fecha.getDate());
		
		java.sql.Date f =  new java.sql.Date(fecha.getTime());
		
		verFechas(restarFechasDias(f, fecha.getDay()));
		
		
		
	    }
	
	
	 public static java.sql.Date sumarFechasDias(java.sql.Date fch, int dias) {
	        Calendar cal = new GregorianCalendar();
	        cal.setTimeInMillis(fch.getTime());
	        cal.add(Calendar.DATE, dias);
	        return new java.sql.Date(cal.getTimeInMillis());
	    }

	 public static synchronized java.sql.Date restarFechasDias(java.sql.Date fch, int dias) {
	        Calendar cal = new GregorianCalendar();
	        cal.setTimeInMillis(fch.getTime());
	        cal.add(Calendar.DATE, -dias);
	        return new java.sql.Date(cal.getTimeInMillis());
	    }
	 
	private void verFechas(java.sql.Date date) {
		// TODO Auto-generated method stub
		
		for(int i=0;i<=34;i++)
		{
			System.out.println(sumarFechasDias(date, i));
		}
		
	}


	public void setCkeckDiaVisita(Integer numero){
		 Integer exp, digito;
	        Double binario;
	        
	        exp= new Integer(0);
	        binario= new Double(0);
	        while(numero!=0){
	                digito = numero % 2;           
	                binario = binario + digito * Math.pow(10, exp);
	               
	                switch (exp) {
					case 0:
						if (new Integer((int) (digito * Math.pow(10, exp))).equals(1)) System.out.println("Domingo");
						break;
					case 1:if (new Integer((int) (digito * Math.pow(10, exp))).equals(10)) System.out.println("Lunes");
					break;
					case 2:if (new Integer((int) (digito * Math.pow(10, exp))).equals(100)) System.out.println("Marte");
					break;
					case 3:if (new Integer((int) (digito * Math.pow(10, exp))).equals(1000)) System.out.println("Miercole");
					break;
					case 4:if (new Integer((int) (digito * Math.pow(10, exp))).equals(10000)) System.out.println("Jueve");
					break;
					case 5:if (new Integer((int) (digito * Math.pow(10, exp))).equals(100000)) System.out.println("Vierne");
					break;
					case 6:if (new Integer((int) (digito * Math.pow(10, exp))).equals(1000000)) System.out.println("Sabado");
					break;

					default:
						break;
					}
	      
	                exp++;
	                numero = numero/2;
	        }
	}
	
	public Integer getCheckDiaVisita(Boolean getCheckDomingo,Boolean getCheckLune,
					Boolean getCheckMarte,Boolean getCheckMiencole,Boolean getCheckJueve,Boolean getCheckVierne,Boolean getCheckSabado){
		Integer decimal = new Integer(0);
		if(getCheckDomingo.booleanValue()) decimal = decimal + (int) Math.pow(2,0);
		if(getCheckLune.booleanValue()) decimal = decimal + (int) Math.pow(2,1);
		if(getCheckMarte.booleanValue()) decimal = decimal + (int) Math.pow(2,2);
		if(getCheckMiencole.booleanValue()) decimal = decimal + (int) Math.pow(2,3);
		if(getCheckJueve.booleanValue()) decimal = decimal + (int) Math.pow(2,4);
		if(getCheckVierne.booleanValue()) decimal = decimal + (int) Math.pow(2,5);
		if(getCheckSabado.booleanValue()) decimal = decimal + (int) Math.pow(2,6);
		return decimal;
	}
		
}
