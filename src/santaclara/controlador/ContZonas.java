package santaclara.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import santaclara.Servicio.ServicioZona;
import santaclara.Servicio.ServicioJefeVenta;
import santaclara.modelo.JefeVenta;
import santaclara.modelo.Zona;
import santaclara.vista.ZonasUI;

public class ContZonas extends ContGeneral implements IContGeneral{
	
	private ServicioZona servicioZona;
	private ZonasUI vista;
	
	public ContZonas(ContPrincipal contPrincipal) throws Exception {
		// TODO Auto-generated constructor stub
		setContPrincipal(contPrincipal);
		servicioZona = new ServicioZona();
		vista = new ZonasUI(this,servicioZona.getZonas());
		vista.activarBinding(servicioZona.getZonas());
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
				vista.activarNuevoZona();
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
					Zona zona  = new Zona();
					try {
						zona = servicioZona.buscar(new Integer(vista.getTable().getValueAt(vista.getTable().getSelectedRow(),0).toString().trim()));
					} catch (NumberFormatException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (zona != null)
					{
						vista.activarNuevoZona();;
						vista.getScrollPanel().setVisible(false);
						
						vista.getTxtId().setText(zona.getId().toString());
						vista.getTxtNombre().setText(zona.getDescripcion());
					}
				}
				else
				{
					JOptionPane.showMessageDialog(vista,"Seleccione la Zona");
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
				Zona zona = new Zona();
				
				if (vista.getTxtId().getText().equals("")) zona.setId(null);
					else zona.setId(new Integer(vista.getTxtId().getText().toString())); 
				
				if (vista.getTxtNombre().getText().equals("")) JOptionPane.showMessageDialog(vista,"Campos Vacios: Nombre Material");
					else
						{
							try {
									zona.setDescripcion(vista.getTxtNombre().getText().toString());
									
									JOptionPane.showMessageDialog(vista,servicioZona.guardar(zona));
									// agregarlo a la lista
									vista.getZonas().add(zona);
									
									vista.getBinZonas().unbind();
									vista.getBinZonas().bind();
									vista.activarBinding(servicioZona.getZonas());
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

	public ServicioZona getServicioZonas() {
		return servicioZona;
	}

	public void setServicioZonas(
			ServicioZona servicioZonas) {
		this.servicioZona = servicioZonas;
	}

	
	public void setVista(ZonasUI vista) {
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
						Zona zona;
						zona = servicioZona.getZona(new Integer(vista.getTable().getValueAt(vista.getTable().getSelectedRow(),0).toString()));
						
						ServicioJefeVenta servicioJefeVenta = new ServicioJefeVenta();
						
						List<JefeVenta> jefeVentas = servicioJefeVenta.getJefeVentas();
						Boolean enc = new Boolean(false);
						for(JefeVenta jefeVenta: jefeVentas)
						{
							if(jefeVenta.getZona().getId().equals(zona.getId()))
								{
									enc=true;
									break;
								}
						}
						if(enc==false)
						{
							servicioZona.eliminar(zona);
						
							vista.getBinZonas().unbind();
							vista.getBinZonas().bind();				
							vista.activarBinding(servicioZona.getZonas());
							JOptionPane.showMessageDialog(vista,"Operacion Exitosa ");
							vista.quitarNuevo();
						}
						else JOptionPane.showMessageDialog(vista,"Operacion Fallida\n"+
								" Objeto Existente en otra Clase? \n Elimine la relacion Exixtente en: JefeVenta");
						
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
	}
}
