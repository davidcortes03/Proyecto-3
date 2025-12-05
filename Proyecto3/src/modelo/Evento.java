package modelo;

import java.time.LocalDateTime;

import usuarios.Organizador;

public class Evento {
	
	private String nombre;
	private Venue venue;
	private String tipoEvento;
	private LocalDateTime fechaHora;
	private String estado;
	private Organizador organizador;

	
	public Evento(String nombre, Venue venue, LocalDateTime fechaHora, String tipoEvento, Organizador organizador) {
		
		this.nombre = nombre;
		this.fechaHora = fechaHora;;
		this.tipoEvento = tipoEvento;
		this.venue = venue;
		this.organizador = organizador;
		this.estado = "ACTIVO";
		
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Venue getVenue() {
		return venue;
	}


	public void setVenue(Venue venue) {
		this.venue = venue;
	}


	public String getTipoEvento() {
		return tipoEvento;
	}


	public void setTipoEvento(String tipoEvento) {
		this.tipoEvento = tipoEvento;
	}


	public LocalDateTime getFechaHora() {
		return fechaHora;
	}


	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public Organizador getOrganizador() {
		return organizador;
	}


	public void setOrganizador(Organizador organizador) {
		this.organizador = organizador;
	}
	
	
	
	
	
}
