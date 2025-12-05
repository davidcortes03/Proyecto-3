package marketPlace;

import java.time.LocalDateTime;
import java.util.UUID;

import usuarios.ClienteNatural;

public class ContraOferta {
	
	private ClienteNatural comprador;
	private double precioNuevo;
	private LocalDateTime fechaHora;
	private String id;
	private String estadoCo; // Contraoferta "ACTIVA", "CANCELADA", "ACEPTADA" o "RECHAZADA".
	private Oferta oferta;
	private String metodoDePago;
	
	public ContraOferta(double precioNuevo, ClienteNatural comprador, Oferta oferta, String metodoDePago) {
		this.comprador = comprador;
		this.precioNuevo = precioNuevo;
		this.id = generarId();
		this.fechaHora = LocalDateTime.now();
		this.estadoCo = "ACTIVA";
		this.oferta = oferta; 
		this.metodoDePago = metodoDePago;
	}

	public ClienteNatural getComprador() {
		return comprador;
	}
	
	public String getMetodoDePago() {
		return metodoDePago;
	}

	public double getPrecioNuevo() {
		return precioNuevo;
	}
	
	public LocalDateTime getFechaHora() {
		return fechaHora;
	}


	public void setPrecioNuevo(double precioNuevo) {
		this.precioNuevo = precioNuevo;
	}

	public Oferta getOferta() {
		return oferta;
	}
	
	public String getEstadoCo() {
		return estadoCo;
	}
	
	public void setEstadoCo(String estadoCo) {
		this.estadoCo = estadoCo;
	}
	
	public String getId() {
		return id;
	}

	public String generarId() {
		return UUID.randomUUID().toString();
	}
		
}
