package santaclara.Servicio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
	public Visita getVisita(Date fecha, Integer idUsuario ,Integer idCliente) throws FileNotFoundException{
		return new VisitaDAO().getVisita(fecha, idUsuario, idCliente);
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
							if (visita1.getUsuario() !=null)
							{
								if(visita1.getCliente().getId().equals(cliente.getId())&&
										visita1.getUsuario().getId().equals(jefeVentaCombo.getId()))
								{
									visitasAux.add(visita1);
								}
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
public List<Visita> ConsultaJefeVenta(JefeVenta jefeVenta, Integer mes,Integer yearActual) throws NumberFormatException, IOException{
	
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
					for(Visita visita1: visitas)
					{
						if (visita1.getUsuario() !=null)
						{
							if(visita1.getCliente().getId().equals(cliente.getId())&&
									visita1.getUsuario().getId().equals(jefeVentaCombo.getId()))
							{
								if (((new Integer(new SimpleDateFormat("MM").format(visita1.getFecha()))).equals(mes)&&
										((new Integer(new SimpleDateFormat("yyyy").format(visita1.getFecha()))).equals(yearActual))))
								{
									visitasAux.add(visita1);
								}
							}
						}
					}
				}
			}
		}
	}
		return visitasAux;	
	}
	return null;
}

public List<Visita> ConsultaConcesionario(Concesionario concesionario, Integer mes,Integer yearActual) throws NumberFormatException, IOException{
	
	Concesionario  concesionarioCombo = new Concesionario(); 
	concesionarioCombo = concesionario;
	if(concesionarioCombo!=null)
	{
	List<Visita> visitas = new ServicioVisita().getVisitas();
	List<Visita> visitasAux = new ArrayList<Visita>();
	List<DomicilioComercio> clientes = new ServicioDomicilioComercio().getDomicilioComercios();
	
	for(DomicilioComercio cliente : clientes)
	{		
		for(Visita visita1: visitas)
		{
			if(visita1.getUsuario() !=null)
			{
				if(visita1.getCliente().getId().equals(cliente.getId())&&
						visita1.getUsuario().getId().equals(concesionario.getId()))
				{
					if (((new Integer(new SimpleDateFormat("MM").format(visita1.getFecha()))).equals(mes)&&
							((new Integer(new SimpleDateFormat("yyyy").format(visita1.getFecha()))).equals(yearActual))))
					{
						visitasAux.add(visita1);
					}
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
	// lista los cliente donde la ruta es igual al del concesionario
	List<DomicilioComercio> domicilioComercios = new ServicioDomicilioComercio().getDomicilioComercios(concesionario.getRuta());
	
	for(DomicilioComercio domicilioComercio : domicilioComercios)
	{	
			Visita visita = new Visita();
			
			for(Visita visita1: visitas)
			{
				if(visita1.getUsuario() !=null)
				{
					if(visita1.getCliente().getId().equals(domicilioComercio.getId())&&
							visita1.getUsuario().getId().equals(concesionario.getId()))
					{
						visitasAux.add(visita1);
					}
				}
				
			}
			visita.setCliente(domicilioComercio);
			visita.setUsuario(concesionario);
			visita.setDescripcion("");
			visita.setEstado(null);
			visita.setFecha("");
			visita.setMotivo("");
			visita.setValorProducto(null);
			visita.setValorVendedor(null);
			
			visitasAux.add(visita);
	}
			return visitasAux;	
	}
	return null;
}

public List<List<Visita>> listaVisitas (List<Visita> visitas) throws Exception {
	
	List<List<Visita>> listaVisitas = new ArrayList<List<Visita>>(31);
	for(int i = 30 ;i>=0;i--) listaVisitas.add(null);
	
	while (!visitas.isEmpty())
	{
		List<Visita> visitasAux1 = new ArrayList<Visita>();
		List<Visita> visitasAux2 = new ArrayList<Visita>();
		Visita value = visitas.remove(0);
		visitasAux1.add(value);
		if (value!=null)
		{
			while(!visitas.isEmpty())
			{	
				if (new Integer(new SimpleDateFormat("dd").format(visitas.get(0).getFecha())).equals(new Integer(
								new SimpleDateFormat("dd").format(value.getFecha()))))
				{
					visitasAux1.add(visitas.remove(0));//la misma fecha
				}
				else visitasAux2.add(visitas.remove(0)); //fecha diferente
			}
			visitas = visitasAux2;
			
			listaVisitas.add(new Integer(new SimpleDateFormat("dd").format(value.getFecha())),visitasAux1);
		}
		else 
		{
			throw new Exception("El usuario no tiene Visita Reguistrada"+JOptionPane.showInternalConfirmDialog(new JPanel(), "Desea Reguistrar Una Nueva Visita"));
		}
	}
	return listaVisitas;
}
}
