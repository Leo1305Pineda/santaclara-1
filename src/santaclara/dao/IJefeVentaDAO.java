package santaclara.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import santaclara.modelo.JefeVenta;

public interface IJefeVentaDAO extends IGenericoDAO{

	public List<JefeVenta>  getJefeVentas() throws FileNotFoundException;

	public void	guardar(JefeVenta jefeVenta) throws IOException;
	
	public void eliminar(JefeVenta jefeVenta) throws   IOException;
	
	public JefeVenta getJefeVenta(Integer id) throws FileNotFoundException;

}
