package vistas.paneles.modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PanelInicioModelo {
    private static final String RUTA_ANIMALES = "datos/animales.txt";
    private static final String RUTA_FAVORITOS = "datos/Favoritos.txt";

    public List<String[]> cargarAnimales() {
        List<String[]> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_ANIMALES))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(":");
                if (datos.length >= 5) {
                    lista.add(datos);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean esFavorito(String correoUsuario, String idAnimal) {
        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_FAVORITOS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith(correoUsuario + ":")) {
                    String[] partes = linea.split(":");
                    if (partes.length > 1) {
                        String[] favoritos = partes[1].split(",");
                        for (String fav : favoritos) {
                            if (fav.trim().equals(idAnimal)) {
                                return true;
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void modificarFavorito(String correoUsuario, String idAnimal, boolean agregar) {
        List<String> lineas = new ArrayList<>();
        boolean usuarioEncontrado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_FAVORITOS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith(correoUsuario + ":")) {
                    usuarioEncontrado = true;
                    String[] partes = linea.split(":");
                    List<String> favoritos = new ArrayList<>();

                    if (partes.length > 1 && !partes[1].isEmpty()) {
                        for (String fav : partes[1].split(",")) {
                            if (!fav.isBlank()) {
                                favoritos.add(fav.trim());
                            }
                        }
                    }

                    if (agregar) {
                        if (!favoritos.contains(idAnimal)) {
                            favoritos.add(idAnimal);
                        }
                    } else {
                        favoritos.remove(idAnimal);
                    }

                    String nuevaLinea = correoUsuario + ":" + String.join(",", favoritos) + ",";
                    lineas.add(nuevaLinea);
                } else {
                    lineas.add(linea);
                }
            }

            if (!usuarioEncontrado && agregar) {
                lineas.add(correoUsuario + ":" + idAnimal + ",");
            }

            java.nio.file.Files.write(java.nio.file.Paths.get(RUTA_FAVORITOS), lineas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
