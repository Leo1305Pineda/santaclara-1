package santaclara.Servicio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import santaclara.modelo.Capacidad;
import santaclara.dao.impl.CapacidadDAO;

public class ServicioCapacidad {
	
	private CapacidadDAO capacidadDAO = new CapacidadDAO();
	private List<Capacidad> capacidades = new ArrayList<Capacidad>();
		
	public List<Capacidad> getCapacidades() throws NumberFormatException, IOException{
		// TODO Auto-generated method stub
		
		return capacidadDAO.getCapacidades();
	}

	public CapacidadDAO getCapacidadDAO() {
		 
		return capacidadDAO;
	}

	public void setCapacidadDAO(CapacidadDAO capacidadDAO) {
		this.capacidadDAO = capacidadDAO;
	}

	public Capacidad buscar(Integer id) throws IOException {
		// TODO Auto-generated method stub
		return capacidadDAO.getCapacidad(id);
	}

	public String guardar(Capacidad capacidad) throws IOException {
		// TODO Auto-generated method stub
		
		capacidades = capacidadDAO.getCapacidades();
		
		for(Capacidad capacidad1 :capacidades)
		{
			if(capacidad1.getVolumen().equals(capacidad.getVolumen())&&
					!capacidad1.getId().equals(capacidad.getId())) 
			{
				return "Volumen Existente";
			}
		}
		
		capacidadDAO.guardar(capacidad);
		return "Operacion Exitosa";
				
		
	}
	
	public Capacidad getCapacidad(Integer id) throws IOException{
		return capacidadDAO.getCapacidad(id);
	}
	
	public void eliminar(Capacidad presentacion) throws IOException{
		capacidadDAO.eliminar(presentacion);
	}
	
}
