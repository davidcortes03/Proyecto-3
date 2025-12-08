package persistencias;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import modelo.Evento;

public class EventoRepositorio implements Serializable {
	private final List<Evento> eventos;

    public EventoRepositorio() {
        this.eventos = new ArrayList<>();
    }

    public void agregar(Evento evento) {
        eventos.add(evento);
    }

    public void actualizar(Evento eventoActualizado) {
        for (int i = 0; i < eventos.size(); i++) {
            Evento evento = eventos.get(i);
            if (evento.getNombre().equals(eventoActualizado.getNombre())) {
                eventos.set(i, eventoActualizado);
                return;
            }
        }
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public List<Evento> getEventosPendientes() {
        List<Evento> pendientes = new ArrayList<>();
        for (Evento evento : eventos) {
            if ("PENDIENTE".equals(evento.getEstado())) {
                pendientes.add(evento);
            }
        }
        return pendientes;
    }

    public List<Evento> getEventosActivos() {
        List<Evento> activos = new ArrayList<>();
        for (Evento evento : eventos) {
            if ("ACTIVO".equals(evento.getEstado())) {
                activos.add(evento);
            }
        }
        return activos;
    }
}
