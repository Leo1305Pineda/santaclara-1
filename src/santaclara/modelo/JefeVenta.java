package santaclara.modelo;

import java.util.ArrayList;
import java.util.List;



public class JefeVenta  extends  Usuario {
	
	private List<Visita> visitas = new ArrayList<Visita>();
	private Zona zona;

	
	public JefeVenta() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JefeVenta(Zona zona,List<Visita> visita) {
		super();
		this.zona = zona;
		this.visitas = visita;
	}
	
	
	public Zona getZona() {
		return zona;
	}

	public void setZona(Zona zona) {
		this.zona = zona;
	}

	public List<Visita> getVisitas() {
		return visitas;
	}

	public void setVisita(List<Visita> visita) {
		this.visitas = visita;
	}
 

}
