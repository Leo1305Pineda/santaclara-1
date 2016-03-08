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

import santaclara.Servicio.ServicioEmpaqueProducto;
import santaclara.Servicio.ServicioProducto;
import santaclara.dao.impl.EmpaqueProductoDAO;
import santaclara.modelo.EmpaqueProducto;
import santaclara.modelo.Producto;
import santaclara.vista.EmpaqueProductosUI;

public class ContEmpaqueProductos extends ContGeneral implements IContGeneral {

	private EmpaqueProductosUI vista;
	private ServicioEmpaqueProducto servicioEmpaqueProducto = new ServicioEmpaqueProducto();;
	private ContPrincipal contPrincipal;
	private List<EmpaqueProducto> empaqueProductos = new ServicioEmpaqueProducto().getEmpaqueProductos();
	private EmpaqueProducto empaqueProducto;
	private List<Producto> 		productos = new ServicioProducto().getProductos();
	String inicio = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date());
	
	public ContEmpaqueProductos(ContPrincipal contPrincipal) throws Exception {
		// TODO Auto-generated constructor stub
		this.contPrincipal = contPrincipal;
		setContPrincipal(contPrincipal);
		vista = new EmpaqueProductosUI(this);		
		dibujar(vista,this);
		activarBinding(empaqueProductos);
		cargarEmpaqueProducto(new EmpaqueProducto());
		cargarCmbProducto();
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
				try {
					
					empaqueProducto = new EmpaqueProducto();
					empaqueProducto.setCantidad((Integer) vista.getTxtCantidad().getValue());
					empaqueProducto.setProducto((Producto)vista.getCmbProducto().getSelectedItem());
					
					if(new EmpaqueProductoDAO().getEmpaqueProducto(empaqueProducto)) throw new Exception("EL Producto y la cantidad ya existen ");
					
					servicioEmpaqueProducto.guardar(empaqueProducto);
					activarBinding(servicioEmpaqueProducto.getEmpaqueProductos());
					cargarEmpaqueProducto(new EmpaqueProducto());
									
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showConfirmDialog(vista,e1.getMessage());
				}
			}
		};
	}
		
	public ActionListener eliminar() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				if (vista.getTable().getSelectedRow()<0) throw new Exception("Seleccione el EmpaqueProducto ");
			
					servicioEmpaqueProducto.eliminar(empaqueProductos.get(vista.getTable().getSelectedRow()));				
					activarBinding(servicioEmpaqueProducto.getEmpaqueProductos());
					JOptionPane.showMessageDialog(vista,"Operacion Exitosa ");
					cargarEmpaqueProducto(new EmpaqueProducto());
			
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(vista,e1.getMessage());
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
					cargarEmpaqueProducto(new EmpaqueProducto());
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
			case "santaclara.modelo.Producto":
				enc = (((Producto)comboBox.getSelectedItem()).getId().equals(id)); 
					break;
			default:
				break;
			}
        	if (enc) break;
        }
    }

	public ActionListener AbrirProducto() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
						new ContProductos(contPrincipal);
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
				try {
					if (vista.getTable().getSelectedRow()>=0)
					{
						EmpaqueProducto empaqueProducto = new EmpaqueProducto();
						empaqueProducto = new ServicioEmpaqueProducto().buscar(new Integer(vista.getTable().getValueAt(vista.getTable().getSelectedRow(),0).toString()));
						ActivarAtras(empaqueProducto);
					}
					else 	ActivarAtras(null);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void activarBinding(List<EmpaqueProducto> empaqueProductos) {
		// TODO Auto-generated method stub
		this.empaqueProductos = empaqueProductos;
		
		vista.getPnTabla().setVisible(true);
		vista.setTable(new JTable());
		vista.getScrollPanel().setViewportView(vista.getTable());
		
		JTableBinding   binEmpaqueProductos = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,
    			empaqueProductos,vista.getTable());
		BeanProperty idEmpaqueProducto  = BeanProperty.create("id");
	    
		BeanProperty nombreProducto = BeanProperty.create("producto.nombre");
	    BeanProperty presentacionProducto = BeanProperty.create("producto.presentacion.material");
	    BeanProperty capacidadProducto = BeanProperty.create("producto.capacidad.volumen");
	    BeanProperty saborProducto = BeanProperty.create("producto.sabor.sabor");
	    
	    BeanProperty cantidadProducto = BeanProperty.create("unidadesStr");

	    binEmpaqueProductos.addColumnBinding(idEmpaqueProducto).setColumnClass(Integer.class).setColumnName("id Empaque");;
	    
	    binEmpaqueProductos.addColumnBinding(nombreProducto).setColumnClass(String.class).setColumnName("Producto");
	    binEmpaqueProductos.addColumnBinding(presentacionProducto).setColumnClass(String.class).setColumnName("Presentacion");
	    binEmpaqueProductos.addColumnBinding(capacidadProducto).setColumnClass(String.class).setColumnName("Capacidad");
	    binEmpaqueProductos.addColumnBinding(saborProducto).setColumnClass(String.class).setColumnName("Sabor");
	    
	    binEmpaqueProductos.addColumnBinding(cantidadProducto).setColumnClass(String.class).setColumnName("Unidades por Empaque");

	    binEmpaqueProductos.bind();
	    
		vista.getTable().addKeyListener(mostrarEmpaqueProducto_keypress());
		vista.getTable().addMouseListener(mostrarEmpaqueProducto());
	}
	
	@SuppressWarnings("rawtypes")
	public void cargarCmbProducto(){
		 JComboBoxBinding jcomboProductos = SwingBindings.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ,productos,vista.getCmbProducto());
		 jcomboProductos.bind();
	}

	public MouseAdapter mostrarEmpaqueProducto() {
		// TODO Auto-generated method stub
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evento) {
				if (evento.getClickCount()==1) {
					cargarEmpaqueProducto(empaqueProductos.get(vista.getTable().getSelectedRow()));
				}
			}
		};
	}

	public KeyAdapter mostrarEmpaqueProducto_keypress() {
		// TODO Auto-generated method stub
		return new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				Integer fila = new Integer(vista.getTable().getSelectedRow());
				Integer contFila = empaqueProductos.size();
				 
				if(e.getKeyCode()==38 )
				{
					if(fila<=0)cargarEmpaqueProducto(empaqueProductos.get(0));
					else cargarEmpaqueProducto(empaqueProductos.get(fila-1));
				}
				else if(e.getKeyCode()==40 )
				{
					if(fila+1>=contFila)cargarEmpaqueProducto(empaqueProductos.get(contFila-1));
					else cargarEmpaqueProducto(empaqueProductos.get(fila+1));
				}

			}
		};
	}  

	public void cargarEmpaqueProducto(EmpaqueProducto empaqueProducto) {
		// TODO Auto-generated method stub
		this.empaqueProducto = empaqueProducto;
		vista.remove(vista.getPanelEmpaqueProducto());
		vista.dibujarPanelEmpaqueProducto();
		cargarCmbProducto();
		if (vista.getTable().getSelectedRow() >= 0)
		{
			cargarCmbProducto();
 			vista.getTxtCantidad().setValue(empaqueProducto.getCantidad());
			setSelectedValue(vista.getCmbProducto(), empaqueProducto.getProducto().getId());
		}
		
	}
	@Override
	public String asociar() {
		// TODO Auto-generated method stub
		return inicio;
	}

}

