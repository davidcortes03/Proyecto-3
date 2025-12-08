package app;

import javax.swing.SwingUtilities;

import vistas.AplicacionFrame;

public class Main {
	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AplicacionFrame().setVisible(true));
    }
}
