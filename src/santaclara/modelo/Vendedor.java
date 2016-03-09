/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.modelo;
 
import java.util.List;

public class Vendedor extends Usuario  {
	
	private List<Ruta> rutas;

	public Vendedor(Integer id, String username, String cedula, String nombres,
			String contrasena,List<Ruta> rutas) {
		super(id, username, cedula, nombres, contrasena);
		// TODO Auto-generated constructor stub
		this.rutas=rutas;
	}

	public Vendedor(List<Ruta> rutas) {
		super();
		this.rutas = rutas;
	}
	
	
	public Vendedor() {
		super();
	}

	public List<Ruta> getRutas() {
		return rutas;
	}

	public void setRutas(List<Ruta> rutas) {
		this.rutas = rutas;
	}
 
 
}
