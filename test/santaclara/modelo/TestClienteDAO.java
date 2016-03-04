package santaclara.modelo;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import santaclara.dao.impl.ClienteDAO;
import santaclara.modelo.Cliente; 
public class TestClienteDAO {

	@Test
	public void clientesTest() throws Exception {
		ClienteDAO clienteDAO = new ClienteDAO();
		assertNotNull(clienteDAO);
		assertEquals(4,clienteDAO.getClientes().size());
	}

	@Test
	public void addRemoveClienteTest() throws Exception {
		ClienteDAO clienteDAO = new ClienteDAO();
		List<Cliente> clientes= clienteDAO.getClientes();
		
		Cliente cliente1 = new Cliente();
		cliente1.setRif("V-1204402");
		cliente1.setRazonsocial("Juana Petra");
		cliente1.setDireccion("aquiiiii :-D ");
		cliente1.setRuta(null);
		cliente1.setTelefono("0913091313");
		
		clienteDAO.guardar(cliente1);
		assertNotNull(cliente1.getId());
		assertNotEquals(4,clienteDAO.getClientes().size());
		assertEquals(clientes.size()+1,clienteDAO.getClientes().size());

		clienteDAO.eliminar(cliente1);
		assertEquals(clientes.size(),clienteDAO.getClientes().size());
	}

	
}
