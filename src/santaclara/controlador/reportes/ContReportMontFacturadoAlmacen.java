package santaclara.controlador.reportes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import santaclara.Servicio.ServicioFactura;
import santaclara.controlador.ContGeneral;
import santaclara.controlador.ContPrincipal;
import santaclara.controlador.IContGeneral;
import santaclara.modelo.Almacen;
import santaclara.modelo.Factura;
import santaclara.vista.reporte.MontoFacturadoAlmacenUI;

public class ContReportMontFacturadoAlmacen extends ContGeneral implements IContGeneral {

	private static MontoFacturadoAlmacenUI vista;
	
	//Estoy Probando A ver
	public ContReportMontFacturadoAlmacen() {
		// TODO Auto-generated constructor stub
		
	}
	
	public ContReportMontFacturadoAlmacen(ContPrincipal contPrincipal) throws Exception {
		// TODO Auto-generated constructor stub
		setContPrincipal(contPrincipal);
		vista = new MontoFacturadoAlmacenUI(this);
		dibujar(vista,this);
		
		vista.cargarCmbAlmacen();
		setSelectedValue(vista.getCmbAlmacen(), null);
	}
	@Override
	public JPanel getVista() {
		// TODO Auto-generated method stub
		return null;
	}

	public ActionListener action() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					//mostrarDato();
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		};
	}
	
	public static void actualizarTabla() throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		List<Factura> facturas = new ArrayList<Factura>(); // se cargaran las facturas a ser mostradas 
		
		Double monto = new Double(0);
	
		for(Factura factura: new ServicioFactura().getFacturas()){
		
		if (factura.getAlmacen().getId().equals(((Almacen)vista.getCmbAlmacen().getSelectedItem()).getId()))
			{
			//Validar las fechas
				facturas.add(factura);
				monto = monto + factura.getTotalAPagar();
				
			}	
		}		
		vista.getLblMonto().setText("Monto Total Facturado es.....: "+monto+" BsF. ".concat("en el Almacen: "+((Almacen)vista.getCmbAlmacen().getSelectedItem()).getUbicacion()));
		vista.activarBinding(facturas);
	}
	@SuppressWarnings("rawtypes")
	public void setSelectedValue(JComboBox comboBox,Integer id)
    {	
        for (int i = 0; i < comboBox.getItemCount(); i++)
        {
        	comboBox.setSelectedIndex(i);
        	Boolean enc=false;
        	switch (comboBox.getSelectedItem().getClass().getName().toString()) {
			case "santaclara.modelo.Almacen":
				enc = (((Almacen)comboBox.getSelectedItem()).getId().equals(id)); 
					break;
			default:
				break;
			}
        	if (enc) break;
        }
    }

	public ActionListener Atras() {
		// TODO Auto-generated method stub
		return null;
	}

	public ActionListener buscar() {
		// TODO Auto-generated method stub
		return null;
	}

	public static ActionListener Actualizar() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					actualizarTabla();
				} catch (NumberFormatException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
	}

}

