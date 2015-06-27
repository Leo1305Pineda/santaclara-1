package santaclara.controlador;

import javax.swing.JPanel;

public abstract class ContGeneral implements IContGeneral {

	
	private ContPrincipal 	 contPrincipal;
		
	public void dibujar(JPanel vista)
	{
		contPrincipal.agregarPanel(vista);
	}

	public ContPrincipal getContPrincipal() {
		return contPrincipal;
	}

	public void setContPrincipal(ContPrincipal contPrincipal) {
		this.contPrincipal = contPrincipal;
	}
	
}
