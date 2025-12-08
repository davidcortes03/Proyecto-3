package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controladores.MainController;
import modelo.Asiento;
import modelo.Evento;
import modelo.Localidad;
import tiquetesCompra.Tiquete;
import usuarios.ClienteNatural;

public class ClienteVista extends JPanel {
	private final MainController mainController;
    private final AplicacionFrame frame;

    private ClienteNatural cliente;
    private final DefaultListModel<Tiquete> modeloTiquetes;
    private final DefaultListModel<Evento> modeloEventos;
    private final DefaultListModel<Localidad> modeloLocalidades;
    private final JLabel lblBienvenida;
    private final JLabel lblSaldo;
    private final JLabel lblEventoSeleccionado;

    public ClienteVista(MainController mainController, AplicacionFrame frame) {
        this.mainController = mainController;
        this.frame = frame;

        setLayout(new BorderLayout());
        setBackground(new Color(248, 250, 252));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        lblBienvenida = new JLabel("Panel Cliente", JLabel.CENTER);
        lblBienvenida.setOpaque(true);
        lblBienvenida.setBackground(new Color(63, 81, 181));
        lblBienvenida.setForeground(Color.WHITE);
        lblBienvenida.setFont(lblBienvenida.getFont().deriveFont(Font.BOLD, 18f));
        lblBienvenida.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(lblBienvenida, BorderLayout.NORTH);

        lblSaldo = new JLabel("Saldo: $0", JLabel.CENTER);
        lblSaldo.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220)));
        lblSaldo.setOpaque(true);
        lblSaldo.setBackground(new Color(232, 235, 246));
        lblSaldo.setFont(lblSaldo.getFont().deriveFont(Font.BOLD));

        modeloTiquetes = new DefaultListModel<>();
        JList<Tiquete> lista = new JList<>(modeloTiquetes);
        lista.setCellRenderer((l, value, i, s, f) -> {
            JLabel label = new JLabel(
                    value.getEvento().getNombre() + " | ID: " + value.getId() + " | Precio: $" + value.getPrecioBase()
                            + (value.getImpreso() ? " (impreso)" : ""));
            if (s) {
                label.setOpaque(true);
                label.setBackground(new Color(227, 242, 253));
            }
            return label;
        });
        JScrollPane scroll = new JScrollPane(lista);
        scroll.setPreferredSize(new Dimension(480, 200));
        scroll.setBorder(BorderFactory.createTitledBorder("Mis tiquetes"));

        modeloEventos = new DefaultListModel<>();
        JList<Evento> listaEventos = new JList<>(modeloEventos);
        listaEventos.setCellRenderer((l, value, i, s, f) -> {
            double precio = value.getVenue().getLocalidades().isEmpty() ? 0.0
                    : value.getVenue().getLocalidades().get(0).getPrecioConOSinDescuento();
            JLabel label = new JLabel(value.getNombre() + " - " + value.getFechaHora() + " | Desde $" + precio);
            if (s) {
                label.setOpaque(true);
                label.setBackground(new Color(232, 245, 233));
            }
            return label;
        });
        JScrollPane scrollEventos = new JScrollPane(listaEventos);
        scrollEventos.setPreferredSize(new Dimension(480, 120));
        scrollEventos.setBorder(BorderFactory.createTitledBorder("Eventos disponibles"));

        modeloLocalidades = new DefaultListModel<>();
        JList<Localidad> listaLocalidades = new JList<>(modeloLocalidades);
        listaLocalidades.setCellRenderer((l, value, i, s, f) -> {
            JLabel label = new JLabel(value.getNombre() + " | $" + value.getPrecioConOSinDescuento()
                    + " | disponibles: " + value.getCapacidadDisponible());
            if (s) {
                label.setOpaque(true);
                label.setBackground(new Color(252, 242, 248));
            }
            return label;
        });
        JScrollPane scrollLocalidades = new JScrollPane(listaLocalidades);
        scrollLocalidades.setPreferredSize(new Dimension(320, 120));
        scrollLocalidades.setBorder(BorderFactory.createTitledBorder("Localidades y cupos"));

        lblEventoSeleccionado = new JLabel("Selecciona un evento para ver localidades", JLabel.CENTER);
        lblEventoSeleccionado.setOpaque(true);
        lblEventoSeleccionado.setBackground(new Color(239, 244, 249));
        lblEventoSeleccionado.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));

        JPanel centro = new JPanel(new BorderLayout());
        centro.setBackground(new Color(248, 250, 252));
        centro.add(scrollEventos, BorderLayout.NORTH);
        centro.add(lblEventoSeleccionado, BorderLayout.CENTER);
        centro.add(scrollLocalidades, BorderLayout.SOUTH);
        add(centro, BorderLayout.NORTH);

        add(scroll, BorderLayout.CENTER);

        JButton btnImprimir = new JButton("Imprimir seleccionado");
        JButton btnRefrescar = new JButton("Actualizar lista");
        JButton btnComprar = new JButton("Comprar localidad seleccionada");
        JButton btnVender = new JButton("Vender en marketplace");
        JButton btnMarketplace = new JButton("Ir al marketplace");
        JButton btnSalir = new JButton("Cerrar sesión");

        JPanel acciones = new JPanel();
        acciones.setBackground(new Color(248, 250, 252));
        acciones.add(lblSaldo);
        acciones.add(btnRefrescar);
        acciones.add(btnComprar);
        acciones.add(btnImprimir);
        acciones.add(btnVender);
        acciones.add(btnMarketplace);
        acciones.add(btnSalir);
        add(acciones, BorderLayout.SOUTH);

        listaEventos.addListSelectionListener(e -> {
            Evento seleccionado = listaEventos.getSelectedValue();
            cargarLocalidades(seleccionado);
        });

        btnRefrescar.addActionListener(e -> {
            cargarTiquetes();
            cargarEventosDisponibles();
        });

        btnImprimir.addActionListener(e -> {
            Tiquete seleccionado = lista.getSelectedValue();
            if (seleccionado == null) {
                JOptionPane.showMessageDialog(this, "Selecciona un tiquete", "Información", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            mainController.getImpresionController().imprimirTiquete(seleccionado);
        });

        btnComprar.addActionListener(e -> {
            Evento evento = listaEventos.getSelectedValue();
            Localidad localidad = listaLocalidades.getSelectedValue();
            if (evento == null || localidad == null) {
                JOptionPane.showMessageDialog(this, "Elige un evento y una localidad", "Información", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if (!localidad.hayDisponibiliadad()) {
                JOptionPane.showMessageDialog(this, "No quedan cupos en la localidad", "Sin cupos", JOptionPane.WARNING_MESSAGE);
                return;
            }
            double precio = localidad.getPrecioConOSinDescuento();
            if (cliente.getSaldoVirtual() < precio) {
                JOptionPane.showMessageDialog(this, "Saldo insuficiente. Precio: $" + precio, "Pago", JOptionPane.WARNING_MESSAGE);
                return;
            }
            List<Asiento> disponibles = localidad.AsientosDisponiblesLista();
            Asiento asiento;
            if (disponibles.isEmpty()) {
                asiento = new Asiento(localidad.getCapacidad() - localidad.getCapacidadDisponible() + 1);
                localidad.getAsientos().add(asiento);
            } else {
                asiento = disponibles.get(0);
            }
            asiento.setEstado("OCUPADO");
            Tiquete nuevo = new Tiquete(precio, false, evento, localidad, asiento);
            nuevo.setEstadoCompra("COMPRADA");
            cliente.setSaldoVirtual(cliente.getSaldoVirtual() - precio);
            localidad.reducirCapacidadDisponible();
            cliente.agregarTiquete(nuevo);
            mainController.getTiqueteRepo().agregar(nuevo);
            cargarTiquetes();
            actualizarSaldo();
            JOptionPane.showMessageDialog(this, "Tiquete añadido a tu cuenta por $" + precio, "Compra exitosa", JOptionPane.INFORMATION_MESSAGE);
        });

        btnVender.addActionListener(e -> {
            Tiquete seleccionado = lista.getSelectedValue();
            if (seleccionado == null) {
                JOptionPane.showMessageDialog(this, "Selecciona un tiquete para vender", "Marketplace", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if (seleccionado.getImpreso()) {
                JOptionPane.showMessageDialog(this, "Los tiquetes impresos no pueden ir a Marketplace", "Restricción", JOptionPane.WARNING_MESSAGE);
                return;
            }
            String precioStr = JOptionPane.showInputDialog(this, "Precio de oferta para el tiquete:", seleccionado.getPrecioBase());
            if (precioStr == null || precioStr.isBlank()) {
                return;
            }
            try {
                double precioOferta = Double.parseDouble(precioStr);
                mainController.getMarketplaceController().publicarOferta(cliente, seleccionado, precioOferta);
                JOptionPane.showMessageDialog(this, "Oferta publicada", "Marketplace", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Precio inválido", "Marketplace", JOptionPane.WARNING_MESSAGE);
            } catch (IllegalStateException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Marketplace", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnMarketplace.addActionListener(e -> {
            if (cliente == null) return;
            new MarketplaceVista(mainController.getMarketplaceController(), cliente, this::cargarTiquetes).setVisible(true);
        });

        btnSalir.addActionListener(e -> frame.mostrarLogin());
    }

    public void setCliente(ClienteNatural cliente) {
        this.cliente = cliente;
        lblBienvenida.setText("Panel Cliente - " + cliente.getNombreUsuario());
        cargarTiquetes();
        cargarEventosDisponibles();
        actualizarSaldo();
    }

    private void cargarTiquetes() {
        modeloTiquetes.clear();
        if (cliente == null) return;
        List<Tiquete> tiquetes = cliente.getMisTiquetes();
        for (Tiquete t : tiquetes) {
            modeloTiquetes.addElement(t);
        }
    }

    private void cargarEventosDisponibles() {
        modeloEventos.clear();
        List<Evento> activos = mainController.getEventoRepo().getEventosActivos();
        for (Evento evento : activos) {
            modeloEventos.addElement(evento);
        }
        lblEventoSeleccionado.setText("Selecciona un evento para ver localidades");
        modeloLocalidades.clear();
    }

    private void cargarLocalidades(Evento evento) {
        modeloLocalidades.clear();
        if (evento == null) {
            lblEventoSeleccionado.setText("Selecciona un evento para ver localidades");
            return;
        }
        lblEventoSeleccionado.setText("Localidades de " + evento.getNombre());
        for (Localidad loc : evento.getVenue().getLocalidades()) {
            modeloLocalidades.addElement(loc);
        }
    }

    private void actualizarSaldo() {
        if (cliente != null) {
            lblSaldo.setText("Saldo: $" + cliente.getSaldoVirtual());
        }
    }
}
