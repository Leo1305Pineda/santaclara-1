package santaclara.Servicio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import santaclara.modelo.Almacen;
import santaclara.dao.impl.AlmacenDAO;

public class ServicioAlmacen {
	
	private AlmacenDAO almacenDAO = new AlmacenDAO();
	private List<Almacen> Almacenes = new ArrayList<Almacen>();
		
	public List<Almacen> getAlmacenes() throws NumberFormatException, IOException{
		// TODO Auto-generated method stub
		
		return almacenDAO.getAlmacenes();
	}

	public AlmacenDAO getAlmacenDAO() {
		 
		return almacenDAO;
	}

	public void setAlmacenDAO(AlmacenDAO almacenDAO) {
		this.almacenDAO = almacenDAO;
	}

	public Almacen buscar(Integer id) throws IOException {
		// TODO Auto-generated method stub
		return almacenDAO.getAlmacen(id);
	}

	public String guardar(Almacen almacen) throws IOException {
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
	
	public void eliminar(Almacen presentacion) throws IOException{
		almacenDAO.eliminar(presentacion);
	}
	
}
