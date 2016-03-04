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
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import santaclara.Servicio.ServicioPresentacion;
import santaclara.Servicio.ServicioProducto;
import santaclara.modelo.Presentacion;
import santaclara.modelo.Producto;
import santaclara.vista.PresentacionesUI;

public class ContPresentaciones extends ContGeneral implements IContGeneral{
	
	private ServicioPresentacion servicioPresentacion;
	private PresentacionesUI vista;
	private List<Presentacion> presentaciones = new ServicioPresentacion().getPresentaciones();
	private Presentacion presentacion = new Presentacion();
	
	public ContPresentaciones(ContPrincipal contPrincipal) throws Exception {
		// TODO Auto-generated constructor stub
		setContPrincipal(contPrincipal);
		servicioPresentacion = new ServicioPresentacion();
		vista = new PresentacionesUI(this);
		dibujar(vista,this);
		activarBinding(presentaciones);
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
				vista.getTable().clearSelection();
				presentacion = new Presentacion();
				cargarPresentacion(presentacion);
			}
		};
	}

	public ActionListener guardar() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub 
				
				if (vista.getTxtNombre().getText().equals("")) JOptionPane.showMessageDialog(vista,"Campos Vacios: Nombre Material");
					else
						{
							try {
									presentacion.setMaterial(vista.getTxtNombre().getText().toString());
									
									JOptionPane.showMessageDialog(vista,servicioPresentacion.guardar(presentacion));
									presentaciones = servicioPresentacion.getPresentaciones();
									
									activarBinding(servicioPresentacion.getPresentaciones());
									
								} catch (Exception e1) {
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
				vista.setTable(buscar(vista.getTable(),vista.getTxtABuscar().getText().toString().trim()));
				Integer fila = new Integer(vista.getTable().getSelectedRow());
				if(fila>=0)
				{
					cargarPresentacion(presentaciones.get(fila));
				}
				else 
				{
					JOptionPane.showMessageDialog(new JPanel(),"No Encontrado");
					presentacion = new Presentacion();
					cargarPresentacion(presentacion);
				}
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
						presentacion = presentaciones.get(vista.getTable().getSelectedRow());
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
							presentacion = new Presentacion();
							presentaciones = servicioPresentacion.getPresentaciones();
							activarBinding(presentaciones);
							JOptionPane.showMessageDialog(vista,"Operacion Exitosa ");
						}
						else JOptionPane.showMessageDialog(vista,"Operacion Fallida\n"+
								" Objeto Existente en otra Clase? \n Elimine la relacion Exixtente en: Producto");
						
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void activarBinding(List<Presentacion> presentaciones) {
		// TODO Auto-generated method stub
		vista.remove(vista.getPnPresentacion());
		vista.getPnTabla().setVisible(true);
		vista.setTable(new JTable());
		vista.getScrollPanel().setViewportView(vista.getTable());	
		JTableBinding binPresentaciones = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,presentaciones,vista.getTable());
		
		BeanProperty idPresentacion  = BeanProperty.create("id");
		BeanProperty materialPresentacion = BeanProperty.create("material");

		binPresentaciones.addColumnBinding(idPresentacion).setColumnClass(Integer.class).setColumnName("id");;
	    binPresentaciones.addColumnBinding(materialPresentacion).setColumnClass(String.class).setColumnName("Material");
	    binPresentaciones.bind();

	    vista.getTable().addKeyListener(mostrarCapacidad_keypress());
		vista.getTable().addMouseListener(mostrarCapacidad());
 
		vista.remove(vista.getPnPresentacion());
		vista.repaint();
	}
	
	public MouseAdapter mostrarCapacidad() {
		// TODO Auto-generated method stub
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evento) {
				if (evento.getClickCount()==1)
				{
					presentacion = presentaciones.get(vista.getTable().getSelectedRow());
					cargarPresentacion(presentacion);
				}
			}
		};
	}

	public KeyAdapter mostrarCapacidad_keypress() {
		// TODO Auto-generated method stub
		return new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				Integer fila = new Integer(vista.getTable().getSelectedRow());
				Integer contFila = presentaciones.size();
				 
				if(e.getKeyCode()==38 )
				{
					if(fila<=0) cargarPresentacion(presentaciones.get(0));
					else cargarPresentacion(presentaciones.get(fila-1));
				}
				else if(e.getKeyCode()==40 )
				{
					if(fila+1>=contFila) cargarPresentacion(presentaciones.get(contFila-1));
					else cargarPresentacion(presentaciones.get(fila+1));
				}

			}
		};
	}  

	public void cargarPresentacion(Presentacion presentacion) {
		// TODO Auto-generated method stub	
		vista.remove(vista.getPnPresentacion());
		vista.dibujarPanelPresentaciones();;
		
		if (vista.getTable().getSelectedRow() >= 0 )
		{
			vista.getTxtNombre().setText(presentacion.getMaterial());	
		}
	}
}
