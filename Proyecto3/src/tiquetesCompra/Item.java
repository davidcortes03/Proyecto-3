package tiquetesCompra;

import java.util.List;

public interface Item {
	
	public double getPrecioBase();
	public List<Tiquete> getTiquetes();
	public String getEstadoCompra();
	public void setEstadoCompra(String estadoCompra);
}
