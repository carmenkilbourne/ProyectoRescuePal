package vistas.paneles.modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PanelSolicitudesAdminModelo {

    private static final String RUTA_SOLICITUDES = "datos/solicitudes.txt";
    private static final String RUTA_REVISADAS = "datos/solicitudesRevisadas.txt";
    private static final String RUTA_ANIMALES = "datos/animales.txt";
    private static final String RUTA_ANIMALESACEPTADOS = "datos/animalesaceptados.txt";

    public List<String> obtenerSolicitudes(String idAnimal) {
        List<String> solicitudes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_SOLICITUDES))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.contains(idAnimal) && linea.contains("Estado: En espera")) {
                    solicitudes.add(linea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return solicitudes;
    }

    public void aceptarSolicitud(String idAnimal, String solicitudOriginal) {
        guardarSolicitudRevisada(solicitudOriginal.replace("En espera", "Aceptada"));

        eliminarSolicitudDeEspera(solicitudOriginal);
        rechazarSolicitudesRestantes(idAnimal);
        eliminarAnimal(idAnimal);
    }

    public void rechazarSolicitud(String solicitudOriginal) {
        guardarSolicitudRevisada(solicitudOriginal.replace("En espera", "Rechazada"));
        eliminarSolicitudDeEspera(solicitudOriginal);
    }
    private void guardarSolicitudRevisada(String solicitud) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA_REVISADAS, true))) {
            bw.write(solicitud);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void eliminarSolicitudDeEspera(String solicitudAEliminar) {
        File inputFile = new File(RUTA_SOLICITUDES);
        File tempFile = new File("datos/solicitudes_temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String linea;
            while ((linea = reader.readLine()) != null) {
                if (!linea.trim().equals(solicitudAEliminar.trim())) {
                    writer.write(linea);
                    writer.newLine();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
            System.err.println("Error actualizando solicitudes.txt");
        }
    }private void rechazarSolicitudesRestantes(String idAnimal) {
        File inputFile = new File(RUTA_SOLICITUDES);
        File tempFile = new File("datos/solicitudes_temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter tempWriter = new BufferedWriter(new FileWriter(tempFile));
             BufferedWriter revisadasWriter = new BufferedWriter(new FileWriter(RUTA_REVISADAS, true))) {

            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.contains(idAnimal) && linea.contains("En espera")) {
                    String rechazada = linea.replace("En espera", "Rechazada");
                    revisadasWriter.write(rechazada);
                    revisadasWriter.newLine();
                } else {
                    tempWriter.write(linea);
                    tempWriter.newLine();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
            System.err.println("Error actualizando solicitudes.txt tras rechazar las restantes.");
        }
    }

    private void eliminarAnimal(String idAnimal) {
        File inputFile = new File(RUTA_ANIMALES);
        File tempFile = new File("datos/animales_temp.txt");
        File outputFile = new File(RUTA_ANIMALESACEPTADOS);

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             BufferedWriter bw2 = new BufferedWriter(new FileWriter(outputFile,true));
             BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.startsWith(idAnimal + ":")) {
                    bw.write(linea);
                    bw.newLine();
                }else{
                    bw2.write(linea);
                    bw2.newLine();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
            System.err.println("Error actualizando animales.txt");
        }
    }


}
