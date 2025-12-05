package controladores;

import java.time.LocalDateTime;
import java.util.List;

import modelo.Evento;
import modelo.Localidad;
import modelo.Venue;
import servicios.ServicioOrganizador;
import servicios.ServicioReportes;
import servicios.ServicioSolicitudes;
import usuarios.Organizador;

public class OrganizadorController {  // ojo al nombre exacto que tengas: OrganizadorController

    private ServicioOrganizador servicioOrganizador;
    private ServicioSolicitudes servicioSolicitudes;
    private ServicioReportes servicioReportes;

    public OrganizadorController(ServicioOrganizador servicioOrganizador,
                                 ServicioSolicitudes servicioSolicitudes,
                                 ServicioReportes servicioReportes) {
        this.servicioOrganizador = servicioOrganizador;
        this.servicioSolicitudes = servicioSolicitudes;
        this.servicioReportes = servicioReportes;
    }

    /**
     * Crea un evento para el organizador.
     */
    public Evento crearEvento(Organizador organizador,
                              String nombre,
                              String tipoEvento,
                              LocalDateTime fecha,
                              Venue venue) {
        // TODO: usar servicioOrganizador.crearEvento(...)
        return null;
    }

    /**
     * Crea y agrega una localidad a un evento.
     */
    public Localidad crearYAgregarLocalidad(Evento evento,
                                            String nombre,
                                            int capacidad,
                                            boolean conAsientos,
                                            double precioLoc) {
        // TODO:
        // Localidad loc = servicioOrganizador.crearLocalidad(...);
        // servicioOrganizador.agregarLocalidad(evento, loc);
        // return loc;
        return null;
    }

    /**
     * Devuelve los eventos del organizador.
     */
    public List<Evento> obtenerEventosDeOrganizador(Organizador organizador) {
        // TODO: usar servicioOrganizador.obtenerEventosDeOrganizador(...)
        return null;
    }

    /**
     * Solicita un nuevo venue para el organizador.
     */
    public void solicitarNuevoVenue(Organizador organizador, Venue venuePropuesto) {
        // TODO: servicioSolicitudes.crearSolicitudVenue(organizador, venuePropuesto);
    }

    /**
     * Solicitud de cancelación de un evento.
     */
    public void solicitarCancelacionEvento(Organizador organizador, Evento evento, String motivo) {
        // TODO: servicioSolicitudes.crearSolicitudCancelacionEvento(...)
    }

    /**
     * Consulta ventas de un evento.
     */
    public double consultarVentasEvento(Evento evento) {
        // TODO: servicioReportes.calcularVentasPorEvento(evento);
        return 0.0;
    }

    /**
     * Consulta cantidad de tiquetes vendidos para un evento.
     */
    public int consultarCantidadTiquetesVendidos(Evento evento) {
        // TODO: servicioReportes.contarTiquetesVendidosPorEvento(evento);
        return 0;
    }
}
