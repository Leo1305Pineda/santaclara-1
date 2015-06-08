package santaclara.modelo;

public class Producto {
	private Capacidad capacidad;
	private Presentacion presentacion;
	private Sabor sabor;
	private String nombre;
	private Double precio;
	
	public Producto(Capacidad capacidad, Presentacion presentacion,
			Sabor sabor, String nombre,Double precio) {
		super();
		this.capacidad = capacidad;
		this.presentacion = presentacion;
		this.sabor = sabor;
		this.nombre = nombre;
		this.precio = precio;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Capacidad getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Capacidad capacidad) {
		this.capacidad = capacidad;
	}

	public Presentacion getPresentacion() {
		return presentacion;
	}

	public void setPresentacion(Presentacion presentacion) {
		this.presentacion = presentacion;
	}

	public Sabor getSabor() {
		return sabor;
	}

	public void setSabor(Sabor sabor) {
		this.sabor = sabor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Producto() {
		// TODO Auto-generated constructor stub
	}

}
