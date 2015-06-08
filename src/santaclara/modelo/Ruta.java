package santaclara.modelo;

import java.util.ArrayList;
import java.util.List;

public class Ruta {
	
	private Integer id;
	private Zona zona;
	private String nombre;
	private String sector;
	private List<Cliente> clientes = new ArrayList<Cliente>();
	
	
	public Ruta() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Ruta(Integer id, Zona zona, String nombre, String sector) {
		super();
		this.id = id;
		this.zona = zona;
		this.nombre = nombre;
		this.sector = sector;
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
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public List<Cliente> getClientes() {
		return clientes;
	}
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}


}
