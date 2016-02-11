package santaclara.modelo;

public class Cliente {

	private Integer id;
	private String rif;
	private String razonsocial;
	private String direccion;
	private String telefono;
	private Ruta ruta;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRif() {
		return rif;
	}
	public void setRif(String rif) {
		this.rif = rif;
	}
	public String getRazonsocial() {
		return razonsocial;
	}
	public void setRazonsocial(String razonsocial) {
		this.razonsocial = razonsocial;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Ruta getRuta() {
		return ruta;
	}
	public String getRutaNombre() {
		return ruta.getNombre();
	}
	public void setRuta(Ruta ruta) {
		this.ruta = ruta;
	}
	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cliente(Integer id, String rif, String razonsocial,
			String direccion, String telefono, Ruta ruta) {
		super();
		this.id = id;
		this.rif = rif;
		this.razonsocial = razonsocial;
		this.direccion = direccion;
		this.telefono = telefono;
		this.ruta = ruta;
	}

	public String getTipoStr()
	{
		if( this instanceof Salp )
		{
			return "Salp";
		}
		else if( this instanceof DomicilioComercio )
		{
			if(((DomicilioComercio)  this).getTipo().equals("D")) return "Domicilio";
			else return "Comercio";
		}
		else
		{
			return "";
		}
		
		
	}
}
