package controladores;

import java.util.List;

import marketPlace.ContraOferta;
import marketPlace.Oferta;
import servicios.ServicioMarketPlace;
import tiquetesCompra.Tiquete;
import usuarios.ClienteNatural;

public class MarketplaceController {

    private ServicioMarketPlace servicioMarketPlace;

    public MarketplaceController(ServicioMarketPlace servicioMarketPlace) {
        this.servicioMarketPlace = servicioMarketPlace;
    }

    /**
     * Publica una oferta de reventa de un tiquete.
     */
    public void publicarOferta(ClienteNatural vendedor, Tiquete tiquete, double precio) {
        // TODO: servicioMarketPlace.publicarOferta(vendedor, tiquete, precio);
    	servicioMarketPlace.publicarOferta(vendedor, tiquete, precio);
    }

    /**
     * Cancela una oferta del vendedor.
     */
    public void cancelarOferta(ClienteNatural vendedor, Oferta oferta) {
        // TODO: servicioMarketPlace.cancelarOferta(oferta, vendedor);
    	servicioMarketPlace.cancelarOferta(oferta, vendedor);
    }

    /**
     * Crea una contraoferta sobre una oferta original.
     */
    public void crearContraOferta(ClienteNatural comprador,
                                  Oferta ofertaOriginal,
                                  double nuevoPrecio,
                                  String metodoDePago) {
        // TODO: servicioMarketPlace.crearContraOferta(comprador, ofertaOriginal, nuevoPrecio, metodoDePago);
    	servicioMarketPlace.crearContraOferta(comprador, ofertaOriginal, nuevoPrecio, metodoDePago);
    }

    /**
     * Cancela una contraoferta hecha por el comprador.
     */
    public void cancelarContraOferta(ClienteNatural comprador, ContraOferta contraOferta) {
        // TODO: servicioMarketPlace.cancelarContraOferta(contraOferta, comprador);
    	servicioMarketPlace.cancelarContraOferta(contraOferta, comprador);
    }

    /**
     * Acepta una contraoferta (vendedor).
     */
    public void aceptarContraOferta(ClienteNatural vendedor, ContraOferta contraOferta) {
        // TODO: servicioMarketPlace.aceptarContraOferta(contraOferta, vendedor);
    	servicioMarketPlace.aceptarContraOferta(contraOferta, vendedor);
    }

    /**
     * Compra una oferta directamente (comprador).
     */
    public void comprarOferta(ClienteNatural comprador,
                              Oferta oferta,
                              String metodoDePago) {
        // TODO: servicioMarketPlace.comprarOferta(oferta, comprador, metodoDePago);
    	servicioMarketPlace.comprarOferta(oferta, comprador, metodoDePago);
    }

    /**
     * Obtiene las ofertas de un cliente.
     */
    public List<Oferta> obtenerOfertasDeCliente(ClienteNatural cliente) {
        // TODO: servicioMarketPlace.obtenerOfertasDeCliente(cliente);
        return servicioMarketPlace.obtenerOfertasDeCliente(cliente);
    }

    /**
     * Obtiene las contraofertas de un cliente.
     */
    public List<ContraOferta> obtenerContraOfertasDeCliente(ClienteNatural cliente) {
        // TODO: servicioMarketPlace.obtenerContraOfertasDeCliente(cliente);
        return servicioMarketPlace.obtenerContraOfertasDeCliente(cliente);
    }
}