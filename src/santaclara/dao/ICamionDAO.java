package santaclara.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import santaclara.modelo.Camion;

public interface ICamionDAO extends IGenericoDAO {

	public List<Camion>  getCamiones() throws FileNotFoundException;

	public void	guardar(Camion camion) throws IOException;
	
	public void eliminar(Camion camion) throws   IOException;
	
	public Camion getCamiones(Integer id) throws FileNotFoundException;
	
}
