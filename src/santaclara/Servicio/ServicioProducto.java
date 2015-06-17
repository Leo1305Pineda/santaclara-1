package santaclara.Servicio;


import java.io.IOException;
import java.util.List;

import santaclara.dao.impl.CapacidadDAO;
import santaclara.dao.impl.PresentacionDAO;
import santaclara.dao.impl.ProductoDAO;
import santaclara.dao.impl.SaborDAO;
import santaclara.modelo.Capacidad;
import santaclara.modelo.Presentacion;
import santaclara.modelo.Producto;
import santaclara.modelo.Sabor;

public class ServicioProducto {

	private ProductoDAO  productoDAO = new ProductoDAO();
	private SaborDAO    saborDAO = new SaborDAO();
	private PresentacionDAO presentacionDAO = new PresentacionDAO();
	private CapacidadDAO capacidadDAO = new CapacidadDAO();
	
	
	public List<Producto>  getProductos() throws NumberFormatException, IOException
	{	
		return productoDAO.getProductos();
	}
		
	public List<Presentacion> getPresentaciones() throws Exception
	{
		return presentacionDAO.getPresentaciones();	
	}
	
	
	public List<Sabor> getSabores() throws Exception 
	{
		return saborDAO.getSabores();
	}
	
	public List<Capacidad> getCapacidades () throws Exception 
	{
		return capacidadDAO.getCapacidades();
	}

	public void guardar(Producto producto) throws IOException {
		// TODO Auto-generated method stub
		// if el es nuevo que no este otro con el mismo nombre 
		productoDAO.guardar(producto);
		
	}
	
}