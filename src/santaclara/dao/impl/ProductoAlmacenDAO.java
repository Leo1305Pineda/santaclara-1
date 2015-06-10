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
	@Override
	public List<ProductoAlmacen> getProductoAlmacenes() throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		List<ProductoAlmacen> productoAlmacenes = new ArrayList<ProductoAlmacen>();
		File file = new File(ruta);
 		Scanner scaner = new Scanner(file);
		while(scaner.hasNext())
		{
			//asigna Producto
			 ProductoAlmacen productoAlmacen = new ProductoAlmacen();
			 String linea;
			 linea = scaner.skip("idCliente:").nextLine();
			 if(linea.trim().length() == 0)
			 {
				 productoAlmacen.setProducto(null);
			 }
			 else
			 {
				 EmpaqueProductoDAO empaqueProductoDAO = new EmpaqueProductoDAO();
				 productoAlmacen.setProducto(empaqueProductoDAO.getEmpaqueProducto(new Integer(linea)));
			 }
			 
			 //asigna Almacen
			 linea = scaner.skip("idAlmacen:").nextLine();
			 if(linea.trim().length() == 0)
			 {
				 productoAlmacen.setAlmacen(null);
			 }
			 else
			 {
				 AlmacenDAO almacenDAO = new AlmacenDAO(); 
				 productoAlmacen.setAlmacen(almacenDAO.getAlmacen(new Integer(linea)));			 	 
			 }
			 productoAlmacen.setStock(new Integer(scaner.skip("stock:").nextLine()));
			 productoAlmacen.setStockMin(new Integer(scaner.skip("stockMin:").nextLine()));
			 productoAlmacen.setExistencia(new Integer(scaner.skip("existencia:").nextLine()));
			 
			 productoAlmacenes.add(productoAlmacen); 
		}
		
		return productoAlmacenes;

	}

	@Override
	public void guardar(ProductoAlmacen productoAlmacen) throws IOException {
		// TODO Auto-generated method stub
		List<ProductoAlmacen> productoAlmacenes = getProductoAlmacenes();
		//buscar codigo el ultimo codigo Asignado 
			for(ProductoAlmacen productoAlmacen1 :productoAlmacenes)
			{
				if(productoAlmacen.getProducto().getId() == null && 
						productoAlmacen.getAlmacen().getId() == null)
				{
					/// vacio 
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
		///guardar Todo 
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
			fw.append("idProducto:"+productoAlmacen.getProducto() == null ? "  ":productoAlmacen.getProducto().getId()+"\n");
			fw.append("idAlmacen:"+productoAlmacen.getAlmacen() == null ? "  ":productoAlmacen.getAlmacen().getId()+"\n");
			fw.append("stock:"+productoAlmacen.getStock()+"\n");
			fw.append("stockMin:"+productoAlmacen.getStockMin()+"\n");
			fw.append("existencia:"+productoAlmacen.getExistencia()+"\n");
		}
		fw.close();
	}

/*
 * idProducto:0
idAlmacen:0
stock:100000
stockMin:900000
existencia: 70000
*/
}
