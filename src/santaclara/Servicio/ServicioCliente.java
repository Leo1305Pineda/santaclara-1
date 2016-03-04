/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.Servicio;

import java.util.ArrayList;
import java.util.List;

import santaclara.dao.impl.ClienteDAO;
import santaclara.dao.impl.RutaDAO;
import santaclara.dao.impl.ZonaDAO;
import santaclara.modelo.Ruta;
import santaclara.modelo.Cliente;
import santaclara.modelo.Zona;

public class ServicioCliente {
	
	private ClienteDAO clienteDAO = new ClienteDAO();  
	private RutaDAO rutaDAO = new  RutaDAO();
	private ZonaDAO zonaDAO = new ZonaDAO();
	
	public List<Ruta>  getRutas() throws Exception
	{	
		return rutaDAO.getRutas();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Cliente> getClientes() throws Exception
	{
		List clientesAux = new ArrayList<>();
		
		clientesAux.addAll(new ServicioDomicilioComercio().getDomicilioComercios());
		clientesAux.addAll(new ServicioSalp().getSalps());
	
		return clientesAux;
	}
	
	public List<Zona> getZonas() throws Exception 
	{
		return zonaDAO.getZonas();
	}
	
	public Boolean guardar(Cliente cliente) throws Exception {
		// TODO Auto-generated method stub
		// if el es nuevo que no este otro con el mismo nombre 
		if (!this.clienteDAO.getCliente(cliente))//mientras que el nombre no se repita 
		{
			clienteDAO.guardar(cliente);
			return true;//Guardo 
		}
		return false;//no Guardo
	}
	
	public Cliente buscar(int id) throws Exception{
		
		return clienteDAO.getCliente(id);
	}
	public void eliminar (Cliente cliente)throws Exception{
		clienteDAO.eliminar(cliente);
	}
	public void modificar (Cliente cliente) throws Exception{
		clienteDAO.guardar(cliente);
	}

	public Cliente getCliente(String rif) throws Exception {
		// TODO Auto-generated method stub
		return clienteDAO.getCliente(rif);
	}
	public List<Cliente> getClientes(Ruta ruta) throws Exception{
		List<Cliente> clientes = new ClienteDAO().getClientes();
		List<Cliente> clientes2 = new ArrayList<Cliente>();
		for(Cliente cliente : clientes)
		{
			if (cliente.getRuta().getId().equals(ruta.getId())) clientes2.add(cliente);
		}
		return clientes2;
	}
}