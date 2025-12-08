package controladores;

import java.time.LocalDateTime;

import javax.swing.SwingUtilities;

import modelo.Asiento;
import modelo.Evento;
import modelo.Localidad;
import modelo.Venue;
import persistencias.EventoRepositorio;
import persistencias.MarketPlaceRepositorio;
import persistencias.SolicitudRepositorio;
import persistencias.TiqueteRepositorio;
import persistencias.TransaccionRepositorio;
import persistencias.UsuarioRepositorio;
import persistencias.VenueRepositorio;
import servicios.ServicioAdmin;
import servicios.ServicioCompras;
import servicios.ServicioImpresion;
import servicios.ServicioMarketPlace;
import servicios.ServicioOrganizador;
import servicios.ServicioReportes;
import servicios.ServicioSolicitudes;
import servicios.ServicioUsuarios;
import tiquetesCompra.Tiquete;
import usuarios.Administrador;
import usuarios.ClienteNatural;
import usuarios.Organizador;
import vistas.LoginVista;

public class MainController {

	private final UsuarioRepositorio usuarioRepo;
    private final EventoRepositorio eventoRepo;
    private final VenueRepositorio venueRepo;
    private final TiqueteRepositorio tiqueteRepo;
    private final TransaccionRepositorio transaccionRepo;
    private final MarketPlaceRepositorio marketPlaceRepo;
    private final SolicitudRepositorio solicitudRepo;

    private final LoginController loginController;
    private final ClienteController clienteController;
    private final OrganizadorController organizadorController;
    private final AdminController adminController;
    private final MarketplaceController marketplaceController;
    private final ImpresionController impresionController;

    private final ServicioCompras servicioCompras;
    private final ServicioOrganizador servicioOrganizador;
    private final ServicioSolicitudes servicioSolicitudes;
    private final ServicioUsuarios servicioUsuarios;
    private final ServicioReportes servicioReportes;
    private final ServicioMarketPlace servicioMarketPlace;
    private final ServicioAdmin servicioAdmin;
    private final ServicioImpresion servicioImpresion;

    public MainController() {
        usuarioRepo = new UsuarioRepositorio();
        eventoRepo = new EventoRepositorio();
        venueRepo = new VenueRepositorio();
        tiqueteRepo = new TiqueteRepositorio();
        transaccionRepo = new TransaccionRepositorio();
        marketPlaceRepo = new MarketPlaceRepositorio();
        solicitudRepo = new SolicitudRepositorio();

        servicioCompras = new ServicioCompras(tiqueteRepo, transaccionRepo, eventoRepo, usuarioRepo);
        servicioOrganizador = new ServicioOrganizador(eventoRepo, venueRepo, usuarioRepo);
        servicioSolicitudes = new ServicioSolicitudes(solicitudRepo, servicioCompras, servicioOrganizador, eventoRepo);
        servicioUsuarios = new ServicioUsuarios(usuarioRepo);
        servicioReportes = new ServicioReportes(transaccionRepo, tiqueteRepo);
        servicioMarketPlace = new ServicioMarketPlace(marketPlaceRepo, tiqueteRepo, usuarioRepo, transaccionRepo);
        servicioAdmin = new ServicioAdmin(servicioCompras, solicitudRepo);
        servicioImpresion = new ServicioImpresion(tiqueteRepo);

        this.loginController = new LoginController(servicioUsuarios);
        this.clienteController = new ClienteController(servicioCompras, servicioSolicitudes,
                                                       servicioImpresion, servicioReportes);
        this.organizadorController = new OrganizadorController(servicioOrganizador,
                                                               servicioSolicitudes,
                                                               servicioReportes);
        this.adminController = new AdminController(servicioAdmin, servicioCompras,
                                                   servicioSolicitudes, servicioReportes);
        this.marketplaceController = new MarketplaceController(servicioMarketPlace);
        this.impresionController = new ImpresionController(servicioImpresion);
        cargarDatosDemo();
    }

    /**
     * Punto de entrada de la aplicación (desde la vista principal).
     */
    public void iniciarAplicacion() {
    	new vistas.AplicacionFrame(this).setVisible(true);
    }

    private void cargarDatosDemo() {
        Administrador admin = new Administrador("admin", "admin@demo.com", "admin");
        Organizador organizador = new Organizador("org", "org@demo.com", "orgpass");
        ClienteNatural cliente = new ClienteNatural("cliente", "cliente@demo.com", "1234");

        usuarioRepo.agregarUsuario(admin);
        usuarioRepo.agregarUsuario(organizador);
        usuarioRepo.agregarUsuario(cliente);

        Venue venue = new Venue("Arena Demo", 100, "Cra 1 # 2-3");
        venueRepo.agregar(venue);

        Localidad vip = new Localidad("VIP", 10, true, 150.0);
        for (int i = 1; i <= 10; i++) {
            vip.getAsientos().add(new Asiento(i));
        }
        Evento concierto = new Evento("Concierto Demo", venue, LocalDateTime.now().plusDays(15), "MUSICA", organizador);
        eventoRepo.agregar(concierto);

        Asiento asiento = vip.getAsientos().get(0);
        Tiquete tiqueteDemo = new Tiquete(150.0, false, concierto, vip, asiento);
        tiqueteDemo.setEstadoCompra("COMPRADA");
        cliente.agregarTiquete(tiqueteDemo);
        tiqueteRepo.agregar(tiqueteDemo);
    }

    // Getters por si la vista necesita acceder a algún controlador

    public LoginController getLoginController() {
        return loginController;
    }

    public ClienteController getClienteController() {
        return clienteController;
    }

    public OrganizadorController getOrganizadorController() {
        return organizadorController;
    }

    public AdminController getAdminController() {
        return adminController;
    }

    public MarketplaceController getMarketplaceController() {
        return marketplaceController;
    }

    public ImpresionController getImpresionController() {
        return impresionController;
    }
    
    public UsuarioRepositorio getUsuarioRepo() {
        return usuarioRepo;
    }

    public EventoRepositorio getEventoRepo() {
        return eventoRepo;
    }

    public TiqueteRepositorio getTiqueteRepo() {
        return tiqueteRepo;
    }
}