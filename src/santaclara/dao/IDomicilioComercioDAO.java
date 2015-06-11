package santaclara.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import santaclara.modelo.DomicilioComercio;

public interface IDomicilioComercioDAO extends IGenericoDAO {

	public List<DomicilioComercio>  getDomicilioComercios() throws FileNotFoundException;

	public void	guardar(DomicilioComercio domicilioComercio) throws IOException;
	
	public void eliminar(DomicilioComercio domicilioComercio) throws   IOException;
	
	public DomicilioComercio getDomicilioComercio(Integer id) throws FileNotFoundException;
		
}	
