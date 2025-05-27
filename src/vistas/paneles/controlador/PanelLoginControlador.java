package vistas.paneles.controlador;

import vistas.paneles.modelo.PanelLoginModelo;
import vistas.paneles.vista.PanelLoginVista;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PanelLoginControlador extends JPanel {
    private final PanelLoginVista vista;
    private final PanelLoginModelo modelo;
    private final Object mainFrame;

    public PanelLoginControlador(Object mainFrame) {
        this.mainFrame = mainFrame;
        this.vista = new PanelLoginVista();
        this.modelo = new PanelLoginModelo();
        setLayout(new BorderLayout());
        add(vista, BorderLayout.CENTER);

        vista.btnLogin.addActionListener(e -> iniciarSesion());
        vista.btnRegistro.addActionListener(e -> mostrarRegistro());
    }

    private void iniciarSesion() {
        String usuario = vista.txtUsuario.getText().trim();
        String password = new String(vista.txtPassword.getPassword()).trim();

        if (usuario.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, completa ambos campos.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader("datos/usuarios.txt"))) {
            String linea;
            boolean encontrado = false;

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(":");
                if (partes.length >= 2 && partes[0].equals(usuario)) {
                    encontrado = true;
                    String passwordArchivo = partes[1];

                    if (passwordArchivo.equals(password)) {
                        if (partes.length == 3 && partes[2].equals("admin")) {
                            mainFrame.getClass().getMethod("setUsuarioLogueado", boolean.class, String.class, String.class)
                                    .invoke(mainFrame, true, usuario, "admin");
                            mainFrame.getClass().getMethod("mostrarInicioAdmin").invoke(mainFrame);
                        } else {
                            mainFrame.getClass().getMethod("setUsuarioLogueado", boolean.class, String.class, String.class)
                                    .invoke(mainFrame, true, usuario, "user");
                            mainFrame.getClass().getMethod("mostrarInicio").invoke(mainFrame);
                        }
                        return;
                    } else {
                        JOptionPane.showMessageDialog(this, "Contraseña incorrecta.");
                        return;
                    }
                }
            }

            if (!encontrado) {
                JOptionPane.showMessageDialog(this, "Usuario no encontrado.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al procesar el inicio de sesión.");
            e.printStackTrace();
        }
    }

    private void mostrarRegistro() {
        try {
            mainFrame.getClass().getMethod("mostrarPanelRegistro").invoke(mainFrame);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public PanelLoginVista getVista() {
        return vista;
    }

}
