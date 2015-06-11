package santaclara.dao;

import java.io.FileNotFoundException;
import java.util.List;

import santaclara.modelo.Ruta;

public interface IRutaDAO extends IGenericoDAO {

	public List<Ruta>  getRutas() throws FileNotFoundException;
	public Ruta getRuta(Integer id) throws FileNotFoundException;
}
