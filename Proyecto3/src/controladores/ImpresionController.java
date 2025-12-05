package controladores;

import java.util.List;

import servicios.ServicioImpresion;
import tiquetesCompra.Tiquete;
import usuarios.ClienteNatural;

public class ImpresionController {

    private ServicioImpresion servicioImpresion;

    public ImpresionController(ServicioImpresion servicioImpresion) {
        this.servicioImpresion = servicioImpresion;
    }

    /**
     * Cambia la ruta donde se guardan los archivos de impresión.
     */
    public void configurarRutaSalida(String ruta) {
        // TODO: servicioImpresion.setRutaSalida(ruta);
    }

    /**
     * Imprime un tiquete.
     */
    public void imprimirTiquete(Tiquete tiquete) {
        // TODO: servicioImpresion.imprimirTiquete(tiquete);
    }

    /**
     * Imprime una lista de tiquetes.
     */
    public void imprimirTiquetes(List<Tiquete> tiquetes) {
        // TODO: servicioImpresion.imprimirTiquetes(tiquetes);
    }

    /**
     * Imprime todos los tiquetes de un cliente.
     */
    public void imprimirTiquetesCliente(ClienteNatural cliente) {
        // TODO: servicioImpresion.imprimirTiquetes(cliente.getMisTiquetes());
    }
}