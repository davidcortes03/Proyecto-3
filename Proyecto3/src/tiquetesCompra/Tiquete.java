package tiquetesCompra;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import modelo.Asiento;
import modelo.Evento;
import modelo.Localidad;

public class Tiquete implements Item {
	
	private double precioBase;
	private LocalDateTime fechaHora;
	private String id;
	private boolean esCortesia;
	private Localidad localidad;
	private Asiento asiento;
	private Evento evento;
	private boolean impreso;
	private boolean transferido;
	private String estadoCompra;
	private String estadoReventa;
	private double precioPagar;
	
	
	public Tiquete(double precioBase, boolean esCortesia, Evento evento, Localidad localidad, Asiento asiento) {
		this.precioBase = precioBase;
		this.fechaHora = LocalDateTime.now();
		this.id = generarId();
		this.esCortesia = esCortesia;
		this.evento = evento;
		this.localidad = localidad;
		this.asiento = asiento;
		this.impreso = false;
		this.transferido = false;
		this.estadoCompra = "NO COMPRADO";
		this.estadoReventa = "NO REVENTA";
		this.precioPagar = 0;
	}

	
	public double getPrecioPagar() {
		return precioPagar;
	}
	
	public void setPrecioPagar(double precioNuevo) {
		precioPagar = precioNuevo;
	}

	public double getPrecioBase() {
		return precioBase;
	}


	public void setPrecioBase(double precioBase) {
		this.precioBase = precioBase;
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}


	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public boolean isEsCortesia() {
		return esCortesia;
	}


	public void setEsCortesia(boolean esCortesia) {
		this.esCortesia = esCortesia;
	}


	public Localidad getLocalidad() {
		return localidad;
	}


	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}


	public Asiento getAsiento() {
		return asiento;
	}


	public void setAsiento(Asiento asiento) {
		this.asiento = asiento;
	}


	public Evento getEvento() {
		return evento;
	}


	public void setEvento(Evento evento) {
		this.evento = evento;
	}


	public boolean getTransferido() {
		return transferido;
	}


	public void setTransferido(boolean transferido) {
		this.transferido = transferido;
	}


	public String getEstadoCompra() {
		return estadoCompra;
	}


	public void setEstadoCompra(String estadoCompra) {
		this.estadoCompra = estadoCompra;
	}


	public String getEstadoReventa() {
		return estadoReventa;
	}


	public void setEstadoReventa(String estadoReventa) {
		this.estadoReventa = estadoReventa;
	}
	
	public boolean getImpreso() {
		return impreso;
	}
	
	public void setImpreso(boolean nuevo) {
		this.impreso = nuevo;
	}
	
	/**
	 * 
	 * Genera un id unico para asignarle al paquete generado
	 * @return Id unico
	 */
	
	public String generarId() {
		return UUID.randomUUID().toString();
	}
	
	public List<Tiquete> getTiquetes() {
		List<Tiquete> tiquetes = new ArrayList<>();
		tiquetes.add(this);
		return tiquetes;
	}
	
	
    

    
    
	
}


