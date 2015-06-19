package santaclara.Servicio;


import java.io.IOException;
import java.util.List;
import santaclara.dao.impl.ClienteDAO;
import santaclara.dao.impl.RutaDAO;
import santaclara.modelo.Ruta;
import santaclara.modelo.Cliente;

public class ServicioCliente {
	
	private ClienteDAO clienteDAO = new ClienteDAO();  
	

	private RutaDAO rutaDAO = new  RutaDAO();
	
	
	public List<Ruta>  getRutas() throws NumberFormatException, IOException
	{	
		return rutaDAO.getRutas();
	}
		
	
	public void guardar(Cliente cliente) throws IOException {
		// TODO Auto-generated method stub
		// if el es nuevo que no este otro con el mismo nombre
		clienteDAO.guardar(cliente);
		
	}
	
}