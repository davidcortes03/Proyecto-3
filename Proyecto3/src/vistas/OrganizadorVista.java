package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import controladores.MainController;
import modelo.Evento;
import modelo.Localidad;
import modelo.Venue;
import usuarios.Organizador;

public class OrganizadorVista extends JPanel {

	private final MainController mainController;
    private final AplicacionFrame frame;
    private Organizador organizador;

    private final DefaultListModel<Evento> modeloEventos;
    private final DefaultListModel<Localidad> modeloLocalidades;
    private final JLabel lblTitulo;

    public OrganizadorVista(MainController mainController, AplicacionFrame frame) {
        this.mainController = mainController;
        this.frame = frame;

        setLayout(new BorderLayout());
        setBackground(new Color(250, 252, 255));
        setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        lblTitulo = new JLabel("Panel Organizador", JLabel.CENTER);
        lblTitulo.setOpaque(true);
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBackground(new Color(243, 156, 18));
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD, 18f));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        add(lblTitulo, BorderLayout.NORTH);

        modeloEventos = new DefaultListModel<>();
        JList<Evento> listaEventos = new JList<>(modeloEventos);
        listaEventos.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
            JLabel label = new JLabel(value.getNombre() + " - " + value.getFechaHora() + " | " + value.getEstado());
            if ("PENDIENTE".equals(value.getEstado())) {
                label.setForeground(new Color(255, 152, 0));
            }
            if (isSelected) {
                label.setOpaque(true);
                label.setBackground(new Color(255, 243, 224));
            }
            return label;
        });
        add(new JScrollPane(listaEventos), BorderLayout.CENTER);

        modeloLocalidades = new DefaultListModel<>();

        JPanel crearPanel = new JPanel();
        crearPanel.setBackground(new Color(250, 252, 255));
        crearPanel.setBorder(BorderFactory.createTitledBorder("Nuevo evento"));
        JTextField txtNombre = new JTextField(10);
        JTextField txtTipo = new JTextField(8);
        JTextField txtVenue = new JTextField(8);
        JTextField txtCapacidad = new JTextField(4);
        JTextField txtPrecio = new JTextField(6);
        JTextField txtLocalidadNombre = new JTextField(8);
        JTextField txtLocalidadCapacidad = new JTextField(4);
        JTextField txtLocalidadPrecio = new JTextField(6);
        JButton btnCrear = new JButton("Solicitar evento");
        JButton btnVenue = new JButton("Solicitar venue");
        JButton btnAgregarLoc = new JButton("Agregar localidad");
        JButton btnSalir = new JButton("Cerrar sesión");
        crearPanel.add(new JLabel("Nombre:"));
        crearPanel.add(txtNombre);
        crearPanel.add(new JLabel("Tipo:"));
        crearPanel.add(txtTipo);
        crearPanel.add(new JLabel("Venue:"));
        crearPanel.add(txtVenue);
        crearPanel.add(new JLabel("Capacidad total:"));
        crearPanel.add(txtCapacidad);
        crearPanel.add(new JLabel("Precio general:"));
        crearPanel.add(txtPrecio);
        crearPanel.add(btnCrear);
        crearPanel.add(btnVenue);
        crearPanel.add(btnSalir);

        JPanel localidadesPanel = new JPanel();
        localidadesPanel.setBackground(new Color(250, 252, 255));
        localidadesPanel.setBorder(BorderFactory.createTitledBorder("Localidades personalizadas"));
        localidadesPanel.add(new JLabel("Nombre:"));
        localidadesPanel.add(txtLocalidadNombre);
        localidadesPanel.add(new JLabel("Capacidad:"));
        localidadesPanel.add(txtLocalidadCapacidad);
        localidadesPanel.add(new JLabel("Precio:"));
        localidadesPanel.add(txtLocalidadPrecio);
        localidadesPanel.add(btnAgregarLoc);

        JList<Localidad> listaLoc = new JList<>(modeloLocalidades);
        listaLoc.setVisibleRowCount(4);
        listaLoc.setFixedCellWidth(260);
        listaLoc.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
            JLabel label = new JLabel(value.getNombre() + " | Cap: " + value.getCapacidad()
                    + " | $" + value.getPrecioConOSinDescuento());
            if (isSelected) {
                label.setOpaque(true);
                label.setBackground(new Color(255, 243, 224));
            }
            return label;
        });
        JScrollPane scrollLoc = new JScrollPane(listaLoc);
        scrollLoc.setBorder(BorderFactory.createTitledBorder("Localidades propuestas"));
        scrollLoc.setPreferredSize(new java.awt.Dimension(380, 120));

        JPanel sur = new JPanel(new BorderLayout());
        sur.setBackground(new Color(250, 252, 255));
        sur.add(crearPanel, BorderLayout.NORTH);
        sur.add(new JSeparator(), BorderLayout.CENTER);
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(new Color(250, 252, 255));
        wrapper.add(localidadesPanel, BorderLayout.NORTH);
        wrapper.add(scrollLoc, BorderLayout.CENTER);
        sur.add(wrapper, BorderLayout.SOUTH);

        add(sur, BorderLayout.SOUTH);

        btnAgregarLoc.addActionListener(e -> {
            String nombreLoc = txtLocalidadNombre.getText().trim();
            int capacidadLoc;
            double precioLoc;
            try {
                capacidadLoc = Integer.parseInt(txtLocalidadCapacidad.getText().trim());
            } catch (NumberFormatException ex) {
                capacidadLoc = 20;
            }
            try {
                precioLoc = Double.parseDouble(txtLocalidadPrecio.getText().trim());
            } catch (NumberFormatException ex) {
                precioLoc = 100.0;
            }
            if (nombreLoc.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nombra la localidad", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }
            Localidad nueva = new Localidad(nombreLoc, capacidadLoc, true, precioLoc);
            nueva.crearTodosLosAsientos();
            modeloLocalidades.addElement(nueva);
            txtLocalidadNombre.setText("");
            txtLocalidadCapacidad.setText("");
            txtLocalidadPrecio.setText("");
        });

        btnCrear.addActionListener(e -> {
            String nombre = txtNombre.getText().trim();
            String tipo = txtTipo.getText().trim();
            double precio;
            int capacidadVenue;
            try {
                precio = Double.parseDouble(txtPrecio.getText().trim());
            } catch (NumberFormatException ex) {
                precio = 120.0;
            }
            try {
                capacidadVenue = Integer.parseInt(txtCapacidad.getText().trim());
            } catch (NumberFormatException ex) {
                capacidadVenue = 100;
            }
            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Escribe un nombre", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }
            Venue venue = mainController.getVenueRepo().getFirstOrDefault(txtVenue.getText().trim(), capacidadVenue);
            venue.getLocalidades().clear();

            if (modeloLocalidades.isEmpty()) {
                Localidad general = new Localidad("General", capacidadVenue, true, precio);
                general.crearTodosLosAsientos();
                modeloLocalidades.addElement(general);
            }

            int capacidadTotal = 0;
            List<Localidad> copias = new ArrayList<>();
            for (int i = 0; i < modeloLocalidades.size(); i++) {
                Localidad loc = modeloLocalidades.get(i);
                Localidad copia = new Localidad(loc.getNombre(), loc.getCapacidad(), loc.isConAsientos(),
                        loc.getPrecioConOSinDescuento());
                copia.crearTodosLosAsientos();
                capacidadTotal += copia.getCapacidad();
                copias.add(copia);
            }
            venue.getLocalidades().addAll(copias);
            if (capacidadTotal > 0) {
                venue.setCapacidadMax(capacidadTotal);
            }
            Evento nuevo = mainController.getOrganizadorController().crearEvento(
                    organizador,
                    nombre,
                    tipo.isEmpty() ? "GENERAL" : tipo,
                    LocalDateTime.now().plusDays(7),
                    venue);
            JOptionPane.showMessageDialog(this,
                    "Evento enviado a aprobación. Estado actual: " + nuevo.getEstado(),
                    "Evento creado",
                    JOptionPane.INFORMATION_MESSAGE);
            cargarEventos();
            txtNombre.setText("");
            txtTipo.setText("");
            txtPrecio.setText("");
            txtVenue.setText("");
            txtCapacidad.setText("");
            modeloLocalidades.clear();
        });

        btnVenue.addActionListener(e -> {
            String nombre = txtVenue.getText().trim();
            int capacidad = 0;
            try {
                capacidad = Integer.parseInt(txtCapacidad.getText().trim());
            } catch (NumberFormatException ex) {
                capacidad = 80;
            }
            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Escribe el nombre del venue", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }
            Venue propuesto = new Venue(nombre, capacidad, "Por definir");
            mainController.getOrganizadorController().solicitarNuevoVenue(organizador, propuesto);
            JOptionPane.showMessageDialog(this, "Solicitud de venue enviada al administrador", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        });

        btnSalir.addActionListener(e -> frame.mostrarLogin());
    }

    public void setOrganizador(Organizador organizador) {
        this.organizador = organizador;
        lblTitulo.setText("Panel Organizador - " + organizador.getNombreUsuario());
        cargarEventos();
    }

    private void cargarEventos() {
        modeloEventos.clear();
        if (organizador == null) return;
        List<Evento> eventos = mainController.getEventoRepo().getEventos();
        for (Evento e : eventos) {
            if (e.getOrganizador().equals(organizador)) {
                modeloEventos.addElement(e);
            }
        }
    }
}
