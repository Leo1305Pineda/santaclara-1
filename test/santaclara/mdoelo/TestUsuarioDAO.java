package santaclara.mdoelo;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import santaclara.dao.impl.ClienteDAO;
import santaclara.modelo.Usuario; 
public class TestUsuarioDAO {

	@Test
	public void usuariosTest() throws FileNotFoundException {
		ClienteDAO UsuarioDAO = new UsuarioDAO();
		assertNotNull(usuarioDAO);
		assertEquals(4,usuarioDAO.getUsuario().size());
	}

	@Test
	public void addRemoveUsuarioTest() throws IOException {
		ClienteDAO usuarioDAO = new UsuuarioDAO();
		List<Usuario> usuarios= usuarioDAO.getusuarios();
		
		Cliente usuario1 = new Cliente();
		usuario1.setId(6);
		usuario1.setUsername("dadi");
		usuario1.setCedula("12456383");
		usuario1.setNombre("Dario");
		usuario1.setContrasena("737332");
		
		usuarioDAO.guardar(usuario1);
		assertNotNull(usuario1.getId());
		assertNotEquals(4,usuarioDAO.getusuarios().size());
		assertEquals(usuarios.size()+1,usuarioDAO.getusuarios().size());

		usuarioDAO.eliminar(usuario1);
		assertEquals(usuarios.size(),usuarioDAO.getUsuarios().size());
	}
    
}
