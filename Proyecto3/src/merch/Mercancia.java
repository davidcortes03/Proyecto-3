package merch;

public abstract class Mercancia {
	
	protected String marca;
	
	public Mercancia(String marca) {
		this.marca = marca;
	}
	
	public String getMarca() {
		return marca;
	}
}
