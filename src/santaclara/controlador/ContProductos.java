package santaclara.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import santaclara.Servicio.ServicioEmpaqueProducto;
import santaclara.Servicio.ServicioProducto;
import santaclara.dao.impl.PresentacionDAO;
import santaclara.modelo.Capacidad;
import santaclara.modelo.EmpaqueProducto;
import santaclara.modelo.Presentacion;
import santaclara.modelo.Producto;
import santaclara.modelo.Sabor;
import santaclara.vista.ProductosUI;

public class ContProductos extends ContGeneral implements IContGeneral {

	private ProductosUI vista;
	private ServicioProducto servicioProducto = new ServicioProducto();;
	
	private PresentacionDAO presentacionDAO = new PresentacionDAO();	
	
	public ContProductos(ContPrincipal contPrincipal) throws Exception {
		// TODO Auto-generated constructor stub
		setContPrincipal(contPrincipal);
		vista = new ProductosUI(this,servicioProducto.getProductos(),
					servicioProducto.getCapacidades(),
					servicioProducto.getSabores(),presentacionDAO.getPresentaciones());
		dibujar(vista,this);
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
				try {
					validar();
					Producto producto = new Producto();
					if (vista.getTxtId().getText().equals(""))
						producto.setId(null);
					else 
						producto.setId(new Integer(vista.getTxtId().getText().toString()));
				
					producto.setNombre(vista.getTxtNombre().getText());			
					producto.setPrecio((Double) vista.getTxtPrecio().getValue());
					producto.setCapacidad((Capacidad) vista.getCmbCapacidad().getSelectedItem());
					producto.setPresentacion((Presentacion)vista.getCmbPresentacion().getSelectedItem());
					producto.setSabor((Sabor)vista.getCmbSabor().getSelectedItem());
					producto.setIva(vista.getCheckIva().getState());
				
				    servicioProducto.guardar(producto);
					// agregarlo a la lista
					vista.getProductos().add(producto);
					vista.getBinProductos().unbind();
					vista.getBinProductos().bind();
					vista.activarBinding(servicioProducto.getProductos());
					vista.quitarNuevo();
					vista.getScrollPanel().setVisible(true);
				    JOptionPane.showMessageDialog(vista,"Operacion Exitosa ");
				} catch (Exception exe) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(vista,exe.getMessage());
					exe.printStackTrace();
				}
			}
		};
	}
	
	protected void validar() throws Exception {
		// TODO Auto-generated method stub
		if(vista.getTxtNombre().getText().trim().length() == 0)
		{
			throw new Exception("Ingrese nombre ");
		}
		if( (Double)(vista.getTxtPrecio().getValue()) <= 0.0 )
		{
			throw new Exception("Ingrese un monto superior a 1 Bsf. ");
		}
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
				if (vista.getTable().getSelectedRow()>0)
				{
					try {
						Producto producto;
						producto=servicioProducto.buscar(new Integer(vista.getTable().getValueAt(vista.getTable().getSelectedRow(),0).toString()));
						
						ServicioEmpaqueProducto servicioEmpaque = new ServicioEmpaqueProducto();
						
						List<EmpaqueProducto> empaqueProductos = servicioEmpaque.getEmpaqueProductos();
						Boolean enc = new Boolean(false);
						for(EmpaqueProducto empaqueProducto: empaqueProductos)
						{
							if(empaqueProducto.getProducto().getId().equals(producto.getId()))
								{
									enc=true;
									break;
								}
						}
						if(enc==false)
						{
							servicioProducto.eliminar(producto);
							vista.getBinProductos().unbind();
							vista.getBinProductos().bind();				
							vista.activarBinding(servicioProducto.getProductos());
							JOptionPane.showMessageDialog(vista,"Operacion Exitosa ");
							vista.quitarNuevo();
						}
						else JOptionPane.showMessageDialog(vista,"Operacion Fallida\n"+
								" Este producto esta siendo utilizado en otra clase ");
		
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
				quitarVista();
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
	
	@SuppressWarnings("rawtypes")
	public void setSelectedValue(JComboBox comboBox,Integer id)
    {	
        for (int i = 0; i < comboBox.getItemCount(); i++)
        {
        	comboBox.setSelectedIndex(i);
        	Boolean enc=false;
        	switch (comboBox.getSelectedItem().getClass().getName().toString()) 
        	{
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
						new ContPresentaciones(getContPrincipal());
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
						new ContCapacidades(getContPrincipal());
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
						new ContSabores(getContPrincipal());
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
				ActivarAtras(null);
			}
		};
	}

	public ActionListener quitarNuevo() {
		// TODO Auto-generated method stub
		return new  ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vista.quitarNuevo();
			}
		};
	}	
}

