package santaclara.modelo;

public class DomicilioComercio extends Cliente {
	
	private String tipo;
	private Integer diaVisita;

	public DomicilioComercio() {
		super();
		// TODO Auto-generated constructor stub
	}
 
	public DomicilioComercio(String tipo) {
		super();
		this.tipo = tipo;
	}
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getDiaVisita() {
		return diaVisita;
	}

	public void setDiaVisita(Integer diaVisita) {
		this.diaVisita = diaVisita;
	}
}
