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

import santaclara.dao.ISalpDAO;
import santaclara.dbPostgresql.modelo.PostgreSql;
import santaclara.modelo.Salp;

public class SalpDAO extends GenericoDAO implements  ISalpDAO{

	@Override
	public List<Salp> getSalps() throws Exception {
		// TODO Auto-generated method stub
		List<Salp> salps = new ArrayList<Salp>(); 
		
		ResultSet rSet = new PostgreSql().getSelect(
				"SELECT idcliente FROM salp Order by idcliente;"); 
		
		if(rSet==null) return null;
			while(rSet.next())salps.add(
					new Salp(new ClienteDAO().getCliente(rSet.getInt("idcliente")))); 
		return salps;
	}
	
	@Override
	public void guardar(Salp salp) throws Exception {
		// TODO Auto-generated method stub
		if (salp.getId()==null){
			try {

				new PostgreSql().ejecutar(
						"  BEGIN;"
						+ ""
						+" INSERT INTO clientes(rif, razonsocial,direccion, telefono,idruta)"
						+" VALUES ("
						+" '" +salp.getRif()			+"', " 
						+" '" +salp.getRazonsocial()	+"', "
						+" '" +salp.getDireccion()		+"', "
						+" '" +salp.getTelefono()		+"', "
						+"  " +salp.getRuta().getId()	+"   "
						+ ");"
						+ "                                  "
						+ "INSERT INTO salp(idcliente) "
						+ " VALUES ((SELECT max(id) FROM clientes)); "
						+ "                                                                 "
						+ " COMMIT;");
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
				
		}
		else{
			new ClienteDAO().guardar(salp);
		}
	}

	@Override
	public void eliminar(Salp salp) throws Exception {
		// TODO Auto-generated method stub
		if(salp!=null) new PostgreSql().ejecutar(
								"BEGIN;"
								+" DELETE FROM salp "
								+" WHERE idcliente = "+salp.getId() +";  "
								+"                   "
								+" DELETE FROM clientes "
								+" WHERE id = "+salp.getId() +";"
								+ " "
								+"COMMINT;"
								+" ;");
	}

	@Override
	public Salp getSalp(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return new Salp(new ClienteDAO().getCliente(id)) ;
		
	}
	
	
	/***
	 * 	
	 private String ruta = "archivos/salps.txt";

	@Override
	public List<Salp> getSalps() throws Exception {
		// TODO Auto-generated method stub
		List<Salp> salps = new ArrayList<Salp>();
		ClienteDAO clienteDAO = new ClienteDAO();
		File file = new File(ruta);
 		Scanner scanner = new Scanner(file);
		while(scanner.hasNext())
		{
			 Salp salp = new Salp();
			 salp.setId(new Integer(scanner.skip("idCliente:").nextLine()));
			 salps.add(salp);
		}
		scanner.close();
		//Cargo la info de Cliente
		List<Cliente> clientes;
		clientes = clienteDAO.getClientes();
		for(Salp salp1 :salps)
		{
			for(Cliente cliente: clientes)
			{
				if(salp1.getId().equals(cliente.getId()))
				{
					salp1.setRif(cliente.getRif());
					salp1.setId(cliente.getId());
					salp1.setRazonsocial(cliente.getRazonsocial());
					salp1.setDireccion(cliente.getDireccion());
					salp1.setTelefono(cliente.getTelefono());
					salp1.setRuta(cliente.getRuta());
					break;
				}
			}
		}			  
		return salps;
		
	}

	@Override
	public void guardar(Salp salp) throws Exception {
		// TODO Auto-generated method stub
		List<Salp> salps = getSalps();
		List<Cliente> clientes = new ServicioCliente().getClientes();
		//buscar codigo el ultimo codigo Asignado 
		if(salp.getId() == null )
		{
			int i = 0;
			for(Cliente cliente : clientes)
			{
				if(cliente.getId()> i )
				{
					i = cliente.getId();
				}
			}
			
			Cliente cliente = new Cliente();
			
			cliente.setId(salp.getId());
			cliente.setRif(salp.getRif());
			cliente.setDireccion(salp.getDireccion());
			cliente.setTelefono(salp.getTelefono());
			cliente.setRazonsocial(salp.getRazonsocial());
			cliente.setRuta(salp.getRuta());
			
			new ClienteDAO().guardar(cliente);
			salp.setId(i+1);
			salps.add(salp);
		}
		else
		{
			for(Salp salp1 :salps)
			{
				if(salp1.getId().equals(salp.getId()))
				{
					Cliente cliente = new Cliente();
					
					cliente.setId(salp.getId());
					cliente.setRif(salp.getRif());
					cliente.setDireccion(salp.getDireccion());
					cliente.setTelefono(salp.getTelefono());
					cliente.setRazonsocial(salp.getRazonsocial());
					cliente.setRuta(salp.getRuta());
					
					new ClienteDAO().guardar(cliente);
					// aca los cambio del salp
					
				}
				
			}
		}
		guardarTodo(salps);
	}

	@Override
	public void eliminar(Salp salp) throws Exception {
		// TODO Auto-generated method stub		
		List<Salp> salps = getSalps();
		for(Salp salp1 :salps)
		{
			if(salp1.getId().equals(salp.getId()))
			{
				salps.remove(salp1);
				break;
			}
		}
		///guardar Todo 
		guardarTodo(salps);
	}

	@Override
	public Salp getSalp(Integer id) throws Exception {
		// TODO Auto-generated method stub
		List<Salp> salps = getSalps();
		for(Salp salp1 :salps)
		{
			if(salp1.getId().equals(id))
			{
				return salp1;
			}
		}
		return null;

	}

	public void guardarTodo(List<Salp> salps ) throws Exception
	{
		FileWriter fw = new FileWriter(ruta);
		for(Salp salp1 :salps)
		{
			fw.append("idCliente:"+(salp1 == null
					? "  ":salp1.getId().toString())+"\n");
		}
		fw.close();
	}

	 */
	/*
 	La Estructura de los Archivos sera la Siguiente 
idCliente:1
idFacturas:1,2
* */
	
} 

