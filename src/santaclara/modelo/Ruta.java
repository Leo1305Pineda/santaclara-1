package santaclara.modelo;

public class Ruta {
	
	private Integer id;
	private Zona zona;
	private String nombre;

	public Ruta() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Ruta(Integer id, Zona zona, String nombre, String sector) {
		super();
		this.id = id;
		this.zona = zona;
		this.nombre = nombre;
	}
	public Ruta(Integer id ) {
		super();
		this.id = id; 
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Zona getZona() {
		return zona;
	}
	public void setZona(Zona zona) {
		this.zona = zona;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
