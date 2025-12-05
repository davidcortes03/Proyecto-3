package servicios;

import java.util.ArrayList;
import java.util.List;

import persistencias.SolicitudRepositorio;
import solicitudes.SolicitudVenue;

public class ServicioAdmin {
	
	private ServicioCompras servicioCompras;
	private SolicitudRepositorio solicitudRepo;
	
	public ServicioAdmin(ServicioCompras servicioCompras, SolicitudRepositorio solicitudRepo) {
		this.servicioCompras = servicioCompras;
		this.solicitudRepo = solicitudRepo;
	}
	

    /*
     * Setea los cargos de la aplicacion para la compra de cada tiquete.
     */
    public void configurarCargos(double porcentajeServicio, double cuotaFija) {
    		servicioCompras.setCuotaFija(cuotaFija);
    		servicioCompras.setPorcentajeServicio(porcentajeServicio);
    }
    	/*
    	 * Retorna la lista de solicitudes de venue por aprobar o rechazar.
    	 */
    public List<SolicitudVenue> obtenerSolicitudesVenuePendientes() {
    		List<SolicitudVenue> solicitudesPendientes = new ArrayList<>();
    		List<SolicitudVenue> todasSolicitudesVenue = solicitudRepo.getSolicitudesVenue();
    		for(SolicitudVenue soli: todasSolicitudesVenue) {
    			if(soli.getEstadoSolicitud() == "EN ESPERA") {
    				solicitudesPendientes.add(soli);
    			}
    		}
    		return solicitudesPendientes;
    }
    
}
