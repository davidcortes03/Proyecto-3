package usuarios;

import java.util.ArrayList;
import java.util.List;

import solicitudes.SolicitudCancelacionEvento;
import solicitudes.SolicitudReembolso;
import solicitudes.SolicitudVenue;

public class Administrador extends Cliente {
	
	private double cargoPorcentual;
	private double cargoImpresionEmision;
	private List<SolicitudReembolso> reembolsosPendientes;
	private List<SolicitudVenue> venuesParaAprobar;
	private List<SolicitudCancelacionEvento> solicitudesCancelacionEventos;
	
	public Administrador(String nombreUsuario, String correo, String contrasena) {
		super(nombreUsuario, correo, contrasena);
		this.cargoPorcentual = 0.0;
		this.cargoImpresionEmision = 0.0;
		this.reembolsosPendientes = new ArrayList<>();
		this.venuesParaAprobar = new ArrayList<>();
		this.solicitudesCancelacionEventos = new ArrayList<>();
	}

	public double getCargoPorcentual() {
		return cargoPorcentual;
	}

	public void setCargoPorcentual(double cargoPorcentual) {
		this.cargoPorcentual = cargoPorcentual;
	}

	public double getCargoImpresionEmision() {
		return cargoImpresionEmision;
	}

	public void setCargoImpresionEmision(double cargoImpresionEmision) {
		this.cargoImpresionEmision = cargoImpresionEmision;
	}

	public List<SolicitudReembolso> getReembolsosPendientes() {
		return reembolsosPendientes;
	}

	public void setReembolsosPendientes(List<SolicitudReembolso> reembolsosPendientes) {
		this.reembolsosPendientes = reembolsosPendientes;
	}

	public List<SolicitudVenue> getVenuesParaAprobar() {
		return venuesParaAprobar;
	}

	public void setVenuesParaAprobar(List<SolicitudVenue> venuesParaAprobar) {
		this.venuesParaAprobar = venuesParaAprobar;
	}

	public List<SolicitudCancelacionEvento> getSolicitudesCancelacionEventos() {
		return solicitudesCancelacionEventos;
	}

	public void setSolicitudesCancelacionEventos(List<SolicitudCancelacionEvento> solicitudesCancelacionEventos) {
		this.solicitudesCancelacionEventos = solicitudesCancelacionEventos;
	}
	
	
}


