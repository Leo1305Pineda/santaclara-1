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
