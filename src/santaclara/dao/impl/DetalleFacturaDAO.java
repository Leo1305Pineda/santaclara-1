package santaclara.dao.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import santaclara.Servicio.ServicioEmpaqueProducto;
import santaclara.dao.IDetalleFacturaDAO;
import santaclara.modelo.DetalleFactura;

public class DetalleFacturaDAO extends GenericoDAO implements IDetalleFacturaDAO{
	private String ruta = "archivos/detalleFacturas.txt";
	@Override
	public List<DetalleFactura> getDetalleFacturas() throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		List<DetalleFactura> detalleFacturas = new ArrayList<DetalleFactura>();		
		File file = new File(ruta);
 		Scanner scaner = new Scanner(file);
 		String linea = new String();
		while(scaner.hasNext())
		{
			DetalleFactura detalleFactura = new DetalleFactura();
			linea = scaner.skip("idFactura:").nextLine().trim();
			 if(linea.trim().length() == 0)
			 {
				 detalleFactura.setFactura(null);
			 }
			 else
			 {
				 FacturaDAO facturaDAO = new FacturaDAO();
				 detalleFactura.setFactura(facturaDAO.getFactura(new Integer(linea)));
			 }
			 linea = scaner.skip("idEmpaqueProducto:").nextLine().trim();
			 if(linea.trim().length() == 0)
			 {
				 detalleFactura.setEmpaqueProducto(null);
			 }
			 else
			 {
				 detalleFactura.setEmpaqueProducto(new ServicioEmpaqueProducto().getEmpaqueProducto(new Integer(linea)));
			 }
			 detalleFactura.setCantidad(new Integer(scaner.skip("cantidad:").nextLine().trim()));
			 detalleFactura.setPrecio(new Double(scaner.skip("precio:").nextLine().trim()));
			 detalleFactura.setPrecio(new Double(scaner.skip("desc:").nextLine().trim()));
			 detalleFactura.setIva(new Double(scaner.skip("iva:").nextLine().trim()));
			 detalleFactura.setTotal(new Double(scaner.skip("total:").nextLine().trim()));
			 
			 
			 detalleFacturas.add(detalleFactura); 
		}
		scaner.close();
		return detalleFacturas;

	}
	
	@Override
	public void guardar(DetalleFactura detalleFactura) throws IOException {
		// TODO Auto-generated method stub
		
		List<DetalleFactura> detalleFacturas = getDetalleFacturas();
	
			Boolean enc = new Boolean(false);
			if(detalleFacturas != null)
			{
				for(DetalleFactura detalleFactura1 : detalleFacturas)
				{
					if(detalleFactura1.getFactura().getId().equals(detalleFactura.getFactura().getId()) &&
							detalleFactura1.getEmpaqueProducto().getId().equals(detalleFactura.getEmpaqueProducto().getId()))
					{ 
						detalleFactura1.setCantidad(detalleFactura.getCantidad());
						detalleFactura1.setTotal(detalleFactura.getTotal());
						detalleFactura1.setIva(detalleFactura.getIva()); 
						detalleFactura1.setPrecio(detalleFactura.getPrecio());
						detalleFactura1.setDescuento(detalleFactura.getDescuento());
						enc = true;
						break;
					}
				}
			}
			if(enc==false) detalleFacturas.add(detalleFactura);
			guardarTodo(detalleFacturas);
	}

	@Override
	public void eliminar(DetalleFactura detalleFactura) throws IOException {
		// TODO Auto-generated method stub
		List<DetalleFactura> detalleFacturas = getDetalleFacturas();
		for(DetalleFactura detalleFactura1 :detalleFacturas)
		{
			if(detalleFactura1.getFactura().getId().equals(detalleFactura.getFactura().getId()) 
						&& detalleFactura1.getEmpaqueProducto().getId().equals(detalleFactura.getEmpaqueProducto().getId()) )
			{
				detalleFacturas.remove(detalleFactura1);
				break;
			}
		}
		guardarTodo(detalleFacturas);
	
	}

	@Override
	public DetalleFactura getDetalleFactura(Integer idFactura,Integer idProducto) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		List<DetalleFactura> detalleFacturas = getDetalleFacturas();
		for(DetalleFactura detalleFactura1 :detalleFacturas)
		{
			if(detalleFactura1.getFactura().getId().equals(idFactura) 
						&& detalleFactura1.getEmpaqueProducto().getId().equals(idProducto) )
			{
				return detalleFactura1;
			}
		}
		return null;
	}
	public void guardarTodo(List<DetalleFactura> detalleFacturas ) throws IOException
	{
		FileWriter fw = new FileWriter(ruta);
		for(DetalleFactura detalleFactura :detalleFacturas)
		{
			fw.append("idFactura:"+(detalleFactura.getFactura() == null ? "  ":detalleFactura.getFactura().getId())+"\n");
			fw.append("idEmpaqueProducto:"+(detalleFactura.getEmpaqueProducto() == null ? "  ":detalleFactura.getEmpaqueProducto().getId())+"\n");
			fw.append("cantidad:"+detalleFactura.getCantidad().toString()+"\n");
			fw.append("precio:"+detalleFactura.getPrecio().toString()+"\n");
			fw.append("desc:"+detalleFactura.getPrecio().toString()+"\n");
			fw.append("iva:"+detalleFactura.getIva().toString()+"\n");
			fw.append("total:"+detalleFactura.getTotal().toString()+"\n");
		}
		fw.close();
	}
	
/*Estructura
 * idFactura:1
idProducto:1
cantidad:73500
precio:100000
desc:0.0
iva:0.12
total:7350000000
*/
}
