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
	
	public JefeVentaDAO(String ruta) {
		super();
		this.ruta = ruta;
	}

	public JefeVentaDAO( ) {
		super();  
	}
	
	@Override
	public List<JefeVenta> getJefeVentas() throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<JefeVenta> jefeVentas = new ArrayList<JefeVenta>();
		File file = new File(ruta);
 		Scanner scaner = new Scanner(file);
		while(scaner.hasNext())
		{
			 JefeVenta jefeVenta = new JefeVenta();
			 jefeVenta.setId(new Integer(scaner.skip("id:").nextLine()));
			 jefeVentas.add(jefeVenta); 
		}
		scaner.close();
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
		//guarda demas datos de la zona
		List<Zona> zonas;
		ZonaDAO zonaDAO = new ZonaDAO();
		zonas = zonaDAO.getZonas();
		for(JefeVenta jefeVenta: jefeVentas)
		{
			for(Zona zona: zonas)
			{
				if(jefeVenta.getId().equals(zona.getJefeVenta()))
				{
					jefeVenta.setZona(zona);
					break;
				}
			}
		}
		return jefeVentas;
	}
	@Override
	public void guardar(JefeVenta jefeVenta) throws IOException {
		// TODO Auto-generated method stub
		List<JefeVenta> jefeVentas = getJefeVentas();
		//buscar codigo el ultimo codigo Asignado 
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if(jefeVenta.getId() == null )
		{
			int i = 0;
			for(JefeVenta jefeVenta1 : jefeVentas)
			{
				if(jefeVenta1.getId()> i )
				{
					i = jefeVenta1.getId();
				}
			}
			jefeVenta.setId(i+1);
			jefeVentas.add(jefeVenta);
		}
		else
		{
			for(JefeVenta jefeVenta1 :jefeVentas)
			{
				if(jefeVenta1.getId().equals(jefeVenta.getId()))
				{
					/// vacio 
					jefeVenta1.setId(jefeVenta.getId());
					jefeVenta1.setUsername(jefeVenta.getUsername());
					jefeVenta1.setCedula(jefeVenta.getCedula());
					jefeVenta1.setNombre(jefeVenta.getNombre());
					jefeVenta1.setContrasena(jefeVenta.getContrasena());
				}
			}
		}
		usuarioDAO.guardar(jefeVenta);
		guardarTodo(jefeVentas);
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
		///guardar Todo 
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
	
	public void guardarTodo(List<JefeVenta> jefeVentas) throws IOException
	{
		FileWriter fw = new FileWriter(ruta);
		for(JefeVenta jefeVenta : jefeVentas)
		{
			fw.append("id:"+jefeVenta.getId().toString()+"\n");
		}
		fw.close();
	}
	
} 
/*Estructura
 * id:0
 *idZona:1 
 * */

