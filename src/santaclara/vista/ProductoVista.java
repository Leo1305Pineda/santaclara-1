package santaclara.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SpringLayout;
import javax.swing.JTable;
import java.awt.Color;

public class ProductoVista extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductoVista frame = new ProductoVista();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ProductoVista() {
		setBackground(Color.GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 579, 510);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setToolTipText("Gestion de Productos");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelproducto panelproducto_ = new panelproducto();
		panelproducto_.setBounds(46, 12, 460, 191);
		contentPane.add(panelproducto_);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setForeground(Color.WHITE);
		btnBuscar.setBackground(Color.DARK_GRAY);
		btnBuscar.setBounds(12, 222, 96, 25);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.add(btnBuscar);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBackground(Color.DARK_GRAY);
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setBounds(120, 222, 96, 25);
		contentPane.add(btnGuardar);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setForeground(Color.WHITE);
		btnModificar.setBackground(Color.DARK_GRAY);
		btnModificar.setBounds(227, 222, 115, 25);
		contentPane.add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setBackground(Color.DARK_GRAY);
		btnEliminar.setBounds(353, 222, 96, 25);
		contentPane.add(btnEliminar);
		
		Panelmostrarproductos panelmostrarproductos = new Panelmostrarproductos();
		panelmostrarproductos.setBounds(28, 259, 523, 207);
		contentPane.add(panelmostrarproductos);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(Color.DARK_GRAY);
		btnSalir.setBounds(461, 222, 96, 25);
		contentPane.add(btnSalir);
	}
}
