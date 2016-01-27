package santaclara.dao.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import santaclara.dao.ISaborDAO;
import santaclara.modelo.Sabor;

public class SaborDAO extends GenericoDAO implements ISaborDAO{
	
	private String ruta = "archivos/sabores.txt";

	@Override
	public List<Sabor> getSabores() throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<Sabor> sabores = new ArrayList<Sabor>();
		File file = new File(ruta);
 		Scanner scanner = new Scanner(file);
		while(scanner.hasNext())
		{
			 Sabor sabor = new Sabor();
			 sabor.setId(new Integer(scanner.skip("id:").nextLine().trim()));
			 sabor.setSabor(scanner.skip("sabor:").nextLine().trim());
			 sabores.add(sabor);
		}
		scanner.close();
		return sabores;
	}

	@Override
	public void guardar(Sabor sabor) throws IOException {
		// TODO Auto-generated method stub
		List<Sabor> sabores = getSabores();
		//buscar codigo el ultimo codigo Asignado 
		if(sabor.getId() == null )
		{
			int i = 0;
			for(Sabor sabor1 : sabores)
			{
				if(sabor1.getId()> i )
				{
					i = sabor1.getId();
				}
			}
			sabor.setId(i+1);
			sabores.add(sabor);
		}
		else
		{
			for(Sabor sabor1 :sabores)
			{
				if(sabor1.getId().equals(sabor.getId()))
				{ 
					sabor1.setSabor(sabor.getSabor());
					
				}
			}
		}
		guardarTodo(sabores);
	}

	@Override
	public void eliminar(Sabor sabor) throws IOException {
		// TODO Auto-generated method stub
		List<Sabor> sabores = getSabores();
		for(Sabor sabor1 :sabores)
		{
			if(sabor1.getId().equals(sabor.getId()))
			{
				sabores.remove(sabor1);
				break;
			}
		}
		guardarTodo(sabores);

	}
	@Override
	public Sabor getSabor(Integer id) throws IOException {
		// TODO Auto-generated method stub
		List<Sabor> sabores = getSabores();
		for(Sabor sabor1 : sabores)
		{
			if (sabor1.getId().equals(id))
			{
				return sabor1;
			}
		}
		return null;
	}
	
	public void guardarTodo(List<Sabor> sabores ) throws IOException
	{
		FileWriter fw = new FileWriter(ruta);
		for(Sabor sabor :sabores)
		{
			fw.append("id:"+sabor.getId().toString()+"\n");
			fw.append("sabor:"+sabor.getSabor()+"\n");
		}
		fw.close();
	}
	/*Estructura
 	id:  0  
	sabor: AguaUva 

	 * */

}
