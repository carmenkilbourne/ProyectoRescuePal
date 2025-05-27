package vistas.paneles.controlador;

import otros.SolicitudAdopcion;
import vistas.paneles.modelo.PanelSolicitudesUsuarioModelo;
import vistas.paneles.vista.PanelSolicitudesUsuarioVista;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class PanelSolicitudesUsuarioControlador extends JPanel {
    private final PanelSolicitudesUsuarioVista vista;
    private final PanelSolicitudesUsuarioModelo modelo;
    private final String usuario;

    public PanelSolicitudesUsuarioControlador(String usuario) {
        this.usuario = usuario;
        this.vista = new PanelSolicitudesUsuarioVista();
        this.modelo = new PanelSolicitudesUsuarioModelo();

        setLayout(new BorderLayout());
        add(vista, BorderLayout.CENTER);

        cargarTarjetas();
    }

        private void cargarTarjetas() {
            List<SolicitudAdopcion> solicitudes = modelo.cargarSolicitudesUsuario(usuario);
            for (SolicitudAdopcion solicitud : solicitudes) {
                vista.panelContenedor.add(crearTarjeta(solicitud));
            }
            vista.revalidate();
            vista.repaint();
        }

    private JPanel crearTarjeta(SolicitudAdopcion solicitud) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        card.setPreferredSize(new Dimension(200, 200));

        JLabel imageLabel = new JLabel();
        String rutaImagen = "src/resources/animales/" + solicitud.getIDanimal() + "_image.png";
        try {
            Image imagen = ImageIO.read(new File(rutaImagen));
            imageLabel.setIcon(new ImageIcon(imagen.getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
        } catch (IOException e) {
            imageLabel.setText("Imagen no disponible");
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        }

        JLabel descriptionLabel = new JLabel("Solicitud de " + SolicitudAdopcion.obtenerNombreAnimalPorID(solicitud.getIDanimal()), SwingConstants.CENTER);
        descriptionLabel.setFont(new Font("Arial", Font.BOLD, 19));

        JLabel estadoLabel = new JLabel();
        ImageIcon iconoEstado = obtenerIconoEstado(solicitud.getEstado());
        if (iconoEstado != null) estadoLabel.setIcon(iconoEstado);

        switch (solicitud.getEstado()) {
            case "Aceptada":
                card.setBackground(new Color(198, 239, 206)); break;
            case "Rechazada":
                card.setBackground(new Color(255, 199, 206)); break;
            default:
                card.setBackground(new Color(255, 255, 204)); break;
        }

        JPanel notificacion = new JPanel(new BorderLayout());
        notificacion.setOpaque(false);
        notificacion.add(estadoLabel, BorderLayout.EAST);

        card.add(notificacion, BorderLayout.NORTH);
        card.add(imageLabel, BorderLayout.WEST);
        card.add(descriptionLabel, BorderLayout.CENTER);

        return card;
    }

    private ImageIcon obtenerIconoEstado(String estado) {
        String ruta = switch (estado) {
            case "Aceptada" -> "src/resources/EstadoSolicitud/Aceptada.png";
            case "Rechazada" -> "src/resources/EstadoSolicitud/Rechazada.png";
            default -> "src/resources/EstadoSolicitud/EnEspera.png";
        };
        try {
            Image imagen = ImageIO.read(new File(ruta));
            return new ImageIcon(imagen.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        } catch (IOException e) {
            return null;
        }
    }
}
