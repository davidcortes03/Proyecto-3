package merch;

public class Termo extends Mercancia{
	
	private String tamanio;
	
	
	public Termo(String marca, String tamanio) {
		super(marca);
		this.tamanio = tamanio;
		
	}
	
	@Override
	public String getMarca() {
		return marca;
		
	}
	
	public String getColor() {
		return tamanio;
	}

}
