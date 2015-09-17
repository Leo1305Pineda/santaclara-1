package santaclara.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import santaclara.modelo.Visita;

public interface IVisitaDAO extends IGenericoDAO {

	public List<Visita>  getVisitas() throws FileNotFoundException;

	public void	guardar(Visita visita) throws IOException;
	
	public void eliminar(Visita visita) throws   IOException;
	
	public Visita getVisita(Date fecha,Integer idJefeVenta,Integer idCliente) throws FileNotFoundException;
		
}	
