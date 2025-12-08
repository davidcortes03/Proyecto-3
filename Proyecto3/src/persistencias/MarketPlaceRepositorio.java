package persistencias;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import marketPlace.ContraOferta;
import marketPlace.Oferta;

public class MarketPlaceRepositorio implements Serializable{
	private final List<Oferta> ofertas;
    private final List<ContraOferta> contraOfertas;

    public MarketPlaceRepositorio() {
        this.ofertas = new ArrayList<>();
        this.contraOfertas = new ArrayList<>();
    }

    public void agregarOferta(Oferta oferta) {
        ofertas.add(oferta);
    }

    public void actualizarOferta(Oferta ofertaActualizada) {
        for (int i = 0; i < ofertas.size(); i++) {
            if (ofertas.get(i).getId() == ofertaActualizada.getId()) {
                ofertas.set(i, ofertaActualizada);
                return;
            }
        }
    }

    public void agregarContraOferta(ContraOferta contraOferta) {
        contraOfertas.add(contraOferta);
    }

    public List<Oferta> getOfertas() {
        return ofertas;
    }

    public List<ContraOferta> getContraOfertas() {
        return contraOfertas;
    }
}
