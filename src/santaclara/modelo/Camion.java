/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.modelo;

public class Camion {

	private Integer id;
	private String  placa;
	private String  color;
	private Double  capacidad;
	private String  modelo;
	private String  marca;
	private String  ano;
	
	public Camion() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Camion(Integer id, String placa, String color, Double capacidad,
			String modelo, String marca, String ano) {
		super();
		this.id = id;
		this.placa = placa;
		this.color = color;
		this.capacidad = capacidad;
		this.modelo = modelo;
		this.marca = marca;
		this.ano = ano;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Double getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(Double capacidad) {
		this.capacidad = capacidad;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}

}
