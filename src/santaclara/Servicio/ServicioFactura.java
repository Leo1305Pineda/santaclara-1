package santaclara.Servicio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import santaclara.dao.impl.FacturaDAO;
import santaclara.modelo.Almacen;
import santaclara.modelo.Cliente;
import santaclara.modelo.Factura;
import santaclara.modelo.Usuario;

public class ServicioFactura {
	
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

	public List<Factura> getFacturas() throws FileNotFoundException{
		return new FacturaDAO().getFacturas();
	}
	 
	public List<Factura> getPedidoFacturados() throws FileNotFoundException{
		return new FacturaDAO().getPedidoFacturados();
	}
	 
	public List<Factura> getPedidoPendientes() throws FileNotFoundException{
		return new FacturaDAO().getPedidoPendientes();
	}
	
	public Factura getFactura(Integer id) throws FileNotFoundException{
		
		for (Factura factura : getFacturas()){
			if (factura.getId().equals(id)){
				return factura;
			}
		}
		return null; 
	}
	
	public Cliente getCliente(Integer id) throws FileNotFoundException{
		for(Factura factura : getFacturas()){
			if (factura.getCliente().getId().equals(id))
			{
				return factura.getCliente();
			}
		}
		return null;
	}
	
	public Almacen getAlmacen(Integer id) throws FileNotFoundException{
		for(Factura factura : getFacturas()){
			if (factura.getAlmacen().getId().equals(id))
			{
				return factura.getAlmacen();
			}
		}
		return null;
	}
	
	public Usuario getVendedor(Integer id) throws FileNotFoundException{
		for(Factura factura : getFacturas()){
			if (factura.getVendedor().getId().equals(id))
			{
				return factura.getVendedor();
			}
		}
		return null;
	}
	
	public void guardar(Factura factura) throws IOException{
		new FacturaDAO().guardar(factura);
	}
	
	public Integer ultimaFactura()throws IOException{
		return new FacturaDAO().ultimaFactura();
	}

	public double getPorcentajeDescuento() {
		// TODO Auto-generated method stub
		
		return 0;
	}

	public List<Factura> getFacturasGroupByCliente(Integer idCliente) throws FileNotFoundException{
		List<Factura> facturasGroupByCliente = new ArrayList<Factura>();
		for(Factura factura1 : getFacturas()){
			
			if(factura1.getCliente().getId().equals(idCliente))
			{
				facturasGroupByCliente.add(factura1);
			}	
		}
		return  facturasGroupByCliente;
	}
	
	@SuppressWarnings("deprecation")
	/*con este metodo determino si elcliente salp opta por el credito
	 * Solo los cliente salp opta por credito 
	 * dicho creditose aprobara si en los ultimos 6 meses todas sus factura superan elmonto de 800.000 bsf 
	 * y al menos tiene una por mes*/
	public Boolean isCredito (Factura factura) throws NumberFormatException, IOException{
		
		java.sql.Date fecha =  new java.sql.Date(factura.getFecha().getTime());// Fecha Actual
		
		Date  fechaini = restarFechasDias(fecha,6*30);
		
		List<Factura> facturasEntreFecha = new ArrayList<Factura>();
		for(Factura factura2 : getFacturasGroupByCliente(factura.getCliente().getId()))
		{
			if(fechaini.getTime() >= factura2.getFecha().getTime() &&
					factura2.getFecha().getTime() <= factura.getFecha().getTime()){
				facturasEntreFecha.add(factura2);
			}
		}
		//tiene almenos una factura por mes
		Double acumMont = new Double(0);
		Boolean enc = new Boolean(false);
		for(int i = 0 ; i <= factura.getFecha().getMonth(); i++)
		{
			Integer mes = new Integer(fechaini.getMonth()+i);
			enc = false;
			for(Factura factura2 : facturasEntreFecha)
			{
				if(mes.equals(factura2.getFecha().getMonth())){
					//Cambia a true si al menos hay una factura en el mes
					enc = true;
					acumMont = acumMont + factura2.getTotalAPagar();
				}
			}
			if(enc==false) break;
		}
		
		if (acumMont>=800.000)
		{
			return enc;
		}
		else return false;
	}
}
