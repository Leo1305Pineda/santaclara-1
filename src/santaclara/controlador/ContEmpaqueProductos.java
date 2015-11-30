package santaclara.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import santaclara.Servicio.ServicioEmpaqueProducto;
import santaclara.modelo.EmpaqueProducto;
import santaclara.modelo.Producto;
import santaclara.vista.EmpaqueProductosUI;

public class ContEmpaqueProductos extends ContGeneral implements IContGeneral {

	private EmpaqueProductosUI vista;
	private ServicioEmpaqueProducto servicioEmpaqueProducto = new ServicioEmpaqueProducto();;
	private ContPrincipal contPrincipal;	
	
	public ContEmpaqueProductos(ContPrincipal contPrincipal) throws Exception {
		// TODO Auto-generated constructor stub
		this.contPrincipal = contPrincipal;
		setContPrincipal(contPrincipal);
		vista = new EmpaqueProductosUI(this, servicioEmpaqueProducto.getEmpaqueProductos(), servicioEmpaqueProducto.getProductos());
		vista.activarBinding(servicioEmpaqueProducto.getEmpaqueProductos());
		dibujar(vista,this);
		vista.quitarNuevo();
	}

	@Override
	public JPanel getVista() {
		// TODO Auto-generated method stub
		return vista;
	}

  // evento Guardar Prodcuto 
	public ActionListener guardar() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// se va hacer las validaciones del controlador 

				EmpaqueProducto empaqueProducto = new EmpaqueProducto();
				String msg="";
				
				if (vista.getTxtId().getText().equals("")) empaqueProducto.setId(null);
					else empaqueProducto.setId(new Integer(vista.getTxtId().getText().toString()));
				
				if(vista.getTxtCantidad().getValue().equals(0))empaqueProducto.setCantidad(0);
					else empaqueProducto.setCantidad((Integer) vista.getTxtCantidad().getValue());
				
				empaqueProducto.setProducto((Producto)vista.getCmbProducto().getSelectedItem());
				
				if (msg!="") JOptionPane.showMessageDialog(vista,"Campos Vacios: "+msg);
					else
						{
							try {
									JOptionPane.showMessageDialog(vista,servicioEmpaqueProducto.guardar(empaqueProducto));
									// agregarlo a la lista
									vista.getEmpaqueProductos().add(empaqueProducto);
									vista.getBinEmpaqueProductos().unbind();
									vista.getBinEmpaqueProductos().bind();
									vista.activarBinding(servicioEmpaqueProducto.getEmpaqueProductos());
									vista.quitarNuevo();
									vista.getScrollPanel().setVisible(true);
									
								} catch (IOException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showConfirmDialog(null,e1.getMessage());
								e1.printStackTrace();
								}
						}
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
				Boolean enc = true;
				for(int i = 0;i<tabla1.getRowCount();i++)
				{
					if (tabla1.getValueAt(i, 0).toString().trim().equals(vista.getTxtABuscar().getText().toString().trim())||
						tabla1.getValueAt(i, 1).toString().equals(vista.getTxtABuscar().getText().toString())||
						tabla1.getValueAt(i, 2).toString().trim().equals(vista.getTxtABuscar().getText().toString().trim()))
					{
						tabla1.setRowSelectionInterval(i,i);;
						enc = false;
						break;
					}
				}
				if (enc) JOptionPane.showMessageDialog(vista,"No Encontrado");
				vista.setTable(tabla1);
				vista.setTxtABuscar("");
				vista.quitarNuevo();
			}
		};
	}
	
	public ActionListener eliminar() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (vista.getTable().getSelectedRow()>=0)
				{
					try {
						EmpaqueProducto empaqueProducto;
						empaqueProducto=servicioEmpaqueProducto.buscar(new Integer(vista.getTable().getValueAt(vista.getTable().getSelectedRow(),0).toString()));
												
						servicioEmpaqueProducto.eliminar(empaqueProducto);
				
						vista.getBinEmpaqueProductos().unbind();
						vista.getBinEmpaqueProductos().bind();				
						vista.activarBinding(servicioEmpaqueProducto.getEmpaqueProductos());
						JOptionPane.showMessageDialog(vista,"Operacion Exitosa ");
						vista.quitarNuevo();
		
					} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					}
				}
				else
				{
					JOptionPane.showMessageDialog(vista,"Seleccione el EmpaqueProducto ");
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
				vista.activarNuevoEmpaqueProducto();
				vista.getPnTabla().setVisible(false);
			}
		};
	}
	
	public ActionListener salir() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				quitarVista();
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
							EmpaqueProducto empaqueProducto = new EmpaqueProducto();
							empaqueProducto = servicioEmpaqueProducto.buscar(new Integer(vista.getTable().getValueAt(vista.getTable().getSelectedRow(),0).toString()));
							if (empaqueProducto != null)
							{
								vista.activarNuevoEmpaqueProducto();
								vista.getPnTabla().setVisible(false);
								
								vista.getTxtId().setText(empaqueProducto.getId().toString());
								vista.getTxtCantidad().setValue(empaqueProducto.getCantidad());
								
								setSelectedValue(vista.getCmbProducto(),empaqueProducto.getProducto().getId());
						
							}
						}
						else
						{
							JOptionPane.showMessageDialog(vista,"Seleccione el Empaque del Producto");
						}	
				}catch (IOException e1) {
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
			case "santaclara.modelo.Producto":
				enc = (((Producto)comboBox.getSelectedItem()).getId().equals(id)); 
					break;
			default:
				break;
			}
        	if (enc) break;
        }
    }

	public ActionListener AbrirProducto() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
						new ContProductos(contPrincipal);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
	}

	public ActionListener Atras() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					if (vista.getTable().getSelectedRow()>=0)
					{
						EmpaqueProducto empaqueProducto = new EmpaqueProducto();
						empaqueProducto = new ServicioEmpaqueProducto().buscar(new Integer(vista.getTable().getValueAt(vista.getTable().getSelectedRow(),0).toString()));
						ActivarAtras(empaqueProducto);
					}
					else 	ActivarAtras(null);
				} catch (NumberFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
	}

}

