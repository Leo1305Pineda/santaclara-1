package santaclara.dbPostgresql.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import santaclara.controlador.ContGeneral;
import santaclara.controlador.ContPrincipal;
import santaclara.controlador.IContGeneral;
import santaclara.dbPostgresql.vista.PostgreSqlUI;


public class ContPostgreSql extends ContGeneral implements IContGeneral {

	private PostgreSqlUI vista;
	
	public ContPostgreSql(ContPrincipal contPrincipal) throws Exception {
		// TODO Auto-generated constructor stub
		setContPrincipal(contPrincipal);
		vista = new PostgreSqlUI(this);
		dibujar(vista, this);
		mostrarConfPostgreSql();
	}

	@Override
	public JPanel getVista() {
		// TODO Auto-generated method stub
		return null;
	}

	public ActionListener Atras(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ActivarAtras(null);
			}
		};
	}
	
	public ActionListener salir(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				quitarVista();
			}
		};
		
	}
	
	public ActionListener guardar(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					GuardarConfPostgreSql();
					
					activarConexiondb(vista);
					
					JOptionPane.showMessageDialog(vista,"OK");
		
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
	}
	
	public void GuardarConfPostgreSql()
	{
		try {
		
					/*public void runAnimacion(String folder,Integer tiempo,String msg){
					
					JLabel lblImagen = new JLabel();
					vista.setLayout(new BorderLayout());
					vista.add(lblImagen,BorderLayout.SOUTH);
					vista.repaint();
					
					List<Icon> imagenes = new ArrayList<Icon>();
					try {
						imagenes = getContPrincipal().getImagenes("img/animados/".concat(folder).concat("/"));
				
							for(Icon icon : imagenes){
							
								lblImagen.setText(msg);
								lblImagen.setIcon(icon);
							
								getContPrincipal().hilo1.sleep(100);
							}
						} catch (Exception e) {
						// TODO: handle exception
					}
				}
			*/
			FileWriter fw = new FileWriter("archivos/confPostgreSQL.txt");		
			fw.append("user:"+vista.getTxtUser().getText().trim()+"\n");
			fw.append("password:"+vista.getTxtPassword().getText().trim()+"\n");
			fw.append("url:"+vista.getTxtUrl().getText().trim()+"\n");
			fw.append("className:"+vista.getTxtClassName().getText().trim()+"\n");
			
			fw.close();
			quitarVista();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void mostrarConfPostgreSql() throws Exception{
		Scanner scaner = new Scanner(new File("archivos/confPostgreSQL.txt"));
			if(scaner.hasNext())
			{	
				vista.getTxtUser().setText(scaner.skip("user:").nextLine().trim());
				vista.getTxtPassword().setText(scaner.skip("password:").nextLine().trim());
				String url = new String(scaner.skip("url:").nextLine().trim());
				vista.getTxtUrl().setText(url);
				vista.getTxtClassName().setText(scaner.skip("className:").nextLine().trim());
				
				mostrarDatoAdicional(url);	
			}
			scaner.close();
	}
	
	@SuppressWarnings("resource")
	public void mostrarDatoAdicional(String url){
		vista.getTxtUbicacionDB().setText("");
		vista.getTxtPuerto().setText("");
		vista.getTxtNombreDB().setText("");
		
		Scanner scUrl = new Scanner(url).useDelimiter("/");
		
		if(scUrl.hasNext())
		{
			scUrl.next();
			
			scUrl.next();
			
			String ubicacionPuerto = new String(scUrl.next());
			
			Scanner scUbicacionPuerto = new Scanner(ubicacionPuerto).useDelimiter(":");
			
			if(scUbicacionPuerto.hasNext()){
				vista.getTxtUbicacionDB().setText(scUbicacionPuerto.next());
				if(scUbicacionPuerto.hasNext())	vista.getTxtPuerto().setText(scUbicacionPuerto.next());
			}
			else	vista.getTxtUbicacionDB().setText(ubicacionPuerto);
						
			vista.getTxtNombreDB().setText(scUrl.next());
			scUbicacionPuerto.close();
		}
		scUrl.close();
	}
	
	public KeyAdapter activarCambioTxtUrl(){
		
		return new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				
				mostrarDatoAdicional(vista.getTxtUrl().getText());
				
			};
		};
	}
	
	public ActionListener Abrir(final String cmd){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ejecutarComando(cmd);
			}
		};
	}

	}
