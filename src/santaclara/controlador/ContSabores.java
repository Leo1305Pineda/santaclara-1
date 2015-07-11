package santaclara.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import santaclara.Servicio.ServicioSabor;
import santaclara.modelo.Sabor;
import santaclara.vista.SaboresUI;

public class ContSabores extends ContGeneral implements IContGeneral{
	
	private ServicioSabor servicioSabor;
	private SaboresUI vista;
	
	public ContSabores(ContPrincipal contPrincipal) throws Exception {
		// TODO Auto-generated constructor stub
		setContPrincipal(contPrincipal);
		servicioSabor = new ServicioSabor();
		vista = new SaboresUI(this,servicioSabor.getSabores());
		vista.activarBinding(servicioSabor.getSabores());
		dibujar(vista);
		vista.quitarNuevo();
	}

	@Override
	public JPanel getVista() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ActionListener nuevo(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				vista.activarNuevoSabor();
				vista.getPnTabla().setVisible(false);
			}
		};
	}

	public ActionListener modificar() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (vista.getTable().getSelectedRow()>=0)
				{
					Sabor Sabor  = new Sabor();
					try {
						Sabor = servicioSabor.buscar(new Integer(vista.getTable().getValueAt(vista.getTable().getSelectedRow(),0).toString().trim()));
					} catch (NumberFormatException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (Sabor != null)
					{
						vista.activarNuevoSabor();
						vista.getScrollPanel().setVisible(false);
						
						vista.getTxtId().setText(Sabor.getId().toString());
						vista.getTxtSabor().setText(Sabor.getSabor());
					}
				}
				else
				{
					JOptionPane.showMessageDialog(vista,"Seleccione el Producto");
				}
		}
		};
	}

	public ActionListener guardar() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Sabor Sabor = new Sabor();
				
				if (vista.getTxtId().getText().equals("")) Sabor.setId(null);
					else Sabor.setId(new Integer(vista.getTxtId().getText().toString())); 
				
				if (vista.getTxtSabor().getText().equals("")) JOptionPane.showMessageDialog(vista,"Campos Vacios: Nombre Material");
					else
						{
							try {
									Sabor.setSabor(vista.getTxtSabor().getText().toString());
									
									JOptionPane.showMessageDialog(vista,servicioSabor.guardar(Sabor));
									// agregarlo a la lista
									vista.getSabores().add(Sabor);
									
									vista.getBinPresentaciones().unbind();
									vista.getBinPresentaciones().bind();
									vista.activarBinding(servicioSabor.getSabores());
									vista.quitarNuevo();
									vista.getScrollPanel().setVisible(true);
									
								} catch (IOException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showConfirmDialog(null,e1.getMessage());
								e1.printStackTrace();
								}
						}
			}
		};
	}

	public ActionListener buscar() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JTable tabla1 = new JTable();
				tabla1 = vista.getTable();
				Boolean enc = false;
				for(int i = 0;i<tabla1.getRowCount();i++)
				{
					if (tabla1.getValueAt(i, 0).toString().trim().equals(vista.getTxtABuscar().getText().toString().trim())||
						tabla1.getValueAt(i, 1).toString().trim().equals(vista.getTxtABuscar().getText().toString().trim()))
					{
						tabla1.setRowSelectionInterval(i,i);
						enc = true;
						break;
					}
				}
				if (!enc) JOptionPane.showMessageDialog(vista,"No Encontrado");
				vista.setTable(tabla1);
				vista.setTxtABuscar("");;
				
			}
		};
	}

	public ServicioSabor getServicioSabores() {
		return servicioSabor;
	}

	public void setServicioSabor(ServicioSabor servicioSabor) {
		this.servicioSabor = servicioSabor;
	}

	
	public void setVista(SaboresUI vista) {
		this.vista = vista;
	}

	public ActionListener atras() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ActivarAtras();
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

	public ActionListener eliminar() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
						Sabor Sabor;
						Sabor = servicioSabor.getSabor(new Integer(vista.getTable().getValueAt(vista.getTable().getSelectedRow(),0).toString()));
				
						servicioSabor.eliminar(Sabor);
						
						vista.getBinPresentaciones().unbind();
						vista.getBinPresentaciones().bind();				
						vista.activarBinding(servicioSabor.getSabores());
						JOptionPane.showMessageDialog(vista,"Operacion Exitosa ");
						vista.quitarNuevo();
						
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
	}
}
