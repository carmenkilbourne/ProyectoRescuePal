package vistas.paneles.controlador;

import vistas.paneles.modelo.PanelInicioAdminModelo;
import vistas.paneles.vista.PanelInicioAdminVista;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static otros.VisualInfo.color2;

public class PanelInicioAdminControlador extends JPanel {
    private final Object mainFrame;
    private final PanelInicioAdminVista vista;
    private final PanelInicioAdminModelo modelo;

    public PanelInicioAdminControlador(Object mainFrame) {
        this.mainFrame = mainFrame;
        this.vista = new PanelInicioAdminVista();
        this.modelo = new PanelInicioAdminModelo();
        setLayout(new BorderLayout());
        add(vista, BorderLayout.CENTER);

        cargarTarjetas();
    }

    private void cargarTarjetas() {
        vista.panelContenedor.add(crearTarjetaAgregarAnimal());

        List<String[]> animales = modelo.cargarAnimalesDesdeArchivo("datos/animales.txt");
        for (String[] datos : animales) {
            vista.panelContenedor.add(crearTarjetaAnimal(datos,false));
        }
        List<String[]> animalesAdoptados = modelo.cargarAnimalesDesdeArchivo("datos/animalesaceptados.txt");



        for (String[] datos : animalesAdoptados) {
            vista.panelContenedor.add(crearTarjetaAnimal(datos,true));

        }

        vista.revalidate();
        vista.repaint();
    }

    private JPanel crearTarjetaAgregarAnimal() {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(new Color(70, 130, 180));
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        card.setPreferredSize(new Dimension(200, 200));

        JLabel plusLabel = new JLabel("+", SwingConstants.CENTER);
        plusLabel.setFont(new Font("Arial", Font.BOLD, 48));
        plusLabel.setForeground(Color.WHITE);

        card.add(plusLabel, BorderLayout.CENTER);

        card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    mainFrame.getClass().getMethod("cambiarPanel", JPanel.class)
                            .invoke(mainFrame, Class.forName("vistas.paneles.controlador.PanelAgregarAnimalControlador")
                                    .getConstructor(Object.class)
                                    .newInstance(mainFrame));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return card;
    }

    private JPanel crearTarjetaAnimal(String[] datos, boolean aceptado) {
        JPanel card = new JPanel(new BorderLayout());
        if (!aceptado) {
            card.setBackground(color2);
        }
        else {card.setBackground(new Color(255, 199, 206));}
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        card.setPreferredSize(new Dimension(200, 200));

        JLabel imageLabel = new JLabel();
        String rutaImagen = "src/resources/animales/" + datos[0] + "_image.png";

        try {
            Image imagen = ImageIO.read(new File(rutaImagen));
            imageLabel.setIcon(new ImageIcon(imagen.getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
        } catch (IOException e) {
            imageLabel.setText("Imagen no disponible");
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        }

        JLabel descriptionLabel = new JLabel("<html>Nombre: " + datos[3] +
                "<br>Animal: " + datos[1] +
                "<br>Raza: " + datos[2] +
                "<br>Edad: " + datos[4] + " años</html>", SwingConstants.CENTER);
        descriptionLabel.setFont(new Font("Arial", Font.BOLD, 19));

        card.add(imageLabel, BorderLayout.WEST);
        card.add(descriptionLabel, BorderLayout.CENTER);

        if (modelo.tieneNotificacion(datos[0])) {
            JLabel notiLabel = new JLabel();
            try {
                Image iconoNoti = ImageIO.read(new File("src/resources/menu/InboxNotificacion.png"));
                notiLabel.setIcon(new ImageIcon(iconoNoti.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
            } catch (IOException e) {
                notiLabel.setText("Notificación");
            }

            JPanel notificacion = new JPanel(new BorderLayout());
            notificacion.setOpaque(false);
            notificacion.add(notiLabel, BorderLayout.EAST);
            card.add(notificacion, BorderLayout.NORTH);
        }

        card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    mainFrame.getClass().getMethod("cambiarPanel", JPanel.class)
                            .invoke(mainFrame, Class.forName("vistas.paneles.controlador.PanelAnimalAdminControlador")
                                    .getConstructor(Object.class, String[].class)
                                    .newInstance(mainFrame, datos));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return card;
    }
}
