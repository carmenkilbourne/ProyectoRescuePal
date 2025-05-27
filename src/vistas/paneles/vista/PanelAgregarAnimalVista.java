package vistas.paneles.vista;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class PanelAgregarAnimalVista extends JPanel {
    public JTextField nombreField, tipoField, razaField, edadField;
    public JButton seleccionarImagenButton, agregarButton;
    public JLabel estadoImagenLabel;


    public PanelAgregarAnimalVista() {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 248, 255));

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBackground(new Color(240, 248, 255));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        nombreField = new JTextField();
        tipoField = new JTextField();
        razaField = new JTextField();
        edadField = new JTextField();
        seleccionarImagenButton = new JButton("Seleccionar Imagen");
        agregarButton = new JButton("Agregar Animal");

        estadoImagenLabel = new JLabel("Imagen no seleccionada.");
        estadoImagenLabel.setForeground(Color.RED);

        formPanel.add(new JLabel("Nombre:"));
        formPanel.add(nombreField);
        formPanel.add(new JLabel("Tipo de Animal:"));
        formPanel.add(tipoField);
        formPanel.add(new JLabel("Raza / Especie:"));
        formPanel.add(razaField);
        formPanel.add(new JLabel("Edad:"));
        formPanel.add(edadField);
        formPanel.add(seleccionarImagenButton);
        formPanel.add(estadoImagenLabel);
        formPanel.add(new JLabel());
        formPanel.add(agregarButton);

        add(formPanel, BorderLayout.CENTER);
    }
}
