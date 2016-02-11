package santaclara.modelo;

public class Usuario {
	
	private Integer id;
	private String username;
	private String cedula;
	private String nombre;
	private String contrasena;
	
	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Usuario(Integer id, String username, String cedula, String nombres,
			String contrasena) {
		super();
		this.id = id;
		this.username = username;
		this.cedula = cedula;
		this.nombre = nombres;
		this.contrasena = contrasena;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombres) {
		this.nombre = nombres;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
 
}
