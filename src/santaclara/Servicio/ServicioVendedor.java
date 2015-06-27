package santaclara.Servicio;

import java.io.IOException;
import java.util.List;

import santaclara.dao.impl.RutaDAO;
import santaclara.dao.impl.VendedorDAO;
import santaclara.modelo.Ruta;
import santaclara.modelo.Vendedor;

public class ServicioVendedor {
	
	private VendedorDAO vendedorDAO = new VendedorDAO();
	private RutaDAO rutaDAO = new RutaDAO();

	public List<Vendedor>  getVendedores() throws NumberFormatException, IOException
	{	
		return vendedorDAO.getVendedores();
	}
	
	public List<Ruta> getRutas() throws NumberFormatException,IOException
	{
		return rutaDAO.getRutas();
	}
	
	public void guardar(Vendedor vendedor) throws IOException {
		// TODO Auto-generated method stub
		// if el es nuevo que no este otro con el mismo nombre
		vendedorDAO.guardar(vendedor);
	}
	
	public void guardar(Ruta ruta)throws IOException{
		rutaDAO.guardar(ruta);
	}
	
}
