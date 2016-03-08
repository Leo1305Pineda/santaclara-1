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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import santaclara.Servicio.ServicioAlmacen;
import santaclara.modelo.Almacen;
import santaclara.vista.AlmacenesUI;
 
public class ContAlmacenes extends ContGeneral implements IContGeneral{
	
	private ServicioAlmacen servicioAlmacen;
	private AlmacenesUI vista;
	private Almacen almacen = new Almacen();
	private List<Almacen> almacenes =new ArrayList<Almacen>();
	
	private ContMediador mediador = new ContMediador();
	
	String inicio = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date());
	
	public ContAlmacenes(ContPrincipal contPrincipal) throws Exception {
		// TODO Auto-generated constructor stub
		setContPrincipal(contPrincipal);
		servicioAlmacen = new ServicioAlmacen();
		List<Almacen> almacenesAux = servicioAlmacen.getAlmacenes();
		vista = new AlmacenesUI(this,almacenesAux);
		dibujar(vista,this);
		activarBinding(almacenesAux);
		
	}

	@Override
	public JPanel getVista() {
		// TODO Auto-generated method stub
		return vista; 
	}
	
	public AlmacenesUI getVistaAlmacen(){
		return vista; 
	}
	
	public ActionListener nuevo(){
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				vista.getTable().clearSelection();
				almacen = new Almacen();
				cargarAlmacen(almacen);
				
			}
		};
	}

	public ActionListener guardar() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				 
				if (vista.getTxtUbicacion().getText().equals(""))
					JOptionPane.showMessageDialog(vista,"Campos Vacios: Ubicacion");
				else
				{
					try {								
						almacen.setUbicacion(vista.getTxtUbicacion().getText().toString());								
						JOptionPane.showMessageDialog(vista,servicioAlmacen.guardar(almacen));
						almacenes = servicioAlmacen.getAlmacenes();
						activarBinding(almacenes);
									
					} catch (Exception e1) {
					// TODO Auto-generated catch block
						JOptionPane.showConfirmDialog(null,e1.getMessage());
						e1.printStackTrace();
					}
				}
			}
		};
	}
 

	public ServicioAlmacen getServicioAlmacenes() {
		return servicioAlmacen;
	}

	public void setServicioAlmacen(
			ServicioAlmacen servicioAlmacen) {
		this.servicioAlmacen = servicioAlmacen;
	}

	
	public void setVista(AlmacenesUI vista) {
		this.vista = vista;
	}

	public void retornarAlmacen(Almacen almacen)
	{
		mediador.regresarAlmacen(ContAlmacenes.this,almacen);
	}
	
	public ActionListener atras() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					if (vista.getTable().getSelectedRow()>=0)
					{
						almacen = almacenes.get(vista.getTable().getSelectedRow());
						retornarAlmacen(almacen);
					//	ActivarAtras(almacen);
					}
					else  retornarAlmacen(null);//ActivarAtras(null);
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
					if (vista.getTable().getSelectedRow()>=0)
					{
						almacen = almacenes.get(vista.getTable().getSelectedRow());
						servicioAlmacen.eliminar(almacen);
						activarBinding(servicioAlmacen.getAlmacenes());
						JOptionPane.showMessageDialog(vista,"Operacion Exitosa ");
				
					}
					else
					{
						JOptionPane.showMessageDialog(vista,"Seleccione el Almacen");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
	}
	
	public MouseAdapter mostrarAlmacen() {
		// TODO Auto-generated method stub
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evento) {
				if (evento.getClickCount()==1)
				{
					almacen = almacenes.get(vista.getTable().getSelectedRow());
					cargarAlmacen(almacen);
				}
			}
		};
	}
/*
	public KeyAdapter mostrarAlmacen_keypress() {
		// TODO Auto-generated method stub
		return new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				Integer fila = new Integer(vista.getTable().getSelectedRow());
				Integer contFila = almacenes.size();
				 
				if(e.getKeyCode()==38 )
				{
					if(fila<=0) cargarAlmacen(almacenes.get(0));
					else cargarAlmacen(almacenes.get(fila-1));
				}
				else if(e.getKeyCode()==40 )
				{
					if(fila+1>=contFila) cargarAlmacen(almacenes.get(contFila-1));
					else cargarAlmacen(almacenes.get(fila+1));
				}

			}
		};
	}  
	
	*/

	public void cargarAlmacen(Almacen almacen) {
		// TODO Auto-generated method stub	
		vista.remove(vista.getPnAlmacen());
		vista.dibujarPanelAlmacen();
		
		if (vista.getTable().getSelectedRow() >= 0 )
		{
			vista.getTxtUbicacion().setText(almacen.getUbicacion());
			
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void activarBinding(List<Almacen> almacenes) {
		// TODO Auto-generated method stub
		this.almacenes = almacenes;
		vista.remove(vista.getPnAlmacen());
		vista.getPnTabla().setVisible(true);
		vista.setTable(new JTable());
		vista.getScrollPanel().setViewportView(vista.getTable());	
		JTableBinding binAlmacenes = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,almacenes,vista.getTable());
		BeanProperty idAlmacen  = BeanProperty.create("id");
		BeanProperty ubicacionAlmacen = BeanProperty.create("ubicacion");
		binAlmacenes.addColumnBinding(idAlmacen).setColumnClass(Integer.class).setColumnName("id");;
	    binAlmacenes.addColumnBinding(ubicacionAlmacen).setColumnClass(String.class).setColumnName("Ubicacion");
	    binAlmacenes.bind();
	    
	    //svista.getTable().addKeyListener(mostrarAlmacen_keypress());
	 	vista.getTable().addMouseListener(mostrarAlmacen());
	  
	 	vista.remove(vista.getPnAlmacen());
		vista.repaint();

	}

	@Override
	public Object asociar() {
		// TODO Auto-generated method stub
		return (Object) almacenes;
	}
	


}
