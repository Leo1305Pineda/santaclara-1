package santaclara.Servicio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import santaclara.dao.impl.RutaDAO;
import santaclara.dao.impl.VendedorDAO;
import santaclara.modelo.Vendedor;
import santaclara.modelo.Ruta;

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
	
	public Vendedor buscar(Integer id) throws FileNotFoundException{
		return vendedorDAO.getVendedor(id);
	}
	
	public String guardar(Vendedor vendedor) throws IOException {
		// TODO Auto-generated method stub
		if (vendedor.getRutas().size()<=0) return "El Vendedor no tiene asociada una ruta";
		
		if (vendedor.getRutas().size()>3) return "El limite de ruta permitida es de 3";  
		
		if (vendedor.equals(vendedorDAO.getVendedor(vendedor.getId()))) return "El vendedor Exixtente";
				
		if (this.vendedorDAO.getVendedor(vendedor)) return "Nombre de Usuario Existente";
		
		
		
		
		vendedorDAO.guardar(vendedor);
		return "";
		
	}
	
	public void guardar(Ruta ruta)throws IOException{
		rutaDAO.guardar(ruta);
	}
	
	public Boolean  getUsuario(Integer id)throws IOException{
		if  (vendedorDAO.getVendedor(id)!=null) return true;
		return false;
	}

	public Vendedor getVendedor(Integer id) throws FileNotFoundException {
		// TODO Auto-generated method stub
		return vendedorDAO.getVendedor(id);
	}

	public void eliminar(Vendedor vendedor) throws IOException {
		// TODO Auto-generated method stub
		vendedorDAO.eliminar(vendedor);
	}	
}
