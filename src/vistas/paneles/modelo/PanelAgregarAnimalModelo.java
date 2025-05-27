package vistas.paneles.modelo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class PanelAgregarAnimalModelo {
    private File imagenSeleccionada;

    public boolean guardarAnimal(String tipo, String raza, String nombre, String edad, File imagen) {
        String nuevoID = generarNuevoID();
        String nuevoAnimal = nuevoID + ":" + tipo + ":" + raza + ":" + nombre + ":" + edad;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("datos/animales.txt", true))) {
            writer.write(nuevoAnimal);
            writer.newLine();

            File destino = new File("src/resources/animales/" + nuevoID + "_image.png");
            Files.copy(imagen.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);

            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private String generarNuevoID() {
        File archivoID = new File("datos/ultimoID.txt");
        int ultimoID = 0;

        try {
            if (archivoID.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(archivoID));
                String linea = reader.readLine();
                if (linea != null) {
                    ultimoID = Integer.parseInt(linea.trim());
                }
                reader.close();
            }
        } catch (IOException | NumberFormatException e) {
            ultimoID = 0;
        }

        int nuevoID = ultimoID + 1;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoID))) {
            writer.write(String.valueOf(nuevoID));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return String.format("ID%05d", nuevoID);
    }

    public void setImagenSeleccionada(File imagen) {
        this.imagenSeleccionada = imagen;
    }


    public File getImagenSeleccionada() {
        return imagenSeleccionada;
    }
}
