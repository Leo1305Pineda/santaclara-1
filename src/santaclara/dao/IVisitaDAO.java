/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.dao;

import java.util.Date;
import java.util.List;

import santaclara.modelo.Visita;

public interface IVisitaDAO extends IGenericoDAO {

	public List<Visita>  getVisitas() throws Exception;

	public void	guardar(Visita visita) throws Exception;
	
	public void eliminar(Visita visita) throws   Exception;
	
	public Visita getVisita(Date fecha,Integer idJefeVenta,Integer idCliente) throws Exception;
		
}	
