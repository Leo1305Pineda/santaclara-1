package santaclara.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import santaclara.Servicio.ServicioProducto;
import santaclara.modelo.Capacidad;
import santaclara.modelo.Presentacion;
import santaclara.modelo.Producto;
import santaclara.modelo.Sabor;
import santaclara.vista.IniciarSesionUI;
import santaclara.vista.ProductosUI;

public class ContProductos extends ContGeneral implements IContGeneral {

	private ProductosUI vista;
	private ServicioProducto servicioProducto;
	
	
	public ContProductos(ContPrincipal contPrincipal) throws Exception {
		// TODO Auto-generated constructor stub
		setContPrincipal(contPrincipal);
		servicioProducto = new ServicioProducto();
		vista = new ProductosUI(this,servicioProducto.getProductos(),
					servicioProducto.getCapacidades(),
					servicioProducto.getSabores(),servicioProducto.getPresentaciones());
		vista.activarBinding();
		dibujar(vista);
	
	}

	@Override
	public JPanel getVista() {
		// TODO Auto-generated method stub
		return vista;
	}

	public ActionListener buscar() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				
			}
		};
	}

	public ActionListener guardar() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// se va hacer las validaciones del controlador 

				Producto producto = new Producto();
				producto.setNombre(vista.getTxtNombre().getText());
				producto.setPrecio(new Double((double) vista.getTxtPrecio().getValue()));
				producto.setCapacidad((Capacidad) vista.getCmbCapacidad().getSelectedItem());
				producto.setPresentacion((Presentacion)vista.getCmbPresentacion().getSelectedItem());
				producto.setSabor((Sabor)vista.getCmbSabor().getSelectedItem());
				
				try {
					servicioProducto.guardar(producto);
					// agregarlo a la lista
					vista.getProductos().add(producto);
					vista.getBinProductos().unbind();
					vista.getBinProductos().bind();
					vista.getTable().repaint();
					JOptionPane.showMessageDialog(vista,"Operacion Exitosa ");
					vista.quitarNuevo();
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showConfirmDialog(null,e1.getMessage());
					e1.printStackTrace();
				}
				
				
			}
		};
	}

	public ActionListener nuevo() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				vista.activarNuevoProducto();
			}
		};
	}
}
