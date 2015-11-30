package santaclara.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import santaclara.Servicio.ServicioCapacidad;
import santaclara.Servicio.ServicioProducto;
import santaclara.modelo.Capacidad;
import santaclara.modelo.Producto;
import santaclara.vista.CapacidadesUI;

public class ContCapacidades extends ContGeneral implements IContGeneral{
	
	private ServicioCapacidad servicioCapacidad;
	private CapacidadesUI vista;
	
	public ContCapacidades(ContPrincipal contPrincipal) throws Exception {
		// TODO Auto-generated constructor stub
		setContPrincipal(contPrincipal);
		servicioCapacidad = new ServicioCapacidad();
		vista = new CapacidadesUI(this,servicioCapacidad.getCapacidades());
		vista.activarBinding(servicioCapacidad.getCapacidades());
		dibujar(vista,this);
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
				vista.activarNuevoCapacidad();
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
					Capacidad capacidad  = new Capacidad();
					try {
						capacidad = servicioCapacidad.buscar(new Integer(vista.getTable().getValueAt(vista.getTable().getSelectedRow(),0).toString().trim()));
					} catch (NumberFormatException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (capacidad != null)
					{
						vista.activarNuevoCapacidad();
						vista.getScrollPanel().setVisible(false);
						
						vista.getTxtId().setText(capacidad.getId().toString());
						vista.getTxtVolumen().setText(capacidad.getVolumenStr());
					}
				}
				else
				{
					JOptionPane.showMessageDialog(vista,"Seleccione la Capacidad");
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
				Capacidad capacidad = new Capacidad();
				
				if (vista.getTxtId().getText().equals("")) capacidad.setId(null);
					else capacidad.setId(new Integer(vista.getTxtId().getText().toString())); 
				
				if (vista.getTxtVolumen().getText().equals("")) JOptionPane.showMessageDialog(vista,"Campos Vacios: Volumen");
					else
						{
							try {
									capacidad.setVolumen(new Double(vista.getTxtVolumen().getText().toString()));
									
									JOptionPane.showMessageDialog(vista,servicioCapacidad.guardar(capacidad));
									// agregarlo a la lista
									vista.getCapacidades().add(capacidad);
									
									vista.getBinCapacidades().unbind();
									vista.getBinCapacidades().bind();
									vista.activarBinding(servicioCapacidad.getCapacidades());
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

	public ServicioCapacidad getServicioPresentaciones() {
		return servicioCapacidad;
	}

	public void setServicioCapacidad(
			ServicioCapacidad servicioCapacidad) {
		this.servicioCapacidad = servicioCapacidad;
	}

	
	public void setVista(CapacidadesUI vista) {
		this.vista = vista;
	}

	public ActionListener atras() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ActivarAtras(null);
			}
		};
	}
	
	public ActionListener salir(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				quitarVista();
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
						Capacidad capacidad;
						capacidad = servicioCapacidad.getCapacidad(new Integer(vista.getTable().getValueAt(vista.getTable().getSelectedRow(),0).toString()));
						
						ServicioProducto servicioProducto = new ServicioProducto();
						
						List<Producto> productos = servicioProducto.getProductos();
						Boolean enc = new Boolean(false);
						for(Producto producto1: productos)
						{
							if(producto1.getCapacidad().getId().equals(capacidad.getId()))
								{
									enc=true;
									break;
								}
						}
						if(enc==false)
						{
							servicioCapacidad.eliminar(capacidad);
							
							vista.getBinCapacidades().unbind();
							vista.getBinCapacidades().bind();				
							vista.activarBinding(servicioCapacidad.getCapacidades());
							JOptionPane.showMessageDialog(vista,"Operacion Exitosa ");
							vista.quitarNuevo();
						}
						else JOptionPane.showMessageDialog(vista,"Operacion Fallida\n"+
								" Objeto Existente en otra Clase? \n Elimine la relacion Exixtente en: Producto");
						
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
	}
}
