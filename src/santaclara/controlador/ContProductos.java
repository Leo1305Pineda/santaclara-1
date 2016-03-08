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
import java.text.SimpleDateFormat;
import java.util.Date;
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

import santaclara.Servicio.ServicioCapacidad;
import santaclara.Servicio.ServicioEmpaqueProducto;
import santaclara.Servicio.ServicioPresentacion;
import santaclara.Servicio.ServicioProducto;
import santaclara.Servicio.ServicioSabor;
import santaclara.modelo.Capacidad;
import santaclara.modelo.Producto;
import santaclara.modelo.EmpaqueProducto;
import santaclara.modelo.Presentacion;
import santaclara.modelo.Sabor;
import santaclara.vista.ProductosUI;

public class ContProductos extends ContGeneral implements IContGeneral {

	private ProductosUI vista;
	private Producto producto;
	private ServicioProducto servicioProducto = new ServicioProducto();;
	private List<Producto> productos = new ServicioProducto().getProductos();
	private List<Sabor> sabores = new ServicioSabor().getSabores();
	private List<Capacidad> capacidades = new ServicioCapacidad().getCapacidades();
	private List<Presentacion> presentaciones = new ServicioPresentacion().getPresentaciones();
	private List<EmpaqueProducto> empaqueProductos = new ServicioEmpaqueProducto().getEmpaqueProductos();
	String inicio = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date());
	@SuppressWarnings("rawtypes")
	private JTableBinding   binProductos;
	
	public ContProductos(ContPrincipal contPrincipal) throws Exception {
		// TODO Auto-generated constructor stub
		setContPrincipal(contPrincipal);
		vista = new ProductosUI(this);
		dibujar(vista,this);
		productos = new ServicioProducto().getProductos();
		activarBinding(productos);
		sabores = new ServicioSabor().getSabores();
		cargarCmbSabor();
		presentaciones = new ServicioPresentacion().getPresentaciones();
		cargarCmbPresentacion();
		capacidades = new ServicioCapacidad().getCapacidades();
		cargarCmbCapacidad();
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
				    
				    productos = servicioProducto.getProductos();
				    
					// agregarlo a la lista
					activarBinding(productos);
				//	vista.quitarNuevo();
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

	
	
	public ActionListener eliminar() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (vista.getTable().getSelectedRow()>0)
				{
					try {
						producto=productos.get(vista.getTable().getSelectedRow());
						
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
							productos =servicioProducto.getProductos();
							producto = new Producto();
							activarBinding(productos);
							JOptionPane.showMessageDialog(vista,"Operacion Exitosa ");
						}
						else JOptionPane.showMessageDialog(vista,"Operacion Fallida\n"+
								" Este producto esta siendo utilizado en otra clase ");
		
					} catch (Exception e1) {
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
				vista.getTable().clearSelection();
				producto = new Producto();
				cargarProducto(producto);
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
	
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public void activarBinding(List<Producto> lsProductos) {
		// TODO Auto-generated method stub
		this.productos=lsProductos;
    	vista.getPnTabla().setVisible(true);
		vista.setTable(new JTable());
		vista.getScrollPanel().setViewportView(vista.getTable());
		
		binProductos = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,lsProductos,vista.getTable());
	    BeanProperty nombreProducto = BeanProperty.create("nombre");
	    BeanProperty idProducto  = BeanProperty.create("id");
	    BeanProperty capacidadProducto  = BeanProperty.create("capacidad.volumenStr");
	    BeanProperty presentacionProducto = BeanProperty.create("presentacion.material");
	    BeanProperty saborProducto = BeanProperty.create("sabor.sabor");
	    BeanProperty precioProducto = BeanProperty.create("precioStr");

	    binProductos.addColumnBinding(idProducto).setColumnClass(Integer.class).setColumnName("id");;
	    binProductos.addColumnBinding(nombreProducto).setColumnClass(String.class).setColumnName("Nombre");
	    binProductos.addColumnBinding(capacidadProducto).setColumnClass(String.class).setColumnName("Capacidad");
	    binProductos.addColumnBinding(presentacionProducto).setColumnClass(String.class).setColumnName("Presentacion");;
	    binProductos.addColumnBinding(saborProducto).setColumnClass(String.class).setColumnName("Sabor");;
	    binProductos.addColumnBinding(precioProducto).setColumnClass(String.class).setColumnName("Precio");;

	    binProductos.bind();
		vista.getTable().addKeyListener(mostrarProducto_keypress());
		vista.getTable().addMouseListener(mostrarProducto());
	    
	    vista.remove(vista.getPnlProducto());
		vista.repaint();
	}
    
    @SuppressWarnings("rawtypes")
	public void cargarCmbSabor(){
    	JComboBoxBinding jcomboSabor = SwingBindings.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ,sabores,vista.getCmbSabor());
	    jcomboSabor.bind();
	    
    }
    @SuppressWarnings("rawtypes")
	public void cargarCmbCapacidad(){
	    JComboBoxBinding jcomboCapacidad = SwingBindings.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ,capacidades,vista.getCmbCapacidad());
	    jcomboCapacidad.bind();	
    }
    
    @SuppressWarnings("rawtypes")
	public void cargarCmbPresentacion(){
	    JComboBoxBinding jcomboPresentacion = SwingBindings.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ,presentaciones,vista.getCmbPresentacion());
	    jcomboPresentacion.bind();
    }
    
	public MouseAdapter mostrarProducto() {
		// TODO Auto-generated method stub
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evento) {
				if (evento.getClickCount()==1)
				{
					producto = (Producto) productos.get(vista.getTable().getSelectedRow());
					cargarProducto(producto);
				}
			}
		};
	}

	public KeyAdapter mostrarProducto_keypress() {
		// TODO Auto-generated method stub
		return new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				Integer fila = new Integer(vista.getTable().getSelectedRow());
				Integer contFila = productos.size();
				 
				if(e.getKeyCode()==38 )
				{
					if(fila<=0) cargarProducto((Producto) productos.get(0));
					else cargarProducto((Producto)productos.get(fila-1));
				}
				else if(e.getKeyCode()==40 )
				{
					if(fila+1>=contFila) cargarProducto((Producto)productos.get(contFila-1));
					else cargarProducto((Producto)productos.get(fila+1));
				}

			}
		};
	}  

	public void cargarProducto(Producto producto) {
		// TODO Auto-generated method stub	
		vista.remove(vista.getPnlProducto());
		vista.dibujarPnlProducto();
		cargarCmbCapacidad();
		cargarCmbPresentacion();
		cargarCmbSabor();
		
		if (vista.getTable().getSelectedRow() >= 0 )
		{
			vista.getTxtId().setText("");
			vista.getTxtNombre().setText("");
			if(producto.getId() != null)
			{
				vista.getTxtId().setText(producto.getId().toString());
			}
			vista.getTxtId().setText(producto.getId().toString());
			vista.getTxtNombre().setText(producto.getNombre());
			vista.getTxtPrecio().setValue(producto.getPrecio());
			setSelectedValue(vista.getCmbPresentacion(),producto.getPresentacion().getId());
			setSelectedValue(vista.getCmbCapacidad(),producto.getCapacidad().getId());
			setSelectedValue(vista.getCmbSabor(),producto.getSabor().getId());
			vista.getCheckIva().setState(producto.getIva());
			
		}
	}
	@Override
	public String asociar() {
		// TODO Auto-generated method stub
		return inicio;
	}

}

