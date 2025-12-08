package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
    private final JLabel lblBienvenida;

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
        lblBienvenida.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(lblBienvenida, BorderLayout.NORTH);

        modeloTiquetes = new DefaultListModel<>();
        JList<Tiquete> lista = new JList<>(modeloTiquetes);
        lista.setCellRenderer((l, value, i, s, f) -> new JLabel(
                value.getEvento().getNombre() + " | ID: " + value.getId() + " | Precio: $" + value.getPrecioBase()
        ));
        JScrollPane scroll = new JScrollPane(lista);
        scroll.setPreferredSize(new Dimension(480, 260));
        scroll.setBorder(BorderFactory.createTitledBorder("Mis tiquetes"));
        add(scroll, BorderLayout.CENTER);

        modeloEventos = new DefaultListModel<>();
        JList<Evento> listaEventos = new JList<>(modeloEventos);
        listaEventos.setCellRenderer((l, value, i, s, f) -> new JLabel(
                value.getNombre() + " - " + value.getFechaHora())) ;
        JScrollPane scrollEventos = new JScrollPane(listaEventos);
        scrollEventos.setPreferredSize(new Dimension(480, 120));
        scrollEventos.setBorder(BorderFactory.createTitledBorder("Eventos disponibles"));
        add(scrollEventos, BorderLayout.NORTH);

        JButton btnImprimir = new JButton("Imprimir seleccionado");
        JButton btnRefrescar = new JButton("Actualizar lista");
        JButton btnComprar = new JButton("Comprar evento seleccionado");
        JButton btnSalir = new JButton("Cerrar sesión");
        JPanel acciones = new JPanel();
        acciones.setBackground(new Color(248, 250, 252));
        acciones.add(btnRefrescar);
        acciones.add(btnComprar);
        acciones.add(btnImprimir);
        acciones.add(btnSalir);
        add(acciones, BorderLayout.SOUTH);

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
            Evento seleccionado = listaEventos.getSelectedValue();
            if (seleccionado == null) {
                JOptionPane.showMessageDialog(this, "Elige un evento activo", "Información", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            Localidad localidad = seleccionado.getVenue().getLocalidades().isEmpty()
                    ? null
                    : seleccionado.getVenue().getLocalidades().get(0);
            if (localidad == null) {
                localidad = new Localidad("General", 20, true, 120.0);
                localidad.crearTodosLosAsientos();
                seleccionado.getVenue().getLocalidades().add(localidad);
            }
            Asiento asiento = localidad.getAsientos().isEmpty() ? new Asiento(1) : localidad.getAsientos().get(0);
            Tiquete nuevo = new Tiquete(localidad.getPrecioConOSinDescuento(), false, seleccionado, localidad, asiento);
            nuevo.setEstadoCompra("COMPRADA");
            cliente.agregarTiquete(nuevo);
            mainController.getTiqueteRepo().agregar(nuevo);
            cargarTiquetes();
            JOptionPane.showMessageDialog(this, "Tiquete añadido a tu cuenta", "Compra exitosa", JOptionPane.INFORMATION_MESSAGE);
        });

        btnSalir.addActionListener(e -> frame.mostrarLogin());
    }

    public void setCliente(ClienteNatural cliente) {
        this.cliente = cliente;
        lblBienvenida.setText("Panel Cliente - " + cliente.getNombreUsuario());
        cargarTiquetes();
        cargarEventosDisponibles();
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
    }
}
