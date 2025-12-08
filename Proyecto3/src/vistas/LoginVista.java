package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controladores.LoginController;
import controladores.MainController;
import usuarios.Administrador;
import usuarios.Cliente;
import usuarios.ClienteNatural;
import usuarios.Organizador;

public class LoginVista extends JPanel{
	
	private final JTextField txtUsuario;
    private final JPasswordField txtContrasena;

    private final LoginController controller;
    private final MainController mainController;
    private final AplicacionFrame frame;

    public LoginVista(MainController mainController, AplicacionFrame frame) {
        this.controller = mainController.getLoginController();
        this.mainController = mainController;
        this.frame = frame;

        setLayout(new BorderLayout());
        setBackground(new Color(244, 248, 252));
        setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        JLabel titulo = new JLabel("BoletaMaster", SwingConstants.CENTER);
        titulo.setOpaque(true);
        titulo.setBackground(new Color(33, 150, 243));
        titulo.setForeground(Color.WHITE);
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        titulo.setFont(titulo.getFont().deriveFont(20f));
        add(titulo, BorderLayout.NORTH);

        JPanel formulario = new JPanel(new GridBagLayout());
        formulario.setBackground(Color.WHITE);
        formulario.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 230, 240)),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        formulario.add(new JLabel("Usuario"), gbc);
        gbc.gridy++;
        txtUsuario = new JTextField(15);
        formulario.add(txtUsuario, gbc);

        gbc.gridy++;
        formulario.add(new JLabel("Contraseña"), gbc);
        gbc.gridy++;
        txtContrasena = new JPasswordField(15);
        formulario.add(txtContrasena, gbc);

        gbc.gridy++;
        JButton btnLogin = new JButton("Iniciar sesión");
        btnLogin.setBackground(new Color(33, 150, 243));
        btnLogin.setForeground(Color.WHITE);
        formulario.add(btnLogin, gbc);

        gbc.gridy++;
        JButton btnDemo = new JButton("Ver usuarios demo");
        formulario.add(btnDemo, gbc);

        add(formulario, BorderLayout.CENTER);

        btnLogin.addActionListener(e -> autenticar());
        btnDemo.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "Usuarios demo:\nadmin / admin\norg / orgpass\ncliente / 1234",
                "Credenciales de prueba",
                JOptionPane.INFORMATION_MESSAGE));
    }

    private void autenticar() {
        String user = txtUsuario.getText();
        String pass = new String(txtContrasena.getPassword());

        Cliente cliente = controller.login(user, pass);

        if (cliente == null) {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (cliente instanceof Administrador admin) {
            frame.mostrarAdmin(admin);
        } else if (cliente instanceof Organizador organizador) {
            frame.mostrarOrganizador(organizador);
        } else if (cliente instanceof ClienteNatural natural) {
            frame.mostrarCliente(natural);
        }
    }

    public void limpiarCampos() {
        txtUsuario.setText("");
        txtContrasena.setText("");
    }
}
