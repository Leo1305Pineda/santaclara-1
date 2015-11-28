package santaclara.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import santaclara.Servicio.ServicioPresentacion;
import santaclara.Servicio.ServicioProducto;
import santaclara.modelo.Presentacion;
import santaclara.modelo.Producto;
import santaclara.vista.PresentacionesUI;

public class ContPresentaciones extends ContGeneral implements IContGeneral{
	
	private ServicioPresentacion servicioPresentacion;
	private PresentacionesUI vista;
	
	public ContPresentaciones(ContPrincipal contPrincipal) throws Exception {
		// TODO Auto-generated constructor stub
		setContPrincipal(contPrincipal);
		servicioPresentacion = new ServicioPresentacion();
		vista = new PresentacionesUI(this,servicioPresentacion.getPresentaciones());
		dibujar(vista,this);
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
						presentacion = servicioPresentacion.buscar(new Integer(vista.getTable().getValueAt(vista.getTable().getSelectedRow(),0).toString().trim()));
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
					JOptionPane.showMessageDialog(vista,"Seleccione la fila");
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
				
				if (vista.getTxtId().getText().equals("")) presentacion.setId(null);
					else presentacion.setId(new Integer(vista.getTxtId().getText().toString())); 
				
				if (vista.getTxtNombre().getText().equals("")) JOptionPane.showMessageDialog(vista,"Campos Vacios: Nombre Material");
					else
						{
							try {
									presentacion.setMaterial(vista.getTxtNombre().getText().toString());
									
									JOptionPane.showMessageDialog(vista,servicioPresentacion.guardar(presentacion));
									// agregarlo a la lista
									vista.getPresentaciones().add(presentacion);
									
									vista.getBinPresentaciones().unbind();
									vista.getBinPresentaciones().bind();
									vista.activarBinding(servicioPresentacion.getPresentaciones());
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

	public ServicioPresentacion getServicioPresentaciones() {
		return servicioPresentacion;
	}

	public void setServicioPresentaciones(
			ServicioPresentacion servicioPresentaciones) {
		this.servicioPresentacion = servicioPresentaciones;
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
						Presentacion presentacion;
						presentacion = servicioPresentacion.getPresentacion(new Integer(vista.getTable().getValueAt(vista.getTable().getSelectedRow(),0).toString()));
						
						ServicioProducto servicioProducto = new ServicioProducto();
						
						List<Producto> productos = servicioProducto.getProductos();
						Boolean enc = new Boolean(false);
						for(Producto producto: productos)
						{
							if(producto.getPresentacion().getId().equals(presentacion.getId()))
								{
									enc=true;
									break;
								}
						}
						if(enc==false)
						{
							servicioPresentacion.eliminar(presentacion);
						
							vista.getBinPresentaciones().unbind();
							vista.getBinPresentaciones().bind();				
							vista.activarBinding(servicioPresentacion.getPresentaciones());
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
