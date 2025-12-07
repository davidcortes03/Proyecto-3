package persistencias;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import compras.Transaccion;
import marketPlace.Oferta;
import modelo.Evento;
import modelo.Venue;
import solicitudes.SolicitudCancelacionEvento;
import solicitudes.SolicitudReembolso;
import solicitudes.SolicitudVenue;
import tiquetesCompra.Tiquete;
import usuarios.Administrador;
import usuarios.Cliente;
import usuarios.ClienteNatural;
import usuarios.Organizador;

public class SistemaBoletaMaster implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Evento> repoEventos = new ArrayList<>();
	private List<Cliente> repoUsuarios = new ArrayList<>();
	private List<Venue> repoVenues = new ArrayList<>();
	private List<Oferta> repoOfertas = new ArrayList<>();
	private List<SolicitudVenue> repoSolicitudesVenue = new ArrayList<>();
	private List<SolicitudReembolso> repoSolicitudesReembolso = new ArrayList<>();
	private List<SolicitudCancelacionEvento> solicitudesCancelacion = new ArrayList<>();
	private List<Tiquete> repoTiquetes = new ArrayList<>();
	private List<Transaccion> repoTransacciones = new ArrayList<>();
	
	
	//Metodos del evento
	public void actualizarEvento(Evento evento) {
		for(Evento e: repoEventos) {
			if(evento.getNombre() == e.getNombre()) {
				e = evento;
			}
		}
	}

	public void agregarEvento(Evento eventoCreado) {
		repoEventos.add(eventoCreado);
	}
	
	//Metodos de MarketPlace
	public void agregarOferta(Oferta oferta) {
		repoOfertas.add(oferta);
	}

	public void actualizarOferta(Oferta oferta) {
		for(Oferta of: repoOfertas) {
			if(of.getId() == oferta.getId()) {
				of = oferta;
			}
		}
	}
	
	public List<Evento> getRepoEventos() {
		return repoEventos;
	}

	//Metodos solicitudes
	public List<SolicitudVenue> getSolicitudesVenue() {
		return repoSolicitudesVenue;
	}

	public void agregarSolicitudReembolso(SolicitudReembolso solicitud) {
		repoSolicitudesReembolso.add(solicitud);
	}

	public void agregarSolicitudVenue(SolicitudVenue solicitud) {
		repoSolicitudesVenue.add(solicitud);
	}

	public void agregarSolicitudCancelacionEvento(SolicitudCancelacionEvento solicitud) {
		solicitudesCancelacion.add(solicitud);
	}

	public void actualizarSolicitudReembolso(SolicitudReembolso solicitud) {
		for(SolicitudReembolso re: repoSolicitudesReembolso) {
			if(re.getId() == solicitud.getId()) {
				re = solicitud;
			}
		}
	}

	public void actualizarSolicitudVenue(SolicitudVenue solicitud) {
		for(SolicitudVenue sol: repoSolicitudesVenue) {
			if(solicitud.getId() == sol.getId()) {
				sol = solicitud;
			}
		}
		
	}

	public void actualizarSolicitudCancelacionEvento(SolicitudCancelacionEvento solicitud) {
		for(SolicitudCancelacionEvento sol: solicitudesCancelacion) {
			if(sol.getId() == solicitud.getId()) {
				sol = solicitud;
			}
		}
	}

	public List<SolicitudReembolso> getSolicitudesReembolsoPendientes() {
		List<SolicitudReembolso> pendientes = new ArrayList<>();
		for(SolicitudReembolso sol: repoSolicitudesReembolso) {
			if(sol.getEstadoReembolso() == "EN ESPERA") {
				pendientes.add(sol);
			}
		}
		return pendientes;
	}

	public List<SolicitudVenue> getSolicitudesVenuePendientes() {
		List<SolicitudVenue> pendientes = new ArrayList<>();
		for(SolicitudVenue sol: repoSolicitudesVenue) {
			if(sol.getEstadoSolicitud() == "EN ESPERA") {
				pendientes.add(sol);
			}
		}
		return pendientes;
	}

	public List<SolicitudCancelacionEvento> getSolicitudesCancelacionPendientes() {
		List<SolicitudCancelacionEvento> pendientes = new ArrayList<>();
		for(SolicitudCancelacionEvento sol: solicitudesCancelacion) {
			if(sol.getEstado() == "EN ESPERA") {
				pendientes.add(sol);
			}
		}
		return pendientes;
	}
	
	//Metodos Tiquete
	public void agregarTiquete(Tiquete t) {
		repoTiquetes.add(t);
	}

	public void eliminarTiquete(Tiquete tiquete) {
		repoTiquetes.remove(tiquete);
	}

	public void actualizarTiquete(Tiquete tiquete) {
		for(Tiquete t: repoTiquetes) {
			if(t.getId() == tiquete.getId()) {
				t = tiquete;
			}
		}
	}
	
	public List<Tiquete> getTiquetes(){
		return repoTiquetes;
	}
	
	//Metodos Trnasacciones
	public void agregarTransaccion(Transaccion tran) {
		repoTransacciones.add(tran);
	}

	public List<Transaccion> getTransaccionesOfertas() {
		List<Transaccion> tranOfertas = new ArrayList<>();
		for(Transaccion tran: repoTransacciones) {
			if(tran.getOferta() != null) {
				tranOfertas.add(tran);
			}
		}
		return tranOfertas;
	}

	public List<Transaccion> getTransaccionesContraOfertas() {
		List<Transaccion> tranContraOfertas = new ArrayList<>();
		for(Transaccion coTran: repoTransacciones) {
			if(coTran.getContraOferta() != null) {
				tranContraOfertas.add(coTran);
			}
		}
		return tranContraOfertas;
	}
	
	public List<Transaccion> getTransacciones(){
		return repoTransacciones;
	}
	
	//Metodos Usuario
	public void actualizarCliente(Cliente cliente) {
		for(Cliente c: repoUsuarios) {
			if(c.getNombreUsuario() == cliente.getNombreUsuario()) {
				c = cliente;
			}
		}
	}

	public List<Cliente> getUsuarios() {
		return repoUsuarios;
	}

	public List<ClienteNatural> getUsuariosNaturales() {
		List<ClienteNatural> clientesNaturales = new ArrayList<>();
		for(Cliente c: repoUsuarios) {
			if(c instanceof ClienteNatural) {
				clientesNaturales.add((ClienteNatural) c);
			}
		}
		return clientesNaturales;
	}

	public void agregarUsuario(Cliente clienteNuevo) {
		repoUsuarios.add(clienteNuevo);
	}

	public List<Cliente> getOrganizadores() {
		List<Cliente> organizadores = new ArrayList<>();
		for(Cliente c: repoUsuarios) {
			if(c instanceof Organizador) {
				organizadores.add(c);
			}
		}
		return organizadores;
	}

	public List<Cliente> getAdministradores() {
		List<Cliente> administradores = new ArrayList<>();
		for(Cliente c: repoUsuarios) {
			if(c instanceof Administrador) {
				administradores.add(c);
			}
		}
		return administradores;
	}
	
	//Metodos venue
	public void agregarVenue(Venue nuevoVenue) {
		repoVenues.add(nuevoVenue);
	}

	public void actualizarVenue(Venue venue) {
		for(Venue v: repoVenues) {
			if(v.getNombre() == venue.getNombre()) {
				v = venue;
			}
		}	
	}
}
