package santaclara.dao.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import santaclara.dao.ISalpDAO;
import santaclara.modelo.Cliente;
import santaclara.modelo.Factura;
import santaclara.modelo.Salp;
//import santaclara.dao.IFactura;

public class SalpDAO extends GenericoDAO implements  ISalpDAO{

	private String ruta = "archivos/salps.txt";
	private Scanner scaner;

	@SuppressWarnings("resource")
	@Override
	public List<Salp> getSalps() throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<Salp> salps = new ArrayList<Salp>();
		ClienteDAO clienteDAO = new ClienteDAO();
		File file = new File(ruta);
 		scaner = new Scanner(file);
		while(scaner.hasNext())
		{
			 Salp salp = new Salp();
			 salp.setId(new Integer(scaner.skip("idCliente:").nextLine()));
			
			 Scanner sc = new Scanner(scaner.skip("idFacturas:").nextLine()).useDelimiter(",");
			 
			 //cargo la lista de facturas
			 if (sc.hasNext())
			 {
				 List<Factura> facturas = new ArrayList<Factura>();
				 while(sc.hasNext())
				 {
					 Factura factura = new Factura();
					 factura.setId(sc.nextInt());
					 facturas.add(factura);
				 }
				 salp.setFacturas(facturas);
				 salps.add(salp);
			 }
			 else
			 {
				 salp.setFacturas(null);
				 salps.add(salp);
			 }
			 sc.close();
		}
		//Cargo la info de Cliente
		List<Cliente> clientes;
		clientes = clienteDAO.getClientes();
		for(Salp sapl1 :salps)
		{
			for(Cliente cliente: clientes)
			{
				if(sapl1.getId().equals(cliente.getId()))
				{
					sapl1.setRif(cliente.getRif());
					sapl1.setId(cliente.getId());
					sapl1.setRazonsocial(cliente.getRazonsocial());
					sapl1.setDireccion(cliente.getDireccion());
					sapl1.setTelefono(cliente.getTelefono());
					break;
				}
			}
		}
		//Cargo la info de las Facturas
//		List<Factura> facturas;
	//	facturas = FacturaDAO.getFacturas();
		for(Salp sapl1 :salps)
		{
			for(Cliente cliente: clientes)
			{
				if(sapl1.getId().equals(cliente.getId()))
				{
					sapl1.setRif(cliente.getRif());
					sapl1.setId(cliente.getId());
					sapl1.setRazonsocial(cliente.getRazonsocial());
					sapl1.setDireccion(cliente.getDireccion());
					sapl1.setTelefono(cliente.getTelefono());
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
		//buscar codigo el ultimo codigo Asignado 
		if(salp.getId() == null )
		{
			int i = 0;
			for(Salp salp1 : salps)
			{
				if(salp1.getId()> i )
				{
					i = salp1.getId();
				}
			}
			salp.setId(i+1);
			salps.add(salp);
		}
		else
		{
			for(Salp salp1 :salps)
			{
				if(salp1.getId().equals(salp.getId()))
				{
					/// vacio 
					salp1.setRif(salp.getRif());
					salp1.setRazonsocial(salp.getRazonsocial());
					salp1.setDireccion(salp.getDireccion());
					salp1.setTelefono(salp.getTelefono());
					salp1.setRuta(salp.getRuta());
					salp1.setFacturas(salp.getFacturas());
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

	public void guardarTodo(List<Salp> domicilioComercios ) throws IOException
	{
		FileWriter fw = new FileWriter(ruta);
		for(Salp domicilioComercio :domicilioComercios)
		{
			fw.append("idCliente:"+(domicilioComercio == null
					? "  ":domicilioComercio.getId().toString())+"\n");
			
			List<Factura> facturas = domicilioComercio.getFacturas();
			String linea = new String(",");
			if (domicilioComercio.getFacturas()==null)
			{
			linea = "";	
			}
			else
			{
				for(Factura factura : facturas ) 
				{
					linea =  linea+factura.getId()+",";
				}
			}
			fw.append("idFacturas:"+linea+"\n");
		}
		fw.close();
	}
	
	/*
 	La Estructura de los Archivos sera la Siguiente 
idCliente:1
idFacturas:1,2
* */
	
} 

