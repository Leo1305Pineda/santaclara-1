package santaclara.Servicio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import santaclara.modelo.Usuario;
import santaclara.dao.impl.UsuarioDAO;

public class ServicioUsuario {
	
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private List<Usuario> usuarios = new ArrayList<Usuario>();
		
	public List<Usuario> getUsuarios() throws NumberFormatException, IOException{
		// TODO Auto-generated method stub
		
		return usuarioDAO.getUsuarios();
	}

	public UsuarioDAO getUsuarioDAO() {
		 
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public Usuario buscar(Integer id) throws IOException {
		// TODO Auto-generated method stub
		return usuarioDAO.getUsuario(id);
	}

	public String guardar(Usuario usuario) throws IOException {
		// TODO Auto-generated method stub
		
		usuarios = usuarioDAO.getUsuarios();
		
		for(Usuario Usuario1 :usuarios)
		{
			if(Usuario1.getCedula().equals(usuario.getCedula())&&
					Usuario1.getUsername().equals(usuario.getUsername())&&
					Usuario1.getId().equals(usuario.getId())) 
			{
				if(Usuario1.getNombre().equals(usuario.getNombre())&&
						Usuario1.getContrasena().equals(usuario.getContrasena()))
				return "Usuario Existente";
				break;//rompe el for para modificar
			}
		}
		
		usuarioDAO.guardar(usuario);
		return "Operacion Exitosa";
				
		
	}
	
	public Usuario getUsuario(Integer id) throws IOException{
		return usuarioDAO.getUsuario(id);
	}
	
	public void eliminar(Usuario usuario) throws IOException{
		usuarioDAO.eliminar(usuario);
	}
	
}
