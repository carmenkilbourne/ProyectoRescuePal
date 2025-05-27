package vistas.paneles.vista;

import javax.swing.*;
import java.awt.*;

public class PanelGestionUsuarioVista extends JPanel {
    public JPanel listaUsuarios;
    public JButton btnAgregar;

    public PanelGestionUsuarioVista() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Registro de Usuarios", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        add(titulo, BorderLayout.NORTH);

        listaUsuarios = new JPanel();
        listaUsuarios.setLayout(new BoxLayout(listaUsuarios, BoxLayout.Y_AXIS));

        JPanel contenedorCentral = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contenedorCentral.add(listaUsuarios);

        JScrollPane scroll = new JScrollPane(contenedorCentral,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        add(scroll, BorderLayout.CENTER);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnAgregar = new JButton("Agregar Usuario");

        botones.add(btnAgregar);

        add(botones, BorderLayout.SOUTH);
    }
}
