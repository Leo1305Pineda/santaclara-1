package santaclara.controlador;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

 

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel; 
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import santaclara.modelo.Almacen;
import santaclara.modelo.Capacidad;
import santaclara.modelo.Presentacion;
import santaclara.modelo.Producto;
import santaclara.modelo.Sabor;
import santaclara.modelo.Zona;

public abstract class ContGeneral implements IContGeneral {

	
	private ContPrincipal 	 contPrincipal;
//	  JLabel lblImagen = new JLabel();
		
	public void dibujar(JPanel vista,Object controladorNuevo)
	{
		contPrincipal.agregarPanel(vista);
		if(!this.contPrincipal.getCacheObjet().empty())
		{
			Object actual = this.contPrincipal.getCacheObjet().pop();
			if (actual.equals(controladorNuevo))
			{
				this.contPrincipal.getCacheObjet().push(actual);
			}
			else
			{
				this.contPrincipal.getCacheObjet().push(actual);
				this.contPrincipal.getCacheObjet().push(controladorNuevo);
			}
		}
		else 
			this.contPrincipal.getCacheObjet().push(controladorNuevo);
	}
	
	public void quitarVista(){//btnSalir
		contPrincipal.quitarPanel();
		if (!this.contPrincipal.getCacheObjet().empty())
		{
			while(this.contPrincipal.getCacheObjet().size()>1)
			{
				this.contPrincipal.getCacheObjet().pop();
			}
		}
	}
	 
	
	
	
	public void ActivarAtras(Object mensaje) {//btnAtras
		try {
			if(this.contPrincipal.getCacheObjet().size()>1)
				this.contPrincipal.ActivarAtras(mensaje);
			else
				quitarVista();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
	}

	public ContPrincipal getContPrincipal() {
		return contPrincipal;
	}

	public void setContPrincipal(ContPrincipal contPrincipal) {
		this.contPrincipal = contPrincipal;
	}	
	
	public Stack<String> getCache() {
		return this.contPrincipal.getCache();
	}

	public void setCache(Stack<String> cache) {
		this.contPrincipal.setCache(cache);
		
	}
	
	public Stack<Object> getCacheObjet() {
		return this.contPrincipal.getCacheObjet();
	}

	public void setCacheObjet(Stack<Object> cacheObject) {
		this.contPrincipal.setCacheObjet(cacheObject);
		
	}

	public List<Icon> getImagenes(String ruta) throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<Icon> iconos = new ArrayList<Icon>();
		File file = new File(ruta.concat("conf.txt"));
 		Scanner scanner = new Scanner(file);
		while(scanner.hasNext())
		{
			iconos.add(new ImageIcon(ruta.concat(scanner.skip("imagen:").nextLine().toString().trim())));
		}
		scanner.close();
		return iconos;
	}
	
	public void runAnimacion(JLabel lblImagen,String folder,Integer tiempo){
		List<Icon> imagenes = new ArrayList<Icon>();
		try {
			imagenes = getImagenes("img/animados/".concat(folder).concat("/"));
	
				//System.out.println(imagenes.size());
				for(Icon icon : imagenes){
					Thread.sleep(tiempo);
					lblImagen.setIcon(icon);	
				}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public JTable buscar(JTable tabla1,String valueBuscar){
		tabla1.clearSelection();
		for(int i = 0;i<tabla1.getRowCount();i++)
		{
			
			for(int j = 0; j<=tabla1.getColumnCount()-1; j++) 
			{
				if (tabla1.getValueAt(i, j).toString().trim().equals(valueBuscar))
				{
					tabla1.setRowSelectionInterval(i,i);
					return tabla1;			
				}
			}
		}
		return tabla1;			
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
			case "santaclara.modelo.Capacidad":
				enc = (((Capacidad)comboBox.getSelectedItem()).getId().equals(id)); 
					break;
			case "santaclara.modelo.Presentacion":
				enc = (((Presentacion)comboBox.getSelectedItem()).getId().equals(id)); 
					break;
			case "santaclara.modelo.Zona":
				enc = (((Zona)comboBox.getSelectedItem()).getId().equals(id)); 
					break;
			case "santaclara.modelo.Producto":
				enc = (((Producto)comboBox.getSelectedItem()).getId().equals(id)); 
					break;
			case "santaclara.modelo.Sabor":
				enc = (((Sabor)comboBox.getSelectedItem()).getId().equals(id)); 
					break;
			default:
				break;
			}
        	if (enc) break;
        }
    }

}
