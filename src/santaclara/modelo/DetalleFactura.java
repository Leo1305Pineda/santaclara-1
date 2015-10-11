package santaclara.modelo;

public class DetalleFactura {
	
	private Factura factura;
	private Producto producto;
	private Integer cantidad;
	private Double precio;
	private Double descuento;
	private Double total;
	private Double iva; 
	

	
	public DetalleFactura() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DetalleFactura(Producto producto,Integer cantidad, Double precio, Factura factura,
			Double iva, Double total) {
		super();
		this.producto = producto;
		this.cantidad = cantidad;
		this.precio = precio;
		this.factura = factura;
		this.iva = iva;
		this.total = total;
	}
	
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Factura getFactura() {
		return factura;
	}
	public void setFactura(Factura factura) {
		this.factura = factura;
	}
	public Double getIva() {
		return iva;
	}
	public void setIva(Double iva) {
		this.iva = iva;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public Double getDescuento() {
		return descuento;
	}
	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}

	
}
