package santaclara.dao.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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

	@SuppressWarnings("deprecation")
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
			 factura.setFecha(new Date(scaner.skip("fecha:").nextLine().trim()));
			 factura.setTotal(new Double(scaner.skip("total:").nextLine().trim()));
			 factura.setSaldo(new Double(scaner.skip("saldo:").nextLine().trim()));
			 factura.setIva(new Double(scaner.skip("iva:").nextLine().trim()));
			 factura.setDescuento(new Double(scaner.skip("descuento:").nextLine().trim()));
			 
			 ClienteDAO clienteDAO = new ClienteDAO();
			 factura.setCliente(
					 clienteDAO.getCliente(
							 new Integer(scaner.skip("idCliente:").nextLine().trim())));
			 
			 VendedorDAO vendedorDAO = new VendedorDAO();
			 factura.setVendedor(
					 (vendedorDAO.getVendedor(
							 new Integer(scaner.skip("idVendedor:").nextLine().trim()))));
			 
			 facturas.add(factura);
		}
		scaner.close();
		return facturas;
	}

	@Override
	public void guardar(Factura factura) throws IOException {
		// TODO Auto-generated method stub
		List<Factura> facturas = getFacturas();
		//buscar codigo el ultimo codigo Asignado 
		if(factura.getId() == null )
		{
			int i = 0;
			for(Factura factura1 : facturas)
			{
				if(factura1.getId()> i )
				{
					i = factura1.getId();
				}
			}
			factura.setId(i+1);
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
		return new Factura();

	}
	
	public void guardarTodo(List<Factura> facturas ) throws IOException
	{
		FileWriter fw = new FileWriter(ruta);
		for(Factura factura :facturas)
		{
			fw.append("id:"+factura.getId().toString()+"\n");
			fw.append("fecha:"+(factura.getFecha().getYear()+"/"+factura.getFecha().getMonth()+"/"+factura.getFecha().getDay())+"\n");
			fw.append("total:"+factura.getTotal().toString()+"\n");
			fw.append("saldo:"+factura.getSaldo().toString()+"\n");
			fw.append("iva:"+factura.getIva().toString()+"\n");
			fw.append("descuento:"+factura.getDescuento().toString()+"\n");
			fw.append("idCliente:"+(factura.getCliente() == null ? "  ":factura.getCliente().getId().toString())+"\n");
			fw.append("idVendedor:"+(factura.getVendedor() == null ? "  ":factura.getVendedor().getId().toString())+"\n");
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
idVendedor:2*/
}
