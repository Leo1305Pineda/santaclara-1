package santaclara.modelo;

import java.util.List;

public class Vendedor extends Usuario  {
	
	private List<Ruta> rutas;

	public Vendedor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Vendedor(List<Ruta> rutas) {
		super();
		this.rutas = rutas;
	}
	
	
	public List<Ruta> getRutas() {
		return rutas;
	}

	public void setRutas(List<Ruta> rutas) {
		this.rutas = rutas;
	}



}
