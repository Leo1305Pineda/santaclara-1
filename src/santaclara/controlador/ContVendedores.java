package santaclara.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import santaclara.Servicio.ServicioVendedor;
import santaclara.modelo.Vendedor;
import santaclara.vista.UsuariosUI;

public class ContVendedores extends ContGeneral implements IContGeneral {
	
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
			
		} catch (NumberFormatException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	
	public void Eliminar() throws NumberFormatException, IOException {
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
	
	void MostrarTabla() throws NumberFormatException, IOException{
		vista.getBinUsuarios().unbind();
		vista.getBinUsuarios().bind();
		vista.activarBindingVendedores(servicioVendedor.getVendedores());
		vista.quitarNuevo();
	}

	
}
