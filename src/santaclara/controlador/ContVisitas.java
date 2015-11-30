package santaclara.controlador;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
import santaclara.Servicio.ServicioDomicilioComercio;
import santaclara.Servicio.ServicioJefeVenta;
import santaclara.Servicio.ServicioSalp;
import santaclara.Servicio.ServicioVisita;
import santaclara.modelo.Cliente;
import santaclara.modelo.Concesionario;
import santaclara.modelo.DomicilioComercio;
import santaclara.modelo.JefeVenta;
import santaclara.modelo.Usuario;
import santaclara.modelo.Visita;
import santaclara.vista.VisitasUI;

public class ContVisitas extends ContGeneral implements IContGeneral {

	private VisitasUI vista;	
	
	public ContVisitas(ContPrincipal contPrincipal) throws Exception {
		// TODO Auto-generated constructor stub
		setContPrincipal(contPrincipal);
		vista = new VisitasUI(this);
		vista.getScrollPanel().setBounds(12, 85, 1154, 600);
		CargarComboUsuario();
		activarBinding(new ServicioVisita().ConsultaJefeVenta((JefeVenta)vista.getComboUsuario().getSelectedItem()));
		dibujar(vista,this);
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
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						
						Visita visita = new ServicioVisita().getVisita(vista.getDateFecha().getDate(),
								new Integer(vista.getLbljefeVentaid().getText()),
								(new ServicioCliente().getCliente(vista.getLblrif().getText())).getId());
								
						if(visita==null)
						{
							visita = new Visita();
							visita.setId(null);
							visita.setCliente(new ServicioCliente().getCliente(vista.getLblrif().getText()));
							if(vista.getComboTipoUser().getSelectedItem().equals("JefeVenta"))
							{
								visita.setUsuario(new ServicioJefeVenta().getJefeVenta(new Integer(vista.getLbljefeVentaid().getText())));
							}
							else
								visita.setUsuario(new ServicioConcesionario().getConcesionario(new Integer(vista.getLbljefeVentaid().getText())));
							
							if(new ServicioVisita().isVisita(vista.getDateFecha().getDate(),
	                				((Cliente)new ServicioCliente().getCliente(vista.getLblrif().getText())).getId(),
	                				new Integer(vista.getLbljefeVentaid().getText())))
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
						
						if(vista.getComboTipoUser().getSelectedItem().equals("JefeVenta"))
						{
							activarBinding(new ServicioVisita().ConsultaJefeVenta((JefeVenta)vista.getComboUsuario().getSelectedItem()));
						}
						else
							activarBinding(new ServicioVisita().ConsultaConcesionario((Concesionario)vista.getComboUsuario().getSelectedItem()));
						
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
				ActivarAtras(null);
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
						activarBinding(new ServicioVisita().ConsultaJefeVenta((JefeVenta)vista.getComboUsuario().getSelectedItem()));
					else if (vista.getComboUsuario().getSelectedItem()!=null &&
							vista.getComboUsuario().getSelectedItem().getClass().getName().equals("santaclara.modelo.Concesionario"))
						activarBinding(new ServicioVisita().ConsultaConcesionario((Concesionario)vista.getComboUsuario().getSelectedItem()));
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
			
			@SuppressWarnings("deprecation")
			@Override
			public Component getTableCellRendererComponent(JTable tabla, Object value,
					boolean arg2, boolean arg3, int row, int column) {
				// TODO Auto-generated method stub
				JPanel celda = new JPanel();
				JLabel label = new JLabel();	
				
				if(value ==null) label.setText("");
				else label.setText(value.toString());
				
				for(int c=0;c<vista.getTabla().getColumnCount() ;c++)
				{  
					String campo = new String(vista.getTabla().getColumnName(c));
					switch(campo){ 
						case "Fecha":{
							try {
								if(column==c && label.getText() != "")
								{
									Date fecha = new Date();
									SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
									fecha = sdf.parse(label.getText());
									
									switch (fecha.getDay()) {
									case 0:label.setText("Dom. "+label.getText());				
										break;
									case 1:label.setText("Lun. "+label.getText());				
										break;
									case 2:label.setText("Mar. "+label.getText());				
										break;
									case 3:label.setText(";Mie. "+label.getText());				
										break;
									case 4:label.setText("Jue. "+label.getText());				
										break;
									case 5:label.setText("Vie. "+label.getText());				
										break;
									case 6:label.setText("Sab. "+label.getText());				
										break;

									default:label.setText("");
										break;
									}

								}								
								if (vista.getTabla().getValueAt(row,c).equals(""))celda.setBackground(Color.lightGray);
								else celda.setBackground(Color.cyan);
								
								if(label.getText().equals("")&& column == c)  
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
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}break;
						
						case "Estado":{
							if(label.getText().equals("") && column == c)celda.setBackground(Color.lightGray);
							else if (label.getText().equals("Hecha") && column == c)celda.setBackground(Color.yellow);
							else if (label.getText().equals("Por Hacer") && column == c) celda.setBackground(Color.cyan);
							else ;
						}break;
						case "Cliente":{
							try {
								if(column == c)
								{
									DomicilioComercio domicilioComercio = new ServicioDomicilioComercio().buscar(new Integer(label.getText()));
									if(domicilioComercio != null)
									{
										if(domicilioComercio.getTipo().equals("D"))label.setText("Domicilio");
										else label.setText("Comencio");
									}
									else if ((new ServicioSalp().buscar(new Integer(label.getText()))!=null)) label.setText("Salp");
									else label.setText("");
								}
							} catch (NumberFormatException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}break;
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
		BeanProperty rifCliente = BeanProperty.create("cliente.rif");
		BeanProperty nombreCliente = BeanProperty.create("cliente.razonsocial");
		BeanProperty Cliente = BeanProperty.create("cliente.id");
	    BeanProperty motivo = BeanProperty.create("motivo");
	    BeanProperty descripcion = BeanProperty.create("descripcion");
	    BeanProperty valorVendedor = BeanProperty.create("valorVendedorStr");
	    BeanProperty valorProducto = BeanProperty.create("valorProductoStr");
	    BeanProperty estado = BeanProperty.create("estadoStr");
	    
	    binVistas.addColumnBinding(fechaVisita).setColumnClass(String.class).setColumnName("Fecha").setEditable(true);
	    binVistas.addColumnBinding(estado).setColumnClass(String.class).setColumnName("Estado");
	    binVistas.addColumnBinding(nombreRuta).setColumnClass(String.class).setColumnName("Ruta");
	
	    binVistas.addColumnBinding(Cliente).setColumnClass(String.class).setColumnName("Cliente");
	    binVistas.addColumnBinding(rifCliente).setColumnClass(String.class).setColumnName("Rif");
	    binVistas.addColumnBinding(nombreCliente).setColumnClass(String.class).setColumnName("Razon Social");
	
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
					limpiar();
					mostrarEditarUsuarios();
				}
			}
		};
	} 
	
	void mostrarEditarUsuarios(){ 
		mostrarEditarUsuario();
		for(int columna=vista.getTabla().getColumnCount()-1;columna>=0 ;columna--)
		{
			String campo = new String(vista.getTabla().getColumnName(columna));
			Integer fila = new Integer(vista.getTabla().getSelectedRow());
			switch (campo) {
			case "Estado":{
				if(vista.getTabla().getValueAt(fila, columna).equals("Hecha")){
					vista.getChckbxEstado().setSelected(true);;	
				}
				else
					vista.getChckbxEstado().setSelected(false);
			}
			break;
			case "Ruta": ;
			break;
			case "Rif":
				try {
						CargarInfoCliente(vista.getTabla().getValueAt(fila, columna).toString());
						CargarInfoUsuario();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			break;
			case "Motivo": vista.getTxtMotivo().setText((String)vista.getTabla().getValueAt(fila, columna));
			break;
			case "Descripcion": vista.getTxtDescripcion().setText((String)vista.getTabla().getValueAt(fila, columna));
			break;
			case "Valor Vendedor":{
				String value = new String(vista.getTabla().getValueAt(fila, columna).toString());
				if (value.equals(""))vista.getTxtValVendedor().setValue(0);
				else vista.getTxtValVendedor().setValue(new Integer(value));
			}
			break;
			case "Valor Producto":{
				String value = new String(vista.getTabla().getValueAt(fila, columna).toString());
				if (value.equals(""))vista.getTxtValProducto().setValue(0);
				else vista.getTxtValProducto().setValue(new Integer(value));

			}
			break;
			case "Fecha":{
				try {
					String value = new String(vista.getTabla().getValueAt(fila, columna).toString());
					if (value.equals("")) vista.getDateFecha().setDate(new Date());
					else vista.getDateFecha().setDate(new SimpleDateFormat("dd/MM/yyyy").parse(value));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
				break;
			default:
				break;
			}
		}
	} 
	
	void limpiar(){
		vista.getLblrif().setText("");
		vista.getLblRazonSocial().setText("");
		vista.getLblTelefono().setText("");
		vista.getLblDireccion().setText("");
		vista.getLblRuta().setText("");
		vista.getLbljefeVentaid().setText("");
		vista.getLblCedula().setText("");
		vista.getLblJefeUsername().setText("");
		vista.getLblNombre().setText("");
		vista.getLblZona().setText("");
		}
	
	void mostrarEditarUsuario(){
		JFrame frame = vista.getFrame();
		
		vista.getPanelVisitaJefeVenta().setVisible(true);
		frame.add(vista.getPanelVisitaJefeVenta());
		frame.setTitle("Editar Jefe de Venta");
		frame.setBounds(100, 200, 500, 400);
		frame.setLayout(new GridLayout(1,0,0,0));
		frame.setVisible(true);	
	}
	
	void CargarInfoCliente(String rif) throws FileNotFoundException{
		Cliente cliente = new ServicioCliente().getCliente(rif);
		vista.getLblrif().setText(cliente.getRif());
		vista.getLblRazonSocial().setText(cliente.getRazonsocial());
		vista.getLblTelefono().setText(cliente.getTelefono());
		vista.getLblDireccion().setText(cliente.getDireccion());
		vista.getLblRuta().setText("Ruta: "+cliente.getRuta().getNombre());
		
	} 
	
	void CargarInfoUsuario() throws IOException{
		Usuario usuario = (Usuario)vista.getComboUsuario().getSelectedItem();
		if (vista.getComboTipoUser().getSelectedItem().equals("JefeVenta"))
		{
			JefeVenta jefeVenta = new ServicioJefeVenta().getJefeVenta(usuario.getId());
			vista.getLbljefeVentaid().setText(jefeVenta.getId().toString());
			vista.getLblCedula().setText(jefeVenta.getCedula());
			vista.getLblJefeUsername().setText(jefeVenta.getUsername());
			vista.getLblNombre().setText(jefeVenta.getNombre());
			vista.getLblZona().setText("Zona: "+jefeVenta.getZona().getDescripcion());
		}
		else if (vista.getComboTipoUser().getSelectedItem().equals("Concesionario"))
		{			
			Concesionario concesionario = new ServicioConcesionario().getConcesionario(usuario.getId());
			vista.getLbljefeVentaid().setText(concesionario.getId().toString());
			vista.getLblCedula().setText(concesionario.getCedula());
			vista.getLblJefeUsername().setText(concesionario.getUsername());
			vista.getLblNombre().setText(concesionario.getNombre());
			vista.getLblZona().setText("Zona: "+concesionario.getRuta().getZona().getDescripcion());
		}
		
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
						if(visita != null)
						{
							Integer opt = new Integer(JOptionPane.showConfirmDialog(new JPanel(),"Esta seguro de eliminar la Visita\n"
									+ "Id: "+visita.getId()+"\n"
									+ "Vendedor/JefeVenta:"+visita.getUsuario().getUsername()+"\n"
									+ "Fecha: "+visita.getFechaStr()+"\n"
											+ "Cliente: "+visita.getCliente().getRazonsocial()));
							
							if (opt.equals(0))
							{ 
								new ServicioVisita().Eliminar(visita);
								JOptionPane.showMessageDialog(vista,"Operacion Exitosa ");
								activarBinding(new ServicioVisita().ConsultaJefeVenta((JefeVenta)vista.getComboUsuario().getSelectedItem()));
							}
				
						}
						else 	JOptionPane.showMessageDialog(vista,"Visita no Existente ");
						} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		};
	}

	public PropertyChangeListener changeDateFecha() {
		// TODO Auto-generated method stub
		return	new PropertyChangeListener(){ 
	        public void propertyChange(PropertyChangeEvent e) {
	               
	                if(vista.getDateFecha().getDate()!=null && 
	                		vista.getLbljefeVentaid().getText()!="" &&
	                		vista.getLblrif().getText()!="")
	                {
	                	try {
	                		if(new ServicioVisita().isVisita(vista.getDateFecha().getDate(),
	                				((Cliente)new ServicioCliente().getCliente(vista.getLblrif().getText())).getId(),
	                				new Integer(vista.getLbljefeVentaid().getText())))
								{
	                				vista.getLblMensaje().setForeground(Color.cyan);
									vista.getLblMensaje().setText( "Editar Visita");
									Visita visita = new ServicioVisita().getVisita(
											vista.getDateFecha().getDate(),
											new Integer(vista.getLbljefeVentaid().getText()),
											((Cliente)new ServicioCliente().getCliente(vista.getLblrif().getText())).getId());
									vista.getTxtMotivo().setText(visita.getMotivo());
		                			vista.getTxtDescripcion().setText(visita.getDescripcion());
		                			vista.getTxtValProducto().setValue(visita.getValorProducto());
		                			vista.getTxtValVendedor().setValue(visita.getValorVendedor());
		                			vista.getChckbxEstado().setSelected(visita.getEstado());
								}
	                		else 
	                		{
	                			vista.getLblMensaje().setForeground(Color.lightGray);
	                			vista.getLblMensaje().setText( "Nueva Visita");
	                			vista.getTxtMotivo().setText("");
	                			vista.getTxtDescripcion().setText("");
	                			vista.getTxtValProducto().setValue(0);
	                			vista.getTxtValVendedor().setValue(0);
	                			vista.getChckbxEstado().setSelected(false);
	                			
	                		}
							} catch (NumberFormatException | FileNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
	                }
	                else vista.getLblMensaje().setText("");
	        }
	};
	}
	@SuppressWarnings("rawtypes")
	public JComboBox getComboTipoUser(){
		return vista.getComboTipoUser();
	}
	@SuppressWarnings("rawtypes")
	public JComboBox getComboUsuario(){
		return vista.getComboUsuario();
	} 
}