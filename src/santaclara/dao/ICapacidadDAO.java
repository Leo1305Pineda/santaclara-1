package santaclara.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import santaclara.modelo.Capacidad;

public interface ICapacidadDAO extends IGenericoDAO{
	
	public List<Capacidad>  getCapacidades() throws FileNotFoundException;

	public void	guardar(Capacidad capacidad) throws IOException;
	
	public void eliminar(Capacidad capacidad) throws   IOException;
	
}
