package solicitudes;

import modelo.Evento;
import usuarios.Organizador;

public class SolicitudCancelacionEvento {
	
	private Organizador organizador;
	private Evento eventoCancelar;
	private String estado; //APROBADO, RECHAZADO O EN ESPERA.
	private String motivo;
	
	
	
	public SolicitudCancelacionEvento(Organizador organizador, Evento evento, String motivo) {
		this.organizador = organizador;
		this.estado = "EN ESPERA";
		this.eventoCancelar = evento;
		this.motivo = motivo;
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
	
}
