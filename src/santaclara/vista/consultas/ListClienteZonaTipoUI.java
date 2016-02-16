package santaclara.vista.consultas;

import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import santaclara.controlador.consultas.ContListClienteTipoZona;
import santaclara.modelo.Cliente;
import santaclara.modelo.Zona;
import santaclara.vista.herramientas.VistaGenericaUI;

import java.awt.Color;
import java.awt.Component;
import java.io.IOException;

import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
public class ListClienteZonaTipoUI extends VistaGenericaUI{

	private JComboBox<Cliente> 		cmbTipoCliente;
    private JLabel lblCliente;
    private  JLabel lblZona;
	private JComboBox<Zona> 	cmbZona;    
    private JButton btnActualizar;
	
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public ListClienteZonaTipoUI(ContListClienteTipoZona contLisClienteZonaTipo) throws NumberFormatException, IOException {
		super();

		/**********************************************************************************************************************************************************/
		dibujarPanelOpciones();
		dibujarBotonAtras();
		getBtnAtras().addActionListener(contLisClienteZonaTipo.Atras());
		dibujarPanelTabla();
	
		lblCliente = new JLabel("Tipo Cliente");
		lblCliente.setForeground(Color.WHITE);
		getPnBotones().add(lblCliente);
		
		cmbTipoCliente = new JComboBox<Cliente>();
		cmbTipoCliente.setModel(new DefaultComboBoxModel(new String[] {"Todos", "Salp", "Domicilio", "Comercio"}));
		cmbTipoCliente.setBackground(SystemColor.controlHighlight);
			
		getPnBotones().add(cmbTipoCliente);

		lblZona = new JLabel("Zona");
		lblZona.setForeground(Color.WHITE);
		getPnBotones().add(lblZona,"cell 1 1");
		
		cmbZona = new JComboBox<Zona>();
		cmbZona.setBackground(SystemColor.controlHighlight);
		cmbZona.setRenderer(new ListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {
				// TODO Auto-generated method stub
				Zona zona = (Zona) value;
				if (zona.getId().equals(0)) return new JLabel("Todos");
				return new JLabel(zona.getDescripcion());
			}
		});
		getPnBotones().add(cmbZona,"cell 2 1");	
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setIcon(new ImageIcon("img/gestion/actualizar.png"));
		btnActualizar.addActionListener(contLisClienteZonaTipo.Actualizar());
		btnActualizar.setBackground(Color.DARK_GRAY);
		btnActualizar.setForeground(Color.WHITE);
		getPnBotones().add(btnActualizar);
		
		dibujarBotonSalir();
		getBtnSalir().addActionListener(contLisClienteZonaTipo.Salir());
	}

	public JComboBox<Cliente> getCmbTipoCliente() {
		return cmbTipoCliente;
	}

	public void setCmbTipoCliente(JComboBox<Cliente> cmbTipoCliente) {
		this.cmbTipoCliente = cmbTipoCliente;
	}

	public JLabel getLblCliente() {
		return lblCliente;
	}

	public void setLblCliente(JLabel lblCliente) {
		this.lblCliente = lblCliente;
	}

	public JButton getBtnActualizar() {
		return btnActualizar;
	}

	public void setBtnActualizar(JButton btnActualizar) {
		this.btnActualizar = btnActualizar;
	}

	public JLabel getLblZona() {
		return lblZona;
	}

	public void setLblZona(JLabel lblZona) {
		this.lblZona = lblZona;
	}

	public JComboBox<Zona> getCmbZona() {
		return cmbZona;
	}

	public void setCmbZona(JComboBox<Zona> cmbZona) {
		this.cmbZona = cmbZona;
	}
}


