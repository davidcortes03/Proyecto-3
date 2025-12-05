package merch;

public class Camiseta extends Mercancia{
	
	private String talla;
	
	
	public Camiseta(String marca, String talla) {
		super(marca);
		this.talla = talla;
		
	}
	
	@Override
	public String getMarca() {
		return marca;
		
	}
	
	public String getColor() {
		return talla;
	}

}

