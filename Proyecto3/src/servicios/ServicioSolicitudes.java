package servicios;

import java.util.List;

import modelo.Evento;
import modelo.Venue;
import persistencias.EventoRepositorio;
import persistencias.SolicitudRepositorio;
import solicitudes.SolicitudCancelacionEvento;
import solicitudes.SolicitudReembolso;
import solicitudes.SolicitudVenue;
import tiquetesCompra.Tiquete;
import usuarios.Administrador;
import usuarios.ClienteNatural;
import usuarios.Organizador;

public class ServicioSolicitudes {

    private SolicitudRepositorio solicitudRepo;
    private ServicioCompras servicioCompras;
    private ServicioOrganizador servicioOrganizador;
    private EventoRepositorio eventosRepo;

    public ServicioSolicitudes(SolicitudRepositorio solicitudRepo,ServicioCompras servicioCompras,ServicioOrganizador servicioOrganizador,EventoRepositorio eventosRepo) {
        this.solicitudRepo = solicitudRepo;
        this.servicioCompras = servicioCompras;
        this.servicioOrganizador = servicioOrganizador;
        this.eventosRepo = eventosRepo;
    }

    /*
     * Crea una solicitud de reembolso para un tiquete de un cliente.
     * 
     */
    public void crearSolicitudReembolso(ClienteNatural cliente, Tiquete tiquete, String motivo) {
        if (!cliente.getMisTiquetes().contains(tiquete)) {
            throw new IllegalArgumentException("El tiquete no pertenece al cliente.");
        }
        SolicitudReembolso solicitud = new SolicitudReembolso(cliente, tiquete, motivo);
        solicitudRepo.agregarSolicitudReembolso(solicitud);
    }

    /*
     * Crea una solicitud de Venue a partir de los datos del venue propuesto.
     * 
     */
    public void crearSolicitudVenue(Organizador organizador, Venue venuePropuesto) {
        SolicitudVenue solicitud = new SolicitudVenue(venuePropuesto.getDireccion(),venuePropuesto.getCapacidadMax(),venuePropuesto.getNombre(), organizador);
        solicitudRepo.agregarSolicitudVenue(solicitud);
    }

    /*
     * Crea una solicitud de cancelación para un evento.
     */
    public void crearSolicitudCancelacionEvento(Organizador organizador, Evento evento, String motivo) {
        if (!evento.getOrganizador().equals(organizador)) {
            throw new IllegalArgumentException("El organizador no es dueño de este evento.");
        }

        SolicitudCancelacionEvento solicitud = new SolicitudCancelacionEvento(organizador, evento, motivo);
        solicitudRepo.agregarSolicitudCancelacionEvento(solicitud);
    }

    /*
     * El admin aprueba un reembolso iniciado por el cliente.
     */
    public void aprobarReembolso(Administrador admin, SolicitudReembolso solicitud) {
        if (!"PENDIENTE".equals(solicitud.getEstadoReembolso())) {
            throw new IllegalStateException("La solicitud ya fue procesada.");
        }
        solicitud.setEstadoReembolso("APROBADA");
        servicioCompras.procesarReembolso(solicitud);
        solicitudRepo.actualizarSolicitudReembolso(solicitud);
    }

    /*
     * El admin rechaza un reembolso.
     */
    public void rechazarReembolso(Administrador admin, SolicitudReembolso solicitud, String motivo) {

        if (!"EN ESPERA".equals(solicitud.getEstadoReembolso())) {
            throw new IllegalStateException("La solicitud ya fue procesada.");
        }

        solicitud.setEstadoReembolso("RECHAZADA");
        solicitudRepo.actualizarSolicitudReembolso(solicitud);
    }

    /*
     * El admin aprueba una solicitud de venue:
     * 1. Marca la solicitud como aprobada.
     * 2. Crea el Venue definitivo en el sistema.
     */
    public void aprobarVenue(Administrador admin, SolicitudVenue solicitud) {

        solicitud.setEstadoSolicitud("APROBADA");

        Venue nuevoVenue = new Venue(solicitud.getNombreVenue(), solicitud.getCapacidadMaxima(), solicitud.getUbicacion());
        servicioOrganizador.registrarVenue(nuevoVenue);
        solicitudRepo.actualizarSolicitudVenue(solicitud);
    }

    /*
     * El admin rechaza un venue propuesto.
     */
    public void rechazarVenue(Administrador admin, SolicitudVenue solicitud, String motivo) {

        if (!"EN ESPERA".equals(solicitud.getEstadoSolicitud())) {
            throw new IllegalStateException("La solicitud de venue ya fue procesada.");
        }
        solicitud.setEstadoSolicitud("RECHAZADA");
        solicitudRepo.actualizarSolicitudVenue(solicitud);
    }

    /*
     * El admin aprueba la cancelación de un evento.
     */
    public void aprobarCancelacionEvento(Administrador admin, SolicitudCancelacionEvento solicitud) {

        if (!"EN ESPERA".equals(solicitud.getEstado())) {
            throw new IllegalStateException("La solicitud de cancelación ya fue procesada.");
        }

        Evento evento = solicitud.getEventoCancelar();
        evento.setEstado("CANCELADO");
        eventosRepo.actualizar(evento);
        solicitud.setEstado("APROBADA");
        solicitudRepo.actualizarSolicitudCancelacionEvento(solicitud);
    }

    /*
     * El admin rechaza la cancelación del evento.
     */
    public void rechazarCancelacionEvento(Administrador admin, SolicitudCancelacionEvento solicitud, String motivo) {

        if (!"EN ESPERA".equals(solicitud.getEstado())) {
            throw new IllegalStateException("La solicitud de cancelación ya fue procesada.");
        }

        solicitud.setEstado("RECHAZADA");
        solicitudRepo.actualizarSolicitudCancelacionEvento(solicitud);
    }

    /*
     * Devuelve todos los reembolsos pendientes.
     * 
     */
    public List<SolicitudReembolso> obtenerReembolsosPendientes() {
        return solicitudRepo.getSolicitudesReembolsoPendientes();
    }

    public List<SolicitudVenue> obtenerVenuesPendientes() {
        return solicitudRepo.getSolicitudesVenuePendientes();
    }

    public List<SolicitudCancelacionEvento> obtenerEventosPendientes() {
        return solicitudRepo.getSolicitudesCancelacionPendientes();
    }
}