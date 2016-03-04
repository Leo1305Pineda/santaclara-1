/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.Servicio;

import java.util.ArrayList;
import java.util.List;

import santaclara.modelo.Capacidad;
import santaclara.dao.impl.CapacidadDAO;

public class ServicioCapacidad {
	
	private CapacidadDAO capacidadDAO = new CapacidadDAO();
	private List<Capacidad> capacidades = new ArrayList<Capacidad>();
		
	public List<Capacidad> getCapacidades() throws Exception{
		// TODO Auto-generated method stub
		
		return capacidadDAO.getCapacidades();
	}

	public CapacidadDAO getCapacidadDAO() {
		 
		return capacidadDAO;
	}

	public void setCapacidadDAO(CapacidadDAO capacidadDAO) {
		this.capacidadDAO = capacidadDAO;
	}

	public Capacidad buscar(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return capacidadDAO.getCapacidad(id);
	}

	public String guardar(Capacidad capacidad) throws Exception {
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
	
	public Capacidad getCapacidad(Integer id) throws Exception{
		return capacidadDAO.getCapacidad(id);
	}
	
	public void eliminar(Capacidad presentacion) throws Exception{
		capacidadDAO.eliminar(presentacion);
	}
	
}
