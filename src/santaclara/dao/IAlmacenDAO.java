package santaclara.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import santaclara.modelo.Almacen;

public interface IAlmacenDAO extends IGenericoDAO{
	
	public List<Almacen>  getAlmacenes() throws FileNotFoundException;

	public void	guardar(Almacen almacen) throws IOException;
	
	public void eliminar(Almacen almacen) throws   IOException;
	
	public Almacen getAlmacen(Integer id) throws FileNotFoundException;

}