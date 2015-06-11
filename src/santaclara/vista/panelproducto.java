package santaclara.vista;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;

public class panelproducto extends JPanel {
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtPrecio;

	/**
	 * Create the panel.
	 */
	public panelproducto() {
		setBackground(Color.GRAY);
		setForeground(Color.GRAY);
		setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192)), "Datos del Producto", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		setLayout(new GridLayout(6, 2, 5, 5));
		
		JLabel lblCodigo = new JLabel("Codigo:");
		lblCodigo.setForeground(Color.WHITE);
		lblCodigo.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		add(lblCodigo);
		
		txtCodigo = new JTextField();
		add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		add(lblNombre);
		
		txtNombre = new JTextField();
		add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblPresentacion = new JLabel("Presentacion:");
		lblPresentacion.setForeground(Color.WHITE);
		lblPresentacion.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		add(lblPresentacion);
		
		JComboBox cmbPresentacion = new JComboBox();
		cmbPresentacion.setBackground(SystemColor.controlHighlight);
		cmbPresentacion.setForeground(Color.BLACK);
		cmbPresentacion.setModel(new DefaultComboBoxModel(new String[] {"Seleccione.."}));
		add(cmbPresentacion);
		
		JLabel lblCapacidad = new JLabel("Capacidad:");
		lblCapacidad.setForeground(Color.WHITE);
		lblCapacidad.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		add(lblCapacidad);
		
		JComboBox cmbCapacidad = new JComboBox();
		cmbCapacidad.setBackground(SystemColor.controlHighlight);
		cmbCapacidad.setModel(new DefaultComboBoxModel(new String[] {"Seleccione.."}));
		add(cmbCapacidad);
		
		JLabel lblSabor = new JLabel("Sabor:");
		lblSabor.setForeground(Color.WHITE);
		lblSabor.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		add(lblSabor);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(SystemColor.controlHighlight);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Seleccione.."}));
		add(comboBox);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBackground(SystemColor.controlHighlight);
		lblPrecio.setForeground(Color.WHITE);
		add(lblPrecio);
		
		txtPrecio = new JTextField();
		add(txtPrecio);
		txtPrecio.setColumns(10);

	}

}
