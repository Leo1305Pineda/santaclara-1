package santaclara.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import santaclara.modelo.Usuario;

public interface IUsuarioDAO extends IGenericoDAO{
	public List<Usuario>  getUsuarios() throws FileNotFoundException;

	public void	guardar(Usuario usuario) throws IOException;
	
	public void eliminar(Usuario usuario) throws   IOException;
	
	public Usuario getUsuario(String cedula) throws FileNotFoundException;

}
