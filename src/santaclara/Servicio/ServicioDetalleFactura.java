/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.Servicio;

import java.util.ArrayList;
import java.util.List;

import santaclara.dao.impl.DetalleFacturaDAO;
import santaclara.modelo.DetalleFactura;
import santaclara.modelo.Factura;

public class ServicioDetalleFactura {

	private DetalleFacturaDAO detalleFacturaDAO = new DetalleFacturaDAO();
	
	//retorna todo el detalle factura facturado y en pedido
	public List<DetalleFactura> getDetalles() throws Exception{
		return detalleFacturaDAO.getDetalles();
	}
	//returna solo el detalle facturado
	public List<DetalleFactura> getDetalleFacturas() throws Exception{
		
		return detalleFacturaDAO.getDetalleFacturados();
	}
	
	public List<DetalleFactura> getDetallePendientes() throws Exception{
		
		return detalleFacturaDAO.getDetallePendientes();
	}
	
	public List<DetalleFactura> getDetallePedidos() throws Exception{
		
		return detalleFacturaDAO.getDetallePedidos();
	}
	
 
	public List<DetalleFactura> getDetalleFacturas(Factura factura) throws Exception{
		// TODO Auto-generated method stub
		List<DetalleFactura> detalleFacturas = new ArrayList<DetalleFactura>();
		for(DetalleFactura detalleFactura : getDetalleFacturas()){
 
			if (detalleFactura.getFactura().getId().equals(factura.getId())) detalleFacturas.add(detalleFactura);
 
		}
		return detalleFacturas;
	}
	 
	
	public void guardar(List<DetalleFactura> detalle) throws Exception{
		detalleFacturaDAO.guardar(detalle);
		}

	public void eliminar(DetalleFactura detallePedido) throws Exception{
		detalleFacturaDAO.eliminar(detallePedido);
	}
	/*
	 * public Integer idValue(String groupBy,DetalleFactura detalleFactura){
		switch (groupBy) {
		case "Month":return detalleFactura.getFactura().getFecha().getMonth();
		case "Almacen": return detalleFactura.getFactura().getAlmacen().getId();
		default:	return null;
		}
	
	}*/
 
}
