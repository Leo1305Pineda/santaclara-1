package santaclara.thread.animacion;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Animado extends Thread{
	private Thread hilo;
	private String folder;
	private Integer time;
	private Boolean estado;
	
	private JLabel lblimagen;
	
	public Animado(String folder,JLabel lblimagen, Integer time) {
		this.folder = folder;
		this.time = time;
		this.lblimagen = lblimagen;
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
	
	public void run(){
				
		List<Icon> imagenes = new ArrayList<Icon>();
		try {
			imagenes = getImagenes("img/animados/".concat(folder).concat("/"));
	
			while (estado)
			{
				for(Icon icon : imagenes){
					
					lblimagen.setIcon(icon);
			//		System.out.println(folder);
					if(estado)Thread.sleep(time);
					else break;
				}
			}
			} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void star(){
		if(hilo==null)
		{
			hilo = new Thread(this);
			hilo.start();
			estado = true;
		}
	}
	
	@SuppressWarnings("deprecation")
	public void detener()
	{
		estado = false;
		hilo.stop();
	}
	
	public JLabel getImagen(){
		return lblimagen;
	}
	
}