package santaclara.dao.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import santaclara.dao.IVisitaDAO;
import santaclara.modelo.Visita;
import santaclara.modelo.JefeVenta;

public class VisitaDAO extends GenericoDAO implements IVisitaDAO{

	private String ruta = "archivos/visitas.txt";

	@SuppressWarnings("deprecation")
	@Override
	public List<Visita> getVisitas() throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<Visita> visitas = new ArrayList<Visita>();
		File file = new File(ruta);
 		Scanner scaner = new Scanner(file);
 		String linea;
		while(scaner.hasNext())
		{
			 Visita visita = new Visita();
			 visita.setId(new Integer(scaner.skip("id:").nextLine()));
			 visita.setFecha(new Date(scaner.skip("fecha:").nextLine()));
			 visita.setDescripcion(scaner.skip("descripcion:").nextLine());
			 visita.setValorVendedor(new Integer(scaner.skip("valorProducto:").nextLine()));
			 visita.setValorProducto(new Integer(scaner.skip("valorVendedor:").nextLine()));
			 linea = new String();
			 linea = scaner.skip("valorVendedor:").nextLine();
			 if (linea == "hecho")
				 {
				 visita.setEstado(true);
				 }
			 else if((linea == "pendiente"))
				 {
				  visita.setEstado(false);
				 }
			 
			 linea =scaner.skip("idJefeVenta:").nextLine();
			 if(linea.trim().length() == 0)
			 {
				 visita.setJefeVenta(null);
			 }
			 else
			 {
				 JefeVenta jefeVenta= new JefeVenta();
				 jefeVenta.setId(new Integer(linea));
				 visita.setJefeVenta(jefeVenta);				 	 
			 }
			 visitas.add(visita); 
		}
		
		return visitas;

	}

	@Override
	public void guardar(Visita visita) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(Visita visita) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Visita getVisita(Integer id) throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<Visita> visitas = getVisitas();
		for(Visita visita1 :visitas)
		{
			if(visita1.getId().equals(id))
			{
				return visita1;
			}
		}
		return null;
	}
	
	
	
	/*
 	La Estructura de los Archivos sera la Siguiente 
	id:0
	fecha:06/06/2015
	motivo:visita rutinaria
	descripcion:realisada exitosamente
	valorVendedor:0
	valorProducto:0
	estado:hecho
	idJefeVenta:1

  * */
	
} 

