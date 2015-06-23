package santaclara.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import santaclara.Servicio.ServicioProducto;
import santaclara.Servicio.ServicioVendedor;
import santaclara.modelo.Capacidad;
import santaclara.modelo.Presentacion;
import santaclara.modelo.Producto;
import santaclara.modelo.Sabor;
import santaclara.vista.IniciarSesionUI;
import santaclara.vista.ProductosUI;
import santaclara.vista.VendedoresUI;

public class ContVendedores extends ContGeneral implements IContGeneral {

	private VendedoresUI vista;
	private ServicioVendedor servicioVendedor = new ServicioVendedor();
	
	public ContVendedores(ContPrincipal contPrincipal) throws Exception {
		// TODO Auto-generated constructor stub
		setContPrincipal(contPrincipal);
		vista = new VendedoresUI(this,servicioVendedor.getVendedores());
		vista.activarBinding();
		dibujar(vista);
	
	}

	@Override
	public JPanel getVista() {
		// TODO Auto-generated method stub
		return vista;
	}

	
  // evento Guardar Prodcuto 
	public ActionListener guardar() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// se va hacer las validaciones del controlador 

				
			}
		};
	}

	public ActionListener nuevo() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				vista.activarNuevoVendedor();
				
			}
		};
	}
}
