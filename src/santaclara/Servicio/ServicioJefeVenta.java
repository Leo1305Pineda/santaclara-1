package santaclara.Servicio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import santaclara.modelo.JefeVenta;
import santaclara.dao.impl.JefeVentaDAO;

public class ServicioJefeVenta {
	
	private JefeVentaDAO jefeVentaDAO = new JefeVentaDAO();
	private List<JefeVenta> jefeVentas = new ArrayList<JefeVenta>();
		
	public List<JefeVenta> getJefeVentas() throws NumberFormatException, IOException{
		// TODO Auto-generated method stub
		
		return jefeVentaDAO.getJefeVentas();
	}

	public JefeVentaDAO getJefeVentaDAO() {
		 
		return jefeVentaDAO;
	}

	public void setJefeVentaDAO(JefeVentaDAO jefeVentaDAO) {
		this.jefeVentaDAO = jefeVentaDAO;
	}

	public JefeVenta buscar(Integer id) throws IOException {
		// TODO Auto-generated method stub
		return jefeVentaDAO.getJefeVenta(id);
	}

	public String guardar(JefeVenta jefeVenta) throws IOException {
		// TODO Auto-generated method stub
		
		jefeVentas = jefeVentaDAO.getJefeVentas();  
		
		if(jefeVenta.getId()!=null)
		{
			for(JefeVenta jefeVenta1 :jefeVentas)
			{
				if(jefeVenta1.getId().equals(jefeVenta.getId())&&
					jefeVenta1.getZona().getId().equals(jefeVenta.getZona().getId()))//Revisar 
				{
					if(jefeVenta1.getCedula().equals(jefeVenta.getCedula())&&
						jefeVenta1.getNombre().equals(jefeVenta.getNombre())&&
						jefeVenta1.getUsername().equals(jefeVenta.getUsername())&&
						jefeVenta1.getContrasena().equals(jefeVenta.getContrasena()))
					
						return "jefeVenta Existente";
					break;//rompe el for para modificar
				}
			}
		}
		jefeVentaDAO.guardar(jefeVenta);
		return "Operacion Exitosa";
				
		
	}
	
	public JefeVenta getConcesionario(Integer id) throws IOException{
		return jefeVentaDAO.getJefeVenta(id);
	}
	
	public void eliminar(JefeVenta jefeVenta) throws IOException{
		jefeVentaDAO.eliminar(jefeVenta);
	}
	
}
