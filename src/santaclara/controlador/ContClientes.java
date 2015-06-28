package santaclara.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.Severity;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import santaclara.Servicio.ServicioCliente;
import santaclara.modelo.Cliente;
import santaclara.modelo.Ruta;
import santaclara.vista.ClientesUI;
import sun.awt.SunHints.Value;

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
				JTable tabla1 = new JTable();
				tabla1 = vista.getTabla();
				Boolean enc = true;
				for(int i = 0;i<tabla1.getRowCount();i++)
				{
					if (tabla1.getValueAt(i, 0).equals(vista.getTxtABuscar().getText())||
						tabla1.getValueAt(i, 1).equals(vista.getTxtABuscar().getText())||
						tabla1.getValueAt(i, 2).equals(vista.getTxtABuscar().getText())||
						tabla1.getValueAt(i, 3).equals(vista.getTxtABuscar().getText())||
						tabla1.getValueAt(i, 4).equals(vista.getTxtABuscar().getText())||
						tabla1.getValueAt(i, 5).equals(vista.getTxtABuscar().getText()))
					{
						tabla1.setRowSelectionInterval(i,i);;
						enc = false;
						break;
					}
				}
				if (enc) JOptionPane.showMessageDialog(vista,"No Encontrado");
				vista.setTabla(tabla1);
				vista.setTxtABuscar("");
			}
		};
	}
	
	public ActionListener eliminar() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (vista.getTabla().getSelectedRow()>=0)
				{
				try {
					
					servicioCliente.eliminar(vista.getTabla().getValueAt(vista.getTabla().getSelectedRow(),0).toString());
vista.activarBinding();
					JOptionPane.showMessageDialog(vista,"Operacion Exitosa ");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}	
			}
		};
	}
	
	public ActionListener modificar(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Cliente cliente1 = new Cliente();
				try {		if (vista.getTabla().getSelectedRow()>=0)
							{
							Cliente cliente = new Cliente();
							cliente = servicioCliente.buscar(new Integer(vista.getTabla().getValueAt(vista.getTabla().getSelectedRow(),0).toString()));
							if (cliente != null)
							{
								vista.activarNuevoCliente();
								vista.getTxtId().setText(cliente.getId().toString());
								vista.getTxtRif().setText(cliente.getRif());
								vista.getTxtRazonSocial().setText(cliente.getRazonsocial());
								vista.getTxtDireccion().setText(cliente.getDireccion());
								vista.getTxtTelefono().setText(cliente.getTelefono());
								vista.getCmbRuta().setSelectedItem(cliente.getRuta());
							}
							}
				vista.activarBinding();
							
				}catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
				
				if (vista.getTxtId().getText().equals("")) 
				{
					cliente.setId(null); 
				}
				else
				{
					cliente.setId(new Integer(vista.getTxtId().getText().toString()));
				}
				
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
					vista.repaint();
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

