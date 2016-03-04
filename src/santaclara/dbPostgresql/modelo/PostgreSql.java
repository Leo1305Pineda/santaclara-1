package santaclara.dbPostgresql.modelo;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PostgreSql {
	private Connection connection ;
	private Statement statement ;
	private ResultSet resultSet;
	
	public PostgreSql() throws Exception {
		super();
		// TODO Auto-generated constructor stub
		activarConexion();
	}

	public void activarConexion() throws Exception{
 			Scanner scaner = new Scanner(new File("archivos/confPostgreSQL.txt"));
 			if(scaner.hasNext())
 			{
 				String user = new String(scaner.skip("user:").nextLine().trim());
 				String password = new String(scaner.skip("password:").nextLine().trim());
 				String url = new String(scaner.skip("url:").nextLine().trim());
 				String className = new String(scaner.skip("className:").nextLine().trim());
 				
 				Class.forName(className);										//Clase Usada Para el Driver
 				connection = DriverManager.getConnection(url, user, password); 	//Conexion a la Base de Dato
 				statement = connection.createStatement();						//Trae los Datos
 			}
 			scaner.close();		
	}
	
	public ResultSet getSelect(String sql) throws SQLException {
		if(statement==null) return null;
		resultSet = statement.executeQuery(sql);
		connection.close();
		return resultSet;
	}
	
	public void ejecutar(String sql) throws SQLException {
		statement.executeUpdate(sql);
		statement.close();
		connection.close();
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public Statement getStatement() {
		return statement;
	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}

	public ResultSet getResultSet() {
		return resultSet;
	}

	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}
	
}
