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
	
	public String getAlmacenStr(){
		return id+"  "+ubicacion;
	}
}
