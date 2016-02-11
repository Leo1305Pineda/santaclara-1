package santaclara.controlador.consultas;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import santaclara.Servicio.ServicioDomicilioComercio;
import santaclara.Servicio.ServicioSalp;
import santaclara.controlador.ContGeneral;
import santaclara.controlador.ContPrincipal;
import santaclara.controlador.IContGeneral;
import santaclara.modelo.Cliente;
import santaclara.vista.consultas.ListClienteZonaTipoUI;

public class ContListClienteTipoZona extends ContGeneral implements IContGeneral {

	private static ListClienteZonaTipoUI vista;
	private static List<Cliente> clientes ;
	Double valueAcum = new Double(0.0);
	
	public ContListClienteTipoZona() {
		// TODO Auto-generated constructor stub
		
	}
	
	public ContListClienteTipoZona(ContPrincipal contPrincipal) throws Exception {
		// TODO Auto-generated constructor stub
		setContPrincipal(contPrincipal);
		vista = new ListClienteZonaTipoUI(this);
		dibujar(vista,this);
		clientes = new ArrayList<>();
		clientes.addAll(new ServicioDomicilioComercio().getDomicilioComercios());
		clientes.addAll(new ServicioSalp().getSalps());
	}
	@Override
	public JPanel getVista() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Cliente> getFilterByCmbCliente(List<Cliente> clientes){
		List<Cliente> clientesAux = new ArrayList<Cliente>();
		if(!vista.getCmbTipoCliente().getSelectedItem().equals("Todos"))
		{
			for(Cliente cliente: clientes)
			{
				if (cliente.getTipoStr().equals(vista.getCmbTipoCliente().getSelectedItem()))
				{
					clientesAux.add(cliente);
				}
				else if (cliente.getTipoStr().equals(vista.getCmbTipoCliente().getSelectedItem()))
				{
					clientesAux.add(cliente);
				}
			}
			return clientesAux;
		}
		return clientes;
	}
	
	public List<List<Cliente>> getOrderListGroupBy(String condition,List<List<Cliente>> listGroup){
		List<List<Cliente>> listGroupByAux = new ArrayList<List<Cliente>>();
		for(List<Cliente> list : listGroup) listGroupByAux.add(getOrderBy(condition, list));
		return listGroupByAux;
	}
	
	public List<Cliente> getOrderBy(String condition,List<Cliente> clientesNotOrder){
		List<Cliente> facturasAux = new ArrayList<Cliente>();
		Cliente menorcliente = new Cliente();
		while(!clientesNotOrder.isEmpty())
		{
			menorcliente = clientesNotOrder.get(0);
			
			for(Cliente cliente: clientesNotOrder)
			{	
				if(cliente!=null && menorcliente !=null)
				{
					if(idValue(condition, cliente) <= idValue(condition, menorcliente) )
					{	
						menorcliente = cliente;	
					}
				}
			}
			facturasAux.add(menorcliente);
			
			clientesNotOrder.remove(menorcliente);
		
		}
		return facturasAux;
	}
	
	public Long idValue(String condition,Cliente cliente){
		if(cliente!=null)
		{ 
			switch (condition) {
			case "Zona": return  new Long(cliente.getRuta().getZona().getId());
			case "TipoCliente":
				if (cliente.getTipoStr()=="Todos")return  new Long(0);
				else if (cliente.getTipoStr()=="Salp")return  new Long(1);
				else if (cliente.getTipoStr()=="Domicilio")return  new Long(2);
				else return  new Long(3);
				
			default:	return null;
			}
		}
		return null; 
	}
	
	public List<List<Cliente>> getGroupListGroupBy(String condition,List<List<Cliente>> listGroup){
		List<List<Cliente>> clientesAux = new ArrayList<List<Cliente>>();
		
		for(List<Cliente> clientes : listGroup)
		{
			for(List<Cliente> clientes2 : getGroupBy(condition, getOrderBy(condition, clientes)))
			{
				clientesAux.add(clientes2);
				
			}
			clientesAux.add(new ArrayList<Cliente>());
		}
	return clientesAux;
	}
	
	public List<List<Cliente>> getGroupBy(String condition,List<Cliente> clientes){
		List<Cliente> clientesAux = new ArrayList<Cliente>();
		List<List<Cliente>> listGroupBy = new ArrayList<List<Cliente>>(); 	
		
		if(!clientes.isEmpty())
		{
			
		Long id =  idValue(condition, clientes.get(0)) ;
		for(Cliente cliente: clientes)
		{
			if(cliente !=null)
			{
				if(!id.equals(idValue(condition, cliente)))
				{
					listGroupBy.add(clientesAux);
					
					listGroupBy.add(new ArrayList<Cliente>());//new Line
					
					clientesAux = new ArrayList<Cliente>();//Lo instancio nuevamente
				}
				clientesAux.add(cliente);
				id = idValue(condition, cliente);
			}
			else listGroupBy.add(new ArrayList<Cliente>());
			
		}
		}
		listGroupBy.add(clientesAux);		
		return listGroupBy;
	}
	
	public List<Cliente> getConvertListGroupToListOrderBy(List<List<Cliente>> listGroup){
		List<Cliente> clientesAux = new ArrayList<Cliente>();
		for(List<Cliente> clientes : listGroup)
		{ 
			if(clientes == null || clientes.size()==0)
			{
				clientesAux.add(new Cliente()); 
			}
			else for(Cliente cliente : clientes) clientesAux.add(cliente);
		} 
		return clientesAux;
	}

	
	
	public List<Cliente> getFilterByLineNull(Integer maxLine,List<Cliente> clientes){
		Integer valueLine = new Integer(0);
		
		List<Cliente> clientesAux = new ArrayList<Cliente>();
		
		for(Cliente cliente : clientes)
		{
			if(cliente.getId() == null)
			{
				valueLine=valueLine+1;
			}
			else
			{
				valueLine = 0;
			}
			
			if(valueLine<=maxLine)
			{
				clientesAux.add(cliente);
			}
		}
		return clientesAux;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void actualizarTabla() throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		/**7- Obtener informacion mediante ventana de consultas acerca de:
		 * Monto total facturado mensualmente 
		 * por zona y tipo de pago 
		 * */
		
		List<List<Cliente>> listGroup = new ArrayList<List<Cliente>>();
		java.util.List listCliente = new ArrayList<Cliente>();
		
		listCliente.addAll(clientes);
	
		listCliente = getOrderBy("Zona", getFilterByCmbCliente(listCliente));
		
		listGroup = getGroupBy("Zona",listCliente);
		
		listGroup = getOrderListGroupBy("TipoCliente", listGroup);
		
		listGroup = getGroupListGroupBy("TipoCliente", listGroup);	
	
		listCliente = getConvertListGroupToListOrderBy(listGroup);
	
		listCliente = getFilterByLineNull(2, listCliente);
		
		vista.activarBinding(listCliente);
		
		
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

	
	void printListOrdenBy(List<Cliente> listOrder,String title){
		System.out.println("/***************Start Print  ".concat(title).concat("********************"));
		for(Cliente factura : listOrder){
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
	
	void printListGroupBy(List<List<Cliente>> listGroup,String title){
		System.out.println("/***************Start Print  ".concat(title).concat("********************"));
		for(List<Cliente> facturas :listGroup){
			
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

