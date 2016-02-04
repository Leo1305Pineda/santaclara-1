package santaclara.modelo;
import santaclara.modelo.Camion;
import santaclara.modelo.Ruta;

public class Concesionario extends Usuario{
	private Camion camion;
	private Ruta ruta;
	
	
	public Concesionario(Integer id, String username, String cedula, String nombres,
			String contrasena,Camion camion, Ruta ruta) {
		super( id, username, cedula,nombres ,contrasena);
		this.camion = camion;
		this.ruta = ruta;
	}
	
	public Concesionario(Camion camion, Ruta ruta) {
		super();
		this.camion = camion;
		this.ruta = ruta;
	}

	public Camion getCamion() {
		return camion;
	}

	public void setCamion(Camion camion) {
		this.camion = camion;
	}

	public Ruta getRuta() {
		return ruta;
	}


	public void setRuta(Ruta ruta) {
		this.ruta = ruta;
	}


	public Concesionario() {
		// TODO Auto-generated constructor stub
	}

}
