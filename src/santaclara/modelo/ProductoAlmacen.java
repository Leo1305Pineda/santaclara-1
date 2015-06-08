package santaclara.modelo;

public class ProductoAlmacen {
	private EmpaqueProducto producto;
	private Almacen almacen;
	private Integer stock;
	private Integer stockMin;
	private Integer existencia;
	
	public ProductoAlmacen(EmpaqueProducto producto, Almacen almacen,
			Integer stock, Integer stockMin, Integer existencia) {
		super();
		this.producto = producto;
		this.almacen = almacen;
		this.stock = stock;
		this.stockMin = stockMin;
		this.existencia = existencia;
	}

	
	public EmpaqueProducto getProducto() {
		return producto;
	}


	public void setProducto(EmpaqueProducto producto) {
		this.producto = producto;
	}


	public Almacen getAlmacen() {
		return almacen;
	}


	public void setAlmacen(Almacen almacen) {
		this.almacen = almacen;
	}


	public Integer getStock() {
		return stock;
	}


	public void setStock(Integer stock) {
		this.stock = stock;
	}


	public Integer getStockMin() {
		return stockMin;
	}


	public void setStockMin(Integer stockMin) {
		this.stockMin = stockMin;
	}


	public Integer getExistencia() {
		return existencia;
	}


	public void setExistencia(Integer existencia) {
		this.existencia = existencia;
	}


	public ProductoAlmacen() {
		// TODO Auto-generated constructor stub
	}

}
