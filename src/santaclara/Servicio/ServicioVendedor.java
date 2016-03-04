/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.Servicio;

import java.util.ArrayList;
import java.util.List;

import santaclara.dao.impl.RutaDAO;
import santaclara.dao.impl.VendedorDAO;
import santaclara.modelo.Vendedor;
import santaclara.modelo.Ruta;

public class ServicioVendedor {
	
	private VendedorDAO vendedorDAO = new VendedorDAO();
	private RutaDAO rutaDAO = new RutaDAO();

	public List<Vendedor>  getVendedores() throws Exception
	{	
		return vendedorDAO.getVendedores();
	}
	
	public List<Ruta> getRutas() throws Exception
	{
		return rutaDAO.getRutas();
	}
	
	public Vendedor buscar(Integer id) throws Exception{
		return vendedorDAO.getVendedor(id);
	}
	
	public void guardar(Vendedor vendedor) throws Exception {
		// TODO Auto-generated method stub
		if (vendedor.getRutas().size()<=0) throw new Exception("El Vendedor no tiene asociada una ruta");
		if (vendedor.getRutas().size()>3) throw new Exception("El limite de ruta permitida es de 3");  
		if (vendedor.equals(vendedorDAO.getVendedor(vendedor.getId()))) throw new Exception("El vendedor Exixtente");
		if (this.vendedorDAO.getVendedor(vendedor)) throw new Exception("Nombre de Usuario Existente");
		
		vendedorDAO.guardar(vendedor);
	}
	
	public void guardar(Ruta ruta)throws Exception{
		rutaDAO.guardar(ruta);
	}
	
	public Boolean  getUsuario(Integer id)throws Exception{
		if  (vendedorDAO.getVendedor(id)!=null) return true;
		return false;
	}

	public Vendedor getVendedor(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return vendedorDAO.getVendedor(id);
	}

	public void eliminar(Vendedor vendedor) throws Exception {
		// TODO Auto-generated method stub
		vendedorDAO.eliminar(vendedor);
	}
	
	public List<Vendedor>  getVendedores(Integer idRuta) throws Exception
	{	
		List<Vendedor> vendedors = new ArrayList<Vendedor>();
		for(Vendedor vendedor:getVendedores()){
			for(Ruta ruta : vendedor.getRutas())
				if (ruta.getId().equals(idRuta)){
					vendedors.add(vendedor);
				}
		}
		return vendedors;
	}

}
