package santaclara.Servicio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import santaclara.modelo.Presentacion;
import santaclara.modelo.Producto;
import santaclara.dao.impl.PresentacionDAO;

public class ServicioPresentaciones {
	
	private PresentacionDAO presentacionDAO = new PresentacionDAO();
	private List<Presentacion> presentaciones = new ArrayList<Presentacion>();
		
	public List<Presentacion> getPresentaciones() throws NumberFormatException, IOException{
		// TODO Auto-generated method stub
		
		return presentacionDAO.getPresentaciones();
	}

	public PresentacionDAO getPresentacionDAO() {
		 
		return presentacionDAO;
	}

	public void setPresentacionDAO(PresentacionDAO presentacionDAO) {
		this.presentacionDAO = presentacionDAO;
	}

	public Presentacion buscar(Integer id) throws IOException {
		// TODO Auto-generated method stub
		return presentacionDAO.getPresentacion(id);
	}

	public String guardar(Presentacion presentacion) throws IOException {
		// TODO Auto-generated method stub
		
		presentaciones = presentacionDAO.getPresentaciones();
		
		for(Presentacion presentacion1 :presentaciones)
		{
			if(presentacion1.getMaterial().equals(presentacion.getMaterial())&&
					!presentacion1.getId().equals(presentacion.getId())) 
			{
				return "Producto Existente";
			}
		}
		
		presentacionDAO.guardar(presentacion);
		return "Operacion Exitosa";
				
		
	}
	
}
