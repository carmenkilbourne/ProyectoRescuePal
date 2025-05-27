package vistas.paneles.controlador;

import vistas.paneles.modelo.PanelInicioModelo;
import vistas.paneles.vista.PanelInicioVista;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static java.lang.Integer.parseInt;
import static otros.VisualInfo.*;

public class PanelInicioControlador extends JPanel {
    private final Object mainFrame;
    private final PanelInicioVista vista;
    private final PanelInicioModelo modelo;

    public PanelInicioControlador(Object mainFrame) {
        this.mainFrame = mainFrame;
        this.vista = new PanelInicioVista();
        this.modelo = new PanelInicioModelo();

        setLayout(new BorderLayout());
        add(vista, BorderLayout.CENTER);

        cargarTarjetas();
    }

    private void cargarTarjetas() {
        List<String[]> animales = modelo.cargarAnimales();
        for (String[] datos : animales) {
            vista.panelContenedor.add(crearTarjetaAnimal(datos));
        }
        vista.revalidate();
        vista.repaint();
    }

    private JPanel crearTarjetaAnimal(String[] datos) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(color1);
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        JLabel imageLabel = new JLabel();
        String rutaImagen = "src/resources/animales/" + datos[0] + "_image.png";
        try {
            Image imagen = ImageIO.read(new File(rutaImagen));
            imageLabel.setIcon(new ImageIcon(imagen.getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
        } catch (IOException e) {
            imageLabel.setText("Imagen no disponible");
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        }
        String años = "";
        int edad = parseInt(datos[4]);
        if (edad == 1) {años = "año";} else {años = "años";}
        JLabel descriptionLabel = new JLabel("<html>Nombre: " + datos[3] +
                "<br>Animal: " + datos[1] +
                "<br>Raza: " + datos[2] +
                "<br>Edad: " + datos[4] + " "+ años+"</html>", SwingConstants.CENTER);
        descriptionLabel.setFont(new Font("Arial", Font.BOLD, 16));

        card.add(imageLabel, BorderLayout.WEST);
        card.add(descriptionLabel, BorderLayout.CENTER);

        try {
            boolean logueado = (boolean) mainFrame.getClass().getMethod("isUsuarioLogueado").invoke(mainFrame);
            if (logueado) {
                JButton botonFavorito = crearBotonFavorito(datos);
                JPanel panelTop = new JPanel(new BorderLayout());
                panelTop.setOpaque(false);
                panelTop.add(botonFavorito, BorderLayout.EAST);
                card.add(panelTop, BorderLayout.NORTH);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    boolean logueado = (boolean) mainFrame.getClass().getMethod("isUsuarioLogueado").invoke(mainFrame);
                    if (logueado) {
                        PanelAnimalControlador panelAnimal = new PanelAnimalControlador(mainFrame, datos);
                        mainFrame.getClass().getMethod("cambiarPanel", JPanel.class)
                                .invoke(mainFrame, panelAnimal);
                    } else {
                        JOptionPane.showMessageDialog(null, "Debes iniciar sesión para acceder a los detalles del animal.");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return card;
    }

    private JButton crearBotonFavorito(String[] datos) {
        JButton boton = new JButton();
        boton.setOpaque(false);
        boton.setBackground(color4);
        boton.setBorderPainted(false);

        try {
            String usuario = (String) mainFrame.getClass().getMethod("getCorreoUsuario").invoke(mainFrame);
            boolean esFavorito = modelo.esFavorito(usuario, datos[0]);

            String iconoPath = esFavorito
                    ? "src/resources/menu/FavoritoTrue.png"
                    : "src/resources/menu/FavoritoFalse.png";

            Image imagen = ImageIO.read(new File(iconoPath));
            boton.setIcon(new ImageIcon(imagen.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        } catch (Exception e) {
            boton.setText("❤");
        }

        boton.addActionListener(e -> {
            try {
                String usuario = (String) mainFrame.getClass().getMethod("getCorreoUsuario").invoke(mainFrame);
                boolean esFavorito = modelo.esFavorito(usuario, datos[0]);
                modelo.modificarFavorito(usuario, datos[0], !esFavorito);


                mainFrame.getClass().getMethod("mostrarInicio").invoke(mainFrame);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        return boton;
    }
}
