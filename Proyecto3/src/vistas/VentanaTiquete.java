package vistas;

import javax.swing.*;

import tiquetesCompra.Tiquete;

import java.awt.*;

public class VentanaTiquete extends JFrame {
	
	public VentanaTiquete(Tiquete tiquete, String rutaQR) {
        setTitle("Tiquete " + tiquete.getId());
        setSize(380, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Evento: " + tiquete.getEvento().getNombre()));
        panel.add(new JLabel("Fecha evento: " + tiquete.getEvento().getFechaHora()));
        panel.add(new JLabel("Asiento: " + tiquete.getAsiento()));
        panel.add(new JLabel("Precio: $" + tiquete.getPrecioPagar()));

        try {
                ImageIcon iconQR = new ImageIcon(rutaQR);
                JLabel labelQR = new JLabel(iconQR);
                labelQR.setAlignmentX(Component.CENTER_ALIGNMENT);
                panel.add(labelQR);
        } catch (Exception e) {
                panel.add(new JLabel("No se pudo cargar el QR"));
        }

        add(panel);
	}
}
