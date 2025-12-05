package tiquetesCompra;

import java.util.List;

public class TiqueteEntradaMultiple implements Item{
	
	private double precioBase;
	private List<Tiquete> entradas;
	private String estadoCompra;
	
	public TiqueteEntradaMultiple(List<Tiquete> entradas, double precioBase, double cargoPorcentual, double cargoImpresionEmision) {
		
		this.estadoCompra = "NO COMPRADO";
		this.entradas = entradas;
		
	}

    public List<Tiquete> getTiquetes() {
		return entradas;
    }

	public void setEntradas(List<Tiquete> entradas) {
		this.entradas = entradas;
	}

	public String getEstadoCompra() {
		return estadoCompra;
	}

	public void setEstadoCompra(String estadoCompra) {
		this.estadoCompra = estadoCompra;
	}
	
	
	public double getPrecioBase() {
		return precioBase;
	}
    
    


}
