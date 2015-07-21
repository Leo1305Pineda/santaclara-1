package santaclara.modelo;

import java.util.Date;

public class Factura {
	
	private Integer id;
	private Date fecha;
	private Double total;
	private Double saldo;
	private Double iva;
	private Double descuento;
	private Cliente cliente;
	private Vendedor vendedor;
	private Boolean estado;
	
	public Factura() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Factura(Integer id, Date fecha, Double total, Double saldo,
			Double iva, Double descuento, Cliente cliente,Vendedor vendedor,Boolean estado) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.total = total;
		this.saldo = saldo;
		this.iva = iva;
		this.descuento = descuento;
		this.cliente = cliente;
		this.vendedor = vendedor;
		this.estado = estado;
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
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	public Double getIva() {
		return iva;
	}
	public void setIva(Double iva) {
		this.iva = iva;
	}
	public Double getDescuento() {
		return descuento;
	}
	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Vendedor getVendedor() {
		return vendedor;
	}
	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	
	}
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

/*
 * Estructura
 * 
 *id:0
 *fecha:06/06/2015
 *total:7350000000
 *saldo:88200000
 *iva:7261800000
 *descuento:0
 *idCliente:2
 *idvendedor:2
 *estado:Pendiente
 **/	

}
