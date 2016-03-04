/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.modelo;

public class Capacidad {
	private Integer id;
	private Double volumen;
	
	public Capacidad(Integer id, Double volumen) {
		super();
		this.id = id;
		this.volumen = volumen;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getVolumen() {
		return volumen;
	}

	
	public String getVolumenStr() {
		return volumen.toString()+" ml.";
	}

	public void setVolumen(Double volumen) {
		this.volumen = volumen;
	}

	
		
	public Capacidad() {
		// TODO Auto-generated constructor stub
	}

}
