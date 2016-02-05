package santaclara.controlador.consultas;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import santaclara.Servicio.ServicioFactura;
import santaclara.controlador.ContGeneral;
import santaclara.controlador.ContPrincipal;
import santaclara.controlador.IContGeneral;
import santaclara.modelo.Almacen;
import santaclara.modelo.Factura;
import santaclara.vista.consultas.MontoFacturadoMesZonaTipoPagoUI;

public class ContMontoFacturadoMesZonaTipoPago extends ContGeneral implements IContGeneral {

	private static MontoFacturadoMesZonaTipoPagoUI vista;
	private static List<Factura> facturas ;
	Double valueAcum = new Double(0.0);
	
	public ContMontoFacturadoMesZonaTipoPago() {
		// TODO Auto-generated constructor stub
		
	}
	
	public ContMontoFacturadoMesZonaTipoPago(ContPrincipal contPrincipal) throws Exception {
		// TODO Auto-generated constructor stub
		setContPrincipal(contPrincipal);
		vista = new MontoFacturadoMesZonaTipoPagoUI(this);
		facturas = new ArrayList<Factura>();
		
		dibujar(vista,this);
		vista.cargarCmbAlmacen();
		setSelectedValue(vista.getCmbAlmacen(), null);
		vista.getDateHasta().setMaxSelectableDate(new Date());
		vista.getDateHasta().setDate(new Date());
		vista.getDateDesde().setDate(new Date());
		
		for(Factura factura :new ServicioFactura().getPedidoFacturados())
		{
			facturas.add(factura);
		}
		for(Factura factura :new ServicioFactura().getPedidoPendientes())
		{
			facturas.add(factura);
		}
	}
	@Override
	public JPanel getVista() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Factura> getFilterByCmbAlmacen(List<Factura> facturas){
		List<Factura> facturasAux = new ArrayList<Factura>();
		if(!((Almacen)vista.getCmbAlmacen().getSelectedItem()).getId().equals(0))
		{
			for(Factura factura: facturas)
			{
				if (factura.getAlmacen().getId().equals(((Almacen)vista.getCmbAlmacen().getSelectedItem()).getId()))
				{
					facturasAux.add(factura);
				}			
			}
			return facturasAux;
		}
		return facturas;
	}
	
	public List<Factura> getFilterByDate(List<Factura> facturas){
		List<Factura> facturasAux = new ArrayList<Factura>();
		//Filtra fecha Respecto Al dateDesde Y dateHasta
		for(Factura factura: facturas)
		{
			
			if (factura.getFecha().getTime() >= vista.getDateDesde().getDate().getTime() &&
					factura.getFecha().getTime() <= vista.getDateHasta().getDate().getTime())
			{
					facturasAux.add(factura);
			}
		}
		return facturasAux;
	}
	
	public List<List<Factura>> getOrderListGroupBy(String condition,List<List<Factura>> listGroup){
		List<List<Factura>> listGroupByAux = new ArrayList<List<Factura>>();
		for(List<Factura> list : listGroup) listGroupByAux.add(getOrderBy(condition, list));
		return listGroupByAux;
	}
	
	public List<Factura> getOrderBy(String condition,List<Factura> facturasNotOrder){
		List<Factura> facturasAux = new ArrayList<Factura>();
		Factura menorFactura = new Factura();
		while(!facturasNotOrder.isEmpty())
		{
			menorFactura = facturasNotOrder.get(0);
			
			for(Factura factura: facturasNotOrder)
			{	
				if(factura!=null && menorFactura !=null)
				{
					if(idValue(condition, factura) <= idValue(condition, menorFactura) )
					{	
						menorFactura = factura;	
					}
				}
			}
			facturasAux.add(menorFactura);
			
			facturasNotOrder.remove(menorFactura);
		
		}
		return facturasAux;
	}
	
	@SuppressWarnings("deprecation")
	public Long idValue(String condition,Factura factura){
		if(factura!=null)
		{
			switch (condition) {
			case "Date":return factura.getFecha().getTime();
			case "Month":return new Long(factura.getFecha().getMonth());
			case "Almacen": return  new Long(factura.getAlmacen().getId());
			case "Zona": return  new Long(factura.getCliente().getRuta().getZona().getId());
			case "TipoPago": 
				if (factura.getEstado()==null)return  new Long(0);
				else if (factura.getEstado()==true)return  new Long(1);
				else return  new Long(2);	
			default:	return null;
			}
		}
		return null; 
	}
	
	public List<List<Factura>> getGroupListGroupBy(String condition,List<List<Factura>> listGroup){
		List<List<Factura>> facturasAux = new ArrayList<List<Factura>>();
		
		for(List<Factura> facturas : listGroup)
		{
			for(List<Factura> facturas2 : getGroupBy(condition, getOrderBy(condition, facturas)))
			{
				facturasAux.add(facturas2);
				
			}
			facturasAux.add(new ArrayList<Factura>());
		}
	return facturasAux;
	}
	
	public List<List<Factura>> getGroupBy(String condition,List<Factura> facturas){
		List<Factura> facturasAux = new ArrayList<Factura>();
		List<List<Factura>> listGroupBy = new ArrayList<List<Factura>>(); 	
		
		if(!facturas.isEmpty())
		{
			
		Long id =  idValue(condition, facturas.get(0)) ;
		for(Factura factura: facturas)
		{
			if(factura !=null)
			{
				if(!id.equals(idValue(condition, factura)))
				{
					listGroupBy.add(facturasAux);
					
					listGroupBy.add(new ArrayList<Factura>());//new Line
					
					facturasAux = new ArrayList<Factura>();//Lo instancio nuevamente
				}
				facturasAux.add(factura);
				id = idValue(condition, factura);
			}
			else listGroupBy.add(new ArrayList<Factura>());
			
		}
		}
		listGroupBy.add(facturasAux);		
		return listGroupBy;
	}
	
	public List<Factura> getConvertListGroupToListOrderBy(List<List<Factura>> listGroup,Factura indicat){
		List<Factura> facturasAux = new ArrayList<Factura>();
		for(List<Factura> facturas : listGroup)
		{
			if(facturas == null || facturas.size()==0)
			{
				facturasAux.add(new Factura());
			}
			else for(Factura factura : facturas) facturasAux.add(factura);
		} 
		return facturasAux;
	}

	public List<Factura> getAcumCantidad(List<Factura> facturas){
		
		for(Factura factura : facturas)
		{
			if(factura.getTotalAPagar() != null) 
			{
				valueAcum = valueAcum + factura.getTotalAPagar();
			}
				else
				{
					if(!valueAcum.equals(0.0))
					{
						factura.setTotalAPagar(valueAcum);
					}
					valueAcum = new Double(0.0);
				}
		}
		return facturas;
	}
	
	
	public List<Factura> getFilterByLineNull(Integer maxLine,List<Factura> facturas){
		Integer valueLine = new Integer(0);
		
		List<Factura> facturasAux = new ArrayList<Factura>();
		
		for(Factura factura : facturas)
		{
			if(factura.getId() == null)
			{
				valueLine=valueLine+1;
			}
			else
			{
				valueLine = 0;
			}
			
			if(valueLine<=maxLine)
			{
				facturasAux.add(factura);
			}
		}
		return facturasAux;
	}
	
	public void actualizarTabla() throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		/**7- Obtener informacion mediante ventana de consultas acerca de:
		 * Monto total facturado mensualmente 
		 * por zona y tipo de pago 
		 * */
		
		List<List<Factura>> listGroup = new ArrayList<List<Factura>>();
		List<Factura> listOrder = new ArrayList<Factura>();
	
		listOrder = getOrderBy("Date", getFilterByDate(getFilterByCmbAlmacen(facturas)));
	
		listGroup = getGroupBy("Month",listOrder);
	
		listGroup = getOrderListGroupBy("Zona", listGroup);
	
		listGroup = getGroupListGroupBy("Zona",listGroup);
	
		listGroup = getOrderListGroupBy("TipoPago", listGroup);
	
		listGroup = getGroupListGroupBy("TipoPago", listGroup);
	
		listOrder = getConvertListGroupToListOrderBy(listGroup, null);

		listOrder = getFilterByLineNull(2, listOrder);

		listOrder = getAcumCantidad(listOrder);
		
		vista.activarBinding(listOrder);
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
					vista.getTable().setDefaultRenderer(String.class, getTableCellRenderer());
				} catch (NumberFormatException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
	}

	public static List<Factura> getDetalleFacturas() {
		return facturas;
	}

	public static void setDetalleFacturas(List<Factura> facturas) {
		ContMontoFacturadoMesZonaTipoPago.facturas = facturas;
	}

	public static void setVista(MontoFacturadoMesZonaTipoPagoUI vista) {
		ContMontoFacturadoMesZonaTipoPago.vista = vista;
	}

	public TableCellRenderer getTableCellRenderer(){
		return new TableCellRenderer() {
			
			@Override
			public Component getTableCellRendererComponent(JTable tabla, Object value,
					boolean arg2, boolean arg3, int row, int column) {
				// TODO Auto-generated method stub
				tabla.setShowGrid(false);
				tabla.setBackground(Color.lightGray);
				
				JPanel celda = new JPanel();
				celda.setBackground(Color.WHITE);
				JLabel label = new JLabel();	
				String linea = new String("");
				String columnName = new String(vista.getTable().getColumnName(column)); 
				
				if(value == null) label.setText(linea);
				else label.setText(value.toString());
				
				if(label.getText().equals(linea)) celda.setBackground(Color.lightGray);
				
				if(columnName.equals("Monto"))
				{
					
					if(vista.getTable().getValueAt(row,0)==null)
					{
						if(!label.getText().equals(""))label.setText("Total: ".concat(label.getText()).concat(" Monto.Facturado"));
						label.setForeground(Color.blue);
					}
				}
				else label.setForeground(Color.BLACK);
				
				celda.add(label);
				celda.setLayout(new GridLayout(1, 0, 0, 0));
				return celda;
			}
		};
	}
	
	void printListOrdenBy(List<Factura> listOrder,String title){
		System.out.println("/***************Start Print  ".concat(title).concat("********************"));
		for(Factura factura : listOrder){
			if (factura==null)
			{
				System.out.println("new line");
			}
			else
			{
				System.out.println("data: "+ factura.getId());
			}
		}
		System.out.println("/***************End Print Size List "+listOrder.size()+"********************");
	}
	
	void printListGroupBy(List<List<Factura>> listGroup,String title){
		System.out.println("/***************Start Print  ".concat(title).concat("********************"));
		for(List<Factura> facturas :listGroup){
			
			if (facturas==null)
			{
				System.out.println("new line");
			}
			else
			{
				printListOrdenBy(facturas,title);
			}
			
		}
		System.out.println("/***************End Print  Size List "+listGroup.size()+" ".concat(title).concat("********************\n"));
	}
	
}

