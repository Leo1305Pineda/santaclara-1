package santaclara.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import santaclara.Servicio.ServicioCliente;
import santaclara.modelo.Cliente;
import santaclara.modelo.Ruta;
import santaclara.vista.ClientesUI;

public class ContClientes extends ContGeneral implements IContGeneral{
	
	private ClientesUI vista;
	private ServicioCliente servicioCliente;
	
	public ContClientes(ContPrincipal contPrincipal) throws Exception {
		// TODO Auto-generated constructor stub
		setContPrincipal(contPrincipal);
		servicioCliente = new ServicioCliente();
		vista = new ClientesUI(this,servicioCliente.getRutas(),servicioCliente.getClientes());
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

				Cliente cliente = new Cliente();
				cliente.setRazonsocial(vista.getTxtRazonSocial().getText());
				cliente.setRif(vista.getTxtRif().getText());
				cliente.setDireccion(vista.getTxtDireccion().getText());
				cliente.setTelefono(vista.getTxtTelefono().getText());
				cliente.setRuta((Ruta)vista.getCmbRuta().getSelectedItem());
				
				try {
					servicioCliente.guardar(cliente);
					// agregarlo a la lista
					vista.getClientes().add(cliente);
					vista.getBinClientes().unbind();
					vista.getBinClientes().bind();
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
				vista.activarNuevoCliente();
			}
		};
	}
}

