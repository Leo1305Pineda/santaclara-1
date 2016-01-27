package santaclara.dao.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import santaclara.dao.IVendedorDAO;
import santaclara.modelo.Ruta;
import santaclara.modelo.Usuario;
import santaclara.modelo.Vendedor;

public class VendedorDAO extends GenericoDAO implements IVendedorDAO{

	private String ruta = "archivos/vendedores.txt";
	
	public VendedorDAO(String ruta) {
		super();
		this.ruta = ruta;
	}

	public VendedorDAO( ) {
		super();  
	}
	
	@SuppressWarnings("resource")
	@Override
	public List<Vendedor> getVendedores() throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<Vendedor> vendedores = new ArrayList<Vendedor>();
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List<Usuario> usuarios = usuarioDAO.getUsuarios();
		
		File file = new File(ruta);
 		Scanner scanner = new Scanner(file);
		while(scanner.hasNext())
		{
			 Vendedor vendedor = new Vendedor();
			 vendedor.setId(new Integer(scanner.skip("id:").nextLine().trim()));
			 
			 Scanner sc = new Scanner(scanner.skip("idRutas:").nextLine()).useDelimiter(",");
			 
			 if (sc.hasNext())
			 {
				 List<Ruta> rutas = new ArrayList<Ruta>();
				 while(sc.hasNext())
				 {
					 Ruta ruta = new Ruta();
					 ruta.setId(sc.nextInt());
					//guardar demas datos de la rutas
					 RutaDAO rutaDAO = new RutaDAO();
					 ruta = rutaDAO.getRuta(ruta.getId());
					 rutas.add(ruta);
				 }
				 vendedor.setRutas(rutas);
			 }
			 else
			 {
				 vendedor.setRutas(null);
			 }
			 sc.close();
			 
		vendedores.add(vendedor);
		}
		scanner.close();
		//guardar demas datos del vendedor 
		
		for(Vendedor vendedor: vendedores)
		{	
			for(Usuario usuario1: usuarios)
			{
				if(vendedor.getId().equals(usuario1.getId()))
				{
					vendedor.setCedula(usuario1.getCedula());
					vendedor.setContrasena(usuario1.getContrasena());
					vendedor.setNombre(usuario1.getNombre());
					vendedor.setUsername(usuario1.getUsername());
					break;
				}
			}
		}
	return vendedores;
	}
	@Override
	public void guardar(Vendedor vendedor) throws IOException {
		// TODO Auto-generated method stub
		UsuarioDAO usuarioDAO = new UsuarioDAO(); 
		List<Usuario> usuarios = usuarioDAO.getUsuarios();
		List<Vendedor> vendedores = getVendedores();
		List<Ruta> rutas;
		//buscar codigo el ultimo codigo Asignado 
		if(vendedor.getId() == null )
		{ 
			int i = 0;
			for(Usuario usuario1 : usuarios)
			{
				if(usuario1.getId()> i )
				{
					i = usuario1.getId();
				}
			}
			usuarioDAO.guardar(vendedor);
			rutas = vendedor.getRutas();
			vendedor = getVendedor(usuarioDAO.getUsuario(i+1));
			vendedor.setRutas(rutas);
			vendedores.add(vendedor);
		}
		else
		{
			for(Vendedor vendedor1 :vendedores)
			{
				if(vendedor1.getId().equals(vendedor.getId()))
				{
					Usuario usuario = new Usuario();
					usuario.setId(vendedor.getId());
					usuario.setUsername(vendedor.getUsername());
					usuario.setCedula(vendedor.getCedula());
					usuario.setNombre(vendedor.getNombre());
					usuario.setContrasena(vendedor.getContrasena());
					new UsuarioDAO().guardar(usuario);
					
					vendedor1.setRutas(vendedor.getRutas());
				}
			}
		}
		guardarTodo(vendedores);
	}
	
	private Vendedor getVendedor(Usuario usuario) throws FileNotFoundException{
				Vendedor vendedor = new Vendedor();
				vendedor.setId(usuario.getId());
				vendedor.setCedula(usuario.getCedula());
				vendedor.setContrasena(usuario.getContrasena());
				vendedor.setNombre(usuario.getNombre());
				vendedor.setUsername(usuario.getUsername());
				
				return vendedor; 
	}
	@Override
	public void eliminar(Vendedor vendedor) throws IOException {
		// TODO Auto-generated method stub
		List<Vendedor> vendedores = getVendedores();
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		for(Vendedor vendedor1 :vendedores)
		{
			if(vendedor1.getId().equals(vendedor.getId()))
			{
				vendedores.remove(vendedor1);
				break;
			}
		}
		usuarioDAO.eliminar(vendedor);
		guardarTodo(vendedores);
	}

	@Override
	public Vendedor getVendedor(Integer id) throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<Vendedor> vendedores = getVendedores();
		for(Vendedor vendedor: vendedores)
		{
			if(vendedor.getId().equals(id))
			{
				return vendedor;
			}
		}
		return null;
	}
	
	public void guardarTodo(List<Vendedor> vendedores) throws IOException
	{
		
		FileWriter fw = new FileWriter(ruta);
		for(Vendedor vendedor : vendedores)
		{
			fw.append("id:"+vendedor.getId().toString()+"\n");
			List<Ruta> rutas = vendedor.getRutas();
			String linea = new String(",");
		
			if (rutas==null) linea = "";	
			else
			{
				for(Ruta ruta : rutas ) 
				{
					linea =  linea+ruta.getId().toString()+",";
				}
			}
			fw.append("idRutas:"+linea+"\n");
		}
		fw.close();
	}
	
	public Boolean getVendedor(Vendedor vendedor) throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<Vendedor> vendedores = getVendedores();
		for(Vendedor vendedor1 :vendedores)
		{
				if(vendedor1.getUsername().equals(vendedor.getUsername())&&
						!vendedor1.getId().equals(vendedor.getId()))
				{ 
					return true;
				}
		}
		return false;
    }
	/*Estructura 
	 * id:1
	 * idRutas:,1,2,
	 * no se acepta espacio despues de lo dos ":" punto ejenplo... id: 1
	 * */
} 


