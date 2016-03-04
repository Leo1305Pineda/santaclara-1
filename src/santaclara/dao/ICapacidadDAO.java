/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.dao;

import java.util.List;

import santaclara.modelo.Capacidad;

public interface ICapacidadDAO extends IGenericoDAO{
	
	public List<Capacidad>  getCapacidades() throws Exception;

	public void	guardar(Capacidad capacidad) throws Exception;
	
	public void eliminar(Capacidad capacidad) throws   Exception;
	
	public Capacidad getCapacidad(Integer id) throws Exception;
}
