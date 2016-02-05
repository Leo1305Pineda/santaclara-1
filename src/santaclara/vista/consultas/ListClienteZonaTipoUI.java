package santaclara.vista.consultas;

import java.awt.SystemColor;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import santaclara.controlador.consultas.ContListClienteTipoZona;

import santaclara.modelo.Cliente;
import santaclara.vista.herramientas.VistaGenericaUI;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
public class ListClienteZonaTipoUI extends VistaGenericaUI{

	private JComboBox<Cliente> 		cmbTipoCliente;
	@SuppressWarnings("rawtypes")
	private JTableBinding   BinClientes;
    private JButton btnAtras;
    private JLabel lblCliente;
    
   
    private JButton btnActualizar;
	
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public ListClienteZonaTipoUI(ContListClienteTipoZona contLisClienteZonaTipo) throws NumberFormatException, IOException {
		super();

		/**********************************************************************************************************************************************************/
		dibujarPanelOpciones();
		dibujarPanelTabla();
	
		cmbTipoCliente = new JComboBox<Cliente>();
		cmbTipoCliente.setModel(new DefaultComboBoxModel(new String[] {"Todos", "Salp", "Domicilio", "Comercio"}));
		cmbTipoCliente.setBackground(SystemColor.controlHighlight);
		
		btnAtras = new JButton("Atras");
		btnAtras.setForeground(Color.WHITE);
		btnAtras.setFont(new Font("Dialog", Font.BOLD, 10));
		btnAtras.setBackground(Color.DARK_GRAY);
		getPnBotones().add(btnAtras);
		
		lblCliente = new JLabel("Tipo Cliente");
		lblCliente.setForeground(Color.WHITE);
		getPnBotones().add(lblCliente);
		
		getPnBotones().add(cmbTipoCliente);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(contLisClienteZonaTipo.Actualizar());
		btnActualizar.setBackground(Color.DARK_GRAY);
		btnActualizar.setForeground(Color.WHITE);
		getPnBotones().add(btnActualizar);
	}


    @SuppressWarnings({ "rawtypes", "unchecked" })
	public void activarBinding(java.util.List listOrder) {
		// TODO Auto-generated method stub
		setTable(new JTable());
		getScrollPanel().setViewportView(getTable());
		BinClientes = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,(java.util.List<Object>) listOrder,getTable());
	    
	    BeanProperty id  = BeanProperty.create("id");
	    BeanProperty rif  = BeanProperty.create("rif");
	    BeanProperty razonsocial = BeanProperty.create("razonsocial");
	    BeanProperty direccion = BeanProperty.create("direccion");
	    BeanProperty telefono = BeanProperty.create("telefono");
	    BeanProperty ruta = BeanProperty.create("ruta.nombre");
	    BeanProperty zona = BeanProperty.create("ruta.zona.descripcion");
	    BeanProperty tipo = BeanProperty.create("tipoStr");
	    

	    BinClientes.addColumnBinding(id).setColumnClass(String.class).setColumnName("Id");
	    BinClientes.addColumnBinding(rif).setColumnClass(String.class).setColumnName("Rif");
	    BinClientes.addColumnBinding(razonsocial).setColumnClass(String.class).setColumnName("Razon Social");
	    BinClientes.addColumnBinding(direccion).setColumnClass(String.class).setColumnName("Direccion");
	    BinClientes.addColumnBinding(telefono).setColumnClass(String.class).setColumnName("Telefono");
	    
	    BinClientes.addColumnBinding(ruta).setColumnClass(String.class).setColumnName("Ruta");
	    BinClientes.addColumnBinding(zona).setColumnClass(String.class).setColumnName("Zona");
        BinClientes.addColumnBinding(tipo).setColumnClass(String.class).setColumnName("Tipo");
    
	    BinClientes.bind();
	}


	public JComboBox<Cliente> getCmbTipoCliente() {
		return cmbTipoCliente;
	}


	public void setCmbTipoCliente(JComboBox<Cliente> cmbTipoCliente) {
		this.cmbTipoCliente = cmbTipoCliente;
	}

	public JButton getBtnAtras() {
		return btnAtras;
	}


	public void setBtnAtras(JButton btnAtras) {
		this.btnAtras = btnAtras;
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
    
    
}


