package vistas.paneles.controlador;

import vistas.paneles.modelo.PanelPerfilModelo;
import vistas.paneles.vista.PanelPerfilVista;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class PanelPerfilControlador extends JPanel {
    private final PanelPerfilVista vista;
    private final PanelPerfilModelo modelo;
    private final Object mainFrame;
    private final String usuario;

    public PanelPerfilControlador(Object mainFrame, String usuario) {
        this.mainFrame = mainFrame;
        this.usuario = usuario;
        this.vista = new PanelPerfilVista();
        this.modelo = new PanelPerfilModelo();

        setLayout(new BorderLayout());
        add(vista, BorderLayout.CENTER);

        cargarPerfil();
        conectarEventos();
    }

    private void cargarPerfil() {
        vista.lblNombre.setText(usuario);

        ImageIcon imagen = modelo.cargarFotoPerfil(usuario);
        if (imagen != null) {
            vista.lblFotoPerfil.setIcon(imagen);
            vista.lblFotoPerfil.setText("");
        }

        vista.txtBiografia.setText(modelo.cargarBiografia(usuario));

        for (String[] datos : modelo.cargarFavoritos(usuario)) {
            vista.panelFavoritos.add(crearTarjeta(datos));
        }
    }

    private void conectarEventos() {
        vista.btnGuardarBiografia.addActionListener(e -> {
            try {
                modelo.guardarBiografia(usuario, vista.txtBiografia.getText());
                JOptionPane.showMessageDialog(this, "Biografía guardada.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        vista.btnCambiarFoto.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                File archivo = fc.getSelectedFile();
                if (!archivo.getName().toLowerCase().endsWith(".jpg")) {
                    JOptionPane.showMessageDialog(this, "Solo se permiten imágenes JPG.", "Formato inválido", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                try {
                    modelo.guardarFotoPerfil(usuario, archivo);
                    cargarPerfil();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error al guardar imagen.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        vista.btnVerSolicitudes.addActionListener(e -> {
            try {
                mainFrame.getClass().getMethod("mostrarSolicitudesUsuario").invoke(mainFrame);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    private JPanel crearTarjeta(String[] datos) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(new Color(230, 240, 255));
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JLabel imageLabel = new JLabel();
        String rutaImagen = "src/resources/animales/" + datos[0] + "_image.png";

        try {
            Image imagen = ImageIO.read(new File(rutaImagen));
            imageLabel.setIcon(new ImageIcon(imagen.getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
        } catch (IOException e) {
            imageLabel.setText("Imagen no disponible");
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        }

        JLabel nombre = new JLabel(datos[3], SwingConstants.CENTER);
        nombre.setFont(new Font("Arial", Font.BOLD, 21));

        card.add(imageLabel, BorderLayout.CENTER);
        card.add(nombre, BorderLayout.SOUTH);

        card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {

                    Class<?> claseAnimal = Class.forName("vistas.paneles.controlador.PanelAnimalControlador");

                    JPanel panelAnimal = (JPanel) claseAnimal
                            .getConstructor(Object.class, String[].class)
                            .newInstance(mainFrame, datos);

                    mainFrame.getClass().getMethod("cambiarPanel", JPanel.class)
                            .invoke(mainFrame, panelAnimal);

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(card, "No se pudo abrir el animal. Intenta más tarde.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });



        return card;
    }
}

/**
 * Estamos implementado el boton ver mis solicitudes dentro del perfil ya Basándonos en el tipo de proyecto que llevamos
 * una protectora de animales, donde adoptar es algo muy relevante pero tampoco se envían solicitudes cada minuto),
 * La navegación es lógica: en tu Perfil ves tus datos, tu biografía, tus favoritos y tus solicitudes.
 *  El menú lateral queda más limpio.
 */

