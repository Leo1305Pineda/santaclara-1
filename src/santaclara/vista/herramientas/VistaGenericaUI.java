/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.vista.herramientas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


@SuppressWarnings("serial")
public class VistaGenericaUI extends JPanel {
	
	private JPanel pnTabla;
	
	private JPanel pnOpciones;
	private JPanel panelBuscar;
	private JPanel pnBotones;
	
	private JScrollPane scrollPanel;
	private JTable table;
	private JButton btnAtras;
	private JButton btnSalir;
	private JButton btnABuscar;
	private JTextField txtABuscar;

	public VistaGenericaUI() {
		super();
		setFont(new Font("Dialog", Font.BOLD, 13));
		setForeground(Color.WHITE);
		setBackground(Color.DARK_GRAY);
		//setSize(getWidthPantalla(),getHeightPantalla());
		setLayout(new BorderLayout());
		//dibujarPanelOpciones();
		//dibujarPanelTabla();
	}
	
	protected void dibujarPanelTabla() {
		pnTabla = new JPanel();
		pnTabla.setBackground(Color.DARK_GRAY);
		pnTabla.setLayout(new BorderLayout());
		pnTabla.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Catalogo", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		add(pnTabla,BorderLayout.CENTER);

		/////////////////////////////////////////////////////////////////////////////
		
		setScrollPanel(new JScrollPane());
		getScrollPanel().setBounds(0, 0,getWidthPantalla(),getHeightPantalla());
		getPnTabla().add(getScrollPanel());
		
	}
	
	protected void dibujarPanelOpciones() {
		pnOpciones = new JPanel();
		pnOpciones.setBounds(10,50,getWidthPantalla(),50);
		pnOpciones.setBackground(Color.DARK_GRAY);
		pnOpciones.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Opciones", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		add(pnOpciones,BorderLayout.NORTH);
		pnOpciones.setLayout(new BorderLayout(0, 0));
		
		panelBuscar = new JPanel();
		panelBuscar.setForeground(Color.GRAY);
		panelBuscar.setBackground(Color.DARK_GRAY);
		pnOpciones.add(panelBuscar,BorderLayout.EAST);

		pnBotones = new JPanel();
		pnBotones.setBackground(Color.DARK_GRAY);
		FlowLayout flowLayout = (FlowLayout) pnBotones.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		pnOpciones.add(pnBotones,BorderLayout.WEST);

	}
	
	protected void dibujarBotonAtras(){
		btnAtras = new JButton("Atras");
		btnAtras.setIcon(new ImageIcon("img/gestion/AtrasCurva.png"));
		btnAtras.setForeground(Color.WHITE);
		btnAtras.setFont(new Font("Dialog", Font.BOLD, 10));
		btnAtras.setBackground(Color.DARK_GRAY);
		pnBotones.add(btnAtras);
	}
	
	protected void dibujarBotonSalir() {
		btnSalir = new JButton("Salir");
		btnSalir.setIcon(new ImageIcon("img/gestion/SalirCurva.png"));
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(Color.DARK_GRAY);
		btnSalir.setFont(new Font("Dialog", Font.BOLD, 10));
		pnBotones.add(btnSalir);
	}
	
	protected void dibujarBuscar() {
		txtABuscar = new JTextField();
		txtABuscar.setForeground(Color.WHITE);
		txtABuscar.setBackground(new Color(64, 64, 64));
		getPanelBuscar().add(txtABuscar);
		txtABuscar.setColumns(10);
		
		btnABuscar = new JButton("");
		btnABuscar.setVerticalAlignment(SwingConstants.TOP);
		btnABuscar.setBackground(Color.DARK_GRAY);
		btnABuscar.setIcon(new ImageIcon("img/gestion/buscar.png"));
		getPanelBuscar().add(btnABuscar);
	}
	
	public static Integer getWidthPantalla()
	{
		Rectangle screenSize =  GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		return (int) (screenSize.width*0.95);
	}
	
	public static Integer getHeightPantalla()
	{
		Rectangle screenSize =  GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		return (int) (screenSize.height*0.95);
		//return screenSize.height;
	}

	public static Integer getMargenX()
	{
		Rectangle screenSize =  GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		return (int) (screenSize.width*0.08);
		//return screenSize.width;

	}
	
	public static Integer getMargenY()
	{
		Rectangle screenSize =  GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		return (int) (screenSize.height*0.05);
	}

	public JPanel getPnOpciones() {
		return pnOpciones;
	}

	public void setPnOpciones(JPanel pnOpciones) {
		this.pnOpciones = pnOpciones;
	}

	public JPanel getPanelBuscar() {
		return panelBuscar;
	}

	public void setPanelBuscar(JPanel panelBuscar) {
		this.panelBuscar = panelBuscar;
	}

	public JPanel getPnBotones() {
		return pnBotones;
	}

	public void setPnBotones(JPanel pnBotones) {
		this.pnBotones = pnBotones;
	}

	public JPanel getPnTabla() {
		return pnTabla;
	}

	public void setPnTabla(JPanel pnTabla) {
		this.pnTabla = pnTabla;
	}

	public JScrollPane getScrollPanel() {
		return scrollPanel;
	}

	public void setScrollPanel(JScrollPane scrollPanel) {
		this.scrollPanel = scrollPanel;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JButton getBtnAtras() {
		return btnAtras;
	}

	public void setBtnAtras(JButton atras) {
		this.btnAtras = atras;
	}

	public JButton getBtnSalir() {
		return btnSalir;
	}

	public void setBtnSalir(JButton btnSalir) {
		this.btnSalir = btnSalir;
	}

	public JButton getBtnABuscar() {
		return btnABuscar;
	}

	public void setBtnABuscar(JButton btnABuscar) {
		this.btnABuscar = btnABuscar;
	}

	public JTextField getTxtABuscar() {
		return txtABuscar;
	}
	
	public void setTxtABuscar(JTextField txtABuscar) {
		this.txtABuscar = txtABuscar;
	}
	
}
