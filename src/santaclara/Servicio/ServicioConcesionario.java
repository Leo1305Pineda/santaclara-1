package santaclara.Servicio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import santaclara.modelo.Concesionario;
import santaclara.dao.impl.ConcesionarioDAO;

public class ServicioConcesionario {
	
	private ConcesionarioDAO concesionarioDAO = new ConcesionarioDAO();
	private List<Concesionario> concesionarios = new ArrayList<Concesionario>();
		
	public List<Concesionario> getConcecionarios() throws NumberFormatException, IOException{
		// TODO Auto-generated method stub
		
		return concesionarioDAO.getConcecionarios();
	}

	public ConcesionarioDAO getConcesionarioDAO() {
		 
		return concesionarioDAO;
	}

	public void setConcesionarioDAO(ConcesionarioDAO concesionarioDAO) {
		this.concesionarioDAO = concesionarioDAO;
	}

	public Concesionario buscar(Integer id) throws IOException {
		// TODO Auto-generated method stub
		return concesionarioDAO.getConcesionario(id);
	}

	public String guardar(Concesionario concesionario) throws IOException {
		// TODO Auto-generated method stub
		
		concesionarios = concesionarioDAO.getConcecionarios();
		
		for(Concesionario comcesionario1 :concesionarios)
		{
			if(comcesionario1.getId().equals(concesionario.getId())&&
					comcesionario1.getCamion().getId().equals(concesionario.getCamion().getId() )&&
					comcesionario1.getRuta().getId().equals(concesionario.getRuta().getId())) 
			{
				if(comcesionario1.getCedula().equals(concesionario.getCedula())&&
						comcesionario1.getNombre().equals(concesionario.getNombre())&&
						comcesionario1.getUsername().equals(concesionario.getUsername())&&
						comcesionario1.getContrasena().equals(concesionario.getContrasena()))
					
				return "Concesionario Existente";
				break;//rompe el for para modificar
			}
		}
		concesionarioDAO.guardar(concesionario);
		return "Operacion Exitosa";
				
		
	}
	
	public Concesionario getConcesionario(Integer id) throws IOException{
		return concesionarioDAO.getConcesionario(id);
	}
	
	public void eliminar(Concesionario concesionario) throws IOException{
		concesionarioDAO.eliminar(concesionario);
	}
	
}
