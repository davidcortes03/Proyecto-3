package marketPlace;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import tiquetesCompra.Tiquete;
import usuarios.ClienteNatural;

public class Oferta {

	private double precioOferta;
	private LocalDateTime fechaHora;
	private Tiquete tiquete;
	private ClienteNatural vendedor;
	private String id;
	private String estadoOf; // oferta "ACTIVA", "CANCELADA" o "COMPRADA".
	private List<ContraOferta> contraOfertas;
	
	public Oferta(double precioOferta, Tiquete tiquete, ClienteNatural vendedor) {
		this.precioOferta = precioOferta;
		this.fechaHora = LocalDateTime.now();
		this.tiquete = tiquete;
		this.vendedor = vendedor;
		this.id  = generarId();
		this.estadoOf = "ACTIVA";
		this.contraOfertas = new ArrayList<>();
	}

	public ClienteNatural getVendedor() {
		return vendedor;
	}
	
	public double getPrecioOferta() {
		return precioOferta;
	}

	public void setPrecioOferta(double precioNuevo) {
		this.precioOferta = precioNuevo;
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}
	
	public Tiquete getTiquete() {
		return tiquete;
	}

	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}
	
	public String getId() {
		return id;
	}
	
	public String getEstadoOf() {
		return estadoOf;
	}
	
	public void setEstadoOf(String estadoOf) {
		this.estadoOf = estadoOf;
	}
	
	public List<ContraOferta> getContraOfertas() {
		return contraOfertas;
	}
	
	public String generarId() {
		return UUID.randomUUID().toString();
	}
	 
}
