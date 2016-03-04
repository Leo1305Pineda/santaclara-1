/*Seccion 6
 * Gipsis Marin 19.828.553
 *Leonardo Pineda 19.727.835
 *Rhonal Chirinos 19.827.297
 *Joan Puerta 19.323.522
 *Vilfer Alvarez 18.735.720
 */

package santaclara.modelo;

public class DomicilioComercio extends Cliente {
	
	private String tipo;
	private Integer diaVisita;

	public DomicilioComercio() {
		super();
		// TODO Auto-generated constructor stub
	}
 
	public DomicilioComercio(Cliente cliente, String tipo,
			Integer diaVisita) {
		super(cliente.getId(), cliente.getRif(), 
				cliente.getRazonsocial(), cliente.getDireccion(), 
				cliente.getTelefono(), cliente.getRuta());
		this.tipo = tipo;
		this.diaVisita = diaVisita;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getDiaVisita() {
		return diaVisita;
	}

	public void setDiaVisita(Integer diaVisita) {
		this.diaVisita = diaVisita;
	}
}
