package santaclara.dao.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import santaclara.Servicio.ServicioEmpaqueProducto;
import santaclara.dao.IDetalleFacturaDAO;
import santaclara.modelo.DetalleFactura;
import santaclara.modelo.Factura;

public class DetalleFacturaDAO extends GenericoDAO implements IDetalleFacturaDAO{
	private String ruta = "archivos/detalleFacturas.txt";
	private List<Factura> facturas;
	@Override
	public List<DetalleFactura> getDetalles() throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		facturas = new FacturaDAO().getPedidoFacturados();
		
		List<DetalleFactura> detalleFacturas = new ArrayList<DetalleFactura>();		
		File file = new File(ruta);
 		Scanner scanner = new Scanner(file);
 		String linea = new String();
		while(scanner.hasNext())
		{
			DetalleFactura detalleFactura = new DetalleFactura();
 
			linea = scanner.skip("idFactura:").nextLine().trim();
			
			 if(linea.trim().length() == 0)	detalleFactura.setFactura(null);
			 else	detalleFactura.setFactura(getFactura(new Integer(linea)));
			 
			 linea = scanner.skip("idEmpaqueProducto:").nextLine().trim();
			 if(linea.trim().length() == 0)	detalleFactura.setEmpaqueProducto(null);
			 else	detalleFactura.setEmpaqueProducto(new ServicioEmpaqueProducto().getEmpaqueProducto(new Integer(linea)));
			 
			 detalleFactura.setCantidad(new Integer(scanner.skip("cantidad:").nextLine().trim()));
			 detalleFactura.setPrecio(new Double(scanner.skip("precio:").nextLine().trim()));
			 detalleFactura.setPrecio(new Double(scanner.skip("desc:").nextLine().trim()));
			 detalleFactura.setIva(new Double(scanner.skip("iva:").nextLine().trim()));
			 detalleFactura.setTotal(new Double(scanner.skip("total:").nextLine().trim()));
			 
			 detalleFacturas.add(detalleFactura); 
		}
		scanner.close();
		return detalleFacturas;

	}
	 
	public List<DetalleFactura> getDetalleFacturados() throws NumberFormatException, IOException {
			// TODO Auto-generated method stub
		/*Funcion que Permite la Validacion de la Busqueda de los Archivos
		 * confGetDetalle(true) 	:  	Facturados
		 */
		return confGetDetalle(true);
		}
	
	public List<DetalleFactura> getDetallePedidos() throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		/*Funcion que Permite la Validacion de la Busqueda de los Archivos
		 * confGetDetalle(null) 	:  	Pedidos
		 */
		return confGetDetalle(null);
	}

	public List<DetalleFactura> getDetallePendientes() throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		/*Funcion que Permite la Validacion de la Busqueda de los Archivos
		 * confGetDetalle(false) 	:  	Pendientes
		 */
		return confGetDetalle(false);
	}

	public List<DetalleFactura> confGetDetalle(Boolean condicion) throws NumberFormatException, IOException{
		/*Variable que Permite la Validacion de la Busqueda de los Archivos
		 * estadoFactura == True 	:  	Facturados
		 */
		if(condicion == true) facturas = new FacturaDAO().getPedidoFacturados();
		else if (condicion == false) facturas = new FacturaDAO().getPedidoPendientes();
		else facturas = new FacturaDAO().getPedidos();
		
		List<DetalleFactura> detalleFacturas = new ArrayList<DetalleFactura>();		
		File file = new File(ruta);
 		Scanner scaner = new Scanner(file);
 		String linea = new String();
		while(scaner.hasNext())
		{
			DetalleFactura detalleFactura = new DetalleFactura();
			
			linea = scaner.skip("idFactura:").nextLine().trim();
			 if(linea.trim().length() == 0)	detalleFactura.setFactura(null);
			 else	detalleFactura.setFactura(getFactura(new Integer(linea)));
			
			 if(detalleFactura.getFactura()!=null && detalleFactura.getFactura().getEstado()==condicion)
			 {
				 linea = scaner.skip("idEmpaqueProducto:").nextLine().trim();
				 if(linea.trim().length() == 0) detalleFactura.setEmpaqueProducto(null);
				 else detalleFactura.setEmpaqueProducto(new ServicioEmpaqueProducto().getEmpaqueProducto(new Integer(linea)));

				 detalleFactura.setCantidad(new Integer(scaner.skip("cantidad:").nextLine().trim()));
				 detalleFactura.setPrecio(new Double(scaner.skip("precio:").nextLine().trim()));
				 detalleFactura.setPrecio(new Double(scaner.skip("desc:").nextLine().trim()));
				 detalleFactura.setIva(new Double(scaner.skip("iva:").nextLine().trim()));
				 detalleFactura.setTotal(new Double(scaner.skip("total:").nextLine().trim()));
				 
				 detalleFacturas.add(detalleFactura); 
			 }
			 else for(int i=0 ; i<6 ; i++) scaner.nextLine();//desecha el resto
			 
		}
		scaner.close();
		return detalleFacturas;

	}
	
	public Factura getFactura(Integer id) throws FileNotFoundException {
		// TODO Auto-generated method stub
	
			for(Factura factura1 :facturas)
			{
				if(factura1.getId().equals(id))
				{
					return factura1;
				}
			}
	
		return null;

	}
	
	@Override
	//Guarda y Permite modificarel pedido
	public void guardar(List<DetalleFactura> detalleFacturas) throws IOException {
		// TODO Auto-generated method stub
		
		List<DetalleFactura> detalleFacturasAux = getDetalles();
		
		for(DetalleFactura detalleFactura : detalleFacturas) detalleFacturasAux.add(detalleFactura); 
	
		guardarTodo(detalleFacturasAux);
 
	}

	@Override
	public void eliminar(DetalleFactura detalleFactura) throws IOException {
		// TODO Auto-generated method stub
		List<DetalleFactura> detalleFacturas = getDetallePedidos();
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
		List<DetalleFactura> detalleFacturas = getDetalles();
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
