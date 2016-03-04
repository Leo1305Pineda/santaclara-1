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

import santaclara.modelo.Sabor;
import santaclara.dao.impl.SaborDAO;

public class ServicioSabor {
	
	private SaborDAO saborDAO = new SaborDAO();
	private List<Sabor> sabores = new ArrayList<Sabor>();
		
	public List<Sabor> getSabores() throws Exception{
		// TODO Auto-generated method stub
		
		return saborDAO.getSabores();
	}

	public SaborDAO getSaborDAO() {
		 
		return saborDAO;
	}

	public void setSaborDAO(SaborDAO saborDAO) {
		this.saborDAO = saborDAO;
	}

	public Sabor buscar(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return saborDAO.getSabor(id);
	}

	public String guardar(Sabor sabor) throws Exception {
		// TODO Auto-generated method stub
		
		sabores = saborDAO.getSabores();
		
		for(Sabor presentacion1 :sabores)
		{
			if(presentacion1.getSabor().equals(sabor.getSabor())&&
					!presentacion1.getId().equals(sabor.getId())) 
			{
				return "Sabor Existente";
			}
		}
		
		saborDAO.guardar(sabor);
		return "Operacion Exitosa";
				
		
	}
	
	public Sabor getSabor(Integer id) throws Exception{
		return saborDAO.getSabor(id);
	}
	
	public void eliminar(Sabor sabor) throws Exception{
		saborDAO.eliminar(sabor);
	}
	
}
