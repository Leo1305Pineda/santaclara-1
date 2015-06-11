package santaclara.dao.impl;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import santaclara.dao.IConcesionarioDAO;
import santaclara.modelo.Camion;
import santaclara.modelo.Concesionario;
import santaclara.modelo.Ruta;
import santaclara.modelo.Usuario;

public class ConcesionarioDAO extends GenericoDAO implements IConcesionarioDAO{

	private String ruta = "archivos/concesionarios.txt";
	
	public ConcesionarioDAO(String ruta) {
		super();
		this.ruta = ruta;
	}

	public ConcesionarioDAO( ) {
		super();  
	}
	
	public void guardarTodo(List<Concesionario> concesionarios) throws IOException
	{
		FileWriter fw = new FileWriter(ruta);
		for(Concesionario concesionario : concesionarios)
		{
			fw.append("id:"+concesionario.getId().toString()+"\n");
			fw.append("camion:"+concesionario.getCamion().getId().toString()+"\n");
			fw.append("ruta:"+concesionario.getRuta().getId().toString()+"\n");

		}
		fw.close();
	}

	@Override
	public List<Concesionario> getConcecionarios() throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<Concesionario> concesionarios = new ArrayList<Concesionario>();
		File file = new File(ruta);
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List<Usuario> usuarios;
 		Scanner scaner = new Scanner(file);
		while(scaner.hasNext())
		{
			 Concesionario concesionario = new Concesionario();
			 concesionario.setId(new Integer(scaner.skip("id:").nextLine()));
			 Camion camion = new Camion();
			 camion.setId(new Integer(scaner.skip("camion:").nextLine()));
			 Ruta ruta = new Ruta();
			 ruta.setId(new Integer(scaner.skip("ruta:").nextLine()));
			 concesionario.setRuta(ruta);
			 concesionario.setCamion(camion);
			 concesionarios.add(concesionario); 
		}
		scaner.close();
		//guardar demas datos del vendedor 
		usuarios = usuarioDAO.getUsuarios();
		
		for(Concesionario concesionario: concesionarios)
		{
			for(Usuario usuario: usuarios)
			{
				if(concesionario.getId().equals(usuario.getId()))
				{
					concesionario.setCedula(usuario.getCedula());
					concesionario.setContrasena(usuario.getContrasena());
					concesionario.setNombre(usuario.getNombre());
					concesionario.setUsername(usuario.getUsername());
					break;
				}
			}
		}
		return concesionarios;
	}

	@Override
	public void guardar(Concesionario concesionario) throws IOException {
		// TODO Auto-generated method stub
		List<Concesionario> concesionarios = getConcecionarios();
		//buscar codigo el ultimo codigo Asignado 
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if(concesionario.getId() == null )
		{
			int i = 0;
			for(Concesionario concesionario1 : concesionarios)
			{
				if(concesionario1.getId()> i )
				{
					i = concesionario1.getId();
				}
			}
			concesionario.setId(i+1);
			concesionarios.add(concesionario);
		}
		else
		{
			for(Concesionario concesionario1 : concesionarios)
			{
				if(concesionario1.getId().equals(concesionario.getId()))
				{
					/// vacio 
					concesionario1.setId(concesionario.getId());
					concesionario1.setUsername(concesionario.getUsername());
					concesionario1.setCedula(concesionario.getCedula());
					concesionario1.setNombre(concesionario.getNombre());
					concesionario1.setContrasena(concesionario.getContrasena());
				}
			}
		}
		usuarioDAO.guardar(concesionario);
		guardarTodo(concesionarios);

	}

	@Override
	public void eliminar(Concesionario concesionario) throws IOException {
		List<Concesionario> concecionarios =getConcecionarios();
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		for(Concesionario concesionario1 :concecionarios)
		{
			if(concesionario1.getId().equals(concesionario.getId()))
			{
				concecionarios.remove(concesionario1);
				break;
			}
		}
		///guardar Todo 
		usuarioDAO.eliminar(concesionario);
		guardarTodo(concecionarios);
		
	}

	@Override
	public Concesionario getConcesionario(Integer id)
			throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<Concesionario> concesionarios = getConcecionarios();
		for(Concesionario concecionario : concesionarios)
		{
			if(concecionario.getId().equals(id))
			{
				return concecionario;
			}
		}
		return null;
	}

	
	
} 


