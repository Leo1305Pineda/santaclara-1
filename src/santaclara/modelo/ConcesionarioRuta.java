package santaclara.modelo;

public class ConcesionarioRuta {
	private Cliente cliente;
	private Concesionario concesionario;
	private Ruta ruta;
	private String dias;
	

	public ConcesionarioRuta(Cliente cliente, Concesionario concesionario,
			Ruta ruta, String dias) {
		super();
		this.cliente = cliente;
		this.concesionario = concesionario;
		this.ruta = ruta;
		this.dias = dias;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public Concesionario getConcesionario() {
		return concesionario;
	}


	public void setConcesionario(Concesionario concesionario) {
		this.concesionario = concesionario;
	}


	public Ruta getRuta() {
		return ruta;
	}


	public void setRuta(Ruta ruta) {
		this.ruta = ruta;
	}


	public String getDias() {
		return dias;
	}


	public void setDias(String dias) {
		this.dias = dias;
	}


	public ConcesionarioRuta() {
		// TODO Auto-generated constructor stub
	}

}
