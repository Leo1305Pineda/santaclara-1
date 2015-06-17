package santaclara.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import santaclara.modelo.Salp;

public interface ISalpDAO extends IGenericoDAO {

	public List<Salp>  getSalps() throws FileNotFoundException;

	public void	guardar(Salp salp) throws IOException;
	
	public void eliminar(Salp salp) throws   IOException;
	
	public Salp getSalp(Integer id) throws FileNotFoundException;
		
}	
