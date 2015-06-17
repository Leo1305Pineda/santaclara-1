package santaclara.dao.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
 


import java.util.Scanner;

import santaclara.dao.IDomicilioComercioDAO;

import santaclara.modelo.DomicilioComercio;

public class DomicilioComercioDAO extends GenericoDAO implements  IDomicilioComercioDAO{

	private String ruta = "archivos/domicilioComercio.txt";
	private Scanner scaner;

	@Override
	public List<DomicilioComercio> getDomicilioComercios() throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<DomicilioComercio> domicilioComercios = new ArrayList<DomicilioComercio>();
		File file = new File(ruta);
 		scaner = new Scanner(file);
		while(scaner.hasNext())
		{
			 DomicilioComercio domicilioComercio = new DomicilioComercio();
			 
			 domicilioComercio.setId(new Integer(scaner.skip("idCliente:").nextLine().trim()));
			 domicilioComercio.setTipo(scaner.skip("tipo:").nextLine().trim());
			 
			 domicilioComercios.add(domicilioComercio); 
		}
		
		return domicilioComercios;

	}

	@Override
	public void guardar(DomicilioComercio domicilioComercio) throws IOException {
		// TODO Auto-generated method stub
		List<DomicilioComercio> DomicilioComercios = getDomicilioComercios();
		//buscar codigo el ultimo codigo Asignado 
		if(domicilioComercio.getId() == null )
		{
			int i = 0;
			for(DomicilioComercio domicilioComercio1 : DomicilioComercios)
			{
				if(domicilioComercio1.getId()> i )
				{
					i = domicilioComercio1.getId();
				}
			}
			domicilioComercio.setId(i+1);
			DomicilioComercios.add(domicilioComercio);
		}
		else
		{
			for(DomicilioComercio domicilioComercio1 :DomicilioComercios)
			{
				if(domicilioComercio1.getId().equals(domicilioComercio.getId()))
				{ 
					domicilioComercio1.setRif(domicilioComercio.getRif());
					domicilioComercio1.setRazonsocial(domicilioComercio.getRazonsocial());
					domicilioComercio1.setDireccion(domicilioComercio.getDireccion());
					domicilioComercio1.setTelefono(domicilioComercio.getTelefono());
					domicilioComercio1.setRuta(domicilioComercio.getRuta());
					domicilioComercio1.setTipo(domicilioComercio.getTipo());
				}
			}
		}
		guardarTodo(DomicilioComercios);

	}

	@Override
	public void eliminar(DomicilioComercio domicilioComercio) throws IOException {
		// TODO Auto-generated method stub
		List<DomicilioComercio> domicilioComercios = getDomicilioComercios();
		for(DomicilioComercio domiciliocomercio1 :domicilioComercios)
		{
			if(domiciliocomercio1.getId().equals(domicilioComercio.getId()))
			{
				domicilioComercios.remove(domiciliocomercio1);
				break;
			}
		}
		guardarTodo(domicilioComercios);
	}

	@Override
	public DomicilioComercio getDomicilioComercio(Integer id) throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<DomicilioComercio> domicilioComercios = getDomicilioComercios();
		for(DomicilioComercio domicilioComercio1 :domicilioComercios)
		{
			if(domicilioComercio1.getId().equals(id))
			{
				return domicilioComercio1;
			}
		}
		return null;

	}

	public void guardarTodo(List<DomicilioComercio> domicilioComercios ) throws IOException
	{
		FileWriter fw = new FileWriter(ruta);
		for(DomicilioComercio domicilioComercio :domicilioComercios)
		{
			fw.append("idCliente:"+(domicilioComercio == null 
					? "  ":domicilioComercio.getId().toString())+"\n");
			
			fw.append("tipo:"+domicilioComercio.getTipo().toString()+"\n");
			
		}
		fw.close();
	}
	
	
	
	/*
 	La Estructura de los Archivos sera la Siguiente 
idCliente:1
tipo:C
* */
	
} 

