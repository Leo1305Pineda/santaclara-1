/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.modelo;

 
public class Producto {
	
	private Integer id;
	private String nombre;
	private Double precio;
	private Double descuento;
	private Boolean iva;
	private Capacidad capacidad;
	private Presentacion presentacion;
	private Sabor sabor;
	
	public Producto(Integer id, String nombre, Double precio, Double descuento,
			Boolean iva, Capacidad capacidad, Presentacion presentacion,
			Sabor sabor) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.descuento = descuento;
		this.iva = iva;
		this.capacidad = capacidad;
		this.presentacion = presentacion;
		this.sabor = sabor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getPrecio() {
		return precio;
	}

	public String getPrecioStr() {
		return precio.toString()+" Bsf.";
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

	public Double getDescuento() {
		return descuento;
	}
	
	public String getDescuentoStr() {
		
		return "%  "+descuento;
	}
	
	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}

	public Boolean getIva() {
		return iva;
	}

	public void setIva(Boolean exento) {
		this.iva = exento;
	}
	
	public String getIvaStr() {
		if (iva.booleanValue()==true) return "Exento";
		else if (iva.booleanValue()==false) return "12.000";
		else return "";
	}
	
	
	public String getIva2Str() {
		if (iva.booleanValue()==true) return "exento";
		 return "gravado";
		
	}
	
	public String getDescripcion(){
		return  getNombre()+"  "+
				getPresentacion().getMaterial()+"  "+
				getCapacidad().getVolumenStr()+"  "+
				getSabor().getSabor();
	}
	
}
