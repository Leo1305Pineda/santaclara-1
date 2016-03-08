/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.controlador.reportes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTable;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import santaclara.Servicio.ServicioDetalleFactura;
import santaclara.controlador.ContGeneral;
import santaclara.controlador.ContPrincipal;
import santaclara.controlador.IContGeneral;
import santaclara.modelo.DetalleFactura;
import santaclara.vista.reporte.ReporteUI;

public class ContReporte extends ContGeneral implements IContGeneral {

	private ReporteUI vista;
	String inicio;
	
	//declaracion de tantas lista sean nesesaria
	@SuppressWarnings({ "rawtypes" })
	private List list = new ArrayList<>();
	
	@Override
	public JPanel getVista() {
		// TODO Auto-generated method stub
		return null;
	}

	public ContReporte(ContPrincipal contPrincipal) throws Exception{
		super();
		// TODO Auto-generated constructor stub
		inicio = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		setContPrincipal(contPrincipal);
		vista = new ReporteUI(this);
		dibujar(vista,this);
		list = new ServicioDetalleFactura().getDetalleFacturas();
				
		}
	
	
	//metodo para filtrado y demas
	
	public ActionListener Actualizar() {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
					actualizarTabla();
				
			}
		};
	}
	
	@SuppressWarnings("unchecked")
	public void actualizarTabla(){
		//Manejo de listas
		
		//por Ultimo lanzar la lista final a ser mostrado
		activarBinding(list);
	}
	
	
	   @SuppressWarnings({ "rawtypes", "unchecked" })
		public void activarBinding(List<DetalleFactura> detalleFacturas) {
			// TODO Auto-generated method stub
			vista.setTable(new JTable());
			vista.getScrollPanel().setViewportView(vista.getTable());
			JTableBinding	binFacturas = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ_WRITE,detalleFacturas,vista.getTable());
		    
		    BeanProperty idFactura  = BeanProperty.create("factura.id");
		    BeanProperty fecha  = BeanProperty.create("factura.fechaCadenaStr");
		    BeanProperty almacen = BeanProperty.create("factura.almacen.ubicacion");
		    BeanProperty producto = BeanProperty.create("empaqueProducto.descripcionEmpaque");
		    BeanProperty cantidad = BeanProperty.create("cantidad");
		
		    binFacturas.addColumnBinding(idFactura).setColumnClass(String.class).setColumnName("Nro Factura");
		    binFacturas.addColumnBinding(fecha).setColumnClass(String.class).setColumnName("Fecha");
		    binFacturas.addColumnBinding(almacen).setColumnClass(String.class).setColumnName("Almacen");
		    binFacturas.addColumnBinding(producto).setColumnClass(String.class).setColumnName("Producto");
		    binFacturas.addColumnBinding(cantidad).setColumnClass(String.class).setColumnName("Cantidad");
		    

		    binFacturas.bind();
		}

		@Override
		public String asociar() {
			// TODO Auto-generated method stub
			return inicio;
		}


}
