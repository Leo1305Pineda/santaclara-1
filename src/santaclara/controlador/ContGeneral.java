package santaclara.controlador;

import java.util.Stack;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public abstract class ContGeneral implements IContGeneral {

	
	private ContPrincipal 	 contPrincipal;
		
	public void dibujar(JPanel vista,Object controladorNuevo)
	{
		contPrincipal.agregarPanel(vista);
		if(!this.contPrincipal.getCacheObjet().empty())
		{
			Object actual = this.contPrincipal.getCacheObjet().pop();
			if (actual.equals(controladorNuevo))
			{
				this.contPrincipal.getCacheObjet().push(actual);
			}
			else
			{
				this.contPrincipal.getCacheObjet().push(actual);
				this.contPrincipal.getCacheObjet().push(controladorNuevo);
			}
		}
		else 
			this.contPrincipal.getCacheObjet().push(controladorNuevo);
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
	 
	
	
	
	public void ActivarAtras(Object mensaje) {//btnAtras
		try {
			if(this.contPrincipal.getCacheObjet().size()>1)
				this.contPrincipal.ActivarAtras(mensaje);
			else
				quitarVista();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
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
