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

	public void guardar(Concesionario concesionario) throws Exception {
		// TODO Auto-generated method stub
		
		validarConcesionario(concesionario);
		validarConcesionarioRuta(concesionario);
		validarCamion(concesionario);
			/*los Concesionario solo podran tomar rutas 
			 * en donde los cliente sean
			 * domicilio Comercial */
			//if(concesionario.getRuta().getId().equals());  pendiente por hacer
		concesionarioDAO.guardar(concesionario);
	}
	public void validarConcesionario(Concesionario concesionario) throws Exception
	{
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
					throw new Exception("Concesionario Existente");
				
			}
		}
	}
	public void validarConcesionarioRuta(Concesionario concesionarioRuta) throws Exception
	{
		concesionarios = concesionarioDAO.getConcecionarios();
		
		for(Concesionario comcesionario1 :concesionarios)
		{	
			if(comcesionario1.getRuta().getId().equals(concesionarioRuta.getRuta().getId()) &&
					!comcesionario1.getId().equals(concesionarioRuta.getId())){
				/*A un Concesionario se le asigna una ruta. ¿Existe otro con la misma Ruta?*/
				throw new Exception( "La Ruta ya fue asignada al Concesionario: \n"+ 
					   "id | Nombre     \n" +
					   comcesionario1.getId()+"  | "+comcesionario1.getUsername()+"\n Cree una Nueva Ruta");	
			}
		}
	}
	public void validarCamion(Concesionario concesionario) throws Exception
	{
		concesionarios = concesionarioDAO.getConcecionarios();
		
		for(Concesionario comcesionario1 :concesionarios)
		{	

			if(comcesionario1.getCamion().getId().equals(concesionario.getCamion().getId()) &&
					!comcesionario1.getId().equals(concesionario.getId())){
				/*Para despachar los producto contrata a los concesionario
				 *  que son los dueños de de los camiones 
				 * 	a travez de los cuales seran entragados los productos. 
				 * 	¿Existe un camion que se le fue asignadao a otro concesionario?*/
				throw new Exception("El Camion ya fue asignada al Concesionario: \n"+ 
						"id | Nombre     \n" +
						comcesionario1.getId()+"  | "+comcesionario1.getUsername()+"\n Cree un Nuevo Camion");
			}
		}
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
	
	public List<Concesionario> getConcecionarios(Integer idRuta) throws NumberFormatException, IOException{
		// TODO Auto-generated method stub
		List<Concesionario> concesionariosAux = new ArrayList<Concesionario>();
		for (Concesionario concesionario : getConcecionarios() )
		{
			if (concesionario.getRuta().getId().equals(idRuta))
			{
				concesionariosAux.add(concesionario);
			}
		}
		
		return concesionariosAux;
	}
}
