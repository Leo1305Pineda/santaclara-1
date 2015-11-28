package santaclara.dao.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import santaclara.dao.IProductoDAO;
import santaclara.modelo.Producto;

public  class ProductoDAO extends GenericoDAO implements IProductoDAO{

	private String ruta = "archivos/productos.txt";
	
	@Override
	public List<Producto> getProductos() throws NumberFormatException, IOException {
		// TODO Auto-generated method stub 
		List<Producto> productos = new ArrayList<Producto>();
		File file = new File(ruta);
		Scanner scanner = new Scanner(file);
		while(scanner.hasNext())
		{
			 Producto producto = new Producto();
			 producto.setId(new Integer(scanner.skip("id:").nextLine().trim()));
			 //Asigna Capacidad
			 CapacidadDAO capacidadDAO = new CapacidadDAO();
			 producto.setCapacidad(
					 capacidadDAO.getCapacidad(
							 new Integer(scanner.skip("idCapacidad:").nextLine().trim())));
			//asigna Presentacion
			 PresentacionDAO presentacionDAO = new PresentacionDAO();
			 producto.setPresentacion(
					 presentacionDAO.getPresentacion(
							 new Integer(scanner.skip("idPresentacion:").nextLine().trim())));
			 //asigna Sabor
			 SaborDAO saborDAO = new SaborDAO();
			 producto.setSabor(
					 saborDAO.getSabor(
							 new Integer(scanner.skip("idSabor:").nextLine().trim())));
			 producto.setNombre(scanner.skip("nombre:").nextLine());
			 producto.setPrecio(new Double(scanner.skip("precio:").nextLine().trim()));
			 
			 productos.add(producto);
		}
		scanner.close();
		return productos;
	}

	@Override
	public void	guardar(Producto producto) throws IOException {
		// TODO Auto-generated method stub
		List<Producto> productos = getProductos();
		//buscar codigo el ultimo codigo Asignado 
		if(producto.getId() == null )
		{
			int i = 0;
			for(Producto producto1 : productos)
			{
				if(producto1.getId()> i )
				{
					i = producto1.getId();
				}
			}
			producto.setId(i+1);
			productos.add(producto);
		}
		else
		{
			for(Producto producto1 :productos)
			{
				if(producto1.getId().equals(producto.getId()))
				{
					/// vacio 
					producto1.setId(producto.getId());
					producto1.setCapacidad(producto.getCapacidad());
					producto1.setPresentacion(producto.getPresentacion());
					producto1.setSabor(producto.getSabor());
					producto1.setNombre(producto.getNombre());
					producto1.setPrecio(producto.getPrecio());
				}
			}
		}
		guardarTodo(productos);
	}

	@Override
	public void eliminar(Producto producto) throws IOException {
		// TODO Auto-generated method stub
		List<Producto> productos = getProductos();
		for(Producto producto1 :productos)
		{
			if(producto1.getId().equals(producto.getId()))
			{
				productos.remove(producto1);
				break;
			}
		}
		///guardar Todo 
		guardarTodo(productos);
		
	}

	@Override
	public Producto getProducto(Integer id) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		List<Producto> productos = getProductos();
		for(Producto producto1 :productos)
		{
			if(producto1.getId().equals(id))
			{
				return producto1;
			}
		}
		return null;
    }

	
	public ProductoDAO(String ruta) {
		super();
		this.ruta = ruta;
	}

	public ProductoDAO( ) {
		super();  
	}
	
	public void guardarTodo(List<Producto> productos ) throws IOException
	{
		FileWriter fw = new FileWriter(ruta);
		for(Producto producto :productos)
		{
			fw.append("id:"+producto.getId().toString()+"\n");
	
			fw.append("idCapacidad:"+(producto.getCapacidad() == null 
					? "  ":producto.getCapacidad().getId())+"\n");
			
			fw.append("idPresentacion:"+(producto.getPresentacion() == null
					? "  ":producto.getPresentacion().getId())+"\n");
			
			fw.append("idSabor:"+(producto.getSabor() == null 
					? "  ":producto.getSabor().getId())+"\n");
			
			fw.append("nombre:"+producto.getNombre()+"\n");
			fw.append("precio:"+producto.getPrecio()+"\n");
		}
		fw.close();
	}
	
	/*
 	La Estructura de los Archivos sera la Siguiente 
  	idProducto:1
	idCapacidad:1
	idPresentacion:2
	idSabor:3
	nombre:CocaCola
	precio:500

  * */
	
} 

