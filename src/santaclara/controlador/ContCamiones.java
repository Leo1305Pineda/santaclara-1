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

import santaclara.Servicio.ServicioCamion;
import santaclara.Servicio.ServicioConcesionario;
import santaclara.modelo.Concesionario;
import santaclara.modelo.Camion;
import santaclara.vista.CamionesUI;

public class ContCamiones extends ContGeneral implements IContGeneral{
	
	private ServicioCamion servicioCamion;
	private CamionesUI vista;
	private Camion camion = new Camion();
	private List<Camion> camiones = new ServicioCamion().getCamiones();
	
	public ContCamiones(ContPrincipal contPrincipal ) throws NumberFormatException, IOException {
		// TODO Auto-generated constructor stub
		setContPrincipal(contPrincipal);
		servicioCamion = new ServicioCamion();
		vista = new CamionesUI(this);
		dibujar(vista,this);
		activarBinding(camiones);
	}

  
	@Override
	public JPanel getVista() {
		// TODO Auto-generated method stub
		return vista;
	}
	
	public ActionListener nuevo(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				vista.getPnTabla().setVisible(false);
			}
		};
	}

	public String ValidarTxt(){
		if (vista.getTxtPlaca().getText().equals("")) return "Campos Vacios:Placa";
		if (vista.getTxtMarca().getText().equals("")) return "Campos Vacios:Marca";
		if (vista.getTxtModelo().getText().equals("")) return "Campos Vacios:Modelo";
		if (vista.getTxtYear().getText().equals("")) return "Campos Vacios:Año";
		if (vista.getTxtColor().getText().equals("")) return "Campos Vacios:Color";
		if (vista.getTxtCapacidad().getValue().equals(0)) return "Campos Vacios:Capacidad";
		
		return "";
	}

	public ActionListener guardar() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				if (vista.getTxtId().getText().equals("")) camion.setId(null);
					else camion.setId(new Integer(vista.getTxtId().getText().toString())); 
				
				if (ValidarTxt()=="")
				{
					try {
						camion.setPlaca(vista.getTxtPlaca().getText().toString());
						camion.setMarca(vista.getTxtMarca().getText().toString());
						camion.setModelo(vista.getTxtModelo().getText().toString());
						camion.setAno(vista.getTxtYear().getText().toString());
						camion.setCapacidad((Double)vista.getTxtCapacidad().getValue());
						camion.setColor(vista.getTxtColor().getText().toString());
						
						JOptionPane.showMessageDialog(vista,servicioCamion.guardar(camion));
						camiones.add(camion);
						activarBinding(servicioCamion.getCamiones());
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showConfirmDialog(null,e1.getMessage());
						e1.printStackTrace();
					}
				}
				else JOptionPane.showMessageDialog(vista,ValidarTxt());
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
					cargarCamion(camiones.get(fila));
				}
				else 
				{
					JOptionPane.showMessageDialog(new JPanel(),"No Encontrado");
					cargarCamion(new Camion());
				}

			}
		};
	}
	
	public void setVista(CamionesUI vista) {
		this.vista = vista;
	}

	public ActionListener atras() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (vista.getTable().getSelectedColumn() > 0 )
				{	
					Camion camion = (Camion) vista.getCamiones().get(vista.getTable().getSelectedRow());
					ActivarAtras(camion);
				}
				else
				{
					ActivarAtras(null);
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
						
						camion = camiones.get(vista.getTable().getSelectedRow());
						
						ServicioConcesionario servicioConcesionario = new ServicioConcesionario();
						
						List<Concesionario> concesionarios = servicioConcesionario.getConcecionarios();
						Boolean enc = new Boolean(false);
						for(Concesionario concesionario: concesionarios)
						{
							if(concesionario.getCamion().getId().equals(camion.getId()))
								{
									enc=true;
									break;
								}
						}
						if(enc==false)
						{
							servicioCamion.eliminar(camion);
										
							activarBinding(servicioCamion.getCamiones());
							JOptionPane.showMessageDialog(vista,"Operacion Exitosa ");
						}
						else JOptionPane.showMessageDialog(vista,"Operacion Fallida\n"+
								" Objeto Existente en otra Clase? \n Elimine la relacion Exixtente en: Vendedor Concesionario");
						
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void activarBinding(List<Camion> camiones) {
		// TODO Auto-generated method stub
		vista.setCamiones(camiones);
		vista.remove(vista.getPnCamion());
		vista.getPnTabla().setVisible(true);
		vista.setTable(new JTable());
		vista.getScrollPanel().setViewportView(vista.getTable());	
		JTableBinding binCamioness = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,camiones,vista.getTable());
		
		BeanProperty idCamiones  = BeanProperty.create("id");
		BeanProperty placaCamion = BeanProperty.create("placa");
		BeanProperty colorCamion = BeanProperty.create("color");
		BeanProperty capacidadCamion = BeanProperty.create("capacidad");
		BeanProperty anoCamion = BeanProperty.create("ano");
		BeanProperty marcaCamion = BeanProperty.create("marca");
		BeanProperty modeloCamion = BeanProperty.create("modelo");
	    
	    binCamioness.addColumnBinding(idCamiones).setColumnClass(Integer.class).setColumnName("id");
	    binCamioness.addColumnBinding(placaCamion).setColumnClass(String.class).setColumnName("Placa");
	    binCamioness.addColumnBinding(marcaCamion).setColumnClass(String.class).setColumnName("Marca");
	    binCamioness.addColumnBinding(modeloCamion).setColumnClass(String.class).setColumnName("Modelo");
	    binCamioness.addColumnBinding(anoCamion).setColumnClass(String.class).setColumnName("Año");
	    binCamioness.addColumnBinding(colorCamion).setColumnClass(String.class).setColumnName("Color");
	    binCamioness.addColumnBinding(capacidadCamion).setColumnClass(String.class).setColumnName("Capacidad");
	    binCamioness.bind();
	    
	    vista.getTable().addKeyListener(mostrarCamion_keypress());
	 	vista.getTable().addMouseListener(mostrarCamion()); 
	  
	 	vista.remove(vista.getPnCamion());
	 	vista.repaint();
	}
	
	public MouseAdapter mostrarCamion() {
		// TODO Auto-generated method stub
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evento) {
				if (evento.getClickCount()==1)
				{
					camion = camiones.get(vista.getTable().getSelectedRow());
					cargarCamion(camion);
				}
			}
		};
	}

	public KeyAdapter mostrarCamion_keypress() {
		// TODO Auto-generated method stub
		return new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				Integer fila = new Integer(vista.getTable().getSelectedRow());
				Integer contFila = camiones.size();
				 
				if(e.getKeyCode()==38 )
				{
					if(fila<=0) cargarCamion(camiones.get(0));
					else cargarCamion(camiones.get(fila-1));
				}
				else if(e.getKeyCode()==40 )
				{
					if(fila+1>=contFila) cargarCamion(camiones.get(contFila-1));
					else cargarCamion(camiones.get(fila+1));
				}

			}
		};
	}  

	public void cargarCamion(Camion camion) {
		// TODO Auto-generated method stub	
		vista.remove(vista.getPnCamion());
		vista.dibujarPanelCamion();
		
		if (vista.getTable().getSelectedRow() >= 0 )
		{
			vista.getTxtId().setText("");
	
			if(camion.getId() != null)
			{
				vista.getTxtId().setText(camion.getId().toString());
			}
			vista.getTxtId().setText(camion.getId().toString());
			vista.getTxtPlaca().setText(camion.getPlaca());
			vista.getTxtMarca().setText(camion.getMarca());
			vista.getTxtModelo().setText(camion.getModelo());
			vista.getTxtYear().setText(camion.getAno());
			vista.getTxtColor().setText(camion.getColor());
			vista.getTxtCapacidad().setValue(camion.getCapacidad());
			
		}
	}

}
