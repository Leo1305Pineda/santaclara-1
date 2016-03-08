package santaclara.factorymethod;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Conexion {
	
	private Connection connection ;
	private Statement statement ;
	private ResultSet resultSet;
	
	public ResultSet getSelect(String sql) throws Exception {
		if(statement==null || statement.isClosed()) {
		//	statement.close();
			return null;
		}
		resultSet = statement.executeQuery(sql);
	//	connection.close();
		return resultSet;
	}
	
	public void ejecutar(String sql) throws Exception {
		statement.executeUpdate(sql);
		//statement.close();
		//connection.close();
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
