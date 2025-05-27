package vistas.paneles.vista;

import javax.swing.*;
import java.awt.*;

public class PanelPerfilVista extends JPanel {
    public JLabel lblNombre, lblFotoPerfil;
    public JTextArea txtBiografia;
    public JButton btnCambiarFoto, btnGuardarBiografia, btnVerSolicitudes;
    public JPanel panelFavoritos;

    public PanelPerfilVista() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);


        panelFavoritos = new JPanel(new GridLayout(0, 1, 0, 10));
        panelFavoritos.setBackground(new Color(240, 240, 240));
        JScrollPane scroll = new JScrollPane(panelFavoritos);
        scroll.setPreferredSize(new Dimension(250, getHeight()));
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.getVerticalScrollBar().setUnitIncrement(20);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scroll, BorderLayout.WEST);


        JPanel panelCentral = new JPanel(new GridBagLayout());
        panelCentral.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        lblFotoPerfil = new JLabel("Sin foto", SwingConstants.CENTER);
        lblFotoPerfil.setPreferredSize(new Dimension(300, 300));

        btnCambiarFoto = new JButton("📸 Cambiar Foto");
        lblNombre = new JLabel("Nombre", SwingConstants.CENTER);
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 22));

        txtBiografia = new JTextArea(5, 20);
        txtBiografia.setLineWrap(true);
        txtBiografia.setWrapStyleWord(true);
        txtBiografia.setBorder(BorderFactory.createTitledBorder("Biografía"));

        btnGuardarBiografia = new JButton("💾 Guardar");
        btnVerSolicitudes = new JButton("📋 Ver Solicitudes");

        int y = 0;
        panelCentral.add(lblFotoPerfil, gbcAt(gbc, y++));
        panelCentral.add(btnCambiarFoto, gbcAt(gbc, y++));
        panelCentral.add(lblNombre, gbcAt(gbc, y++));
        panelCentral.add(new JScrollPane(txtBiografia), gbcAt(gbc, y++));
        panelCentral.add(btnGuardarBiografia, gbcAt(gbc, y++));
        panelCentral.add(btnVerSolicitudes, gbcAt(gbc, y++));

        add(panelCentral, BorderLayout.CENTER);
    }

    private GridBagConstraints gbcAt(GridBagConstraints gbc, int y) {
        GridBagConstraints clone = (GridBagConstraints) gbc.clone();
        clone.gridy = y;
        return clone;
    }
}