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
	private List<Producto> productos;
	
	
	public List<Producto>  getProductos() throws NumberFormatException, IOException
	{	
		return productoDAO.getProductos();
	}
		
	public List<Presentacion> getPresentaciones() throws Exception
	{
		return presentacionDAO.getPresentaciones();	
	}
	
	public Presentacion getPresentacion(Integer id) throws IOException{
		return presentacionDAO.getPresentacion(id);
	}
	
	public Sabor getSabor(Integer id)throws IOException{
		return saborDAO.getSabor(id);
	}
	
	public Capacidad getCapacidad(Integer id) throws IOException{
		return capacidadDAO.getCapacidad(id);  
	}
	
	public List<Sabor> getSabores() throws Exception 
	{
		return saborDAO.getSabores();
	}
	
	public List<Capacidad> getCapacidades () throws Exception 
	{
		return capacidadDAO.getCapacidades();
	}

	public void guardar(Producto producto) throws Exception {
		// TODO Auto-generated method stub
		
			productos = productoDAO.getProductos();

			for(Producto producto1 :productos)
			{
				if(producto1.getNombre().equals(producto.getNombre()) &&
					producto1.getCapacidad().getId().equals(producto.getCapacidad().getId())&&
					producto1.getPresentacion().getId().equals(producto.getPresentacion().getId())&&
					producto1.getSabor().getId().equals(producto.getSabor().getId()) &&	
					!producto1.getId().equals(producto.getId()))
				{ 
					throw new Exception("Producto Existente");
				}
			}
			productoDAO.guardar(producto); 
	}
	
	public void eliminar(Producto producto) throws IOException{
		
		productoDAO.eliminar(producto);
	}
	
	public void modificar(Producto producto) throws IOException{
		productoDAO.guardar(producto);
	}
	
	public Producto buscar(Integer id)throws IOException{
		return productoDAO.getProducto(id);
	}
}