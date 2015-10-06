package santaclara.controlador;

import java.util.Stack;

import javax.swing.JPanel;

public abstract class ContGeneral implements IContGeneral {

	
	private ContPrincipal 	 contPrincipal;
		
	public void dibujar(JPanel vista)
	{
		contPrincipal.agregarPanel(vista);
		this.contPrincipal.getCacheObjet().push(vista);
	}
	
	public void qutarVista(){//btnSalir
		contPrincipal.quitarPanel();
		
		if (!this.contPrincipal.getCacheObjet().empty())
		{
			while(this.contPrincipal.getCacheObjet().size()>1)
			{
				this.contPrincipal.getCacheObjet().pop();
			}
		}
	}
	
	public void ActivarAtras(Object object) {//btnAtras
		
		if(this.contPrincipal.getCacheObjet().size()>1)	this.contPrincipal.ActivarAtras(object);
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
	
	public Stack<Object> getCacheObjet() {
		return this.contPrincipal.getCacheObjet();
	}

	public void setCacheObjet(Stack<Object> cacheObject) {
		this.contPrincipal.setCacheObjet(cacheObject);
		
	}

}
