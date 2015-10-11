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
	public List<Factura> getFacturas() throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<Factura> facturas = new ArrayList<Factura>();
		File file = new File(ruta);
 		Scanner scaner = new Scanner(file);
		while(scaner.hasNext())
		{
			 Factura factura = new Factura();
			 factura.setId(new Integer(scaner.skip("id:").nextLine().trim()));
			 
			 Date fecha = new Date();
				try {
					fecha = new SimpleDateFormat("dd/MM/yyyy").parse(scaner.skip("fecha:").nextLine().toString().trim());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				factura.setFecha(fecha);
			 
			 factura.setTotal(new Double(scaner.skip("total:").nextLine().trim()));
			 factura.setSaldo(new Double(scaner.skip("saldo:").nextLine().trim()));
			 factura.setIva(new Double(scaner.skip("iva:").nextLine().trim()));
			 factura.setDescuento(new Double(scaner.skip("descuento:").nextLine().trim()));			 
			 ClienteDAO clienteDAO = new ClienteDAO();
			 factura.setCliente(
					 clienteDAO.getCliente(
							 new Integer(scaner.skip("idCliente:").nextLine().trim())));
			 
			 UsuarioDAO usuarioDAO = new UsuarioDAO();
			 factura.setVendedor(
					 (usuarioDAO.getUsuario(
							 new Integer(scaner.skip("idVendedor:").nextLine().trim()))));
			 
			 AlmacenDAO almacenDAO = new AlmacenDAO();

			 factura.setAlmacen(
					(almacenDAO.getAlmacen(
							new Integer(scaner.skip("idAlmacen:").nextLine().trim())))); 
/*
 * Si el Estado de la Factura es Facturado o true : Es porque la Operacion Fue de contado
 * si no si el Estado de la Factura es Pendiente false : Es porque la operacon fue a credito
 * si no el estado es de la Factura es pedido o null : Es porque no se a Facturado esta en pedido   
 * */			 
			switch (scaner.skip("estado:").nextLine().toString().trim()) {
			case "Facturado":factura.setEstado(true);	
				break;
			case "Pendiente":factura.setEstado(false);
				break;
			case "Pedido":factura.setEstado(null);
				break;

			default:factura.setEstado(false);
				break;
			}
 
			
			 facturas.add(factura);
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
					factura1.setFecha(factura.getFecha());
					factura1.setTotal(factura.getTotal());
					factura1.setSaldo(factura.getSaldo());
					factura1.setIva(factura.getIva());
					factura1.setDescuento(factura.getDescuento());
					factura1.setCliente(factura.getCliente());
					factura1.setVendedor(factura.getVendedor());
					factura1.setAlmacen(factura.getAlmacen());
					factura1.setEstado(factura.getEstado());
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
		List<Factura> facturas = getFacturas();
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
			fw.append("fecha:"+(factura.getFechaStr()+"\n"));
			fw.append("total:"+factura.getTotal().toString()+"\n");
			fw.append("saldo:"+factura.getSaldo().toString()+"\n");
			fw.append("iva:"+factura.getIva().toString()+"\n");
			fw.append("descuento:"+factura.getDescuento().toString()+"\n");
			fw.append("idCliente:"+(factura.getCliente().getId().toString())+"\n");
			fw.append("idVendedor:"+(factura.getVendedor().getId().toString())+"\n");
			fw.append("idAlmacen:"+(factura.getAlmacen().getId().toString() )+"\n");
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
			
		}
		fw.close();
	}

/*
 * id:0
fecha:06/06/2015
total:7350000000
saldo:88200000
iva:7261800000
descuento:0
idCliente:2
idVendedor:2
idAlmacen:1
estado:Pedido*/
	
}
