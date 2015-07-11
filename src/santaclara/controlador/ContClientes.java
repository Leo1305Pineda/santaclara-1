package santaclara.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import santaclara.Servicio.ServicioCliente;
import santaclara.modelo.Cliente;
import santaclara.modelo.Ruta;
import santaclara.vista.ClientesUI;

public class ContClientes extends ContGeneral implements IContGeneral{
	
	private ClientesUI vista;
	private ServicioCliente servicioCliente;
	private ContPrincipal contPrincipal;
	private ContRutas contRutas;
	
	public ContClientes(ContPrincipal contPrincipal) throws Exception {
		// TODO Auto-generated constructor stub
		this.contPrincipal = contPrincipal;
		setContPrincipal(contPrincipal);
		servicioCliente = new ServicioCliente();
		vista = new ClientesUI(this,servicioCliente.getRutas(),servicioCliente.getClientes());
		vista.activarBinding(servicioCliente.getClientes());
		dibujar(vista);
		vista.quitarNuevo();
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
						Cliente cliente;
						cliente=servicioCliente.buscar(new Integer(vista.getTabla().getValueAt(vista.getTabla().getSelectedRow(),0).toString()));
												
						servicioCliente.eliminar(cliente);
				
						vista.getBinClientes().unbind();
						vista.getBinClientes().bind();				
						vista.activarBinding(servicioCliente.getClientes());
						JOptionPane.showMessageDialog(vista,"Operacion Exitosa ");
		
					} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					}
				}
				else
				{
					JOptionPane.showMessageDialog(vista,"Seleccione Cliente ");
				}
			}
		};
	}
	
	public ActionListener modificar(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
						if (vista.getTabla().getSelectedRow()>=0)
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
						else
						{
							JOptionPane.showMessageDialog(vista,"Seleccione Cliente ");
						}	
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
				String msg="";
				
				if (vista.getTxtId().getText().equals("")) cliente.setId(null); 
					else cliente.setId(new Integer(vista.getTxtId().getText().toString()));
				
				if (vista.getTxtRazonSocial().getText().equals("")) msg=" Razon Social, ";
				else cliente.setRazonsocial(vista.getTxtRazonSocial().getText());
				
				if (vista.getTxtRif().getText().equals("")) msg=msg+" Rif, ";
				else cliente.setRif(vista.getTxtRif().getText());
				
				if (vista.getTxtDireccion().getText().equals("")) msg=msg+" Direccion, ";
				else cliente.setDireccion(vista.getTxtDireccion().getText());
				
				if (vista.getTxtTelefono().getText().equals("")) msg=msg+" Telefono, ";
				else cliente.setTelefono(vista.getTxtTelefono().getText());
				
				cliente.setRuta((Ruta)vista.getCmbRuta().getSelectedItem());
				
				if (msg!="") JOptionPane.showMessageDialog(vista,"Campos Vacios: "+msg);
				else
				{
					try {
						if (!servicioCliente.guardar(cliente)){
							JOptionPane.showMessageDialog(vista,"Razon Social Existente");
						}
						else
						{
						// agregarlo a la lista;
							vista.getBinClientes().unbind();
							vista.getBinClientes().bind();
							vista.activarBinding(servicioCliente.getClientes());   
							JOptionPane.showMessageDialog(vista,"Operacion Exitosa ");
						}
						vista.quitarNuevo();
					
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showConfirmDialog(null,e1.getMessage());
						e1.printStackTrace();
					}
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
	
	public ActionListener salir(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				qutarVista();
			}
		};
	}
	
	public ActionListener ruta(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
							try {
								contRutas= new ContRutas(contPrincipal);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
			}
		};
	}

	
}


