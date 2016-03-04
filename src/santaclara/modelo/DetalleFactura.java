/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.modelo;

public class DetalleFactura {
	
	private Factura factura;
	private EmpaqueProducto empaqueProducto;
	private Integer cantidad;
	private Double precio;
	private Double descuento;
	private Double total;
	private Double iva; 
	

	
	public DetalleFactura() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public DetalleFactura(Factura factura, EmpaqueProducto empaqueProducto,
			Integer cantidad, Double precio, Double descuento, Double iva, 
			Double total) {
		super();
		this.factura = factura;
		this.empaqueProducto = empaqueProducto;
		this.cantidad = cantidad;
		this.precio = precio;
		this.descuento = descuento;
		this.total = total;
		this.iva = iva;
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
	public String getIvaStr() {
		return empaqueProducto.getProducto().getIvaStr();
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
	public EmpaqueProducto getEmpaqueProducto() {
		return empaqueProducto;
	} 
	public void setEmpaqueProducto(EmpaqueProducto empaqueProducto) {
		this.empaqueProducto = empaqueProducto;
	}
	public Double getDescuento() {
		return descuento;
	}
	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}
	
	public String getCodigoStr(){
		return empaqueProducto.getId().toString();
	}
	
	public String  getUnidadesStr(){
		return empaqueProducto.getUnidadesStr();
	}
	
	public String getDescripcion(){
		return empaqueProducto.getDescripcionEmpaque();
	}

}
