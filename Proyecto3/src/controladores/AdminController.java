package controladores;

import java.util.List;

import modelo.Evento;
import servicios.ServicioAdmin;
import servicios.ServicioCompras;
import servicios.ServicioReportes;
import servicios.ServicioSolicitudes;
import solicitudes.SolicitudCancelacionEvento;
import solicitudes.SolicitudReembolso;
import solicitudes.SolicitudVenue;
import usuarios.Administrador;

public class AdminController {

    private ServicioAdmin servicioAdmin;          // si lo usas para cosas específicas de admin
    private ServicioCompras servicioCompras;      // para configurar cargos, si lo haces desde admin
    private ServicioSolicitudes servicioSolicitudes;
    private ServicioReportes servicioReportes;

    public AdminController(ServicioAdmin servicioAdmin,
                           ServicioCompras servicioCompras,
                           ServicioSolicitudes servicioSolicitudes,
                           ServicioReportes servicioReportes) {
        this.servicioAdmin = servicioAdmin;
        this.servicioCompras = servicioCompras;
        this.servicioSolicitudes = servicioSolicitudes;
        this.servicioReportes = servicioReportes;
    }

    /**
     * Configura cargos de servicio para las compras.
     */
    public void configurarCargos(double porcentajeServicio, double cuotaFija) {
        // TODO: servicioCompras.actualizarParametros(porcentajeServicio, cuotaFija);
        // o delegar a servicioAdmin si lo encapsulas allá
    }

    /**
     * Obtiene reembolsos pendientes.
     */
    public List<SolicitudReembolso> obtenerReembolsosPendientes() {
        // TODO: servicioSolicitudes.obtenerReembolsosPendientes();
        return null;
    }

    public void aprobarReembolso(Administrador admin, SolicitudReembolso solicitud) {
        // TODO: servicioSolicitudes.aprobarReembolso(admin, solicitud);
    }

    public void rechazarReembolso(Administrador admin, SolicitudReembolso solicitud, String motivo) {
        // TODO: servicioSolicitudes.rechazarReembolso(admin, solicitud, motivo);
    }

    /**
     * Venues pendientes de aprobación.
     */
    public List<SolicitudVenue> obtenerVenuesPendientes() {
        // TODO: servicioSolicitudes.obtenerVenuesPendientes();
        return null;
    }

    public void aprobarVenue(Administrador admin, SolicitudVenue solicitud) {
        // TODO: servicioSolicitudes.aprobarVenue(admin, solicitud);
    }

    public void rechazarVenue(Administrador admin, SolicitudVenue solicitud, String motivo) {
        // TODO: servicioSolicitudes.rechazarVenue(admin, solicitud, motivo);
    }

    /**
     * Solicitudes de cancelación pendientes.
     */
    public List<SolicitudCancelacionEvento> obtenerSolicitudesCancelacionPendientes() {
        // TODO: servicioSolicitudes.obtenerEventosPendientes();
        return null;
    }

    public void aprobarCancelacionEvento(Administrador admin, SolicitudCancelacionEvento solicitud) {
        // TODO: servicioSolicitudes.aprobarCancelacionEvento(admin, solicitud);
    }

    public void rechazarCancelacionEvento(Administrador admin,
                                          SolicitudCancelacionEvento solicitud,
                                          String motivo) {
        // TODO: servicioSolicitudes.rechazarCancelacionEvento(admin, solicitud, motivo);
    }

    /**
     * Consulta ganancias totales de la plataforma.
     */
    public double consultarGananciasTotales() {
        // TODO: servicioReportes.calcularGananciasTotales();
        return 0.0;
    }

    /**
     * Consulta total de transacciones.
     */
    public int consultarTotalTransacciones() {
        // TODO: servicioReportes.totalTransacciones();
        return 0;
    }

    /**
     * Consulta total de tiquetes vendidos.
     */
    public int consultarTotalTiquetesVendidos() {
        // TODO: servicioReportes.totalTiquetesVendidos();
        return 0;
    }
}