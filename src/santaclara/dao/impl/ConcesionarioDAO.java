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

import santaclara.dao.IConcesionarioDAO;
import santaclara.dbPostgresql.modelo.PostgreSql;
import santaclara.modelo.Concesionario;

public class ConcesionarioDAO extends GenericoDAO implements IConcesionarioDAO{
	
	@Override
	public List<Concesionario> getConcecionarios() throws Exception {
		// TODO Auto-generated method stub
		List<Concesionario> concesionarios = new ArrayList<Concesionario>();
		
		ResultSet rSet = new PostgreSql().getSelect(
				"SELECT id, idcamion, idruta FROM concesionarios Order by id;"); 
		
		if(rSet==null) return null;
		
			while(rSet.next())concesionarios.add(
					new Concesionario(new UsuarioDAO().getUsuario(rSet.getInt("id")),
							new CamionDAO().getCamion(rSet.getInt("idcamion")), 
							new RutaDAO().getRuta(rSet.getInt("idruta")))); 
		return concesionarios;
	}
	
	@Override
	public void guardar(Concesionario concesionario) throws Exception {
		// TODO Auto-generated method stub
		if (concesionario.getId()==null){
			new PostgreSql().ejecutar( 
					"BEGIN;"
							+ "    "
							+ "INSERT INTO usuarios(username,cedula,nombre,contrasena) "
							+"VALUES ("
							+"'" +concesionario.getUsername()	  		+ "', "
							+"'" +concesionario.getCedula()	  			+ "', "
							+"'" +concesionario.getNombre()     		+ "', "
							+"'" +concesionario.getContrasena() 		+ "'  "
							+ "); "
							+ "           "
							+"INSERT INTO vendedores(id, idrutas) "
							+"VALUES ("
							+ " (select max(id) from usuarios) "		+", "
							+ " " + concesionario.getCamion().getId() 	+", "
							+ " " + concesionario.getRuta().getId() 	+"  "
							+ ");"
							+ "       "
							+ "COMMIT;");	
		}
		else{
			new PostgreSql().ejecutar(
					" UPDATE  usuarios  SET "
							+" username   ='" +concesionario.getUsername()		+ "', " 
							+" cedula     ='" +concesionario.getCedula()		+ "', "
							+" nombre     ='" +concesionario.getNombre()		+ "', "
							+" contrasena ='" +concesionario.getContrasena() 	+ "'  "
							+" WHERE id   = " +concesionario.getId()			+ ";  "
							+"              "							
							+" UPDATE vendedores SET "
							+" idcamion   = " +concesionario.getCamion().getId()+ " "
							+" idruta     = " +concesionario.getRuta().getId()  + " "
							+" WHERE id   = " +concesionario.getId()			 + ";  "
							+ " ");
		}
	}

	@Override
	public void eliminar(Concesionario concesionario) throws Exception {
		// TODO Auto-generated method stub
		if(concesionario!=null) new PostgreSql().ejecutar(
								"DELETE FROM concesionarios "
								+"WHERE id = "+concesionario.getId() +" ;");
	}

	@Override
	public Concesionario getConcesionario(Integer id) throws Exception {
		// TODO Auto-generated method stub
		
		ResultSet rSet = new PostgreSql().getSelect(
				"SELECT id, idcamion, idruta FROM concesionarios "
				+ "WHERE id ="+id+" ;");

		if(rSet == null) return null;

		rSet.next();
		return new Concesionario(new UsuarioDAO().getUsuario(rSet.getInt("id")),
				new CamionDAO().getCamion(rSet.getInt("idcamion")), 
				new RutaDAO().getRuta(rSet.getInt("idruta")));
		
	}

	public Concesionario getConcecionariosCedula(String cedula) {
		// TODO Auto-generated method stub
		ResultSet rSet = new PostgreSql().getSelect(
				"SELECT id, idcamion, idruta FROM concesionarios "
				+ "WHERE id ="+id+" ;");

		if(rSet == null) return null;

		rSet.next();
		return new Concesionario(new UsuarioDAO().getUsuario(rSet.getInt("id")),
				new CamionDAO().getCamion(rSet.getInt("idcamion")), 
				new RutaDAO().getRuta(rSet.getInt("idruta")));
	}

/*
 	private String ruta = "archivos/concesionarios.txt";
	
	public ConcesionarioDAO(String ruta) {
		super();
		this.ruta = ruta;
	}

	public ConcesionarioDAO( ) {
		super();  
	}
	
	public void guardarTodo(List<Concesionario> concesionarios) throws Exception
	{
		FileWriter fw = new FileWriter(ruta);
		for(Concesionario concesionario : concesionarios)
		{
			fw.append("id:"+concesionario.getId().toString()+"\n");
			fw.append("camion:"+(concesionario.getCamion() == null
					? "  ":concesionario.getCamion().getId())+"\n");
			fw.append("ruta:"+(concesionario.getRuta() == null
					? "  ":concesionario.getRuta().getId())+"\n");

		}
		fw.close();
	}

	@Override
	public List<Concesionario> getConcecionarios() throws Exception {
		// TODO Auto-generated method stub
		List<Concesionario> concesionarios = new ArrayList<Concesionario>();
		File file = new File(ruta);
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List<Usuario> usuarios;
 		Scanner scanner = new Scanner(file);
		while(scanner.hasNext())
		{
			 Concesionario concesionario = new Concesionario();
			 concesionario.setId(new Integer(scanner.skip("id:").nextLine().trim()));
			 
			 CamionDAO camionDAO = new CamionDAO();
			 		concesionario.setCamion(
			 			camionDAO.getCamion(
			 					new Integer(scanner.skip("camion:").nextLine().toString().trim())));
			 
			 RutaDAO rutaDAO = new RutaDAO();
			 concesionario.setRuta(
					 rutaDAO.getRuta(
							 new Integer(scanner.skip("ruta:").nextLine().trim())));
			 
			 concesionarios.add(concesionario); 
		}
		scanner.close();
		//guardar demas datos del vendedor 
		usuarios = usuarioDAO.getUsuarios();
		
		for(Concesionario concesionario: concesionarios)
		{
			for(Usuario usuario: usuarios)
			{
				if(concesionario.getId().equals(usuario.getId()))
				{
					concesionario.setCedula(usuario.getCedula());
					concesionario.setContrasena(usuario.getContrasena());
					concesionario.setNombre(usuario.getNombre());
					concesionario.setUsername(usuario.getUsername());
					break;
				}
			}
		}
		return concesionarios;
	}

	@Override
	public void guardar(Concesionario concesionario) throws Exception {
		// TODO Auto-generated method stub
		List<Concesionario> concesionarios = getConcecionarios();
		//buscar codigo el ultimo codigo Asignado 
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List<Usuario> usuarios = usuarioDAO.getUsuarios();
		Ruta ruta;
		Camion camion;
		if(concesionario.getId() == null )
		{
			int i = 0;
			for(Usuario usuario1 : usuarios)
			{
				if(usuario1.getId()> i )
				{
					i = usuario1.getId();
				}
			}
			Usuario usuario = new Usuario(null,concesionario.getUsername(),
												concesionario.getCedula(), 
												concesionario.getNombre(),
												concesionario.getContrasena());
			
			usuarioDAO.guardar(usuario);
			
			ruta = concesionario.getRuta();
			camion = concesionario.getCamion(); 
			
			concesionario = getConcesionario(usuarioDAO.getUsuario(i+1));
			concesionario.setRuta(ruta);
			concesionario.setCamion(camion);
			concesionarios.add(concesionario);
		}
		else
		{
			for(Concesionario concesionario1 : concesionarios)
			{
				if(concesionario1.getId().equals(concesionario.getId()))
				{ 
					Usuario usuario = new Usuario();
					usuario.setId(concesionario.getId());
					usuario.setUsername(concesionario.getUsername());
					usuario.setCedula(concesionario.getCedula());
					usuario.setNombre(concesionario.getNombre());
					usuario.setContrasena(concesionario.getContrasena());
					new UsuarioDAO().guardar(usuario);
					
					concesionario1.setCamion(concesionario.getCamion());
					concesionario1.setRuta(concesionario.getRuta());
				}
			}
		}
		guardarTodo(concesionarios);
	}

	private Concesionario getConcesionario(Usuario usuario) throws FileNotFoundException{
		Concesionario concesionario = new Concesionario();
		concesionario.setId(usuario.getId());
		concesionario.setCedula(usuario.getCedula());
		concesionario.setContrasena(usuario.getContrasena());
		concesionario.setNombre(usuario.getNombre());
		concesionario.setUsername(usuario.getUsername());
		
		return concesionario; 
}
	@Override
	public void eliminar(Concesionario concesionario) throws Exception {
		List<Concesionario> concecionarios =getConcecionarios();
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		for(Concesionario concesionario1 :concecionarios)
		{
			if(concesionario1.getId().equals(concesionario.getId()))
			{
				concecionarios.remove(concesionario1);
				break;
			}
		}
		usuarioDAO.eliminar(concesionario);
		guardarTodo(concecionarios);
		
	}

	@Override
	public Concesionario getConcesionario(Integer id)
			throws Exception {
		// TODO Auto-generated method stub
		List<Concesionario> concesionarios = getConcecionarios();
		for(Concesionario concecionario : concesionarios)
		{
			if(concecionario.getId().equals(id))
			{
				return concecionario;
			}
		}
		return null;
	}

 * */	
	
} 


