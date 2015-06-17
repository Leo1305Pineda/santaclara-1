package santaclara.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import santaclara.modelo.Zona;

public interface IZonaDAO extends IGenericoDAO {

	public List<Zona>  getZonas() throws FileNotFoundException;
	
	public void	guardar(Zona zona) throws IOException;
	
	public void eliminar(Zona zona) throws   IOException;
	
	public Zona getZona(Integer id) throws FileNotFoundException;
}
