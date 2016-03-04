/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import santaclara.dao.IUsuarioDAO;
import santaclara.dbPostgresql.modelo.PostgreSql;
import santaclara.modelo.Usuario;

public class UsuarioDAO extends GenericoDAO implements IUsuarioDAO{

	@Override
	public List<Usuario> getUsuarios() throws Exception {
		// TODO Auto-generated method stub
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		ResultSet rSet = new PostgreSql().getSelect(
				"Select id,username,cedula,nombre,contrasena From usuarios where id !=1"); 
	
		if(rSet==null) return null;
		
		while(rSet.next())usuarios.add(
				new Usuario(rSet.getInt(1), rSet.getString(2), 
						rSet.getString(3), rSet.getString(4), rSet.getString(5))); 
		
		return usuarios;
	}
	
	@Override
	public void guardar(Usuario usuario) throws Exception {
		// TODO Auto-generated method stub
		if (usuario.getId()==null){
			new PostgreSql().ejecutar(
					"INSERT INTO usuarios(username,cedula,nombre,contrasena) "
					+"VALUES ("
					+"'" +usuario.getUsername()	  + "', "
					+"'" +usuario.getCedula()	  + "', "
					+"'" +usuario.getNombre()     + "', "
					+"'" +usuario.getContrasena() + "');");	
		}
		else{
			new PostgreSql().ejecutar(
					"UPDATE usuarios SET"
					+"ubicacion  ='" +usuario.getUsername()   +"',"
					+"cedula     ='" +usuario.getCedula()	  +"',"
					+"nombre     ='" +usuario.getNombre()	  +"',"
					+"contrasena ='" +usuario.getContrasena() +"' "
					+"WHERE id   ="  +usuario.getId()         +"; ");
		}

	}

	@Override
	public void eliminar(Usuario usuario) throws Exception {
		// TODO Auto-generated method stub
		if(usuario!=null) new PostgreSql().ejecutar(
								  "DELETE FROM almacenes "
								  + "WHERE id = "+usuario.getId() +";");
	}

	@Override
	public Usuario getUsuario(String username) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rSet = new PostgreSql().getSelect(
								"Select id,username,cedula,nombre,contrasena From usuarios "
								+"where username = '"+ username +"';"); 
		
		if(rSet==null) return null;

		rSet.next();
		return new Usuario(rSet.getInt(1), rSet.getString(2), rSet.getString(3), rSet.getString(4), rSet.getString(5));
	}

	public Usuario getUsuario(Integer id) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rSet = new PostgreSql().getSelect(
				"Select id,username,cedula,nombre,contrasena From usuarios "
				+"where id = '"+ id +"';");  
		
		if(rSet==null) return null;
		
		rSet.next();
		return new Usuario(
				rSet.getInt("id"), rSet.getString("username"),
				rSet.getString("cedula"), rSet.getString("nombre"),
				rSet.getString("contrasena"));
    }	
	
	public Usuario getUsuarioCedula(String cedula) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rSet = new PostgreSql().getSelect(
				"Select id,username,cedula,nombre,contrasena From usuarios "
				+"where cedula = '"+ cedula +"';");  
		
		if(rSet==null) return null;
		
		rSet.next();
		return new Usuario(rSet.getInt(1), rSet.getString(2), rSet.getString(3), rSet.getString(4), rSet.getString(5));
	}
	
	/***Manejo con txt
	 private String ruta = "archivos/usuarios.txt";
	
	@Override
	public List<Usuario> getUsuarios() throws Exception {
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
	public Usuario getUsuario(String username) throws Exception {
		// TODO Auto-generated method stub
		List<Usuario> usuarios = getUsuarios();
		
		for(Usuario usuario: usuarios)
		{
			if(usuario.getUsername().equals(username))
			{
				return usuario;
			}
		}
		return null;

	}

	public Usuario getUsuario(Integer id) throws Exception {
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
	
	public Usuario getUsuarioCedula(String cedula) throws Exception {
		// TODO Auto-generated method stub
		List<Usuario> usuarios = getUsuarios();
		
		for(Usuario usuario: usuarios)
		{
			if(usuario.getCedula().equals(cedula))
			{
				return usuario;
			}
		}
		return null;
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
	
	 * /
	
	/*
 	La Estructura de los Archivos sera la Siguiente 
  	id:0
	username:Rhonal
	cedula:V-19827297
	nombres:Rhonal Chirino
	contrasena:1234
	 * */
	
} 


