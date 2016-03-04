/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.dao;

import java.util.List;

import santaclara.modelo.DomicilioComercio;

public interface IDomicilioComercioDAO extends IGenericoDAO {

	public List<DomicilioComercio>  getDomicilioComercios() throws Exception;

	public void	guardar(DomicilioComercio domicilioComercio) throws Exception;
	
	public void eliminar(DomicilioComercio domicilioComercio) throws   Exception;
	
	public DomicilioComercio getDomicilioComercio(Integer id) throws Exception;
		
}	
