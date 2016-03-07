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
import java.util.Scanner;

import santaclara.dao.IVendedorDAO;
import santaclara.modelo.Vendedor;
import santaclara.modelo.Ruta;

public class VendedorDAO extends GenericoDAO implements IVendedorDAO{

	public VendedorDAO(){
		super();
		// TODO Auto-generated constructor stub
		try {
			activarConexionBaseDato();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("resource")
	@Override
	public List<Vendedor> getVendedores() throws Exception {
		// TODO Auto-generated method stub
		List<Vendedor> vendedores = new ArrayList<Vendedor>();
		
		ResultSet rSet = getConexion().getSelect(
				" SELECT u .* , v.id, v.idrutas "
				+ " FROM vendedores v , usuarios u "
				+ " WHERE v.id = u.id"
				+ " Order by v.id;"); 
		
		if(rSet==null || rSet.getFetchSize()!=0) return null;
		rSet.next();
		while(rSet.next())
		{
			Scanner sc = new Scanner(rSet.getString("idrutas")).useDelimiter("-");
			 List<Ruta> rutas = new ArrayList<Ruta>();
			 if (sc.hasNext())
			 {
				
				 while(sc.hasNext())
				 {
					 Ruta ruta = new Ruta();
					 ruta.setId(sc.nextInt());
					//guardar demas datos de la rutas
					 RutaDAO rutaDAO = new RutaDAO();
					 ruta = rutaDAO.getRuta(ruta.getId());
					 rutas.add(ruta);
				 }
			 }
			 
			vendedores.add(
					new Vendedor(rSet.getInt("id"),
							rSet.getString("username"),
							rSet.getString("cedula"),
							rSet.getString("nombre"),
							rSet.getString("contrasena"), rutas));
		} 
		return vendedores;
	}
	
	public String getRutasStr(List<Ruta> rutas){
		String lineaRutas = new String("-");
		
		if (rutas==null) lineaRutas = "";	
		else
		{
			for(Ruta ruta : rutas) 
			{
				lineaRutas =  lineaRutas+ruta.getId().toString()+"-";
			}
		}
			return lineaRutas;
	}
	
	@Override
	public void guardar(Vendedor vendedor) throws Exception {
		// TODO Auto-generated method stub
		if (vendedor.getId()==null)
		{
			String lineaRutas =	getRutasStr(vendedor.getRutas());
			
			getConexion().ejecutar( 
					"BEGIN;"
					+ "    "
					+ "INSERT INTO usuarios(username,cedula,nombre,contrasena) "
					+"VALUES ("
					+"'" +vendedor.getUsername()	  		+ "', "
					+"'" +vendedor.getCedula()	  			+ "', "
					+"'" +vendedor.getNombre()     			+ "', "
					+"'" +vendedor.getContrasena() 			+ "'  "
					+ "); "
					+ "           "
					+"INSERT INTO vendedores(id, idrutas) "
					+"VALUES ("
					+ " (select max(id) from usuarios) "	+", "
					+ "' " + lineaRutas +"'  "
					+ ");"
					+ "       "
					+ "COMMIT;");
		}
		else
		{
			String lineaRutas =	getRutasStr(vendedor.getRutas());
			
			new UsuarioDAO().guardar(vendedor);
			getConexion().ejecutar(
					" UPDATE  usuarios  SET "
					+" username   ='" +vendedor.getUsername()	+ "', " 
					+" cedula     ='" +vendedor.getCedula()		+ "', "
					+" nombre     ='" +vendedor.getNombre()		+ "', "
					+" contrasena ='" +vendedor.getContrasena() + "'  "
					+" WHERE id   = " +vendedor.getId()			+ ";  "
					+"              "							
					+" UPDATE vendedores SET "
					+" idrutas     = '" +lineaRutas              + "' "
					+" WHERE id   = " +vendedor.getId()			 + ";  "
					+ " ");
		}
	}

	@Override
	public void eliminar(Vendedor vendedor) throws Exception {
		// TODO Auto-generated method stub
		if(vendedor!=null) getConexion().ejecutar(
				" DELETE FROM vendedores "
				+" WHERE id = " + vendedor.getId() +" "
				+" ;");		
		new UsuarioDAO().eliminar(vendedor);
	}

	@SuppressWarnings("resource")
	@Override
	public Vendedor getVendedor(Integer id) throws Exception { 
		// TODO Auto-generated method stub
	try {
		
		ResultSet rSet = getConexion().getSelect(
				" SELECT u .* , v.id, v.idrutas "
				+ " FROM vendedores v , usuarios u "
				+ " WHERE v.id = u.id AND "
				+ "v.id = " +id
				+ " ;"); 
		
		
		if(rSet==null) return null;
		if(rSet.getFetchSize()==0)return null;
		
		System.out.println(rSet.getFetchSize());
		rSet.next();
		System.out.println(rSet.getString("idrutas"));
		Scanner sc = new Scanner(rSet.getString("idrutas")).useDelimiter("-");
		 List<Ruta> rutas = new ArrayList<Ruta>();
		 if (sc.hasNext())
		 {
			 while(sc.hasNext())
			 {
				 Ruta ruta = new Ruta();
				 ruta.setId(sc.nextInt());
				 RutaDAO rutaDAO = new RutaDAO();
				 ruta = rutaDAO.getRuta(ruta.getId());
				 rutas.add(ruta);
			 }
		 }
	
			return new Vendedor(rSet.getInt("id"),
						rSet.getString("username"),
						rSet.getString("cedula"),
						rSet.getString("nombres"),
						rSet.getString("contrasena"), rutas);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
		 return null;
	}
	
	public Boolean getVendedor(Vendedor vendedor) throws Exception {
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

	/*
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
	public List<Vendedor> getVendedores() throws Exception {
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
	public void guardar(Vendedor vendedor) throws Exception {
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
	
	private Vendedor getVendedor(Usuario usuario) throws Exception{
				Vendedor vendedor = new Vendedor();
				vendedor.setId(usuario.getId());
				vendedor.setCedula(usuario.getCedula());
				vendedor.setContrasena(usuario.getContrasena());
				vendedor.setNombre(usuario.getNombre());
				vendedor.setUsername(usuario.getUsername());
				
				return vendedor; 
	}
	@Override
	public void eliminar(Vendedor vendedor) throws Exception {
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
	public Vendedor getVendedor(Integer id) throws Exception {
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
	
	public void guardarTodo(List<Vendedor> vendedores) throws Exception
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
	
	public Boolean getVendedor(Vendedor vendedor) throws Exception {
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

	 * */
	
	/*Estructura 
	 * id:1
	 * idRutas:,1,2,
	 * no se acepta espacio despues de lo dos ":" punto ejenplo... id: 1
	 * */
} 


