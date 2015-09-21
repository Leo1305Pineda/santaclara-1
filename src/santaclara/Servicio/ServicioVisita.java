package santaclara.Servicio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import santaclara.dao.impl.ClienteDAO;
import santaclara.dao.impl.VisitaDAO;
import santaclara.modelo.Cliente;
import santaclara.modelo.Concesionario;
import santaclara.modelo.DomicilioComercio;
import santaclara.modelo.JefeVenta;
import santaclara.modelo.Ruta;
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
					visita1.getUsuario().getId().equals(visita.getUsuario().getId())&&
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
	
public List<Visita> ConsultaJefeVenta(JefeVenta jefeVenta) throws NumberFormatException, IOException{
		
		JefeVenta jefeVentaCombo = new JefeVenta();
		jefeVentaCombo = jefeVenta;
		if(jefeVentaCombo!=null)
		{
		List<Visita> visitas = new ServicioVisita().getVisitas();
		List<Visita> visitasAux = new ArrayList<Visita>();
		List<Ruta> rutas = new ServicioRuta().getRutas();
		List<Cliente> clientes = new ServicioCliente().getClientes();
		
		for(Ruta ruta: rutas)
		{
			if(ruta.getZona().getId().equals(jefeVentaCombo.getZona().getId()))
			{
				for(Cliente cliente: clientes)
				{
					if(cliente.getRuta().getId().equals(ruta.getId()))
					{
						Visita visita = new Visita();
						
						for(Visita visita1: visitas)
						{
							if(visita1.getCliente().getId().equals(cliente.getId())&&
									visita1.getUsuario().getId().equals(jefeVentaCombo.getId()))
							{
								visitasAux.add(visita1);
							}
						}
						visita.setCliente(cliente);
						visita.setUsuario(jefeVentaCombo);
						visita.setDescripcion("");
						visita.setEstado(null);
						visita.setFecha("");
						visita.setMotivo("");
						visita.setValorProducto(null);
						visita.setValorVendedor(null);
						
						visitasAux.add(visita);
					}
				}
			}
		}
			return visitasAux;	
		}
		return null;
	}

public List<Visita> ConsultaConcesionario(Concesionario concesionario) throws NumberFormatException, IOException{
	
	
	if(concesionario!=null)
	{
	List<Visita> visitas = new ServicioVisita().getVisitas();
	List<Visita> visitasAux = new ArrayList<Visita>();
	List<Cliente> clientes = new ServicioCliente().getClientes();
	
	for(Cliente cliente: clientes)
	{
		if(cliente.getRuta().getId().equals(concesionario.getRuta().getId())&&
				cliente.getClass().getName().equals(new DomicilioComercio().getClass().getName()))
		{
			Visita visita = new Visita();
			
			for(Visita visita1: visitas)
			{
				if(visita1.getCliente().getId().equals(cliente.getId())&&
						visita1.getUsuario().getId().equals(concesionario.getId()))
				{
					visitasAux.add(visita1);
				}
			}
			visita.setCliente(cliente);
			visita.setUsuario(concesionario);
			visita.setDescripcion("");
			visita.setEstado(null);
			visita.setFecha("");
			visita.setMotivo("");
			visita.setValorProducto(null);
			visita.setValorVendedor(null);
			
			visitasAux.add(visita);
		}
	}
			return visitasAux;	
	}
	return null;
}


}