package persistencias;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import usuarios.Cliente;
import usuarios.ClienteNatural;

public class UsuarioRepositorio implements Serializable{
	private final List<Cliente> usuarios;

    public UsuarioRepositorio() {
        this.usuarios = new ArrayList<>();
    }

    public List<Cliente> getUsuarios() {
        return usuarios;
    }

    public List<ClienteNatural> getUsuariosNaturales() {
        List<ClienteNatural> naturales = new ArrayList<>();
        for (Cliente cliente : usuarios) {
            if (cliente instanceof ClienteNatural natural) {
                naturales.add(natural);
            }
        }
        return naturales;
    }

    public void agregarUsuario(Cliente cliente) {
        usuarios.add(cliente);
    }

    public void actualizar(Cliente clienteActualizado) {
        for (int i = 0; i < usuarios.size(); i++) {
            Cliente cliente = usuarios.get(i);
            if (cliente.getNombreUsuario().equals(clienteActualizado.getNombreUsuario())) {
                usuarios.set(i, clienteActualizado);
                return;
            }
        }
    }
}
