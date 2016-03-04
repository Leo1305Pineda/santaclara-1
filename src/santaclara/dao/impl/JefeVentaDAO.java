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

import santaclara.dao.IJefeVentaDAO;
import santaclara.dbPostgresql.modelo.PostgreSql;
import santaclara.modelo.JefeVenta;
import santaclara.modelo.Usuario;

public class JefeVentaDAO extends GenericoDAO implements IJefeVentaDAO{
	 
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private ResultSet rSet;
	private List<JefeVenta> jefeVentas = new ArrayList<JefeVenta>();
	private JefeVenta jefeVenta;

	@Override
	public List<JefeVenta> getJefeVentas() throws Exception {
		// TODO Auto-generated method stub
		
		ResultSet rSet = new PostgreSql().getSelect("SELECT j.id,u.username,u.cedula,u.nombre,u.contrasena,j.idzona,z.descripcion "
				+ "FROM jefeventas j,usuario u,zona z "
				+ "WHERE j.id = u.id AND j.idzona = z.id "); 
		
		if(rSet!=null)
		{
			while(rSet.next())
			{
				jefeVenta = new JefeVenta(rSet.getInt(1), rSet.getString(2), 
						rSet.getString(3), rSet.getString(4), rSet.getString(5), new ZonaDAO().getZona(rSet.getInt(2)));
				 			 
				jefeVentas.add(jefeVenta);
			}
		}
		return jefeVentas;
	}
	
	@Override
	public void guardar(JefeVenta jefeVenta) throws Exception {
		// TODO Auto-generated method stub
		
		if (jefeVenta.getId()==null){
			
			new UsuarioDAO().guardar(new Usuario(null,
					jefeVenta.getUsername(), jefeVenta.getCedula(),
					jefeVenta.getNombre(), jefeVenta.getContrasena()));
			
			new PostgreSql().ejecutar(
					"BEGIN;"
					+ "INSERT INTO usuarios(username,cedula,nombre,contrasena) "
					+"VALUES ("
					+"'" +jefeVenta.getUsername()	  		+ "', "
					+"'" +jefeVenta.getCedula()	  			+ "', "
					+"'" +jefeVenta.getNombre()     		+ "', "
					+"'" +jefeVenta.getContrasena() 		+ "'); "
					+ "           "
					+"INSERT INTO jefeventas(id, idzona) "
					+"VALUES ("
					+ " (select max(id) from usuarios) "	+", "
					+ " " +jefeVenta.getZona().getId()		+"  "	
					+");"
					+ "COMMIT;"
					+ "");	
		} 
		else{
			usuarioDAO.guardar(jefeVenta);
			new PostgreSql().ejecutar(
					"UPDATE  usuarios  SET "
					+"username   ='" +jefeVenta.getUsername()	+ "'," 
					+"cedula     ='" +jefeVenta.getCedula()		+ "',"
					+"nombre     ='" +jefeVenta.getNombre()		+ "',"
					+"contrasena ='" +jefeVenta.getContrasena() + "' "
					+"WHERE id   = " +jefeVenta.getId()			+ "; "							
					+"UPDATE jefeventas SET "
					+"idzona     = " +jefeVenta.getZona().getId()
					+"WHERE id   = " +jefeVenta.getId()			+ ";");
		}
	}
	
	@Override
	public void eliminar(JefeVenta jefeVenta) throws Exception {
		// TODO Auto-generated method stub
		if(jefeVenta!=null) new PostgreSql().ejecutar(
				" DELETE FROM jefeventas "
				+ "WHERE id = " + jefeVenta.getId() +" "
				+";");		
		new UsuarioDAO().eliminar(jefeVenta);
	}

	@Override
	public JefeVenta getJefeVenta(Integer id) throws Exception {
		// TODO Auto-generated method stub
		rSet = new PostgreSql().getSelect("SELECT j.id,u.username,u.cedula,u.nombre,u.contrasena,j.idzona,z.descripcion "
				+ "FROM jefeventas j,usuario u,zona z "
				+ "WHERE j.id = u.id AND j.idzona = z.id  AND where id = ".concat(id.toString()).concat(";")); 
		
		if(rSet!=null) return null;
		
		rSet.next();
		
		return jefeVenta = new JefeVenta(rSet.getInt(1), rSet.getString(2), 
				rSet.getString(3), rSet.getString(4), rSet.getString(5), new ZonaDAO().getZona(rSet.getInt(2)));
	}
	
	public JefeVenta getJefeVenta(String username) throws Exception {
		// TODO Auto-generated method stub
		rSet = new PostgreSql().getSelect("SELECT j.id,u.username,u.cedula,u.nombre,u.contrasena,j.idzona,z.descripcion "
				+ "FROM jefeventas j,usuario u,zona z "
				+ "WHERE j.id = u.id AND j.idzona = z.id  AND where username = '".concat(username).concat("';")); 
		
		if(rSet!=null) return null;
		
		rSet.next();
		
		return jefeVenta = new JefeVenta(rSet.getInt(1), rSet.getString(2), 
				rSet.getString(3), rSet.getString(4), rSet.getString(5), new ZonaDAO().getZona(rSet.getInt(2)));
	}
	
	public JefeVenta getJefeVentaCedula(String cedula) throws Exception {
		// TODO Auto-generated method stub
		rSet = new PostgreSql().getSelect("SELECT j.id,u.username,u.cedula,u.nombre,u.contrasena,j.idzona,z.descripcion "
				+ "FROM jefeventas j,usuario u,zona z "
				+ "WHERE j.id = u.id AND j.idzona = z.id  AND where cedula = '".concat(cedula).concat("';")); 
		
		if(rSet!=null) return null;
		
		rSet.next();
		
		return jefeVenta = new JefeVenta(rSet.getInt(1), rSet.getString(2), 
				rSet.getString(3), rSet.getString(4), rSet.getString(5), new ZonaDAO().getZona(rSet.getInt(2)));
	}
	
	public JefeVentaDAO() {
		super();
		// TODO Auto-generated constructor stub
	}	
}
	/**** Manejo con txt
	 
	 	
	private String ruta = "archivos/jefeVentas.txt";

	@Override
	public List<JefeVenta> getJefeVentas() throws Exception {
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
	public void guardar(JefeVenta jefeVenta) throws Exception {
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

	
	@Override
	public JefeVenta getJefeVenta(Integer id) throws Exception {
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
	
	public JefeVenta getJefeVenta(String username) throws Exception {
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
	
	public JefeVenta getJefeVentaCedula(String cedula) throws Exception {
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
	
	@Override
	public void eliminar(JefeVenta jefeVenta) throws Exception {
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
	
	public void eliminar(JefeVenta jefeVenta) throws Exception {
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

	private JefeVenta getJefeVenta(Usuario usuario) throws Exception{
		JefeVenta jefeVenta = new JefeVenta();
		jefeVenta.setId(usuario.getId());
		jefeVenta.setCedula(usuario.getCedula());
		jefeVenta.setContrasena(usuario.getContrasena());
		jefeVenta.setNombre(usuario.getNombre());
		jefeVenta.setUsername(usuario.getUsername());
		
		return jefeVenta; 
	}

	 
	 * /

/*Estructura
 id:0
idVisitas:,1,
idZona:1
* */

