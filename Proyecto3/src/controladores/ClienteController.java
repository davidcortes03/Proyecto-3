package controladores;

import java.util.List;

import modelo.Evento;
import servicios.ServicioCompras;
import servicios.ServicioImpresion;
import servicios.ServicioReportes;
import servicios.ServicioSolicitudes;
import tiquetesCompra.Item;
import tiquetesCompra.Tiquete;
import usuarios.ClienteNatural;

public class ClienteController {

    private ServicioCompras servicioCompras;
    private ServicioSolicitudes servicioSolicitudes;
    private ServicioImpresion servicioImpresion;
    private ServicioReportes servicioReportes;

    public ClienteController(ServicioCompras servicioCompras,
                             ServicioSolicitudes servicioSolicitudes,
                             ServicioImpresion servicioImpresion,
                             ServicioReportes servicioReportes) {
        this.servicioCompras = servicioCompras;
        this.servicioSolicitudes = servicioSolicitudes;
        this.servicioImpresion = servicioImpresion;
        this.servicioReportes = servicioReportes;
    }

    /**
     * Agrega un item al carrito del cliente.
     */
    public void agregarItemAlCarrito(ClienteNatural cliente, Item item) {
        // TODO: cliente.getCarrito().add(item) o cliente.agregarAlCarrito(item)
    }

    /**
     * Elimina un item del carrito del cliente.
     */
    public void eliminarItemDelCarrito(ClienteNatural cliente, Item item) {
        // TODO: remover item del carrito
    }

    /**
     * Vacía el carrito del cliente.
     */
    public void vaciarCarrito(ClienteNatural cliente) {
        // TODO: limpiar lista carritoCompras
    }

    /**
     * Devuelve los items del carrito del cliente.
     */
    public List<Item> obtenerCarrito(ClienteNatural cliente) {
        // TODO: return cliente.getCarrito();
        return null;
    }

    /**
     * Compra todo el carrito del cliente.
     */
    public void comprarCarrito(ClienteNatural cliente, String metodoDePago) {
        // TODO: usar servicioCompras.comprarCarrito(...)
        // TODO: opcionalmente llamar a servicioImpresion para imprimir tiquetes
    }

    /**
     * Compra un solo item del carrito.
     */
    public void comprarItem(ClienteNatural cliente, Item item, String metodoDePago) {
        // TODO: usar servicioCompras.comprarItemIndividual(...)
        // TODO: opcionalmente imprimir tiquetes de ese item
    }

    /**
     * Solicita reembolso de un tiquete.
     */
    public void solicitarReembolso(ClienteNatural cliente, Tiquete tiquete, String motivo) {
        // TODO: usar servicioSolicitudes.crearSolicitudReembolso(...)
    }

    /**
     * Obtiene los tiquetes del cliente.
     */
    public List<Tiquete> obtenerTiquetesCliente(ClienteNatural cliente) {
        // TODO: return cliente.getMisTiquetes();
        return null;
    }

    /**
     * Imprime un tiquete específico.
     */
    public void imprimirTiquete(Tiquete tiquete) {
        // TODO: servicioImpresion.imprimirTiquete(tiquete);
    }

    /**
     * Imprime todos los tiquetes del cliente.
     */
    public void imprimirTodosLosTiquetes(ClienteNatural cliente) {
        // TODO: servicioImpresion.imprimirTiquetes(cliente.getMisTiquetes());
    }

    /**
     * Consulta las ventas de un evento, vista cliente (si aplica para reportes).
     */
    public double consultarVentasEvento(Evento evento) {
        // TODO: servicioReportes.calcularVentasPorEvento(evento);
        return 0.0;
    }
}