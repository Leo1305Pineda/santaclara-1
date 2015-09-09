package santaclara.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import santaclara.modelo.Cliente;

public interface IClienteDAO extends IGenericoDAO {

	public List<Cliente>  getClientes() throws FileNotFoundException;

	public void	guardar(Cliente cliente) throws IOException;
	
	public void eliminar(Cliente cliente) throws   IOException;
	
	public void Mostrar() throws IOException; 
	
	public Cliente getCliente(Integer id) throws FileNotFoundException;
	
	public Boolean getCliente(Cliente cliente) throws FileNotFoundException;

	public Cliente getCliente(String rif) throws FileNotFoundException;

	
}	
