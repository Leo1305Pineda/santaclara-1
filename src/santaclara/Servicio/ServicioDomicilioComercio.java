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

import santaclara.modelo.DomicilioComercio;
import santaclara.modelo.Ruta;
import santaclara.dao.impl.DomicilioComercioDAO;

public class ServicioDomicilioComercio {
	
	private DomicilioComercioDAO domicilioComercioDAO = new DomicilioComercioDAO();
		
	public List<DomicilioComercio> getDomicilioComercios() throws Exception{
		// TODO Auto-generated method stub
		
		return domicilioComercioDAO.getDomicilioComercios();
	}

	public DomicilioComercioDAO getDomicilioComercioDAO() {
		 
		return domicilioComercioDAO;
	}

	public void setDomicilioComercioDAO(DomicilioComercioDAO domicilioComercioDAO) {
		this.domicilioComercioDAO = domicilioComercioDAO;
	}

	public DomicilioComercio buscar(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return domicilioComercioDAO.getDomicilioComercio(id);
	}
	
	public Boolean isbuscar(Integer id) throws Exception {
		DomicilioComercio domicilioComercio = domicilioComercioDAO.getDomicilioComercio(id);
		if (domicilioComercio != null) return true; 
		return false;
	} 

	public void guardar(DomicilioComercio domicilioComercio) throws Exception {
		// TODO Auto-generated method stub
		domicilioComercioDAO.guardar(domicilioComercio);
	}
	
	public DomicilioComercio getdDomicilioComercio(Integer id) throws Exception{
		return domicilioComercioDAO.getDomicilioComercio(id);
	}
	
	public void eliminar(DomicilioComercio domicilioComercio) throws Exception{
		domicilioComercioDAO.eliminar(domicilioComercio);
	}
	public List<DomicilioComercio> getDomicilioComercios(Ruta ruta) throws Exception{
		List<DomicilioComercio> domicilioComercios = new DomicilioComercioDAO().getDomicilioComercios();
		List<DomicilioComercio> domicilioComercios2 = new ArrayList<DomicilioComercio>();
		for(DomicilioComercio domicilioComercio : domicilioComercios)
		{
			if (domicilioComercio.getRuta().getId().equals(ruta.getId())) domicilioComercios2.add(domicilioComercio);
		}
		return domicilioComercios2;
	}
	
	public List<DomicilioComercio> getDomicilioComercios(List<Ruta> rutas) throws Exception{
		// TODO Auto-generated method stub
		if (rutas == null)
		{
			return null;
		}
		List<DomicilioComercio> domicilioComercios = new ArrayList<DomicilioComercio>();
		for(DomicilioComercio cliente : getDomicilioComercios()){
			for(Ruta ruta : rutas){
				if (cliente.getRuta().getId().equals(ruta.getId()))
				{
					domicilioComercios.add(cliente);
				}
			}
		}
		
		return domicilioComercios; 
	}

}
