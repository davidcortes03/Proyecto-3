package tiquetesCompra;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import merch.Mercancia;

public class PaqueteDeluxe implements Item{
	
	private String nombrePaquete;
	private double precioBase;
	private Tiquete tiquete;
	private List<Tiquete> cortesias;
	private List<Mercancia> mercancias;
	private String id;
	private String estadoCompra;

	public PaqueteDeluxe(String nombrePaquete, Tiquete tiquete, double precioBase) {
		
		this.nombrePaquete = nombrePaquete;
		this.tiquete = tiquete;
		this.cortesias = new ArrayList<>();
		this.mercancias = new ArrayList<>();
		this.id = generarId();
		this.estadoCompra = "NO COMPRADO";
	}
	
	
	
	
	public String getNombrePaquete() {
		return nombrePaquete;
	}




	public void setNombrePaquete(String nombrePaquete) {
		this.nombrePaquete = nombrePaquete;
	}




	public Tiquete getTiquete() {
		return tiquete;
	}




	public void setTiquete(Tiquete tiquete) {
		this.tiquete = tiquete;
	}




	public List<Tiquete> getCortesias() {
		return cortesias;
	}




	public void setCortesias(List<Tiquete> cortesias) {
		this.cortesias = cortesias;
	}




	public List<Mercancia> getMercancias() {
		return mercancias;
	}




	public void setMercancias(List<Mercancia> mercancias) {
		this.mercancias = mercancias;
	}




	public String getId() {
		return id;
	}




	public void setId(String id) {
		this.id = id;
	}




	public String getEstadoCompra() {
		return estadoCompra;
	}



	@Override
	public void setEstadoCompra(String estadoCompra) {
		this.estadoCompra = estadoCompra;
	}
	
	
	
	
	public double getPrecioBase() {
		return precioBase;
	}




	public void setPrecioBase(double precioBase) {
		this.precioBase = precioBase;
	}
	





	public List<Tiquete> getTiquetes() {
		List<Tiquete> tiquetes = new ArrayList<>();
		tiquetes.add(tiquete);
		for(Tiquete t: cortesias) {
			tiquetes.add(t);
		}
		return tiquetes;
	}
	

    
    
	/**
	 * 
	 * Genera un id unico para asignarle al paquete generado
	 * @return Id unico
	 */
	
	public String generarId() {
		return UUID.randomUUID().toString();
	}
	
}
