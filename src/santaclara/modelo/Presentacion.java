/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.modelo;

public class Presentacion {
	private Integer id;
	private String material;
	
	public Integer getId() {
		return id;
	}


	public Presentacion(Integer id, String material) {
		super();
		this.id = id;
		this.material = material;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getMaterial() {
		return material;
	}


	public void setMaterial(String material) {
		this.material = material;
	}
	
	public Presentacion() {
		// TODO Auto-generated constructor stub
	}

}
