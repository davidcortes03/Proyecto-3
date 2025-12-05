package solicitudes;

import tiquetesCompra.Tiquete;
import usuarios.*;

public class SolicitudReembolso {
	
	private ClienteNatural cliente;
	private Tiquete tiqueteReembolso;
	private String estadoReembolso;
	
	public SolicitudReembolso(ClienteNatural cliente, Tiquete tiqueteReembolso, String motivo) {
		this.cliente = cliente;
		this.tiqueteReembolso = tiqueteReembolso;
		this.estadoReembolso = "EN ESPERA";
	}
	
	
	public String getEstadoReembolso() {
		return estadoReembolso;
	}
	
	public void setEstadoReembolso(String cambio) {
		this.estadoReembolso = cambio;	
	}
	
	public ClienteNatural getCliente() {
		return cliente;
	}


	public void setCliente(ClienteNatural cliente) {
		this.cliente = cliente;
	}


	public Tiquete getTiqueteReembolso() {
		return tiqueteReembolso;
	}



	public void setTiqeteReembolso(Tiquete nuevoTiquete) {
		this.tiqueteReembolso = nuevoTiquete;
	}

	
	
}
