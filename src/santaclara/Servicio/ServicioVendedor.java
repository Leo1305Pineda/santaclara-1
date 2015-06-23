package santaclara.Servicio;

import java.io.IOException;
import java.util.List;

import santaclara.dao.impl.ClienteDAO;
import santaclara.dao.impl.RutaDAO;
import santaclara.dao.impl.VendedorDAO;
import santaclara.modelo.Cliente;
import santaclara.modelo.Ruta;
import santaclara.modelo.Vendedor;

public class ServicioVendedor {
	
	private VendedorDAO vendedorDAO = new VendedorDAO();  

	public List<Vendedor>  getVendedores() throws NumberFormatException, IOException
	{	
		return vendedorDAO.getVendedores();
	}
	
	public void guardar(Vendedor vendedor) throws IOException {
		// TODO Auto-generated method stub
		// if el es nuevo que no este otro con el mismo nombre
		vendedorDAO.guardar(vendedor);
		
	}

}
