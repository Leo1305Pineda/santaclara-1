package santaclara.dao.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import santaclara.dao.IAlmacenDAO;
import santaclara.modelo.Almacen;

public class AlmacenDAO extends GenericoDAO implements IAlmacenDAO {
	private String ruta = "archivos/almacenes.txt";
	@Override
	public List<Almacen> getAlmacenes() throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<Almacen> almacenes = new ArrayList<Almacen>();
		File file = new File(ruta);
 		Scanner scaner = new Scanner(file);
		while(scaner.hasNext())
		{
			Almacen almacen = new Almacen();
			almacen.setId(new Integer(scaner.skip("id:").nextLine()));
			almacen.setUbicacion(scaner.skip("ubicacion:").nextLine());
			almacenes.add(almacen); 
		}
		scaner.close();
		return almacenes;
	}

	@Override
	public void guardar(Almacen almacen) throws IOException {
		// TODO Auto-generated method stub
		List<Almacen> almacenes = getAlmacenes();
		//buscar codigo el ultimo codigo Asignado 
		if(almacen.getId() == null )
		{
			int i = 0;
			for(Almacen almacen1 : almacenes)
			{
				if(almacen1.getId()> i )
				{
					i = almacen1.getId();
				}
			}
			almacen.setId(i+1);
			almacenes.add(almacen);
		}
		else
		{
			for(Almacen almacen1 :almacenes)
			{
				if(almacen1.getId().equals(almacen.getId()))
				{ 
					almacen1.setUbicacion(almacen.getUbicacion());
				}
			}
		}
		guardarTodo(almacenes);

	}

	@Override
	public void eliminar(Almacen almacen) throws IOException {
		// TODO Auto-generated method stub
		List<Almacen> almacenes = getAlmacenes();
		for(Almacen almacene1 :almacenes)
		{
			if(almacene1.getId().equals(almacen.getId()))
			{
				almacenes.remove(almacene1);
				break;
			}
		} 
		guardarTodo(almacenes);
	}

	@Override
	public Almacen getAlmacen(Integer id) throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<Almacen> almacenes = getAlmacenes();
		for(Almacen almacene1 :almacenes)
		{
			if(almacene1.getId().equals(id))
			{
				return almacene1;
			}
		}
		return null;
	}
	public AlmacenDAO(String ruta) {
		super();
		this.ruta = ruta;
	}

	public AlmacenDAO( ) {
		super();  
	}
	
	public void guardarTodo(List<Almacen> almacenes) throws IOException
	{
		FileWriter fw = new FileWriter(ruta);
		for(Almacen almacen :almacenes)
		{
			fw.append("id:"+almacen.getId().toString()+"\n");
			fw.append("ubicacion:"+almacen.getUbicacion().toString()+"\n");
		}
		fw.close();
	}
	
	public void Mostrar() throws IOException {
		// TODO Auto-generated method stub
		List<Almacen> almacenes = getAlmacenes();
		System.out.println("Listar Todos los Almacenes");
		for(Almacen almacen1 :almacenes)
		{
			System.out.println("id: "+almacen1.getId());
			System.out.println("Ubicacion: "+almacen1.getUbicacion()+"\n");
		}
	}
	/*Estructura
	 * id:1
	 * ubicacion:Zoma industrial I
	 * */
}
