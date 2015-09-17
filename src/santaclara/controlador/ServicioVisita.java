package santaclara.controlador;

import java.io.FileNotFoundException;
import java.util.List;

import santaclara.dao.impl.VisitaDAO;
import santaclara.modelo.Visita;

public class ServicioVisita {

	public List<Visita> getVisitas() throws FileNotFoundException{
		return new VisitaDAO().getVisitas();
	}

}
