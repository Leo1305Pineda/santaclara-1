package santaclara.dao.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import santaclara.dao.IConcesionarioRutaDAO;
import santaclara.modelo.Cliente;
import santaclara.modelo.ConcesionarioRuta;
import santaclara.modelo.Concesionario;
import santaclara.modelo.Ruta;

public class ConcesionarioRutaDAO extends GenericoDAO implements IConcesionarioRutaDAO{
	private String ruta = "archivos/concesionarioRutas.txt";
	@Override
	public List<ConcesionarioRuta> getConcesionarioRutas() throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<ConcesionarioRuta> concesionarioRutas = new ArrayList<ConcesionarioRuta>();
		File file = new File(ruta);
		String linea;
 		Scanner scaner = new Scanner(file);
		while(scaner.hasNext())
		{
			 ConcesionarioRuta concesionarioRuta = new ConcesionarioRuta();
			 
			 linea =scaner.skip("idCliente:").nextLine();
			 if(linea.trim().length() == 0)
			 {
				 concesionarioRuta.setCliente(null);
			 }
			 else
			 {
				 Cliente cliente = new Cliente();
				 concesionarioRuta.setCliente(cliente);				 	 
			 }
			 //asigna Ruta
			 linea =scaner.skip("idConcesionario").nextLine();
			 if(linea.trim().length() == 0)
			 {
				 concesionarioRuta.setConcesionario(null);
			 }
			 else
			 {
				 Concesionario concesionario = new Concesionario();
				 concesionarioRuta.setConcesionario(concesionario);				 	 
			 }
			 linea =scaner.skip("idRuta").nextLine();
			 if(linea.trim().length() == 0)
			 {
				 concesionarioRuta.setRuta(null);
			 }
			 else
			 {
				 Ruta ruta = new Ruta();
				 concesionarioRuta.setRuta(ruta);				 	 
			 }
			 
			 concesionarioRutas.add(concesionarioRuta); 
		}
		
		return concesionarioRutas;
	}

	@Override
	public void guardar(ConcesionarioRuta concesionarioRuta) throws IOException {
		// TODO Auto-generated method stub
		List<ConcesionarioRuta> consecionarioRutas = getConcesionarioRutas();
		// 
		Boolean enc= new Boolean(false);
		for(ConcesionarioRuta concesionarioRuta1 :consecionarioRutas)
		{
			if(concesionarioRuta1.getCliente().getId().equals(concesionarioRuta.getCliente().getId())
					&& concesionarioRuta1.getConcesionario().getId().equals(concesionarioRuta.getConcesionario().getId())
					&&  concesionarioRuta1.getRuta().getId().equals(concesionarioRuta.getRuta().getId()))
			{
				enc = true;
				concesionarioRuta1.setDias(concesionarioRuta.getDias());
			}
		}
		
		if(enc == false)
		{
			consecionarioRutas.add(concesionarioRuta);
			
		}
		guardarTodo(consecionarioRutas);
	}

	@Override
	public void eliminar(ConcesionarioRuta concesionarioRuta) throws IOException {
		// TODO Auto-generated method stub
		List<ConcesionarioRuta> consecionarioRutas = getConcesionarioRutas();
		for(ConcesionarioRuta concesionarioRuta1 :consecionarioRutas)
		{
			if(concesionarioRuta1.getCliente().getId().equals(concesionarioRuta.getCliente().getId())
					&& concesionarioRuta1.getConcesionario().getId().equals(concesionarioRuta.getConcesionario().getId())
					&&  concesionarioRuta1.getRuta().getId().equals(concesionarioRuta.getRuta().getId()))
			{
				consecionarioRutas.remove(concesionarioRuta1);
				break;
			}
		}
		//guardar Todo 
		guardarTodo(consecionarioRutas);
	}

	@Override
	public ConcesionarioRuta getConcesionarioRuta(Integer idCliente,Integer idConcesionario,Integer idRuta) throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<ConcesionarioRuta> concesionarioRutas = getConcesionarioRutas();
		for(ConcesionarioRuta concesionarioRuta1 :concesionarioRutas)
		{
			if(concesionarioRuta1.getCliente().getId().equals(idCliente)
					&& concesionarioRuta1.getConcesionario().getId().equals(idConcesionario)
					&&  concesionarioRuta1.getRuta().getId().equals(idRuta))
			{
				return concesionarioRuta1;
			}
		}
		return null;
	}
	public ConcesionarioRutaDAO(String ruta) {
		super();
		this.ruta = ruta;
	}

	public ConcesionarioRutaDAO( ) {
		super();  
	}
	public void guardarTodo(List<ConcesionarioRuta> concesionarioRutas ) throws IOException
	{
		FileWriter fw = new FileWriter(ruta);
		for(ConcesionarioRuta concesionarioRuta :concesionarioRutas)
		{
			fw.append("idCliente:"+concesionarioRuta.getCliente().toString()+"\n");
			fw.append("idConcesionario:"+concesionarioRuta.getConcesionario()+"\n");
			fw.append("idRuta:"+concesionarioRuta.getRuta()+"\n");
			fw.append("dia:"+concesionarioRuta.getDias()+"\n");
	}
	fw.close();
	}
}
/*
	La Estructura de los Archivos sera la Siguiente 
idCliente:03
idConcesionario:03
idRuta:00
dia:123
idCliente:04
id:usuario:04
idRuta:01
dia:456
* */
