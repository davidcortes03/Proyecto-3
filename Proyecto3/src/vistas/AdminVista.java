package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controladores.MainController;
import modelo.Evento;
import usuarios.Administrador;

public class AdminVista extends JPanel {

	private final MainController mainController;
    private final AplicacionFrame frame;

    private final JLabel lblResumen;
    private final JLabel lblBienvenida;

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
        add(lblBienvenida, BorderLayout.NORTH);

        lblResumen = new JLabel("", SwingConstants.CENTER);
        lblResumen.setFont(lblResumen.getFont().deriveFont(Font.PLAIN, 15f));
        lblResumen.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10));
        add(lblResumen, BorderLayout.CENTER);

        JButton btnSalir = new JButton("Cerrar sesión");
        btnSalir.addActionListener(e -> frame.mostrarLogin());
        JPanel pie = new JPanel();
        pie.setBackground(new Color(247, 250, 252));
        pie.add(btnSalir);
        add(pie, BorderLayout.SOUTH);
    }

    public void setAdministrador(Administrador admin) {
        lblBienvenida.setText("Panel Administrador - " + admin.getNombreUsuario());
        lblResumen.setText("Usuarios: " + mainController.getUsuarioRepo().getUsuarios().size()
                + " | Eventos: " + mainController.getEventoRepo().getEventos()
                + " | Tiquetes: " + mainController.getTiqueteRepo().getTiquetes());
    }
}
