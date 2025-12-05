package controladores;

import servicios.ServicioUsuarios;
import usuarios.Administrador;
import usuarios.Cliente;
import usuarios.ClienteNatural;
import usuarios.Organizador;

public class LoginController {

    private ServicioUsuarios servicioUsuarios;

    public LoginController(ServicioUsuarios servicioUsuarios) {
        this.servicioUsuarios = servicioUsuarios;
    }

    /**
     * Intenta iniciar sesión con usuario y contraseña.
     */
    public Cliente login(String nombreUsuario, String contrasena) {
        // TODO: usar servicioUsuarios.login(nombreUsuario, contrasena)
        return null;
    }

    /**
     * Registra un nuevo cliente natural.
     */
    public ClienteNatural registrarClienteNatural(String nombreUsuario, String correo, String contrasena) {
        // TODO: usar servicioUsuarios.registrarClienteNatural(...)
        return null;
    }

    /**
     * Registra un nuevo organizador.
     */
    public Organizador registrarOrganizador(String nombreUsuario, String correo, String contrasena) {
        // TODO: usar servicioUsuarios.registrarOrganizador(...)
        return null;
    }

    /**
     * Registra un nuevo administrador.
     */
    public Administrador registrarAdministrador(String nombreUsuario, String correo, String contrasena) {
        // TODO: usar servicioUsuarios.registrarAdministrador(...)
        return null;
    }

    /**
     * Verifica si ya existe un usuario con ese nombre.
     */
    public boolean existeUsuario(String nombreUsuario) {
        // TODO: usar servicioUsuarios.existeUsuario(nombreUsuario)
        return false;
    }
}
