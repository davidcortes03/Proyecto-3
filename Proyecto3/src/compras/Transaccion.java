package compras;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import marketPlace.ContraOferta;
import marketPlace.Oferta;
import tiquetesCompra.Item;
import usuarios.ClienteNatural;

public class Transaccion {
	
	private LocalDateTime fechaHora;
	private String idTransaccion;
	private String metodoDePago; // "PASARELA", "SALDO VIRTUAL" o "N/A" si es organizador.
	private ClienteNatural comprador;
	private List<Item> items;
	private Item item;
	private Oferta oferta;
	private ContraOferta contraOferta;

	public Transaccion(ClienteNatural comprador, String metodoDePago, List<Item> items) {
		this.fechaHora = LocalDateTime.now();
		this.idTransaccion = generarId();
		this.metodoDePago = metodoDePago;
		this.comprador = comprador;
		this.items = items;
		this.item = null;
		this.oferta = null;
		this.contraOferta = null;
		
	}
	
	public Transaccion(ClienteNatural comprador, String metodoDePago, Item item) {
		this.fechaHora = LocalDateTime.now();
		this.idTransaccion = generarId();
		this.metodoDePago = metodoDePago;
		this.comprador = comprador;
		this.items = null;
		this.item = item;
		this.oferta = null;
		this.contraOferta = null;
	}
	
	public Transaccion(ClienteNatural comprador, String metodoDePago, Oferta oferta) {
		this.fechaHora = LocalDateTime.now();
		this.idTransaccion = generarId();
		this.metodoDePago = metodoDePago;
		this.comprador = comprador;
		this.items = null;
		this.item = null;
		this.oferta = oferta;
		this.contraOferta = null;
	}
	
	public Transaccion(ClienteNatural comprador, String metodoDePago, ContraOferta contraOferta) {
		this.fechaHora = LocalDateTime.now();
		this.idTransaccion = generarId();
		this.metodoDePago = metodoDePago;
		this.comprador = comprador;
		this.items = null;
		this.item = null;
		this.oferta = null;
		this.contraOferta = contraOferta;
	}
	
	
	
	public LocalDateTime getFechaHora() {
		return fechaHora;
	}



	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}



	public String getIdTransaccion() {
		return idTransaccion;
	}



	public void setIdTransaccion(String idTransaccion) {
		this.idTransaccion = idTransaccion;
	}



	public String getMetodoDePago() {
		return metodoDePago;
	}



	public void setMetodoDePago(String metodoDePago) {
		this.metodoDePago = metodoDePago;
	}



	public ClienteNatural getComprador() {
		return comprador;
	}



	public void setComprador(ClienteNatural comprador) {
		this.comprador = comprador;
	}



	public List<Item> getItems() {
		return items;
	}



	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	public Item getItem() {
		return item;
	}
	
	public void setItem(Item i) {
		item = i;
	}
	
	public Oferta getOferta() {
		return oferta;
	}
	
	public void setOferta(Oferta nuevaOferta) {
		oferta = nuevaOferta;
	}
	
	public ContraOferta getContraOferta() {
		return contraOferta;
	}

	public void setContraOferta(ContraOferta contraOferta) {
		this.contraOferta = contraOferta;
	}

	/**
	 * 
	 * Genera un id unico para asignarle a la transaccion generada
	 * @return Id unico
	 */
	
	public String generarId() {
		return UUID.randomUUID().toString();
	}

}


