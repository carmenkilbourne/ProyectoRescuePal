package vistas.paneles.vista;


import javax.swing.*;
import java.awt.*;

public class PanelAnimalVista extends JPanel {
    public JLabel imageLabel, nombreLabel, tipoLabel, razaLabel, edadLabel;
    public JButton btnVolver, btnAdoptar;

    public PanelAnimalVista() {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 248, 255));

        imageLabel = new JLabel("Imagen no disponible", SwingConstants.CENTER);
        imageLabel.setPreferredSize(new Dimension(450, 450));

        JPanel panelImagen = new JPanel();
        panelImagen.setBackground(Color.WHITE);
        panelImagen.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
        panelImagen.add(imageLabel);

        JPanel infoPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        infoPanel.setBackground(Color.WHITE);

        nombreLabel = new JLabel();
        tipoLabel = new JLabel();
        razaLabel = new JLabel();
        edadLabel = new JLabel();

        nombreLabel.setFont(new Font("Arial", Font.BOLD, 35));
        tipoLabel.setFont(new Font("Arial", Font.BOLD, 35));
        razaLabel.setFont(new Font("Arial", Font.BOLD, 35));
        edadLabel.setFont(new Font("Arial", Font.BOLD, 35));

        infoPanel.add(nombreLabel);
        infoPanel.add(tipoLabel);
        infoPanel.add(razaLabel);
        infoPanel.add(edadLabel);

        JPanel botonesPanel = new JPanel();
        botonesPanel.setBackground(new Color(230, 230, 250));

        btnVolver = new JButton("⬅ Volver");
        btnVolver.setFont(new Font("Arial", Font.BOLD, 30));

        btnAdoptar = new JButton("💖 Solicitar Adopción");
        btnAdoptar.setFont(new Font("Arial", Font.BOLD, 30));

        botonesPanel.add(btnVolver);
        botonesPanel.add(btnAdoptar);

        JPanel contenedor = new JPanel(new BorderLayout(20, 20));
        contenedor.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        contenedor.setBackground(new Color(240, 248, 255));
        contenedor.add(panelImagen, BorderLayout.WEST);
        contenedor.add(infoPanel, BorderLayout.CENTER);

        add(contenedor, BorderLayout.CENTER);
        add(botonesPanel, BorderLayout.SOUTH);
    }
}