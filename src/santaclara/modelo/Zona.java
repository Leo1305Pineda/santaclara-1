package santaclara.modelo;

import java.util.ArrayList;
import java.util.List;

public class Zona {
	
	private Integer	id;
	private JefeVenta jefeVenta; 
	private String descripcion;
	
	private List<Ruta> rutas = new ArrayList<Ruta>();

	
	public Zona() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Zona(JefeVenta jefeVenta, String descripcion, List<Ruta> rutas) {
		super();
		this.jefeVenta = jefeVenta;
		this.descripcion = descripcion;
		this.rutas = rutas;
	}
	 
	public JefeVenta getJefeVenta() {
		return jefeVenta;
	}
	public void setJefeVenta(JefeVenta jefeVenta) {
		this.jefeVenta = jefeVenta;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public List<Ruta> getRutas() {
		return rutas;
	}
	public void setRutas(List<Ruta> rutas) {
		this.rutas = rutas;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	 
}
