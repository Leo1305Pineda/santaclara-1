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

import santaclara.Servicio.ServicioProducto;
import santaclara.Servicio.ServicioSabor;
import santaclara.modelo.Producto;
import santaclara.modelo.Sabor;
import santaclara.vista.SaboresUI;

public class ContSabores extends ContGeneral implements IContGeneral{
	
	private ServicioSabor servicioSabor;
	private SaboresUI vista;
	private Sabor sabor = new Sabor();
	private List<Sabor> sabores = new ServicioSabor().getSabores(); 
	
	public ContSabores(ContPrincipal contPrincipal) throws Exception {
		// TODO Auto-generated constructor stub
		setContPrincipal(contPrincipal);
		servicioSabor = new ServicioSabor();
		vista = new SaboresUI(this);
		dibujar(vista,this);
		activarBinding(sabores);
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
				sabor = new Sabor();
				cargarSabor(sabor);
			}
		};
	}

	public ActionListener guardar() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
			
				if (vista.getTxtSabor().getText().equals("")) JOptionPane.showMessageDialog(vista,"Campos Vacios: Nombre Material");
					else
						{
							try {
									sabor.setSabor(vista.getTxtSabor().getText().toString());
									
									JOptionPane.showMessageDialog(vista,servicioSabor.guardar(sabor));
									sabores = servicioSabor.getSabores();
									activarBinding(sabores);
									
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
					cargarSabor(sabores.get(fila));
				}
				else 
				{
					JOptionPane.showMessageDialog(new JPanel(),"No Encontrado");
					cargarSabor(new Sabor());
				}		}
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
						sabor = sabores.get(vista.getTable().getSelectedRow());	
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
							sabor = new Sabor();
							sabores = servicioSabor.getSabores();
							activarBinding(sabores);
							JOptionPane.showMessageDialog(vista,"Operacion Exitosa ");
						}
						else JOptionPane.showMessageDialog(vista,"Operacion Fallida\n"+
								" Objeto Existente en otra Clase? \n Elimine la relacion Exixtente en: EmpaqueProducto");
						
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void activarBinding(List<Sabor> sabores) {
		// TODO Auto-generated method stub
		vista.remove(vista.getPnSabor());
		vista.getPnTabla().setVisible(true);
		vista.setTable(new JTable());
		vista.getScrollPanel().setViewportView(vista.getTable());	
		JTableBinding binSabores = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,sabores,vista.getTable());
		
		BeanProperty idSabor  = BeanProperty.create("id");
		BeanProperty Sabor = BeanProperty.create("sabor");
	    
	    binSabores.addColumnBinding(idSabor).setColumnClass(Integer.class).setColumnName("id");;
	    binSabores.addColumnBinding(Sabor).setColumnClass(String.class).setColumnName("Sabor");
	    binSabores.bind();

	    vista.getTable().addKeyListener(mostrarSabor_keypress());
		vista.getTable().addMouseListener(mostrarSabor());
 
		vista.remove(vista.getPnSabor());
		vista.repaint();
	}
	public MouseAdapter mostrarSabor() {
		// TODO Auto-generated method stub
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evento) {
				if (evento.getClickCount()==1)
				{
					sabor = sabores.get(vista.getTable().getSelectedRow());
					cargarSabor(sabor);
				}
			}
		};
	}

	public KeyAdapter mostrarSabor_keypress() {
		// TODO Auto-generated method stub
		return new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				Integer fila = new Integer(vista.getTable().getSelectedRow());
				Integer contFila = sabores.size();
				 
				if(e.getKeyCode()==38 )
				{
					if(fila<=0) cargarSabor(sabores.get(0));
					else cargarSabor(sabores.get(fila-1));
				}
				else if(e.getKeyCode()==40 )
				{
					if(fila+1>=contFila) cargarSabor(sabores.get(contFila-1));
					else cargarSabor(sabores.get(fila+1));
				}

			}
		};
	}  

	public void cargarSabor(Sabor sabor) {
		// TODO Auto-generated method stub	
		vista.remove(vista.getPnSabor());
		vista.dibujarPanelSabor();
		
		if (vista.getTable().getSelectedRow() >= 0 )
		{
			vista.getTxtSabor().setText(sabor.getSabor());			
		}
	}
}
