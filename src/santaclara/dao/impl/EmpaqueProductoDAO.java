package santaclara.dao.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import santaclara.dao.IEmpaqueProductoDAO;
import santaclara.modelo.EmpaqueProducto;

public  class EmpaqueProductoDAO extends GenericoDAO implements IEmpaqueProductoDAO{
	
private String ruta = "archivos/empaqueProducto.txt";	
public List<EmpaqueProducto> getEmpaques() throws NumberFormatException, IOException {
	// TODO Auto-generated method stub
	List<EmpaqueProducto> empaques = new ArrayList<EmpaqueProducto>();
	File file = new File(ruta);
		Scanner scaner = new Scanner(file);
	while(scaner.hasNext())
	{
		 EmpaqueProducto empaque = new EmpaqueProducto();
		 empaque.setId(new Integer(scaner.skip("id:").nextLine().trim()));
		 //Asigna Producto
		 ProductoDAO productoDAO = new ProductoDAO();
		 empaque.setProducto(
				 productoDAO.getProducto(
						 new Integer(scaner.skip("idProducto:").nextLine().trim())));
		 empaque.setCantidad(new Integer(scaner.skip("cantidad:").nextLine().trim()));
		 
		 empaques.add(empaque); 
	}
	
	return empaques;

}

	public void	guardar(EmpaqueProducto EmpaqueProducto) throws IOException {
		// TODO Auto-generated method stub
		List<EmpaqueProducto> empaques = getEmpaques();
		//buscar codigo el ultimo codigo Asignado 
		if(EmpaqueProducto.getId() == null )
		{
			int i = 0;
			for(EmpaqueProducto empaque1 : empaques)
			{
				if(empaque1.getId()> i )
				{
					i = empaque1.getId();
				}
			}
			EmpaqueProducto.setId(i+1);
			empaques.add(EmpaqueProducto);
		}
		else
		{
			for(EmpaqueProducto empaque1 :empaques)
			{
				if(empaque1.getId().equals(EmpaqueProducto.getId()))
				{
		 
					empaque1.setId(EmpaqueProducto.getId());
					empaque1.setProducto(EmpaqueProducto.getProducto());
					empaque1.setCantidad(EmpaqueProducto.getCantidad());
				}
			}
		}
		guardarTodo(empaques);
	}

	@Override
	public void eliminar(EmpaqueProducto empaque) throws IOException {
		// TODO Auto-generated method stub
		List<EmpaqueProducto> empaques = getEmpaques();
		for(EmpaqueProducto empaque1 :empaques)
		{
			if(empaque1.getId().equals(empaque.getId()))
			{
				empaques.remove(empaque1);
				break;
			}
		} 
		guardarTodo(empaques);
		
	}
	
	public EmpaqueProductoDAO(String ruta) {
		super();
		this.ruta = ruta;
	}

	public EmpaqueProductoDAO( ) {
		super();  
	}
	

	public void guardarTodo(List<EmpaqueProducto> productos ) throws IOException
	{
		FileWriter fw = new FileWriter(ruta);
		for(EmpaqueProducto producto :productos)
		{
			fw.append("id:"+producto.getId().toString()+"\n");
			fw.append("idProducto:"+(producto.getProducto() == null 
					? "  ":producto.getProducto().getId())+"\n");
			fw.append("cantidad:"+producto.getCantidad()+"\n");
		}
		fw.close();
	}

	@Override
	public EmpaqueProducto getEmpaqueProducto(Integer id) throws IOException {
		// TODO Auto-generated method stub
		List<EmpaqueProducto> Empaques = getEmpaques();
		for(EmpaqueProducto Empaqueproducto1 : Empaques)
		{
			if(Empaqueproducto1.getId().equals(id))
			{
				return Empaqueproducto1;
			}
		}
		return new EmpaqueProducto();
	}
	
/* 	La Estructura de los Archivos sera la Siguiente 
  	id:1
	idProducto:1
	cantidad:10000  
  * */
}
