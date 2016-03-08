/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.controlador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Stack;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import santaclara.modelo.Almacen;
import santaclara.modelo.Capacidad;
import santaclara.modelo.Presentacion;
import santaclara.modelo.Producto;
import santaclara.modelo.Sabor;
import santaclara.modelo.Usuario;
import santaclara.modelo.Zona;

public class ContGeneral extends StackControlador {
	
	private ContPrincipal 	 contPrincipal;
	private Usuario usuario = new Usuario(); 
	
	public ContGeneral() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * Metodo para añadir un Controlador al ControladorGeneral
	 * @param IContGeneral Object que implementa esta interfaz 
	 * */
	public void addControlador(IContGeneral controlador){
		super.addControlador(controlador);
	}
	
	/*
	 * Metodo para remover un Controlador del ControladorGeneral 
	 * @param IContGeneral Object que implementa esta interfaz 
	 * */
	public void removeControlador(IContGeneral controlador){
		super.removeControlador(controlador);
	}
	
	public void dibujar(JPanel vista,Object cacheobject)
	{	
		contPrincipal.agregarPanel(vista);
		//addControlador
		if(!this.contPrincipal.getCacheObjet().empty())
		{
			Object object = this.contPrincipal.getCacheObjet().pop();
			if (object.equals(cacheobject))
			{
				this.contPrincipal.getCacheObjet().push(object);
			}
			else
			{
				this.contPrincipal.getCacheObjet().push(object);
				this.contPrincipal.getCacheObjet().push(cacheobject);
			}
		}
		else 
			this.contPrincipal.getCacheObjet().push(cacheobject);
		//fin
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
	
	public void ejecutarComando(String comando){
		try{
			Runtime runtime = Runtime.getRuntime();
			Process process;

			process = runtime.exec(comando);
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
	
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
		}
	
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}

	}
	
	public void ActivarAtras(Object object) {//btnAtras
		
		if(this.contPrincipal.getCacheObjet().size()>1)
		{
			this.contPrincipal.ActivarAtras(object);
		}
		else
		{
			quitarVista();
		}
	}

	public ContPrincipal getContPrincipal() {
		return contPrincipal;
	}

	public void setContPrincipal(ContPrincipal contPrincipal) {
		this.contPrincipal = contPrincipal;
	}	
	
	public Stack<Object> getCacheObjet() {
		return this.contPrincipal.getCacheObjet();
	}

	public void setCacheObjet(Stack<Object> cacheObject) {
		this.contPrincipal.setCacheObjet(cacheObject);
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
		
	public void dibujarImagen(JPanel panel,JLabel lblImagen,Object ubicacion){
		if(ubicacion.equals("")) panel.add(lblImagen);
		else panel.add(lblImagen,ubicacion);
		
		contPrincipal.getVista().getFrame().repaint();
	}
	
	public void quitarImagen(JLabel lblImagen){
		
		contPrincipal.getVista().getFrame().remove(lblImagen);
		contPrincipal.getVista().getFrame().repaint();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}	
}
