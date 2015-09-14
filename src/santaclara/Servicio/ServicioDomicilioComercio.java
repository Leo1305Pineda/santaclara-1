package santaclara.Servicio;

import java.io.IOException;
import java.util.List;

import santaclara.modelo.DomicilioComercio;
import santaclara.dao.impl.DomicilioComercioDAO;

public class ServicioDomicilioComercio {
	
	private DomicilioComercioDAO domicilioComercioDAO = new DomicilioComercioDAO();
		
	public List<DomicilioComercio> getDomicilioComercios() throws NumberFormatException, IOException{
		// TODO Auto-generated method stub
		
		return domicilioComercioDAO.getDomicilioComercios();
	}

	public DomicilioComercioDAO getDomicilioComercioDAO() {
		 
		return domicilioComercioDAO;
	}

	public void setDomicilioComercioDAO(DomicilioComercioDAO domicilioComercioDAO) {
		this.domicilioComercioDAO = domicilioComercioDAO;
	}

	public DomicilioComercio buscar(Integer id) throws IOException {
		// TODO Auto-generated method stub
		return domicilioComercioDAO.getDomicilioComercio(id);
	}

	public void guardar(DomicilioComercio domicilioComercio) throws IOException {
		// TODO Auto-generated method stub
		domicilioComercioDAO.guardar(domicilioComercio);
	}
	
	public DomicilioComercio getdDomicilioComercio(Integer id) throws IOException{
		return domicilioComercioDAO.getDomicilioComercio(id);
	}
	
	public void eliminar(DomicilioComercio domicilioComercio) throws IOException{
		domicilioComercioDAO.eliminar(domicilioComercio);
	}
	
}
