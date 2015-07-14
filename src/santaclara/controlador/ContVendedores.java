package santaclara.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import santaclara.Servicio.ServicioVendedor;
import santaclara.modelo.Vendedor;
import santaclara.vista.VendedoresUI;

public class ContVendedores extends ContGeneral implements IContGeneral {

	private VendedoresUI vista;
	private ServicioVendedor servicioVendedor = new ServicioVendedor();
	private ContPrincipal contPrincipal;
	private ContRutas contRutas;
	private ContClientes contClientes;
	
	public ContVendedores(ContPrincipal contPrincipal) throws Exception {
		// TODO Auto-generated constructor stub
		this.contPrincipal = contPrincipal;
		setContPrincipal(contPrincipal);
		vista = new VendedoresUI(this,servicioVendedor.getVendedores(),servicioVendedor.getRutas());
		vista.activarBinding(servicioVendedor.getVendedores());
		vista.activarJComboBoxBinding();
		dibujar(vista);
		vista.quitarNuevo();
	}

	@Override
	public JPanel getVista() {
		// TODO Auto-generated method stub
		return vista;
	}

	@SuppressWarnings("deprecation")
	public String ValidarTxt(){
		
	if (vista.getTxtNombre().getText().equals("")) return " Nombre ";
	if (vista.getTxtCedula().getText().equals("")) return " Cedula ";
	if (vista.getTxtContrasena().getText().equals("")) return " Contraseña ";
	if (vista.getTxtUsername().getText().equals("")) return " UserName ";
	if (!vista.getTxtContrasenaRepetir().getText().equals(vista.getTxtContrasena().getText())) return "Contraseña invalida ";
	return "";
	}
	
  // evento Guardar Prodcuto 
	public ActionListener guardar() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// se va hacer las validaciones del controlador 
				Vendedor vendedor = new Vendedor();
			
				if (vista.getTxtId().getText().equals("")) vendedor.setId(null);//Nuevo vendedor
				else vendedor.setId(new Integer(vista.getTxtId().getText().toString()));//Modifica vendedor 
				
				if (ValidarTxt().equals(""))//los text no estan vacios
				{
					vendedor.setNombre(vista.getTxtNombre().getText());
					vendedor.setCedula(vista.getTxtCedula().getText());
					vendedor.setContrasena(vista.getTxtContrasena().getText());
					vendedor.setUsername(vista.getTxtUsername().getText());
					vendedor.setRutas(vista.getRutasVendedores());
					try {
							JOptionPane.showMessageDialog(vista,servicioVendedor.guardar(vendedor));
							
							// agregarlo a la lista;
							vista.getBinVendedores().unbind();
							vista.getBinVendedores().bind();
							vista.activarBinding(servicioVendedor.getVendedores());   
					
						} catch (IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showConfirmDialog(null,e1.getMessage());
						e1.printStackTrace();
						}
				}
				else  JOptionPane.showMessageDialog(vista,"Campos Vacios: "+ValidarTxt());
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
	
	public ActionListener salir(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				qutarVista();
			}
		};
	}
	
	public ActionListener cliente(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					contClientes = new ContClientes(contPrincipal);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
	}
	
	public ActionListener ruta(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					contRutas= new ContRutas(contPrincipal);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
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
							vista.getTxtContrasenaRepetir().setText(vendedor.getContrasena());
							vista.getTxtUsername().setText(vendedor.getUsername());
							vista.setRutasVendedores(vendedor.getRutas());
							vista.activarBindingRutas(vista.getRutasVendedores());
							
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


	public ServicioVendedor getServicioVendedor() {
		return servicioVendedor;
	}

	public void setServicioVendedor(ServicioVendedor servicioVendedor) {
		this.servicioVendedor = servicioVendedor;
	}

	public ContRutas getContRutas() {
		return contRutas;
	}

	public void setContRutas(ContRutas contRutas) {
		this.contRutas = contRutas;
	}

	public ContClientes getContClientes() {
		return contClientes;
	}

	public void setContClientes(ContClientes contClientes) {
		this.contClientes = contClientes;
	}

	public void setVista(VendedoresUI vista) {
		this.vista = vista;
	} 
	
}
