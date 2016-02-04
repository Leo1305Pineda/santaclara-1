package santaclara.controlador;

import java.util.Stack;

import javax.swing.JPanel;

public abstract class ContGeneral implements IContGeneral {

	
	private ContPrincipal 	 contPrincipal;
		
	public void dibujar(JPanel vista,Object cacheobject)
	{
		contPrincipal.agregarPanel(vista);
		if(!this.contPrincipal.getCacheObjet().empty())
		{
			Object object = this.contPrincipal.getCacheObjet().pop();
			if (object.equals(cacheobject))
			{
				this.contPrincipal.getCacheObjet().push(object);
			}
			else
			{
				this.contPrincipal.getCacheObjet().push(object);
				this.contPrincipal.getCacheObjet().push(cacheobject);
			}
		}
		else 
			this.contPrincipal.getCacheObjet().push(cacheobject);
	}
	
	public void quitarVista(){//btnSalir
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
		
		if(this.contPrincipal.getCacheObjet().size()>1)
			this.contPrincipal.ActivarAtras(object);
		else
			quitarVista();
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
