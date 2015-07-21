package santaclara.modelo;

public class EmpaqueProducto {
	private Integer id;
	private Producto producto;
	private Integer cantidad;
	
	public EmpaqueProducto(Integer id, Producto producto, Integer cantidad) {
		super();
		this.id = id;
		this.producto = producto;
		this.cantidad = cantidad;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public EmpaqueProducto() {
		// TODO Auto-generated constructor stub
	}
	
	public String getCantidadStr() {
		return cantidad.toString();
	}
	
	public String getDescripcionEmpaque(){
		return producto.getNombre()+
				producto.getPresentacion().getMaterial()+
				producto.getCapacidad().getVolumenStr()+
				producto.getSabor().getSabor()+producto.getPrecio();
	}

}
