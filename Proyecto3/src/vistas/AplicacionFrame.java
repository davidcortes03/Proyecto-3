package vistas;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controladores.MainController;
import usuarios.Administrador;
import usuarios.ClienteNatural;
import usuarios.Organizador;

public class AplicacionFrame extends JFrame{
	private static final String VISTA_LOGIN = "LOGIN";
    private static final String VISTA_ADMIN = "ADMIN";
    private static final String VISTA_ORGANIZADOR = "ORGANIZADOR";
    private static final String VISTA_CLIENTE = "CLIENTE";

    private final CardLayout layout;
    private final JPanel contenedor;

    private final MainController mainController;
    private final LoginVista loginVista;
    private final AdminVista adminVista;
    private final OrganizadorVista organizadorVista;
    private final ClienteVista clienteVista;

    public AplicacionFrame() {
        this(new MainController());
    }

    public AplicacionFrame(MainController mainController) {
        this.mainController = mainController;

        setTitle("BoletaMaster - Proyecto 3");
        setSize(780, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 247, 250));

        layout = new CardLayout();
        contenedor = new JPanel(layout);

        loginVista = new LoginVista(mainController, this);
        adminVista = new AdminVista(mainController, this);
        organizadorVista = new OrganizadorVista(mainController, this);
        clienteVista = new ClienteVista(mainController, this);

        contenedor.add(loginVista, VISTA_LOGIN);
        contenedor.add(adminVista, VISTA_ADMIN);
        contenedor.add(organizadorVista, VISTA_ORGANIZADOR);
        contenedor.add(clienteVista, VISTA_CLIENTE);

        setContentPane(contenedor);
        mostrarLogin();
    }

    public void mostrarLogin() {
        loginVista.limpiarCampos();
        layout.show(contenedor, VISTA_LOGIN);
    }

    public void mostrarAdmin(Administrador admin) {
        adminVista.setAdministrador(admin);
        layout.show(contenedor, VISTA_ADMIN);
    }

    public void mostrarOrganizador(Organizador organizador) {
        organizadorVista.setOrganizador(organizador);
        layout.show(contenedor, VISTA_ORGANIZADOR);
    }

    public void mostrarCliente(ClienteNatural cliente) {
        clienteVista.setCliente(cliente);
        layout.show(contenedor, VISTA_CLIENTE);
    }
}
