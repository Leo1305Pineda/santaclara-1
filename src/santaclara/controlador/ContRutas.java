package santaclara.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import santaclara.Servicio.ServicioRuta;
import santaclara.modelo.Ruta;
import santaclara.modelo.Zona;
import santaclara.vista.RutasUI;

public class ContRutas extends ContGeneral implements IContGeneral {
	
	private RutasUI vista;
	private ServicioRuta servicioRuta;

	
	public ContRutas(ContPrincipal contPrincipal) throws Exception {
		// TODO Auto-generated constructor stub
		setContPrincipal(contPrincipal);
		servicioRuta = new ServicioRuta();
		vista = new RutasUI(this,servicioRuta.getRutas(),servicioRuta.getZonas());
		vista.activarBinding(servicioRuta.getRutas());
		dibujar(vista,this);
		vista.quitarNuevo();
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
				tabla1 = vista.getTabla();
				Boolean enc = true;
				for(int i = 0;i<tabla1.getRowCount();i++)
				{
					if (tabla1.getValueAt(i, 0).equals(vista.getTxtABuscar().getText())||
						tabla1.getValueAt(i, 1).equals(vista.getTxtABuscar().getText())||
						tabla1.getValueAt(i, 2).equals(vista.getTxtABuscar().getText()))
					{
						tabla1.setRowSelectionInterval(i,i);;
						enc = false;
						break;
					}
				}
				if (enc) JOptionPane.showMessageDialog(vista,"No Encontrado");
				vista.setTabla(tabla1);
				vista.setTxtABuscar("");
			}
		};
	}
	
	public ActionListener eliminar() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (vista.getTabla().getSelectedRow()>=0)
				{
					try {
						Ruta ruta;
						ruta=servicioRuta.buscar(new Integer(vista.getTabla().getValueAt(vista.getTabla().getSelectedRow(),0).toString()));
												
						servicioRuta.eliminar(ruta);
				
						vista.getBinRutas().unbind();
						vista.getBinRutas().bind();				
						vista.activarBinding(servicioRuta.getRutas());
						JOptionPane.showMessageDialog(vista,"Operacion Exitosa ");
		
					} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					}
				}
				else
				{
					JOptionPane.showMessageDialog(vista,"Seleccione la Ruta ");
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
						if (vista.getTabla().getSelectedRow()>=0)
						{
							Ruta ruta = new Ruta();
							ruta = servicioRuta.buscar(new Integer(vista.getTabla().getValueAt(vista.getTabla().getSelectedRow(),0).toString()));
							if (ruta != null)
							{
								vista.activarNuevoRuta();
								vista.getTxtId().setText(ruta.getId().toString());
								vista.getTxtNombre().setText(ruta.getNombre());
								setSelectedValue(vista.getCmbZona(), ruta.getZona().getId());
							}
						}
						else
						{
							JOptionPane.showMessageDialog(vista,"Seleccione la Ruta ");
						}	
				}catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}; 
	}

	public ActionListener guardar() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				// se va hacer las validaciones del controlador 

				Ruta ruta = new Ruta();
				String msg="";
				
				if (vista.getTxtId().getText().equals("")) ruta.setId(null); 
					else ruta.setId(new Integer(vista.getTxtId().getText().toString()));
				
				if (vista.getTxtNombre().getText().equals("")) msg=" Nombre, ";
				else ruta.setNombre(vista.getTxtNombre().getText());
				
				ruta.setZona((Zona)vista.getCmbZona().getSelectedItem());
				
				if (msg!="") JOptionPane.showMessageDialog(vista,"Campos Vacios: "+msg);
				else
				{
					try {
							if (!servicioRuta.guardar(ruta))
							{
								JOptionPane.showMessageDialog(vista,"Nombre de la Ruta Existente");
							}
							else
								{
									// agregarlo a la lista;
									vista.getBinRutas().unbind();
									vista.getBinRutas().bind();
									vista.activarBinding(servicioRuta.getRutas());   
									JOptionPane.showMessageDialog(vista,"Operacion Exitosa ");
								}
							vista.quitarNuevo();
					
						} catch (IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showConfirmDialog(null,e1.getMessage());
						e1.printStackTrace();
						}
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
				vista.activarNuevoRuta();
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


	public ActionListener AbrirZona() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
						new ContZonas(getContPrincipal());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
}
