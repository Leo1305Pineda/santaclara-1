package santaclara.Servicio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import santaclara.dao.impl.DetalleFacturaDAO;
import santaclara.modelo.DetalleFactura;
import santaclara.modelo.Factura;

public class ServicioDetalleFactura {
	
	public List<DetalleFactura> getDetalleFacturas() throws NumberFormatException, IOException{
		return new DetalleFacturaDAO().getDetalleFacturas();
	}
	

	public List<DetalleFactura> getDetalleFacturas(Factura factura) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		List<DetalleFactura> detalleFacturas = new ArrayList<DetalleFactura>();
		for(DetalleFactura detalleFactura : getDetalleFacturas()){
			if (detalleFactura.getFactura().getId().equals(factura.getId())){
				detalleFacturas.add(detalleFactura);
			}
		}
		return detalleFacturas;
	}
	
	public void guardar(DetalleFactura detalleFactura) throws IOException{
		new DetalleFacturaDAO().guardar(detalleFactura);
	}
	
	public void guardar(List<DetalleFactura> detalleFacturas) throws IOException{
		for (DetalleFactura detalleFactura : detalleFacturas)
		{ 
			if (detalleFactura != null)
			{
				guardar(detalleFactura);
			}
		}
	}

	public void eliminar(DetalleFactura detalleFactura) throws IOException{
		new DetalleFacturaDAO().eliminar(detalleFactura);
	}
}
