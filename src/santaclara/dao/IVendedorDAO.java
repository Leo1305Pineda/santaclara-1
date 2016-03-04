/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.dao;

import java.util.List;
import santaclara.modelo.Vendedor;

public interface IVendedorDAO extends IGenericoDAO{

	public List<Vendedor>  getVendedores() throws Exception;

	public void	guardar(Vendedor vendedor) throws Exception;
	
	public void eliminar(Vendedor vendedor) throws   Exception;
	
	public Vendedor getVendedor(Integer id) throws Exception;

}
