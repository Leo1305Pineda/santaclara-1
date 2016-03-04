/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.modelo;
import santaclara.modelo.Camion;
import santaclara.modelo.Ruta;

public class Concesionario extends Usuario{
	private Camion camion;
	private Ruta ruta;
	
	
	public Concesionario(Usuario concesionario,Camion camion, Ruta ruta) {
		super( concesionario.getId(), concesionario.getUsername(),
				concesionario.getCedula(), concesionario.getNombre() ,concesionario.getContrasena());
		this.camion = camion;
		this.ruta = ruta;
	}
	
	public Concesionario(Camion camion, Ruta ruta) {
		super();
		this.camion = camion;
		this.ruta = ruta;
	}

	public Camion getCamion() {
		return camion;
	}

	public void setCamion(Camion camion) {
		this.camion = camion;
	}

	public Ruta getRuta() {
		return ruta;
	}


	public void setRuta(Ruta ruta) {
		this.ruta = ruta;
	}


	public Concesionario() {
		// TODO Auto-generated constructor stub
	}

}
