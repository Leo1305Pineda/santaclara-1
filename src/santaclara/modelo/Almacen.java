package santaclara.modelo;//Cambio

public class Almacen {
	private Integer id;
	private String ubicacion;
	
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

	public Almacen() {
		// TODO Auto-generated constructor stub
	}

}
