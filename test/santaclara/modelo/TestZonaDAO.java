package santaclara.modelo;

import java.math.*;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;

import santaclara.dao.impl.ZonaDAO;

public class TestZonaDAO {

	@Test
	public void saboresTest() throws FileNotFoundException {
		ZonaDAO zonaDAO = new ZonaDAO();
		assertNotNull(zonaDAO);
		assertEquals(5,zonaDAO.getZonas().size());
	}
	
	public void p ()
	{
		int t;
    	Long n;
    	Scanner s = new Scanner(System.in);
    	t = s.nextInt();
    	
    	for(int i = t; i>0 ; ++i)
    	{
    		n = new Long(s.nextLine().trim());
    		Double b =5.*Math.pow(n,2)+4;
    		Double a =5.*Math.pow(n,2)-4;
    		if(Math.pow((int) Math.sqrt(b),2) == b ||
    				Math.pow((int) Math.sqrt(a),2) == a  )
    		{
    			System.out.println("IsFibo ");
    		}
    		else
    		{
    			System.out.println("IsNotFibo ");
    		}
    		
    	}
	}


}
