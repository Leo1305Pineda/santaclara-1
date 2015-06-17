package santaclara.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import santaclara.modelo.Sabor;

public interface ISaborDAO extends IGenericoDAO{
	
	public List<Sabor>  getSabores() throws FileNotFoundException;

	public void	guardar(Sabor sabor) throws IOException;
	
	public void eliminar(Sabor sabor) throws   IOException;
	
	public Sabor getSabor(Integer id) throws IOException;
	
}
