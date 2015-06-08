package santaclara.modelo;

public class Camion {
	private String placa;
	private String Color;
	private Double capacidad;
	private String modelo;
	private String marca;
	private String anno;
	

	public Camion(String placa, String color, Double capacidad, String modelo,
			String marca, String anno) {
		super();
		this.placa = placa;
		Color = color;
		this.capacidad = capacidad;
		this.modelo = modelo;
		this.marca = marca;
		this.anno = anno;
	}


	public String getPlaca() {
		return placa;
	}


	public void setPlaca(String placa) {
		this.placa = placa;
	}


	public String getColor() {
		return Color;
	}


	public void setColor(String color) {
		Color = color;
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


	public String getAnno() {
		return anno;
	}


	public void setAnno(String anno) {
		this.anno = anno;
	}

	public Camion() {
		// TODO Auto-generated constructor stub
	}

}
