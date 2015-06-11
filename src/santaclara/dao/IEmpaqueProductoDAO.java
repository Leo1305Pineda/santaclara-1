package santaclara.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import santaclara.modelo.EmpaqueProducto;

public interface IEmpaqueProductoDAO extends IGenericoDAO{
	
	public List<EmpaqueProducto>  getEmpaques() throws FileNotFoundException;

	public void	guardar(EmpaqueProducto producto) throws IOException;
	
	public void eliminar(EmpaqueProducto producto) throws   IOException;
	
	public EmpaqueProducto getEmpaqueProducto(Integer id) throws   IOException;
}