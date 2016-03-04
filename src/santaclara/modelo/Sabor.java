/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.modelo;

public class Sabor {
	private Integer id;
	private String sabor;
	
	public Sabor(Integer id, String sabor) {
		super();
		this.id = id;
		this.sabor = sabor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSabor() {
		return sabor;
	}

	public void setSabor(String sabor) {
		this.sabor = sabor;
	}
	
	
	public Sabor() {
		// TODO Auto-generated constructor stub
	}

}
