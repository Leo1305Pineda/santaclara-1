package santaclara.dao.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import santaclara.dao.IVisitaDAO;
import santaclara.modelo.Visita;

public class VisitaDAO extends GenericoDAO implements IVisitaDAO{

	private String ruta = "archivos/visitas.txt";
	private Scanner scaner;

	@Override
	public List<Visita> getVisitas() throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<Visita> visitas = new ArrayList<Visita>();
		File file = new File(ruta);
 		scaner = new Scanner(file);
 		String linea;
		while(scaner.hasNext())
		{
			 Visita visita = new Visita();
			 visita.setId(new Integer(scaner.skip("id:").nextLine().trim()));
			 Date fecha = new Date();
			try {
				fecha = new SimpleDateFormat("dd/mm/yyyy").parse(scaner.skip("fecha:").nextLine().toString().trim());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 visita.setFecha(fecha);
			 
			 visita.setMotivo(scaner.skip("motivo:").nextLine());
			 visita.setDescripcion(scaner.skip("descripcion:").nextLine());
			 visita.setValorVendedor(new Integer(scaner.skip("valorVendedor:").nextLine().trim()));
			 visita.setValorProducto(new Integer(scaner.skip("valorProducto:").nextLine().trim()));
			 linea = new String();
			 linea = scaner.skip("estado:").nextLine().trim();
			 if (linea.toString().equals("hecho"))
				 {
				 visita.setEstado(true);
				 }
			 else if((linea.toString().equals("pendiente")))
				 {
				  visita.setEstado(false);
				 }
			 
			 linea =scaner.skip("idJefeVenta:").nextLine().trim();
			 if(linea.trim().length() == 0)
			 {
				 visita.setJefeVenta(null);
			 }
			 else
			 {
				 JefeVentaDAO jefeVentaDAO = new JefeVentaDAO();
				 visita.setJefeVenta(jefeVentaDAO.getJefeVenta(new Integer(linea)));				 	 
			 }
			 
			 linea =scaner.skip("idCliente:").nextLine().trim();
			 if(linea.trim().length() == 0)
			 {
				 visita.setCliente(null);
			 }
			 else
			 {
				 ClienteDAO clienteDAO = new ClienteDAO();
				 visita.setCliente(clienteDAO.getCliente(new Integer(linea)));				 	 
			 }
			 
			 visitas.add(visita); 
		}
		
		return visitas;

	}

	@Override
	public void guardar(Visita visita) throws IOException {
		// TODO Auto-generated method stub
		List<Visita> visitas = getVisitas();
		//buscar codigo el ultimo codigo Asignado 
		
		if(visita.getId() == null )
		{
			int i = 0;
			for(Visita visita1 : visitas)
			{
				if(visita1.getId()> i )
				{
					i = visita1.getId();
				}
			}
			visita.setId(i+1);
			visitas.add(visita);
		}
		else
		{
			for(Visita visita1 :visitas)
			{
				if(visita1.getId().equals(visita.getId()))
				{ 
					visita1.setDescripcion(visita.getDescripcion());
					visita1.setEstado(visita.getEstado());
					visita1.setFecha(visita.getFecha());
					visita1.setJefeVenta(visita.getJefeVenta());
					visita1.setMotivo(visita.getMotivo());
					visita1.setValorProducto(visita.getValorProducto());
					visita1.setValorVendedor(visita.getValorVendedor());
					visita1.setCliente(visita.getCliente());
				}
			}
		}
		guardarTodo(visitas);

	}

	@Override
	public void eliminar(Visita visita) throws IOException {
		// TODO Auto-generated method stub
		List<Visita> Visitas = getVisitas();
		for(Visita visita1 :Visitas)
		{
			if(visita1.getId().equals(visita.getId()))
			{
				Visitas.remove(visita1);
				break;
			}
		}
		///guardar Todo 
		guardarTodo(Visitas);
	}

	@Override
	public Visita getVisita(Date fecha,Integer idJefeVenta,Integer idCliente) throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<Visita> visitas = getVisitas();
		
		for(Visita visita1 :visitas)
		{
			if(visita1.getFecha().equals(fecha)
					&& visita1.getJefeVenta().getId().equals(idJefeVenta)
					&& visita1.getCliente().getId().equals(idCliente))
			{
				return visita1;
			}
		}
		return null;
	}
	
	public String getFechaStr(Date fecha) {
		if (fecha==null)return "";
		else{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
			return sdf.format(fecha);
		} 
	}
	
	public Boolean isVisita(Date fecha,Integer idCliente,Integer idJefeVenta)throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<Visita> visitas = getVisitas();
		
		for(Visita visita1 :visitas)
		{
			
			System.out.println(getFechaStr(fecha)+" = "+getFechaStr(visita1.getFecha()));	
			if(visita1.getFecha().equals(fecha)
					&& visita1.getJefeVenta().getId().equals(idJefeVenta)
					&& visita1.getCliente().getId().equals(idCliente))
			{
				return true;
			}
		}
		return false;
	}
	
	public void guardarTodo(List<Visita> visitas) throws IOException
	{
		FileWriter fw = new FileWriter(ruta);
		for(Visita visita : visitas)
		{
			fw.append("id:"+visita.getId().toString()+"\n");
			fw.append("fecha:"+visita.getFechaStr()+"\n");
			fw.append("motivo:"+visita.getMotivo().toString()+"\n");
			fw.append("descripcion:"+visita.getDescripcion().toString()+"\n");
			fw.append("valorVendedor:"+visita.getValorVendedor().toString()+"\n");
			fw.append("valorProducto:"+visita.getValorProducto().toString()+"\n");
			if(visita.getEstado()==true)
			fw.append("estado:hecho\n");
			else fw.append("estado:pendiente\n");
			fw.append("idJefeVenta:"+(visita.getJefeVenta()== null
					? "  ":visita.getJefeVenta().getId().toString())+"\n");
			
			fw.append("idCliente:"+(visita.getCliente() == null 
					?" ":visita.getCliente().getId().toString())+"\n");
		}
		fw.close();
	}

}
	
	/*
 	La Estructura de los Archivos sera la Siguiente 
	id:3
fecha:06/06/2015
motivo:en espera
descripcion:por realizar
valorVendedor:0
valorProducto:0
estado:pendiente
idJefeVenta:1
idCliente:3


  * */
	

