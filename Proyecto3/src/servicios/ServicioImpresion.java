package servicios;

import java.util.List;

import persistencias.TiqueteRepositorio;
import tiquetesCompra.Tiquete;
import usuarios.ClienteNatural;

public class ServicioImpresion {
	
	private TiqueteRepositorio tiqueteRepo;

	public ServicioImpresion(TiqueteRepositorio tiqueteRepo) {
		this.tiqueteRepo = tiqueteRepo;
	}
	
	/*
	 * Imprime el tiquete y lo hace intransferible. 
	 */
	 public void imprimirTiquete(Tiquete tiquete) {
		 
	 }
	 /*
	  * Genera el QR para poder visualizar la info del tiquete.
	  */
	 public void generarQR(Tiquete tiquete) {
		 
	 }
	 /*
	  * Revisa en los tiquetes ya comprados del usuario cual de estos ya son imprimibles o no.
	  */
	 public List<Tiquete> obtenerTiquetesImprimibles(ClienteNatural cliente) {
	    	
	 }
}
