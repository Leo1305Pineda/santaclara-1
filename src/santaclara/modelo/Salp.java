package santaclara.modelo;

import java.util.ArrayList;
import java.util.List;

public class Salp extends Cliente{
	private List<Factura> facturas = new ArrayList<Factura>();

	public Salp() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Salp(List<Factura> facturas) {
		super();
		this.facturas = facturas;
	}
	
	public List<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}
 

}
