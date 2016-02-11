package santaclara.dao.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import santaclara.dao.IFacturaDAO;
import santaclara.modelo.Factura;

public class FacturaDAO extends GenericoDAO implements IFacturaDAO {
	private String ruta = "archivos/facturas.txt";
	
	public FacturaDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FacturaDAO(String ruta) {
		super();
		this.ruta = ruta;
	}

	@Override
	public List<Factura> getFacturas() throws FileNotFoundException{
		// TODO Auto-generated method stub
		List<Factura> facturas = new ArrayList<Factura>();
		File file = new File(ruta);
 		Scanner scanner = new Scanner(file);
		while(scanner.hasNext())
		{
			 Factura factura = new Factura();
 
			 factura.setId(new Integer(scanner.skip("id:").nextLine().toString().trim()));
			 
			 switch (scanner.skip("estado:").nextLine().toString().trim()) {
			 case "Facturado":factura.setEstado(true);	
			 break;
			 case "Pendiente":factura.setEstado(false);
			 break;
			 case "Pedido":factura.setEstado(null);
			 break;
			 default:factura.setEstado(null);
			 break;
			 }
 
			 
			 Date fecha = new Date();
				try {
					fecha = new SimpleDateFormat("dd/MM/yyyy").parse(scanner.skip("fecha:").nextLine().toString().trim());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				factura.setFecha(fecha);
 
				
				ClienteDAO clienteDAO = new ClienteDAO();
				 factura.setCliente(
						 clienteDAO.getCliente(
								 new Integer(scanner.skip("idCliente:").nextLine().toString().trim())));
				 
				 UsuarioDAO usuarioDAO = new UsuarioDAO();
				 factura.setVendedor(
						 (usuarioDAO.getUsuario(
								 new Integer(scanner.skip("idVendedor:").nextLine().toString().trim()))));
				 
				 AlmacenDAO almacenDAO = new AlmacenDAO();

				 factura.setAlmacen(
						(almacenDAO.getAlmacen(
								new Integer(scanner.skip("idAlmacen:").nextLine().toString().trim())))); 
				 
				 factura.setSubTotalExento(new Double(scanner.skip("subTotalExento:").nextLine().toString().trim()));
				 factura.setSubTotalGravado(new Double(scanner.skip("subTotalGravado:").nextLine().toString().trim()));
				 factura.setDescuento(new Double(scanner.skip("desc%:").nextLine().toString().trim()));
				 factura.setIvaSobreBs(new Double(scanner.skip("ivaSobreBs:").nextLine().toString().trim()));
				 factura.setIva(new Double(scanner.skip("iva:").nextLine().toString().trim()));
				 factura.setTotalAPagar(new Double(scanner.skip("totalAPagar:").nextLine().toString().trim()));
	 
			 facturas.add(factura);
		}
		scanner.close();
		return facturas;
	}
 
	public List<Factura> getPedidoFacturados()throws FileNotFoundException{
		return confGetFactura(true);
	}
	
	public List<Factura> getPedidoPendientes()throws FileNotFoundException{
		return confGetFactura(false);
	}
	
	public List<Factura> getPedidos()throws FileNotFoundException{
		return confGetFactura(null);
	}
	
	@Override
	// Permite retornar Datos selectivos
	public List<Factura> confGetFactura(Boolean condicion) throws FileNotFoundException {
		/*Variable que Permite la Validacion de la Busqueda de los Archivos
		 * estado == True 	:  	Facturados
		 * estado == false  :   Pendientes
		 * estado == null   :   Pedido
		 */
		List<Factura> facturas = new ArrayList<Factura>();
		File file = new File(ruta);
 		Scanner scaner = new Scanner(file);
		while(scaner.hasNext())
		{
			 Factura factura = new Factura();
			 factura.setId(new Integer(scaner.skip("id:").nextLine().toString().trim()));
			 
			 switch (scaner.skip("estado:").nextLine().toString().trim()) {
			 case "Facturado":factura.setEstado(true);	
			 break;
			 case "Pendiente":factura.setEstado(false);
			 break;
			 case "Pedido":factura.setEstado(null);
			 break;
			 default:factura.setEstado(null);
			 break;
			 }
			 	if(factura.getEstado()!=null && factura.getEstado()==condicion)
				{
					 Date fecha = new Date();
						try {
							fecha = new SimpleDateFormat("dd/MM/yyyy").parse(scaner.skip("fecha:").nextLine().toString().trim());
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						factura.setFecha(fecha);
					
					ClienteDAO clienteDAO = new ClienteDAO();
					 factura.setCliente(
							 clienteDAO.getCliente(
									 new Integer(scaner.skip("idCliente:").nextLine().toString().trim())));
					 
					 UsuarioDAO usuarioDAO = new UsuarioDAO();
					 factura.setVendedor(
							 (usuarioDAO.getUsuario(
									 new Integer(scaner.skip("idVendedor:").nextLine().toString().trim()))));
					 
					 AlmacenDAO almacenDAO = new AlmacenDAO();

					 factura.setAlmacen(
							(almacenDAO.getAlmacen(
									new Integer(scaner.skip("idAlmacen:").nextLine().toString().trim()))));
					 
					 factura.setSubTotalExento(new Double(scaner.skip("subTotalExento:").nextLine().toString().trim()));
					 factura.setSubTotalGravado(new Double(scaner.skip("subTotalGravado:").nextLine().toString().trim()));
					 factura.setDescuento(new Double(scaner.skip("desc%:").nextLine().toString().trim()));
					 factura.setIvaSobreBs(new Double(scaner.skip("ivaSobreBs:").nextLine().toString().trim()));
					 factura.setIva(new Double(scaner.skip("iva:").nextLine().toString().trim()));
					 factura.setTotalAPagar(new Double(scaner.skip("totalAPagar:").nextLine().toString().trim()));
					 facturas.add(factura);
				}	
				else
				{
					for(int i=0 ; i<10 ; i++) 
					{
						scaner.nextLine();
					}
				}
		}
		scaner.close();
		return facturas;
	}

	
	public Integer ultimaFactura() throws FileNotFoundException{
		int i = 0;
		for(Factura factura1 : getFacturas())
		{
			if(factura1.getId()> i )
			{
				i = factura1.getId();
			}
		}
		return i;
	}
	
	@Override
	public void guardar(Factura factura) throws IOException {
		// TODO Auto-generated method stub
		List<Factura> facturas = getFacturas();
		//buscar codigo el ultimo codigo Asignado 
		if(factura.getId() == null )
		{
			factura.setId(ultimaFactura()+1);
			facturas.add(factura);
		}
		else
		{
			for(Factura factura1 :facturas)
			{
				if(factura1.getId().equals(factura.getId()))
				{ 
					factura1.setEstado(factura.getEstado());
					factura1.setFecha(factura.getFecha());
					factura1.setCliente(factura.getCliente());
					factura1.setVendedor(factura.getVendedor());
					factura1.setAlmacen(factura.getAlmacen());
 
					
					factura1.setSubTotalExento(factura.getSubTotalExento());
					factura1.setSubTotalGravado(factura.getSubTotalGravado());
					factura1.setDescuento(factura.getDescuento());
					factura1.setIvaSobreBs(factura.getIvaSobreBs());
					factura1.setIva(factura.getIva());
					factura1.setTotalAPagar(factura.getTotalAPagar());
				}
			}
		}
		guardarTodo(facturas);
	}

	@Override
	public void eliminar(Factura factura) throws IOException {
		// TODO Auto-generated method stub
		List<Factura> facturas = getFacturas();
		for(Factura factura1 :facturas)
		{
			if(factura1.getId().equals(factura.getId()))
			{
				facturas.remove(factura1);
				break;
			}
		}
		guardarTodo(facturas);
	}

	@Override
	public Factura getFactura(Integer id) throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<Factura> facturas = getPedidoFacturados();
		for(Factura factura1 :facturas)
		{
			if(factura1.getId().equals(id))
			{
				return factura1;
			}
		}
		return null;

	}
	
	public void guardarTodo(List<Factura> facturas ) throws IOException
	{
		FileWriter fw = new FileWriter(ruta);
		for(Factura factura :facturas)
		{
			fw.append("id:"+factura.getId().toString()+"\n");
 
			if(factura.getEstado()!=null)
			{
				if(factura.getEstado()==true)
				{
					fw.append("estado:Facturado\n");
				}
				else if(factura.getEstado()==false)
				{
					fw.append("estado:Pendiente\n");
				}	
			}
			else
			{
				fw.append("estado:Pedido\n");	
			} 
			fw.append("fecha:"+(factura.getFechaStr()+"\n"));
			fw.append("idCliente:"+(factura.getCliente().getId().toString())+"\n");
			fw.append("idVendedor:"+(factura.getVendedor().getId().toString())+"\n");
			fw.append("idAlmacen:"+(factura.getAlmacen().getId().toString() )+"\n");
 
			fw.append("subTotalExento:"+factura.getSubTotalExento().toString().trim()+"\n");
			fw.append("subTotalGravado:"+factura.getSubTotalGravado().toString().trim()+"\n");
			fw.append("desc%:"+factura.getDescuento().toString().trim()+"\n");
			fw.append("ivaSobreBs:"+factura.getIvaSobreBs().toString().trim()+"\n");
			fw.append("iva:"+factura.getIva().toString().trim()+"\n");
			fw.append("totalAPagar:"+factura.getTotalAPagar().toString().trim()+"\n");
			
		}
		fw.close();
	}

/*
 * id:0 
 * estado:Pedido 
 * fecha:06/06/2015
 * idCliente:2
 * idVendedor:2
 * idAlmacen:1
 
 * subTotalExento:0
 * subTotalGravado:100
 * desc%:0
 * ivaSobreBs:100
 * iva:12
 * tatalAPagar:120
 */
	
}
