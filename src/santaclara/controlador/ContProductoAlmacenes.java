package santaclara.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import santaclara.Servicio.ServicioAlmacen;
import santaclara.Servicio.ServicioEmpaqueProducto;
import santaclara.Servicio.ServicioProductoAlmacen;
import santaclara.modelo.Almacen;
import santaclara.modelo.EmpaqueProducto;
import santaclara.modelo.ProductoAlmacen;
import santaclara.vista.ProductoAlmacenesUI;

public class ContProductoAlmacenes extends ContGeneral implements IContGeneral {

	private ProductoAlmacenesUI vista;
	private ServicioProductoAlmacen servicioProductoAlmacen = new ServicioProductoAlmacen();
	private ServicioEmpaqueProducto servicioEmpaqueProducto = new ServicioEmpaqueProducto();
	private ServicioAlmacen servicioAlmacen = new ServicioAlmacen();
	
	public ContProductoAlmacenes(ContPrincipal contPrincipal) throws Exception {
		// TODO Auto-generated constructor stub
		setContPrincipal(contPrincipal);
		vista = new ProductoAlmacenesUI(this,servicioProductoAlmacen.getProductoAlmacenes(),
				servicioEmpaqueProducto.getEmpaqueProductos(),servicioAlmacen.getAlmacenes());
	
		vista.activarBinding(servicioProductoAlmacen.getProductoAlmacenes());
		dibujar(vista,this);
		vista.quitarNuevo();
	}

	@Override
	public ProductoAlmacenesUI getVista() {
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

				ProductoAlmacen productoAlmacen = new ProductoAlmacen();
				
				productoAlmacen.setStock((Integer) vista.getTxtStock().getValue());
				productoAlmacen.setStockMin((Integer) vista.getTxtStockMin().getValue());
				productoAlmacen.setExistencia((Integer) vista.getTxtExistencia().getValue());
				productoAlmacen.setEmpaqueProducto((EmpaqueProducto)vista.getCmbEmpaqueProducto().getSelectedItem());
				productoAlmacen.setAlmacen((Almacen)vista.getCmbAlmacenes().getSelectedItem());
				
				try {
						JOptionPane.showMessageDialog(vista,servicioProductoAlmacen.guardar(productoAlmacen));
						vista.getProductoAlmacenes().add(productoAlmacen);
						vista.getBinProductoAlmacenes().unbind();
						vista.getBinProductoAlmacenes().bind();
						vista.activarBinding(servicioProductoAlmacen.getProductoAlmacenes());
						vista.quitarNuevo();
					
				} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
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
					if (tabla1.getValueAt(i, 0).toString().trim().equals(vista.getTxtABuscar().getText().toString().trim())||
						tabla1.getValueAt(i, 1).toString().equals(vista.getTxtABuscar().getText().toString())||
						tabla1.getValueAt(i, 2).toString().trim().equals(vista.getTxtABuscar().getText().toString().trim()))
						//faltan las demas columnas
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
						ProductoAlmacen productoAlmacen;
						productoAlmacen=servicioProductoAlmacen.getProductoAlmacen(
								new Integer(vista.getTable().getValueAt(vista.getTable().getSelectedRow(),2).toString()),
								new Integer(vista.getTable().getValueAt(vista.getTable().getSelectedRow(),0).toString()));
						if (productoAlmacen!=null)
						{	
							servicioProductoAlmacen.eliminar(productoAlmacen);
							vista.getBinProductoAlmacenes().unbind();
							vista.getBinProductoAlmacenes().bind();				
							vista.activarBinding(servicioProductoAlmacen.getProductoAlmacenes());
							JOptionPane.showMessageDialog(vista,"Operacion Exitosa ");
							vista.quitarNuevo();
						}
						else JOptionPane.showMessageDialog(vista,"Operacion Fallida");
		
					} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else
				{
					JOptionPane.showMessageDialog(vista,"Seleccione el EmpaqueProducto ");
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
				vista.activarNuevoProductoAlmacen();
				vista.getTxtStock().setValue(1);
				vista.getTxtStockMin().setValue(1);
				vista.getTxtExistencia().setValue(1);
				vista.getPnTabla().setVisible(false);
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
							ProductoAlmacen productoAlmacen = new ProductoAlmacen();
							productoAlmacen = servicioProductoAlmacen.getProductoAlmacen(
									new Integer(vista.getTable().getValueAt(vista.getTable().getSelectedRow(),2).toString()),
									new Integer(vista.getTable().getValueAt(vista.getTable().getSelectedRow(),0).toString()));
							if (productoAlmacen != null)
							{	
								setSelectedValue(vista.getCmbEmpaqueProducto(),productoAlmacen.getEmpaqueProducto().getId());
								setSelectedValue(vista.getCmbAlmacenes(),productoAlmacen.getAlmacen().getId());
								vista.getTxtStock().setValue(productoAlmacen.getStock());
								vista.getTxtStockMin().setValue(productoAlmacen.getStockMin());
								vista.getTxtExistencia().setValue(productoAlmacen.getExistencia());
								vista.activarNuevoProductoAlmacen();
							}
							else JOptionPane.showMessageDialog(vista,"No Encontrado\n"+"Almacen ");
						}
						else
						{
							JOptionPane.showMessageDialog(vista,"Seleccione el Empaque del Producto");
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
        	switch (comboBox.getSelectedItem().getClass().getName().toString()) {
			case "santaclara.modelo.EmpaqueProducto":
				enc = (((EmpaqueProducto)comboBox.getSelectedItem()).getId().equals(id)); 
					break;
			case "santaclara.modelo.Almacen":
				enc = (((Almacen)comboBox.getSelectedItem()).getId().equals(id)); 
					break;
			default:
				break;
			}
        	if (enc) break;
        }
    }

	public ActionListener Atras() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					if (vista.getTable().getSelectedRow()>=0)
					{
						ProductoAlmacen productoAlmacen = new ProductoAlmacen();
						productoAlmacen = servicioProductoAlmacen.getProductoAlmacen(
								new Integer(vista.getTable().getValueAt(vista.getTable().getSelectedRow(),2).toString()),
								new Integer(vista.getTable().getValueAt(vista.getTable().getSelectedRow(),0).toString()));
						ActivarAtras(productoAlmacen);
					}
					else ActivarAtras(null);
				} catch (NumberFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
	}

	public ActionListener AbrirEmpaqueProducto() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					new ContEmpaqueProductos(getContPrincipal());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
	}

	public ActionListener AbrirAlmacen() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					new ContAlmacenes(getContPrincipal());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
	}

}

