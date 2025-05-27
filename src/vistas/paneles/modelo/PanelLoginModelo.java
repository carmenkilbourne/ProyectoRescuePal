package vistas.paneles.modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PanelLoginModelo {
    public String verificarUsuario(String correo, String contraseña) {
        try (BufferedReader br = new BufferedReader(new FileReader("datos/usuarios.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(":");
                if (partes.length < 2) continue;
                String correoArchivo = partes[0].trim();
                String contraseñaArchivo = partes[1].trim();
                String rolArchivo = partes.length > 2 ? partes[2].trim() : "";

                if (correoArchivo.equals(correo)) {
                    if (contraseñaArchivo.equals(contraseña)) {
                        return "admin".equals(rolArchivo) ? "admin" : "user";
                    } else {
                        return "wrong_password";
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "not_found";
    }
}