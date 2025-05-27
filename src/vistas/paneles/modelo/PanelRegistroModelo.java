package vistas.paneles.modelo;

import java.io.*;
import java.util.Random;

public class PanelRegistroModelo {
    private final char[] LETRAS_DNI = "TRWAGMYFPDXBNJZSQVHLCKE".toCharArray();

    public boolean correoYaExiste(String correo) {
        try (BufferedReader br = new BufferedReader(new FileReader("datos/usuarios.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(":");
                if (partes[0].trim().equals(correo)) return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean guardarUsuario(String correo, String password, String dni, String cp, String fecha, String tel) {
        try (BufferedWriter bwFicha = new BufferedWriter(new FileWriter("datos/FichaUsuario.txt", true));
             BufferedWriter bwUsuarios = new BufferedWriter(new FileWriter("datos/usuarios.txt", true));
             BufferedWriter bwFavoritos = new BufferedWriter(new FileWriter("datos/Favoritos.txt", true));
             BufferedWriter bwBiografias = new BufferedWriter(new FileWriter("datos/biografias.txt", true))) {

            bwFicha.write("Correo: " + correo + "\nDNI: " + dni + "\nCódigo Postal: " + cp +
                    "\nFecha de Nacimiento: " + fecha + "\nTeléfono: " + tel + "\n-------------------------\n");

            bwUsuarios.write(correo + ":" + password + ":usuario\n");
            bwFavoritos.write(correo + ":\n");
            bwBiografias.write(correo + ":\n");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String[] generarUsuario() {
        Random r = new Random();
        char letraUser = (char) ('a' + r.nextInt(26));
        String correo = letraUser + "@gmail.com";
        String password = String.valueOf(letraUser);

        String numeroDNI = String.format("%08d", r.nextInt(100000000));
        String dni = numeroDNI + LETRAS_DNI[Integer.parseInt(numeroDNI) % 23];

        String cp = String.format("%02d", r.nextInt(53) + 1) + String.format("%03d", r.nextInt(1000));
        String fecha = String.format("%02d", r.nextInt(28) + 1) + "/" +
                String.format("%02d", r.nextInt(12) + 1) + "/" +
                (1950 + r.nextInt(58));

        String tel = "6" + String.format("%08d", r.nextInt(100000000));

        return new String[]{correo, password, dni, cp, fecha, tel};
    }
}
