package santaclara.dao.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import santaclara.dao.IVendedorDAO;
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
		File file = new File(ruta);
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List<Usuario> usuarios;
 		Scanner scaner = new Scanner(file);
		while(scaner.hasNext())
		{
			 Vendedor vendedor = new Vendedor();
			 vendedor.setId(new Integer(scaner.skip("id:").nextLine()));
			//pendiente por cargar la lista de rutas
			 vendedores.add(vendedor); 
		}
		scaner.close();
		//guardar demas datos del vendedor 
		usuarios = usuarioDAO.getUsuarios();
		
		for(Vendedor vendedor: vendedores)
		{
			for(Usuario usuario: usuarios)
			{
				if(vendedor.getId().equals(usuario.getId()))
				{
					vendedor.setCedula(usuario.getCedula());
					vendedor.setContrasena(usuario.getContrasena());
					vendedor.setNombre(usuario.getNombre());
					vendedor.setUsername(usuario.getUsername());
					break;
				}
			}
		}
		//Falta implementar la lista de Rutas de Rutas //scaner.skip("idRutas:").nextLine();
		return vendedores;
	}
	@Override
	public void guardar(Vendedor vendedor) throws IOException {
		// TODO Auto-generated method stub
		List<Vendedor> vendedores = getVendedores();
		//buscar codigo el ultimo codigo Asignado 
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if(vendedor.getId() == null )
		{
			int i = 0;
			for(Vendedor vendedor1 : vendedores)
			{
				if(vendedor1.getId()> i )
				{
					i = vendedor1.getId();
				}
			}
			vendedor.setId(i+1);
			vendedores.add(vendedor);
		}
		else
		{
			for(Vendedor vendedor1 :vendedores)
			{
				if(vendedor1.getId().equals(vendedor.getId()))
				{
					/// vacio 
					vendedor1.setId(vendedor.getId());
					vendedor1.setUsername(vendedor.getUsername());
					vendedor1.setCedula(vendedor.getCedula());
					vendedor1.setNombre(vendedor.getNombre());
					vendedor1.setContrasena(vendedor.getContrasena());
				}
			}
		}
		usuarioDAO.guardar(vendedor);
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
		}
		fw.close();
	}
	
} 


