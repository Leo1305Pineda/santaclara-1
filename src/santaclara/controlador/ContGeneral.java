package santaclara.controlador;

import java.util.Stack;

import javax.swing.JPanel;

public abstract class ContGeneral implements IContGeneral {

	
	private ContPrincipal 	 contPrincipal;
		
	public void dibujar(JPanel vista)
	{
		contPrincipal.agregarPanel(vista);
		this.contPrincipal.getCache().push(vista.getClass().getName());
	}
	
	public void qutarVista(){//btnSalir
		contPrincipal.quitarPanel();
		if (!this.contPrincipal.getCache().empty())
		{
			this.contPrincipal.getCache().clear();
			this.contPrincipal.getCache().push("santaclara.IniciarSesionUI");
		}
		
	}
	
	public void ActivarAtras() {//btnAtras
		
		if(this.contPrincipal.getCache().size()>1)	this.contPrincipal.ActivarAtras();
		else qutarVista();
	}


	public ContPrincipal getContPrincipal() {
		return contPrincipal;
	}

	public void setContPrincipal(ContPrincipal contPrincipal) {
		this.contPrincipal = contPrincipal;
	}	
	
	public Stack<String> getCache() {
		return this.contPrincipal.getCache();
	}

	public void setCache(Stack<String> cache) {
		this.contPrincipal.setCache(cache);
		
	}
	
}
