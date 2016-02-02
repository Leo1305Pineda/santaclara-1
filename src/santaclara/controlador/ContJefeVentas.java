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

import santaclara.Servicio.ServicioJefeVenta;
import santaclara.Servicio.ServicioZona;
import santaclara.modelo.Cliente;
import santaclara.modelo.JefeVenta;
import santaclara.modelo.Zona;
import santaclara.vista.JefeVentaUI;
 

public class ContJefeVentas extends ContGeneral implements IContGeneral {
	
	private JefeVentaUI vista ;
	private ServicioJefeVenta servicioJefeVenta;
	private ServicioZona 	servicioZona;
	 
 
	public ContJefeVentas(ContPrincipal contPrincipal) throws Exception {
		// TODO Auto-generated constructor stub
		setContPrincipal(contPrincipal);
		servicioJefeVenta = new ServicioJefeVenta();
		servicioZona = new ServicioZona();
		
		vista = new JefeVentaUI(this,servicioJefeVenta.getJefeVentas(),servicioZona.getZonas());
		dibujar(vista,this); 
	}
	
	
	
	public String ValidarTxt(){
		
		if (vista.getTxtNombre().getText().equals("")) return " Nombre ";
		if (vista.getTxtCedula().getText().equals("")) return " Cedula ";
		if (vista.getTxtContrasena().getText().equals("")) return " Contraseña ";
		if (vista.getTxtUserName().getText().equals("")) return " UserName ";
		if (!vista.getTxtReContrasena().getText().equals(vista.getTxtContrasena().getText())) return "Contraseña invalida ";
		return "";
		}
 

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
	

	private void validar() {
		// TODO Auto-generated method stub
		
	}

	public ActionListener guardar() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub 
				try {
					JefeVenta usuario = new JefeVenta(); 
					if (vista.getTxtId().getText().equals("")) 
						usuario.setId(null);
					else 
					{ 
						usuario.setId(new Integer(vista.getTxtId().getText().toString().trim()));			
						usuario.setCedula(vista.getTxtCedula().getText().toString());
						usuario.setNombre(vista.getTxtNombre().getText().toString());
						usuario.setUsername(vista.getTxtUserName().getText().toString());
						usuario.setContrasena(vista.getTxtContrasena().getText().toString());
						usuario.setZona((Zona) vista.getCmbZona().getSelectedItem());	
						servicioJefeVenta.guardar(usuario);
						vista.activarBinding(servicioJefeVenta.getJefeVentas());
						JOptionPane.showMessageDialog(vista,"Operacion Exitosa");
					} 
				}catch (IOException e) {
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
			}
		};
	}
	

	public ActionListener eliminar() {
		// TODO Auto-generated method 
		
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (vista.getTable().getSelectedRow()>=0)
				{
					try {				
						JefeVenta jefeVenta = servicioJefeVenta.getJefeVenta(
								new Integer(vista.getTable().getValueAt(
											vista.getTable().getSelectedRow(),0).toString()));
							if (jefeVenta.getId().equals(null))
							{
								JOptionPane.showMessageDialog(vista,"Operacion Fallida\n Valor no exixtente");
							}
							else 
							{
								servicioJefeVenta.eliminar(jefeVenta);
								JOptionPane.showMessageDialog(vista,"Operacion Exitosa");
					
							}
						} catch (Exception e) {
							// TODO: handle exception
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
					JefeVenta  jefeVenta = (JefeVenta) vista.getJefeVentas().get(vista.getTable().getSelectedRow());
					vista.repaint();
					vista.cargarJefeVenta(jefeVenta);
				}
			}
		};
	}
	
}
