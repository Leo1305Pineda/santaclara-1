package santaclara.factorymethod;

//Creador
public class FabricaConexionDataBase extends Fabrica {
	protected String tipo;

	public FabricaConexionDataBase(String tipo) {
		super();
		this.tipo = tipo;
	}

	public Conexion fabricarConexion(){
		if(tipo.equalsIgnoreCase("MySql")){
			return new ConexionMySql();
		}
		else if(tipo.equals("Oralcle")){
			
		}
		else if(tipo.equals("PostgreSql")){
			return new ConexionPostgreSql();
		}
		return null;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
