/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import santaclara.dao.IClienteDAO;
import santaclara.modelo.Cliente;
import santaclara.modelo.Ruta;
import santaclara.modelo.Zona;

public class ClienteDAO extends GenericoDAO implements IClienteDAO{

	public ClienteDAO() {
		super();
		// TODO Auto-generated constructor stub
		try {
			activarConexionBaseDato();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Cliente> getClientes() throws Exception {
		// TODO Auto-generated method stub
		List<Cliente> clientes = new ArrayList<Cliente>();
		
		ResultSet rSet = getConexion().getSelect(
				"SELECT c.id, c.rif, c.razonsocial, c.direccion, c.telefono, c.idruta ,r.nombre as nombreruta , r.idzona , z.descripcion"
				+ " FROM clientes c, rutas r ,zonas z"
				+ " WHERE c.idruta = r.id AND r.idzona = z.id "
				+ " Order by c.id;"); 
		
		if(rSet==null || rSet.getFetchSize()!=0) return null;
	
			while(rSet.next())clientes.add(
					new Cliente(
							rSet.getInt("id"),rSet.getString("rif") , 
							rSet.getString("razonsocial"), rSet.getString("direccion"), 
							rSet.getString("telefono"),
							new Ruta(
									rSet.getInt("idruta"),
									rSet.getString("nombreruta"), 
									new Zona(
											rSet.getInt("idzona"),
											rSet.getString("descripcion"))))); 
		return clientes;
	}
	
	@Override
	public void guardar(Cliente cliente) throws Exception {
		// TODO Auto-generated method stub
		if (cliente.getId()==null){
			getConexion().ejecutar( 
					"INSERT INTO clientes(rif, razonsocial, direccion, telefono, idruta) "
					+ "VALUES ("
					+"' " +cliente.getRif()				+"', "
					+"' " +cliente.getRazonsocial()		+"', "
					+"' " +cliente.getDireccion()		+"', "
					+"' " +cliente.getTelefono()		+"', "
					+"  " +cliente.getRuta().getId()	+" "
					+ ");");	
		}
		else{
			getConexion().ejecutar(
					"UPDATE clientes SET   "
					+" rif         ='" +cliente.getRif() +"', "
					+" razonsocial ='" +cliente.getRazonsocial() 	+"', "
					+" direccion   ='" +cliente.getDireccion()		+"', "
					+" telefono    ='" +cliente.getTelefono() 		+"', "
					+" idruta      = " +cliente.getRuta().getId() 	+"  "
					+" WHERE    id = " +cliente.getId()+";");
		}
	}

	@Override
	public void eliminar(Cliente cliente) throws Exception {
		// TODO Auto-generated method stub
		if(cliente!=null) getConexion().ejecutar(
								"DELETE FROM clientes "
								+"WHERE id = "+cliente.getId() +" ;");
	}

	@Override
	public Cliente getCliente(Integer id) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rSet = getConexion().getSelect(
				"SELECT c.id, c.rif, c.razonsocial, c.direccion, c.telefono, c.idruta ,r.nombre as nombreruta , r.idzona , z.descripcion"
				+ " FROM clientes c, rutas r ,zonas z"
				+ " WHERE c.idruta = r.id AND r.idzona = z.id  AND c.id ="+id+""
				+ " ;");

		if(rSet == null || rSet.getFetchSize()!=0 ) return null;

		while(rSet.next())
		{
			new Cliente(
					rSet.getInt("id"),rSet.getString("rif") , 
					rSet.getString("razonsocial"), rSet.getString("direccion"), 
					rSet.getString("telefono"),
					new Ruta(
							rSet.getInt("idruta"),
							rSet.getString("nombreruta"), 
							new Zona(
									rSet.getInt("idzona"),
									rSet.getString("descripcion"))));
		}
		return null;
	}

	public Cliente getCliente(String rif) throws Exception {
		// TODO Auto-generated method stub
		
		ResultSet rSet = getConexion().getSelect(
				"SELECT c.id, c.rif, c.razonsocial, c.direccion, c.telefono, c.idruta ,r.nombre as nombreruta , r.idzona , z.descripcion"
				+ " FROM clientes c, rutas r ,zonas z"
				+ " WHERE c.idruta = r.id AND r.idzona = z.id  AND c.rif ='"+rif+"'"
				+ " ;");

		if(rSet == null || rSet.getFetchSize()!=0 ) return null;

		while(rSet.next())
		{
			new Cliente(
					rSet.getInt("id"),rSet.getString("rif") , 
					rSet.getString("razonsocial"), rSet.getString("direccion"), 
					rSet.getString("telefono"),
					new Ruta(
							rSet.getInt("idruta"),
							rSet.getString("nombreruta"), 
							new Zona(
									rSet.getInt("idzona"),
									rSet.getString("descripcion"))));
		}
			
			
	return null;
		
	}

	@Override
	public Boolean getCliente(Cliente cliente) throws Exception {
		// TODO Auto-generated method stub
		List<Cliente> clientes = getClientes();
		for(Cliente cliente1 :clientes)
		{
				if(cliente1.getRazonsocial().equals(cliente.getRazonsocial())&&
						!cliente1.getId().equals(cliente.getId()))
				{ 
					return true;
				}
		}
		return false;
    }
	
	/**
 * 
 	private String ruta = "archivos/Clientes.txt";

	@Override
	public List<Cliente> getClientes() throws Exception {
		// TODO Auto-generated method stub
		// Listar Todos lo Clientes 
		List<Cliente> clientes = new ArrayList<Cliente>();
		File file = new File(ruta);
 		Scanner  scanner = new Scanner(file);
		while(scanner.hasNext())
		{
			 Cliente cliente = new Cliente();
			 cliente.setId(new Integer(scanner.skip("id:").nextLine().trim()));
			 cliente.setRif(scanner.skip("rif:").nextLine().trim());
			 cliente.setRazonsocial(scanner.skip("razonsocial:").nextLine().trim());
			 cliente.setDireccion(scanner.skip("direccion:").nextLine());
			 cliente.setTelefono(scanner.skip("telefono:").nextLine().trim());
			 String linea =scanner.skip("ruta:").nextLine().trim();
			 if(linea.trim().length() == 0)
			 {
				 cliente.setRuta(null);
			 }
			 else
			 {
				 RutaDAO rutaDAO = new RutaDAO();
				 cliente.setRuta(rutaDAO.getRuta(new Integer(linea)));				 	 
			 }
			 clientes.add(cliente); 
		}
  	    scanner.close();
		return clientes;
	}
	@Override
	public void guardar(Cliente cliente) throws Exception {
		// TODO Auto-generated method stub
		List<Cliente> clientes = getClientes();
		//buscar codigo el ultimo codigo Asignado 
		if(cliente.getId() == null )
		{
			int i = 0;
			for(Cliente cliente1 : clientes)
			{
				if(cliente1.getId()> i )
				{
					i = cliente1.getId();
				}
			}
			cliente.setId(i+1);
			clientes.add(cliente);
		}
		else
		{
			for(Cliente cliente1 :clientes)
			{
				if(cliente1.getId().equals(cliente.getId()))
				{ 
					cliente1.setRif(cliente.getRif());
					cliente1.setRazonsocial(cliente.getRazonsocial());
					cliente1.setDireccion(cliente.getDireccion());
					cliente1.setTelefono(cliente.getTelefono());
					cliente1.setRuta(cliente.getRuta());
				}
			}
		}
		guardarTodo(clientes);
	}

	@Override
	public void eliminar(Cliente cliente) throws Exception {
		// TODO Auto-generated method stub
		if (cliente != null)
		{
			List<Cliente> clientes = getClientes();
			for(Cliente cliente1 :clientes)
			{
				if(cliente1.getId().equals(cliente.getId()))
				{
					clientes.remove(cliente1);
					break;
				}
			}
			guardarTodo(clientes);
		}
	}

	@Override
	public Cliente getCliente(Integer id) throws Exception {
		// TODO Auto-generated method stub
		List<Cliente> clientes = getClientes();
		for(Cliente cliente1 :clientes)
		{
			if(cliente1.getId().equals(id))
			{
				return cliente1;
			}
		}
		return null;
    }
	
	public Boolean getCliente(Cliente cliente) throws Exception {
		// TODO Auto-generated method stub
		List<Cliente> clientes = getClientes();
		for(Cliente cliente1 :clientes)
		{
				if(cliente1.getRazonsocial().equals(cliente.getRazonsocial())&&
						!cliente1.getId().equals(cliente.getId()))
				{ 
					return true;
				}
		}
		return false;
    }
	
	public ClienteDAO(String ruta) {
		super();
		this.ruta = ruta;
	}

	public ClienteDAO( ) {
		super();  
	}
	
	public void guardarTodo(List<Cliente> clientes ) throws Exception
	{
		FileWriter fw = new FileWriter(ruta);
		for(Cliente cliente :clientes)
		{
			fw.append("id:"+cliente.getId().toString()+"\n");
			fw.append("rif:"+cliente.getRif()+"\n");
			fw.append("razonsocial:"+cliente.getRazonsocial()+"\n");
			fw.append("direccion:"+cliente.getDireccion()+"\n");
			fw.append("telefono:"+cliente.getTelefono()+"\n");
			fw.append("ruta:"+(cliente.getRuta() == null ? "  ":cliente.getRuta().getId())+"\n");
		}
		fw.close();
	}


	public void Mostrar() throws Exception{
		List<Cliente> clientes = getClientes();
		System.out.println("Listar Todos los Clientes");
		for(Cliente cliente1 :clientes)
		{
			System.out.println("id: "+cliente1.getId());
			System.out.println("rif: "+cliente1.getRif());
			System.out.println("razonSocial: "+cliente1.getRazonsocial());
			System.out.println("direccion: "+cliente1.getDireccion());
			System.out.println("telefono: "+cliente1.getTelefono()+"\n");
			
			System.out.println("Informacion de la Ruta del Cliente: ");
			System.out.println("id: "+cliente1.getRuta().getId());
			System.out.println("nombre: "+cliente1.getRuta().getNombre()+"\n");
			
			System.out.println("Informacion de la Ruta-Zona del Cliente: ");
			System.out.println("id: "+cliente1.getRuta().getZona().getId());
			System.out.println("descripcion: "+cliente1.getRuta().getZona().getDescripcion()+"\n");
			
		}
	}
	@Override
	public Cliente getCliente(String rif) throws Exception {
		// TODO Auto-generated method stub
		List<Cliente> clientes = getClientes();
		for(Cliente cliente1 :clientes)
		{
			if(cliente1.getRif().equals(rif))
			{
				return cliente1;
			}
		}
		return null;
	}

 
 */
	
	/*
 	La Estructura de los Archivos sera la Siguiente 
  	id:1
	rif:V-19827297
	razonsocial:Rhonal Alfredo
	direccion:Barrio el Jebe Sector la Estrella
	telefono:04161556613
	ruta:1
  * */
	
} 

