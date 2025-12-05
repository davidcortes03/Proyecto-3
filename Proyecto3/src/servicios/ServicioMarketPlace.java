package servicios;

import java.util.List;

import compras.Transaccion;
import marketPlace.ContraOferta;
import marketPlace.Oferta;
import persistencias.MarketPlaceRepositorio;
import persistencias.TiqueteRepositorio;
import persistencias.TransaccionRepositorio;
import persistencias.UsuarioRepositorio;
import tiquetesCompra.Tiquete;
import usuarios.ClienteNatural;

public class ServicioMarketPlace {
	
	private MarketPlaceRepositorio marketPlaceRepo;
	private TiqueteRepositorio tiqueteRepo;
	private UsuarioRepositorio usuarioRepo;
	private TransaccionRepositorio  transaccionRepo;

	public ServicioMarketPlace(MarketPlaceRepositorio marketPlaceRepo, TiqueteRepositorio tiqueteRepo,
			UsuarioRepositorio usuarioRepo, TransaccionRepositorio  transaccionRepo) {
		this.marketPlaceRepo = marketPlaceRepo;
		this.tiqueteRepo = tiqueteRepo;
		this.usuarioRepo = usuarioRepo;
		this.transaccionRepo = transaccionRepo;
	}
	
	/*
	 * revisa si el tiquete es apto para ser ofertado y si sí, crea la oferta. 
	 */
	public void publicarOferta(ClienteNatural vendedor, Tiquete tiquete, double precio) {
		if("NO REVENTA".equals(tiquete.getEstadoReventa())) {
			Oferta OfertaCreada = new Oferta(precio, tiquete, vendedor);
			marketPlaceRepo.agregarOferta(OfertaCreada);
			tiquete.setEstadoReventa("EN REVENTA");
			vendedor.getOfertas().add(OfertaCreada);
			tiqueteRepo.actualizar(tiquete);
			usuarioRepo.actualizar(vendedor);
		}
	}
	/*
	 * Elimina una oferta creada. No la borra del marketPlaceRepo porque esta podría ser el registro.
	 */
    public void cancelarOferta(Oferta oferta, ClienteNatural vendedor) {
    		oferta.setEstadoOf("CANCELADA");
    		vendedor.getOfertas().remove(oferta);
    		marketPlaceRepo.actualizarOferta(oferta);
    		usuarioRepo.actualizar(vendedor);
    }
    
    /*
     * Cancela la contra oferta hecha hacia una oferta.
     */
    public void cancelarContraOferta(ContraOferta contraOferta, ClienteNatural comprador) {
    		contraOferta.setEstadoCo("CANCELADA");
    		contraOferta.getOferta().getContraOfertas().remove(contraOferta);
    		comprador.getContraOfertas().remove(contraOferta);
    		marketPlaceRepo.actualizarOferta(contraOferta.getOferta());
    		usuarioRepo.actualizar(comprador);  	
    }
    
    public void crearContraOferta(ClienteNatural comprador, Oferta ofertaOriginal, double nuevoPrecio, String metodoDePago) {
    		if("ACTIVA".equals(ofertaOriginal.getEstadoOf())) {
    			ContraOferta contraOferta = new ContraOferta(nuevoPrecio, comprador, ofertaOriginal, metodoDePago);
    			comprador.getContraOfertas().add(contraOferta);
    			ofertaOriginal.getContraOfertas().add(contraOferta);
    			marketPlaceRepo.actualizarOferta(ofertaOriginal);
    			usuarioRepo.actualizar(comprador);
    		}
    		
    }
    
    /*
     * Acepta la contra oferta hecha por el comprador sobre la oferta del vendedor. 
     */
    public Transaccion aceptarContraOferta(ContraOferta contraOferta, ClienteNatural vendedor) {
        Tiquete t = contraOferta.getOferta().getTiquete();
        double saldoComprador = contraOferta.getComprador().getSaldoVirtual();
        double precioPagar = contraOferta.getPrecioNuevo();
        double saldoVirtualVendedor = vendedor.getSaldoVirtual();
        Transaccion tran = new Transaccion(contraOferta.getComprador(), contraOferta.getMetodoDePago(), contraOferta);
        if ("SALDO VIRTUAL".equals(contraOferta.getMetodoDePago())) {
            if (precioPagar > saldoComprador) {
                throw new IllegalStateException("Saldo insuficiente para aceptar la contraoferta.");
            }
            contraOferta.getComprador().setSaldoVirtual(saldoComprador - precioPagar);
            vendedor.setSaldoVirtual(saldoVirtualVendedor + precioPagar);
        }
        else if ("PASARELA".equals(contraOferta.getMetodoDePago())) {
            vendedor.setSaldoVirtual(saldoVirtualVendedor + precioPagar);
        }
        contraOferta.setEstadoCo("COMPRADA");
        contraOferta.getOferta().setEstadoOf("COMPRADA");
        for (ContraOferta co : contraOferta.getOferta().getContraOfertas()) {
            if (!co.equals(contraOferta)) {
                co.setEstadoCo("RECHAZADA");
            }
        }
        contraOferta.getComprador().agregarTiquete(t);
        vendedor.getMisTiquetes().remove(t);
        contraOferta.getComprador().agregarHistorialFinanciero(tran);
        transaccionRepo.agregar(tran);
        
        tiqueteRepo.actualizar(t);
        marketPlaceRepo.actualizarOferta(contraOferta.getOferta());
        usuarioRepo.actualizar(vendedor);
        usuarioRepo.actualizar(contraOferta.getComprador());

        return tran;
    }
    
    /*
     * Acepta la oferta del vendedor y hace todo el proceso de pago.
     */
    public Transaccion comprarOferta(Oferta oferta, ClienteNatural comprador, String metodoDePago) {
    		Transaccion tran = new Transaccion(comprador, null, oferta);
    		if (!"ACTIVA".equals(oferta.getEstadoOf())) {
    		    throw new IllegalStateException("La oferta no está disponible.");
    		}
    		if (comprador.equals(oferta.getVendedor())) {
    		    throw new IllegalArgumentException("No puedes comprar tu propia oferta.");
    		}
    		double saldoCliente = comprador.getSaldoVirtual();
		double precioPagar = oferta.getPrecioOferta();
		ClienteNatural vendedor = oferta.getVendedor();
    		if("SALDO VIRTUAL".equals(metodoDePago)) {
    			if(precioPagar <= saldoCliente) {
    				comprador.setSaldoVirtual(saldoCliente - precioPagar);
    				double saldoVirtualVendedor = oferta.getVendedor().getSaldoVirtual();
    				oferta.getVendedor().setSaldoVirtual(precioPagar + saldoVirtualVendedor);
    				Tiquete t = oferta.getTiquete();
    				comprador.agregarTiquete(t);
    				vendedor.getMisTiquetes().remove(t);
    				oferta.setEstadoOf("COMPRADA");
    				tran.setMetodoDePago("SALDO VIRTUAL");
    				comprador.agregarHistorialFinanciero(tran);
    				transaccionRepo.agregar(tran);
    				usuarioRepo.actualizar(vendedor);
    				usuarioRepo.actualizar(comprador);
    			}
    		} else if("PASARELA".equals(metodoDePago)) {
    			Tiquete t = oferta.getTiquete();
    			comprador.agregarTiquete(t);
    			double saldoVirtualVendedor = oferta.getVendedor().getSaldoVirtual();
			oferta.getVendedor().setSaldoVirtual(precioPagar + saldoVirtualVendedor);
    			vendedor.getMisTiquetes().remove(t);
    			oferta.setEstadoOf("COMPRADA");
    			tran.setMetodoDePago("PASARELA");
    			comprador.agregarHistorialFinanciero(tran);;
    			transaccionRepo.agregar(tran);
			usuarioRepo.actualizar(vendedor);
			usuarioRepo.actualizar(comprador);
			}
    		return tran;
    }
    
    public List<Oferta> obtenerOfertasDeCliente(ClienteNatural cliente) {
    		return cliente.getOfertas();
    }
    
    public List<ContraOferta> obtenerContraOfertasDeCliente(ClienteNatural cliente) {
    		return cliente.getContraOfertas();
    }
}
