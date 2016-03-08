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

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import santaclara.Servicio.ServicioCapacidad;
import santaclara.Servicio.ServicioProducto;
import santaclara.modelo.Capacidad;
import santaclara.modelo.Producto;
import santaclara.vista.CapacidadesUI;

public class ContCapacidades extends ContGeneral implements IContGeneral{
	
	private List<Capacidad> capacidades = new ServicioCapacidad().getCapacidades();
	private ServicioCapacidad servicioCapacidad = new ServicioCapacidad();
	private CapacidadesUI vista;
	private Capacidad capacidad = new Capacidad();
	String inicio = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date());
	
	public ContCapacidades(ContPrincipal contPrincipal) throws Exception {
		// TODO Auto-generated constructor stub
		setContPrincipal(contPrincipal);
		vista = new CapacidadesUI(this);
		activarBinding(capacidades);
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
				vista.getTable().clearSelection();
				Capacidad capacidad = new Capacidad();
				cargarCapacidad(capacidad);
			}
		};
	}

	public ActionListener guardar() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				if (vista.getTxtId().getText().equals("")) capacidad.setId(null);
					else capacidad.setId(new Integer(vista.getTxtId().getText().toString())); 
				
				if (vista.getTxtVolumen().getText().equals("")) JOptionPane.showMessageDialog(vista,"Campos Vacios: Volumen");
					else
						{
							try {
									capacidad.setVolumen(new Double(vista.getTxtVolumen().getText().toString()));
									JOptionPane.showMessageDialog(vista,servicioCapacidad.guardar(capacidad));
									capacidades = servicioCapacidad.getCapacidades();
									activarBinding(capacidades);
									
								} catch (Exception e1) {
								// TODO Auto-generated catch block
								JOptionPane.showConfirmDialog(null,e1.getMessage());
								e1.printStackTrace();
								}
						}
			}
		};
	}
	
	public void setVista(CapacidadesUI vista) {
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
						capacidad = capacidades.get(vista.getTable().getSelectedRow());
						
						ServicioProducto servicioProducto = new ServicioProducto();
						
						List<Producto> productos = servicioProducto.getProductos();
						Boolean enc = new Boolean(false);
						for(Producto producto1: productos)
						{
							if(producto1.getCapacidad().getId().equals(capacidad.getId()))
								{
									enc=true;
									break;
								}
						}
						if(enc==false)
						{
							servicioCapacidad.eliminar(capacidad);	
							capacidades = new ServicioCapacidad().getCapacidades();
							activarBinding(capacidades);
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
	public void activarBinding(List<Capacidad> capacidades) {
		// TODO Auto-generated method stub
		
		this.capacidades = capacidades;
		
		vista.remove(vista.getPnCapacidad());
		vista.getPnTabla().setVisible(true);
		vista.setTable(new JTable());
		vista.getScrollPanel().setViewportView(vista.getTable());	
		JTableBinding binCapacidades = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,
    	capacidades,vista.getTable());
		
		BeanProperty idCapacidad  = BeanProperty.create("id");
		BeanProperty volumenCapacidad = BeanProperty.create("volumen");
	    
	    binCapacidades.addColumnBinding(idCapacidad).setColumnClass(Integer.class).setColumnName("id");;
	    binCapacidades.addColumnBinding(volumenCapacidad).setColumnClass(String.class).setColumnName("Volumen");
	    binCapacidades.bind();
	    
	    vista.getTable().addKeyListener(mostrarCapacidad_keypress());
		vista.getTable().addMouseListener(mostrarCapacidad());
 
		vista.remove(vista.getPnCapacidad());
		vista.repaint();
	}

	public MouseAdapter mostrarCapacidad() {
		// TODO Auto-generated method stub
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evento) {
				if (evento.getClickCount()==1)
				{
					capacidad = (Capacidad) capacidades.get(vista.getTable().getSelectedRow());
					cargarCapacidad(capacidad);
				}
			}
		};
	}

	public KeyAdapter mostrarCapacidad_keypress() {
		// TODO Auto-generated method stub
		return new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				Integer fila = new Integer(vista.getTable().getSelectedRow());
				Integer contFila = capacidades.size();
				 
				if(e.getKeyCode()==38 )
				{
					if(fila<=0) cargarCapacidad((Capacidad) capacidades.get(0));
					else cargarCapacidad((Capacidad)capacidades.get(fila-1));
				}
				else if(e.getKeyCode()==40 )
				{
					if(fila+1>=contFila) cargarCapacidad((Capacidad)capacidades.get(contFila-1));
					else cargarCapacidad((Capacidad)capacidades.get(fila+1));
				}

			}
		};
	}  

	public void cargarCapacidad(Capacidad capacidad) {
		// TODO Auto-generated method stub	
		vista.remove(vista.getPnCapacidad());
		vista.dibujarPanelCapacidades();
		
		if (vista.getTable().getSelectedRow() >= 0 )
		{
			vista.getTxtId().setText("");
	
			if(capacidad.getId() != null)
			{
				vista.getTxtId().setText(capacidad.getId().toString());
			}
		vista.getTxtVolumen().setText(capacidad.getVolumen().toString());
			
		}
	}
	@Override
	public String asociar() {
		// TODO Auto-generated method stub
		return inicio;
	}

}
