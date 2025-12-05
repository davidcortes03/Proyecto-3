package merch;

public class Gorra extends Mercancia {
	
	private String color;
	
	
	public Gorra(String marca, String color) {
		super(marca);
		this.color = color;
		
	}
	
	@Override
	public String getMarca() {
		return marca;
		
	}
	
	public String getColor() {
		return color;
	}

}
