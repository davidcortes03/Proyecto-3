package modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Venue {
	
	private String nombre;
	private int capacidadMax;
	private String direccion;
	private List<Localidad> localidades;
	private List<Evento> eventosAsociados;

	
	public Venue(String nombre, int capacidadMax, String direccion) {
		
		this.nombre = nombre;
		this.capacidadMax = capacidadMax;
		this.direccion = direccion;
		this.localidades = new ArrayList<>();
		this.eventosAsociados = new ArrayList<>();
		
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public int getCapacidadMax() {
		return capacidadMax;
	}


	public void setCapacidadMax(int capacidadMax) {
		this.capacidadMax = capacidadMax;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public List<Localidad> getLocalidades() {
		return localidades;
	}


	public void setLocalidades(List<Localidad> localidades) {
		this.localidades = localidades;
	}


	public List<Evento> getEventosAsociados() {
		return eventosAsociados;
	}


	public void setEventosAsociados(List<Evento> eventosAsociados) {
		this.eventosAsociados = eventosAsociados;
	}
	
	/**
	 * Verifica si un venue está disponible para una nueva fecha y hora,
	 * comprobando que no haya otro evento programado en el mismo lugar y momento.
	 *
	 * @param fechaPropuesta Fecha y hora que se quiere reservar el venue.
	 * @return true si el venue está disponible; false si ya está ocupado.
	 */
    public boolean validarDisponibilidadFecha(LocalDateTime fechaPropuesta) {
    		boolean respuesta = true;
    		for(Evento e: eventosAsociados) {
    			if(e.getFechaHora() == fechaPropuesta) {
    				respuesta = false;
    			}
    		}
    		return respuesta; 	
    }
	
}

