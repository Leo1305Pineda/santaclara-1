package santaclara.vista;

import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

import org.jdesktop.swingbinding.JTableBinding;

import santaclara.controlador.ContVisitas;
import santaclara.modelo.Usuario;

import javax.swing.DefaultComboBoxModel;

import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class VisitasUI extends JPanel {
	
	private JFrame frame = new JFrame();
	
	private JPanel pnVisitas;
	private JPanel pnConsultar;
	private JPanel panelVisitaJefeVenta;
	private JPanel pntipoUser;
	private JPanel pnUsuario;
	
	private JButton btnAtras;
	private JButton btnSalir;
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JButton boton = new JButton("+ Visita");
	
	private JComboBox<String> comboTipoUser;
	private JComboBox<Usuario> comboUsuario;
	
	private JCheckBox chckbxEstado;
   
    private JTextField txtMotivo;
    private JTextField txtDescripcion;
	
	private JSpinner txtValVendedor;
	private JSpinner txtValProducto;
	
	private JLabel lblValorproducto;
	private JLabel lblMotivo;
	private JLabel lblValVendedor;
	private JLabel lblFecha;
	private JLabel lblDescripcion;
	
	private JScrollPane scrollPanel;
	
	private JTable table;
	
	@SuppressWarnings("rawtypes")
	private JTableBinding  binVisitas;
	private JTextField txtFecha;
	private JTextField txtCliente;
	private JTextField txtRuta;
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public VisitasUI(ContVisitas contVisitas) {
		
		setFont(new Font("Dialog", Font.BOLD, 13));
		setForeground(Color.WHITE);
		setBackground(Color.DARK_GRAY);
		setSize(895,696);
		setLayout(null);
		
		pnVisitas = new JPanel();
		pnVisitas.setLocation(12, 12);
		pnVisitas.setSize(875,672);
		pnVisitas.setBackground(Color.DARK_GRAY);
		pnVisitas.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Modulo Visita", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		add(pnVisitas);

		scrollPanel = new JScrollPane();
		scrollPanel.setBounds(12, 85, 852, 391);
		table = new JTable();
		table.addMouseListener(contVisitas.ActivarClick());
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		table.setForeground(new Color(51, 51, 51));
		table.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		
		table.setDefaultRenderer(String.class, contVisitas.getTableCellRenderer());
		
		table.setBackground(Color.WHITE);
		scrollPanel.setViewportView(table);
		pnVisitas.setLayout(null);
				
		pnVisitas.add(scrollPanel);
		
		panelVisitaJefeVenta = new JPanel();
		panelVisitaJefeVenta.setBounds(12, 488, 852, 172);
		pnVisitas.add(panelVisitaJefeVenta);
		panelVisitaJefeVenta.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229), 1, true), "Editar Visita Jefe Venta", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		panelVisitaJefeVenta.setBackground(Color.DARK_GRAY);
		panelVisitaJefeVenta.getSize(new Dimension(800, 150));
		panelVisitaJefeVenta.setLayout(null);
		
		lblMotivo = new JLabel("Motivo:");
		lblMotivo.setBounds(12, 62, 85, 25);
		lblMotivo.setForeground(Color.WHITE);
		lblMotivo.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		panelVisitaJefeVenta.add(lblMotivo);

		txtMotivo = new JTextField();
		txtMotivo.setBounds(114, 66, 287, 16);
		panelVisitaJefeVenta.add(txtMotivo);
		txtMotivo.setColumns(10);
				
		lblValVendedor = new JLabel("Valor Vendedor:");
		lblValVendedor.setBounds(12, 99,  116,25);
		lblValVendedor.setBackground(SystemColor.controlHighlight);
		lblValVendedor.setForeground(Color.WHITE);
		panelVisitaJefeVenta.add(lblValVendedor);
				
		txtValVendedor = new JSpinner(new SpinnerNumberModel(0.0,0.00,Double.MAX_VALUE,0.1));
		txtValVendedor.setBounds(129, 103, 69,16);
		((JSpinner.NumberEditor)txtValVendedor.getEditor()).getFormat().setMinimumFractionDigits(2);
		panelVisitaJefeVenta.add(txtValVendedor);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(504, 112, 127, 16);
		panelVisitaJefeVenta.add(btnGuardar);
		btnGuardar.setIcon(new ImageIcon("img/gestion/bien.png"));
		btnGuardar.setBackground(Color.DARK_GRAY);
		btnGuardar.setForeground(Color.WHITE);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(643, 108, 130, 16);
		panelVisitaJefeVenta.add(btnCancelar);
		btnCancelar.setIcon(new ImageIcon("img/gestion/cancel.png"));
		btnCancelar.addActionListener(contVisitas.QuitarFrame());
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBackground(Color.DARK_GRAY);
	
		lblValorproducto = new JLabel("Valor Producto:");
		lblValorproducto.setForeground(Color.WHITE);
		lblValorproducto.setBackground(SystemColor.controlHighlight);
		lblValorproducto.setBounds(217, 99, 116, 25);
		panelVisitaJefeVenta.add(lblValorproducto);
		
		txtValProducto = new JSpinner(new SpinnerNumberModel(1.0,1.0,Double.MAX_VALUE,1.0));
		txtValProducto.setBounds(334, 108, 69, 16);
		panelVisitaJefeVenta.add(txtValProducto);
		
		lblFecha = new JLabel("Fecha:");
		lblFecha.setForeground(Color.WHITE);
		lblFecha.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblFecha.setBounds(12, 19, 69, 25);
		panelVisitaJefeVenta.add(lblFecha);
		
		lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setForeground(Color.WHITE);
		lblDescripcion.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblDescripcion.setBounds(434, 57, 100, 25);
		panelVisitaJefeVenta.add(lblDescripcion);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(536, 61, 287, 16);
		panelVisitaJefeVenta.add(txtDescripcion);
		
		chckbxEstado = new JCheckBox("Estado");
		chckbxEstado.setForeground(Color.WHITE);
		chckbxEstado.setBackground(Color.DARK_GRAY);
		chckbxEstado.setBounds(412, 105, 129, 23);
		panelVisitaJefeVenta.add(chckbxEstado);
		
		txtFecha = new JTextField();
		txtFecha.setColumns(10);
		txtFecha.setBounds(114, 22, 145, 16);
		panelVisitaJefeVenta.add(txtFecha);
		
		txtCliente = new JTextField();
		txtCliente.setColumns(10);
		txtCliente.setBounds(406, 19, 145, 16);
		panelVisitaJefeVenta.add(txtCliente);
		
		txtRuta = new JTextField();
		txtRuta.setColumns(10);
		txtRuta.setBounds(678, 19, 145, 16);
		panelVisitaJefeVenta.add(txtRuta);
		
		pnConsultar = new JPanel();
		pnConsultar.setBounds(11, 18, 853, 68);
		pnVisitas.add(pnConsultar);
		pnConsultar.setBackground(Color.DARK_GRAY);
		pnConsultar.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), "", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(255, 255, 255)));
		pnConsultar.setLayout(null);
		
		btnAtras = new JButton("Atras");
		btnAtras.addActionListener(contVisitas.Atras());
		btnAtras.setIcon(new ImageIcon("img/gestion/AtrasCurva.png"));
		btnAtras.setBounds(12, 17, 92, 16);
		pnConsultar.add(btnAtras);
		btnAtras.setForeground(Color.WHITE);
		btnAtras.setBackground(Color.DARK_GRAY);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(contVisitas.salir());
		btnSalir.setIcon(new ImageIcon("img/gestion/SalirCurva.png"));
		btnSalir.setBounds(751, 17, 86, 16);
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(Color.DARK_GRAY);
		pnConsultar.add(btnSalir);
		
		pntipoUser = new JPanel();
		pntipoUser.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229), 1, true), "tipo de Usuario", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pntipoUser.setBackground(Color.DARK_GRAY);
		pntipoUser.setBounds(116, 10, 260, 45);
		pnConsultar.add(pntipoUser);
		pntipoUser.setLayout(null);
		
		comboTipoUser = new JComboBox<String>();
		comboTipoUser.addActionListener(contVisitas.ActivarComboTipo());
		comboTipoUser.setBounds(5, 15, 250, 25);
		comboTipoUser.setModel(new DefaultComboBoxModel(new String[] {"JefeVenta", "Concesionario"}));
		comboTipoUser.setForeground(Color.BLACK);
		comboTipoUser.setBackground(SystemColor.controlHighlight);
		pntipoUser.add(comboTipoUser);
		
		pnUsuario = new JPanel();
		pnUsuario.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229), 1, true), "idUsuario  |Nombre   ", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnUsuario.setBackground(Color.DARK_GRAY);
		pnUsuario.setBounds(376, 10, 260, 45);
		pnConsultar.add(pnUsuario);
		pnUsuario.setLayout(null);
		
		comboUsuario = new JComboBox<Usuario>();
		comboUsuario.addActionListener(contVisitas.ActivarComboUsuario());
		comboUsuario.setBounds(5, 15, 250, 25);
		comboUsuario.setForeground(Color.BLACK);
		comboUsuario.setRenderer(contVisitas.setRendererComboUsuario());
		
		comboUsuario.setBackground(SystemColor.controlHighlight);
		pnUsuario.add(comboUsuario);
		btnGuardar.addActionListener(contVisitas.guardar());
	}

	public JPanel getPnVisitas() {
		return pnVisitas;
	}

	public void setPnVisitas(JPanel pnVisitas) {
		this.pnVisitas = pnVisitas;
	}

	public JPanel getPnConsultar() {
		return pnConsultar;
	}

	public void setPnConsultar(JPanel pnConsultar) {
		this.pnConsultar = pnConsultar;
	}

	public JPanel getPanelVisitaJefeVenta() {
		return panelVisitaJefeVenta;
	}

	public void setPanelVisitaJefeVenta(JPanel panelVisitaJefeVenta) {
		this.panelVisitaJefeVenta = panelVisitaJefeVenta;
	}

	public JPanel getPntipoUser() {
		return pntipoUser;
	}

	public void setPntipoUser(JPanel pntipoUser) {
		this.pntipoUser = pntipoUser;
	}

	public JPanel getPnUsuario() {
		return pnUsuario;
	}

	public void setPnUsuario(JPanel pnUsuario) {
		this.pnUsuario = pnUsuario;
	}

	public JButton getBtnAtras() {
		return btnAtras;
	}

	public void setBtnAtras(JButton btnAtras) {
		this.btnAtras = btnAtras;
	}

	public JButton getBtnSalir() {
		return btnSalir;
	}

	public void setBtnSalir(JButton btnSalir) {
		this.btnSalir = btnSalir;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(JButton btnGuardar) {
		this.btnGuardar = btnGuardar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(JButton btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	public JComboBox<String> getComboTipoUser() {
		return comboTipoUser;
	}

	public void setComboTipoUser(JComboBox<String> comboTipoUser) {
		this.comboTipoUser = comboTipoUser;
	}

	public JComboBox<Usuario> getComboUsuario() {
		return comboUsuario;
	}

	public void setComboUsuario(JComboBox<Usuario> comboUsuario) {
		this.comboUsuario = comboUsuario;
	}

	public JCheckBox getChckbxEstado() {
		return chckbxEstado;
	}

	public void setChckbxEstado(JCheckBox chckbxEstado) {
		this.chckbxEstado = chckbxEstado;
	}

	public JTextField getTxtNombre() {
		return txtMotivo;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtMotivo = txtNombre;
	}

	public JTextField getTxtDescripcion() {
		return txtDescripcion;
	}

	public void setTxtDescripcion(JTextField txtDescripcion) {
		this.txtDescripcion = txtDescripcion;
	}

	public JSpinner getTxtValVendedor() {
		return txtValVendedor;
	}

	public void setTxtValVendedor(JSpinner txtValVendedor) {
		this.txtValVendedor = txtValVendedor;
	}

	public JSpinner getTxtValProducto() {
		return txtValProducto;
	}

	public void setTxtValProducto(JSpinner txtValProducto) {
		this.txtValProducto = txtValProducto;
	}

	public JLabel getLblValorproducto() {
		return lblValorproducto;
	}

	public void setLblValorproducto(JLabel lblValorproducto) {
		this.lblValorproducto = lblValorproducto;
	}

	public JLabel getLblMotivo() {
		return lblMotivo;
	}

	public void setLblMotivo(JLabel lblMotivo) {
		this.lblMotivo = lblMotivo;
	}

	public JLabel getLblValVendedor() {
		return lblValVendedor;
	}

	public void setLblValVendedor(JLabel lblValVendedor) {
		this.lblValVendedor = lblValVendedor;
	}

	public JLabel getLblFecha() {
		return lblFecha;
	}

	public void setLblFecha(JLabel lblFecha) {
		this.lblFecha = lblFecha;
	}

	public JLabel getLblDescripcion() {
		return lblDescripcion;
	}

	public void setLblDescripcion(JLabel lblDescripcion) {
		this.lblDescripcion = lblDescripcion;
	}

	public JScrollPane getScrollPanel() {
		return scrollPanel;
	}

	public void setScrollPanel(JScrollPane scrollPanel) {
		this.scrollPanel = scrollPanel;
	}

	public JTable getTabla() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	@SuppressWarnings("rawtypes")
	public JTableBinding getBinVisitas() {
		return binVisitas;
	}

	@SuppressWarnings("rawtypes")
	public void setBinVisitas(JTableBinding binVisitas) {
		this.binVisitas = binVisitas;
	}
	public void quitarNuevo() {
		// TODO Auto-generated method stub
		panelVisitaJefeVenta.setVisible(false);
		scrollPanel.setVisible(true);
		
	}

	public JButton getBoton() {
		return boton;
	}

	public void setBoton(JButton boton) {
		this.boton = boton;
	}

	public JTable getTable() {
		return table;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JTextField getTxtFecha() {
		return txtFecha;
	}

	public void setTxtFecha(JTextField txtFecha) {
		this.txtFecha = txtFecha;
	}

	public JTextField getTxtCliente() {
		return txtCliente;
	}

	public void setTxtCliente(JTextField txtCliente) {
		this.txtCliente = txtCliente;
	}

	public JTextField getTxtRuta() {
		return txtRuta;
	}

	public void setTxtRuta(JTextField txtRuta) {
		this.txtRuta = txtRuta;
	}

	public JTextField getTxtMotivo() {
		return txtMotivo;
	}

	public void setTxtMotivo(JTextField txtMotivo) {
		this.txtMotivo = txtMotivo;
	}
	
}

