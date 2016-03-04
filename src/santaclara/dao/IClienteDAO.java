/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.dao;

import java.util.List;

import santaclara.modelo.Cliente;

public interface IClienteDAO extends IGenericoDAO {

	public List<Cliente>  getClientes() throws Exception;

	public void	guardar(Cliente cliente) throws Exception;
	
	public void eliminar(Cliente cliente) throws   Exception;
	
	public Cliente getCliente(Integer id) throws Exception;
	
	public Boolean getCliente(Cliente cliente) throws Exception;

	public Cliente getCliente(String rif) throws Exception;

}	
