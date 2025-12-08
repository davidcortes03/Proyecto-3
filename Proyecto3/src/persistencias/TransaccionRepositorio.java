package persistencias;

import java.util.ArrayList;
import java.util.List;

import compras.Transaccion;

public class TransaccionRepositorio {
	private final List<Transaccion> transacciones;

    public TransaccionRepositorio() {
        this.transacciones = new ArrayList<>();
    }

    public void agregar(Transaccion transaccion) {
        transacciones.add(transaccion);
    }

    public List<Transaccion> getTransacciones() {
        return transacciones;
    }

    public List<Transaccion> getTransaccionesOfertas() {
        return new ArrayList<>(transacciones);
    }

    public List<Transaccion> getTransaccionesContraOfertas() {
        return new ArrayList<>(transacciones);
    }
}
