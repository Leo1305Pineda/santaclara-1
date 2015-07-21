package santaclara.modelo;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import santaclara.dao.impl.FacturaDAO;
import santaclara.dao.impl.SalpDAO;
import santaclara.modelo.Salp; 
public class TestSalpDAO {

	@Test
	public void salpTest() throws FileNotFoundException {
		SalpDAO salpDAO = new SalpDAO();
		assertNotNull(salpDAO);
		assertEquals(2,salpDAO.getSalps().size());
	}

	@Test
	public void addRemoveSalpTest() throws IOException {
		SalpDAO salpDAO = new SalpDAO();
		List<Salp> salps= salpDAO.getSalps();
		
		Salp salp1 = new Salp();
		salp1.setId(null);
		salp1.setRif("V-1204402");
		salp1.setRazonsocial("Juana Petra");
		salp1.setDireccion("aquiiiiiD ");
		salp1.setRuta(null);
		salp1.setTelefono("0913091313");
		
		List<Factura> facturas = new ArrayList<Factura>();
		FacturaDAO facturaDAO = new FacturaDAO();
		facturas.add(facturaDAO.getFactura(0));
		facturas.add(facturaDAO.getFactura(1));
	//	salp1.setFacturas(facturas);
		
		salpDAO.guardar(salp1);
		assertNotNull(salp1.getId());
		assertNotEquals(1,salpDAO.getSalps().size());
		assertEquals(salps.size()+1,salpDAO.getSalps().size());

		salpDAO.eliminar(salp1);
		assertEquals(salps.size(),salpDAO.getSalps().size());
	}

	
}
