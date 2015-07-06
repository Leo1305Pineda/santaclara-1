package santaclara.Servicio;

import santaclara.dao.impl.ConcesionarioDAO;
import santaclara.dao.impl.JefeVentaDAO;
import santaclara.dao.impl.UsuarioDAO;
import santaclara.dao.impl.VendedorDAO;
import santaclara.modelo.Concesionario;
import santaclara.modelo.JefeVenta;
import santaclara.modelo.Usuario;
import santaclara.modelo.Vendedor;

public class ServicioIniciarSesion {

	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private VendedorDAO vendedorDAO  = new VendedorDAO();
	private ConcesionarioDAO concesionarioDAO = new ConcesionarioDAO();
	private JefeVentaDAO jefeVentaDAO = new JefeVentaDAO();
	
	
	public Usuario iniciarSesion(String username, String contrasena) throws Exception {
		// TODO Auto-generated method stub
		Usuario usuario = usuarioDAO.getUsuario(username);
		if (usuario== null)
		{
			return null;
		}
		if (usuario.getContrasena().equals(contrasena))
		{

			//cargar elTipo de USuario 
			Vendedor vendedor = vendedorDAO.getVendedor(usuario.getId());
			if(vendedor != null)
			{
				return vendedor;
			}
			Concesionario concesionario = concesionarioDAO.getConcesionario(usuario.getId());
			if(concesionario != null )
			{
				return concesionario;
			}
			JefeVenta jefeVenta = jefeVentaDAO.getJefeVenta(usuario.getId());
			if(jefeVenta != null)
			{
				return jefeVenta;
			}
			return usuario;
		}
		else
		{
			return null;
		}
	}
}
