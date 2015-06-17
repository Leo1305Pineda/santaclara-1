package santaclara.dao.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import santaclara.dao.IProductoAlmacenDAO;
import santaclara.modelo.ProductoAlmacen;

public class ProductoAlmacenDAO extends GenericoDAO implements IProductoAlmacenDAO {
	private String ruta = "archivos/productoAlmacenes.txt";
	private Scanner scaner;
	@Override
	public List<ProductoAlmacen> getProductoAlmacenes() throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		List<ProductoAlmacen> productoAlmacenes = new ArrayList<ProductoAlmacen>();
		File file = new File(ruta);
 		scaner = new Scanner(file);
		while(scaner.hasNext())
		{			
			 ProductoAlmacen productoAlmacen = new ProductoAlmacen();
			 //asigna Producto
			 EmpaqueProductoDAO productoEmpaqueDAO = new EmpaqueProductoDAO();
			 System.out.println("Paso Por Aca");
			 productoAlmacen.setProducto(
					 productoEmpaqueDAO.getEmpaqueProducto(
							 new Integer(scaner.skip("idEmpaqueProducto:").nextLine().trim())));
			 //asigna Almacen
			 AlmacenDAO almacenDAO = new AlmacenDAO();
			 productoAlmacen.setAlmacen(
					 almacenDAO.getAlmacen(
							 new Integer(scaner.skip("idAlmacen:").nextLine().trim())));
			 
			 productoAlmacen.setStock(new Integer(scaner.skip("stock:").nextLine().trim()));
			 productoAlmacen.setStockMin(new Integer(scaner.skip("stockMin:").nextLine().trim()));
			 productoAlmacen.setExistencia(new Integer(scaner.skip("existencia:").nextLine().trim()));
			 
			 productoAlmacenes.add(productoAlmacen); 
		}
		
		return productoAlmacenes;

	}

	@Override
	public void guardar(ProductoAlmacen productoAlmacen) throws IOException {
		// TODO Auto-generated method stub
		List<ProductoAlmacen> productoAlmacenes = getProductoAlmacenes(); 
			for(ProductoAlmacen productoAlmacen1 :productoAlmacenes)
			{
				if(productoAlmacen.getProducto().getId() == null && 
						productoAlmacen.getAlmacen().getId() == null)
				{ 
					productoAlmacen1.setExistencia(productoAlmacen.getExistencia());
					productoAlmacen1.setStock(productoAlmacen.getStock());
					productoAlmacen1.setStockMin(productoAlmacen.getStockMin());
				}
			}
		
		guardarTodo(productoAlmacenes);

	}

	@Override
	public void eliminar(ProductoAlmacen productoAlmacen) throws IOException {
		// TODO Auto-generated method stub
		List<ProductoAlmacen> ProductoAlmacenes = getProductoAlmacenes();
		for(ProductoAlmacen productoAlmacen1 :ProductoAlmacenes)
		{
			if(productoAlmacen1.getProducto().getId().equals(productoAlmacen.getProducto().getId())
					&& productoAlmacen1.getAlmacen().getId().equals(productoAlmacen.getAlmacen().getId()) )
			{
				ProductoAlmacenes.remove(productoAlmacen1);
				break;
			}
		} 
		guardarTodo(ProductoAlmacenes);
	}

	public ProductoAlmacenDAO(String ruta) {
		super();
		this.ruta = ruta;
	}

	public ProductoAlmacenDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public ProductoAlmacen getProductoAlmacen(Integer idProducto,Integer id) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		List<ProductoAlmacen> productoAlmacenes = getProductoAlmacenes();
		for(ProductoAlmacen productoAlmacen1 : productoAlmacenes)
		{
			if(productoAlmacen1.getAlmacen().getId().equals(id) 
					&& productoAlmacen1.getProducto().getId().equals(idProducto))
			{
				return productoAlmacen1;
			}
		}
		return null;
	}
	
	public void guardarTodo(List<ProductoAlmacen> productoAlmacenes ) throws IOException
	{
		FileWriter fw = new FileWriter(ruta);
		for(ProductoAlmacen productoAlmacen :productoAlmacenes)
		{
			fw.append("idEmpaqueProducto:"+(productoAlmacen.getProducto() == null 
					? "  ":productoAlmacen.getProducto().getId().toString())+"\n");
			fw.append("idAlmacen:"+(productoAlmacen.getAlmacen() == null 
					? "  ":productoAlmacen.getAlmacen().getId().toString())+"\n");
			fw.append("stock:"+productoAlmacen.getStock()+"\n");
			fw.append("stockMin:"+productoAlmacen.getStockMin()+"\n");
			fw.append("existencia:"+productoAlmacen.getExistencia()+"\n");
		}
		fw.close();
	}

/*
 * idEmpaqueProducto::0
idAlmacen:0
stock:100000
stockMin:900000
existencia: 70000
*/
}
