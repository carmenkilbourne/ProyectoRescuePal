package otros;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SolicitudAdopcion {
    private final String usuario;
    private final String IDanimal;
    private final String estado;

    public SolicitudAdopcion(String usuario, String IDanimal, String estado) {
        this.usuario = usuario;
        this.IDanimal = IDanimal;
        this.estado = estado;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getIDanimal() {
        return IDanimal;
    }

    public String getEstado() {
        return estado;
    }

    public static String obtenerNombreAnimalPorID(String idAnimal) {
        try (BufferedReader br = new BufferedReader(new FileReader("datos/animales.txt"));) {
            BufferedReader br2 = new BufferedReader(new FileReader("datos/animalesaceptados.txt"));
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(":");
                if (partes.length >= 5 && partes[0].equals(idAnimal)) {
                    return partes[3]; // Nombre del animal
                }
            }
            while ((linea = br2.readLine()) != null) {
                String[] partes = linea.split(":");
                if (partes.length >= 5 && partes[0].equals(idAnimal)) {
                    return partes[3]; // Nombre del animal
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Desconocido";
    }
}
