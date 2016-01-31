package santaclara.reporte;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import santaclara.Servicio.ServicioFactura;
import santaclara.modelo.Factura;

public class TestReportMontoFacturadoAlmacen {

	private ServicioFactura servicioFactura = new ServicioFactura();
	
	@Test
	public void reporteTest() throws FileNotFoundException{
	
		assertNotNull(servicioFactura);
		assertEquals(2,servicioFactura.getFacturas().size());
	}
	
	@Test
	public void MontoFacturaTest() throws IOException {
		List<Factura> facturas= servicioFactura.getFacturas();
		List<Factura> facturasAux = new ArrayList<Factura>();
		Double monto = new Double(0);
	
		for(Factura factura: facturas){
			if (factura.getAlmacen().getId().equals(1))
			{
				facturasAux.add(factura);
				monto = monto + factura.getTotalAPagar();
				System.out.println("factura del Almacen 1: "+factura.getId());
			}	
		}
		
		for(Factura factura: facturasAux){
			if (factura.getFecha().equals(facturasAux))
			{
				facturasAux.add(factura);
				monto = monto + factura.getTotalAPagar();
				System.out.println("factura del Almacen 1: "+factura.getId());
			}	
		}
		
		
		System.out.println("monto total"+ monto );
	}

}
