package santaclara.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import santaclara.Servicio.ServicioConcesionario;
import santaclara.Servicio.ServicioJefeVenta;
import santaclara.Servicio.ServicioRuta;
import santaclara.Servicio.ServicioZona;
import santaclara.modelo.Camion;
import santaclara.modelo.Concesionario;
import santaclara.modelo.JefeVenta;
import santaclara.modelo.Ruta;
import santaclara.modelo.Zona;
import santaclara.vista.ConcesionarioUI;
import santaclara.vista.JefeVentaUI;
import santaclara.vista.UsuariosUI;

public class ContConcesionarios extends ContGeneral implements IContGeneral {
	
	private ConcesionarioUI vista ;
	private ServicioConcesionario servicioConcesionario;
	private ServicioRuta 	servicioRuta;
	private Concesionario concesionario= new Concesionario();;
	
	
	
	public ContConcesionarios(ContPrincipal contPrincipal) throws Exception {
		// TODO Auto-generated constructor stub
		setContPrincipal(contPrincipal);
		servicioConcesionario = new ServicioConcesionario();
		servicioRuta = new ServicioRuta();
		
		vista = new ConcesionarioUI(this,servicioConcesionario.getConcecionarios(),servicioRuta.getRutas());
		dibujar(vista,this); 
	}
	
 
/*
	public void Modificar() {
		// TODO Auto-generated method stub
		try {	
				JefeVenta jefeVenta  = new JefeVenta();
				jefeVenta = servicioJefeVenta.buscar(new Integer(vista.getTable().getValueAt(vista.getTable().getSelectedRow(),0).toString().trim()));
				if (jefeVenta != null)
				{
					//vista.activarNuevo();
					vista.getTxtId().setText(jefeVenta.getId().toString());
					vista.getTxtNombre().setText(jefeVenta.getNombre());
					vista.getTxtCedula().setText(jefeVenta.getCedula());
					vista.getTxtContrasena().setText(jefeVenta.getContrasena());
					vista.getTxtUserName().setText(jefeVenta.getUsername());
					vista.getTxtReContrasena().setText(jefeVenta.getContrasena());//Temporal
					setSelectedValue(vista.getCmbZona(),jefeVenta.getZona().getId());

				}
				else JOptionPane.showMessageDialog(vista,"Seleccione la fila");
				
			} catch (NumberFormatException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
*/	
	/*
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
 */

	
	public ActionListener buscar() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JTable tabla1 = new JTable();
				tabla1 = vista.getTable();
				Boolean enc = false;
				for(int i = 0;i<tabla1.getRowCount();i++)
				{
					if (tabla1.getValueAt(i, 0).toString().trim().equals(vista.getTxtABuscar().getText().toString().trim())||
						tabla1.getValueAt(i, 1).toString().trim().equals(vista.getTxtABuscar().getText().toString().trim())||
						tabla1.getValueAt(i, 2).toString().trim().equals(vista.getTxtABuscar().getText().toString().trim())||
						tabla1.getValueAt(i, 3).toString().trim().equals(vista.getTxtABuscar().getText().toString().trim())||
						tabla1.getValueAt(i, 4).toString().trim().equals(vista.getTxtABuscar().getText().toString().trim()))
					{
						tabla1.setRowSelectionInterval(i,i);
						enc = true;
						break;
					}
				}
				if (!enc) 
					JOptionPane.showMessageDialog(vista,"No Encontrado");
				vista.setTable(tabla1);
				vista.getTxtABuscar().setText("");
				
			}
		};
	}
	

	public void validar() throws Exception {
		// TODO Auto-generated method stub.
		if(vista.getTxtCedula().getText().length() < 1 )
		{
				 throw new Exception("Campo Cedula Vacio ");
		}
		else if(vista.getTxtNombre().getText().length() < 3 )
		{
			 throw new Exception("Campo Nombre No es lo suficiente mente largo  ");
		}
		else if(vista.getTxtUserName().getText().length() < 4 )
		{
			 throw new Exception("Campo Nombre Usuario No es lo suficiente mente largo ");
		}
		else if(vista.getTxtContrasena().getText().length() < 4 )
		{
			 throw new Exception("Campo Contrasena Usuario No es lo suficiente mente largo ");
		}
		else if(vista.getTxtReContrasena().getText().length() < 4 )
		{
			 throw new Exception("Campo Repetir Contrasena Usuario No es lo suficiente mente largo ");
		}
		else if(! vista.getTxtReContrasena().getText().equals(vista.getTxtContrasena().getText()) )
		{
			 throw new Exception("Contrasena no coinciden ");
		}
		else if(vista.getCmbRuta().getSelectedIndex() < 0 )
		{
			 throw new Exception("seleccione una Ruta ");
		}
		else if (vista.getCamion() == null)
		{
			 throw new Exception("seleccione una Camion ");
		}
		else
		{
			String cedula = vista.getTxtCedula().getText();
			String nombreUsuario = vista.getTxtUserName().getText();
			//validar cedula, nombre de usuario y contrasena
			if (!vista.getTxtContrasena().getText().equals(vista.getTxtReContrasena().getText()))
			{
				 throw new Exception("La contraseña no coincide ");
			}
			else  //es nuevo ?
				if(vista.getTxtId().getText() == "")
				{
					if(servicioConcesionario.buscar(nombreUsuario) != null)
					{
						 throw new Exception(" nombre de usuario actualmente utilizado ");
					}
					else if(servicioConcesionario.buscarCedula(cedula) != null)
					{
						 throw new Exception(" cedula de usuario actualmente utilizado ");
					}		
				}
		}

	}

	public ActionListener guardar() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub 
				try
				{
					validar();
					Concesionario usuario = new Concesionario(); 
					if (vista.getTxtId().getText().equals("")) 
					{
						usuario.setId(null);
					}
					else 
					{
						usuario.setId(new Integer(vista.getTxtId().getText().toString().trim()));			
					}
					usuario.setCedula(vista.getTxtCedula().getText().toString());
					usuario.setNombre(vista.getTxtNombre().getText().toString());
					usuario.setUsername(vista.getTxtUserName().getText().toString());
					usuario.setContrasena(vista.getTxtContrasena().getText().toString());
					usuario.setRuta((Ruta) vista.getCmbRuta().getSelectedItem());	
					usuario.setCamion(vista.getCamion());
					servicioConcesionario.guardar(usuario);
					vista.activarBinding(servicioConcesionario.getConcecionarios());
					JOptionPane.showMessageDialog(vista,"Operacion Exitosa");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(vista,e.getMessage(),"error",JOptionPane.ERROR_MESSAGE);
				}
			}
		};
	}
	
	public ActionListener nuevo(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				vista.LimpiarTxt(); 
				vista.getTxtCedula().setEnabled(true);
				vista.getTxtUserName().setEnabled(true);
			   concesionario = new Concesionario();
			}
		};
	}
	

	public ActionListener eliminar() {
		// TODO Auto-generated method 
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (vista.getTable().getSelectedRow()>=0 && vista.getTable().getSelectedColumn() < vista.getConcecionarios().size())
				{
					try {				
						Concesionario concesionario = (Concesionario) vista.getConcecionarios().get(vista.getTable().getSelectedColumn());
						servicioConcesionario.eliminar(concesionario);
						JOptionPane.showMessageDialog(vista,"Operacion Exitosa");
						vista.activarBinding(servicioConcesionario.getConcecionarios());
					} catch (Exception e) 
					{
						e.printStackTrace(); 
						JOptionPane.showMessageDialog(vista,e.getMessage());
					}
					}
					else 
						JOptionPane.showMessageDialog(vista,"Seleccione la fila");
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

	public MouseListener mostrar() {
		// TODO Auto-generated method stub
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evento) {
				if (evento.getClickCount()==1)
				{
					concesionario = (Concesionario) vista.getConcecionarios().get(vista.getTable().getSelectedRow());
					ContConcesionarios.this.concesionario = concesionario;
					vista.getTxtCedula().setEnabled(false);
					vista.getTxtUserName().setEnabled(false);
					vista.repaint();
					vista.cargar(concesionario);
				}
			}
		};
	}


	public ActionListener verCamiones() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {

					if (vista.getTxtId().getText().equals("")) 
					{
						concesionario.setId(null);
					}
					else 
					{
						concesionario.setId(new Integer(vista.getTxtId().getText().toString().trim()));			
					}
					concesionario.setCedula(vista.getTxtCedula().getText().toString());
					concesionario.setNombre(vista.getTxtNombre().getText().toString());
					concesionario.setUsername(vista.getTxtUserName().getText().toString());
					concesionario.setContrasena(vista.getTxtContrasena().getText().toString());
					concesionario.setRuta((Ruta) vista.getCmbRuta().getSelectedItem());	
					concesionario.setCamion(vista.getCamion());;
					new ContCamiones(getContPrincipal(),concesionario);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showConfirmDialog(null,e1.getMessage());
				}
			}
		};
	}


	public Concesionario getConcesionario() {
		return concesionario;
	}


	public void setConcesionario(Concesionario concesionario) {
		this.concesionario = concesionario;
	}


	public void setCamion(Camion camion) {
		// TODO Auto-generated method stub
		concesionario.setCamion(camion);
		System.out.println(camion.getPlaca());
		vista.cargar(concesionario);	
	}


	@Override
	public JPanel getVista() {
		// TODO Auto-generated method stub
		return vista;
	}
	
	
	
	/*
	private UsuariosUI vista ;
	private ServicioConcesionario servicioConcesionario = new ServicioConcesionario();
	
	@Override
	public JPanel getVista() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ContConcesionarios(UsuariosUI vista) {
		super();
		this.vista = vista;
	}

	public void ValidarTxt() throws Exception{
		
	if (vista.getTxtNombre().getText().equals("")) throw new  Exception(" Nombre ");
	if (vista.getTxtCedula().getText().equals("")) throw new Exception(" Cedula ");
	if (vista.getTxtContrasena().getText().equals("")) throw new Exception(" Contraseña ");
	if (vista.getTxtUserName().getText().equals("")) throw new Exception(" UserName ");
	if (!vista.getTxtReContrasena().getText().equals(vista.getTxtContrasena().getText())) throw new Exception("Contraseña invalida ");
	}
	
	public void Guardar() throws Exception {
		// TODO Auto-generated method stub
				// se va hacer las validaciones del controlador 
				Concesionario concesionario = new Concesionario();
			
				if (vista.getTxtId().getText().equals("")) concesionario.setId(null);//Nuevo vendedor
				else concesionario.setId(new Integer(vista.getTxtId().getText().toString()));//Modifica vendedor 
				
				ValidarTxt();
				
					concesionario.setNombre(vista.getTxtNombre().getText());
					concesionario.setCedula(vista.getTxtCedula().getText());
					concesionario.setContrasena(vista.getTxtContrasena().getText());
					concesionario.setUsername(vista.getTxtUserName().getText());
					concesionario.setRuta((Ruta)vista.getComboRutas().getSelectedItem());
					concesionario.setCamion((Camion)vista.getCmbCamion().getSelectedItem());
					try {
							servicioConcesionario.guardar(concesionario);
							JOptionPane.showMessageDialog(vista,"Operacion Exitosa");
							MostrarTabla();
						} catch (IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showConfirmDialog(null,e1.getMessage());
						e1.printStackTrace();
						}
	}
	
	public ActionListener nuevo() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				vista.activarNuevoConcesionario();
				
			}
		};
	}

	public void Modificar() {
		// TODO Auto-generated method stub
		try {
				Concesionario concesionario = new Concesionario();
				concesionario = servicioConcesionario.buscar(new Integer(vista.getTable().getValueAt(vista.getTable().getSelectedRow(),0).toString()));
				if (concesionario != null)
				{
					
					vista.activarNuevoConcesionario();
					vista.getTxtId().setText(concesionario.getId().toString());
					vista.getTxtCedula().setText(concesionario.getCedula());
					vista.getTxtNombre().setText(concesionario.getNombre());
					vista.getTxtContrasena().setText(concesionario.getContrasena());
					vista.getTxtReContrasena().setText(concesionario.getContrasena());
					vista.getTxtUserName().setText(concesionario.getUsername());
					setSelectedValue(vista.getComboRutas(),concesionario.getRuta().getId());
					setSelectedValue(vista.getCmbCamion(), concesionario.getCamion().getId());
					
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
	}
	
	@SuppressWarnings("rawtypes")
	public void setSelectedValue(JComboBox comboBox,Integer id)
	{	
		for (int i = 0; i < comboBox.getItemCount(); i++)
		{
			comboBox.setSelectedIndex(i);
			Boolean enc=false;
			switch (comboBox.getSelectedItem().getClass().getName().toString()) {
			case "santaclara.modelo.Ruta":
				enc = (((Ruta)comboBox.getSelectedItem()).getId().equals(id)); 
				break;
			case "santaclara.modelo.Camion":
				enc = (((Camion)comboBox.getSelectedItem()).getId().equals(id)); 
				break;
			default:
				break;
			}
			if (enc) break;
		}
	}
	
	public void Eliminar() throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		Concesionario concesionario = servicioConcesionario.getConcesionario(
				new Integer(vista.getTable().getValueAt(
							vista.getTable().getSelectedRow(),0).toString()));
		
		//	falta implementar cuando existe un Concesionario el ConcesionarioRuta lo cual no se podria eliminar el jefe de venta

		if (concesionario.getId().equals(null)) JOptionPane.showMessageDialog(vista,"Operacion Fallida\n Valor no exixtente");
		else
			{
				servicioConcesionario.eliminar(concesionario);
				JOptionPane.showMessageDialog(vista,"Operacion Exitosa");
				MostrarTabla();
			}
	}

	void MostrarTabla() throws NumberFormatException, IOException{
		vista.getBinUsuarios().unbind();
		vista.getBinUsuarios().bind();
		vista.activarBindingConcesionarios(servicioConcesionario.getConcecionarios());
		vista.quitarNuevo();
	}
	
	*/
	
}
