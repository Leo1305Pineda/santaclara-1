package santaclara.modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Factura {
	
	private Integer id;
	private Date fecha;
	private Cliente cliente;
	private Usuario vendedor;
	private Almacen almacen;
	private Boolean estado;
	
	private Double subTotalExento;
	private Double subTotalGravado;
	private Double descuento;
	private Double ivaSobreBs;
	private Double iva;
	private Double totalAPagar;

	public Factura() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Factura(Integer id, Date fecha, Cliente cliente, Usuario vendedor,
			Almacen almacen, Boolean estado, Double subTotalExento,
			Double subTotalGravado, Double descuento, Double ivaSobreBs,
			Double iva, Double totalAPagar) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.cliente = cliente;
		this.vendedor = vendedor;
		this.almacen = almacen;
		this.estado = estado;
		this.subTotalExento = subTotalExento;
		this.subTotalGravado = subTotalGravado;
		this.descuento = descuento;
		this.ivaSobreBs = ivaSobreBs;
		this.iva = iva;
		this.totalAPagar = totalAPagar;
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
	
	public Usuario getVendedor() {
		return vendedor;
	}
	
	public void setVendedor(Usuario vendedor) {
		this.vendedor = vendedor;
	
	}
	
	public Almacen getAlmacen() {
		return almacen;
	}
	
	public void setAlmacen(Almacen almacen) {
		this.almacen = almacen;
	}
	
	public Boolean getEstado() {
		return estado;
	}
	
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	
	public String getEstadoStr() {
		if (estado == null) return "";
		if (getEstado().equals(true))return "Cancelada";
		else return "Pendiente";
		
	}

	public void setFecha(String cadena) throws ParseException {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			this.fecha = sdf.parse(cadena);
	}
		
	public String getFechaStr(Date fecha) {
		if (fecha==null)return "";
		else{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			return sdf.format(fecha);
			} 
		}
		
		public String getFechaStr() {
			if (fecha==null)return "";
			else{
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				return sdf.format(fecha);
			} 
		}

		public Double getSubTotalExento() {
			return subTotalExento;
		}

		public void setSubTotalExento(Double subTotalExento) {
			this.subTotalExento = subTotalExento;
		}

		public Double getSubTotalGravado() {
			return subTotalGravado;
		}

		public void setSubTotalGravado(Double subTotalGravado) {
			this.subTotalGravado = subTotalGravado;
		}

		public Double getIvaSobreBs() {
			return ivaSobreBs;
		}

		public void setIvaSobreBs(Double ivaSobreBs) {
			this.ivaSobreBs = ivaSobreBs;
		}

		public Double getIva() {
			return iva;
		}

		public void setIva(Double iva) {
			this.iva = iva;
		}

		public Double getTotalAPagar() {
			return totalAPagar;
		}

		public void setTotalAPagar(Double totalAPagar) {
			this.totalAPagar = totalAPagar;
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
 *idAlmacen:2
 *estado:Pendiente
 **/	

}
