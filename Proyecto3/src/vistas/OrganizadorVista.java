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
        listaEventos.setCellRenderer((list, value, index, isSelected, cellHasFocus) ->
                new JLabel(value.getNombre() + " - " + value.getFechaHora()));
        add(new JScrollPane(listaEventos), BorderLayout.CENTER);

        JPanel crearPanel = new JPanel();
        crearPanel.setBackground(new Color(250, 252, 255));
        crearPanel.setBorder(BorderFactory.createTitledBorder("Nuevo evento demo"));
        JTextField txtNombre = new JTextField(10);
        JTextField txtTipo = new JTextField(8);
        JButton btnCrear = new JButton("Crear");
        JButton btnSalir = new JButton("Cerrar sesión");
        crearPanel.add(new JLabel("Nombre:"));
        crearPanel.add(txtNombre);
        crearPanel.add(new JLabel("Tipo:"));
        crearPanel.add(txtTipo);
        crearPanel.add(btnCrear);
        crearPanel.add(btnSalir);
        add(crearPanel, BorderLayout.SOUTH);

        btnCrear.addActionListener(e -> {
            String nombre = txtNombre.getText().trim();
            String tipo = txtTipo.getText().trim();
            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Escribe un nombre", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }
            Venue venue = new Venue("Venue " + (mainController.getEventoRepo().getEventos().size() + 1), 50, "Por definir");
            Evento nuevo = new Evento(nombre, venue, LocalDateTime.now().plusDays(7), tipo.isEmpty() ? "GENERAL" : tipo, organizador);
            mainController.getEventoRepo().agregar(nuevo);
            cargarEventos();
            txtNombre.setText("");
            txtTipo.setText("");
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
