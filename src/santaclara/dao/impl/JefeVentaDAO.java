package santaclara.dao.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import santaclara.dao.IJefeVentaDAO;
import santaclara.modelo.JefeVenta;
import santaclara.modelo.Usuario; 
import santaclara.modelo.Zona;

public class JefeVentaDAO extends GenericoDAO implements IJefeVentaDAO{

	private String ruta = "archivos/jefeVentas.txt";
	
	@Override
	public List<JefeVenta> getJefeVentas() throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<JefeVenta> jefeVentas = new ArrayList<JefeVenta>();
		File file = new File(ruta);
 		Scanner scanner = new Scanner(file);
		while(scanner.hasNext())
		{
			 JefeVenta jefeVenta = new JefeVenta();
			 
			 UsuarioDAO usuarioDAO = new UsuarioDAO();
			 
			 jefeVenta = getJefeVenta(usuarioDAO.getUsuario(
					 new Integer(scanner.skip("id:").nextLine().toString().trim())));
		
			 ZonaDAO zonaDAO = new ZonaDAO();
			 jefeVenta.setZona(zonaDAO.getZona(new Integer(scanner.skip("idZona:").nextLine().toString().trim())));
			 			 
			 jefeVentas.add(jefeVenta); 
		}
		scanner.close();
		//guardar demas datos del vendedor
		List<Usuario> usuarios;
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarios = usuarioDAO.getUsuarios();
		
		for(JefeVenta jefeVenta: jefeVentas)
		{
			for(Usuario usuario: usuarios)
			{
				if(jefeVenta.getId().equals(usuario.getId()))
				{
					jefeVenta.setCedula(usuario.getCedula());
					jefeVenta.setContrasena(usuario.getContrasena());
					jefeVenta.setNombre(usuario.getNombre());
					jefeVenta.setUsername(usuario.getUsername());
					break;
				}
			}
		}
	
		return jefeVentas;
	}
	@Override
	public void guardar(JefeVenta jefeVenta) throws IOException {
		// TODO Auto-generated method stub
		UsuarioDAO usuarioDAO = new UsuarioDAO(); 
		List<Usuario> usuarios = usuarioDAO.getUsuarios();
		List<JefeVenta> jefeVentas = getJefeVentas();
		Zona zona;
		//buscar codigo el ultimo codigo Asignado 
		if(jefeVenta.getId() == null )
		{
			int i = 0;
			for(Usuario usuario1 : usuarios)
			{
				if(usuario1.getId()> i )
				{
					i = usuario1.getId();
				}
			}
			Usuario usuario = new Usuario(null,jefeVenta.getUsername(), jefeVenta.getCedula(), jefeVenta.getNombre(),
					jefeVenta.getContrasena());
			
			usuarioDAO.guardar(usuario);
			
			zona = jefeVenta.getZona();
			
			jefeVenta = getJefeVenta(usuarioDAO.getUsuario(i+1));
			jefeVenta.setZona(zona);
			jefeVentas.add(jefeVenta);
		}
		else
		{
			for(JefeVenta jefeVenta1 :jefeVentas)
			{
				if(jefeVenta1.getId().equals(jefeVenta.getId()))
				{ 
					Usuario usuario = new Usuario();
					usuario.setId(jefeVenta.getId());
					usuario.setUsername(jefeVenta.getUsername());
					usuario.setCedula(jefeVenta.getCedula());
					usuario.setNombre(jefeVenta.getNombre());
					usuario.setContrasena(jefeVenta.getContrasena());
					new UsuarioDAO().guardar(usuario);
					
					jefeVenta1.setZona(jefeVenta.getZona());
				}
			}
		}
		guardarTodo(jefeVentas);
	}

	private JefeVenta getJefeVenta(Usuario usuario) throws FileNotFoundException{
		JefeVenta jefeVenta = new JefeVenta();
		jefeVenta.setId(usuario.getId());
		jefeVenta.setCedula(usuario.getCedula());
		jefeVenta.setContrasena(usuario.getContrasena());
		jefeVenta.setNombre(usuario.getNombre());
		jefeVenta.setUsername(usuario.getUsername());
		
		return jefeVenta; 
	}
	
	@Override
	public void eliminar(JefeVenta jefeVenta) throws IOException {
		// TODO Auto-generated method stub
		List<JefeVenta> jefeVentas = getJefeVentas();
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		for(JefeVenta jefeVenta1 :jefeVentas)
		{
			if(jefeVenta1.getId().equals(jefeVenta.getId()))
			{
				jefeVentas.remove(jefeVenta1);
				break;
			}
		}
		usuarioDAO.eliminar(jefeVenta);
		guardarTodo(jefeVentas);
	}

	@Override
	public JefeVenta getJefeVenta(Integer id) throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<JefeVenta> jefeVentas = getJefeVentas();
		for(JefeVenta jefeVenta: jefeVentas)
		{
			if(jefeVenta.getId().equals(id))
			{
				return jefeVenta;
			}
		}
		return null;
	}
	
	public JefeVenta getJefeVenta(String username) throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<JefeVenta> jefeVentas = getJefeVentas();
		for(JefeVenta jefeVenta: jefeVentas)
		{
			if(jefeVenta.getUsername().equals(username))
			{
				return jefeVenta;
			}
		}
		return null;
	}
	
	public JefeVenta getJefeVentaCedula(String cedula) throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<JefeVenta> jefeVentas = getJefeVentas();
		for(JefeVenta jefeVenta: jefeVentas)
		{
			if(jefeVenta.getCedula().equals(cedula))
			{
				return jefeVenta;
			}
		}
		return null;
	}
	
	
	
	public void guardarTodo(List<JefeVenta> jefeVentas) throws IOException
	{
		FileWriter fw = new FileWriter(ruta);
		for(JefeVenta jefeVenta : jefeVentas)
		{
			fw.append("id:"+jefeVenta.getId().toString()+"\n");
			fw.append("idZona:"+(jefeVenta.getZona() == null 
					?"  ": jefeVenta.getZona().getId().toString())+"\n");
		}
		fw.close();
	}

	public JefeVentaDAO(String ruta) {
		super();
		this.ruta = ruta;
	}

	public JefeVentaDAO( ) {
		super();  
	}
	

} 
/*Estructura
 id:0
idVisitas:,1,
idZona:1
* */

