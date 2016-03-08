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

import santaclara.Servicio.ServicioRuta;
import santaclara.Servicio.ServicioZona;
import santaclara.modelo.Ruta;
import santaclara.modelo.Zona;
import santaclara.vista.ZonasUI;


public class ContZonas extends ContGeneral implements IContGeneral{
	
	private ServicioZona servicioZona = new ServicioZona();
	private List<Zona> zonas = servicioZona.getZonas();
	private ZonasUI vista ;
	private Zona zona = new Zona();
	String inicio = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date());
	
	private Long tiempoActivo = new Long(0);
	
	public ContZonas(ContPrincipal contPrincipal) throws Exception {
		// TODO Auto-generated constructor stub
		setContPrincipal(contPrincipal);
		servicioZona = new ServicioZona();
		vista = new ZonasUI(this);
		dibujar(vista,this);
		activarBinding(zonas);
		
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
				cargarZona(new Zona());
				vista.getTable().clearSelection();
			}
		};
	}

	public ActionListener guardar() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub 
				try {
					
					if (vista.getTxtNombre().getText().equals(""))throw new Exception("Campos Vacios: Nombre Material");	
					if((new ServicioZona().getZona(vista.getTxtNombre().getText())!=null))throw new Exception("La zona ya existe") ;
					if(zona != null && zona.getId() !=null )
					{
						zona.setDescripcion(vista.getTxtNombre().getText());
						servicioZona.guardar(zona);
					}
					else zona = new Zona(null, vista.getTxtNombre().getText());
					
					servicioZona.guardar(zona);
					
					activarBinding(servicioZona.getZonas());
					cargarZona(new Zona());
					JOptionPane.showMessageDialog(vista,"Operacion Exitosa ");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						JOptionPane.showMessageDialog(vista,e.getMessage());
					}	
			}
		};
	}

	public void setVista(ZonasUI vista) {
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
						zona = zonas.get(vista.getTable().getSelectedRow());
						ServicioRuta ServicioRuta = new ServicioRuta();
						
						List<Ruta> rutas = ServicioRuta.getRutas();
						Boolean enc = new Boolean(false);
						for(Ruta ruta: rutas)
						{
							if(ruta.getZona().getId().equals(zona.getId()))
								{
									enc=true;
									break;
								}
						}
						if(enc==false)
						{
							servicioZona.eliminar(zona);
							cargarZona(new Zona());
							activarBinding(servicioZona.getZonas());
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
	public void activarBinding(List<Zona> Zonas) {
		// TODO Auto-generated method stub
		this.zonas = Zonas;
	
		vista.getPnTabla().setVisible(true);
		vista.setTable(new JTable());
		vista.getScrollPanel().setViewportView(vista.getTable());	
		JTableBinding binZonas = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,Zonas,vista.getTable());
		
		BeanProperty idPresentacion  = BeanProperty.create("id");
		BeanProperty materialPresentacion = BeanProperty.create("descripcion");

		binZonas.addColumnBinding(idPresentacion).setColumnClass(Integer.class).setColumnName("id");;
	    binZonas.addColumnBinding(materialPresentacion).setColumnClass(String.class).setColumnName("Nombre");
	    binZonas.bind();

	    vista.getTable().addKeyListener(mostrarZona_keypress());
		vista.getTable().addMouseListener(mostrarZona());

	}
	
	public MouseAdapter mostrarZona() {
		// TODO Auto-generated method stub
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evento) {
				if (evento.getClickCount()==1)
				{
					cargarZona(zonas.get(vista.getTable().getSelectedRow()));
				}
			}
			public void mouseMoved(MouseEvent evento){
				
				tiempoActivo += new Date().getTime()-tiempoActivo; 
			}
		};
	}
	
	public KeyAdapter mostrarZona_keypress() {
		// TODO Auto-generated method stub
		return new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				
				Integer fila = new Integer(vista.getTable().getSelectedRow());
				Integer contFila = zonas.size();
				 
				if(e.getKeyCode()==38 )
				{
					if(fila<=0) cargarZona(zonas.get(0));
					else cargarZona(zonas.get(fila-1));
				}
				else if(e.getKeyCode()==40 )
				{
					if(fila+1>=contFila) cargarZona(zonas.get(contFila-1));
					else cargarZona(zonas.get(fila+1));
				}

			}
		};
	}  

	public void cargarZona(Zona zona) {
		// TODO Auto-generated method stub	
		this.zona = zona;
		vista.getTxtNombre().setText("");
		
		if (vista.getTable().getSelectedRow() >= 0 && zona!=null)
		{
			vista.getTxtNombre().setText(zona.getDescripcion());	
		}
		vista.getPnZona().setVisible(true);
	}

	@Override
	public Object asociar() {
		// TODO Auto-generated method stub
		return (Object) zonas; 
	}
}
