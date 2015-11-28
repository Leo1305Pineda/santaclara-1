package santaclara.dao.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import santaclara.Servicio.ServicioCliente;
import santaclara.dao.ISalpDAO;
import santaclara.modelo.Cliente;
import santaclara.modelo.Salp;
//import santaclara.dao.IFactura;

public class SalpDAO extends GenericoDAO implements  ISalpDAO{

	private String ruta = "archivos/salps.txt";

	@Override
	public List<Salp> getSalps() throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<Salp> salps = new ArrayList<Salp>();
		ClienteDAO clienteDAO = new ClienteDAO();
		File file = new File(ruta);
 		Scanner scanner = new Scanner(file);
		while(scanner.hasNext())
		{
			 Salp salp = new Salp();
			 salp.setId(new Integer(scanner.skip("idCliente:").nextLine()));
			 salps.add(salp);
		}
		scanner.close();
		//Cargo la info de Cliente
		List<Cliente> clientes;
		clientes = clienteDAO.getClientes();
		for(Salp salp1 :salps)
		{
			for(Cliente cliente: clientes)
			{
				if(salp1.getId().equals(cliente.getId()))
				{
					salp1.setRif(cliente.getRif());
					salp1.setId(cliente.getId());
					salp1.setRazonsocial(cliente.getRazonsocial());
					salp1.setDireccion(cliente.getDireccion());
					salp1.setTelefono(cliente.getTelefono());
					salp1.setRuta(cliente.getRuta());
					break;
				}
			}
		}			  
		return salps;
		
	}

	@Override
	public void guardar(Salp salp) throws IOException {
		// TODO Auto-generated method stub
		List<Salp> salps = getSalps();
		List<Cliente> clientes = new ServicioCliente().getClientes();
		//buscar codigo el ultimo codigo Asignado 
		if(salp.getId() == null )
		{
			int i = 0;
			for(Cliente cliente : clientes)
			{
				if(cliente.getId()> i )
				{
					i = cliente.getId();
				}
			}
			
			Cliente cliente = new Cliente();
			
			cliente.setId(salp.getId());
			cliente.setRif(salp.getRif());
			cliente.setDireccion(salp.getDireccion());
			cliente.setTelefono(salp.getTelefono());
			cliente.setRazonsocial(salp.getRazonsocial());
			cliente.setRuta(salp.getRuta());
			
			new ClienteDAO().guardar(cliente);
			salp.setId(i+1);
			salps.add(salp);
		}
		else
		{
			for(Salp salp1 :salps)
			{
				if(salp1.getId().equals(salp.getId()))
				{
					Cliente cliente = new Cliente();
					
					cliente.setId(salp.getId());
					cliente.setRif(salp.getRif());
					cliente.setDireccion(salp.getDireccion());
					cliente.setTelefono(salp.getTelefono());
					cliente.setRazonsocial(salp.getRazonsocial());
					cliente.setRuta(salp.getRuta());
					
					new ClienteDAO().guardar(cliente);
					// aca los cambio del salp
					
				}
				
			}
		}
		guardarTodo(salps);
	}

	@Override
	public void eliminar(Salp salp) throws IOException {
		// TODO Auto-generated method stub		
		List<Salp> salps = getSalps();
		for(Salp salp1 :salps)
		{
			if(salp1.getId().equals(salp.getId()))
			{
				salps.remove(salp1);
				break;
			}
		}
		///guardar Todo 
		guardarTodo(salps);
	}

	@Override
	public Salp getSalp(Integer id) throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<Salp> salps = getSalps();
		for(Salp salp1 :salps)
		{
			if(salp1.getId().equals(id))
			{
				return salp1;
			}
		}
		return null;

	}

	public void guardarTodo(List<Salp> salps ) throws IOException
	{
		FileWriter fw = new FileWriter(ruta);
		for(Salp salp1 :salps)
		{
			fw.append("idCliente:"+(salp1 == null
					? "  ":salp1.getId().toString())+"\n");
		}
		fw.close();
	}
	
	/*
 	La Estructura de los Archivos sera la Siguiente 
idCliente:1
idFacturas:1,2
* */
	
} 

