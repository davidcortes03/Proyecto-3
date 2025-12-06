package servicios;

import java.util.ArrayList;
import java.util.List;

import persistencias.TiqueteRepositorio;
import tiquetesCompra.Tiquete;
import usuarios.ClienteNatural;
import vistas.VentanaTiquete;

public class ServicioImpresion {
	
	private TiqueteRepositorio tiqueteRepo;

	public ServicioImpresion(TiqueteRepositorio tiqueteRepo) {
		this.tiqueteRepo = tiqueteRepo;
	}
	
	/*
	 * Imprime el tiquete y lo hace intransferible. 
	 */
	public void imprimirTiquete(Tiquete t) {

	    if (t.getImpreso()) {
	        System.out.println("Este tiquete ya fue impreso y no puede volver a imprimirse.");
	        return;
	    }

	    String rutaQR = generarQR(t);

	    new VentanaTiquete(t, rutaQR).setVisible(true);

	    t.setImpreso(true);

	    tiqueteRepo.actualizar(t);
	}
	 
	 /*
	  * Imprime una lista de tiquetes
	  */
	 public void imprimirTiquetes(List<Tiquete> tiquetes) {
		 
		 for (Tiquete t: tiquetes) {
			 imprimirTiquete(t);
		 }
		
	 }
	 
	 /*
	  * Genera el QR para poder visualizar la info del tiquete.
	  */
	@SuppressWarnings("deprecation")
	public String generarQR(Tiquete t) {

		    try {
		        String textoQR =
		                "Evento: " + t.getEvento().getNombre() + "\n" +
		                "ID: " + t.getId() + "\n" +
		                "Fecha Evento: " + t.getEvento().getFechaHora() + "\n" +
		                "Fecha Impresión: " + java.time.LocalDate.now();


		        String data = java.net.URLEncoder.encode(textoQR, "UTF-8");


		        String urlQR = "https://api.qrserver.com/v1/create-qr-code/?size=250x250&data=" + data;

		        String ruta = "qr_tiquete_" + t.getId() + ".png";

		        java.io.InputStream in = new java.net.URL(urlQR).openStream();
		        java.nio.file.Files.copy(
		                in,
		                java.nio.file.Paths.get(ruta),
		                java.nio.file.StandardCopyOption.REPLACE_EXISTING
		        );

		        return ruta; // <-- Devuelve la ruta del PNG para mostrarlo en la interfaz

		    } catch (Exception e) {
		        e.printStackTrace();
		        return null;
		    }
		}
	 /*
	  * Revisa en los tiquetes ya comprados del usuario cual de estos ya son imprimibles o no.
	  */
	 public List<Tiquete> obtenerTiquetesImprimibles(ClienteNatural cliente) {
		 
		 List<Tiquete> tiquetesAImprimir = new ArrayList<>();
		 List<Tiquete> tiquetesCliente = cliente.getMisTiquetes();
		 for (Tiquete tiq : tiquetesCliente) {
			if (tiq.getImpreso() == false) {
				tiquetesAImprimir.add(tiq);
				tiq.setImpreso(true);
			}
		 }
		 
		 return tiquetesAImprimir;

	 }

	 public void setRutaSalida(String ruta) {
		// TODO Auto-generated method stub
		
	 }
}
