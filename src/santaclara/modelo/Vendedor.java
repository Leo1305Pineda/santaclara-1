package santaclara.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

public class Vendedor extends Usuario  {
	
	private List<Ruta> rutas;

	public Vendedor(Integer id, String username, String cedula, String nombres,
			String contrasena,List<Ruta> rutas) {
		super(id, username, cedula, nombres, contrasena);
		// TODO Auto-generated constructor stub
		this.rutas=rutas;
	}

	public Vendedor(List<Ruta> rutas) {
		super();
		this.rutas = rutas;
	}
	
	
	public Vendedor() {
		super();
	}

	public List<Ruta> getRutas() {
		return rutas;
	}

	public void setRutas(List<Ruta> rutas) {
		this.rutas = rutas;
	}

	public List<String> getStrRutas(){
		
		List<String> ru = new ArrayList<String>();
		for(Ruta ruta1 : rutas){
		 ru.add("    "+ruta1.getNombre()+"   ");
		 
		}
		return ru;
	} 
	
public JComboBox<String> getCtrRutas(){
		
		JComboBox<String> ru = new JComboBox<String>();
		for(Ruta ruta1 : rutas){
		 ru.addItem("   "+ruta1.getNombre()+"   ");
		}
		return ru;
	}
}
