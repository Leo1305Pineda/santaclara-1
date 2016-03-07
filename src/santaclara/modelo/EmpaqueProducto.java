/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.modelo;

public class EmpaqueProducto {
	private Integer id;
	private Producto producto;
	private Integer unidades;
	
	public EmpaqueProducto(Integer id, Producto producto, Integer cantidad) {
		super();
		this.id = id;
		this.producto = producto;
		this.unidades = cantidad;
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
		return unidades;
	}

	public void setCantidad(Integer cantidad) {
		this.unidades = cantidad;
	}

	public EmpaqueProducto() {
		// TODO Auto-generated constructor stub
	}
	
	public String getDescripcionEmpaque(){
		return  producto.getId()+"  "+
				producto.getNombre()+"  "+
				producto.getPresentacion().getMaterial()+"  "+
				producto.getCapacidad().getVolumenStr();
	}

	
	public Double getPrecioEmpaque(){
		return (producto.getPrecio()*unidades);
	}
	
	public String  getPrecioEmpaqueStr() {
	return (producto.getPrecio()*unidades)+" BsF.";
	}

	public String getUnidadesStr() {
		// TODO Auto-generated method stub
		return unidades.toString().concat(" Un.xCAJ."); 
	}
	
	public String getProductoSabor(){
		return producto.getSabor().getSabor();
	}
	
	public String getProductoPresentacionStr(){
		return producto.getPresentacion().getMaterial();
	}
	
	public String getProductoStr(){
		return producto.getNombre();
	}

	public String getPresentacionStr(){
		return producto.getPresentacionStr();
	}
	
	public String getCapacidadStr(){
		return producto.getCapacidadStr();
	}
	
	public String getSaborStr(){
		return producto.getSaborStr();
	}
}
