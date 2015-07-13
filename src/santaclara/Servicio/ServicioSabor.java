package santaclara.Servicio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import santaclara.modelo.Sabor;
import santaclara.dao.impl.SaborDAO;

public class ServicioSabor {
	
	private SaborDAO saborDAO = new SaborDAO();
	private List<Sabor> sabores = new ArrayList<Sabor>();
		
	public List<Sabor> getSabores() throws NumberFormatException, IOException{
		// TODO Auto-generated method stub
		
		return saborDAO.getSabores();
	}

	public SaborDAO getSaborDAO() {
		 
		return saborDAO;
	}

	public void setSaborDAO(SaborDAO saborDAO) {
		this.saborDAO = saborDAO;
	}

	public Sabor buscar(Integer id) throws IOException {
		// TODO Auto-generated method stub
		return saborDAO.getSabor(id);
	}

	public String guardar(Sabor sabor) throws IOException {
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
	
	public Sabor getSabor(Integer id) throws IOException{
		return saborDAO.getSabor(id);
	}
	
	public void eliminar(Sabor sabor) throws IOException{
		saborDAO.eliminar(sabor);
	}
	
}
