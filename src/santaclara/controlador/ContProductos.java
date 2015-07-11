package santaclara.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import santaclara.Servicio.ServicioProducto;
import santaclara.dao.impl.PresentacionDAO;
import santaclara.modelo.Capacidad;
import santaclara.modelo.Presentacion;
import santaclara.modelo.Producto;
import santaclara.modelo.Sabor;
import santaclara.vista.ProductosUI;

public class ContProductos extends ContGeneral implements IContGeneral {

	private ProductosUI vista;
	private ServicioProducto servicioProducto = new ServicioProducto();;
	private ContPresentaciones contPresentaciones;
	private ContCapacidades contCapacidades;
	private ContSabores contSabores; 
	private PresentacionDAO presentacionDAO = new PresentacionDAO();	
	
	public ContProductos(ContPrincipal contPrincipal) throws Exception {
		// TODO Auto-generated constructor stub
		setContPrincipal(contPrincipal);
		vista = new ProductosUI(this,servicioProducto.getProductos(),
					servicioProducto.getCapacidades(),
					servicioProducto.getSabores(),presentacionDAO.getPresentaciones());
		vista.activarBinding(servicioProducto.getProductos());
		dibujar(vista);
		vista.quitarNuevo();
	}

	@Override
	public JPanel getVista() {
		// TODO Auto-generated method stub
		return vista;
	}

  // evento Guardar Prodcuto 
	public ActionListener guardar() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// se va hacer las validaciones del controlador 

				Producto producto = new Producto();
				String msg="";
				
				if (vista.getTxtId().getText().equals("")) producto.setId(null);
					else producto.setId(new Integer(vista.getTxtId().getText().toString()));
				
				if (vista.getTxtNombre().getText().equals(""))msg=" Nombre ";
					else producto.setNombre(vista.getTxtNombre().getText());
				
				
				if(vista.getTxtPrecio().getValue().equals(0))producto.setPrecio(0.0);
					else producto.setPrecio((Double) vista.getTxtPrecio().getValue());
				
				producto.setCapacidad((Capacidad) vista.getCmbCapacidad().getSelectedItem());
				producto.setPresentacion((Presentacion)vista.getCmbPresentacion().getSelectedItem());
				producto.setSabor((Sabor)vista.getCmbSabor().getSelectedItem());
				
				if (msg!="") JOptionPane.showMessageDialog(vista,"Campos Vacios: "+msg);
					else
						{
							try {
									JOptionPane.showMessageDialog(vista,servicioProducto.guardar(producto));
									// agregarlo a la lista
									vista.getProductos().add(producto);
									vista.getBinProductos().unbind();
									vista.getBinProductos().bind();
									vista.activarBinding(servicioProducto.getProductos());
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
				vista.setTable(tabla1);
				vista.setTxtABuscar("");
				vista.quitarNuevo();
			}
		};
	}
	
	public ActionListener eliminar() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (vista.getTable().getSelectedRow()>=0)
				{
					try {
						Producto producto;
						producto=servicioProducto.buscar(new Integer(vista.getTable().getValueAt(vista.getTable().getSelectedRow(),0).toString()));
												
						servicioProducto.eliminar(producto);
				
						vista.getBinProductos().unbind();
						vista.getBinProductos().bind();				
						vista.activarBinding(servicioProducto.getProductos());
						JOptionPane.showMessageDialog(vista,"Operacion Exitosa ");
						vista.quitarNuevo();
		
					} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					}
				}
				else
				{
					JOptionPane.showMessageDialog(vista,"Seleccione el Producto ");
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
				vista.activarNuevoProducto();
				vista.getScrollPanel().setVisible(false);
			}
		};
	}
	
	public ActionListener salir() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				qutarVista();
			}
		};
	}

	public ActionListener modificar(){
	return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
						if (vista.getTable().getSelectedRow()>=0)
						{
							Producto producto = new Producto();
							producto = servicioProducto.buscar(new Integer(vista.getTable().getValueAt(vista.getTable().getSelectedRow(),0).toString()));
							if (producto != null)
							{
								vista.activarNuevoProducto();
								vista.getScrollPanel().setVisible(false);
								
								vista.getTxtId().setText(producto.getId().toString());
								vista.getTxtNombre().setText(producto.getNombre());
								vista.getTxtPrecio().setValue(producto.getPrecio());
								
								setSelectedValue(vista.getCmbPresentacion(),producto.getPresentacion().getId());
								
								setSelectedValue(vista.getCmbCapacidad(),producto.getCapacidad().getId());
								
								setSelectedValue(vista.getCmbSabor(),producto.getSabor().getId());
							}
						}
						else
						{
							JOptionPane.showMessageDialog(vista,"Seleccione el Producto");
						}	
				}catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}; 

	}
	
	public void setSelectedValue(JComboBox comboBox,Integer id)
    {	
        for (int i = 0; i < comboBox.getItemCount(); i++)
        {
        	comboBox.setSelectedIndex(i);
        	Boolean enc=false;
        	switch (comboBox.getSelectedItem().getClass().getName().toString()) {
			case "santaclara.modelo.Presentacion":
				enc = (((Presentacion)comboBox.getSelectedItem()).getId().equals(id)); 
					break;
			case "santaclara.modelo.Sabor":
				enc = (((Sabor)comboBox.getSelectedItem()).getId().equals(id));
					break;
			case "santaclara.modelo.Capacidad":
				enc = (((Capacidad)comboBox.getSelectedItem()).getId().equals(id));
					break;
			default:
				break;
			}
        	if (enc) break;
        }
    }

	public ActionListener AbrirPresentaciones() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
						contPresentaciones = new ContPresentaciones(getContPrincipal());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
	}

	public ActionListener AbrirCapacidades() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					contCapacidades = new ContCapacidades(getContPrincipal());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		};
	}

	public ActionListener AbrirSabor() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					contSabores = new ContSabores(getContPrincipal());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		};
	}

	public ActionListener Atras() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ActivarAtras();
			}
		};
	}	
}

