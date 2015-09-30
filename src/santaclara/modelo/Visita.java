package santaclara.modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Visita {
	private Integer id;
	private  Date fecha;
	private String motivo;
	private String descripcion;
	private Integer valorVendedor;
	private Integer valorProducto;
	private Boolean estado;
	private Usuario usuario;
	private Cliente cliente;
	
	public Visita(Integer id, Date fecha, String motivo, String descripcion,
			Integer valorVendedor, Integer valorProducto, Boolean estado,
			Usuario jefeVenta,Cliente cliente) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.motivo = motivo;
		this.descripcion = descripcion;
		this.valorVendedor = valorVendedor;
		this.valorProducto = valorProducto;
		this.estado = estado;
		this.usuario = jefeVenta;
		this.cliente = cliente;
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
	public String getFechaStr() {
		if (fecha==null)return "";
		else{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			return sdf.format(fecha);
		} 
	}
	
	public String getFechaStr(Date fecha) {
		if (fecha==null)return "";
		else{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			return sdf.format(fecha);
		} 
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
	public String getValorVendedorStr() {
		if (valorVendedor==null) return "";
		else return valorVendedor.toString();
	}
	public void setValorVendedor(Integer valorVendedor) {
		this.valorVendedor = valorVendedor;
	}
	public Integer getValorProducto() {
		return valorProducto;
	}
	public String getValorProductoStr() {
		if (valorProducto==null) return "";
		else return valorProducto.toString();
	}
	public void setValorProducto(Integer valorProducto) {
		this.valorProducto = valorProducto;
	}
	public Boolean getEstado() {
		return estado;
	}
	public String getEstadoStr() {
		if (estado == null) return "";
		if (getEstado().equals(true))return "Hecha";
		else return "Por Hacer";
		
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void setFecha(String cadena) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			this.fecha = sdf.parse(cadena);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
