package santaclara.modelo;

import java.util.Date;

public class Visita {
	private Integer id;
	private  Date fecha;
	private String motivo;
	private String descripcion;
	private Integer valorVendedor;
	private Integer ValorProducto;
	private Boolean estado;
	private JefeVenta jefeVenta;
	public Visita(Integer id, Date fecha, String motivo, String descripcion,
			Integer valorVendedor, Integer valorProducto, Boolean estado,
			JefeVenta jefeVenta) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.motivo = motivo;
		this.descripcion = descripcion;
		this.valorVendedor = valorVendedor;
		ValorProducto = valorProducto;
		this.estado = estado;
		this.jefeVenta = jefeVenta;
	}
	public Visita() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getValorVendedor() {
		return valorVendedor;
	}
	public void setValorVendedor(Integer valorVendedor) {
		this.valorVendedor = valorVendedor;
	}
	public Integer getValorProducto() {
		return ValorProducto;
	}
	public void setValorProducto(Integer valorProducto) {
		ValorProducto = valorProducto;
	}
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	public JefeVenta getJefeVenta() {
		return jefeVenta;
	}
	public void setJefeVenta(JefeVenta jefeVenta) {
		this.jefeVenta = jefeVenta;
	}
	
	
	
}
