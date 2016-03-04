/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.dao;

import java.util.List;

import santaclara.modelo.DetalleFactura;

public interface IDetalleFacturaDAO extends IGenericoDAO {

	public List<DetalleFactura>  getDetalles() throws Exception;
	
	public List<DetalleFactura>  getDetalleFacturados() throws Exception;
	
	public List<DetalleFactura>  getDetallePedidos() throws Exception;
	
	public List<DetalleFactura>  getDetallePendientes() throws Exception;
	
	public void	guardar(List<DetalleFactura> detalleFactura) throws Exception;
	
	public void eliminar(DetalleFactura detalleFactura) throws   Exception;
	
	public DetalleFactura getDetalleFactura(Integer idFactura,Integer idProducto) throws Exception;

}
