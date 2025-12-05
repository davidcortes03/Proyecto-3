package solicitudes;


import usuarios.Organizador;

public class SolicitudVenue {

	private String ubicacion;
	private int capacidadMaxima;
	private String nombreVenue;
	private String estadoSolicitud; //APROBADA, RECHAZADA O EN ESPERA. 
	private Organizador organizador;
	
	public SolicitudVenue (String ubicacion, int capacidadMaxima, String nombreVenue, Organizador organizador) {
		this.ubicacion = ubicacion;
		this.capacidadMaxima = capacidadMaxima;
		this.nombreVenue = nombreVenue;
		this.estadoSolicitud = "EN ESPERA";	
		this.organizador = organizador;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public Organizador getOrg() {
		return organizador;
	}

	public void setOrg(Organizador org) {
		this.organizador = org;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public int getCapacidadMaxima() {
		return capacidadMaxima;
	}

	public void setCapacidadMaxima(int capacidadMaxima) {
		this.capacidadMaxima = capacidadMaxima;
	}

	public String getNombreVenue() {
		return nombreVenue;
	}

	public void setNombreVenue(String nombreVenue) {
		this.nombreVenue = nombreVenue;
	}

	public String getEstadoSolicitud() {
		return estadoSolicitud;
	}

	public void setEstadoSolicitud(String estadoSolicitud) {
		this.estadoSolicitud = estadoSolicitud;
	}

	
	
}
