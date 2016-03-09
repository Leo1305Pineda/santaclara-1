package santaclara.controlador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;

import santaclara.modelo.DomicilioComercio;
import santaclara.modelo.Ruta;
import santaclara.modelo.Salp;
import santaclara.modelo.Zona;

public class StackControlador implements IContGeneral{

	private List<IContGeneral> controladores; 
	
	public StackControlador() {
		super();
		// TODO Auto-generated constructor stub
		controladores = new ArrayList<IContGeneral>();
	}

	public void addControlador(IContGeneral controlador){
		this.controladores.add(controlador);
	}
	
	public void removeControlador(IContGeneral controlador){
		this.controladores.remove(controlador);
	}
	
	@Override
	public JPanel getVista() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ActivarAtras(Object object) {
		// TODO Auto-generated method stub
	
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object asociar() {
		// TODO Auto-generated method stub
		Boolean enc = false;
		List<Object> auxList = new ArrayList<Object>();
		List<Ruta> rutas = new ArrayList<Ruta>();
		List<Zona> zonas = new ArrayList<Zona>(); 
		List<Salp> salps = new ArrayList<Salp>();
		List<DomicilioComercio> domicilioComercios = new ArrayList<DomicilioComercio>();
				
		for(Iterator<IContGeneral> it = controladores.iterator(); it.hasNext();){
			IContGeneral controlador = it.next();
			switch (((List<Object>)(controlador.asociar())).get(0).getClass().getName().toString()) {
			case "santaclara.modelo.Ruta":
			rutas = (List<Ruta>) controlador.asociar();
				break;
			case "santaclara.modelo.Zona":
				zonas = (List<Zona>) controlador.asociar();
					break;
			case "santaclara.modelo.Salp":
				salps = (List<Salp>) controlador.asociar();
					break;
			case "santaclara.modelo.DomicilioComercio":
				domicilioComercios = (List<DomicilioComercio>) controlador.asociar();
					break;

			default:
				break;
			}
			
			if(!salps.isEmpty())
			{
				for(Salp cliente : salps)
				{
					for(Ruta ruta:rutas)
					{
						for(Zona zona : zonas)
						{
							if(cliente.getRuta().getId().equals(ruta.getId()) && 
									ruta.getZona().getId().equals(zona.getId()))auxList.add(cliente);
							enc=true;
						}
					}
				}
				if(enc==true)return auxList;
			}
			else {
				for(DomicilioComercio cliente : domicilioComercios)
				{
					for(Ruta ruta:rutas)
					{
						for(Zona zona : zonas)
						{
							if(cliente.getRuta().getId().equals(ruta.getId()) && 
									ruta.getZona().getId().equals(zona.getId()))auxList.add(cliente);
							enc=true;
						}
					}
				}
				if(enc==true)return auxList;
			}
			
			for(Ruta ruta:rutas)
			{
				for(Zona zona : zonas)
				{
					if(	ruta.getZona().getId().equals(zona.getId()))auxList.add(ruta);
					enc=true;
				}
			}
			if(enc==true)return auxList;
		}
		return null;

	}

	public List<IContGeneral> getControladores() {
		return this.controladores;
	}

	public void setControladores(List<IContGeneral> controladores) {
		this.controladores = controladores;
	}


}
