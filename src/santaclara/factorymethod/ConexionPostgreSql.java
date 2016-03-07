package santaclara.factorymethod;

import java.io.File;
import java.sql.DriverManager;
import java.util.Scanner;

public class ConexionPostgreSql extends Conexion{

	public ConexionPostgreSql() {
		super();
		// TODO Auto-generated constructor stub
		try {
			activarConexion();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
		
	public void activarConexion() throws Exception{
			
		Scanner scaner = new Scanner(new File("archivosConexionBaseDato/confPostgreSQL.txt"));
			if(scaner.hasNext())
			{
				String user = new String(scaner.skip("user:").nextLine().trim());
				String password = new String(scaner.skip("password:").nextLine().trim());
				String url = new String(scaner.skip("url:").nextLine().trim());
				String className = new String(scaner.skip("className:").nextLine().trim());
				Class.forName(className);										//Clase Usada Para el Driver
				setConnection(DriverManager.getConnection(url, user, password)); 	//Conexion a la Base de Dato
				setStatement(getConnection().createStatement());						//Trae los Datos
			}
			scaner.close();				
	}
		
}
