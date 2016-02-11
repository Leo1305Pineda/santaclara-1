package santaclara.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import santaclara.modelo.Factura;

public interface IFacturaDAO extends IGenericoDAO {

	public List<Factura>  getFacturas() throws FileNotFoundException;
	
	public List<Factura>  getPedidoFacturados() throws FileNotFoundException;
	
	public List<Factura>  getPedidoPendientes() throws FileNotFoundException;
	
	public List<Factura>  getPedidos() throws FileNotFoundException;

	public void	guardar(Factura factura) throws IOException;
	
	public void eliminar(Factura factura) throws   IOException;
	
	public Factura getFactura(Integer id) throws FileNotFoundException;

	public List<Factura> confGetFactura(Boolean condicion)throws FileNotFoundException;

}
