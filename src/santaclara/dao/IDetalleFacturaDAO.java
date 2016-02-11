package santaclara.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import santaclara.modelo.DetalleFactura;

public interface IDetalleFacturaDAO extends IGenericoDAO {

	public List<DetalleFactura>  getDetalles() throws FileNotFoundException, NumberFormatException, IOException;
	
	public List<DetalleFactura>  getDetalleFacturados() throws FileNotFoundException, NumberFormatException, IOException;
	
	public List<DetalleFactura>  getDetallePedidos() throws FileNotFoundException, NumberFormatException, IOException;
	
	public List<DetalleFactura>  getDetallePendientes() throws FileNotFoundException, NumberFormatException, IOException;
	
	public void	guardar(List<DetalleFactura> detalleFactura) throws IOException;
	
	public void eliminar(DetalleFactura detalleFactura) throws   IOException;
	
	public DetalleFactura getDetalleFactura(Integer idFactura,Integer idProducto) throws FileNotFoundException, NumberFormatException, IOException;

}
