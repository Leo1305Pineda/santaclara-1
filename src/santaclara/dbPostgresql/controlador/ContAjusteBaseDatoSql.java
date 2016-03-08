package santaclara.dbPostgresql.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JPanel;

import santaclara.controlador.ContGeneral;
import santaclara.controlador.ContPrincipal;
import santaclara.controlador.IContGeneral;
import santaclara.dbPostgresql.vista.AjusteBaseDatoUI;

public class ContAjusteBaseDatoSql extends ContGeneral implements IContGeneral {

	private AjusteBaseDatoUI vista;
	String inicio;
	
	public ContAjusteBaseDatoSql(ContPrincipal contPrincipal) throws Exception {
		// TODO Auto-generated constructor stub
		inicio = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		setContPrincipal(contPrincipal);
		vista = new AjusteBaseDatoUI(this);
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
					GuardarConf();
		
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
	}
	
	public void GuardarConf() throws IOException
	{	
		FileWriter fw1 = new FileWriter("archivosConexionBaseDato/ultimaConexion.txt");		
		fw1.append("tipo:"+vista.getCmbTipoBaseDato().getSelectedItem()+"\n");
		fw1.close();
		
		if(vista.getCmbTipoBaseDato().getSelectedItem().equals("PostgreSql"))
		{	
			FileWriter fw = new FileWriter("archivosConexionBaseDato/confPostgreSQL.txt");		
			fw.append("user:"+vista.getTxtUser().getText().trim()+"\n");
			fw.append("password:"+vista.getTxtPassword().getText().trim()+"\n");
			fw.append("url:"+vista.getTxtUrl().getText().trim()+"\n");
			fw.append("className:"+vista.getTxtClassName().getText().trim()+"\n");			
			fw.close();
		}
		else if(vista.getCmbTipoBaseDato().getSelectedItem().equals("MySql"))
		{	
			FileWriter fw = new FileWriter("archivosConexionBaseDato/confMySQL.txt");		
			fw.append("user:"+vista.getTxtUser().getText().trim()+"\n");
			fw.append("password:"+vista.getTxtPassword().getText().trim()+"\n");
			fw.append("url:"+vista.getTxtUrl().getText().trim()+"\n");
			fw.append("className:"+vista.getTxtClassName().getText().trim()+"\n");			
			fw.close();
		}
		else if(vista.getCmbTipoBaseDato().getSelectedItem().equals("Oracle"))
		{	
			FileWriter fw = new FileWriter("archivosConexionBaseDato/confOracle.txt");		
			fw.append("user:"+vista.getTxtUser().getText().trim()+"\n");
			fw.append("password:"+vista.getTxtPassword().getText().trim()+"\n");
			fw.append("url:"+vista.getTxtUrl().getText().trim()+"\n");
			fw.append("className:"+vista.getTxtClassName().getText().trim()+"\n");			
			fw.close();
		}
	quitarVista();
	}

	public void mostrarConfPostgreSql() throws Exception{
		
		String tipoConexion = new String();
		Scanner sc = new Scanner(new File("archivosConexionBaseDato/ultimaConexion.txt"));
		if(sc.hasNext())
		{
			tipoConexion = sc.skip("tipo:").nextLine().trim();	
			vista.getCmbTipoBaseDato().setSelectedItem(tipoConexion);
		}
		sc.close();
			if(tipoConexion.equals("PostgreSql"))
			{
				Scanner scaner = new Scanner(new File("archivosConexionBaseDato/confPostgreSQL.txt"));
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
			else if(tipoConexion.equals("MySql"))
			{
				Scanner scaner = new Scanner(new File("archivosConexionBaseDato/confMySQL.txt"));
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
			else if(tipoConexion.equals("Oracle"))
			{
				Scanner scaner = new Scanner(new File("archivosConexionBaseDato/confOracle.txt"));
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
	@Override
	public String asociar() {
		// TODO Auto-generated method stub
		return inicio;
	}


	}
