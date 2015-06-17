package santaclara.vista;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import java.awt.SystemColor;

public class Panelmostrarproductos extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public Panelmostrarproductos() {
		setBackground(Color.GRAY);
		setForeground(Color.GRAY);
		setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192)), "Productos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		setLayout(null);
		
		table = new JTable();
		table.setForeground(SystemColor.controlDkShadow);
		table.setToolTipText("Productos");
		table.setFillsViewportHeight(true);
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, "", null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"Codigo", "Nombre", "Presentacion", "Capacidad", "Sabor", "Precio"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, Object.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(108);
		table.getColumnModel().getColumn(1).setPreferredWidth(128);
		table.getColumnModel().getColumn(2).setPreferredWidth(117);
		table.getColumnModel().getColumn(3).setPreferredWidth(124);
		table.getColumnModel().getColumn(4).setPreferredWidth(112);
		table.getColumnModel().getColumn(5).setPreferredWidth(110);
		table.setBackground(SystemColor.text);
		
		
		table.setBounds(12, 33, 447, 160);
		
 		
		add(table);

	}
}
