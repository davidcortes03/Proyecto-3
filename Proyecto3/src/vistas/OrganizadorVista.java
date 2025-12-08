package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.time.LocalDateTime;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import controladores.MainController;
import modelo.Evento;
import modelo.Venue;
import usuarios.Organizador;

public class OrganizadorVista extends JPanel {

	private final MainController mainController;
    private final AplicacionFrame frame;
    private Organizador organizador;

    private final DefaultListModel<Evento> modeloEventos;
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
        lblTitulo.setBackground(new Color(255, 152, 0));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        add(lblTitulo, BorderLayout.NORTH);

        modeloEventos = new DefaultListModel<>();
        JList<Evento> listaEventos = new JList<>(modeloEventos);
        listaEventos.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
            JLabel label = new JLabel(value.getNombre() + " - " + value.getFechaHora() + " | " + value.getEstado());
            if ("PENDIENTE".equals(value.getEstado())) {
                label.setForeground(new Color(255, 152, 0));
            }
            return label;
        });
        add(new JScrollPane(listaEventos), BorderLayout.CENTER);

        JPanel crearPanel = new JPanel();
        crearPanel.setBackground(new Color(250, 252, 255));
        crearPanel.setBorder(BorderFactory.createTitledBorder("Nuevo evento"));
        JTextField txtNombre = new JTextField(10);
        JTextField txtTipo = new JTextField(8);
        JTextField txtVenue = new JTextField(8);
        JTextField txtCapacidad = new JTextField(4);
        JButton btnCrear = new JButton("Solicitar evento");
        JButton btnVenue = new JButton("Solicitar venue");
        JButton btnSalir = new JButton("Cerrar sesión");
        crearPanel.add(new JLabel("Nombre:"));
        crearPanel.add(txtNombre);
        crearPanel.add(new JLabel("Tipo:"));
        crearPanel.add(txtTipo);
        crearPanel.add(new JLabel("Venue:"));
        crearPanel.add(txtVenue);
        crearPanel.add(new JLabel("Capacidad:"));
        crearPanel.add(txtCapacidad);
        crearPanel.add(btnCrear);
        crearPanel.add(btnVenue);
        crearPanel.add(btnSalir);
        add(crearPanel, BorderLayout.SOUTH);

        btnCrear.addActionListener(e -> {
            String nombre = txtNombre.getText().trim();
            String tipo = txtTipo.getText().trim();
            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Escribe un nombre", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }
            Venue venue = mainController.getVenueRepo().getFirstOrDefault(txtVenue.getText().trim(), 80);
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
