package santaclara.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import santaclara.Servicio.ServicioCamion;
import santaclara.Servicio.ServicioConcesionario;
import santaclara.modelo.Concesionario;
import santaclara.modelo.Camion;
import santaclara.vista.CamionesUI;

public class ContCamiones extends ContGeneral implements IContGeneral{
	
	private ServicioCamion servicioCamion;
	private CamionesUI vista;
	
	public ContCamiones(ContPrincipal contPrincipal) throws Exception {
		// TODO Auto-generated constructor stub
		setContPrincipal(contPrincipal);
		servicioCamion = new ServicioCamion();
		vista = new CamionesUI(this, servicioCamion.getCamiones());
		dibujar(vista,this);
	}

	@Override
	public JPanel getVista() {
		// TODO Auto-generated method stub
		return vista;
	}
	
	public ActionListener nuevo(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				vista.activarNuevoCamion();
				vista.getPnTabla().setVisible(false);
			}
		};
	}

	public ActionListener modificar() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (vista.getTable().getSelectedRow()>=0)
				{
					Camion camion  = new Camion();
					try {
						camion = servicioCamion.buscar(new Integer(vista.getTable().getValueAt(vista.getTable().getSelectedRow(),0).toString().trim()));
					} catch (NumberFormatException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if (camion != null)
					{
						vista.activarNuevoCamion();
						vista.getScrollPanel().setVisible(false);
						
						vista.getTxtId().setText(camion.getId().toString());
						vista.getTxtPlaca().setText(camion.getPlaca());
						vista.getTxtMarca().setText(camion.getMarca());
						vista.getTxtModelo().setText(camion.getModelo());
						vista.getTxtYear().setText(camion.getAno());
						vista.getTxtColor().setText(camion.getColor());
						vista.getTxtCapacidad().setValue(camion.getCapacidad());
						
					}
				}
				else
				{
					JOptionPane.showMessageDialog(vista,"Seleccione el Producto");
				}
		}
		};
	}
	public String ValidarTxt(){
		if (vista.getTxtPlaca().getText().equals("")) return "Campos Vacios:Placa";
		if (vista.getTxtMarca().getText().equals("")) return "Campos Vacios:Marca";
		if (vista.getTxtModelo().getText().equals("")) return "Campos Vacios:Modelo";
		if (vista.getTxtYear().getText().equals("")) return "Campos Vacios:Año";
		if (vista.getTxtColor().getText().equals("")) return "Campos Vacios:Color";
		if (vista.getTxtCapacidad().getValue().equals(0)) return "Campos Vacios:Capacidad";
		
		return "";
	}

	public ActionListener guardar() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Camion camiones = new Camion();
				
				if (vista.getTxtId().getText().equals("")) camiones.setId(null);
					else camiones.setId(new Integer(vista.getTxtId().getText().toString())); 
				
				if (ValidarTxt()=="")
				{
							try {
									camiones.setPlaca(vista.getTxtPlaca().getText().toString());
									camiones.setMarca(vista.getTxtMarca().getText().toString());
									camiones.setModelo(vista.getTxtModelo().getText().toString());
									camiones.setAno(vista.getTxtYear().getText().toString());
									camiones.setCapacidad((Double)vista.getTxtCapacidad().getValue());
									camiones.setColor(vista.getTxtColor().getText().toString());
									
									JOptionPane.showMessageDialog(vista,servicioCamion.guardar(camiones));
									// agregarlo a la lista
									vista.getCamiones().add(camiones);
									
									vista.getBinCamiones().unbind();
									vista.getBinCamiones().bind();
									vista.activarBinding(servicioCamion.getCamiones());
									vista.quitarNuevo();
									vista.getScrollPanel().setVisible(true);
									
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										JOptionPane.showConfirmDialog(null,e1.getMessage());
										e1.printStackTrace();
									}
				}
				else JOptionPane.showMessageDialog(vista,ValidarTxt());
			}
			
		};
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
				vista.setTxtABuscar("");;
				
			}
		};
	}
	
	public void setVista(CamionesUI vista) {
		this.vista = vista;
	}

	public ActionListener atras() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ActivarAtras(null);
			}
		};
	}
	
	public ActionListener salir(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				vista.quitarNuevo();
			}
		};
		
	}

	public ActionListener quitarNuevo() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				vista.quitarNuevo();
			}
		};
	}
	
	public ActionListener eliminar() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
						Camion camion;
						camion = servicioCamion.getCamion(new Integer(vista.getTable().getValueAt(vista.getTable().getSelectedRow(),0).toString()));
						
						ServicioConcesionario servicioConcesionario = new ServicioConcesionario();
						
						List<Concesionario> concesionarios = servicioConcesionario.getConcecionarios();
						Boolean enc = new Boolean(false);
						for(Concesionario concesionario: concesionarios)
						{
							if(concesionario.getCamion().getId().equals(camion.getId()))
								{
									enc=true;
									break;
								}
						}
						if(enc==false)
						{
							servicioCamion.eliminar(camion);
						
							vista.getBinCamiones().unbind();
							vista.getBinCamiones().bind();				
							vista.activarBinding(servicioCamion.getCamiones());
							JOptionPane.showMessageDialog(vista,"Operacion Exitosa ");
							vista.quitarNuevo();
						}
						else JOptionPane.showMessageDialog(vista,"Operacion Fallida\n"+
								" Objeto Existente en otra Clase? \n Elimine la relacion Exixtente en: Vendedor Concesionario");
						
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
	}
}
