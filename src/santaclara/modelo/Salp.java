/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.modelo;

public class Salp extends Cliente{

	public Salp(Cliente cliente) {
		super(cliente.getId(), cliente.getRif(), 
				cliente.getRazonsocial(), cliente.getDireccion(), 
				cliente.getTelefono(), cliente.getRuta());
		// TODO Auto-generated constructor stub
	}

	public Salp() {
		super();
		// TODO Auto-generated constructor stub
	}

}
