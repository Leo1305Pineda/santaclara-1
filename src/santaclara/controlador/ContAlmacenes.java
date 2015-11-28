package santaclara.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import santaclara.Servicio.ServicioAlmacen;
import santaclara.modelo.Almacen;
import santaclara.vista.AlmacenesUI;

public class ContAlmacenes extends ContGeneral implements IContGeneral{
	
	private ServicioAlmacen servicioAlmacen;
	private AlmacenesUI vista;
	
	public ContAlmacenes() throws NumberFormatException, IOException{
		servicioAlmacen = new ServicioAlmacen();
		vista = new AlmacenesUI(this,servicioAlmacen.getAlmacenes());
	}
	
	public ContAlmacenes(ContPrincipal contPrincipal) throws Exception {
		// TODO Auto-generated constructor stub
		setContPrincipal(contPrincipal);
		servicioAlmacen = new ServicioAlmacen();
		vista = new AlmacenesUI(this,servicioAlmacen.getAlmacenes());
		dibujar(vista,this);
	}

	@Override
	public JPanel getVista() {
		// TODO Auto-generated method stub
		return vista;
	}
	
	public ActionListener nuevo(){
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				vista.activarNuevoAlmacen();
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
					Almacen almacen  = new Almacen();
					try {
						almacen = servicioAlmacen.buscar(new Integer(vista.getTable().getValueAt(vista.getTable().getSelectedRow(),0).toString().trim()));
						if (almacen != null)
						{
							vista.activarNuevoAlmacen();
							vista.getScrollPanel().setVisible(false);
							vista.getTxtId().setText(almacen.getId().toString());
							vista.getTxtUbicacion().setText(almacen.getUbicacion());
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(vista,e1.getMessage());
						e1.printStackTrace();
					}
				}
				else
				{
					JOptionPane.showMessageDialog(vista,"Seleccione el Almacen");
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
				Almacen almacen = new Almacen();
				
				if (vista.getTxtId().getText().equals("")) almacen.setId(null);
					else almacen.setId(new Integer(vista.getTxtId().getText().toString())); 
				
				if (vista.getTxtUbicacion().getText().equals(""))
					JOptionPane.showMessageDialog(vista,"Campos Vacios: Ubicacion");
				else
				{
					try {								
						almacen.setUbicacion(vista.getTxtUbicacion().getText().toString());								
						JOptionPane.showMessageDialog(vista,servicioAlmacen.guardar(almacen));
						// agregarlo a la lista
						vista.getAlmacenes().add(almacen);
						vista.getBinAlmacenes().unbind();
						vista.getBinAlmacenes().bind();
						vista.activarBinding(servicioAlmacen.getAlmacenes());
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

	public ServicioAlmacen getServicioAlmacenes() {
		return servicioAlmacen;
	}

	public void setServicioAlmacen(
			ServicioAlmacen servicioAlmacen) {
		this.servicioAlmacen = servicioAlmacen;
	}

	
	public void setVista(AlmacenesUI vista) {
		this.vista = vista;
	}

	public ActionListener atras() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					if (vista.getTable().getSelectedRow()>=0)
					{
						Almacen almacen = new Almacen();
						almacen = servicioAlmacen.buscar(new Integer(vista.getTable().getValueAt(vista.getTable().getSelectedRow(),0).toString().trim()));
						ActivarAtras(almacen);
					}
					else ActivarAtras(null);
				} catch (NumberFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
					if (vista.getTable().getSelectedRow()>=0)
					{
						Almacen almacen;
						almacen = servicioAlmacen.buscar(new Integer(vista.getTable().getValueAt(vista.getTable().getSelectedRow(),0).toString().trim()));
						if (almacen != null)
						{
							almacen = servicioAlmacen.buscar(new Integer(vista.getTable().getValueAt(vista.getTable().getSelectedRow(),0).toString()));
							servicioAlmacen.eliminar(almacen);
							vista.getBinAlmacenes().unbind();
							vista.getBinAlmacenes().bind();				
							vista.activarBinding(servicioAlmacen.getAlmacenes());
							JOptionPane.showMessageDialog(vista,"Operacion Exitosa ");
							vista.quitarNuevo();
						}
					}
					else
					{
						JOptionPane.showMessageDialog(vista,"Seleccione el Almacen");
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
	}

	public ActionListener quitarNuevo() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				vista.quitarNuevo();
			}
		};
	}
}
