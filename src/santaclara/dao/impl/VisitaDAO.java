/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.dao.impl;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import santaclara.dao.IVisitaDAO;
import santaclara.dbPostgresql.modelo.PostgreSql;
import santaclara.modelo.Visita;

public class VisitaDAO extends GenericoDAO implements IVisitaDAO{

	@Override
	public List<Visita> getVisitas() throws Exception {
		// TODO Auto-generated method stub
		List<Visita> visitas = new ArrayList<Visita>();
		
		ResultSet rSet = new PostgreSql().getSelect(
				"SELECT id, fecha, motivo, descripcion, valorvendedor, valorproducto,"
				+ " estado, idusuario, idcliente "
				+ "FROM visitas  ORDER BY id;"); 
		
		if(rSet==null) return null;
	
		
			while(rSet.next())
			{
				Boolean estado;
				if(rSet.getString("estado").equals("hecho")) estado = true;
				else estado = false;
				
				visitas.add(new Visita(
					rSet.getInt("id"), 
					rSet.getDate("fecha"),
						rSet.getString("motivo"),
						rSet.getString("descripcion"),
						rSet.getInt("valorvendedor"), 
						rSet.getInt("valorproducto"),
						estado,
						new UsuarioDAO().getUsuario(rSet.getInt("idusuario")) , 
						new ClienteDAO().getCliente(rSet.getInt("idcliente"))));
			}
			
		return visitas;
	}

	@Override
	public void guardar(Visita visita) throws Exception {
		// TODO Auto-generated method stub
		if (visita.getId()==null){
			
			new PostgreSql().ejecutar( 
					"INSERT INTO visitas( fecha, motivo, descripcion, valorvendedor, valorproducto, estado, idusuario, idcliente) "
							+"VALUES ("
							+ " '" +visita.getFechaStr()   		+"', "
							+ " '" +visita.getMotivo()   		+"', "
							+ " '" +visita.getDescripcion()   	+"', "
							+ "  " +visita.getValorProducto()   +" , "
							+ "  " +visita.getValorVendedor()   +" , "
							+ " '" +visita.getEstadoStr()       +"', "
							+ "  " +visita.getUsuario().getId() +" , "
							+ "  " +visita.getCliente().getId()	+");");
		}
		else{			
				new PostgreSql().ejecutar(
						"UPDATE visitas SET   "
								+ "fecha         = '" +visita.getFechaStr("-")      +"', "
								+ "motivo        = '" +visita.getMotivo()   		+"', "
								+ "descripcion   = '" +visita.getDescripcion()   	+"', "
								+ "valorproducto =  " +visita.getValorProducto()    +" , "
								+ "valorvendedor =  " +visita.getValorVendedor()    +" , "
								+ "estado        = '" +visita.getEstadoStr()        +"', "
								+ "idusuario     =  " +visita.getUsuario().getId()  +" , "
								+ "idcliente     =  " +visita.getCliente().getId()	+"   "
						+"WHERE    id = " +visita.getId() + ";");
		}
	}

	@Override
	public void eliminar(Visita visita) throws Exception {
		// TODO Auto-generated method stub
		if(visita!=null) new PostgreSql().ejecutar(
				"DELETE FROM visitas "
				+"WHERE id = "+visita.getId() +" ;");
	}

	@Override
	public Visita getVisita(Date fecha, Integer idJefeVenta, Integer idCliente)
			throws Exception {
		// TODO Auto-generated method stub
		
		ResultSet rSet = new PostgreSql().getSelect(
				"SELECT id, fecha, motivo, descripcion, valorvendedor, valorproducto,"
				+ " estado, idusuario, idcliente "
				+ " FROM visitas "
				+ " WHERE fecha = '" +new SimpleDateFormat("dd-MM-yyyy").format(fecha) + "' AND "
				+ " idusuario  =  " +idJefeVenta+ " AND"
				+ " idcliente  =  "	+idCliente	+ ";");

		if(rSet == null) return null;
		
		rSet.next();
		
		Boolean estado = false;
		if(rSet.getString("estado").equals("hecho")) estado = true;
		
		return new Visita(
				rSet.getInt("id"), 
				rSet.getDate("fecha"),
					rSet.getString("motivo"),
					rSet.getString("descripcion"),
					rSet.getInt("valorvendedor"), 
					rSet.getInt("valorproducto"),
					estado,
					new UsuarioDAO().getUsuario(rSet.getInt("idusuario")) , 
					new ClienteDAO().getCliente(rSet.getInt("idcliente")));

	}
	
	public Boolean isVisita(Date fecha,Integer idCliente,Integer idJefeVenta)throws Exception {
		// TODO Auto-generated method stub
		List<Visita> visitas = getVisitas();
		for(Visita visita1 :visitas)
		{	
			if (visita1.getUsuario() !=null)
			{
				if( visita1.getUsuario().getId().equals(idJefeVenta) && visita1.getCliente().getId().equals(idCliente))
				{
					
					if (visita1.getFecha().equals(fecha)) return true;
				}
			}
		}
		return false;
	}


	/****
	 private String ruta = "archivos/visitas.txt";
	@Override
	public List<Visita> getVisitas() throws Exception {
		// TODO Auto-generated method stub
		List<Visita> visitas = new ArrayList<Visita>();
		File file = new File(ruta);
 		Scanner scanner = new Scanner(file);
 		String linea;
		while(scanner.hasNext())
		{
			 Visita visita = new Visita();
			 visita.setId(new Integer(scanner.skip("id:").nextLine().trim()));
			 Date fecha = new Date();
			try {
				fecha = new SimpleDateFormat("dd/MM/yyyy").parse(scanner.skip("fecha:").nextLine().toString().trim());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 visita.setFecha(fecha);
			 
			 visita.setMotivo(scanner.skip("motivo:").nextLine());
			 visita.setDescripcion(scanner.skip("descripcion:").nextLine());
			 visita.setValorVendedor(new Integer(scanner.skip("valorVendedor:").nextLine().trim()));
			 visita.setValorProducto(new Integer(scanner.skip("valorProducto:").nextLine().trim()));
			 linea = new String();
			 linea = scanner.skip("estado:").nextLine().trim();
			 if (linea.toString().equals("hecho"))
				 {
				 visita.setEstado(true);
				 }
			 else if((linea.toString().equals("pendiente")))
				 {
				  visita.setEstado(false);
				 }
			 
			 linea =scanner.skip("idUsuario:").nextLine().trim();
			 
			 if(linea.trim().length() == 0) visita.setUsuario(null);
			 else visita.setUsuario(new UsuarioDAO().getUsuario((new Integer(linea))));
			 
			 linea =scanner.skip("idCliente:").nextLine().trim();
			 if(linea.trim().length() == 0)
			 {
				 visita.setCliente(null);
			 }
			 else
			 {
				 ClienteDAO clienteDAO = new ClienteDAO();
				 visita.setCliente(clienteDAO.getCliente(new Integer(linea)));				 	 
			 }
			 
			 visitas.add(visita); 
		}
		scanner.close();
		return visitas;

	}

	@Override
	public void guardar(Visita visita) throws Exception {
		// TODO Auto-generated method stub
		List<Visita> visitas = getVisitas();
		//buscar codigo el ultimo codigo Asignado 
		
		if(visita.getId() == null )
		{
			int i = 0;
			for(Visita visita1 : visitas)
			{
				if(visita1.getId()> i )
				{
					i = visita1.getId();
				}
			}
			visita.setId(i+1);
			visitas.add(visita);
		}
		else
		{
			for(Visita visita1 :visitas)
			{
				if(visita1.getId().equals(visita.getId()))
				{ 
					visita1.setDescripcion(visita.getDescripcion());
					visita1.setEstado(visita.getEstado());
					visita1.setFecha(visita.getFecha());
					visita1.setUsuario(visita.getUsuario());
					visita1.setMotivo(visita.getMotivo());
					visita1.setValorProducto(visita.getValorProducto());
					visita1.setValorVendedor(visita.getValorVendedor());
					visita1.setCliente(visita.getCliente());
				}
			}
		}
		guardarTodo(visitas);

	}

	@Override
	public void eliminar(Visita visita) throws Exception {
		// TODO Auto-generated method stub
		List<Visita> Visitas = getVisitas();
		for(Visita visita1 :Visitas)
		{
			if(visita1.getId().equals(visita.getId()))
			{
				Visitas.remove(visita1);
				break;
			}
		}
		///guardar Todo 
		guardarTodo(Visitas);
	}

	@Override
	public Visita getVisita(Date fecha,Integer idUsuario,Integer idCliente) throws Exception {
		// TODO Auto-generated method stub
		List<Visita> visitas = getVisitas();
		
		for(Visita visita1 :visitas)
		{
			if(visita1.getFecha().equals(fecha)
					&& visita1.getUsuario().getId().equals(idUsuario)
					&& visita1.getCliente().getId().equals(idCliente))
			{
				return visita1;
			}
		}
		return null;
	}
		
	public void guardarTodo(List<Visita> visitas) throws Exception
	{
		FileWriter fw = new FileWriter(ruta);
		for(Visita visita : visitas)
		{
			fw.append("id:"+visita.getId().toString()+"\n");
			fw.append("fecha:"+visita.getFechaStr()+"\n");
			fw.append("motivo:"+visita.getMotivo().toString()+"\n");
			fw.append("descripcion:"+visita.getDescripcion().toString()+"\n");
			fw.append("valorVendedor:"+visita.getValorVendedor().toString()+"\n");
			fw.append("valorProducto:"+visita.getValorProducto().toString()+"\n");
			if(visita.getEstado()==true)
			fw.append("estado:hecho\n");
			else fw.append("estado:pendiente\n");
			fw.append("idUsuario:"+(visita.getUsuario()== null
					? "  ":visita.getUsuario().getId().toString())+"\n");
			
			fw.append("idCliente:"+(visita.getCliente() == null 
					?" ":visita.getCliente().getId().toString())+"\n");
		}
		fw.close();
	}

	 *
	 */
}
	
	/*
 	La Estructura de los Archivos sera la Siguiente 
	id:3
fecha:06/06/2015
motivo:en espera
descripcion:por realizar
valorVendedor:0
valorProducto:0
estado:pendiente
idUsuario:1
idCliente:3


  * */
