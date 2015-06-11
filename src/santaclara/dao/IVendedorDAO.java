package santaclara.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import santaclara.modelo.Vendedor;

public interface IVendedorDAO extends IGenericoDAO{

	public List<Vendedor>  getVendedores() throws FileNotFoundException;

	public void	guardar(Vendedor vendedor) throws IOException;
	
	public void eliminar(Vendedor vendedor) throws   IOException;
	
	public Vendedor getVendedor(Integer id) throws FileNotFoundException;

}
