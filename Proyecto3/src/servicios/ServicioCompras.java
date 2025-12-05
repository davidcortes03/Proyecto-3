package servicios;


import compras.Transaccion;
import persistencias.EventoRepositorio;
import persistencias.TiqueteRepositorio;
import persistencias.TransaccionRepositorio;
import persistencias.UsuarioRepositorio;
import solicitudes.SolicitudReembolso;
import tiquetesCompra.Item;
import tiquetesCompra.Tiquete;
import usuarios.ClienteNatural;
import usuarios.Organizador;

import java.util.List;

public class ServicioCompras {
	
	
	private TiqueteRepositorio tiqueteRepo;
	private TransaccionRepositorio transaccionRepo;
	private EventoRepositorio eventoRepo;
	private UsuarioRepositorio usuarioRepo;
	private double porcentajeServicio;
	private double cuotaFija;

	public ServicioCompras(TiqueteRepositorio tiqueteRepo, TransaccionRepositorio transaccionRepo,
			EventoRepositorio eventoRepo, UsuarioRepositorio usuarioRepo) {
		this.tiqueteRepo = tiqueteRepo;
		this.transaccionRepo = transaccionRepo;
		this.eventoRepo = eventoRepo;
		this.usuarioRepo = usuarioRepo;
		this.porcentajeServicio = 0.0;
		this.cuotaFija = 0.0;
	}
	

	public double getPorcentajeServicio() {
		return porcentajeServicio;
	}


	public void setPorcentajeServicio(double porcentajeServicio) {
		this.porcentajeServicio = porcentajeServicio;
	}


	public double getCuotaFija() {
		return cuotaFija;
	}


	public void setCuotaFija(double cuotaFija) {
		this.cuotaFija = cuotaFija;
	}

	/*
	 * Compra todos los objetos que están en el carrito de compra del comprador.
	 */
	public Transaccion comprarCarrito(ClienteNatural cliente, String metodoDePago) {
		List<Item> carrito = cliente.getCarrito();
		Transaccion tran = new Transaccion(cliente, null, carrito);
		if(cliente instanceof Organizador) {
			for(Item i: carrito) {
				if(validarDisponibilidad(i) == true) {
					for(Tiquete t: i.getTiquetes()) {
						if(t.getEvento().getOrganizador() == cliente) {
							t.setEsCortesia(true);
							t.getLocalidad().reducirCapacidadDisponible();
							t.setEstadoCompra("COMPRADA");
							if(t.getLocalidad().isConAsientos() == true) {
								t.getAsiento().setEstado("OCUPADO");
							}
						}
						cliente.agregarTiquete(t);
						tiqueteRepo.agregar(t);
						eventoRepo.actualizar(t.getEvento());
					}
				}
			}
			tran.setMetodoDePago("N/A");
			cliente.agregarHistorialFinanciero(tran);
			transaccionRepo.agregar(tran);
			usuarioRepo.actualizar(cliente);
		} else {
			if("SALDO VIRTUAL".equals(metodoDePago)) {
				double saldoCliente = cliente.getSaldoVirtual();
				double precioPagar = calcularTotalCarrito(cliente);
				if(precioPagar <= saldoCliente) {
					cliente.setSaldoVirtual(saldoCliente - precioPagar);
					for(Item i: carrito) {
						if(validarDisponibilidad(i) == true) {
							for(Tiquete t: i.getTiquetes()) {
								t.getLocalidad().reducirCapacidadDisponible();
								t.setEstadoCompra("COMPRADA");
								if(t.getLocalidad().isConAsientos() == true) {
									t.getAsiento().setEstado("OCUPADO");
								}
								cliente.agregarTiquete(t);
								tiqueteRepo.agregar(t);
								eventoRepo.actualizar(t.getEvento());
							}
						}
					}
				tran.setMetodoDePago("SALDO VIRTUAL");
				cliente.agregarHistorialFinanciero(tran);
				transaccionRepo.agregar(tran);
				usuarioRepo.actualizar(cliente);
				}
			}
			else if("PASARELA".equals(metodoDePago)) {
				for(Item i: carrito) {
					if(validarDisponibilidad(i) == true) {
						for(Tiquete t: i.getTiquetes()) {
							t.setEstadoCompra("COMPRADA");
							t.getLocalidad().reducirCapacidadDisponible();
							if(t.getLocalidad().isConAsientos() == true) {
								t.getAsiento().setEstado("OCUPADO");
							}
							cliente.agregarTiquete(t);
							tiqueteRepo.agregar(t);
							eventoRepo.actualizar(t.getEvento());
						}
					}
				}
				tran.setMetodoDePago("PASARELA");
				cliente.agregarHistorialFinanciero(tran);
				transaccionRepo.agregar(tran);
				usuarioRepo.actualizar(cliente);
			}
			
		}	
		return tran;
	}
	/*
	 * Compra un item especifico que el cliente tiene en su carrito. (Verificar que lo tenga)
	 */
    public Transaccion comprarItemIndividual(ClienteNatural cliente, Item item, String metodoDePago) {
		Transaccion tran = new Transaccion(cliente, null, item);
		if(cliente instanceof Organizador) {
			for(Tiquete t: item.getTiquetes()) {
				if(t.getEvento().getOrganizador() == cliente) {
					t.setEsCortesia(true);
					t.getLocalidad().reducirCapacidadDisponible();
					t.setEstadoCompra("COMPRADA");
					if(t.getLocalidad().isConAsientos() == true) {
						t.getAsiento().setEstado("OCUPADO");
					}					}
				cliente.agregarTiquete(t);
				tiqueteRepo.agregar(t);
				eventoRepo.actualizar(t.getEvento());
			}
			tran.setMetodoDePago("N/A");
			cliente.agregarHistorialFinanciero(tran);
			transaccionRepo.agregar(tran);
			usuarioRepo.actualizar(cliente);
		} else {
			if("SALDO VIRTUAL".equals(metodoDePago)) {
				double saldoCliente = cliente.getSaldoVirtual();
				double precioPagar = calcularPrecioItem(item);
				if(precioPagar <= saldoCliente) {
					cliente.setSaldoVirtual(saldoCliente - precioPagar);
					for(Tiquete t: item.getTiquetes()) {
						t.getLocalidad().reducirCapacidadDisponible();
						t.setEstadoCompra("COMPRADA");
						if(t.getLocalidad().isConAsientos() == true) {
							t.getAsiento().setEstado("OCUPADO");
						}
						cliente.agregarTiquete(t);
						tiqueteRepo.agregar(t);
						eventoRepo.actualizar(t.getEvento());
					}
				tran.setMetodoDePago("SALDO VIRTUAL");
				cliente.agregarHistorialFinanciero(tran);
				transaccionRepo.agregar(tran);
				usuarioRepo.actualizar(cliente);
				}
			}
			else if("PASARELA".equals(metodoDePago)) {
				for(Tiquete t: item.getTiquetes()) {
					t.setEstadoCompra("COMPRADA");
					t.getLocalidad().reducirCapacidadDisponible();
					if(t.getLocalidad().isConAsientos() == true) {
						t.getAsiento().setEstado("OCUPADO");
					}
					cliente.agregarTiquete(t);
					tiqueteRepo.agregar(t);
					eventoRepo.actualizar(t.getEvento());
				}	
				tran.setMetodoDePago("PASARELA");
				cliente.agregarHistorialFinanciero(tran);
				transaccionRepo.agregar(tran);
				usuarioRepo.actualizar(cliente);
			}
			
		}	
		return tran;
	}
    
    /*
     * Procesar el reembolso de solicitud en caso de ser aprobado.
     */
    public void procesarReembolso(SolicitudReembolso solicitud) {
    		Tiquete tiquete = solicitud.getTiqueteReembolso();
    		ClienteNatural cliente = solicitud.getCliente();
    		cliente.getMisTiquetes().remove(tiquete);
    		double saldoVirtual = cliente.getSaldoVirtual();
    		cliente.setSaldoVirtual(saldoVirtual + tiquete.getPrecioPagar());
    		tiquete.getLocalidad().agregarCapacidadDisponible();
    		tiquete.getAsiento().setEstado("DESOCUPADO");
    		tiqueteRepo.eliminarTiquete(tiquete);
    }
    
    /*
     * Transfiere el tiquete al otro cliente que le entra como parametro
     */
    public void transferirTiquete(String usuarioReceptor, Tiquete tiquete, ClienteNatural clienteQueTransfiere) {
    		if(tiquete.getTransferido() == false) {
    			for(ClienteNatural usuario: usuarioRepo.getUsuariosNaturales()) {
    				String nombreUsuario = usuario.getNombreUsuario();
    				if(nombreUsuario == usuarioReceptor) {
    					usuario.getMisTiquetes().add(tiquete);
    	    				clienteQueTransfiere.getMisTiquetes().remove(tiquete);
    	    				tiquete.setTransferido(true);
    	    				usuarioRepo.actualizar(usuario);
    	    				usuarioRepo.actualizar(clienteQueTransfiere);
    				}
    			}
    		}
    }
    
    
    public double calcularTotalCarrito(ClienteNatural cliente) {
    		double total = 0; 
    		List<Item> carrito = cliente.getCarrito();
    		for(Item i: carrito) {
    			for(Tiquete t: i.getTiquetes()) {
    				double precioBase = t.getPrecioBase();
    				double precioTiquete = calcularPrecioFinal(precioBase, porcentajeServicio, cuotaFija);
    				t.setPrecioPagar(precioTiquete);
    				total += precioTiquete;
    			}
    		}
    		return total;
    }
    /*
     * Acutualiza todos cargos para la compra de cualquier objeto.
     */
    public void actualizarParametros(double porcentajeServicio, double cuotaFija) {
    		this.porcentajeServicio = porcentajeServicio;
        this.cuotaFija = cuotaFija;
    }
    /*
     * Valida la disponibilidad 1. evento del item no cancelado.
     */
    public boolean validarDisponibilidad(Item item) {
    		boolean respuesta = true;
        List<Tiquete> tiquetes = item.getTiquetes();
        if(tiquetes == null || tiquetes.isEmpty()) {
            respuesta = false;
        }
        for(Tiquete t : tiquetes) {
            if ("CANCELADO".equals(t.getEvento().getEstado())) {
            	respuesta = false;
            }
        }
        return respuesta;
    }

    /*
     * Calcula el precio de cada item por si solo se va a comprar uno individualmente.
     */
    public double calcularPrecioItem(Item item) {
    		double total = 0; 
		for(Tiquete t: item.getTiquetes()) {
			double precioBase = t.getPrecioBase();
			double precioTiquete = calcularPrecioFinal(precioBase, porcentajeServicio, cuotaFija);
			t.setPrecioPagar(precioTiquete);
			total += precioTiquete;
		}
		return total;
	}
    
    /**
     * Calcula el precio final con los parametros correspondientes.
     * 
     * @param precioBase
     * @param cargoPorcentual
     * @param cargoImpresionEmision
     * @return el valor final a pagar por el boleto.
     */
    public double calcularPrecioFinal(double precioBase, double cargoPorcentual, double cargoImpresionEmision) {
    	double porcentaje = precioBase * (cargoPorcentual / 100);
    	double precioFinal = precioBase + porcentaje + cargoImpresionEmision;
    	return precioFinal;
    }
    
    /*
     * Si el evento se cancela, se llama a este metodo para que haga toda la logica de reembolsar cada tiquete.
     */
    public void reembolsarPorCancelacionEvento(Tiquete tiquete) {
    		
    }
}
