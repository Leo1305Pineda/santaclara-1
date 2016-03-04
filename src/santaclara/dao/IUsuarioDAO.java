/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.dao;

import java.util.List;

import santaclara.modelo.Usuario;

public interface IUsuarioDAO extends IGenericoDAO{
	public List<Usuario>  getUsuarios() throws Exception;

	public void	guardar(Usuario usuario) throws Exception;
	
	public void eliminar(Usuario usuario) throws   Exception;
	
	public Usuario getUsuario(String username) throws Exception;

}
