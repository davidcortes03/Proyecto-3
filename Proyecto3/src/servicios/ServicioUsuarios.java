package servicios;

import persistencias.UsuarioRepositorio;
import usuarios.Administrador;
import usuarios.Cliente;
import usuarios.ClienteNatural;
import usuarios.Organizador;

public class ServicioUsuarios {

    private UsuarioRepositorio usuarioRepo; 

    public ServicioUsuarios(UsuarioRepositorio usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    /*
     * Intenta iniciar sesión. 
     * Devuelve el Cliente si las credenciales son correctas, o null si no.
     */
    public Cliente login(String usuario, String contrasena) {
        for (Cliente cliente : usuarioRepo.getUsuarios()) {
            if (cliente.getNombreUsuario().equals(usuario) &&
                cliente.getContrasena().equals(contrasena)) {
                return cliente;
            }
        }
        throw new RuntimeException("Usuario o contraseña incorrectos");
    }

    /*
     * Registra un cliente natural si no existe ya un usuario con ese nombre.
     */
    public ClienteNatural registrarClienteNatural(String nombreUsuario, String correo, String contrasena) {
        if (existeUsuario(nombreUsuario)) {
            System.out.println("El usuario ya existe.");
            return null;
        }
        ClienteNatural clienteNuevo = new ClienteNatural(nombreUsuario, correo, contrasena);
        usuarioRepo.agregarUsuario(clienteNuevo);
        return clienteNuevo;
    }

    /*
     * Registra un organizador si no existe ya un usuario con ese nombre.
     */
    public Organizador registrarOrganizador(String nombreUsuario, String correo, String contrasena) {
        if (existeUsuario(nombreUsuario)) {
            System.out.println("El usuario ya existe.");
            return null;
        }
        Organizador organizadorNuevo = new Organizador(nombreUsuario, correo, contrasena);
        usuarioRepo.agregarUsuario(organizadorNuevo);
        return organizadorNuevo;
    }

    /*
     * Registra un administrador si no existe ya un usuario con ese nombre.
     */
    public Administrador registrarAdministrador(String nombreUsuario, String correo, String contrasena) {
        if (existeUsuario(nombreUsuario)) {
            System.out.println("El usuario ya existe.");
            return null;
        }
        Administrador adminNuevo = new Administrador(nombreUsuario, correo, contrasena);
        usuarioRepo.agregarUsuario(adminNuevo);
        return adminNuevo;
    }
    
    /*
     * Busca por nombre a los usuarios.
     */
    public Cliente buscarPorNombreUsuario(String nombreUsuario) {
    		Cliente respuesta = null;
    		for(Cliente c: usuarioRepo.getUsuarios()) {
    			if(c.getNombreUsuario().equals(nombreUsuario)){
    				respuesta = c;
    			}
    		}
    		return respuesta;
    }
    
    /*
     * Revisa si existe un usuario a partir de su nombre.
     */
    public boolean existeUsuario(String nombreUsuario) {
    		boolean respuesta = false;
    		for(Cliente c: usuarioRepo.getUsuarios()) {
    			if(c.getNombreUsuario().equals(nombreUsuario)) {
    				respuesta = true;
    			}
    		}
    	return respuesta;
    }
	
}