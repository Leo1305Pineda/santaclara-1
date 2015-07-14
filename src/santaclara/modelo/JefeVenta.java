package santaclara.modelo;

import java.util.List;

public class JefeVenta  extends  Usuario {
	
	private Zona zona;

	
	public JefeVenta(Integer id, String username, String cedula, String nombres,
			String contrasena,Zona zona) {
		super( id,  username,  cedula,  nombres,
				 contrasena);
		// TODO Auto-generated constructor stub
		this.zona=zona;
	}

	public JefeVenta(Zona zona,List<Visita> visita) {
		super();
		this.zona = zona;
	}
	public JefeVenta(){
		// TODO Auto-generated constructor stub
		super();
	}
	public Zona getZona() {
		return zona;
	}

	public void setZona(Zona zona) {
		this.zona = zona;
	}

}
