package vistas.paneles.modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PanelInicioAdminModelo {

    public List<String[]> cargarAnimalesDesdeArchivo(String ruta) {
        List<String[]> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(":");
                if (datos.length >= 5) {
                    lista.add(datos);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer los animales desde " + ruta);
        }

        return lista;
    }

    public boolean tieneNotificacion(String IDAnimal) {
        try (BufferedReader br = new BufferedReader(new FileReader("datos/solicitudes.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.contains(IDAnimal) && linea.contains("En espera")) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error al verificar notificaciones.");
        }

        return false;
    }
}
