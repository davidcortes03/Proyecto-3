package persistencias;

import java.util.ArrayList;
import java.util.List;

import modelo.Evento;

public class EventoRepositorio {
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
}
