/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.modelo;

public class JefeVenta  extends  Usuario {
	
	private Zona zona;
	
	public JefeVenta(Integer id, String username, String cedula,
			String nombres, String contrasena,Zona zona) {
		super(id, username, cedula, nombres, contrasena);
		// TODO Auto-generated constructor stub
		this.zona = zona;
	}
	public JefeVenta(){
		// TODO Auto-generated constructor stub
		super();
	}
	public Zona getZona() {
		return zona;
	}

	public void setZona(Zona zona) {
		this.zona = zona;
	}

}
