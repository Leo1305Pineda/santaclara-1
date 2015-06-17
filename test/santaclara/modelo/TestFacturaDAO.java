package santaclara.modelo;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import santaclara.dao.impl.ClienteDAO;
import santaclara.dao.impl.FacturaDAO;
import santaclara.dao.impl.VendedorDAO;
import santaclara.modelo.Factura; 
public class TestFacturaDAO {

	@Test
	public void facturaTest() throws FileNotFoundException {
		FacturaDAO facturaDAO = new FacturaDAO();
		assertNotNull(facturaDAO);
		assertEquals(2,facturaDAO.getFacturas().size());
	}

	@Test
	public void addRemoveFacturaTest() throws IOException {
		FacturaDAO facturaDAO = new FacturaDAO();

		List<Factura> facturas= facturaDAO.getFacturas();
		
		ClienteDAO clienteDAO = new ClienteDAO();
		VendedorDAO vendedorDAO = new VendedorDAO();
		
		@SuppressWarnings("deprecation")
		Factura factura1 = new Factura(
				null,new Date("2015/10/05"),new Double("655"),
				new Double("8329"),new Double("655"),
				new Double("655"),
				clienteDAO.getCliente(1),
				vendedorDAO.getVendedor(1));
			
		facturaDAO.guardar(factura1);
		assertNotNull(factura1.getId());
		assertNotEquals(1,facturaDAO.getFacturas().size());
		assertEquals(facturas.size()+1,facturaDAO.getFacturas().size());

		//facturaDAO.eliminar(factura1);
		assertEquals(facturas.size(),facturaDAO.getFacturas().size());
	}

	
}
