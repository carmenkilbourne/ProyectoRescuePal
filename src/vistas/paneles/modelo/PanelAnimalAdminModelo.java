package vistas.paneles.modelo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class PanelAnimalAdminModelo {
    private static final String RUTA_FAVORITOS = "datos/Favoritos.txt";

    public int contarSolicitudes(String idAnimal) {
        int contador = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("datos/solicitudes.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.contains(idAnimal)) {
                    contador++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contador;
    }

    public boolean actualizarAnimal(String idAnimal, String tipo, String raza, String nombre, String edad) {
        try (BufferedReader br = new BufferedReader(new FileReader("datos/animales.txt"))) {
            BufferedWriter bw = new BufferedWriter(new FileWriter("datos/animales_temp.txt"));
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(":");
                if (partes[0].equals(idAnimal)) {
                    linea = idAnimal + ":" + tipo + ":" + raza + ":" + nombre + ":" + edad;
                }
                bw.write(linea);
                bw.newLine();
            }
            bw.close();
            Files.move(new File("datos/animales_temp.txt").toPath(), new File("datos/animales.txt").toPath(), StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    public void eliminarAnimal(String idAnimal,String ruta) {
        File archivo = new File(ruta+".txt");
        File archivoTemporal = new File(ruta+"_temp.txt");
        boolean eliminado = false;

        try (
                BufferedReader lector = new BufferedReader(new FileReader(archivo));
                PrintWriter escritor = new PrintWriter(new FileWriter(archivoTemporal))
        ) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                if (!linea.startsWith(idAnimal + ":")) {
                    escritor.println(linea);
                } else {
                    eliminado = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



        if (archivo.delete() && archivoTemporal.renameTo(archivo)) {
        } else {
        }
    }

    public boolean eliminarAnimalaceptados(String idAnimal) {
        File archivo = new File("datos/animalesaceptados.txt");
        File archivoTemporal = new File("datos/animales_temp.txt");
        boolean eliminado = false;

        try (
                BufferedReader lector = new BufferedReader(new FileReader(archivo));
                PrintWriter escritor = new PrintWriter(new FileWriter(archivoTemporal))
        ) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                if (!linea.startsWith(idAnimal + ":")) {
                    escritor.println(linea);
                } else {
                    eliminado = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }



        if (archivo.delete() && archivoTemporal.renameTo(archivo)) {
            return eliminado;
        } else {
            return false;
        }
    }

    public void eliminarDeFavoritos(String ruta, String idAnimal) {
        boolean modificado = false;
        File original = new File(ruta);
        File temp = new File(ruta + ".tmp");

        try (BufferedReader reader = new BufferedReader(new FileReader(original));
             PrintWriter writer = new PrintWriter(new FileWriter(temp))) {

            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(":");
                if (partes.length > 1) {
                    String usuario = partes[0];
                    String[] ids = partes[1].split(",");
                    StringBuilder nuevosIds = new StringBuilder();
                    for (String id : ids) {
                        id = id.trim();
                        if (!id.equals(idAnimal) && !id.isEmpty()) {
                            if (nuevosIds.length() > 0) nuevosIds.append(",");
                            nuevosIds.append(id);
                        } else {
                            modificado = true;
                        }
                    }
                    writer.println(usuario + ":" + nuevosIds.toString());
                } else {
                    writer.println(linea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (original.delete() && temp.renameTo(original)) {
        }
    }


    public void eliminarSolicitudesDelAnimal(String idAnimal, String ruta) throws IOException {
        try {
        File inputFile = new File(ruta);
        File tempFile = new File(ruta + ".tmp");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String linea;
        while ((linea = reader.readLine()) != null) {
            String[] partes = linea.split(";");
            if (partes.length > 1 && !partes[1].equals(idAnimal)) {
                writer.write(linea);
                writer.newLine();
            }
        }
        writer.close();
        reader.close();
        inputFile.delete();
        tempFile.renameTo(inputFile);
    } catch (Exception e) {
        e.printStackTrace();
    }
    }

    public boolean cambiarImagen(String idAnimal, File nuevaImagen) {
        try {
            File destino = new File("src/resources/animales/" + idAnimal + "_image.png");
            Files.copy(nuevaImagen.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
