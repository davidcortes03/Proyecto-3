package modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Localidad {
	
	private String nombre;
	private double precioLoc;
	private int capacidad;
	private List<Asiento> asientos;
	private boolean conAsientos;
	private double descuentoPorcentaje;
	private LocalDateTime descuentoDesde;
    private LocalDateTime descuentoHasta;
    private int capacidadDisponible;
	
    public Localidad(String nombre, int capacidad, boolean conAsientos, double precioLoc) {
    		
    		this.nombre = nombre;
    		this.precioLoc = precioLoc;
    		this.capacidad = capacidad;
    		this.conAsientos = conAsientos;
    		this.descuentoPorcentaje = 0.0;
    		this.descuentoDesde = null;
    		this.descuentoHasta = null;
    		this.asientos = new ArrayList<>();
    		this.capacidadDisponible = capacidad;
    		
    }

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public List<Asiento> getAsientos() {
		return asientos;
	}

	public void setAsientos(List<Asiento> asientos) {
		this.asientos = asientos;
	}

	public boolean isConAsientos() {
		return conAsientos;
	}

	public void setConAsientos(boolean conAsientos) {
		this.conAsientos = conAsientos;
	}

	public double getDescuentoPorcentaje() {
		return descuentoPorcentaje;
	}

	public void setDescuentoPorcentaje(double descuentoPorcentaje) {
		this.descuentoPorcentaje = descuentoPorcentaje;
	}

	public LocalDateTime getDescuentoDesde() {
		return descuentoDesde;
	}

	public void setDescuentoDesde(LocalDateTime descuentoDesde) {
		this.descuentoDesde = descuentoDesde;
	}

	public LocalDateTime getDescuentoHasta() {
		return descuentoHasta;
	}

	public void setDescuentoHasta(LocalDateTime descuentoHasta) {
		this.descuentoHasta = descuentoHasta;
	}

	public int getCapacidadDisponible() {
		return capacidadDisponible;
	}

	public void setCapacidadDisponible(int capacidadDisponible) {
		this.capacidadDisponible = capacidadDisponible;
	}
	
	/**
     * Obtiene el precio de la localidad, aplica el descuento si el descuento no está venicdo.
     * 
     * @return Precio actual de la localidad.
     */
    public double getPrecioConOSinDescuento() {
        double respuesta = precioLoc;
        if(descuentoPorcentaje != 0.0 && descuentoDesde != null && descuentoHasta != null &&
        		false == LocalDateTime.now().isBefore(descuentoDesde) && false == LocalDateTime.now().isAfter(descuentoHasta)) {
        		respuesta = precioLoc - (precioLoc * (descuentoPorcentaje/100));
        } else {
        		this.descuentoPorcentaje = 0.0;
        		this.descuentoDesde = null;
        		this.descuentoHasta = null;
        }
        return respuesta;
    }
    
    public void setPrecioConOSinDescuento(double descuentoNuevoPorcentaje, LocalDateTime finDescuentoFecha) {
    		this.descuentoPorcentaje = descuentoNuevoPorcentaje;
        this.descuentoDesde = LocalDateTime.now();
        this.descuentoHasta = finDescuentoFecha;
    }
	
	/*
	 * Agrega un asiento a la lista de asientos
	 */
	public void agregarAsiento(int numero) { 
		Asiento asiento = new Asiento(numero);
		asientos.add(asiento);
	}
	
	/*
	 * Crea los asientos cuando se crea la instancia y los mete en la lista de asientos.
	 */
	public void crearTodosLosAsientos() {
        if (conAsientos) {
            int capacidadVenue = getCapacidad();
            for (int i = 1; i <= capacidadVenue; i++) {
                agregarAsiento(i);
            }
        }
	}
	
	/*
	 * Entrega la lista de asientos disponibles en la localidad.
	 */
	public List<Asiento> AsientosDisponiblesLista() {
        List<Asiento> asientosDisponibles = new ArrayList<>();
        for(Asiento a: asientos) {
                if("DESOCUPADO".equals(a.getEstado())) {
                        asientosDisponibles.add(a);
                }
        }
        return asientosDisponibles;
	}
	
	/*
	 * Verifica que haya disponibilidad en la localidad.
	 */
	public boolean hayDisponibiliadad() {
		boolean respuesta = true;
		if(capacidadDisponible == 0) {
			respuesta = false;
		}
		return respuesta;
	}
	
	/*
	 * Reduce la capacidadDisponible de la localidad en 1 por cada boleta comprada.
	 */
	public void reducirCapacidadDisponible() {
        int nuevaCapacidad = (capacidadDisponible - 1);
        setCapacidadDisponible(nuevaCapacidad);
}
	
	/*
	 * Aumenta la capacidadaDisponible de la localidad en 1 por cada boleta reembolsada.
	 */
	public void agregarCapacidadDisponible() {
		if(capacidad > capacidadDisponible) {
			int nuevaCapacidad = (capacidadDisponible + 1);
			setCapacidadDisponible(nuevaCapacidad);
		}
	}
	  
    
}
