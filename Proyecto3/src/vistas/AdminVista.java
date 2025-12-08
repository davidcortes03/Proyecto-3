package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionListener;

import controladores.MainController;
import modelo.Evento;
import solicitudes.SolicitudVenue;
import usuarios.Administrador;

public class AdminVista extends JPanel {

	private final MainController mainController;
    private final AplicacionFrame frame;

    private Administrador admin;
    private final JLabel lblResumen;
    private final JLabel lblBienvenida;
    private final JList<Evento> listaPendientes;
    private final JList<SolicitudVenue> listaVenue;
    private final JLabel lblDetalleEvento;

    public AdminVista(MainController mainController, AplicacionFrame frame) {
        this.mainController = mainController;
        this.frame = frame;

        setLayout(new BorderLayout());
        setBackground(new Color(247, 250, 252));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        lblBienvenida = new JLabel("Panel Administrador", SwingConstants.CENTER);
        lblBienvenida.setOpaque(true);
        lblBienvenida.setBackground(new Color(76, 175, 80));
        lblBienvenida.setForeground(Color.WHITE);
        lblBienvenida.setFont(lblBienvenida.getFont().deriveFont(Font.BOLD, 18f));
        lblBienvenida.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        lblResumen = new JLabel("", SwingConstants.CENTER);
        lblResumen.setFont(lblResumen.getFont().deriveFont(Font.PLAIN, 15f));
        lblResumen.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel cabecera = new JPanel(new BorderLayout());
        cabecera.setBackground(new Color(247, 250, 252));
        cabecera.add(lblBienvenida, BorderLayout.NORTH);
        cabecera.add(lblResumen, BorderLayout.SOUTH);
        add(cabecera, BorderLayout.NORTH);

        lblDetalleEvento = new JLabel("Selecciona un evento para ver sus localidades", SwingConstants.CENTER);
        lblDetalleEvento.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        lblDetalleEvento.setOpaque(true);
        lblDetalleEvento.setBackground(new Color(235, 245, 238));

        listaPendientes = new JList<>();
        listaPendientes.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
            double precio = value.getVenue().getLocalidades().isEmpty()
                    ? 0.0
                    : value.getVenue().getLocalidades().get(0).getPrecioConOSinDescuento();
            JLabel label = new JLabel(value.getNombre() + " | " + value.getFechaHora()
                    + " | Precio sugerido: $" + precio + " | Estado: " + value.getEstado());
            if (isSelected) {
                label.setOpaque(true);
                label.setBackground(new Color(230, 245, 233));
            }
            return label;
        });

        ListSelectionListener listener = e -> {
            Evento seleccionado = listaPendientes.getSelectedValue();
            if (seleccionado == null) {
                lblDetalleEvento.setText("Selecciona un evento para ver sus localidades");
                return;
            }
            StringBuilder builder = new StringBuilder("Localidades: ");
            seleccionado.getVenue().getLocalidades().forEach(loc -> builder.append(loc.getNombre())
                    .append(" ($").append(loc.getPrecioConOSinDescuento())
                    .append(", cupos ").append(loc.getCapacidadDisponible())
                    .append(")  "));
            lblDetalleEvento.setText(builder.toString());
        };
        listaPendientes.addListSelectionListener(listener);
        JScrollPane scrollEventos = new JScrollPane(listaPendientes);
        scrollEventos.setBorder(BorderFactory.createTitledBorder("Eventos por aprobar"));

        listaVenue = new JList<>();
        JScrollPane scrollVenue = new JScrollPane(listaVenue);
        scrollVenue.setBorder(BorderFactory.createTitledBorder("Solicitudes de venue"));

        JPanel centro = new JPanel(new java.awt.GridLayout(1, 2, 12, 0));
        centro.setBackground(new Color(247, 250, 252));
        centro.add(scrollEventos);
        centro.add(scrollVenue);
        add(centro, BorderLayout.CENTER);

        JButton btnAprobar = new JButton("Aprobar evento");
        JButton btnRechazar = new JButton("Rechazar evento");
        JButton btnAprobarVenue = new JButton("Aprobar venue");
        JButton btnRechazarVenue = new JButton("Rechazar venue");
        JButton btnSalir = new JButton("Cerrar sesión");

        btnSalir.addActionListener(e -> frame.mostrarLogin());
        btnAprobar.addActionListener(e -> {
            Evento seleccionado = listaPendientes.getSelectedValue();
            if (seleccionado != null) {
                mainController.getAdminController().aprobarEvento(admin, seleccionado);
                refrescarTablas(admin);
            }
        });
        btnRechazar.addActionListener(e -> {
            Evento seleccionado = listaPendientes.getSelectedValue();
            if (seleccionado != null) {
                mainController.getAdminController().rechazarEvento(admin, seleccionado);
                refrescarTablas(admin);
            }
        });
        btnAprobarVenue.addActionListener(e -> {
            SolicitudVenue solicitud = listaVenue.getSelectedValue();
            if (solicitud != null) {
                mainController.getServicioSolicitudes().aprobarVenue(admin, solicitud);
                refrescarTablas(admin);
            }
        });
        btnRechazarVenue.addActionListener(e -> {
            SolicitudVenue solicitud = listaVenue.getSelectedValue();
            if (solicitud != null) {
                mainController.getServicioSolicitudes().rechazarVenue(admin, solicitud, "Rechazado por el administrador");
                refrescarTablas(admin);
            }
        });

        JPanel pie = new JPanel();
        pie.setBackground(new Color(247, 250, 252));
        pie.add(btnAprobar);
        pie.add(btnRechazar);
        pie.add(btnAprobarVenue);
        pie.add(btnRechazarVenue);
        pie.add(btnSalir);

        JPanel pieWrapper = new JPanel(new BorderLayout());
        pieWrapper.setBackground(new Color(247, 250, 252));
        pieWrapper.add(lblDetalleEvento, BorderLayout.NORTH);
        pieWrapper.add(pie, BorderLayout.SOUTH);
        add(pieWrapper, BorderLayout.SOUTH);
    }

    public void setAdministrador(Administrador admin) {
        lblBienvenida.setText("Panel Administrador - " + admin.getNombreUsuario());
        refrescarTablas(admin);
    }

    private void refrescarTablas(Administrador admin) {
        lblResumen.setText("Usuarios: " + mainController.getUsuarioRepo().getUsuarios().size()
                + " | Eventos: " + mainController.getEventoRepo().getEventos().size()
                + " | Tiquetes: " + mainController.getTiqueteRepo().getTiquetes().size());

        List<Evento> pendientes = mainController.getAdminController().obtenerEventosPendientes();
        listaPendientes.setListData(pendientes.toArray(new Evento[0]));

        List<SolicitudVenue> venues = mainController.getServicioSolicitudes().obtenerVenuesPendientes();
        listaVenue.setListData(venues.toArray(new SolicitudVenue[0]));

        this.admin = admin;
    }
}
