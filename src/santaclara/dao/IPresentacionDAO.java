/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.dao;

import java.util.List;

import santaclara.modelo.Presentacion;

public interface IPresentacionDAO extends IGenericoDAO{
	
	public List<Presentacion>  getPresentaciones() throws Exception;

	public void	guardar(Presentacion Presentasion) throws Exception;
	
	public void eliminar(Presentacion Presentacion) throws   Exception;
	
	public Presentacion getPresentacion(Integer id) throws Exception;
	
}
