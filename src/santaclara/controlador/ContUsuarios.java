package santaclara.controlador;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import santaclara.Servicio.ServicioCamion;
import santaclara.Servicio.ServicioUsuario;
import santaclara.modelo.Usuario;
import santaclara.vista.UsuariosUI;

public class ContUsuarios extends ContGeneral implements IContGeneral{
	
	private ServicioUsuario servicioUsuario = new ServicioUsuario();
	private ServicioCamion servicioCamion;
	private UsuariosUI vista;
	
	public ContUsuarios(ContPrincipal contPrincipal) throws Exception {
		// TODO Auto-generated constructor stub
		setContPrincipal(contPrincipal);
		
		servicioUsuario = new ServicioUsuario();
		servicioCamion = new ServicioCamion();
		
		vista = new UsuariosUI(this, servicioUsuario.getUsuarios());
		dibujar(vista,this);
	}

	@Override
	public JPanel getVista() {
		// TODO Auto-generated method stub
		return vista;
	}
	
	public UsuariosUI getVistaUsuario(){
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
						tabla1.getValueAt(i, 1).toString().trim().equals(vista.getTxtABuscar().getText().toString().trim()))
					{
						tabla1.setRowSelectionInterval(i,i);
						enc = true;
						break;
					}
				}
				if (!enc) JOptionPane.showMessageDialog(vista,"No Encontrado");
				vista.setTable(tabla1);
				vista.getTxtABuscar().setText("");
				
			}
		};
	}
	
	public void setVista(UsuariosUI vista) {
		this.vista = vista;
	}

	public ActionListener atras() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					if (vista.getTable().getSelectedRow()>=0)
					{
						Usuario usuario  = new Usuario();
						usuario = vista.getUsuarios().get(vista.getTable().getSelectedColumn());
						ActivarAtras(usuario);
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

	
	void MostrarTabla() throws NumberFormatException, IOException{
		vista.getBinUsuarios().unbind();
		vista.getBinUsuarios().bind();				
		vista.activarBinding(servicioUsuario.getUsuarios());
	}
	
	public ActionListener ActivarTipoUsuario() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					if(vista.getCmbTipoUsuario().getSelectedItem().equals("Todos")){
						vista.activarBinding(servicioUsuario.getUsuarios());
					}
					else if(vista.getCmbTipoUsuario().getSelectedItem().equals("JefeVenta"))
					{
						vista.activarBindingJefeVentas(servicioUsuario.getJefeVentas());
					}
					else if(vista.getCmbTipoUsuario().getSelectedItem().equals("Vendedor"))
					{
						vista.activarBindingVendedores(servicioUsuario.getVendedores());
					}
					else if(vista.getCmbTipoUsuario().getSelectedItem().equals("Concesionario")){
						vista.activarBindingConcesionarios(servicioUsuario.getConcesionarios());
					}
				} catch (NumberFormatException | IOException e)
				{
					e.printStackTrace();
				}
			}
		};
	}
	

	public ServicioUsuario getServicioUsuario() {
		return servicioUsuario;
	}

	public void setServicioUsuario(ServicioUsuario servicioUsuario) {
		this.servicioUsuario = servicioUsuario;
	}


	public ServicioCamion getServicioCamion() {
		return servicioCamion;
	}

	public void setServicioCamion(ServicioCamion servicioCamion) {
		this.servicioCamion = servicioCamion;
	}
	
	
}
