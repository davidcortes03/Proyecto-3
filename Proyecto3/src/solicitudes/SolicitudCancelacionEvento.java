package solicitudes;

import java.util.UUID;

import modelo.Evento;
import usuarios.Organizador;

public class SolicitudCancelacionEvento {
	
	private Organizador organizador;
	private Evento eventoCancelar;
	private String estado; //APROBADO, RECHAZADO O EN ESPERA.
	private String motivo;
	private String id;
	
	
	
	public SolicitudCancelacionEvento(Organizador organizador, Evento evento, String motivo, String id) {
		this.organizador = organizador;
		this.estado = "EN ESPERA";
		this.eventoCancelar = evento;
		this.motivo = motivo;
		this.id = id;
	}



	public String getMotivo() {
		return motivo;
	}
	
	
	public Organizador getOrganizador() {
		return organizador;
	}



	public void setOrganizador(Organizador organizador) {
		this.organizador = organizador;
	}



	public Evento getEventoCancelar() {
		return eventoCancelar;
	}



	public void setEventoCancelar(Evento eventoCancelar) {
		this.eventoCancelar = eventoCancelar;
	}


	public String getEstado() {
		return estado;
	}



	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String generarId() {
		return UUID.randomUUID().toString();
	}
	
	public String getId() {
		return id;
	}
	
}

