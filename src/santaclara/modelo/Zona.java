package santaclara.modelo;

import java.util.List;

public class Zona {
	
	private Integer	id; 
	private String descripcion;
	
	public Zona() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Zona(String descripcion, List<Ruta> rutas) {
		super();
		this.descripcion = descripcion;
	}
	 
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	 
}
