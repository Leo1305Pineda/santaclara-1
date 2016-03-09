/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.dao;
import java.util.List;

import santaclara.modelo.Concesionario;

public interface IConcesionarioDAO extends IGenericoDAO{

	public List<Concesionario>  getConcecionarios() throws Exception;

	public void	guardar(Concesionario concesionario) throws Exception;
	
	public void eliminar(Concesionario concesionario) throws   Exception;
	
	public Concesionario getConcesionario(Integer id) throws Exception;
 
	public Concesionario getConcesionario(String nombre) throws Exception;
	
}

