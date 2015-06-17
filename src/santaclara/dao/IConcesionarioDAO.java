package santaclara.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import santaclara.modelo.Concesionario;

public interface IConcesionarioDAO extends IGenericoDAO{

	public List<Concesionario>  getConcecionarios() throws FileNotFoundException;

	public void	guardar(Concesionario concesionario) throws IOException;
	
	public void eliminar(Concesionario concesionario) throws   IOException;
	
	public Concesionario getConcesionario(Integer id) throws FileNotFoundException;

}
