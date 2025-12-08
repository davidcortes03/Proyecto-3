package persistencias;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import tiquetesCompra.Tiquete;

public class TiqueteRepositorio implements Serializable{
	private final List<Tiquete> tiquetes;

    public TiqueteRepositorio() {
        this.tiquetes = new ArrayList<>();
    }

    public void agregar(Tiquete tiquete) {
        tiquetes.add(tiquete);
    }

    public void eliminarTiquete(Tiquete tiquete) {
        tiquetes.remove(tiquete);
    }

    public void actualizar(Tiquete tiqueteActualizado) {
        for (int i = 0; i < tiquetes.size(); i++) {
            Tiquete tiquete = tiquetes.get(i);
            if (tiquete.getId().equals(tiqueteActualizado.getId())) {
                tiquetes.set(i, tiqueteActualizado);
                return;
            }
        }
    }

    public List<Tiquete> getTiquetes() {
        return tiquetes;
    }
}
