package santaclara.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import santaclara.Servicio.ServicioPresentaciones;
import santaclara.modelo.Capacidad;
import santaclara.modelo.Presentacion;
import santaclara.modelo.Producto;
import santaclara.modelo.Sabor;
import santaclara.vista.PresentacionesUI;

public class ContPresentaciones extends ContGeneral implements IContGeneral{
	
	private ServicioPresentaciones servicioPresentaciones;
	private PresentacionesUI vista;
	
	public ContPresentaciones(ContPrincipal contPrincipal) throws Exception {
		// TODO Auto-generated constructor stub
		setContPrincipal(contPrincipal);
		servicioPresentaciones = new ServicioPresentaciones();
		vista = new PresentacionesUI(this,servicioPresentaciones.getPresentaciones());
		vista.activarBinding(servicioPresentaciones.getPresentaciones());
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
				vista.activarNuevoPresentacion();
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
					Presentacion presentacion  = new Presentacion();
					try {
						presentacion = servicioPresentaciones.buscar(new Integer(vista.getTable().getValueAt(vista.getTable().getSelectedRow(),0).toString().trim()));
					} catch (NumberFormatException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (presentacion != null)
					{
						vista.activarNuevoPresentacion();
						vista.getScrollPanel().setVisible(false);
						
						vista.getTxtId().setText(presentacion.getId().toString());
						vista.getTxtNombre().setText(presentacion.getMaterial());
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
				Presentacion presentacion = new Presentacion();
				String msg="";
				
				if (vista.getTxtId().getText().equals("")) presentacion.setId(null);
					else presentacion.setId(new Integer(vista.getTxtId().getText().toString())); 
				
				if (vista.getTxtNombre().getText().equals("")) JOptionPane.showMessageDialog(vista,"Campos Vacios: Nombre Material");
					else
						{
							try {
									presentacion.setMaterial(vista.getTxtNombre().getText().toString());
									
									JOptionPane.showMessageDialog(vista,servicioPresentaciones.guardar(presentacion));
									// agregarlo a la lista
									vista.getPresentaciones().add(presentacion);
									
									vista.getBinPresentaciones().unbind();
									vista.getBinPresentaciones().bind();
									vista.activarBinding(servicioPresentaciones.getPresentaciones());
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

	public ServicioPresentaciones getServicioPresentaciones() {
		return servicioPresentaciones;
	}

	public void setServicioPresentaciones(
			ServicioPresentaciones servicioPresentaciones) {
		this.servicioPresentaciones = servicioPresentaciones;
	}

	
	public void setVista(PresentacionesUI vista) {
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
}
