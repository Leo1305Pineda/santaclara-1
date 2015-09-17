package santaclara.Servicio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import santaclara.dao.impl.ClienteDAO;
import santaclara.dao.impl.VisitaDAO;
import santaclara.modelo.Cliente;
import santaclara.modelo.Visita;


public class ServicioVisita {

	private ClienteDAO  clienteDAO = new ClienteDAO();
	private VisitaDAO visitaDAO = new VisitaDAO();
	
	private List<Visita> visitas;
	
	public List<Visita>  getVisitas() throws NumberFormatException, IOException
	{	
		return visitaDAO.getVisitas();
	}
		
	public List<Cliente> getClientes() throws Exception
	{
		return clienteDAO.getClientes();	
	}
	
	public void guardar(Visita visita) throws Exception {
		// TODO Auto-generated method stub
		
			visitas = visitaDAO.getVisitas();

			for(Visita visita1 :visitas)
			{
				if(visita1.getMotivo().equals(visita.getMotivo()) &&
					visita1.getJefeVenta().getId().equals(visita.getJefeVenta().getId())&&
					visita1.getCliente().getId().equals(visita.getCliente().getId())&&
					visita1.getDescripcion().equals(visita.getDescripcion()) &&	
					visita1.getFecha().equals(visita.getFecha()) &&
					visita1.getValorProducto().equals(visita.getValorProducto()) &&
					visita1.getValorVendedor().equals(visita.getValorVendedor()) &&
					!visita1.getId().equals(visita.getId()))
				{ 
					throw new Exception("Producto Existente");
				}
			}
			visitaDAO.guardar(visita); 
	}
	public Visita getVisita(Date fecha, Integer idJefeVenta ,Integer idCliente) throws FileNotFoundException{
		return new VisitaDAO().getVisita(fecha, idJefeVenta, idCliente);
	}
	public Boolean isVisita(Date fecha, Integer idJefeVenta ,Integer idCliente) throws FileNotFoundException{
		return new VisitaDAO().isVisita(fecha, idJefeVenta, idCliente);
	}
	public void  Eliminar(Visita visita) throws IOException{
		new VisitaDAO().eliminar(visita);
	}
}