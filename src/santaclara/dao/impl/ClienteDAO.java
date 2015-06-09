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
import santaclara.modelo.Ruta;

public class ClienteDAO extends GenericoDAO implements IClienteDAO{

	private String ruta = "archivos/Clientes.txt";
	
	@Override 
	public List<Cliente> getClientes() throws FileNotFoundException {
		// TODO Auto-generated method stub
		// Listar Todos lo Clientes 
		List<Cliente> clientes = new ArrayList<Cliente>();
		File file = new File(ruta);
 		Scanner scaner = new Scanner(file);
		while(scaner.hasNext())
		{
			 Cliente cliente = new Cliente();
			 cliente.setId(new Integer(scaner.skip("id:").nextLine()));
			 cliente.setCedula(scaner.skip("cedula:").nextLine());
			 cliente.setNombre(scaner.skip("nombre:").nextLine());
			 cliente.setApellido(scaner.skip("apellido:").nextLine());
			 cliente.setDireccion(scaner.skip("direccion:").nextLine());
			 cliente.setTelefono(scaner.skip("telefono:").nextLine());
			 String linea =scaner.skip("ruta:").nextLine();
			 if(linea.trim().length() == 0)
			 {
				 cliente.setRuta(null);
			 }
			 else
			 {
				 Ruta ruta = new Ruta();
				 ruta.setId(new Integer(linea));
				 cliente.setRuta(ruta);				 	 
			 }
			 clientes.add(cliente); 
		}
		
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
					/// vacio 
					cliente1.setCedula(cliente.getCedula());
					cliente1.setNombre(cliente.getNombre());
					cliente1.setApellido(cliente.getApellido());
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
		List<Cliente> clientes = getClientes();
		for(Cliente cliente1 :clientes)
		{
			if(cliente1.getId().equals(cliente.getId()))
			{
				clientes.remove(cliente1);
				break;
			}
		}
		///guardar Todo 
		guardarTodo(clientes);
		
	}

	@Override
	public Cliente getCliente(String cedula) {
		// TODO Auto-generated method stub
		return null;
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
			fw.append("cedula:"+cliente.getCedula()+"\n");
			fw.append("nombre:"+cliente.getNombre()+"\n");
			fw.append("apellido:"+cliente.getApellido()+"\n");
			fw.append("direccion:"+cliente.getDireccion()+"\n");
			fw.append("telefono:"+cliente.getTelefono()+"\n");
			fw.append("ruta:"+(cliente.getRuta() == null ? "  ":cliente.getRuta().getId())+"\n");
		}
		fw.close();
	}

	
	/*
 	La Estructura de los Archivos sera la Siguiente 
  	id: 00
	ced: V-19827297
	nom: Rhonal Alfredo
	ape: Cirinos Rodriguez
	dir: Barrio el Jebe Sector la Estrella 
	tel: 0416155-6613
	ru:  null
  * */
	
} 

