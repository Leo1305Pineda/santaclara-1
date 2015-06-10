package santaclara.dao.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import santaclara.dao.IProductoDAO;
import santaclara.modelo.Producto;
import santaclara.modelo.Capacidad;
import santaclara.modelo.Sabor;
import santaclara.modelo.Presentacion;

public  class ProductoDAO extends GenericoDAO implements IProductoDAO{

	private String ruta = "archivos/producto.txt";
	
	@Override
	public List<Producto> getproductos() throws FileNotFoundException {
		// TODO Auto-generated method stub
		// Listar Todos lo Clientes 
		List<Producto> productos = new ArrayList<Producto>();
		File file = new File(ruta);
 		Scanner scaner = new Scanner(file);
		while(scaner.hasNext())
		{
			 Producto producto = new Producto();
			 String linea;
			 producto.setId(new Integer(scaner.skip("id:").nextLine()));
			 //Asigna Capacidad
			 linea = scaner.skip("idCapacidad:").nextLine();
			 if(linea.trim().length() == 0)
			 {
				 producto.setCapacidad(null);
			 }
			 else
			 {
				 Capacidad capacidad = new Capacidad();
				capacidad.setId(new Integer(linea));
				 producto.setCapacidad(capacidad);				 	 
			 }
			 //asigna Presentacion
			 linea = scaner.skip("idPresentacion:").nextLine();
			 if(linea.trim().length() == 0)
			 {
				 producto.setPresentacion(null);
			 }
			 else
			 {
				 Presentacion presentacion = new Presentacion();
				 presentacion.setId(new Integer(linea));
				 producto.setPresentacion(presentacion);				 	 
			 }
			 //asigna Sabor
			 linea = scaner.skip("idPresentacion:").nextLine();
			 if(linea.trim().length() == 0)
			 {
				 producto.setSabor(null);
			 }
			 else
			 {
				 Sabor sabor = new Sabor();
				 sabor.setId(new Integer(linea));
				 producto.setSabor(sabor);				 	 
			 }
 
			 producto.setNombre(scaner.skip("nombre:").nextLine());
			 producto.setPrecio(new Double(scaner.skip("precio:").nextLine()));
			 
			 productos.add(producto); 
		}
		
		return productos;
	}

	@Override
	public void	guardar(Producto producto) throws IOException {
		// TODO Auto-generated method stub
		List<Producto> productos = getproductos();
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
		List<Producto> productos = getproductos();
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
	public Producto getProducto(Integer id) throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<Producto> productos = getproductos();
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
			fw.append("idCapacidad:"+producto.getCapacidad() == null ? "  ":producto.getCapacidad().getId()+"\n");
			fw.append("idPresentacion:"+producto.getPresentacion() == null ? "  ":producto.getPresentacion().getId()+"\n");
			fw.append("idSabor:"+producto.getSabor() == null ? "  ":producto.getSabor().getId()+"\n");
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

