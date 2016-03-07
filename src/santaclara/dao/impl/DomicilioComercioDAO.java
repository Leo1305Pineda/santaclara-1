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

import santaclara.dao.IDomicilioComercioDAO;
import santaclara.modelo.DomicilioComercio;

public class DomicilioComercioDAO extends GenericoDAO implements  IDomicilioComercioDAO{

	public DomicilioComercioDAO(){
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
	public List<DomicilioComercio> getDomicilioComercios() throws Exception {
		// TODO Auto-generated method stub
		List<DomicilioComercio> domicilioComercios = new ArrayList<DomicilioComercio>();
		try {	
		ResultSet rSet = getConexion().getSelect(
				"SELECT idcliente, tipo, diavisita FROM domiciliocomercio Order by idcliente;"); 
		
		if(rSet==null || rSet.getFetchSize()!=0) return null;	
			while(rSet.next())domicilioComercios.add(
					new DomicilioComercio(new ClienteDAO().getCliente(rSet.getInt("idcliente")),rSet.getString("tipo"),rSet.getInt("diavisita"))); 
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
		
		return domicilioComercios;
	}
	
	@Override
	public void guardar(DomicilioComercio domociliocomercio) throws Exception {
		// TODO Auto-generated method stub
		if (domociliocomercio.getId()==null){
			try {
				getConexion().ejecutar(
						"  BEGIN;"
						+ ""
						+" INSERT INTO clientes(rif, razonsocial,direccion, telefono,idruta)"
						+" VALUES ("
						+" '" +domociliocomercio.getRif()			+"', " 
						+" '" +domociliocomercio.getRazonsocial()	+"', "
						+" '" +domociliocomercio.getDireccion()		+"', "
						+" '" +domociliocomercio.getTelefono()		+"', "
						+"  " +domociliocomercio.getRuta().getId()	+"   "
						+ ");"
						+ "                                  "
						+ "INSERT INTO domiciliocomercio(idcliente,tipo,diavisita) "
						+ " VALUES ((SELECT max(id) FROM clientes),"
						+ " '"+ domociliocomercio.getTipo()      +"',"
						+ "  "+ domociliocomercio.getDiaVisita() +" "
						+ "); "
						+ "                                                                 "
						+ " COMMIT;");
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
				
		}
		else{
			getConexion().ejecutar(
					" BEGIN;"
					+ "     "
					+ "UPDATE clientes SET   "
					+" rif         ='" +domociliocomercio.getRif() 			+"', "
					+" razonsocial ='" +domociliocomercio.getRazonsocial() 	+"', "
					+" direccion   ='" +domociliocomercio.getDireccion()	+"', "
					+" telefono    ='" +domociliocomercio.getTelefono() 	+"', "
					+" idruta      = " +domociliocomercio.getRuta().getId() +"  "
					+" WHERE    id = " +domociliocomercio.getId()+";"
					+"               "
					+"UPDATE domiciliocomercio SET     " 
					+" tipo ='" +domociliocomercio.getTipo()         		+"', "
					+" diavisita = " +domociliocomercio.getDiaVisita()   	+" "
					+" WHERE    idcliente = " +domociliocomercio.getId() 	+" "
					+ "; "
					+ "                                                                 "
					+ " COMMIT;");
		}
	}

	@Override
	public void eliminar(DomicilioComercio domiciliocomercio) throws Exception {
		// TODO Auto-generated method stub
		if(domiciliocomercio!=null) getConexion().ejecutar(
								"  DELETE FROM domiciliocomercio "
								+" WHERE idcliente = "+domiciliocomercio.getId() +" ;");
	}

	@Override
	public DomicilioComercio getDomicilioComercio(Integer id) throws Exception {
		// TODO Auto-generated method stub
		
	List<DomicilioComercio> domicilioComercios = getDomicilioComercios();
	for(DomicilioComercio domicilioComercio : domicilioComercios)
	{
		if(domicilioComercio.getId().equals(id))return domicilioComercio;
	}
	return null;
	}

	/**	
	 * 
	 private String ruta = "archivos/domicilioComercio.txt";

	@Override
	public List<DomicilioComercio> getDomicilioComercios() throws Exception {
		// TODO Auto-generated method stub
		List<DomicilioComercio> domicilioComercios = new ArrayList<DomicilioComercio>();
		ClienteDAO clienteDAO = new ClienteDAO();
		File file = new File(ruta);
		Scanner scanner = new Scanner(file);
		while(scanner.hasNext())
		{
			 DomicilioComercio domicilioComercio = new DomicilioComercio();
			 domicilioComercio.setId(new Integer(scanner.skip("idCliente:").nextLine()));
			 domicilioComercio.setTipo(scanner.skip("tipo:").nextLine().toString().trim());
			 domicilioComercio.setDiaVisita(new Integer(scanner.skip("diaVisita:").nextLine().trim()));
			 domicilioComercios.add(domicilioComercio);
		}
		//Cargo la info de Cliente
		List<Cliente> clientes;
		clientes = clienteDAO.getClientes();
		for(DomicilioComercio domicilioComercio1 :domicilioComercios)
		{
			for(Cliente cliente: clientes)
			{
				if(domicilioComercio1.getId().equals(cliente.getId()))
				{
					domicilioComercio1.setRif(cliente.getRif());
					domicilioComercio1.setId(cliente.getId());
					domicilioComercio1.setRazonsocial(cliente.getRazonsocial());
					domicilioComercio1.setDireccion(cliente.getDireccion());
					domicilioComercio1.setTelefono(cliente.getTelefono());
					domicilioComercio1.setRuta(cliente.getRuta());
					break;
				}
			}
		}			  
		scanner.close();
		return domicilioComercios;

	}

	@Override
	public void guardar(DomicilioComercio domicilioComercio) throws Exception {
		// TODO Auto-generated method stub
		List<Cliente> clientes = new ServicioCliente().getClientes();
		List<DomicilioComercio> domicilioComercios = getDomicilioComercios();
		//buscar codigo el ultimo codigo Asignado 
		if(domicilioComercio.getId() == null )
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
			
			cliente.setId(domicilioComercio.getId());
			cliente.setRif(domicilioComercio.getRif());
			cliente.setDireccion(domicilioComercio.getDireccion());
			cliente.setTelefono(domicilioComercio.getTelefono());
			cliente.setRazonsocial(domicilioComercio.getRazonsocial());
			cliente.setRuta(domicilioComercio.getRuta());
			
			new ClienteDAO().guardar(cliente);
			
			domicilioComercio.setId(i+1);
			domicilioComercios.add(domicilioComercio);
		}
		else
		{
			for(DomicilioComercio domicilioComercio1 :domicilioComercios)
			{
				Cliente cliente = new Cliente();
				
				cliente.setId(domicilioComercio.getId());
				cliente.setRif(domicilioComercio.getRif());
				cliente.setDireccion(domicilioComercio.getDireccion());
				cliente.setTelefono(domicilioComercio.getTelefono());
				cliente.setRazonsocial(domicilioComercio.getRazonsocial());
				cliente.setRuta(domicilioComercio.getRuta());
				
				new ClienteDAO().guardar(cliente);
				
				if(domicilioComercio1.getId().equals(domicilioComercio.getId()))
				{ 
					domicilioComercio1.setTipo(domicilioComercio.getTipo());
					domicilioComercio1.setDiaVisita(domicilioComercio.getDiaVisita());
				}
			}
		}
		guardarTodo(domicilioComercios);
 
	}

	@Override
	public void eliminar(DomicilioComercio domicilioComercio) throws Exception {
		// TODO Auto-generated method stub
		List<DomicilioComercio> domicilioComercios = getDomicilioComercios();
		for(DomicilioComercio domiciliocomercio1 :domicilioComercios)
		{
			if(domiciliocomercio1.getId().equals(domicilioComercio.getId()))
			{
				domicilioComercios.remove(domiciliocomercio1);
				break;
			}
		}
		guardarTodo(domicilioComercios);
	}

	@Override
	public DomicilioComercio getDomicilioComercio(Integer id) throws Exception {
		// TODO Auto-generated method stub
		List<DomicilioComercio> domicilioComercios = getDomicilioComercios();
		for(DomicilioComercio domicilioComercio1 :domicilioComercios)
		{
			if(domicilioComercio1.getId().equals(id))
			{
				return domicilioComercio1;
			}
		}
		return null;

	}

	public void guardarTodo(List<DomicilioComercio> domicilioComercios ) throws Exception
	{
		FileWriter fw = new FileWriter(ruta);
		for(DomicilioComercio domicilioComercio :domicilioComercios)
		{
			fw.append("idCliente:"+(domicilioComercio == null 
					? "  ":domicilioComercio.getId().toString())+"\n");
			
			fw.append("tipo:"+domicilioComercio.getTipo().toString()+"\n");
			fw.append("diaVisita:"+domicilioComercio.getDiaVisita().toString()+"\n");
			
		}
		fw.close();
	}
	

	 */
	
	/*
 	La Estructura de los Archivos sera la Siguiente 
idCliente:1
tipo:C
diaVisita:37
* */
	
} 

