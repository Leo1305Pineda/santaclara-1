package santaclara.dao;

import java.io.FileNotFoundException;
import java.util.List;

import santaclara.modelo.Zona;

public interface IZonaDAO extends IGenericoDAO {

	public List<Zona>  getZonas() throws FileNotFoundException;
	public Zona getZona(Integer id) throws FileNotFoundException;
}
