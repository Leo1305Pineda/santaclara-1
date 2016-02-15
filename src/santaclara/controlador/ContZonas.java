package santaclara.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
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
	
	private ServicioZona servicioZona;
	private ZonasUI vista;
	private List<Zona> Zonas = new ServicioZona().getZonas();
	private Zona Zona = new Zona();
	
	public ContZonas(ContPrincipal contPrincipal) throws Exception {
		// TODO Auto-generated constructor stub
		setContPrincipal(contPrincipal);
		servicioZona = new ServicioZona();
		vista = new ZonasUI(this);
		dibujar(vista,this);
		activarBinding(Zonas);
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
				Zona = new Zona();
				cargarZona(Zona);
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
									Zona.setDescripcion(vista.getTxtNombre().getText().toString());
									
									JOptionPane.showMessageDialog(vista,servicioZona.guardar(Zona));
									Zonas = servicioZona.getZonas();
									
									activarBinding(servicioZona.getZonas());
									
								} catch (IOException e1) {
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
					cargarZona(Zonas.get(fila));
				}
				else 
				{
					JOptionPane.showMessageDialog(new JPanel(),"No Encontrado");
					Zona = new Zona();
					cargarZona(Zona);
				}
			}
		};
	}

	public ServicioZona getServicioZonas() {
		return servicioZona;
	}

	public void setServicioZonas(
			ServicioZona servicioPresentaciones) {
		this.servicioZona = servicioPresentaciones;
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
						Zona = Zonas.get(vista.getTable().getSelectedRow());
						ServicioRuta ServicioRuta = new ServicioRuta();
						
						List<Ruta> rutas = ServicioRuta.getRutas();
						Boolean enc = new Boolean(false);
						for(Ruta ruta: rutas)
						{
							if(ruta.getZona().getId().equals(Zona.getId()))
								{
									enc=true;
									break;
								}
						}
						if(enc==false)
						{
							servicioZona.eliminar(Zona);
							Zona = new Zona();
							Zonas = servicioZona.getZonas();
							activarBinding(Zonas);
							JOptionPane.showMessageDialog(vista,"Operacion Exitosa ");
						}
						else JOptionPane.showMessageDialog(vista,"Operacion Fallida\n"+
								" Objeto Existente en otra Clase? \n Elimine la relacion Exixtente en: Producto");
						
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void activarBinding(List<Zona> Zonas) {
		// TODO Auto-generated method stub
		vista.remove(vista.getPnZona());
		vista.getPnTabla().setVisible(true);
		vista.setTable(new JTable());
		vista.getScrollPanel().setViewportView(vista.getTable());	
		JTableBinding binZonas = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,Zonas,vista.getTable());
		
		BeanProperty idPresentacion  = BeanProperty.create("id");
		BeanProperty materialPresentacion = BeanProperty.create("descripcion");

		binZonas.addColumnBinding(idPresentacion).setColumnClass(Integer.class).setColumnName("id");;
	    binZonas.addColumnBinding(materialPresentacion).setColumnClass(String.class).setColumnName("Material");
	    binZonas.bind();

	    vista.getTable().addKeyListener(mostrarZona_keypress());
		vista.getTable().addMouseListener(mostrarZona());
 
		vista.remove(vista.getPnZona());
		vista.repaint();
	}
	
	public MouseAdapter mostrarZona() {
		// TODO Auto-generated method stub
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evento) {
				if (evento.getClickCount()==1)
				{
					Zona = Zonas.get(vista.getTable().getSelectedRow());
					cargarZona(Zona);
				}
			}
		};
	}

	public KeyAdapter mostrarZona_keypress() {
		// TODO Auto-generated method stub
		return new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				Integer fila = new Integer(vista.getTable().getSelectedRow());
				Integer contFila = Zonas.size();
				 
				if(e.getKeyCode()==38 )
				{
					if(fila<=0) cargarZona(Zonas.get(0));
					else cargarZona(Zonas.get(fila-1));
				}
				else if(e.getKeyCode()==40 )
				{
					if(fila+1>=contFila) cargarZona(Zonas.get(contFila-1));
					else cargarZona(Zonas.get(fila+1));
				}

			}
		};
	}  

	public void cargarZona(Zona presentacion) {
		// TODO Auto-generated method stub	
		vista.remove(vista.getPnZona());
		vista.dibujarPanelZonas();;
		
		if (vista.getTable().getSelectedRow() >= 0 )
		{
			vista.getTxtNombre().setText(presentacion.getDescripcion());	
		}
	}
}
