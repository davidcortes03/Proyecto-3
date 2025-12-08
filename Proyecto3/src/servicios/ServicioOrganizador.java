package servicios;

import java.time.LocalDateTime;
import java.util.List;

import modelo.Evento;
import modelo.Localidad;
import modelo.Venue;
import persistencias.EventoRepositorio;
import persistencias.UsuarioRepositorio;
import persistencias.VenueRepositorio;
import usuarios.Organizador;

public class ServicioOrganizador {
	
	private EventoRepositorio eventoRepo;
	private VenueRepositorio venueRepo;
	private UsuarioRepositorio usuarioRepo;

	public ServicioOrganizador(EventoRepositorio eventoRepo, VenueRepositorio venueRepo,
			UsuarioRepositorio usuarioRepo) {
		this.eventoRepo = eventoRepo;
		this.venueRepo = venueRepo;
		this.usuarioRepo = usuarioRepo;
	}
	
	/*
	 * Crea el evento.
	 */
	public Evento crearEvento(Organizador organizador, String nombre, String tipoEvento, LocalDateTime fecha, Venue venue) {
        boolean comprobante = true;
        Evento eventoCreado = new Evento(nombre, venue, fecha, tipoEvento, organizador);
        eventoCreado.setEstado("PENDIENTE");
        for (Evento e : venue.getEventosAsociados()) {
            if (e.getFechaHora().equals(fecha)) {
                comprobante = false;
            }
        }
        if (comprobante) {
            if (venue.getLocalidades().isEmpty()) {
                Localidad general = new Localidad("General", Math.min(venue.getCapacidadMax(), 50), true, 120.0);
                general.crearTodosLosAsientos();
                venue.getLocalidades().add(general);
            }
            venue.getEventosAsociados().add(eventoCreado);
            organizador.getMisEventos().add(eventoCreado);
            eventoRepo.agregar(eventoCreado);
            usuarioRepo.actualizar(organizador);
            venueRepo.actualizar(venue);
        }

        return eventoCreado;
    }
	
	/*
	 * Crea la localidad.
	 */
	public Localidad crearLocalidad(String nombre, int capacidad, boolean conAsientos, double precioLoc) {
		Localidad localidadCreada = new Localidad(nombre, capacidad, conAsientos, precioLoc);
		return localidadCreada;
	}
	
	/*
	 * Agrega una localidad a un evento.
	 */
    public void agregarLocalidad(Evento evento, Localidad localidad) {
    		int capacidadActual = 0;
    		for (Localidad loc : evento.getVenue().getLocalidades()) {
    			capacidadActual += loc.getCapacidad();
    		}
    		if(evento.getVenue().getCapacidadMax() > localidad.getCapacidad() &&
    				capacidadActual + localidad.getCapacidad() < evento.getVenue().getCapacidadMax()) {
    			if(localidad.isConAsientos() == true) {
    				localidad.crearTodosLosAsientos();
    			}
    			evento.getVenue().getLocalidades().add(localidad);
    			venueRepo.actualizar(evento.getVenue());
    			eventoRepo.actualizar(evento);
    		}
    }
    

    public List<Evento> obtenerEventosDeOrganizador(Organizador organizador) {
    		return organizador.getMisEventos();
    }

	public void registrarVenue(Venue nuevoVenue) {
		venueRepo.agregar(nuevoVenue);
	}
}
