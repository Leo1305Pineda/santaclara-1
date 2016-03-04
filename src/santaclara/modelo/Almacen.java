/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.modelo;

public class Almacen{
	private Integer id;
	private String ubicacion;
	
	
	public Almacen() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Almacen(Integer id, String ubicacion) {
		super();
		this.id = id;
		this.ubicacion = ubicacion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
}
