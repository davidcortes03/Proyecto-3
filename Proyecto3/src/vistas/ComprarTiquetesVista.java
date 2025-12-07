package vistas;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelo.*;
import servicios.ServicioCompras;
import tiquetesCompra.*;
import usuarios.ClienteNatural;
import persistencias.SistemaBoletaMaster;

public class ComprarTiquetesVista extends JFrame {
	
	private SistemaBoletaMaster sistema;
	private ClienteNatural comprador;
	private ServicioCompras servicioCompras;
	
	private JComboBox<String> comboEventos;
	private JComboBox<String> comboLocalidades;
	private JComboBox<String> comboAsientos;
	private JLabel lblPrecioBase;
	
	private JTextField txtCantidad;
	
	public ComprarTiquetesVista(SistemaBoletaMaster sistema, ServicioCompras servicioCompras, ClienteNatural cliente) {
		this.sistema = sistema;
		this.servicioCompras = servicioCompras;
		this.comprador = cliente;
		
		
		JPanel panel = new JPanel(new GridLayout(3,2,10,10));
		
		panel.add(new JLabel("Evento: "));
		comboEventos = new JComboBox<>();
		panel.add(comboEventos);
		
		panel.add(new JLabel("Localidad: "));
		comboLocalidades = new JComboBox<>();
		add(comboLocalidades);
		
		panel.add(new JLabel("Asiento: "));
		comboAsientos = new JComboBox<>();
		add(comboAsientos);
		
		panel.add(new JLabel("Precio base: "));
		lblPrecioBase = new JLabel("-");
		add(lblPrecioBase);
		
		panel.add(new JLabel("Cantidad a comprar: "));
		txtCantidad = new JTextField();
		panel.add(txtCantidad);
		
		add(panel, BorderLayout.CENTER);
		
		JButton btnComprar = new JButton("Comprar");
		add(new JLabel());
		add(btnComprar);
		
		cargarEventos();
		
		comboEventos.addActionListener(e -> cargarLocalidades());
		comboLocalidades.addActionListener(e -> cargarAsientos());
		
		btnComprar.addActionListener( e -> procesarCompra());
		
		setVisible(true);
		
	}
	
	private void cargarEventos() {
		comboEventos.removeAllItems();
		List<Evento> eventos = sistema.getRepoEventos();
		
		for (Evento e: eventos) {
			comboEventos.addItem(e.getNombre());
		}
	}
	
	private Evento obtenerEventoSeleccionado() {
		
		String nombre = (String) comboEventos.getSelectedItem();
		if (nombre == null) {
			return null;
		}
		
		for (Evento e : sistema.getRepoEventos()) {
			if (e.getNombre().equals(nombre)) {
				return e;
			}
		}
		
		return null;
	}
	
	private void cargarLocalidades() {
		
		comboLocalidades.removeAllItems();
		
		Evento evento = obtenerEventoSeleccionado();
		if (evento == null) return;
		
		List<Localidad> locs = evento.getVenue().getLocalidades();
		for (Localidad loc: locs) {
			comboLocalidades.addItem(loc.getNombre());
		}
	}
	
	private Localidad obtenerLocalidadSeleccionada(Evento evento) {
		
		String nombre = (String) comboLocalidades.getSelectedItem();
		if (nombre == null) {
			return null;
		}
		
		for (Localidad loc : evento.getVenue().getLocalidades()) {
			if (loc.getNombre().equals(nombre)) {
				return loc;
			}
		}
		
		return null;
	}
	
	private void cargarAsientos() {
		
		comboAsientos.removeAllItems();
		
		Evento evento = obtenerEventoSeleccionado();
		if (evento == null) return;
		
		Localidad loc = obtenerLocalidadSeleccionada(evento);
		if (loc == null) return;
		
		lblPrecioBase.setText(loc.getPrecioConOSinDescuento() + "");
		
		for (Asiento a: loc.AsientosDisponiblesLista()) {
			comboAsientos.addItem(String.valueOf(a.getNumero()));
		}
		
	}

	
	private void procesarCompra() {
		
		Evento evento = obtenerEventoSeleccionado();
		if (evento == null ) {
			JOptionPane.showMessageDialog(this,  "No se encontró el evento");
			return;
		}
		
		int cantidad;
		try {
			cantidad = Integer.parseInt(txtCantidad.getText());
			if (cantidad <= 0) throw new NumberFormatException();
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this,  "Cantidad inválida");
			return;
		}
		  
		Localidad localidad = obtenerLocalidadSeleccionada(evento);
		if (localidad == null) {
			JOptionPane.showMessageDialog(this,  "Seleccione una localidad");
			return;
		}
		
		List<Asiento> disponibles = localidad.AsientosDisponiblesLista();
		if (disponibles.size() < cantidad) {
			JOptionPane.showMessageDialog(this,  "No hay asientos disponibles");
			return;
		}
		
		double precioBase = localidad.getPrecioConOSinDescuento();
		
		for (int i = 0; i < cantidad; i++) {
			Asiento asiento = disponibles.get(i);
			
			Tiquete t = new Tiquete(precioBase, false, evento,localidad, asiento);

			t.setEstadoCompra("COMPRADO");
			t.setPrecioPagar(precioBase);
			
			asiento.setEstado("OCUPADO");
			
			comprador.getCarrito().add(t);
		
		}
		
		servicioCompras.comprarCarrito(comprador, "PASARELA");
		
		JOptionPane.showMessageDialog(this,  "Compra realizada con éxito: " + cantidad + "tiquete(s)");
	}

}
