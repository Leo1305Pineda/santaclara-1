package santaclara.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import santaclara.Servicio.ServicioProducto;
import santaclara.Servicio.ServicioSabor;
import santaclara.modelo.Producto;
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
						tabla1.getValueAt(i, 1).toString().equals(vista.getTxtABuscar().getText().toString()))
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
						Sabor sabor;
						sabor = servicioSabor.getSabor(new Integer(vista.getTable().getValueAt(vista.getTable().getSelectedRow(),0).toString()));
						
						ServicioProducto servicioProducto = new ServicioProducto();
						
						List<Producto> productos = servicioProducto.getProductos();
						Boolean enc = new Boolean(false);
						for(Producto producto: productos)
						{
							if(producto.getSabor().getId().equals(sabor.getId()))
								{
									enc=true;
									break;
								}
						}
						if(enc==false)
						{
							servicioSabor.eliminar(sabor);
						
							vista.getBinPresentaciones().unbind();
							vista.getBinPresentaciones().bind();				
							vista.activarBinding(servicioSabor.getSabores());
							JOptionPane.showMessageDialog(vista,"Operacion Exitosa ");
							vista.quitarNuevo();
						}
						else JOptionPane.showMessageDialog(vista,"Operacion Fallida\n"+
								" Objeto Existente en otra Clase? \n Elimine la relacion Exixtente en: EmpaqueProducto");
						
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
	}
}
