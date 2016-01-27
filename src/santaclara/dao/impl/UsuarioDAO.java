package santaclara.dao.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import santaclara.dao.IUsuarioDAO;
import santaclara.modelo.Usuario;

public class UsuarioDAO extends GenericoDAO implements IUsuarioDAO{

	private String ruta = "archivos/usuarios.txt";
	
	@Override
	public List<Usuario> getUsuarios() throws FileNotFoundException {
		// TODO Auto-generated method stub
		// Listar Todos lo Usuario 
		List<Usuario> usuarios = new ArrayList<Usuario>();
		File file = new File(ruta);
 		Scanner scanner = new Scanner(file);
		while(scanner.hasNext())
		{
			 Usuario usuario = new Usuario();
			 usuario.setId(new Integer(scanner.skip("id:").nextLine()));
			 usuario.setUsername(scanner.skip("username:").nextLine());
			 usuario.setCedula(scanner.skip("cedula:").nextLine());
			 usuario.setNombre(scanner.skip("nombre:").nextLine());
			 usuario.setContrasena(scanner.skip("contrasena:").nextLine());
			 		 
			 usuarios.add(usuario); 
		}
		scanner.close();
		return usuarios;
	}
	@Override
	public void guardar(Usuario usuario) throws IOException {
		// TODO Auto-generated method stub
		List<Usuario> usuarios = getUsuarios();
		//buscar codigo el ultimo codigo Asignado 
		if(usuario.getId() == null )
		{
			int i = 0;
			for(Usuario usuario1 : usuarios)
			{
				if(usuario1.getId()> i )
				{
					i = usuario1.getId();
				}
			}
			usuario.setId(i+1);
			usuarios.add(usuario);
		}
		else
		{
			for(Usuario usuario1 :usuarios)
			{
				if(usuario1.getId().equals(usuario.getId()))
				{
					/// vacio 
					usuario1.setId(usuario.getId());
					usuario1.setUsername(usuario.getUsername());
					usuario1.setCedula(usuario.getCedula());
					usuario1.setNombre(usuario.getNombre());
					usuario1.setContrasena(usuario.getContrasena());
				}
			}
		}
		guardarTodo(usuarios);
	}

	@Override
	public void eliminar(Usuario usuario) throws IOException {
		// TODO Auto-generated method stub
		List<Usuario> usuarios = getUsuarios();
		for(Usuario usuario1 :usuarios)
		{
			if(usuario1.getId().equals(usuario.getId()))
			{
				usuarios.remove(usuario1);
				break;
			}
		}
		///guardar Todo 
		guardarTodo(usuarios);
		
	}

	@Override
	public Usuario getUsuario(String username) throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<Usuario> usuarios = getUsuarios();
		
		for(Usuario usuario: usuarios)
		{
			if(usuario.getUsername().equals(username.trim()))
			{
				return usuario;
			}
		}
		return null;
    }

	public Usuario getUsuario(Integer id) throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<Usuario> usuarios = getUsuarios();
		
		for(Usuario usuario: usuarios)
		{
			if(usuario.getId().equals(id))
			{
				return usuario;
			}
		}
		return null;
    }
	
	public UsuarioDAO(String ruta) {
		super();
		this.ruta = ruta;
	}

	public UsuarioDAO( ) {
		super();  
	}
	
	public void guardarTodo(List<Usuario> usuarios) throws IOException
	{
		FileWriter fw = new FileWriter(ruta);
		for(Usuario usuario :usuarios)
		{
			fw.append("id:"+usuario.getId().toString()+"\n");
			fw.append("username:"+usuario.getUsername()+"\n");
			fw.append("cedula:"+usuario.getCedula()+"\n");
			fw.append("nombre:"+usuario.getNombre()+"\n");
			fw.append("contrasena:"+usuario.getContrasena()+"\n");
		}
		fw.close();
	}

	
	/*
 	La Estructura de los Archivos sera la Siguiente 
  	id:0
	username:Rhonal
	cedula:V-19827297
	nombres:Rhonal Chirino
	contrasena:1234
	 * */
	
} 


