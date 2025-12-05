package servicios;

import java.util.List;

import compras.Transaccion;
import modelo.Evento;
import persistencias.TiqueteRepositorio;
import persistencias.TransaccionRepositorio;
import tiquetesCompra.Tiquete;

public class ServicioReportes {
	
	private TransaccionRepositorio transaccionRepo;
	private TiqueteRepositorio tiqueteRepo;


	public ServicioReportes(TransaccionRepositorio transaccionRepo, TiqueteRepositorio tiqueteRepo) {
		this.transaccionRepo = transaccionRepo;
		this.tiqueteRepo = tiqueteRepo;

	}
	
	/*
	 * Calcula la ganancia total del evento.
	 */
	public double calcularVentasPorEvento(Evento evento) {
		double gananciaPorEvento = 0;
		for(Tiquete t: tiqueteRepo.getTiquetes()) {
			Evento e = t.getEvento();
			if(e.equals(evento)) {
				gananciaPorEvento += t.getPrecioPagar();
			}
		}
		return gananciaPorEvento;
	}
	
	/*
	 * Calcula la ganancia total de la pagina. 
	 */
    public double calcularGananciasTotales() {
    		double ganancia = 0;
    		for(Tiquete t: tiqueteRepo.getTiquetes()) {
    			ganancia += t.getPrecioPagar();
    		}
    		return ganancia;
    }
    
    /*
     * Cuenta la cantidad de tiquetes vendidos para un evento.
     */
    public int contarTiquetesVendidosPorEvento(Evento evento) {
    		int contador = 0;
    		for(Tiquete t : tiqueteRepo.getTiquetes()) {
    			if(t.getEvento().equals(evento)) {
    				contador += 1;
    			}
    		}
    		return contador;
    }
    
    public List<Transaccion> obtenerHistorialMarketplaceOfertas() {
    		return transaccionRepo.getTransaccionesOfertas();
    }

    public List<Transaccion> obtenerHistorialMarketplaceContraOfertas() {
    		return transaccionRepo.getTransaccionesContraOfertas();
    }
    
    public int totalTransacciones() {
    		return transaccionRepo.getTransacciones().size();
    }
    
    
    public int totalTiquetesVendidos() {
    		return tiqueteRepo.getTiquetes().size();
    }
}
