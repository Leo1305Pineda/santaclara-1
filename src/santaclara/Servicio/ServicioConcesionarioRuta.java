package santaclara.Servicio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import santaclara.modelo.ConcesionarioRuta;
import santaclara.dao.impl.ConcesionarioRutaDAO;

public class ServicioConcesionarioRuta {
	
	private ConcesionarioRutaDAO concesionarioRutaDAO = new ConcesionarioRutaDAO();
	private List<ConcesionarioRuta> concesionarioRutas = new ArrayList<ConcesionarioRuta>();
		
	public List<ConcesionarioRuta> getConcecionarioRutas() throws NumberFormatException, IOException{
		// TODO Auto-generated method stub
		
		return concesionarioRutaDAO.getConcesionarioRutas();
	}

	public ConcesionarioRutaDAO getConcesionarioRutaDAO() {
		 
		return concesionarioRutaDAO;
	}

	public void setConcesionarioRutaDAO(ConcesionarioRutaDAO concesionarioRutaDAO) {
		this.concesionarioRutaDAO = concesionarioRutaDAO;
	}

	public String guardar(ConcesionarioRuta concesionarioRuta) throws IOException {
		// TODO Auto-generated method stub
		
		concesionarioRutas = concesionarioRutaDAO.getConcesionarioRutas();
		
		for(ConcesionarioRuta comcesionarioRuta1 :concesionarioRutas)
		{
			if(comcesionarioRuta1.getCliente().getId().equals(concesionarioRuta.getCliente().getId())&&
					comcesionarioRuta1.getConcesionario().getId().equals(concesionarioRuta.getConcesionario().getId() )&&
					comcesionarioRuta1.getRuta().getId().equals(concesionarioRuta.getRuta().getId())) 
			{
				if(comcesionarioRuta1.getDias().equals(concesionarioRuta.getDias())) return "Camion Existente";
				break;//rompe el for para modificar
			}
		}
		
		concesionarioRutaDAO.guardar(concesionarioRuta);
		return "Operacion Exitosa";
				
		
	}
	
	public ConcesionarioRuta getConcesionario(Integer idCliente,Integer idConcesionario,Integer idRuta) throws IOException{
		return concesionarioRutaDAO.getConcesionarioRuta(idCliente, idConcesionario, idRuta);
	}
	
	public void eliminar(ConcesionarioRuta concesionarioRuta) throws IOException{
		concesionarioRutaDAO.eliminar(concesionarioRuta);
	}
	
	public Boolean getConcesionario(Integer id) throws NumberFormatException, IOException{
		
		//concesionarioRutas =  new ArrayList<ConcesionarioRuta>();
		this.concesionarioRutas = concesionarioRutaDAO.getConcesionarioRutas();
		for(ConcesionarioRuta concesionarioRuta1: concesionarioRutas)
		{
			if(concesionarioRuta1.getConcesionario().getId().equals(id)) return true;
		}
		
		return false;
	}
}
