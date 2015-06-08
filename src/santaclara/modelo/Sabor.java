package santaclara.modelo;

public class Sabor {
	private Integer id;
	private String sabor;
	
	public Sabor(Integer id, String sabor) {
		super();
		this.id = id;
		this.sabor = sabor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSabor() {
		return sabor;
	}

	public void setSabor(String sabor) {
		this.sabor = sabor;
	}
	
	
	public Sabor() {
		// TODO Auto-generated constructor stub
	}

}
