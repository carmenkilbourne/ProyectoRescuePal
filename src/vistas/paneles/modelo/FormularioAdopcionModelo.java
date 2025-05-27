package vistas.paneles.modelo;

import java.io.*;
import java.util.List;

public class FormularioAdopcionModelo {

    private final double[] puntuaciones = {0.0, 1.0, 0.5, 0.0};

    public boolean existeSolicitud(String correoUsuario, String IDAnimal) {
        try (BufferedReader br = new BufferedReader(new FileReader("datos/solicitudes.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.contains("Usuario: " + correoUsuario) && linea.contains(IDAnimal)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void guardarSolicitud(String correoUsuario, String IDAnimal, String respuestasTexto, double puntajeTotal, String resultado) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("datos/solicitudes.txt", true))) {
            bw.write("<html>Usuario: " + correoUsuario + "<br>");
            bw.write("ID_Animal:" + IDAnimal + "<br>");
            bw.write(respuestasTexto);
            bw.write("Puntaje total: " + puntajeTotal + "<br>");
            bw.write("Resultado: " + resultado + "<br>");
            bw.write("| Estado: En espera\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public double[] getPuntuaciones() {
        return puntuaciones;
    }
}
