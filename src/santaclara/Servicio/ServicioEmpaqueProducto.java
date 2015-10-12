package santaclara.Servicio;

import java.io.IOException;
import java.util.List;
import santaclara.dao.impl.ProductoDAO;
import santaclara.dao.impl.EmpaqueProductoDAO;
import santaclara.modelo.Producto;
import santaclara.modelo.EmpaqueProducto;

public class ServicioEmpaqueProducto {

	private EmpaqueProductoDAO  empaqueProductoDAO = new EmpaqueProductoDAO();
	private ProductoDAO productoDAO = new ProductoDAO();
	private List<EmpaqueProducto> empaqueProductos;
	
	
	public List<EmpaqueProducto>  getEmpaqueProductos() throws NumberFormatException, IOException
	{	
		return empaqueProductoDAO.getEmpaques();
	}
		
	public List<Producto> getProductos() throws Exception
	{
		return productoDAO.getProductos();	
	}
	
	public Producto getProducto(Integer id) throws IOException{
		return productoDAO.getProducto(id);
	}
	public EmpaqueProducto getEmpaqueProducto(Integer idEmpaqueProducto)throws IOException{
		
		for(EmpaqueProducto empaqueProducto : getEmpaqueProductos()){
			if(empaqueProducto.getId().equals(idEmpaqueProducto)){
				return empaqueProducto;
			}
		}
		return null; 
		
	}
	
	
	public String guardar(EmpaqueProducto empaqueProducto) throws IOException {
		// TODO Auto-generated method stub
		
			empaqueProductos = empaqueProductoDAO.getEmpaques();
			
			for(EmpaqueProducto empaque :empaqueProductos)
			{
				/*&&
						*/
					if(empaque.getCantidad().equals(empaqueProducto.getCantidad())&&
							empaque.getProducto().getId().equals(empaqueProducto.getProducto().getId())&&	
						!empaque.getId().equals(empaqueProducto.getId()))
					{ 
						return "Producto Existente";
					}
			}
			
		
			empaqueProductoDAO.guardar(empaqueProducto); 
			return "Operacion Exitosa ";
	}
	
	public void eliminar(EmpaqueProducto empaqueProducto) throws IOException{
		empaqueProductoDAO.eliminar(empaqueProducto);
	}
	
	public void modificar(EmpaqueProducto empaqueProducto) throws IOException{
		empaqueProductoDAO.guardar(empaqueProducto);
	}
	
	public EmpaqueProducto buscar(Integer id)throws IOException{
		return empaqueProductoDAO.getEmpaqueProducto(id);
	}
}