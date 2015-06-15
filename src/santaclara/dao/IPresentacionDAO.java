package santaclara.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import santaclara.modelo.Presentacion;

public interface IPresentacionDAO extends IGenericoDAO{
	
	public List<Presentacion>  getPresentaciones() throws FileNotFoundException;

	public void	guardar(Presentacion Presentasion) throws IOException;
	
	public void eliminar(Presentacion Presentacion) throws   IOException;
	
	public Presentacion getPresentacion(Integer id) throws IOException;
	
}
