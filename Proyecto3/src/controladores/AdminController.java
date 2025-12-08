package controladores;

import java.util.List;

import modelo.Evento;
import persistencias.EventoRepositorio;
import servicios.ServicioAdmin;
import servicios.ServicioCompras;
import servicios.ServicioReportes;
import servicios.ServicioSolicitudes;
import solicitudes.SolicitudCancelacionEvento;
import solicitudes.SolicitudReembolso;
import solicitudes.SolicitudVenue;
import usuarios.Administrador;

public class AdminController {

    private ServicioAdmin servicioAdmin;         
    private ServicioCompras servicioCompras;      
    private ServicioSolicitudes servicioSolicitudes;
    private ServicioReportes servicioReportes;
    private EventoRepositorio eventoRepositorio;

    public AdminController(ServicioAdmin servicioAdmin,
                           ServicioCompras servicioCompras,
                           ServicioSolicitudes servicioSolicitudes,
                           ServicioReportes servicioReportes,
                           EventoRepositorio eventoRepositorio) {
        this.servicioAdmin = servicioAdmin;
        this.servicioCompras = servicioCompras;
        this.servicioSolicitudes = servicioSolicitudes;
        this.servicioReportes = servicioReportes;
        this.eventoRepositorio = eventoRepositorio;
    }

    /**
     * Configura cargos de servicio para las compras.
     */
    public void configurarCargos(double porcentajeServicio, double cuotaFija) {
    	servicioCompras.actualizarParametros(porcentajeServicio, cuotaFija);
        // TODO: servicioCompras.actualizarParametros(porcentajeServicio, cuotaFija);
        // o delegar a servicioAdmin si lo encapsulas allá
    }

    /**
     * Obtiene reembolsos pendientes.
     */
    public List<SolicitudReembolso> obtenerReembolsosPendientes() {
    	servicioSolicitudes.obtenerReembolsosPendientes();
        // TODO: servicioSolicitudes.obtenerReembolsosPendientes();
        return null;
    }

    public void aprobarReembolso(Administrador admin, SolicitudReembolso solicitud) {
    	servicioSolicitudes.aprobarReembolso(admin, solicitud);
        // TODO: servicioSolicitudes.aprobarReembolso(admin, solicitud);
    }

    public void rechazarReembolso(Administrador admin, SolicitudReembolso solicitud, String motivo) {
    	servicioSolicitudes.rechazarReembolso(admin, solicitud, motivo);
        // TODO: servicioSolicitudes.rechazarReembolso(admin, solicitud, motivo);
    }

    /**
     * Venues pendientes de aprobación.
     */
    public List<SolicitudVenue> obtenerVenuesPendientes() {
    	servicioSolicitudes.obtenerVenuesPendientes();
        // TODO: servicioSolicitudes.obtenerVenuesPendientes();
        return null;
    }

    public void aprobarVenue(Administrador admin, SolicitudVenue solicitud) {
    	servicioSolicitudes.aprobarVenue(admin, solicitud);
        // TODO: servicioSolicitudes.aprobarVenue(admin, solicitud);
    }

    public void rechazarVenue(Administrador admin, SolicitudVenue solicitud, String motivo) {
    	servicioSolicitudes.rechazarVenue(admin, solicitud, motivo);
        // TODO: servicioSolicitudes.rechazarVenue(admin, solicitud, motivo);
    }

    /**
     * Solicitudes de cancelación pendientes.
     */
    public List<SolicitudCancelacionEvento> obtenerSolicitudesCancelacionPendientes() {
    	servicioSolicitudes.obtenerEventosPendientes();
        // TODO: servicioSolicitudes.obtenerEventosPendientes();
        return null;
    }

    public void aprobarCancelacionEvento(Administrador admin, SolicitudCancelacionEvento solicitud) {
    	servicioSolicitudes.aprobarCancelacionEvento(admin, solicitud);
        // TODO: servicioSolicitudes.aprobarCancelacionEvento(admin, solicitud);
    }

    public void rechazarCancelacionEvento(Administrador admin,
                                          SolicitudCancelacionEvento solicitud,
                                          String motivo) {
    	servicioSolicitudes.rechazarCancelacionEvento(admin, solicitud, motivo);
        // TODO: servicioSolicitudes.rechazarCancelacionEvento(admin, solicitud, motivo);
    }

    public List<Evento> obtenerEventosPendientes() {
        return eventoRepositorio.getEventosPendientes();
    }

    public void aprobarEvento(Administrador admin, Evento evento) {
        evento.setEstado("ACTIVO");
        eventoRepositorio.actualizar(evento);
    }

    public void rechazarEvento(Administrador admin, Evento evento) {
        evento.setEstado("RECHAZADO");
        eventoRepositorio.actualizar(evento);
    }

    /**
     * Consulta ganancias totales de la plataforma.
     */
    public double consultarGananciasTotales() {
    	servicioReportes.calcularGananciasTotales();
        // TODO: servicioReportes.calcularGananciasTotales();
        return 0.0;
    }

    /**
     * Consulta total de transacciones.
     */
    public int consultarTotalTransacciones() {
    	servicioReportes.totalTransacciones();
        // TODO: servicioReportes.totalTransacciones();
        return 0;
    }

    /**
     * Consulta total de tiquetes vendidos.
     */
    public int consultarTotalTiquetesVendidos() {
    	servicioReportes.totalTiquetesVendidos();
        // TODO: servicioReportes.totalTiquetesVendidos();
        return 0;
    }
}