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

import santaclara.modelo.Almacen;
import santaclara.dao.impl.AlmacenDAO;

public class ServicioAlmacen {
	
	private AlmacenDAO almacenDAO = new AlmacenDAO();
	private List<Almacen> Almacenes = new ArrayList<Almacen>();
		
	public List<Almacen> getAlmacenes() throws Exception{
		// TODO Auto-generated method stub
		
		return almacenDAO.getAlmacenes();
	}

	public AlmacenDAO getAlmacenDAO() {
		 
		return almacenDAO;
	}

	public void setAlmacenDAO(AlmacenDAO almacenDAO) {
		this.almacenDAO = almacenDAO;
	}

	public Almacen buscar(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return almacenDAO.getAlmacen(id);
	}

	public String guardar(Almacen almacen) throws Exception {
		// TODO Auto-generated method stub
		
		Almacenes = almacenDAO.getAlmacenes();
		for(Almacen almacen1 :Almacenes)
		{
			if(almacen1.getUbicacion().equals(almacen.getUbicacion())&&
					!almacen1.getId().equals(almacen.getId())) 
			{
				return "Producto Existente";
			}
		}
		
		almacenDAO.guardar(almacen);
		return "Operacion Exitosa";
				
		
	}
	
	public void eliminar(Almacen presentacion) throws Exception{
		almacenDAO.eliminar(presentacion);
	}
}
