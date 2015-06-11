package santaclara.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import santaclara.modelo.DetalleFactura;

public interface IDetalleFacturaDAO extends IGenericoDAO {

	public List<DetalleFactura>  getDetalleFacturas() throws FileNotFoundException;

	public void	guardar(DetalleFactura detalleFactura) throws IOException;
	
	public void eliminar(DetalleFactura detalleFactura) throws   IOException;
	
	public DetalleFactura getDetalleFactura(Integer idFactura,Integer idProducto) throws FileNotFoundException;

}
