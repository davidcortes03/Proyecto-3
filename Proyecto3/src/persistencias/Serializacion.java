package persistencias;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serializacion {
	
	private static final String RUTA_ARCHIVO = "data/ArchivoDatos.ser";

	

    public static void guardarSistema(SistemaBoletaMaster sistema) { 
        try (ObjectOutputStream escritura = new ObjectOutputStream(new FileOutputStream(RUTA_ARCHIVO))) { //Abre el archivo en el que se van a guardar los datos y se conecta para poder manipularlo.
        	escritura.writeObject(sistema); //Se escribe en el archivo en forma de bytes.
            System.out.println("Datos guardado");
        } catch (IOException e) {
            System.err.println("Error al guardar los datos");
        }
    }

    public static SistemaBoletaMaster cargarSistema() {

        try (ObjectInputStream lectura = new ObjectInputStream(new FileInputStream(RUTA_ARCHIVO))) { //Abre el archivo y los vuelve objetos en java.
            SistemaBoletaMaster sistema = (SistemaBoletaMaster) lectura.readObject(); //
            System.out.println("Sistema cargado correctamente");
            return sistema;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar");
            return new SistemaBoletaMaster();
        }
    }
}
