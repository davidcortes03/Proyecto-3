package vistas;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controladores.MarketplaceController;
import marketPlace.Oferta;
import usuarios.ClienteNatural;

public class MarketplaceVista extends JFrame{

	private MarketplaceController controller;
	private ClienteNatural usuario;
	private JTable tabla;
	
	public MarketplaceVista(MarketplaceController controller, ClienteNatural usuario) {
		this.controller = controller;
		this.usuario = usuario;
		
		setTitle("MarketPlace");
		setSize(700, 400);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		tabla = new JTable();
		refrescarOfertas();
		
		add(new JScrollPane(tabla), BorderLayout.CENTER);
		
		JPanel panelBotones = new JPanel();
		JButton btnComprar = new JButton("Comprar oferta");
		JButton btnContraOferta = new JButton("Hacer contraoferta");
		
		panelBotones.add(btnComprar);
		panelBotones.add(btnContraOferta);
		
		add(panelBotones, BorderLayout.SOUTH);
		
		btnComprar.addActionListener(e -> comprarOferta());
		
		btnComprar.addActionListener(e -> crearContraOferta());
		
		setVisible(true);
		
	}
	
	private void refrescarOfertas() {
		
		List<Oferta> ofertas = controller.obtenerOfertasDeCliente(usuario);
		
		DefaultTableModel modelo = new DefaultTableModel(
				new Object[] {"ID", "Precio", "Vendedor", "Estado"}, 0);
		
		for (Oferta o: ofertas) {
			modelo.addRow(new Object[] {
					o.getId(),
					o.getPrecioOferta(),
					o.getVendedor().getNombreUsuario(),
					o.getEstadoOf()
			});
		}
		
		tabla.setModel(modelo);
	}
	
	private Oferta getOfertaSeleccionada() {
		int fila = tabla.getSelectedRow();
		if (fila == -1) {
			JOptionPane.showMessageDialog(this,  "Selecciona una oferta");
			return null;
		}
		
		String idOferta = (String) tabla.getValueAt(fila,  0);
		
		for (Oferta o: controller.obtenerOfertasDeCliente(usuario)) {
			if (o.getId().equals(idOferta)) {
				return o;
			}
		}	
		return null;
	}               
	
	private void comprarOferta() {
		Oferta oferta = getOfertaSeleccionada();
		if (oferta == null) {
			return;
		}
		
		String metodo = JOptionPane.showInputDialog(null, "Método de pago: SALDO VIRTUAL o PASARELA");
		
		controller.comprarOferta(usuario, oferta, metodo);
		
		JOptionPane.showMessageDialog(this, "Compra realizada");
		refrescarOfertas();
	}
	
	private void crearContraOferta() {
		Oferta oferta = getOfertaSeleccionada();
		if (oferta == null) return;
		
		String precioStr = JOptionPane.showInputDialog("Nuevo precio: ");
		double precio = Double.parseDouble(precioStr);
		
		String metodo = JOptionPane.showInputDialog("Método de pago: ");
		
		controller.crearContraOferta(usuario, oferta, precio, metodo);
		
		JOptionPane.showMessageDialog(this,  "Contraoferta enviada");
	}
	
}
