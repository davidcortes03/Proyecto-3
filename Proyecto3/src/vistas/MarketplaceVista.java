package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import controladores.MarketplaceController;
import marketPlace.Oferta;
import usuarios.ClienteNatural;

public class MarketplaceVista extends JFrame{

	private final MarketplaceController controller;
    private final ClienteNatural usuario;
    private final JTable tabla;
    private final Runnable callbackRefrescar;

    public MarketplaceVista(MarketplaceController controller, ClienteNatural usuario, Runnable refrescarCallback) {
            this.controller = controller;
            this.usuario = usuario;
            this.callbackRefrescar = refrescarCallback == null ? () -> {} : refrescarCallback;

            setTitle("MarketPlace");
            setSize(780, 420);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout());
            getContentPane().setBackground(new Color(245, 247, 250));

            JLabel titulo = new JLabel("Marketplace activo", JLabel.CENTER);
            titulo.setOpaque(true);
            titulo.setBackground(new Color(30, 136, 229));
            titulo.setForeground(Color.WHITE);
            titulo.setFont(titulo.getFont().deriveFont(Font.BOLD, 17f));
            titulo.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
            add(titulo, BorderLayout.NORTH);

            tabla = new JTable();
            tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            refrescarOfertas();

            JScrollPane scroll = new JScrollPane(tabla);
            scroll.setPreferredSize(new Dimension(760, 260));
            scroll.setBorder(BorderFactory.createTitledBorder("Ofertas activas"));
            add(scroll, BorderLayout.CENTER);

            JPanel panelBotones = new JPanel();
            panelBotones.setBackground(new Color(245, 247, 250));
            JButton btnComprar = new JButton("Comprar oferta");
            JButton btnContraOferta = new JButton("Hacer contraoferta");
            JButton btnActualizar = new JButton("Actualizar");

            panelBotones.add(btnActualizar);
            panelBotones.add(btnComprar);
            panelBotones.add(btnContraOferta);

            add(panelBotones, BorderLayout.SOUTH);

            btnActualizar.addActionListener(e -> refrescarOfertas());
            btnComprar.addActionListener(e -> comprarOferta());
            btnContraOferta.addActionListener(e -> crearContraOferta());
    }

    private void refrescarOfertas() {
            List<Oferta> ofertas = controller.obtenerOfertasDisponibles(usuario);

            DefaultTableModel modelo = new DefaultTableModel(
                            new Object[] {"ID", "Evento", "Localidad", "Precio", "Vendedor"}, 0) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                            return false;
                    }
            };

            for (Oferta o: ofertas) {
                    modelo.addRow(new Object[] {
                                    o.getId(),
                                    o.getTiquete().getEvento().getNombre(),
                                    o.getTiquete().getLocalidad().getNombre(),
                                    o.getPrecioOferta(),
                                    o.getVendedor().getNombreUsuario()
                    });
            }

            tabla.setModel(modelo);
    }

    private Oferta getOfertaSeleccionada() {
            int fila = tabla.getSelectedRow();
            if (fila == -1) {
                    JOptionPane.showMessageDialog(this,  "Selecciona una oferta activa");
                    return null;
            }

            String idOferta = (String) tabla.getValueAt(fila,  0);

            for (Oferta o: controller.obtenerOfertasDisponibles(usuario)) {
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

            String metodo = JOptionPane.showInputDialog(null, "Método de pago: SALDO VIRTUAL o PASARELA", "SALDO VIRTUAL");
            if (metodo == null || metodo.isBlank()) return;

            try {
                    controller.comprarOferta(usuario, oferta, metodo.toUpperCase());
                    JOptionPane.showMessageDialog(this, "Compra realizada");
                    refrescarOfertas();
                    callbackRefrescar.run();
            } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de compra", JOptionPane.WARNING_MESSAGE);
            }
    }

    private void crearContraOferta() {
            Oferta oferta = getOfertaSeleccionada();
            if (oferta == null) return;

            String precioStr = JOptionPane.showInputDialog(this, "Nuevo precio: ", oferta.getPrecioOferta());
            if (precioStr == null || precioStr.isBlank()) return;
            double precio;
            try {
                    precio = Double.parseDouble(precioStr);
            } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Precio inválido", "Contraoferta", JOptionPane.WARNING_MESSAGE);
                    return;
            }

            String metodo = JOptionPane.showInputDialog(this, "Método de pago: SALDO VIRTUAL o PASARELA", "SALDO VIRTUAL");
            if (metodo == null || metodo.isBlank()) return;

            controller.crearContraOferta(usuario, oferta, precio, metodo.toUpperCase());

            JOptionPane.showMessageDialog(this,  "Contraoferta enviada");
    }
	
}
