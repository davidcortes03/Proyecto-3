package modelo;

public class Asiento {
	
	private int numero;
	private String estado; //OCUPADO O DESOCUPADO
	

	public Asiento(int numero) {
		
		this.numero = numero;
		this.estado = "DESOCUPADO";
		
	}

	public int getNumero() {
		return numero;
	}


	public void setNumero(int numero) {
		this.numero = numero;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
}
