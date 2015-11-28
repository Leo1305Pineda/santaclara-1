package santaclara.dao.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import santaclara.dao.IClienteDAO;
import santaclara.modelo.Cliente;

public class ClienteDAO extends GenericoDAO implements IClienteDAO{

	private String ruta = "archivos/Clientes.txt";

	@Override
	public List<Cliente> getClientes() throws FileNotFoundException {
		// TODO Auto-generated method stub
		// Listar Todos lo Clientes 
		List<Cliente> clientes = new ArrayList<Cliente>();
		File file = new File(ruta);
 		Scanner  scanner = new Scanner(file);
		while(scanner.hasNext())
		{
			 Cliente cliente = new Cliente();
			 cliente.setId(new Integer(scanner.skip("id:").nextLine().trim()));
			 cliente.setRif(scanner.skip("rif:").nextLine().trim());
			 cliente.setRazonsocial(scanner.skip("razonsocial:").nextLine().trim());
			 cliente.setDireccion(scanner.skip("direccion:").nextLine());
			 cliente.setTelefono(scanner.skip("telefono:").nextLine().trim());
			 String linea =scanner.skip("ruta:").nextLine().trim();
			 if(linea.trim().length() == 0)
			 {
				 cliente.setRuta(null);
			 }
			 else
			 {
				 RutaDAO rutaDAO = new RutaDAO();
				 cliente.setRuta(rutaDAO.getRuta(new Integer(linea)));				 	 
			 }
			 clientes.add(cliente); 
		}
  	    scanner.close();
		return clientes;
	}
	@Override
	public void guardar(Cliente cliente) throws IOException {
		// TODO Auto-generated method stub
		List<Cliente> clientes = getClientes();
		//buscar codigo el ultimo codigo Asignado 
		if(cliente.getId() == null )
		{
			int i = 0;
			for(Cliente cliente1 : clientes)
			{
				if(cliente1.getId()> i )
				{
					i = cliente1.getId();
				}
			}
			cliente.setId(i+1);
			clientes.add(cliente);
		}
		else
		{
			for(Cliente cliente1 :clientes)
			{
				if(cliente1.getId().equals(cliente.getId()))
				{ 
					cliente1.setRif(cliente.getRif());
					cliente1.setRazonsocial(cliente.getRazonsocial());
					cliente1.setDireccion(cliente.getDireccion());
					cliente1.setTelefono(cliente.getTelefono());
					cliente1.setRuta(cliente.getRuta());
				}
			}
		}
		guardarTodo(clientes);
	}

	@Override
	public void eliminar(Cliente cliente) throws IOException {
		// TODO Auto-generated method stub
		if (cliente != null)
		{
			List<Cliente> clientes = getClientes();
			for(Cliente cliente1 :clientes)
			{
				if(cliente1.getId().equals(cliente.getId()))
				{
					clientes.remove(cliente1);
					break;
				}
			}
			guardarTodo(clientes);
		}
	}

	@Override
	public Cliente getCliente(Integer id) throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<Cliente> clientes = getClientes();
		for(Cliente cliente1 :clientes)
		{
			if(cliente1.getId().equals(id))
			{
				return cliente1;
			}
		}
		return null;
    }
	
	public Boolean getCliente(Cliente cliente) throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<Cliente> clientes = getClientes();
		for(Cliente cliente1 :clientes)
		{
				if(cliente1.getRazonsocial().equals(cliente.getRazonsocial())&&
						!cliente1.getId().equals(cliente.getId()))
				{ 
					return true;
				}
		}
		return false;
    }
	
	public ClienteDAO(String ruta) {
		super();
		this.ruta = ruta;
	}

	public ClienteDAO( ) {
		super();  
	}
	
	public void guardarTodo(List<Cliente> clientes ) throws IOException
	{
		FileWriter fw = new FileWriter(ruta);
		for(Cliente cliente :clientes)
		{
			fw.append("id:"+cliente.getId().toString()+"\n");
			fw.append("rif:"+cliente.getRif()+"\n");
			fw.append("razonsocial:"+cliente.getRazonsocial()+"\n");
			fw.append("direccion:"+cliente.getDireccion()+"\n");
			fw.append("telefono:"+cliente.getTelefono()+"\n");
			fw.append("ruta:"+(cliente.getRuta() == null ? "  ":cliente.getRuta().getId())+"\n");
		}
		fw.close();
	}


	public void Mostrar() throws IOException{
		List<Cliente> clientes = getClientes();
		System.out.println("Listar Todos los Clientes");
		for(Cliente cliente1 :clientes)
		{
			System.out.println("id: "+cliente1.getId());
			System.out.println("rif: "+cliente1.getRif());
			System.out.println("razonSocial: "+cliente1.getRazonsocial());
			System.out.println("direccion: "+cliente1.getDireccion());
			System.out.println("telefono: "+cliente1.getTelefono()+"\n");
			
			System.out.println("Informacion de la Ruta del Cliente: ");
			System.out.println("id: "+cliente1.getRuta().getId());
			System.out.println("nombre: "+cliente1.getRuta().getNombre()+"\n");
			
			System.out.println("Informacion de la Ruta-Zona del Cliente: ");
			System.out.println("id: "+cliente1.getRuta().getZona().getId());
			System.out.println("descripcion: "+cliente1.getRuta().getZona().getDescripcion()+"\n");
			
		}
	}
	/*
 	La Estructura de los Archivos sera la Siguiente 
  	id:1
	rif:V-19827297
	razonsocial:Rhonal Alfredo
	direccion:Barrio el Jebe Sector la Estrella
	telefono:04161556613
	ruta:1
  * */
	@Override
	public Cliente getCliente(String rif) throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<Cliente> clientes = getClientes();
		for(Cliente cliente1 :clientes)
		{
			if(cliente1.getRif().equals(rif))
			{
				return cliente1;
			}
		}
		return null;
	}
	
} 

