package santaclara.vista.herramientas;

 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

public class VistaGenericaUI extends JPanel {
	
	private JPanel pnTabla;
	
	private JPanel pnOpciones;
	private JPanel panelBuscar;
	private JPanel pnBotones;
	private JScrollPane scrollPanel;
	private JTable table;

	public VistaGenericaUI() {
		super();
		setFont(new Font("Dialog", Font.BOLD, 13));
		setForeground(Color.WHITE);
		setBackground(Color.DARK_GRAY);
		setSize(getWidthPantalla(),getHeightPantalla());
		setLayout(new BorderLayout());
		dibujarPanelOpciones();
		dibujarPanelTabla();

	}
	
	
	protected void dibujarPanelTabla() {
		pnTabla = new JPanel();
		//pnTabla.setBounds(10,50,getWidthPantalla()-20,70);
		pnTabla.setBackground(Color.DARK_GRAY);
		pnTabla.setLayout(new BorderLayout(0, 0));
		pnTabla.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Catalogo", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		add(pnTabla,BorderLayout.CENTER);

		/////////////////////////////////////////////////////////////////////////////
		
		setScrollPanel(new JScrollPane());
		getScrollPanel().setBounds(0, 0,getWidthPantalla(),getHeightPantalla());
		getPnTabla().add(getScrollPanel());
		
	}
	
	
	protected void dibujarPanelOpciones() {

		pnOpciones = new JPanel();
		//pnOpciones.setBounds(10,50,getWidthPantalla()-20,70);
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

	
	
	public static Integer getWidthPantalla()
	{
		Rectangle screenSize =  GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		return (int) (screenSize.width*0.95);
	}
	
	public static Integer getHeightPantalla()
	{
		Rectangle screenSize =  GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		return (int) (screenSize.height*0.95);
	}

	public static Integer getMargenX()
	{
		Rectangle screenSize =  GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		return (int) (screenSize.width*0.08);
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
}
