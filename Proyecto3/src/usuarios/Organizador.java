package usuarios;

import java.util.ArrayList;
import java.util.List;
import modelo.Evento;

public class Organizador extends ClienteNatural {
	
	public List<Evento> misEventos;
	
	public Organizador(String usuarioNombre, String correo, String contrasena) {
		super(usuarioNombre, correo, contrasena);
		this.misEventos = new ArrayList<>();
	}	
	
	public List<Evento> getMisEventos(){
		return misEventos;
	}
	
	public void agregarEvento(Evento e) {
		misEventos.add(e);
	}
	
	
	
}

