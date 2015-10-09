package santaclara.Servicio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import santaclara.modelo.Ruta;
import santaclara.modelo.Salp;
import santaclara.dao.impl.SalpDAO;

public class ServicioSalp {
	
	private SalpDAO salpDAO = new SalpDAO();
		
	public List<Salp> getSalps() throws NumberFormatException, IOException{
		// TODO Auto-generated method stub
		
		return salpDAO.getSalps();
	}

	public SalpDAO getSalpDAO() {
		 
		return salpDAO;
	}

	public void setSalpDAO(SalpDAO salpDAO) {
		this.salpDAO = salpDAO;
	}

	public Salp buscar(Integer id) throws IOException {
		// TODO Auto-generated method stub
		return salpDAO.getSalp(id);
	}

	public void guardar(Salp salp) throws IOException {
		// TODO Auto-generated method stub
		
		salpDAO.guardar(salp);
	}
	
	public Salp getSalp(Integer id) throws IOException{
		return salpDAO.getSalp(id);
	}
	
	public void eliminar(Salp salp) throws IOException{
		salpDAO.eliminar(salp);
	}
	
	public List<Salp> getSalps(List<Ruta> rutas) throws NumberFormatException, IOException{
		// TODO Auto-generated method stub
		List<Salp> salps = new ArrayList<Salp>();
		for(Salp cliente : getSalps()){
			for(Ruta ruta : rutas){
				if (cliente.getRuta().getId().equals(ruta.getId()))
				{
					salps.add(cliente);
				}
			}
		}
		return salps;
	}
}
