package santaclara.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import santaclara.Servicio.ServicioConcesionario;
import santaclara.modelo.Camion;
import santaclara.modelo.Concesionario;
import santaclara.modelo.Ruta;
import santaclara.vista.UsuariosUI;

public class ContConcesionarios extends ContGeneral implements IContGeneral {
	
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
	
}
