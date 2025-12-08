package persistencias;

import java.util.ArrayList;
import java.util.List;

import solicitudes.SolicitudCancelacionEvento;
import solicitudes.SolicitudReembolso;
import solicitudes.SolicitudVenue;

public class SolicitudRepositorio {
	private final List<SolicitudReembolso> solicitudesReembolso;
    private final List<SolicitudVenue> solicitudesVenue;
    private final List<SolicitudCancelacionEvento> solicitudesCancelacion;

    public SolicitudRepositorio() {
        this.solicitudesReembolso = new ArrayList<>();
        this.solicitudesVenue = new ArrayList<>();
        this.solicitudesCancelacion = new ArrayList<>();
    }

    public void agregarSolicitudReembolso(SolicitudReembolso solicitud) {
        solicitudesReembolso.add(solicitud);
    }

    public void agregarSolicitudVenue(SolicitudVenue solicitud) {
        solicitudesVenue.add(solicitud);
    }

    public List<SolicitudVenue> getSolicitudesVenue() {
        return solicitudesVenue;
    }

    public void agregarSolicitudCancelacionEvento(SolicitudCancelacionEvento solicitud) {
        solicitudesCancelacion.add(solicitud);
    }

    public void actualizarSolicitudReembolso(SolicitudReembolso solicitudActualizada) {
        for (int i = 0; i < solicitudesReembolso.size(); i++) {
            if (solicitudesReembolso.get(i).getId() == solicitudActualizada.getId()) {
                solicitudesReembolso.set(i, solicitudActualizada);
                return;
            }
        }
    }

    public void actualizarSolicitudVenue(SolicitudVenue solicitudActualizada) {
        for (int i = 0; i < solicitudesVenue.size(); i++) {
            if (solicitudesVenue.get(i).getId() == solicitudActualizada.getId()) {
                solicitudesVenue.set(i, solicitudActualizada);
                return;
            }
        }
    }

    public void actualizarSolicitudCancelacionEvento(SolicitudCancelacionEvento solicitudActualizada) {
        for (int i = 0; i < solicitudesCancelacion.size(); i++) {
            if (solicitudesCancelacion.get(i).getId() == solicitudActualizada.getId()) {
                solicitudesCancelacion.set(i, solicitudActualizada);
                return;
            }
        }
    }

    public List<SolicitudReembolso> getSolicitudesReembolsoPendientes() {
        List<SolicitudReembolso> pendientes = new ArrayList<>();
        for (SolicitudReembolso solicitud : solicitudesReembolso) {
            if ("EN ESPERA".equals(solicitud.getEstadoReembolso()) || "PENDIENTE".equals(solicitud.getEstadoReembolso())) {
                pendientes.add(solicitud);
            }
        }
        return pendientes;
    }

    public List<SolicitudVenue> getSolicitudesVenuePendientes() {
        List<SolicitudVenue> pendientes = new ArrayList<>();
        for (SolicitudVenue solicitud : solicitudesVenue) {
            if ("EN ESPERA".equals(solicitud.getEstadoSolicitud())) {
                pendientes.add(solicitud);
            }
        }
        return pendientes;
    }

    public List<SolicitudCancelacionEvento> getSolicitudesCancelacionPendientes() {
        List<SolicitudCancelacionEvento> pendientes = new ArrayList<>();
        for (SolicitudCancelacionEvento solicitud : solicitudesCancelacion) {
            if ("EN ESPERA".equals(solicitud.getEstado())) {
                pendientes.add(solicitud);
            }
        }
        return pendientes;
    }
}
