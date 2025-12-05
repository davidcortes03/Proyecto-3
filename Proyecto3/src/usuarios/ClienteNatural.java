package usuarios;

import java.util.ArrayList;
import java.util.List;

import compras.Transaccion;
import marketPlace.ContraOferta;
import marketPlace.Oferta;
import tiquetesCompra.Item;
import tiquetesCompra.Tiquete;

public class ClienteNatural extends Cliente {
	
	protected double saldoVirtual;
	protected List<Tiquete> misTiquetes; //Cada item es una lista con los tiquetes después de que la compra se realice
	protected List<Transaccion> historialFinanciero;
	protected List<Item> carritoCompras; // lista donde se irá almacenando los items a comprar seleccionados por el comprador.
	protected List<Oferta> ofertas; //Estos si son tiquetes.
	protected List<ContraOferta> contraOfertas; // Son tiquetes.
	
	public ClienteNatural(String nombreUsuario, String correo, String contrasena) {
		
		super(nombreUsuario, correo, contrasena);
		this.saldoVirtual = 0.0;
		this.misTiquetes = new ArrayList<>();
		this.historialFinanciero = new ArrayList<>();
		this.carritoCompras = new ArrayList<>();
		this.ofertas = new ArrayList<>();
		this.contraOfertas = new ArrayList<>();
		
	}

	public double getSaldoVirtual() {
		return saldoVirtual;
	}

	public void setSaldoVirtual(double saldoVirtual) {
		this.saldoVirtual = saldoVirtual;
	}

	public List<Tiquete> getMisTiquetes() {
		return misTiquetes;
	}

	public void setMisTiquetes(List<Tiquete> misTiquetes) {
		this.misTiquetes = misTiquetes;
	}

	public List<Transaccion> getHistorialFinanciero() {
		return historialFinanciero;
	}

	public void setHistorialFinanciero(List<Transaccion> historialFinanciero) {
		this.historialFinanciero = historialFinanciero;
	}

	public List<Item> getCarrito() {
		return carritoCompras;
	}
	
	public void agregarAlCarrito(Item item) {
	    carritoCompras.add(item);
	}

	public void removerDelCarrito(Item item) {
	    carritoCompras.remove(item);
	}

	public void vaciarCarrito() {
	    carritoCompras.clear();
	}

	public List<Oferta> getOfertas() {
		return ofertas;
	}

	public void setOfertas(List<Oferta> ofertas) {
		this.ofertas = ofertas;
	}

	public List<ContraOferta> getContraOfertas() {
		return contraOfertas;
	}

	public void setContraOfertas(List<ContraOferta> contraOfertas) {
		this.contraOfertas = contraOfertas;
	}
	
	public void agregarTiquete(Tiquete t) {
		misTiquetes.add(t);	
	}

	public void eliminarTiquete(Tiquete t) {
		misTiquetes.remove(t);
	}

	public void agregarHistorialFinanciero(Transaccion tran) {
		historialFinanciero.add(tran);		
	}
	
	

}