package santaclara.dao.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import santaclara.dao.IConcesionarioRutaDAO;
import santaclara.modelo.ConcesionarioRuta;

public class ConcesionarioRutaDAO extends GenericoDAO implements IConcesionarioRutaDAO{
	private String ruta = "archivos/concesionarioRutas.txt";
	@Override
	public List<ConcesionarioRuta> getConcesionarioRutas() throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<ConcesionarioRuta> concesionarioRutas = new ArrayList<ConcesionarioRuta>();
		File file = new File(ruta);
 		Scanner scanner = new Scanner(file);
 		
 		while(scanner.hasNext())
		{
			ConcesionarioRuta concesionarioRuta = new ConcesionarioRuta();
			 
			ClienteDAO clienteDAO = new ClienteDAO();
			concesionarioRuta.setCliente(
					clienteDAO.getCliente(
						new Integer(scanner.skip("idCliente:").nextLine().trim())));
			 
			ConcesionarioDAO concesionarioDAO = new ConcesionarioDAO();
			concesionarioRuta.setConcesionario(
					concesionarioDAO.getConcesionario(
							new Integer(scanner.skip("idConcesionario:").nextLine().trim())));				 	 
			 
			RutaDAO rutaDAO = new RutaDAO();
			 concesionarioRuta.setRuta(
					 rutaDAO.getRuta(
							 new Integer(scanner.skip("idRuta:").nextLine().trim())));				 	 
			 
			 concesionarioRuta.setDias(scanner.skip("dias:").nextLine().trim());
			 
			 concesionarioRutas.add(concesionarioRuta); 
		}
 		scanner.close();
		return concesionarioRutas;
	}

	@Override
	public void guardar(ConcesionarioRuta concesionarioRuta) throws IOException {
		// TODO Auto-generated method stub
		List<ConcesionarioRuta> consecionarioRutas = getConcesionarioRutas(); 
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
		return new ConcesionarioRuta();
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
			fw.append("idCliente:"+(concesionarioRuta.getCliente() == null 
					? " ":concesionarioRuta.getCliente().getId().toString())+"\n");
			fw.append("idConcesionario:"+(concesionarioRuta.getConcesionario() == null 
					? "  ":concesionarioRuta.getConcesionario().getId())+"\n");
			fw.append("idRuta:"+(concesionarioRuta.getRuta() == null 
					? "  ":concesionarioRuta.getRuta().getId())+"\n");
			fw.append("dias:"+concesionarioRuta.getDias()+"\n");
	}
	fw.close();
	}
}
/*
	La Estructura de los Archivos sera la Siguiente 
idCliente:03
idConcesionario:03
idRuta:00
dias:123
idCliente:04
id:usuario:04
idRuta:01
dias:456
* */
