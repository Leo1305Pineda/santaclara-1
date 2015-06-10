package santaclara.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import santaclara.modelo.ConcesionarioRuta;

public interface IConcesionarioRutaDAO extends IGenericoDAO {

	public List<ConcesionarioRuta>  getConcesionarioRutas() throws FileNotFoundException;

	public void	guardar(ConcesionarioRuta concesionarioRuta) throws IOException;
	
	public void eliminar(ConcesionarioRuta concesionarioRuta) throws   IOException;
	
	public ConcesionarioRuta getConcesionarioRuta(Integer idClienete,Integer idConcesionario,Integer idRuta) throws FileNotFoundException;
	
}	
