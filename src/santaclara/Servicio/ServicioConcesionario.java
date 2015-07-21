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
			if(comcesionario1.getId().equals(concesionario.getId()))
			{
				/* ¿ Existe Algun Cambio?*/
				if(comcesionario1.getCedula().equals(concesionario.getCedula())&&
						comcesionario1.getNombre().equals(concesionario.getNombre())&&
						comcesionario1.getUsername().equals(concesionario.getUsername())&&
						comcesionario1.getContrasena().equals(concesionario.getContrasena())&&
						comcesionario1.getCamion().getId().equals(concesionario.getCamion().getId())&&
						comcesionario1.getRuta().getId().equals(concesionario.getRuta().getId())) 
					return "Concesionario Existente";
				
			}
			else if(comcesionario1.getRuta().getId().equals(concesionario.getRuta().getId())){
				/*A un Concesionario se le asigna una ruta. ¿Existe otro con la misma Ruta?*/
				return "La Ruta ya fue asignada al Concesionario: \n"+ 
					   "id | Nombre     \n" +
					   comcesionario1.getId()+"  | "+comcesionario1.getUsername();
			}
			else if(comcesionario1.getCamion().getId().equals(concesionario.getCamion().getId())){
					/*Para despachar los producto contrata a los concesionario
					 *  que son los dueños de de los camiones 
					 * a travez de los cuales seran entragados los productos. 
					 * ¿Existe un camion que se le fue asignadao a otro concesionario?*/
					return "El Camion ya fue asignada al Concesionario: \n"+ 
						   "id | Nombre     \n" +
						   comcesionario1.getId()+"  | "+comcesionario1.getUsername();
			}
			/*los Concesionario solo podran tomar rutas 
			 * en donde los cliente sean
			 * domicilio Comercial */
			//if(concesionario.getRuta().getId().equals());  pendiente por hacer
		
		}
		concesionarioDAO.guardar(concesionario);
		return "";
	}
	
	public Concesionario getConcesionario(Integer id) throws IOException{
		return concesionarioDAO.getConcesionario(id);
	}
	
	public void eliminar(Concesionario concesionario) throws IOException{
		concesionarioDAO.eliminar(concesionario);
	}
	
	public Boolean  getUsuario(Integer id)throws IOException{
		if  (concesionarioDAO.getConcesionario(id)!=null) return true;
		return false;
	}
}
