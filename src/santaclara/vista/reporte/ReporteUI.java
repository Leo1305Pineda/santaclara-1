package santaclara.vista.reporte;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import santaclara.controlador.reportes.ContReporte;
import santaclara.vista.herramientas.VistaGenericaUI;

@SuppressWarnings("serial")
public class ReporteUI extends VistaGenericaUI{

	private ContReporte contReporte;
	
	private JButton btnActualizar;
	
	public ReporteUI(ContReporte contReporte) {
		super();
		// TODO Auto-generated constructor stub
		this.contReporte = contReporte;
		
		dibujarPanelOpciones();
		dibujarPanelTabla();
		
		//estructurar los demas componentes
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setIcon(new ImageIcon("img/gestion/actualizar.png"));
		btnActualizar.addActionListener(contReporte.Actualizar());
		btnActualizar.setBackground(Color.DARK_GRAY);
		btnActualizar.setForeground(Color.WHITE);
		getPnBotones().add(btnActualizar);
	}

	public ContReporte getContReporte() {
		return contReporte;
	}

	public void setContReporte(ContReporte contReporte) {
		this.contReporte = contReporte;
	}

	public JButton getBtnActualizar() {
		return btnActualizar;
	}

	public void setBtnActualizar(JButton btnActualizar) {
		this.btnActualizar = btnActualizar;
	}
}
