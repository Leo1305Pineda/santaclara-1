package santaclara.modelo;

public class Salp extends Cliente{
	private String descripcion;

	public Salp(String descripcion) {
		super();
		this.descripcion = descripcion;
	}

	public Salp() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Salp(Integer id, String rif, String razonsocial, String direccion,
			String telefono, Ruta ruta) {
		super(id, rif, razonsocial, direccion, telefono, ruta);
		// TODO Auto-generated constructor stub
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	

}
