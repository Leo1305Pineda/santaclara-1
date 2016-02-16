package santaclara.vista.consultas;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import santaclara.controlador.consultas.ContConsulta;
import santaclara.vista.herramientas.VistaGenericaUI;

@SuppressWarnings("serial")
public class ConsultaUI extends VistaGenericaUI{
	private ContConsulta contConsulta;
	
	private JButton btnActualizar;
	
	//constructor de la clase
	public ConsultaUI(ContConsulta contConsulta){
		this.contConsulta = contConsulta;
		dibujarPanelOpciones();
		dibujarPanelTabla();
		
		//estructurar los demas componentes
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setIcon(new ImageIcon("img/gestion/actualizar.png"));
		btnActualizar.addActionListener(contConsulta.Actualizar());
		btnActualizar.setBackground(Color.DARK_GRAY);
		btnActualizar.setForeground(Color.WHITE);
		getPnBotones().add(btnActualizar);
	}

	public ContConsulta getContConsulta() {
		return contConsulta;
	}

	public void setContConsulta(ContConsulta contConsulta) {
		this.contConsulta = contConsulta;
	}

	public JButton getBtnActualizar() {
		return btnActualizar;
	}

	public void setBtnActualizar(JButton btnActualizar) {
		this.btnActualizar = btnActualizar;
	}

}
