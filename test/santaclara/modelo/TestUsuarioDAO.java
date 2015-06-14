package santaclara.modelo;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import santaclara.dao.impl.UsuarioDAO;
public class TestUsuarioDAO {

	@Test
	public void usuariosTest() throws FileNotFoundException {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		assertNotNull(usuarioDAO);
		assertEquals(2,usuarioDAO.getUsuarios().size());
	} 
	@Test
	public void addRemoveUsuarioTest() throws IOException {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List<Usuario> usuarios= usuarioDAO.getUsuarios();
		Usuario usuario1 = new Usuario();
		usuario1.setCedula("V-8796484");
		usuario1.setContrasena("1234");
		usuario1.setNombre("Pedro Perez");
		usuario1.setUsername("Vgowen");
		
		usuarioDAO.guardar(usuario1);
		assertNotNull(usuario1.getId());
		assertNotEquals(1,usuarioDAO.getUsuarios().size());
		assertEquals(usuarios.size()+1,usuarioDAO.getUsuarios().size());

		usuarioDAO.eliminar(usuario1);
		assertEquals(usuarios.size(),usuarioDAO.getUsuarios().size());
	
				
		
	}

}
