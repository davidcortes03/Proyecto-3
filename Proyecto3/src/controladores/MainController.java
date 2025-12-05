package controladores;

import servicios.ServicioAdmin;
import servicios.ServicioCompras;
import servicios.ServicioImpresion;
import servicios.ServicioMarketPlace;
import servicios.ServicioOrganizador;
import servicios.ServicioReportes;
import servicios.ServicioSolicitudes;
import servicios.ServicioUsuarios;

public class MainController {

    private LoginController loginController;
    private ClienteController clienteController;
    private OrganizadorController organizadorController;
    private AdminController adminController;
    private MarketplaceController marketplaceController;
    private ImpresionController impresionController;

    // Puedes pasarle todos los servicios y crear aquí los demás controladores
    public MainController(ServicioUsuarios servicioUsuarios,
                          ServicioCompras servicioCompras,
                          ServicioImpresion servicioImpresion,
                          ServicioMarketPlace servicioMarketPlace,
                          ServicioOrganizador servicioOrganizador,
                          ServicioReportes servicioReportes,
                          ServicioSolicitudes servicioSolicitudes,
                          ServicioAdmin servicioAdmin) {

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
    }

    /**
     * Punto de entrada de la aplicación (desde la vista principal).
     */
    public void iniciarAplicacion() {
        // TODO: mostrar menú principal, pedir login, decidir a qué controlador ir, etc.
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
}