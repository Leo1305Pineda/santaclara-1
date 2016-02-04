package santaclara.controlador;

import java.awt.HeadlessException;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import santaclara.Servicio.ServicioJefeVenta;
import santaclara.modelo.JefeVenta;
import santaclara.modelo.Zona;
import santaclara.vista.UsuariosUI;

public class ContJefeVentas extends ContGeneral implements IContGeneral {
	
	private UsuariosUI vista ;
	private ServicioJefeVenta servicioJefeVenta = new ServicioJefeVenta();
	
	@Override
	public JPanel getVista() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ContJefeVentas(UsuariosUI vista) {
		super();
		this.vista = vista;
	}
	
	public String ValidarTxt(){
		
		if (vista.getTxtNombre().getText().equals("")) return " Nombre ";
		if (vista.getTxtCedula().getText().equals("")) return " Cedula ";
		if (vista.getTxtContrasena().getText().equals("")) return " Contraseña ";
		if (vista.getTxtUserName().getText().equals("")) return " UserName ";
		if (!vista.getTxtReContrasena().getText().equals(vista.getTxtContrasena().getText())) return "Contraseña invalida ";
		return "";
		}

	public void Guardar() throws HeadlessException, IOException {
		// TODO Auto-generated method stub
		if (ValidarTxt().equals(""))
		{
			JefeVenta jefeVenta = new JefeVenta();
		
			if (vista.getTxtId().getText().equals("")) jefeVenta.setId(null);
			else jefeVenta.setId(new Integer(vista.getTxtId().getText().toString().trim()));
		
			jefeVenta.setUsername(vista.getTxtUserName().getText().toString());
			jefeVenta.setCedula(vista.getTxtCedula().getText().toString().trim());  
			jefeVenta.setNombre(vista.getTxtNombre().getText().toString());
			jefeVenta.setContrasena(vista.getTxtContrasena().getText().toString().trim());
			jefeVenta.setZona((Zona) vista.getCmbZona().getSelectedItem());	
			
			JOptionPane.showMessageDialog(vista,servicioJefeVenta.guardar(jefeVenta));
			MostrarTabla();
		}
		else JOptionPane.showMessageDialog(vista,ValidarTxt());
	}

	public void Modificar() {
		// TODO Auto-generated method stub
		try {	
				JefeVenta jefeVenta  = new JefeVenta();
				jefeVenta = servicioJefeVenta.buscar(new Integer(vista.getTable().getValueAt(vista.getTable().getSelectedRow(),0).toString().trim()));
	
				if (jefeVenta != null)
				{
					vista.activarNuevo();

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

	public void Eliminar() throws IOException {
		// TODO Auto-generated method stub
		JefeVenta jefeVenta = servicioJefeVenta.getJefeVenta(
									new Integer(vista.getTable().getValueAt(
												vista.getTable().getSelectedRow(),0).toString()));
		//falta implementar cuando existe un jefe de venta el la visita lo cual no se podria eliminar el jefe de venta
		
		if (jefeVenta.getId().equals(null)) JOptionPane.showMessageDialog(vista,"Operacion Fallida\n Valor no exixtente");
		else 
			{
				servicioJefeVenta.eliminar(jefeVenta);
				JOptionPane.showMessageDialog(vista,"Operacion Exitosa");
				MostrarTabla();
			}
	}

	void MostrarTabla() throws NumberFormatException, IOException{
		vista.getBinUsuarios().unbind();
		vista.getBinUsuarios().bind();
		vista.activarBindingJefeVentas(servicioJefeVenta.getJefeVentas());
		vista.quitarNuevo();
	}
	
}
