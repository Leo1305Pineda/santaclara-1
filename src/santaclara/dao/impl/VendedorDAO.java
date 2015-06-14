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
	
	@Override
	public List<Vendedor> getVendedores() throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<Vendedor> vendedores = new ArrayList<Vendedor>();
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List<Usuario> usuarios = usuarioDAO.getUsuarios();
		
		File file = new File(ruta);
 		Scanner scaner = new Scanner(file);
		while(scaner.hasNext())
		{
			 Vendedor vendedor = new Vendedor();
			 vendedor.setId(new Integer(scaner.skip("id:").nextLine().trim()));
			 
			 Scanner sc = new Scanner(scaner.skip("idRutas:").nextLine()).useDelimiter(",");
			 
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
		scaner.close();
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
			vendedor.setId(i+1);
			vendedores.add(vendedor);
		}
		else
		{
			for(Vendedor vendedor1 :vendedores)
			{
				if(vendedor1.getId().equals(vendedor.getId()))
				{ 
					vendedor1.setUsername(vendedor.getUsername());
					vendedor1.setCedula(vendedor.getCedula());
					vendedor1.setNombre(vendedor.getNombre());
					vendedor1.setContrasena(vendedor.getContrasena());
					vendedor1.setRutas(vendedor.getRutas());
				}
			}
		}
		guardarTodo(vendedores);
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
		///guardar Todo 
		usuarioDAO.eliminar(vendedor);
		guardarTodo(vendedores);
	}

	@Override
	public Vendedor getVendedor(Integer id) throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<Vendedor> vendedores = getVendedores();
		for(Vendedor vendedor: vendedores)
		{
			if(vendedor.getCedula().equals(id))
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
			if (vendedor.getRutas()==null)
			{
			linea = "";	
			}
			else
			{
				for(Ruta ruta : rutas ) 
				{
					linea =  linea+ruta.getId()+",";
				}
			}
			fw.append("idRutas:"+linea+"\n");
		}
		fw.close();
	}
	/*Estructura 
	 * id:1
	 * idRutas:,1,2,
	 * no se acepta espacio despues de lo dos ":" punto ejenplo... id: 1
	 * */
} 


