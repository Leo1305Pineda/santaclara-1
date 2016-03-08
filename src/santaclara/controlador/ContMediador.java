package santaclara.controlador;

import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import santaclara.Servicio.ServicioConcesionario;
import santaclara.Servicio.ServicioDomicilioComercio;
import santaclara.Servicio.ServicioProductoAlmacen;
import santaclara.Servicio.ServicioSalp;
import santaclara.Servicio.ServicioVendedor;
import santaclara.modelo.Almacen;
import santaclara.modelo.Cliente;
import santaclara.modelo.Concesionario;
import santaclara.modelo.DomicilioComercio;
import santaclara.modelo.ProductoAlmacen;
import santaclara.modelo.Usuario;
import santaclara.modelo.Vendedor;

public class ContMediador {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void cargarCliente(ContPedidos contPedidos) {
		// TODO Auto-generated method stub
	
		try {
			ContClientes contClientes = new  ContClientes(contPedidos.getContPrincipal());
			contClientes.getVista().getBtnAtras().setText("Seleccione");
			if(contPedidos.getFactura().getVendedor()!=null)
			{
				if((new ServicioVendedor().getVendedor(contPedidos.getFactura().getVendedor().getId()))!=null)
				{
					Vendedor vendedor1 = new ServicioVendedor().getVendedor(contPedidos.getFactura().getVendedor().getId());
					contClientes.getVista().getCmbTipoCliente().setModel(new DefaultComboBoxModel(new String[] {"Salp"}));
					contClientes.getVista().getCmbTipoCliente().setEnabled(true);
					contClientes.setRutas(vendedor1.getRutas());
					contClientes.activarBindingSalp(new ServicioSalp().getSalps(vendedor1.getRutas()));
				}
				else if((new ServicioConcesionario().getConcesionario(contPedidos.getFactura().getVendedor().getId()))!=null)
				{
					Concesionario vendedor1 = new ServicioConcesionario().getConcesionario(contPedidos.getFactura().getVendedor().getId());
					contClientes.getVista().getCmbTipoCliente().setModel(
							new DefaultComboBoxModel(
									new String[] {"Domicilio","Comercial"}));
					contClientes.getVista().getCmbTipoCliente().setEnabled(true);
					contClientes.getRutas().add(vendedor1.getRuta());
					List<DomicilioComercio> domicilioComercios =new ServicioDomicilioComercio().getDomicilioComercios(vendedor1.getRuta());
					if (domicilioComercios.isEmpty())
					{
						contClientes.getCliente().setRuta(vendedor1.getRuta());
						contClientes.cargarCliente(contClientes.getCliente());
						contClientes.activarBindingDomicilioComercios(domicilioComercios);
						throw new Exception("ruta null");
					}
					else
					{
						contClientes.activarBindingDomicilioComercios(domicilioComercios);
					}
							
				}
			}
		} catch (Exception exe) {
			// TODO Auto-generated catch block
			switch (exe.getMessage()) {
			case "ruta null":JOptionPane.showMessageDialog(contPedidos.getVista(), "No existen Cliente en la ruta del Vendedor \n "
					+ "Cree un nuevo Cliente", "..:: Aviso ::.." ,2, new ImageIcon("img/gestion/group.png"));
				break;

			default:
				break;
			}
		}
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void cargarVendedor(ContPedidos contPedidos){
		try {
			ContUsuarios contUsuarios = new ContUsuarios(contPedidos.getContPrincipal());
			contUsuarios.getVistaUsuario().getBtnAtras().setText("Seleccione");
			if(contPedidos.getFactura().getCliente() !=null)
			{
				if ((new ServicioSalp().getSalp(contPedidos.getFactura().getCliente().getId()))!=null)
				{
					contUsuarios.getVistaUsuario().getCmbTipoUsuario().setModel(
							new DefaultComboBoxModel(
									new String[] {"Vendedor"}));
					contUsuarios.getVistaUsuario().getCmbTipoUsuario().setEnabled(true);;
					contUsuarios.getVistaUsuario().activarBindingVendedores(
							new ServicioVendedor().getVendedores(contPedidos.getFactura().getCliente().getRuta().getId()));
				}
				else if (new ServicioDomicilioComercio().getdDomicilioComercio(contPedidos.getFactura().getCliente().getId())!=null)
				{
					contUsuarios.getVistaUsuario().getCmbTipoUsuario().setModel(
							new DefaultComboBoxModel(
									new String[] {"Concesionario"}));
					contUsuarios.getVistaUsuario().getCmbTipoUsuario().setEnabled(true);
					contUsuarios.getVistaUsuario().activarBindingConcesionarios(
							new ServicioConcesionario().getConcecionarios(contPedidos.getFactura().getCliente().getRuta().getId()));
				}					
			}
			else
			{
				contUsuarios.getVistaUsuario().getCmbTipoUsuario().setModel(
						new DefaultComboBoxModel(
								new String[] {"Vendedor", "Concesionario"}));
				contUsuarios.getVistaUsuario().activarBindingVendedores(
						new ServicioVendedor().getVendedores());
			}
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	}
	
	public void cargarAlmacen(ContPedidos contPedidos){
		try {
			
			ContAlmacenes contAlmacenes = new ContAlmacenes(contPedidos.getContPrincipal());
			contAlmacenes.getVistaAlmacen().getBtnAtras().setText("Seleccione");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void cargarProducto(ContPedidos contPedidos){
		try {
			
			if(contPedidos.getFactura().getAlmacen() == null ) 
			{
				throw new Exception("Almacen");
			}
			else 
			{
				ContProductoAlmacenes contProductoAlmacenes = new ContProductoAlmacenes(contPedidos.getContPrincipal());

				contProductoAlmacenes.getVista().getBtnAtras().setText("Seleccione");

				contProductoAlmacenes.activarBinding(
						new ServicioProductoAlmacen().getProductoAlmacenes(contPedidos.getFactura().getAlmacen().getId()));
			}
			
		} catch (Exception exe) {
			// TODO Auto-generated catch block
			Integer opt = new Integer(	JOptionPane.showConfirmDialog(contPedidos.getVista(),"Cargar la Informacion del "+exe.getMessage()));
			if (opt==0)
			{
				switch (exe.getMessage()) {
				case "Almacen":contPedidos.getVista().getBtnAlmacen().doClick();
					break;
				default:
					break;
				}

			}
			
		}

	}

	public void regresarCliente(ContClientes contClientes, Cliente cliente) {
		// TODO Auto-generated method stub
		
		if(contClientes.getCacheObjet().size()>1)
		{
			contClientes.ActivarAtras(cliente);
		}
		else
		{
			contClientes.quitarVista();
		}
	}

	public void regresarAlmacen(ContAlmacenes contAlmacenes, Almacen almacen) {
		// TODO Auto-generated method stub
		
		if(contAlmacenes.getCacheObjet().size()>1)
		{
			contAlmacenes.ActivarAtras(almacen);
		}
		else
		{
			contAlmacenes.quitarVista();
		}
	}
	
	public void regresarProducto(ContProductoAlmacenes contProductos, ProductoAlmacen productoAlmacen) {
		// TODO Auto-generated method stub
		
		if(contProductos.getCacheObjet().size()>1)
		{
			contProductos.ActivarAtras(productoAlmacen);
		}
		else
		{
			contProductos.quitarVista();
		}
	}
	
	public void regresarUsuario(ContUsuarios contUsuarios, Usuario usuario) {
		// TODO Auto-generated method stub
		
		if(contUsuarios.getCacheObjet().size()>1)
		{
			contUsuarios.ActivarAtras(usuario);
		}
		else
		{
			contUsuarios.quitarVista();
		}
	}

}
