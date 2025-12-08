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
import tiquetesCompra.Tiquete;
import usuarios.ClienteNatural;

public class ClienteVista extends JPanel {
	private final MainController mainController;
    private final AplicacionFrame frame;

    private ClienteNatural cliente;
    private final DefaultListModel<Tiquete> modeloTiquetes;
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

        JButton btnImprimir = new JButton("Imprimir seleccionado");
        JButton btnRefrescar = new JButton("Actualizar lista");
        JButton btnSalir = new JButton("Cerrar sesión");
        JPanel acciones = new JPanel();
        acciones.setBackground(new Color(248, 250, 252));
        acciones.add(btnRefrescar);
        acciones.add(btnImprimir);
        acciones.add(btnSalir);
        add(acciones, BorderLayout.SOUTH);

        btnRefrescar.addActionListener(e -> cargarTiquetes());

        btnImprimir.addActionListener(e -> {
            Tiquete seleccionado = lista.getSelectedValue();
            if (seleccionado == null) {
                JOptionPane.showMessageDialog(this, "Selecciona un tiquete", "Información", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            mainController.getImpresionController().imprimirTiquete(seleccionado);
        });

        btnSalir.addActionListener(e -> frame.mostrarLogin());
    }

    public void setCliente(ClienteNatural cliente) {
        this.cliente = cliente;
        lblBienvenida.setText("Panel Cliente - " + cliente.getNombreUsuario());
        cargarTiquetes();
    }

    private void cargarTiquetes() {
        modeloTiquetes.clear();
        if (cliente == null) return;
        List<Tiquete> tiquetes = cliente.getMisTiquetes();
        for (Tiquete t : tiquetes) {
            modeloTiquetes.addElement(t);
        }
    }
}
