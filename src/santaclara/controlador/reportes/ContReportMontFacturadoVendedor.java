package santaclara.controlador.reportes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import santaclara.Servicio.ServicioConcesionario;
import santaclara.Servicio.ServicioFactura;
import santaclara.Servicio.ServicioVendedor;
import santaclara.controlador.ContGeneral;
import santaclara.controlador.ContPrincipal;
import santaclara.controlador.IContGeneral;
import santaclara.modelo.Almacen;
import santaclara.modelo.Factura;
import santaclara.vista.reporte.MontoFacturadoVendedorUI;

public class ContReportMontFacturadoVendedor extends ContGeneral implements IContGeneral {

	private static MontoFacturadoVendedorUI vista;
	private List<Factura> pedidoFacturados;
	
	public ContReportMontFacturadoVendedor() {
		// TODO Auto-generated constructor stub
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ContReportMontFacturadoVendedor(ContPrincipal contPrincipal) throws Exception {
		// TODO Auto-generated constructor stub
		setContPrincipal(contPrincipal);
		vista = new MontoFacturadoVendedorUI(this);
		dibujar(vista,this);
		vista.getCmbVendedor().setModel(new DefaultComboBoxModel(new String[] {"Vendedor", "Concesinario", "Todos"}));
		vista.getDateHasta().setMaxSelectableDate(new Date());
		vista.getDateHasta().setDate(new Date());
		vista.getDateDesde().setDate(new Date());
		
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
	
	public void actualizarTabla() throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		pedidoFacturados = new ServicioFactura().getPedidoFacturados();
		
		List<Factura> facturasAux = new ArrayList<Factura>();// se cargaran las facturas filtradas 
		List<Factura> facturas = new ArrayList<Factura>();// se cargaran las facturas a ser mostradas
		
		Double monto = new Double(0);
		//Valida vendedor
		if(vista.getCmbVendedor().getSelectedItem().equals("Todos"))
		{
			facturasAux = pedidoFacturados;
		}
		else
		{
			for(Factura factura:pedidoFacturados )
			{	
				if(vista.getCmbVendedor().getSelectedItem().equals("Vendedor"))
				{
					if (new ServicioVendedor().getVendedor(factura.getVendedor().getId())!=null)
					{
						facturasAux.add(factura); //carga la factura filtrada por vendedor
					}
				
				}
				else
				{
					if (new ServicioConcesionario().getConcesionario(factura.getVendedor().getId())!=null)
					{
						facturasAux.add(factura); //carga la factura filtrada por vendedor
					}
				}
			}
		} 
		
		//Valida fecha
		for(Factura factura: facturasAux)
		{
			if (factura.getFecha().getTime() >= vista.getDateDesde().getDate().getTime() &&
					factura.getFecha().getTime() <= vista.getDateHasta().getDate().getTime())
			{
				facturas.add(factura);
			}
		}
		//Acumula monto e imprime 
		for(Factura factura: facturas) monto = monto + factura.getTotalAPagar();
		vista.getLblMonto().setText("Monto Total Facturado es.....: "+monto+" BsF. ".concat("en el Almacen: "+vista.getCmbVendedor().getSelectedItem()));
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

	public ActionListener Actualizar() {
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

