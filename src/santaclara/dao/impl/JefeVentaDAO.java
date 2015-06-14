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

public class JefeVentaDAO extends GenericoDAO implements IJefeVentaDAO{

	private String ruta = "archivos/jefeVentas.txt";
	
	@Override
	public List<JefeVenta> getJefeVentas() throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<JefeVenta> jefeVentas = new ArrayList<JefeVenta>();
		File file = new File(ruta);
 		Scanner scaner = new Scanner(file);
		while(scaner.hasNext())
		{
			 JefeVenta jefeVenta = new JefeVenta();
			 jefeVenta.setId(new Integer(scaner.skip("id:").nextLine().trim()));
			 ZonaDAO zonaDAO = new ZonaDAO();
			 jefeVenta.setZona(zonaDAO.getZona(new Integer(scaner.skip("idZona:").nextLine().trim())));
			 
/*			 Scanner sc = new Scanner(scaner.skip("idVisitas:").nextLine()).useDelimiter(",");
			 
			 if (sc.hasNext())
			 {
				 List<Visita> visitas = new ArrayList<Visita>();
				 while(sc.hasNext())
				 {
					 Visita visita = new Visita();
					 visita.setId(sc.nextInt());
					//guardar demas datos de la rutas
					 VisitaDAO visitaDAO = new VisitaDAO();
					 visita = visitaDAO.getVisita(visita.getId());
					 visitas.add(visita);
				 }
				 jefeVenta.setVisita(visitas);
			 }
			 else
			 {
				jefeVenta.setVisita(null);
			 }
			 sc.close();
*/
			 
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
/* 		List<Zona> zonas;
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
*/
		return jefeVentas;
	}
	@Override
	public void guardar(JefeVenta jefeVenta) throws IOException {
		// TODO Auto-generated method stub
		List<JefeVenta> jefeVentas = getJefeVentas();
		//buscar codigo el ultimo codigo Asignado 
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List<Usuario> usuarios = usuarioDAO.getUsuarios();
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
			/*	List<Visita> visitas = jefeVenta.getVisitas();
				String linea = new String(",");
				for(Visita visita : visitas ) {
					linea =  linea+visita.getId()+",";
				}
				fw.append("idVisitas:"+linea+"\n");
			*/
			fw.append("idZona:"+jefeVenta.getZona().getId().toString()+"\n");
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

