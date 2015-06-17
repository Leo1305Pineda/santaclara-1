package santaclara.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import santaclara.modelo.Ruta;

public interface IRutaDAO extends IGenericoDAO {

	public List<Ruta>  getRutas() throws FileNotFoundException;
	
	public void	guardar(Ruta ruta) throws IOException;
	
	public void eliminar(Ruta ruta) throws   IOException;
	
	public Ruta getRuta(Integer id) throws FileNotFoundException;
	
	public void Mostrar() throws IOException;
}
