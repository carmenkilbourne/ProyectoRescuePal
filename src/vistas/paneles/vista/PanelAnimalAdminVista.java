package vistas.paneles.vista;

import javax.swing.*;
import java.awt.*;

public class PanelAnimalAdminVista extends JPanel {
    public JLabel imageLabel, contadorSolicitudesLabel;
    public JTextField nombreField, tipoField, razaField, edadField;
    public JButton actualizarButton, eliminarButton, cambiarImagenButton, revisarSolicitudesButton;

    public PanelAnimalAdminVista() {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 248, 255));

        imageLabel = new JLabel("Sin imagen", SwingConstants.CENTER);
        imageLabel.setPreferredSize(new Dimension(250, 250));

        JPanel panelInfo = new JPanel(new GridLayout(6, 2, 10, 10));
        panelInfo.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        panelInfo.setBackground(new Color(240, 248, 255));

        nombreField = new JTextField();
        tipoField = new JTextField();
        razaField = new JTextField();
        edadField = new JTextField();
        contadorSolicitudesLabel = new JLabel("0 solicitudes");

        actualizarButton = new JButton("Actualizar Datos");
        eliminarButton = new JButton("Eliminar Animal");
        cambiarImagenButton = new JButton("Cambiar Imagen");
        revisarSolicitudesButton = new JButton("Revisar solicitudes");

        JPanel solicitudesPanel = new JPanel(new BorderLayout());
        solicitudesPanel.setOpaque(false);
        solicitudesPanel.add(contadorSolicitudesLabel, BorderLayout.WEST);
        solicitudesPanel.add(revisarSolicitudesButton, BorderLayout.EAST);

        panelInfo.add(new JLabel("Nombre:")); panelInfo.add(nombreField);
        panelInfo.add(new JLabel("Tipo:")); panelInfo.add(tipoField);
        panelInfo.add(new JLabel("Raza:")); panelInfo.add(razaField);
        panelInfo.add(new JLabel("Edad:")); panelInfo.add(edadField);
        panelInfo.add(new JLabel("Solicitudes:")); panelInfo.add(solicitudesPanel);
        panelInfo.add(actualizarButton); panelInfo.add(eliminarButton);

        add(imageLabel, BorderLayout.WEST);
        add(panelInfo, BorderLayout.CENTER);
        add(cambiarImagenButton, BorderLayout.SOUTH);
    }
}
