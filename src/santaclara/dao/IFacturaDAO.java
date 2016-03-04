/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.dao;

import java.util.List;

import santaclara.modelo.Factura;

public interface IFacturaDAO extends IGenericoDAO {

	public List<Factura>  getFacturas() throws Exception;
	
	public List<Factura>  getPedidoFacturados() throws Exception;
	
	public List<Factura>  getPedidoPendientes() throws Exception;
	
	public List<Factura>  getPedidos() throws Exception;

	public void	guardar(Factura factura) throws Exception;
	
	public void eliminar(Factura factura) throws   Exception;
	
	public Factura getFactura(Integer id) throws Exception;

	public List<Factura> confGetFactura(Boolean condicion)throws Exception;

}
