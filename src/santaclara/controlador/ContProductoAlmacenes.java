/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.swingbinding.JComboBoxBinding;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

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
	private List<ProductoAlmacen> productoAlmacenes = new ServicioProductoAlmacen().getProductoAlmacen();
	private List<Almacen> 		almacenes = new ServicioAlmacen().getAlmacenes();
	private List<EmpaqueProducto> empaqueProductos = new ServicioEmpaqueProducto().getEmpaqueProductos();
	
	private ProductoAlmacen productoAlmacen = new ProductoAlmacen();

	
	public ContProductoAlmacenes(ContPrincipal contPrincipal) throws Exception {
		// TODO Auto-generated constructor stub
		setContPrincipal(contPrincipal);
		vista = new ProductoAlmacenesUI(this);
		dibujar(vista,this);
		activarBinding(productoAlmacenes);
		cargarCmbAlmacen();
		cargarCmbEmpaqueProducto();
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
						
						productoAlmacenes = servicioProductoAlmacen.getProductoAlmacenes();
						
						activarBinding(productoAlmacenes);
					
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
				vista.setTable(buscar(vista.getTable(),vista.getTxtABuscar().getText().toString().trim()));
				Integer fila = new Integer(vista.getTable().getSelectedRow());
				if(fila>=0)
				{
					cargarProductoAlmacen(productoAlmacenes.get(fila));
				}
				else 
				{
					JOptionPane.showMessageDialog(new JPanel(),"No Encontrado");
					productoAlmacen = new ProductoAlmacen();
					cargarProductoAlmacen(productoAlmacen);
				}
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
						productoAlmacen = productoAlmacenes.get(vista.getTable().getSelectedRow());
						if (productoAlmacen!=null)
						{	
							servicioProductoAlmacen.eliminar(productoAlmacen);
							productoAlmacen = new ProductoAlmacen();
							productoAlmacenes = servicioProductoAlmacen.getProductoAlmacenes();
							activarBinding(productoAlmacenes);
							
							JOptionPane.showMessageDialog(vista,"Operacion Exitosa ");
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
				vista.getTable().clearSelection();
				productoAlmacen = new ProductoAlmacen();
				cargarProductoAlmacen(productoAlmacen);
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
				} catch (Exception e1) {
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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void activarBinding(List<ProductoAlmacen> productoAlmacenes) {
		// TODO Auto-generated method stub
		vista.remove(vista.getPanelProductoAlmacen());
		vista.getPnTabla().setVisible(true);
		vista.setTable(new JTable());
		vista.getScrollPanel().setViewportView(vista.getTable());
		
		JTableBinding binProductoAlmacenes = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,
    			productoAlmacenes,vista.getTable());
		
		BeanProperty idAlmacen = BeanProperty.create("idAlmacen");
		BeanProperty nombreAlmacen = BeanProperty.create("ubicacion");
		
		BeanProperty idEmpaqueProducto = BeanProperty.create("empaqueProducto.id");
	    BeanProperty nombreProducto = BeanProperty.create("empaqueProducto.producto.nombre");
	    BeanProperty presentacionProducto = BeanProperty.create("empaqueProducto.producto.presentacion.material");
	    BeanProperty capacidadProducto = BeanProperty.create("empaqueProducto.producto.capacidad.volumen");
	    BeanProperty saborProducto = BeanProperty.create("empaqueProducto.producto.sabor.sabor");
	    
	    BeanProperty stockProductAlmacen = BeanProperty.create("stock");
	    BeanProperty stockMinProductoAlmacen = BeanProperty.create("stockMin");
	    BeanProperty existenciaProductoAlmacen = BeanProperty.create("existencia");

	    binProductoAlmacenes.addColumnBinding(idAlmacen).setColumnClass(String.class).setColumnName("Id");
	    binProductoAlmacenes.addColumnBinding(nombreAlmacen).setColumnClass(String.class).setColumnName("Ubicacion del Almacen");
	    
	    binProductoAlmacenes.addColumnBinding(idEmpaqueProducto).setColumnClass(String.class).setColumnName("Id");
	    binProductoAlmacenes.addColumnBinding(nombreProducto).setColumnClass(String.class).setColumnName("Producto");
	    binProductoAlmacenes.addColumnBinding(presentacionProducto).setColumnClass(String.class).setColumnName("Presentacion");
	    binProductoAlmacenes.addColumnBinding(capacidadProducto).setColumnClass(String.class).setColumnName("Capacidad");
	    binProductoAlmacenes.addColumnBinding(saborProducto).setColumnClass(String.class).setColumnName("Sabor");
	    
	    binProductoAlmacenes.addColumnBinding(stockProductAlmacen).setColumnClass(String.class).setColumnName("Stock");
	    binProductoAlmacenes.addColumnBinding(stockMinProductoAlmacen).setColumnClass(String.class).setColumnName("Stock Minimo");
	    binProductoAlmacenes.addColumnBinding(existenciaProductoAlmacen).setColumnClass(String.class).setColumnName("Existencia");

	    binProductoAlmacenes.bind();

	    vista.getTable().addKeyListener(mostrarProductoAlmacen_keypress());
		vista.getTable().addMouseListener(mostrarEmpaqueProducto());
	  
	}

	@SuppressWarnings("rawtypes")
	public void cargarCmbAlmacen() {  
		    JComboBoxBinding jcomboAlmacenes = SwingBindings.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ,almacenes,vista.getCmbAlmacenes());
		    jcomboAlmacenes.bind();
	}

	@SuppressWarnings("rawtypes")
	public void cargarCmbEmpaqueProducto() {  
		JComboBoxBinding jcomboEmpaqueProductos = SwingBindings.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ,empaqueProductos,vista.getCmbEmpaqueProducto());
	    jcomboEmpaqueProductos.bind();}

	public MouseAdapter mostrarEmpaqueProducto() {
		// TODO Auto-generated method stub
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evento) {
				if (evento.getClickCount()==1) {
					cargarProductoAlmacen(productoAlmacenes.get(vista.getTable().getSelectedRow()));
				}
			}
		};
	}

	public KeyAdapter mostrarProductoAlmacen_keypress() {
		// TODO Auto-generated method stub
		return new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				Integer fila = new Integer(vista.getTable().getSelectedRow());
				Integer contFila = productoAlmacenes.size();
				 
				if(e.getKeyCode()==38 )
				{
					if(fila<=0)cargarProductoAlmacen(productoAlmacenes.get(0));
					else cargarProductoAlmacen(productoAlmacenes.get(fila-1));
				}
				else if(e.getKeyCode()==40 )
				{
					if(fila+1>=contFila)cargarProductoAlmacen(productoAlmacenes.get(contFila-1));
					else cargarProductoAlmacen(productoAlmacenes.get(fila+1));
				}

			}
		};
	}  

	public void cargarProductoAlmacen(ProductoAlmacen productoAlmacen) {
		// TODO Auto-generated method stub
		vista.remove(vista.getPanelProductoAlmacen());
		vista.dibujarProductoAlmacen();
		cargarCmbAlmacen();
		cargarCmbEmpaqueProducto();
		if (vista.getTable().getSelectedRow() >= 0)
		{
 			setSelectedValue(vista.getCmbEmpaqueProducto(),productoAlmacen.getEmpaqueProducto().getId());
			setSelectedValue(vista.getCmbAlmacenes(),productoAlmacen.getAlmacen().getId());
			vista.getTxtStock().setValue(productoAlmacen.getStock());
			vista.getTxtStockMin().setValue(productoAlmacen.getStockMin());
			vista.getTxtExistencia().setValue(productoAlmacen.getExistencia());
		}
		
	}
	
}

