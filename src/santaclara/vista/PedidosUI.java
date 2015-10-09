package santaclara.vista;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.Box;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;

import org.jdesktop.swingbinding.JTableBinding;

import santaclara.controlador.ContPedidos;

@SuppressWarnings("serial")
public class PedidosUI extends JPanel{
	
	private JPanel pnlPedido;
	private JPanel pnlOption;
	private JPanel pnlInfo;
	private JPanel pnlDetalle;
	private JPanel pnlVendedor;
	private JPanel pnlCliente;
	private JPanel pnlTotales;
	private JPanel pnlAlmacen;
	
	private JButton btnAtras;
	private JButton btnGuardar;
	private JButton btnNuevo;
	private JButton btnCliente;
	private JButton btnAlmacen;
	private JButton btnProducto;
	private JButton btnVendedor;
	private JButton btnQuitarProducto;
	private JButton btnBuscar;
	private JButton btnGuardarFactura;
	private JButton btnSalir;
	
	private JLabel lblCedulaVendedor;
	private JLabel lblNombreVendedor;
	private JLabel lblRif;
	private JLabel lblRazonSocial;
	private JLabel lblTelefono;
	private JLabel lblDireccion;
	private JLabel lblRuta;
	private JLabel lblTotalIva;
	private JLabel lblSaldoAPagar;
	private JLabel lblTotalSinIva;
	private JLabel lblNumeroPedido;
	private JLabel lblAlmacen;
	private JLabel lblFecha;
	private JLabel lblDescuento;
	private JLabel lblTotalDescuento;
	
	private JScrollPane scrollPane;
	
	@SuppressWarnings("rawtypes")
	private JTableBinding BinPedidos;
	
	private JTable table;
	
	public PedidosUI(ContPedidos contPedidos) throws IOException {
		setBounds(0, 0, 800, 700);
		setBackground(Color.DARK_GRAY);
		setSize(1216,696);
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
		btnAtras.addActionListener(contPedidos.actionAtras());
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
		btnBuscar.addActionListener(contPedidos.actionBuscarPedido());
		btnBuscar.setBounds(50, 0, 40, 40);
		btnBuscar.setIcon(new ImageIcon("/home/leo/git/santaclara/img/gestion/buscar.png"));
		btnBuscar.setBackground(Color.GRAY);
		btnBuscar.setForeground(Color.WHITE);
		pnlOption.add(btnBuscar);
		
		btnGuardar = new JButton("");
		btnGuardar.addActionListener(contPedidos.actionGuardarPedido());
		btnGuardar.setBounds(140, 0, 40, 40);
		btnGuardar.setIcon(new ImageIcon("/home/leo/git/santaclara/img/gestion/disk.png"));
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setBackground(Color.GRAY);
		pnlOption.add(btnGuardar);
		
		btnNuevo = new JButton("");
		btnNuevo.addActionListener(contPedidos.actionNuevo());
		btnNuevo.setBounds(95, 0, 40, 40);
		btnNuevo.setToolTipText("Nuevo");
		btnNuevo.setIcon(new ImageIcon("/home/leo/git/santaclara/img/gestion/add.png"));
		btnNuevo.setForeground(Color.WHITE);
		btnNuevo.setBackground(Color.GRAY);
		pnlOption.add(btnNuevo);
		
		Component verticalGlue_1 = Box.createVerticalGlue();
		verticalGlue_1.setBackground(Color.LIGHT_GRAY);
		verticalGlue_1.setBounds(182, 0, 12, 38);
		pnlOption.add(verticalGlue_1);
		
		btnAlmacen = new JButton("");
		btnAlmacen.addActionListener(contPedidos.actionAlmacen());
		btnAlmacen.setBounds(190, 0, 40, 40);
		btnAlmacen.setIcon(new ImageIcon("/home/leo/git/santaclara/img/gestion/empresa.png"));
		btnAlmacen.setToolTipText("Almacen");
		btnAlmacen.setForeground(Color.WHITE);
		btnAlmacen.setBackground(Color.GRAY);
		pnlOption.add(btnAlmacen);
		
		btnCliente = new JButton("");
		btnCliente.addActionListener(contPedidos.actionCliente());
		btnCliente.setBounds(235, 0, 40, 40);
		btnCliente.setToolTipText("Cliente");
		btnCliente.setIcon(new ImageIcon("/home/leo/git/santaclara/img/gestion/group.png"));
		btnCliente.setBackground(Color.GRAY);
		btnCliente.setForeground(Color.WHITE);
		pnlOption.add(btnCliente);
		
		btnVendedor = new JButton("");
		btnVendedor.addActionListener(contPedidos.actionVendedor());
		btnVendedor.setBounds(280, 0, 40, 40);
		btnVendedor.setToolTipText("Vendedor");
		btnVendedor.setIcon(new ImageIcon("/home/leo/git/santaclara/img/gestion/user.png"));
		btnVendedor.setBackground(Color.GRAY);
		btnVendedor.setForeground(Color.WHITE);
		pnlOption.add(btnVendedor);
		
		btnProducto = new JButton("");
		btnProducto.addActionListener(contPedidos.actionProducto());
		btnProducto.setBounds(325, 0, 40, 40);
		btnProducto.setToolTipText("Agregar Producto");
		btnProducto.setIcon(new ImageIcon("/home/leo/git/santaclara/img/gestion/NuevoPaquete.png"));
		btnProducto.setBackground(Color.GRAY);
		btnProducto.setForeground(Color.WHITE);
		pnlOption.add(btnProducto);
		
		btnQuitarProducto = new JButton("");
		btnQuitarProducto.addActionListener(contPedidos.actionQuitarProductoDetalle());
		btnQuitarProducto.setBounds(370, 0, 40, 40);
		btnQuitarProducto.setToolTipText("Quitar Producto");
		btnQuitarProducto.setIcon(new ImageIcon("/home/leo/git/santaclara/img/gestion/desktop.png"));
		btnQuitarProducto.setBackground(Color.GRAY);
		btnQuitarProducto.setForeground(Color.WHITE);
		pnlOption.add(btnQuitarProducto);
		
		Component verticalGlue_2 = Box.createVerticalGlue();
		verticalGlue_2.setBackground(Color.LIGHT_GRAY);
		verticalGlue_2.setBounds(415, 0, 0, 38);
		pnlOption.add(verticalGlue_2);
		
		btnSalir = new JButton("");
		btnSalir.addActionListener(contPedidos.actionSalir());
		btnSalir.setBounds(711, 0, 40, 40);
		btnSalir.setToolTipText("Salir");
		btnSalir.setIcon(new ImageIcon("/home/leo/git/santaclara/img/gestion/SalirCurva.png"));
		btnSalir.setBackground(Color.GRAY);
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setHorizontalAlignment(SwingConstants.LEFT);
		pnlOption.add(btnSalir);
		
		btnGuardarFactura = new JButton("");
		btnGuardarFactura.addActionListener(contPedidos.actionGenerarFactura());
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
		pnlInfo.setBounds(25, 77, 763, 135);
		pnlPedido.add(pnlInfo);
		pnlInfo.setLayout(null);
		
		pnlAlmacen = new JPanel();
		pnlAlmacen.setBackground(Color.GRAY);
		pnlAlmacen.setBorder(new TitledBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 1, true), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(192, 192, 192)), "Almacen", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		pnlAlmacen.setBounds(12, 24, 250, 30);
		pnlInfo.add(pnlAlmacen);
		pnlAlmacen.setLayout(new GridLayout(0, 1, 0, 0));
		
		lblAlmacen = new JLabel("Ubicacion:");
		lblAlmacen.setForeground(Color.WHITE);
		pnlAlmacen.add(lblAlmacen);
		
		pnlVendedor = new JPanel();
		pnlVendedor.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 1, true), "Vendedor", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		pnlVendedor.setBackground(Color.GRAY);
		pnlVendedor.setBounds(12, 60, 250, 46);
		pnlInfo.add(pnlVendedor);
		pnlVendedor.setLayout(new GridLayout(0, 1, 0, 0));
		
		lblCedulaVendedor = new JLabel("Cedula:");
		lblCedulaVendedor.setForeground(Color.WHITE);
		pnlVendedor.add(lblCedulaVendedor);
		
		lblNombreVendedor = new JLabel("Nombre:");
		lblNombreVendedor.setForeground(Color.WHITE);
		pnlVendedor.add(lblNombreVendedor);
		
		pnlCliente = new JPanel();
		pnlCliente.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 1, true), "Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		pnlCliente.setBackground(Color.GRAY);
		pnlCliente.setBounds(270, 24, 250, 82);
		pnlInfo.add(pnlCliente);
		pnlCliente.setLayout(new GridLayout(0, 1, 0, 0));
		
		lblRif = new JLabel("Rif:");
		lblRif.setForeground(Color.WHITE);
		pnlCliente.add(lblRif);
		
		lblRazonSocial = new JLabel("Razon Social:");
		lblRazonSocial.setForeground(Color.WHITE);
		pnlCliente.add(lblRazonSocial);
		
		lblTelefono = new JLabel("Telefono:");
		lblTelefono.setForeground(Color.WHITE);
		pnlCliente.add(lblTelefono);
		
		lblDireccion = new JLabel("Direccion:");
		lblDireccion.setForeground(Color.WHITE);
		pnlCliente.add(lblDireccion);
		
		lblRuta = new JLabel("Ruta:");
		lblRuta.setForeground(Color.WHITE);
		pnlCliente.add(lblRuta);
		
		lblNumeroPedido = new JLabel("Numero:");
		lblNumeroPedido.setForeground(Color.WHITE);
		lblNumeroPedido.setBounds(527, 38, 192, 10);
		pnlInfo.add(lblNumeroPedido);
		
		lblFecha = new JLabel("Fecha:");
		lblFecha.setForeground(Color.WHITE);
		lblFecha.setBounds(527, 60, 192, 10);
		pnlInfo.add(lblFecha);
		
		lblDescuento = new JLabel("Descuento:");
		lblDescuento.setForeground(Color.WHITE);
		lblDescuento.setBounds(527, 82, 192, 10);
		lblDescuento.setVisible(false);
		pnlInfo.add(lblDescuento);
		
		pnlDetalle = new JPanel();
		pnlDetalle.setForeground(Color.WHITE);
		pnlDetalle.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 3, true), "Detalle", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		pnlDetalle.setBackground(Color.GRAY);
		pnlDetalle.setBounds(25, 220, 763, 371);
		pnlPedido.add(pnlDetalle);
		pnlDetalle.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 14, 737, 347);
		pnlDetalle.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		pnlTotales = new JPanel();
		pnlTotales.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 2, true), "Totales", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnlTotales.setBackground(Color.DARK_GRAY);
		pnlTotales.setBounds(534, 590, 254, 100);
		pnlPedido.add(pnlTotales);
		pnlTotales.setLayout(null);
		
		lblTotalSinIva = new JLabel("Total Sin Iva:");
		lblTotalSinIva.setForeground(Color.WHITE);
		lblTotalSinIva.setBounds(12, 18, 244, 10);
		pnlTotales.add(lblTotalSinIva);
		
		lblTotalIva = new JLabel("Total Iva:");
		lblTotalIva.setForeground(Color.WHITE);
		lblTotalIva.setBounds(12, 36, 244, 10);
		pnlTotales.add(lblTotalIva);
		
		lblSaldoAPagar = new JLabel("Saldo a Pagar:");
		lblSaldoAPagar.setForeground(Color.WHITE);
		lblSaldoAPagar.setBounds(12, 77, 244, 10);
		pnlTotales.add(lblSaldoAPagar);
		
		lblTotalDescuento = new JLabel("Total Descuento:");
		lblTotalDescuento.setForeground(Color.WHITE);
		lblTotalDescuento.setBounds(12, 55, 244, 10);
		pnlTotales.add(lblTotalDescuento);
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
		return btnNuevo;
	}

	public void setBtnImprimir(JButton btnImprimir) {
		this.btnNuevo = btnImprimir;
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
		return btnQuitarProducto;
	}

	public void setBtnCatalogo(JButton btnCatalogo) {
		this.btnQuitarProducto = btnCatalogo;
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

	public JPanel getPnlVendedor() {
		return pnlVendedor;
	}

	public void setPnlVendedor(JPanel pnlVendedor) {
		this.pnlVendedor = pnlVendedor;
	}

	public JPanel getPnlCliente() {
		return pnlCliente;
	}

	public void setPnlCliente(JPanel pnlCliente) {
		this.pnlCliente = pnlCliente;
	}

	public JPanel getPnlTotales() {
		return pnlTotales;
	}

	public void setPnlTotales(JPanel pnlTotales) {
		this.pnlTotales = pnlTotales;
	}

	public JPanel getPnlAlmacen() {
		return pnlAlmacen;
	}

	public void setPnlAlmacen(JPanel pnlAlmacen) {
		this.pnlAlmacen = pnlAlmacen;
	}

	public JLabel getLblCedulaVendedor() {
		return lblCedulaVendedor;
	}

	public void setLblCedulaVendedor(JLabel lblCedulaVendedor) {
		this.lblCedulaVendedor = lblCedulaVendedor;
	}

	public JLabel getLblNombreVendedor() {
		return lblNombreVendedor;
	}

	public void setLblNombreVendedor(JLabel lblNombreVendedor) {
		this.lblNombreVendedor = lblNombreVendedor;
	}

	public JLabel getLblRif() {
		return lblRif;
	}

	public void setLblRif(JLabel lblRif) {
		this.lblRif = lblRif;
	}

	public JLabel getLblRazonSocial() {
		return lblRazonSocial;
	}

	public void setLblRazonSocial(JLabel lblRazonSocial) {
		this.lblRazonSocial = lblRazonSocial;
	}

	public JLabel getLblTelefono() {
		return lblTelefono;
	}

	public void setLblTelefono(JLabel lblTelefono) {
		this.lblTelefono = lblTelefono;
	}

	public JLabel getLblDireccion() {
		return lblDireccion;
	}

	public void setLblDireccion(JLabel lblDireccion) {
		this.lblDireccion = lblDireccion;
	}

	public JLabel getLblRuta() {
		return lblRuta;
	}

	public void setLblRuta(JLabel lblRuta) {
		this.lblRuta = lblRuta;
	}

	public JLabel getLblTotalIva() {
		return lblTotalIva;
	}

	public void setLblTotalIva(JLabel lblTotalIva) {
		this.lblTotalIva = lblTotalIva;
	}

	public JLabel getLblSaldoAPagar() {
		return lblSaldoAPagar;
	}

	public void setLblSaldoAPagar(JLabel lblSaldoAPagar) {
		this.lblSaldoAPagar = lblSaldoAPagar;
	}

	public JLabel getLblTotalSinIva() {
		return lblTotalSinIva;
	}

	public void setLblTotalSinIva(JLabel lblTotalSinIva) {
		this.lblTotalSinIva = lblTotalSinIva;
	}

	public JLabel getLblNumeroPedido() {
		return lblNumeroPedido;
	}

	public void setLblNumeroPedido(JLabel lblNumeroPedido) {
		this.lblNumeroPedido = lblNumeroPedido;
	}

	public JLabel getLblAlmacen() {
		return lblAlmacen;
	}

	public void setLblAlmacen(JLabel lblAlmacen) {
		this.lblAlmacen = lblAlmacen;
	}

	public JLabel getLblFecha() {
		return lblFecha;
	}

	public void setLblFecha(JLabel lblFecha) {
		this.lblFecha = lblFecha;
	}

	public JLabel getLblDescuento() {
		return lblDescuento;
	}

	public void setLblDescuento(JLabel lblDescuento) {
		this.lblDescuento = lblDescuento;
	}

	@SuppressWarnings("rawtypes")
	public JTableBinding getBinPedidos() {
		return BinPedidos;
	}

	@SuppressWarnings("rawtypes")
	public void setBinPedidos(JTableBinding binPedidos) {
		BinPedidos = binPedidos;
	}

	public JButton getBtnNuevo() {
		return btnNuevo;
	}

	public void setBtnNuevo(JButton btnNuevo) {
		this.btnNuevo = btnNuevo;
	}

	public JButton getBtnQuitarProducto() {
		return btnQuitarProducto;
	}

	public void setBtnQuitarProducto(JButton btnQuitarProducto) {
		this.btnQuitarProducto = btnQuitarProducto;
	}

	public JLabel getLblTotalDescuento() {
		return lblTotalDescuento;
	}

	public void setLblTotalDescuento(JLabel lblTotalDescuento) {
		this.lblTotalDescuento = lblTotalDescuento;
	}
	
}
