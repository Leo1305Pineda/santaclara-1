/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.controlador;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

import santaclara.Servicio.ServicioRuta;
import santaclara.Servicio.ServicioZona;
import santaclara.modelo.Ruta;
import santaclara.modelo.Zona;
import santaclara.vista.RutasUI;

public class ContRutas extends ContGeneral implements IContGeneral {
	
	private RutasUI vista;
	private ServicioRuta servicioRuta = new ServicioRuta();
	private List<Ruta> rutas = servicioRuta.getRutas();
	private List<Zona> zonas = new ServicioZona().getZonas();
	
	private Ruta ruta = new Ruta();
	
	public ContRutas(ContPrincipal contPrincipal) throws Exception {
		// TODO Auto-generated constructor stub
		setContPrincipal(contPrincipal);
		vista = new RutasUI(this);
		dibujar(vista,this);
		activarBinding(rutas);
		cargarCmbZona(zonas);
	}

	@Override
	public JPanel getVista() {
		// TODO Auto-generated method stub
		return null;
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
					cargarRuta((Ruta)rutas.get(fila));
				}
				else 
				{
					JOptionPane.showMessageDialog(new JPanel(),"No Encontrado");
					cargarRuta(new Ruta());
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
						servicioRuta.eliminar(rutas.get(vista.getTable().getSelectedRow()));
						rutas = servicioRuta.getRutas();
						activarBinding(servicioRuta.getRutas());
						cargarRuta(new Ruta());
						JOptionPane.showMessageDialog(vista,"Operacion Exitosa ");
					} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					}
				}
				else
				{
					JOptionPane.showMessageDialog(vista,"Seleccione la Ruta ");
				}
			}
		};
	}
	
	public ActionListener guardar() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
				
					String msg="";
					if (vista.getTxtId().getText().equals("")) ruta.setId(null); 
						else ruta.setId(new Integer(vista.getTxtId().getText().toString()));
					
					if (vista.getTxtNombre().getText().equals("")) msg=" Nombre, ";
					else ruta.setNombre(vista.getTxtNombre().getText());
					
					ruta.setZona((Zona)vista.getCmbZona().getSelectedItem());
					
					if (msg!="") JOptionPane.showMessageDialog(vista,"Campos Vacios: "+msg);
					else
					{
						servicioRuta.guardar(ruta);
						activarBinding( servicioRuta.getRutas());
						cargarRuta(new Ruta());
						JOptionPane.showMessageDialog(vista,"Operacion Exitosa ");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showConfirmDialog(null,e1.getMessage());
					e1.printStackTrace();
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
				cargarRuta(new Ruta());
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


	public ActionListener AbrirZona() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
						new ContZonas(getContPrincipal());
				} catch (Exception e1) {
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
			case "santaclara.modelo.Zona":
				enc = (((Zona)comboBox.getSelectedItem()).getId().equals(id)); 
					break;
			default:
				break;
			}
        	if (enc) break;
        }
    }
	
	public KeyAdapter mostrarRuta_keypress() {
		// TODO Auto-generated method stub
		return new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				Integer fila = new Integer(vista.getTable().getSelectedRow());
				Integer contFila = rutas.size();
				 
				if(e.getKeyCode()==38 )
				{
					if(fila<=0)cargarRuta(rutas.get(0));
					else cargarRuta(rutas.get(fila-1));
				}
				else if(e.getKeyCode()==40 )
				{
					if(fila+1>=contFila)cargarRuta(rutas.get(contFila-1));
					else cargarRuta(rutas.get(fila+1));
				}


			}
		};
	}
	
	public MouseAdapter mostrarRuta() {
		// TODO Auto-generated method stub
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evento) {
				if (evento.getClickCount()==1)
				{
					cargarRuta(rutas.get(vista.getTable().getSelectedRow()));
				}
			}
		};
	}
	
	public void cargarRuta(Ruta ruta) {
		// TODO Auto-generated method stub
		vista.add(vista.getPanelRuta(),BorderLayout.SOUTH);
		vista.getBtnCancelar().setVisible(false);
		this.ruta = ruta;
		vista.getTxtNombre().setText("");
		if (vista.getTable().getSelectedRow() >= 0 && this.ruta != null)
		{	
			setSelectedValue(vista.getCmbZona(), this.ruta.getZona().getId());
			vista.getTxtNombre().setText(this.ruta.getNombre());
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void activarBinding(List<Ruta> rutas) {
		// TODO Auto-generated method stub
		this.rutas = rutas;
		
		vista.getPnTabla().setVisible(true);
		vista.setTable(new JTable());
		vista.getScrollPanel().setViewportView(vista.getTable());
		
		JTableBinding  binRutas = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE, rutas,vista.getTable());
	    BeanProperty nombreRuta = BeanProperty.create("nombre");
	    BeanProperty idRuta  = BeanProperty.create("id");
	    BeanProperty zonaDescripcion  = BeanProperty.create("zona.descripcion");    

	    binRutas.addColumnBinding(idRuta).setColumnClass(Integer.class).setColumnName("id");;
	    binRutas.addColumnBinding(nombreRuta).setColumnClass(String.class).setColumnName("Nombre");
	    binRutas.addColumnBinding(zonaDescripcion).setColumnClass(String.class).setColumnName("Zona");
	    
	    binRutas.bind();
	    vista.getTable().addKeyListener(mostrarRuta_keypress());
	    vista.getTable().addMouseListener(mostrarRuta());

	    vista.remove(vista.getPanelRuta());
	}
	
	@SuppressWarnings("rawtypes")
	public void cargarCmbZona(List<Zona> zonas){
		JComboBoxBinding jcomboZona = SwingBindings.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ,zonas,vista.getCmbZona());
	    jcomboZona.bind();	
	}

	public ServicioRuta getServicioRuta() {
		return servicioRuta;
	}

	public void setServicioRuta(ServicioRuta servicioRuta) {
		this.servicioRuta = servicioRuta;
	}

	public Ruta getRuta() {
		return ruta;
	}

	public void setRuta(Ruta ruta) {
		this.ruta = ruta;
	}
	
	public void setVista(RutasUI vista) {
		this.vista = vista;
	}
}
