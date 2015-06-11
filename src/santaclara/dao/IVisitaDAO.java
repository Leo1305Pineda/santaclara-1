package santaclara.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import santaclara.modelo.Visita;

public interface IVisitaDAO extends IGenericoDAO {

	public List<Visita>  getVisitas() throws FileNotFoundException;

	public void	guardar(Visita visita) throws IOException;
	
	public void eliminar(Visita visita) throws   IOException;
	
	public Visita getVisita(Integer id) throws FileNotFoundException;
		
}	
