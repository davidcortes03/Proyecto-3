package persistencias;

import java.util.ArrayList;
import java.util.List;

import modelo.Venue;

public class VenueRepositorio {
	private final List<Venue> venues;

    public VenueRepositorio() {
        this.venues = new ArrayList<>();
    }

    public void agregar(Venue venue) {
        venues.add(venue);
    }

    public void actualizar(Venue venueActualizado) {
        for (int i = 0; i < venues.size(); i++) {
            Venue venue = venues.get(i);
            if (venue.getNombre().equals(venueActualizado.getNombre())) {
                venues.set(i, venueActualizado);
                return;
            }
        }
    }

    public List<Venue> getVenues() {
        return venues;
    }

    public Venue getFirstOrDefault(String nombrePreferido, int capacidadPorDefecto) {
        if (!nombrePreferido.isEmpty()) {
            for (Venue venue : venues) {
                if (venue.getNombre().equalsIgnoreCase(nombrePreferido)) {
                    return venue;
                }
            }
        }
        if (!venues.isEmpty()) {
            return venues.get(0);
        }
        Venue creado = new Venue(nombrePreferido.isEmpty() ? "Venue temporal" : nombrePreferido,
                capacidadPorDefecto,
                "Por definir");
        agregar(creado);
        return creado;
    }
}
