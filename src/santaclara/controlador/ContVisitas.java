package santaclara.controlador;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.table.TableCellRenderer;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.swingbinding.JComboBoxBinding;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import santaclara.Servicio.ServicioCliente;
import santaclara.Servicio.ServicioConcesionario;
import santaclara.Servicio.ServicioJefeVenta;
import santaclara.Servicio.ServicioRuta;
import santaclara.Servicio.ServicioVisita;
import santaclara.dao.impl.VisitaDAO;
import santaclara.modelo.Cliente;
import santaclara.modelo.Concesionario;
import santaclara.modelo.JefeVenta;
import santaclara.modelo.Ruta;
import santaclara.modelo.Usuario;
import santaclara.modelo.Visita;
import santaclara.vista.VisitasUI;

public class ContVisitas extends ContGeneral implements IContGeneral {

	private VisitasUI vista;
//	private ServicioVisita servicioVisita = new ServicioVisita();;
	
//	private JefeVentaDAO jefeVentaDAO = new JefeVentaDAO();	
	
	public ContVisitas(ContPrincipal contPrincipal) throws Exception {
		// TODO Auto-generated constructor stub
		setContPrincipal(contPrincipal);
		vista = new VisitasUI(this);
		vista.getScrollPanel().setBounds(12, 85, 852, 575);
		CargarComboUsuario();
		ConsultaJefeVenta();
		dibujar(vista);
		vista.quitarNuevo();
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
			
			@SuppressWarnings({ "unused" })
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// se va hacer las validaciones del controlador 
				try {
					if(vista.getComboTipoUser().getSelectedItem().equals("JefeVenta"))
					{
						SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
						
						Visita visita = new ServicioVisita().getVisita(vista.getDateFecha().getDate(),
								new Integer(vista.getLbljefeVentaid().getText()),
								(new ServicioCliente().getCliente(vista.getLblrif().getText())).getId());
								
						if(visita==null)
						{
							visita = new Visita();
							visita.setId(null);
							visita.setCliente(new ServicioCliente().getCliente(vista.getLblrif().getText()));
							visita.setJefeVenta(new ServicioJefeVenta().getJefeVenta(new Integer(vista.getLbljefeVentaid().getText())));
							
							if(new ServicioVisita().isVisita(new SimpleDateFormat("dd/mm/yyyy").parse(new VisitaDAO().getFechaStr(vista.getDateFecha().getDate())),
											new Integer(vista.getLbljefeVentaid().getText()),
											((Cliente)new ServicioCliente().getCliente(vista.getLblrif().getText())).getId()))
									{
										throw new Exception("La Fecha ya se Encuentra Asociada al Cliente ");
									}
							visita.setFecha(vista.getDateFecha().getDate());
						}
						validar();
						visita.setMotivo(vista.getTxtMotivo().getText());
						visita.setDescripcion(vista.getTxtDescripcion().getText());
						visita.setEstado(vista.getChckbxEstado().isSelected());
						visita.setValorProducto((Integer) vista.getTxtValProducto().getValue());
						visita.setValorVendedor((Integer) vista.getTxtValVendedor().getValue());
			
						new ServicioVisita().guardar(visita);
						ConsultaJefeVenta();
					}
					else 
					{
						//new ContVendedores(vista).Guardar();
					}
					JOptionPane.showMessageDialog(vista,"Operacion Exitosa ");
				} catch (Exception exe) {
					// TODO Auto-generated catch block
					JOptionPane.showConfirmDialog(vista,exe.getMessage());
					exe.printStackTrace();
				}
			}
		};
	}
	
	protected void validar() throws Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				if(vista.getTxtDescripcion().getText()=="")
				{
					throw new Exception("Ingrese la Descripcion");
				}
				if(vista.getTxtMotivo().getText()=="")
				{
					throw new Exception("Ingrese el Motivo");
				}
	}
	
	public ActionListener salir() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				qutarVista();
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
			case "santaclara.modelo.Usuario":
				enc = (((Usuario)comboBox.getSelectedItem()).getId().equals(id)); 
					break;
			default:
				break;
			}
        	if (enc) break;
        }
    }

	public ActionListener Atras() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ActivarAtras();
			}
		};
	}
	
	public ActionListener ActivarComboUsuario(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					if (vista.getComboUsuario().getSelectedItem()!=null &&
							vista.getComboUsuario().getSelectedItem().getClass().getName().equals("santaclara.modelo.JefeVenta"))
							ConsultaJefeVenta();
					else {}//ConsultaConcesionario
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
	}
	
	public ActionListener ActivarComboTipo(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				CargarComboUsuario();
			}
		};
	}
	@SuppressWarnings("rawtypes")
	public void CargarComboUsuario(){
		if (vista.getComboTipoUser().getSelectedItem().equals("Concesionario")){
			List<Usuario> usuarios = new ArrayList<Usuario>();
			List<Concesionario> concesionarios= new ArrayList<Concesionario>();
		
			try {
				concesionarios = new ServicioConcesionario().getConcecionarios();
				for(Concesionario concesionario : concesionarios){
					usuarios.add((Usuario)concesionario);
				}
			} catch (NumberFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			JComboBoxBinding jcomboUsuario = SwingBindings.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ,usuarios,vista.getComboUsuario());

			jcomboUsuario.bind();	
		}
		else{
			List<Usuario> usuarios = new ArrayList<Usuario>();
			List<JefeVenta> jefeVentas= new ArrayList<JefeVenta>();
	
		try {
			jefeVentas = new ServicioJefeVenta().getJefeVentas();
				for(JefeVenta jefeVenta : jefeVentas){
					usuarios.add((Usuario)jefeVenta);
				}
			} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		JComboBoxBinding jcomboUsuario = SwingBindings.createJComboBoxBinding(AutoBinding.UpdateStrategy.READ,usuarios,vista.getComboUsuario());

		
			jcomboUsuario.bind();
		}

	}

	@SuppressWarnings("rawtypes")
	public ListCellRenderer setRendererComboUsuario(){
		return new ListCellRenderer() {
			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				// TODO Auto-generated method stub
				Usuario usuario = (Usuario)value;
			
				return new JLabel(usuario.getId().toString()+"  "+usuario.getNombre());	
			}
		};
	}
	public TableCellRenderer getTableCellRenderer(){
		return new TableCellRenderer() {
			
			@Override
			public Component getTableCellRendererComponent(JTable tabla, Object value,
					boolean arg2, boolean arg3, int row, int column) {
				// TODO Auto-generated method stub
			    
				JPanel celda = new JPanel();
				JLabel label = new JLabel();			
				
				label.setText(value.toString());
				
				if (vista.getTabla().getValueAt(row,0).equals("")) celda.setBackground(Color.lightGray);
				else celda.setBackground(Color.cyan);
				
				for(int i=0;i<vista.getTabla().getColumnCount() ;i++)
				{ 
					String campo = new String(vista.getTabla().getColumnName(i));
					if(campo.equals("Fecha"))
					{
						if(label.getText().equals("")&& column == 0)  
						{
							celda.setLayout(null);
							vista.getBoton().setIcon(new ImageIcon("img/gestion/mas.png"));
							vista.getBoton().setBackground(Color.lightGray);
							vista.getBoton().setFont(new Font("Dialog", Font.BOLD, 10));
							vista.getBoton().setForeground(Color.green);
							vista.getBoton().setBounds(2, 0, 20, 20);
							celda.add(vista.getBoton());
						}
						else
						{
							celda.add(label);
							celda.setLayout(new GridLayout(1, 0, 0, 0));
						}
						
					}
				}
				
				return celda;
			}
		};
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void activarBinding(List<Visita> visitas) {
		// TODO Auto-generated method stub
		
		vista.getScrollPanel().setViewportView(vista.getTabla());
		JTableBinding binVistas = vista.getBinVisitas();
		
		 binVistas= SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,
    		visitas,vista.getTabla());
	  
		BeanProperty fechaVisita  = BeanProperty.create("fechaStr");
		BeanProperty nombreRuta  = BeanProperty.create("cliente.ruta.nombre");
		BeanProperty idCliente = BeanProperty.create("cliente.rif");
		BeanProperty nombreCliente = BeanProperty.create("cliente.razonsocial");
	    BeanProperty motivo = BeanProperty.create("motivo");
	    BeanProperty descripcion = BeanProperty.create("descripcion");
	    BeanProperty valorVendedor = BeanProperty.create("valorVendedorStr");
	    BeanProperty valorProducto = BeanProperty.create("valorProductoStr");
	    BeanProperty estado = BeanProperty.create("estadoStr");
	    
	    binVistas.addColumnBinding(fechaVisita).setColumnClass(String.class).setColumnName("Fecha").setEditable(true);
	    binVistas.addColumnBinding(estado).setColumnClass(String.class).setColumnName("Estado");
	    binVistas.addColumnBinding(nombreRuta).setColumnClass(String.class).setColumnName("Ruta");
	    binVistas.addColumnBinding(idCliente).setColumnClass(String.class).setColumnName("Rif Cliente");
	    binVistas.addColumnBinding(nombreCliente).setColumnClass(String.class).setColumnName("Cliente");
	    binVistas.addColumnBinding(motivo).setColumnClass(String.class).setColumnName("Motivo");
	    binVistas.addColumnBinding(descripcion).setColumnClass(String.class).setColumnName("Descripcion");
	    binVistas.addColumnBinding(valorVendedor).setColumnClass(String.class).setColumnName("Valor Vendedor");
	    binVistas.addColumnBinding(valorProducto).setColumnClass(String.class).setColumnName("Valor Producto");

	    binVistas.bind();
	}

	public MouseAdapter ActivarClick(){
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount()==2)
				{
					JFrame frame = vista.getFrame();
					
					vista.getPanelVisitaJefeVenta().setVisible(true);
					frame.add(vista.getPanelVisitaJefeVenta());
					frame.setBounds(100, 200, 500, 400);
					frame.setLayout(new GridLayout(1,0,0,0));
					frame.setVisible(true);
					for(int i=0;i<vista.getTabla().getColumnCount() ;i++)
					{ 
						String campo = new String(vista.getTabla().getColumnName(i));
						Integer fila = new Integer(vista.getTabla().getSelectedRow());
						
						switch (campo) {
						case "Fecha":{
							try {
								String value = new String(vista.getTabla().getValueAt(fila, i).toString());
								if (value.equals("")) vista.getDateFecha().setDate(new Date());
								else vista.getDateFecha().setDate(new SimpleDateFormat("dd/mm/yyyy").parse(value));
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
							break;
						case "Estado":{
							if(vista.getTabla().getValueAt(fila, i).equals("Hecha")){
								vista.getChckbxEstado().setSelected(true);;	
							}
							else
								vista.getChckbxEstado().setSelected(false);
						}
						break;
						case "Ruta": ;
						break;
						case "Rif Cliente": try {
								CargarInfoCliente(vista.getTabla().getValueAt(fila, i).toString());
								try {
									CargarInfoJefeVenta();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							} catch (FileNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} 
						break;
						case "Motivo": vista.getTxtMotivo().setText((String)vista.getTabla().getValueAt(fila, i));
						break;
						case "Descripcion": vista.getTxtDescripcion().setText((String)vista.getTabla().getValueAt(fila, i));
						break;
						case "Valor Vendedor":{
							String value = new String(vista.getTabla().getValueAt(fila, i).toString());
							if (value.equals(""))vista.getTxtValVendedor().setValue(0);
							else vista.getTxtValVendedor().setValue(new Integer(value));
						}
						break;
						case "Valor Producto":{
							String value = new String(vista.getTabla().getValueAt(fila, i).toString());
							if (value.equals(""))vista.getTxtValProducto().setValue(0);
							else vista.getTxtValProducto().setValue(new Integer(value));
		
						}
						break;

						default:
							break;
						}
						
					}
				}
			}
		};
	} 
	
	void CargarInfoCliente(String rif) throws FileNotFoundException{
		Cliente cliente = new ServicioCliente().getCliente(rif);
		vista.getLblrif().setText(cliente.getRif());
		vista.getLblRazonSocial().setText(cliente.getRazonsocial());
		vista.getLblTelefono().setText(cliente.getTelefono());
		vista.getLblDireccion().setText(cliente.getDireccion());
		vista.getLblRuta().setText("Ruta: "+cliente.getRuta().getNombre());
		
	}
	
	void CargarInfoJefeVenta() throws IOException{
		Usuario usuario = (Usuario)vista.getComboUsuario().getSelectedItem();
		
		JefeVenta jefeVenta = new ServicioJefeVenta().getJefeVenta(
				usuario.getId());
		vista.getLbljefeVentaid().setText(jefeVenta.getId().toString());
		vista.getLblCedula().setText(jefeVenta.getCedula());
		vista.getLblJefeUsername().setText(jefeVenta.getUsername());
		vista.getLblNombre().setText(jefeVenta.getNombre());
		vista.getLblZona().setText("Zona: "+jefeVenta.getZona().getDescripcion());
		
	}
	
	public ActionListener QuitarFrame(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				vista.getFrame().removeAll();
			}
		};
	}
	public void ConsultaJefeVenta() throws NumberFormatException, IOException{
		
		JefeVenta jefeVentaCombo = new JefeVenta();
		jefeVentaCombo = (JefeVenta)vista.getComboUsuario().getSelectedItem();
		if(jefeVentaCombo!=null)
		{
		List<Visita> visitas = new ServicioVisita().getVisitas();
		List<Visita> visitasAux = new ArrayList<Visita>();
		List<Ruta> rutas = new ServicioRuta().getRutas();
		List<Cliente> clientes = new ServicioCliente().getClientes();
		
		for(Ruta ruta: rutas)
		{
			if(ruta.getZona().getId().equals(jefeVentaCombo.getZona().getId()))
			{
				for(Cliente cliente: clientes)
				{
					if(cliente.getRuta().getId().equals(ruta.getId()))
					{
						Visita visita = new Visita();
						
						for(Visita visita1: visitas)
						{
							if(visita1.getCliente().getId().equals(cliente.getId())&&
									visita1.getJefeVenta().getId().equals(jefeVentaCombo.getId()))
							{
								visitasAux.add(visita1);
							}
						}
						visita.setCliente(cliente);
						visita.setJefeVenta(jefeVentaCombo);
						visita.setDescripcion("");
						visita.setEstado(null);
						visita.setFecha("");
						visita.setMotivo("");
						visita.setValorProducto(null);
						visita.setValorVendedor(null);
						
						visitasAux.add(visita);
					}
				}
			}
		}
				
		activarBinding(visitasAux);
		}
	}

	public ActionListener ActivarGuardar() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					guardar();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		};
	}

	public ActionListener ActivarEliminar() throws IOException {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e){
				// TODO Auto-generated method stub
					try {
						Visita visita = new ServicioVisita().getVisita(vista.getDateFecha().getDate(),
								new Integer(vista.getLbljefeVentaid().getText()),
								(new ServicioCliente().getCliente(vista.getLblrif().getText())).getId());
						
						Integer opt = new Integer(JOptionPane.showConfirmDialog(new JPanel(),"Esta seguro de eliminar la Visita\n"
								+ "Id: "+visita.getId()+"\n"
								+ "JefeVenta:"+visita.getJefeVenta().getUsername()+"\n"
								+ "Fecha: "+visita.getFechaStr()+"\n"
										+ "Cliente: "+visita.getCliente().getRazonsocial()));
						
						if (opt.equals(0))
						{ 
							new ServicioVisita().Eliminar(visita);
							JOptionPane.showMessageDialog(vista,"Operacion Exitosa ");
							ConsultaJefeVenta();
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		};
	}
	
}
