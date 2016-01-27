package santaclara.vista;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;

import org.jdesktop.swingbinding.JTableBinding;

import santaclara.controlador.ContPedidos;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.JTextField;

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
	private JLabel lblNombreVendedor;
	private JLabel lblRif;
	private JLabel lblRazonSocial;
	private JLabel lblTelefono;
	private JLabel lblDireccion;
	private JLabel lblRuta;
	private JLabel lblIvaSobreBs;
	private JLabel lblTotalAPagar;
	private JLabel lblDesc;
	private JLabel lblAlmacen;
	private JLabel lblFecha;
	private JLabel lblIva;
	private JLabel lblCondicion;
	
	private JScrollPane scrollPane;
	
	@SuppressWarnings("rawtypes")
	private JTableBinding BinPedidos;
	
	private JTable table;
	private JLabel lblDomicilioFiscal;
	private JLabel lblSubtotalGravado;
	private JLabel lblSubtotalExento;
	private JPanel panel_1;
	private JTextField txtNumeroPedido;
	
	public PedidosUI(ContPedidos contPedidos) throws IOException {
		setBounds(0, 0, 800, 700);
		setBackground(Color.DARK_GRAY);
		setSize(1216,696);
		setLayout(null);
		
		pnlPedido = new JPanel();
		pnlPedido.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 3, true), "Registro de Pedidos", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnlPedido.setBackground(Color.DARK_GRAY);
		pnlPedido.setBounds(12, 12, 1170, 700);
		add(pnlPedido);
		pnlPedido.setLayout(null);
		
		pnlOption = new JPanel();
		pnlOption.setToolTipText("Operaciones");
		pnlOption.setBackground(Color.DARK_GRAY);
		pnlOption.setBounds(12, 25, 437, 40);
		pnlPedido.add(pnlOption);
		
		btnAtras = new JButton("Atras");
		btnAtras.addActionListener(contPedidos.actionAtras());
		pnlOption.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		btnAtras.setIcon(new ImageIcon("/home/leo/git/santaclara/img/gestion/AtrasCurva.png"));
		btnAtras.setForeground(Color.WHITE);
		btnAtras.setBackground(Color.DARK_GRAY);
		pnlOption.add(btnAtras);
		
		btnNuevo = new JButton("Limpiar");
		btnNuevo.addActionListener(contPedidos.actionNuevo());
		btnNuevo.setToolTipText("Limpiar");
		btnNuevo.setIcon(new ImageIcon("/home/leo/git/santaclara/img/gestion/add.png"));
		btnNuevo.setForeground(Color.WHITE);
		btnNuevo.setBackground(Color.DARK_GRAY);
		pnlOption.add(btnNuevo);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(contPedidos.actionGuardarPedido());
		btnGuardar.setIcon(new ImageIcon("/home/leo/git/santaclara/img/gestion/disk.png"));
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setBackground(Color.DARK_GRAY);
		pnlOption.add(btnGuardar);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(contPedidos.actionSalir());
		btnSalir.setToolTipText("Salir");
		btnSalir.setIcon(new ImageIcon("/home/leo/git/santaclara/img/gestion/SalirCurva.png"));
		btnSalir.setBackground(Color.DARK_GRAY);
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setHorizontalAlignment(SwingConstants.LEFT);
		pnlOption.add(btnSalir);
		
		pnlInfo = new JPanel();
		pnlInfo.setBackground(Color.DARK_GRAY);
		pnlInfo.setForeground(Color.WHITE);
		pnlInfo.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 3, true), "Informacion del Pedido", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnlInfo.setBounds(12, 77, 1146, 185);
		pnlPedido.add(pnlInfo);
		pnlInfo.setLayout(null);
		
		pnlAlmacen = new JPanel();
		pnlAlmacen.setBackground(Color.DARK_GRAY);
		pnlAlmacen.setBorder(new TitledBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 1, true), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(192, 192, 192)), "Almacen", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		pnlAlmacen.setBounds(541, 43, 547, 51);
		pnlInfo.add(pnlAlmacen);
		pnlAlmacen.setLayout(new GridLayout(0, 1, 0, 0));
		
		lblAlmacen = new JLabel("Ubicacion:");
		lblAlmacen.setForeground(Color.WHITE);
		pnlAlmacen.add(lblAlmacen);
		
		pnlVendedor = new JPanel();
		pnlVendedor.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 1, true), "Vendedor", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		pnlVendedor.setBackground(Color.DARK_GRAY);
		pnlVendedor.setBounds(541, 106, 547, 51);
		pnlInfo.add(pnlVendedor);
		pnlVendedor.setLayout(new GridLayout(0, 1, 0, 0));
		
		lblNombreVendedor = new JLabel("");
		lblNombreVendedor.setForeground(Color.WHITE);
		pnlVendedor.add(lblNombreVendedor);
		
		pnlCliente = new JPanel();
		pnlCliente.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 1, true), "Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		pnlCliente.setBackground(Color.DARK_GRAY);
		pnlCliente.setBounds(12, 43, 480, 130);
		pnlInfo.add(pnlCliente);
		pnlCliente.setLayout(new GridLayout(0, 1, 0, 0));
		
		lblRif = new JLabel("Rif:");
		lblRif.setFont(new Font("Dialog", Font.BOLD, 12));
		lblRif.setForeground(Color.WHITE);
		pnlCliente.add(lblRif);
		
		lblRazonSocial = new JLabel("Razon Social:");
		lblRazonSocial.setForeground(Color.WHITE);
		pnlCliente.add(lblRazonSocial);
		
		lblDomicilioFiscal = new JLabel("Domicilio Fiscal:");
		lblDomicilioFiscal.setForeground(Color.WHITE);
		pnlCliente.add(lblDomicilioFiscal);
		
		lblDireccion = new JLabel("");
		lblDireccion.setForeground(Color.WHITE);
		pnlCliente.add(lblDireccion);
		
		lblTelefono = new JLabel("Telefono:");
		lblTelefono.setForeground(Color.WHITE);
		pnlCliente.add(lblTelefono);
		
		lblRuta = new JLabel("Ruta:");
		lblRuta.setForeground(Color.WHITE);
		pnlCliente.add(lblRuta);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 12, 415, 35);
		pnlInfo.add(panel);
		panel.setBackground(Color.DARK_GRAY);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnAlmacen = new JButton("Almacenes");
		panel.add(btnAlmacen);
		btnAlmacen.addActionListener(contPedidos.actionAlmacen());
		btnAlmacen.setIcon(new ImageIcon("/home/leo/git/santaclara/img/gestion/empresa.png"));
		btnAlmacen.setToolTipText("Almacen");
		btnAlmacen.setForeground(Color.WHITE);
		btnAlmacen.setBackground(Color.DARK_GRAY);
		
		btnCliente = new JButton("Clientes");
		panel.add(btnCliente);
		btnCliente.addActionListener(contPedidos.actionCliente());
		btnCliente.setToolTipText("Cliente");
		btnCliente.setIcon(new ImageIcon("/home/leo/git/santaclara/img/gestion/group.png"));
		btnCliente.setBackground(Color.DARK_GRAY);
		btnCliente.setForeground(Color.WHITE);
		
		btnVendedor = new JButton("Vendedores");
		panel.add(btnVendedor);
		btnVendedor.addActionListener(contPedidos.actionVendedor());
		btnVendedor.setToolTipText("Vendedor");
		btnVendedor.setIcon(new ImageIcon("/home/leo/git/santaclara/img/gestion/user.png"));
		btnVendedor.setBackground(Color.DARK_GRAY);
		btnVendedor.setForeground(Color.WHITE);
		
		pnlDetalle = new JPanel();
		pnlDetalle.setForeground(Color.WHITE);
		pnlDetalle.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 3, true), "Detalle", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		pnlDetalle.setBackground(Color.DARK_GRAY);
		pnlDetalle.setBounds(12, 264, 1146, 282);
		pnlPedido.add(pnlDetalle);
		pnlDetalle.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 49, 1120, 230);
		pnlDetalle.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(14, 12, 239, 35);
		pnlDetalle.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnProducto = new JButton("Agregar");
		panel_1.add(btnProducto);
		btnProducto.addActionListener(contPedidos.actionProducto());
		btnProducto.setToolTipText("Agregar Producto");
		btnProducto.setIcon(new ImageIcon("/home/leo/git/santaclara/img/gestion/NuevoPaquete.png"));
		btnProducto.setBackground(Color.DARK_GRAY);
		btnProducto.setForeground(Color.WHITE);
		
		btnQuitarProducto = new JButton("Quitar");
		panel_1.add(btnQuitarProducto);
		btnQuitarProducto.addActionListener(contPedidos.actionQuitarProductoDetalle());
		btnQuitarProducto.setToolTipText("Quitar Producto");
		btnQuitarProducto.setIcon(new ImageIcon("/home/leo/git/santaclara/img/gestion/desktop.png"));
		btnQuitarProducto.setBackground(Color.DARK_GRAY);
		btnQuitarProducto.setForeground(Color.WHITE);
		
		pnlTotales = new JPanel();
		pnlTotales.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 2, true), "Totales", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		pnlTotales.setBackground(Color.DARK_GRAY);
		pnlTotales.setBounds(817, 548, 341, 140);
		pnlPedido.add(pnlTotales);
		pnlTotales.setLayout(new GridLayout(0, 1, 0, 0));
		
		lblSubtotalExento = new JLabel("SUBTOTAL EXENTO:");
		lblSubtotalExento.setFont(new Font("Abyssinica SIL", Font.ITALIC, 12));
		lblSubtotalExento.setForeground(Color.WHITE);
		pnlTotales.add(lblSubtotalExento);
		
		lblSubtotalGravado = new JLabel("SUBTOTAL GRAVADO:");
		lblSubtotalGravado.setFont(new Font("Abyssinica SIL", Font.ITALIC, 12));
		lblSubtotalGravado.setForeground(Color.WHITE);
		pnlTotales.add(lblSubtotalGravado);
		
		lblDesc = new JLabel("%DESC.:");
		lblDesc.setFont(new Font("Abyssinica SIL", Font.ITALIC, 12));
		lblDesc.setForeground(Color.WHITE);
		pnlTotales.add(lblDesc);
		
		lblIvaSobreBs = new JLabel("I.V.A. SOBRE Bs.:");
		lblIvaSobreBs.setFont(new Font("Abyssinica SIL", Font.ITALIC, 12));
		lblIvaSobreBs.setForeground(Color.WHITE);
		pnlTotales.add(lblIvaSobreBs);
		
		lblIva = new JLabel("I.V.A: 12.00  %");
		lblIva.setFont(new Font("Abyssinica SIL", Font.ITALIC, 12));
		lblIva.setForeground(Color.WHITE);
		pnlTotales.add(lblIva);
		
		lblTotalAPagar = new JLabel("TOTAL A PAGAR:");
		lblTotalAPagar.setFont(new Font("Abyssinica SIL", Font.ITALIC, 12));
		lblTotalAPagar.setForeground(Color.WHITE);
		pnlTotales.add(lblTotalAPagar);
		
		btnGuardarFactura = new JButton("Generar Factura");
		btnGuardarFactura.setBounds(36, 617, 178, 26);
		pnlPedido.add(btnGuardarFactura);
		btnGuardarFactura.addActionListener(contPedidos.actionGenerarFactura());
		btnGuardarFactura.setIcon(new ImageIcon("/home/leo/git/santaclara/img/gestion/bien.png"));
		btnGuardarFactura.setToolTipText("GenararFactura");
		btnGuardarFactura.setForeground(Color.WHITE);
		btnGuardarFactura.setBackground(Color.DARK_GRAY);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.DARK_GRAY);
		panel_2.setBounds(914, 25, 244, 40);
		pnlPedido.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		txtNumeroPedido = new JTextField("");
		panel_2.add(txtNumeroPedido);
		txtNumeroPedido.setColumns(15);
		
		btnBuscar = new JButton("");
		panel_2.add(btnBuscar);
		btnBuscar.addActionListener(contPedidos.actionBuscarPedido());
		btnBuscar.setIcon(new ImageIcon("/home/leo/git/santaclara/img/gestion/buscar.png"));
		btnBuscar.setBackground(Color.DARK_GRAY);
		btnBuscar.setForeground(Color.WHITE);
		
		lblFecha = new JLabel("Fecha:");
		lblFecha.setFont(new Font("Dialog", Font.BOLD, 18));
		lblFecha.setBounds(544, 36, 313, 24);
		pnlPedido.add(lblFecha);
		lblFecha.setForeground(Color.WHITE);
		
		lblCondicion = new JLabel("Condicion de pago:");
		lblCondicion.setBounds(36, 575, 250, 15);
		pnlPedido.add(lblCondicion);
		lblCondicion.setForeground(Color.WHITE);
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

	public JLabel getLblCondicion() {
		return lblCondicion;
	}

	public void setLblCondicion(JLabel lblCondicion) {
		this.lblCondicion = lblCondicion;
	}

	public JLabel getLblIvaSobreBs() {
		return lblIvaSobreBs;
	}

	public void setLblIvaSobreBs(JLabel lblIvaSobreBs) {
		this.lblIvaSobreBs = lblIvaSobreBs;
	}

	public JLabel getLblTotalAPagar() {
		return lblTotalAPagar;
	}

	public void setLblTotalAPagar(JLabel lblTotalAPagar) {
		this.lblTotalAPagar = lblTotalAPagar;
	}

	public JLabel getLblDesc() {
		return lblDesc;
	}

	public void setLblDesc(JLabel lblDesc) {
		this.lblDesc = lblDesc;
	}

	public JLabel getLblIva() {
		return lblIva;
	}

	public void setLblIva(JLabel lblIva) {
		this.lblIva = lblIva;
	}

	public JLabel getLblDomicilioFiscal() {
		return lblDomicilioFiscal;
	}

	public void setLblDomicilioFiscal(JLabel lblDomicilioFiscal) {
		this.lblDomicilioFiscal = lblDomicilioFiscal;
	}

	public JLabel getLblSubtotalGravado() {
		return lblSubtotalGravado;
	}

	public void setLblSubtotalGravado(JLabel lblSubtotalGravado) {
		this.lblSubtotalGravado = lblSubtotalGravado;
	}

	public JLabel getLblSubtotalExento() {
		return lblSubtotalExento;
	}

	public void setLblSubtotalExento(JLabel lblSubtotalExento) {
		this.lblSubtotalExento = lblSubtotalExento;
	}

	public JTextField getTxtNumeroPedido() {
		return txtNumeroPedido;
	}

	public void setTxtNumeroPedido(JTextField txtNumeroPedido) {
		this.txtNumeroPedido = txtNumeroPedido;
	}
	
}

