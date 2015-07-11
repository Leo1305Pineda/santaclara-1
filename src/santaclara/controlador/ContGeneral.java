package santaclara.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Stack;

import javax.swing.JPanel;

public abstract class ContGeneral implements IContGeneral {

	
	private ContPrincipal 	 contPrincipal;
		
	public void dibujar(JPanel vista)
	{
		contPrincipal.agregarPanel(vista);
		this.contPrincipal.getCache().push(vista.getClass().getName());
		
	}
	
	public void qutarVista(){
		contPrincipal.quitarPanel();
		if (!this.contPrincipal.getCache().empty()) this.contPrincipal.getCache().pop();
	}

	public ContPrincipal getContPrincipal() {
		return contPrincipal;
	}

	public void setContPrincipal(ContPrincipal contPrincipal) {
		this.contPrincipal = contPrincipal;
	}	
	
	public void ActivarAtras() {
		
		this.contPrincipal.ActivarAtras();
	}

	public Stack<String> getCache() {
		return this.contPrincipal.getCache();
	}

	public void setCache(Stack<String> cache) {
		this.contPrincipal.setCache(cache);
		
	}
	
}
