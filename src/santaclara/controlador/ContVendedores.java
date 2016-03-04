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
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import santaclara.Servicio.ServicioJefeVenta;
import santaclara.Servicio.ServicioRuta;
import santaclara.Servicio.ServicioUsuario;
import santaclara.Servicio.ServicioVendedor;
import santaclara.Servicio.ServicioZona;
import santaclara.modelo.JefeVenta;
import santaclara.modelo.Ruta;
import santaclara.modelo.Vendedor;
import santaclara.modelo.Zona;
import santaclara.vista.JefeVentaUI;
import santaclara.vista.UsuariosUI;
import santaclara.vista.VendedorUI;

public class ContVendedores extends ContGeneral implements IContGeneral {

	private VendedorUI 			vista ;
	private ServicioVendedor 	servicioVendedor;
	private ServicioZona 		servicioZona;
	private ServicioRuta   		servicioRuta;
	private ServicioUsuario 	servicioUsuario;
	
 
	public ContVendedores(ContPrincipal contPrincipal) throws Exception {
		// TODO Auto-generated constructor stub
		setContPrincipal(contPrincipal);
		servicioVendedor = new ServicioVendedor();
		servicioZona = new ServicioZona();
		servicioRuta = new ServicioRuta();
		servicioUsuario= new ServicioUsuario();
		
		vista = new VendedorUI(this,servicioVendedor.getVendedores(),servicioZona.getZonas());
		dibujar(vista,this); 
	}
	
	@Override
	public JPanel getVista() {
		// TODO Auto-generated method stub
		return vista;
	}
	
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
					if(servicioUsuario.buscar(nombreUsuario) != null)
					{
						 throw new Exception(" nombre de usuario actualmente utilizado ");
					}
					else if(servicioUsuario.buscarCedula(cedula) != null)
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
					Vendedor usuario = new Vendedor(); 
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
					usuario.setRutas(vista.getRutasVendedor());
					servicioVendedor.guardar(usuario);
					vista.activarBinding(servicioVendedor.getVendedores());
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
			}
		};
	}
	

	public ActionListener eliminar() {
		// TODO Auto-generated method 
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (vista.getTable().getSelectedRow()>=0 && vista.getTable().getSelectedColumn() < vista.getVendedores().size())
				{
					try {				
						JefeVenta jefeVenta = (JefeVenta) vista.getVendedores().get(vista.getTable().getSelectedColumn());
						///servicioVendedor.eliminar(jefeVenta);
						JOptionPane.showMessageDialog(vista,"Operacion Exitosa");
						vista.activarBinding(servicioVendedor.getVendedores());
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
					Vendedor  vendedor = (Vendedor) vista.getVendedores().get(vista.getTable().getSelectedRow());
					vista.repaint();
					vista.cargar(vendedor);
				}
			}
		};
	}
	/*
	private UsuariosUI vista ;
	private ServicioVendedor servicioVendedor = new ServicioVendedor();
	
	@Override
	public JPanel getVista() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ContVendedores(UsuariosUI vista) {
		super();
		this.vista = vista;
	}

	public String ValidarTxt(){
		
	if (vista.getTxtNombre().getText().equals("")) return " Nombre ";
	if (vista.getTxtCedula().getText().equals("")) return " Cedula ";
	if (vista.getTxtContrasena().getText().equals("")) return " Contraseña ";
	if (vista.getTxtUserName().getText().equals("")) return " UserName ";
	if (!vista.getTxtReContrasena().getText().equals(vista.getTxtContrasena().getText())) return "Contraseña invalida ";
	if (vista.getRutasVendedores().size()<=0) return "Ninguna Ruta Asociada al Concesionario";
	return "";
	}
	
	public void Guardar() throws Exception {
		// TODO Auto-generated method stub
				// se va hacer las validaciones del controlador 
				Vendedor vendedor = new Vendedor();
			
				if (vista.getTxtId().getText().equals("")) vendedor.setId(null);//Nuevo vendedor
				else vendedor.setId(new Integer(vista.getTxtId().getText().toString().trim()));//Modifica vendedor 
				
				ValidarTxt();
				
					vendedor.setNombre(vista.getTxtNombre().getText());
					vendedor.setCedula(vista.getTxtCedula().getText());
					vendedor.setContrasena(vista.getTxtContrasena().getText());
					vendedor.setUsername(vista.getTxtUserName().getText());
					vendedor.setRutas(vista.getRutasVendedores());
					try {
							servicioVendedor.guardar(vendedor);
							JOptionPane.showMessageDialog(vista,"Operacion Exitosa");
							MostrarTabla();
						} catch (IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showConfirmDialog(null,e1.getMessage());
						e1.printStackTrace();
						}
	}
	
	public ActionListener modificar(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					if (vista.getTable().getSelectedRow()>=0)
					{
						Vendedor vendedor = new Vendedor();
						vendedor = servicioVendedor.buscar(new Integer(vista.getTable().getValueAt(vista.getTable().getSelectedRow(),0).toString()));
						if (vendedor != null)
						{
							vista.activarNuevoVendedor();
							vista.getTxtId().setText(vendedor.getId().toString());
							vista.getTxtCedula().setText(vendedor.getCedula());
							vista.getTxtNombre().setText(vendedor.getNombre());
							vista.getTxtContrasena().setText(vendedor.getContrasena());
							vista.getTxtReContrasena().setText(vendedor.getContrasena());
							vista.getTxtUserName().setText(vendedor.getUsername());
							
							vista.setRutasVendedores(vendedor.getRutas());
							
							vista.activarBindingRutas(vendedor.getRutas());//vista.getRutasVendedores());
							
						}
					}
					else
					{
						JOptionPane.showMessageDialog(vista,"Seleccione Vendedor ");
					}	
				} catch (Exception e1) {
					// TODO Auto-generated catch block
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
				vista.activarNuevoVendedor();
				
			}
		};
	}

	public void Modificar() {
		// TODO Auto-generated method stub
		try {	
			Vendedor vendedor  = new Vendedor();
			vendedor = servicioVendedor.buscar(new Integer(vista.getTable().getValueAt(vista.getTable().getSelectedRow(),0).toString().trim()));

			if (vendedor != null)
			{
				vista.activarNuevo();

				vista.getTxtId().setText(vendedor.getId().toString());
				vista.getTxtNombre().setText(vendedor.getNombre());
				vista.getTxtCedula().setText(vendedor.getCedula());
				vista.getTxtContrasena().setText(vendedor.getContrasena());
				vista.getTxtUserName().setText(vendedor.getUsername());
				vista.getTxtReContrasena().setText(vendedor.getContrasena());//Temporal
				vista.setRutasVendedores(vendedor.getRutas());
				vista.activarBindingRutas(vendedor.getRutas());

			}
			else JOptionPane.showMessageDialog(vista,"Seleccione la fila");
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	
	public void Eliminar() throws Exception{
		// TODO Auto-generated method stub
		Vendedor vendedor = servicioVendedor.getVendedor(
				new Integer(vista.getTable().getValueAt(
							vista.getTable().getSelectedRow(),0).toString()));

		if (vendedor.getId().equals(null)) JOptionPane.showMessageDialog(vista,"Operacion Fallida\n Valor no exixtente");
		else
			{
				servicioVendedor.eliminar(vendedor);
				JOptionPane.showMessageDialog(vista,"Operacion Exitosa");
				MostrarTabla();
			}
	}
	
	void MostrarTabla() throws Exception{
		vista.getBinUsuarios().unbind();
		vista.getBinUsuarios().bind();
		vista.activarBindingVendedores(servicioVendedor.getVendedores());
		vista.quitarNuevo();
	}
*/

	public ActionListener cargarRuta() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					if (vista.getCmbZona() != null )
					{
						 Zona zona = (Zona) vista.getCmbZona().getSelectedItem();
						 System.out.println(zona);
						 List<Ruta> rutas = servicioRuta.getRutas(zona);
						 vista.activarBindingRuta(rutas);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(vista,"Error al cargar las Rutas "+e1.getMessage());
				}
			}
		};
	}

	public ActionListener agregarRuta() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(vista.getCmbRuta().getSelectedItem() != null )
				{
					Ruta ruta = (Ruta) vista.getCmbRuta().getSelectedItem();
					if(vista.getRutasVendedor().size() < 3 )
					{
						vista.agregarRuta(ruta);
					}
				}
			}
		};
	}

	public ActionListener quitarRuta() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(vista.getjTblRutas().getSelectedIndex() >= 0 )
				{
					Ruta ruta = (Ruta) vista.getRutasVendedor().get(vista.getjTblRutas().getSelectedIndex());
					int respuesta = JOptionPane.showConfirmDialog(null, "Quieres quitar esta Ruta ?","Seleccione",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
					if(respuesta == JOptionPane.YES_OPTION)
					{
						vista.quitarRuta(ruta);
					}
				}
			}
		};
	}
}
