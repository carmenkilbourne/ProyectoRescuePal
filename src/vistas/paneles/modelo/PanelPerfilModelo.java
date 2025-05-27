package vistas.paneles.modelo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PanelPerfilModelo {
    private final String rutaBiografias = "datos/Biografias.txt";
    private final String rutaPerfiles = "src/resources/Perfiles/";
    private final String rutaFavoritos = "datos/Favoritos.txt";
    private final String rutaAnimales = "datos/animales.txt";

    public String cargarBiografia(String usuario) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaBiografias))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith(usuario + " :")) {
                    return linea.substring((usuario + " :").length()).replace("\\n", "\n").trim();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public void guardarBiografia(String usuario, String biografia) throws IOException {
        List<String> lineas = Files.readAllLines(Paths.get(rutaBiografias));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaBiografias))) {
            boolean encontrado = false;
            for (String linea : lineas) {
                if (linea.startsWith(usuario + " :")) {
                    writer.write(usuario + " : " + biografia.replace("\n", "\\n"));
                    encontrado = true;
                } else {
                    writer.write(linea);
                }
                writer.newLine();
            }
            if (!encontrado) {
                writer.write(usuario + " : " + biografia.replace("\n", "\\n"));
                writer.newLine();
            }
        }
    }

    public ImageIcon cargarFotoPerfil(String usuario) {
        File archivo = new File(rutaPerfiles + usuario + ".jpg");
        if (archivo.exists()) {
            try {
                BufferedImage img = ImageIO.read(archivo);
                return new ImageIcon(img.getScaledInstance(300, 300, Image.SCALE_SMOOTH));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void guardarFotoPerfil(String usuario, File archivoSeleccionado) throws IOException {
        BufferedImage imagen = ImageIO.read(archivoSeleccionado);
        ImageIO.write(imagen, "jpg", new File(rutaPerfiles + usuario + ".jpg"));
    }

    public List<String[]> cargarFavoritos(String usuario) {
        List<String[]> lista = new ArrayList<>();
        List<String> ids = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaFavoritos))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith(usuario + ":")) {
                    String[] partes = linea.split(":");
                    if (partes.length > 1) {
                        for (String id : partes[1].split(",")) {
                            if (!id.isEmpty()) ids.add(id.trim());
                        }
                    }
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(rutaAnimales))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(":");
                if (datos.length >= 5 && ids.contains(datos[0])) {
                    lista.add(datos);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lista;
    }
}