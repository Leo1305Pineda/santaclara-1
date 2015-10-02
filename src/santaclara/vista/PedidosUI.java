package santaclara.vista;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Component;

import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.Box;
import javax.swing.JScrollPane;
import javax.swing.JTable;


@SuppressWarnings("serial")
public class PedidosUI extends JPanel{
	
	private JPanel pnlPedido;
	private JPanel pnlOption;
	private JPanel pnlInfo;
	private JPanel pnlDetalle;
	
	private JButton btnAtras;
	private JButton btnGuardar;
	private JButton btnImprimir;
	private JButton btnCliente;
	private JButton btnAlmacen;
	private JButton btnProducto;
	private JButton btnVendedor;
	private JButton btnCatalogo;
	private JButton btnBuscar;
	private JButton btnGuardarFactura;
	private JButton btnSalir;
	
	private JScrollPane scrollPane;
	
	
	private JTable table;
	
	public PedidosUI() {
		setBounds(0, 0, 800, 700);
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		
		pnlPedido = new JPanel();
		pnlPedido.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 3, true), "Registro de Pedidos", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnlPedido.setBackground(Color.DARK_GRAY);
		pnlPedido.setBounds(0, 0, 800, 700);
		add(pnlPedido);
		pnlPedido.setLayout(null);
		
		pnlOption = new JPanel();
		pnlOption.setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true));
		pnlOption.setToolTipText("Operaciones");
		pnlOption.setBackground(Color.GRAY);
		pnlOption.setBounds(25, 25, 763, 40);
		pnlPedido.add(pnlOption);
		pnlOption.setLayout(null);
		
		btnAtras = new JButton("");
		btnAtras.setBounds(0, 0, 40, 40);
		btnAtras.setIcon(new ImageIcon("/home/leo/git/santaclara/img/gestion/AtrasCurva.png"));
		btnAtras.setForeground(Color.WHITE);
		btnAtras.setBackground(Color.GRAY);
		pnlOption.add(btnAtras);
		
		Component verticalGlue = Box.createVerticalGlue();
		verticalGlue.setForeground(Color.WHITE);
		verticalGlue.setBackground(Color.LIGHT_GRAY);
		verticalGlue.setBounds(42, 0, 12, 38);
		pnlOption.add(verticalGlue);
		
		btnBuscar = new JButton("");
		btnBuscar.setBounds(50, 0, 40, 40);
		btnBuscar.setIcon(new ImageIcon("/home/leo/git/santaclara/img/gestion/buscar.png"));
		btnBuscar.setBackground(Color.GRAY);
		btnBuscar.setForeground(Color.WHITE);
		pnlOption.add(btnBuscar);
		
		btnGuardar = new JButton("");
		btnGuardar.setBounds(95, 0, 40, 40);
		btnGuardar.setIcon(new ImageIcon("/home/leo/git/santaclara/img/gestion/disk.png"));
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setBackground(Color.GRAY);
		pnlOption.add(btnGuardar);
		
		btnImprimir = new JButton("");
		btnImprimir.setBounds(140, 0, 40, 40);
		btnImprimir.setToolTipText("Imprimir");
		btnImprimir.setIcon(new ImageIcon("/home/leo/git/santaclara/img/gestion/printer.png"));
		btnImprimir.setForeground(Color.WHITE);
		btnImprimir.setBackground(Color.GRAY);
		pnlOption.add(btnImprimir);
		
		Component verticalGlue_1 = Box.createVerticalGlue();
		verticalGlue_1.setBackground(Color.LIGHT_GRAY);
		verticalGlue_1.setBounds(182, 0, 12, 38);
		pnlOption.add(verticalGlue_1);
		
		btnAlmacen = new JButton("");
		btnAlmacen.setBounds(190, 0, 40, 40);
		btnAlmacen.setIcon(new ImageIcon("/home/leo/git/santaclara/img/gestion/empresa.png"));
		btnAlmacen.setToolTipText("Almacen");
		btnAlmacen.setForeground(Color.WHITE);
		btnAlmacen.setBackground(Color.GRAY);
		pnlOption.add(btnAlmacen);
		
		btnCliente = new JButton("");
		btnCliente.setBounds(235, 0, 40, 40);
		btnCliente.setToolTipText("Cliente");
		btnCliente.setIcon(new ImageIcon("/home/leo/git/santaclara/img/gestion/group.png"));
		btnCliente.setBackground(Color.GRAY);
		btnCliente.setForeground(Color.WHITE);
		pnlOption.add(btnCliente);
		
		btnVendedor = new JButton("");
		btnVendedor.setBounds(280, 0, 40, 40);
		btnVendedor.setToolTipText("Vendedor");
		btnVendedor.setIcon(new ImageIcon("/home/leo/git/santaclara/img/gestion/persona.png"));
		btnVendedor.setBackground(Color.GRAY);
		btnVendedor.setForeground(Color.WHITE);
		pnlOption.add(btnVendedor);
		
		btnProducto = new JButton("");
		btnProducto.setBounds(325, 0, 40, 40);
		btnProducto.setToolTipText("Producto");
		btnProducto.setIcon(new ImageIcon("/home/leo/git/santaclara/img/gestion/NuevoPaquete.png"));
		btnProducto.setBackground(Color.GRAY);
		btnProducto.setForeground(Color.WHITE);
		pnlOption.add(btnProducto);
		
		btnCatalogo = new JButton("");
		btnCatalogo.setBounds(370, 0, 40, 40);
		btnCatalogo.setToolTipText("Catalogo de Producto");
		btnCatalogo.setIcon(new ImageIcon("/home/leo/git/santaclara/img/gestion/desktop.png"));
		btnCatalogo.setBackground(Color.GRAY);
		btnCatalogo.setForeground(Color.WHITE);
		pnlOption.add(btnCatalogo);
		
		Component verticalGlue_2 = Box.createVerticalGlue();
		verticalGlue_2.setBackground(Color.LIGHT_GRAY);
		verticalGlue_2.setBounds(415, 0, 0, 38);
		pnlOption.add(verticalGlue_2);
		
		btnSalir = new JButton("");
		btnSalir.setBounds(711, 0, 40, 40);
		btnSalir.setToolTipText("Salir");
		btnSalir.setIcon(new ImageIcon("/home/leo/git/santaclara/img/gestion/SalirCurva.png"));
		btnSalir.setBackground(Color.GRAY);
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setHorizontalAlignment(SwingConstants.LEFT);
		pnlOption.add(btnSalir);
		
		btnGuardarFactura = new JButton("");
		btnGuardarFactura.setIcon(new ImageIcon("/home/leo/git/santaclara/img/gestion/bien.png"));
		btnGuardarFactura.setToolTipText("GenararFactura");
		btnGuardarFactura.setForeground(Color.WHITE);
		btnGuardarFactura.setBackground(Color.GRAY);
		btnGuardarFactura.setBounds(427, 0, 40, 40);
		pnlOption.add(btnGuardarFactura);
		
		pnlInfo = new JPanel();
		pnlInfo.setBackground(Color.GRAY);
		pnlInfo.setForeground(Color.WHITE);
		pnlInfo.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 3, true), "Informacion del Pedido", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnlInfo.setBounds(25, 77, 763, 118);
		pnlPedido.add(pnlInfo);
		pnlInfo.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBorder(new TitledBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 1, true), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(192, 192, 192)), "Almacen", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panel.setBounds(12, 24, 298, 23);
		pnlInfo.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 1, true), "Vendedor", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panel_1.setBackground(Color.GRAY);
		panel_1.setBounds(12, 60, 298, 46);
		pnlInfo.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 1, true), "Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panel_2.setBackground(Color.GRAY);
		panel_2.setBounds(319, 24, 298, 82);
		pnlInfo.add(panel_2);
		
		pnlDetalle = new JPanel();
		pnlDetalle.setForeground(Color.WHITE);
		pnlDetalle.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 3, true), "Detalle", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		pnlDetalle.setBackground(Color.GRAY);
		pnlDetalle.setBounds(25, 207, 763, 384);
		pnlPedido.add(pnlDetalle);
		pnlDetalle.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 14, 737, 358);
		pnlDetalle.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}

	public JPanel getPnlPedido() {
		return pnlPedido;
	}

	public void setPnlPedido(JPanel pnlPedido) {
		this.pnlPedido = pnlPedido;
	}

	public JPanel getPnlOption() {
		return pnlOption;
	}

	public void setPnlOption(JPanel pnlOption) {
		this.pnlOption = pnlOption;
	}

	public JPanel getPnlInfo() {
		return pnlInfo;
	}

	public void setPnlInfo(JPanel pnlInfo) {
		this.pnlInfo = pnlInfo;
	}

	public JPanel getPnlDetalle() {
		return pnlDetalle;
	}

	public void setPnlDetalle(JPanel pnlDetalle) {
		this.pnlDetalle = pnlDetalle;
	}

	public JButton getBtnAtras() {
		return btnAtras;
	}

	public void setBtnAtras(JButton btnAtras) {
		this.btnAtras = btnAtras;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(JButton btnGuardar) {
		this.btnGuardar = btnGuardar;
	}

	public JButton getBtnImprimir() {
		return btnImprimir;
	}

	public void setBtnImprimir(JButton btnImprimir) {
		this.btnImprimir = btnImprimir;
	}

	public JButton getBtnCliente() {
		return btnCliente;
	}

	public void setBtnCliente(JButton btnCliente) {
		this.btnCliente = btnCliente;
	}

	public JButton getBtnAlmacen() {
		return btnAlmacen;
	}

	public void setBtnAlmacen(JButton btnAlmacen) {
		this.btnAlmacen = btnAlmacen;
	}

	public JButton getBtnProducto() {
		return btnProducto;
	}

	public void setBtnProducto(JButton btnProducto) {
		this.btnProducto = btnProducto;
	}

	public JButton getBtnVendedor() {
		return btnVendedor;
	}

	public void setBtnVendedor(JButton btnVendedor) {
		this.btnVendedor = btnVendedor;
	}

	public JButton getBtnCatalogo() {
		return btnCatalogo;
	}

	public void setBtnCatalogo(JButton btnCatalogo) {
		this.btnCatalogo = btnCatalogo;
	}

	public JButton getBtnBuscar() {
		return btnBuscar;
	}

	public void setBtnBuscar(JButton btnBuscar) {
		this.btnBuscar = btnBuscar;
	}

	public JButton getBtnGuardarFactura() {
		return btnGuardarFactura;
	}

	public void setBtnGuardarFactura(JButton btnGuardarFactura) {
		this.btnGuardarFactura = btnGuardarFactura;
	}

	public JButton getBtnSalir() {
		return btnSalir;
	}

	public void setBtnSalir(JButton btnSalir) {
		this.btnSalir = btnSalir;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}
}
