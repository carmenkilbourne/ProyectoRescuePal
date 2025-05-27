package vistas.paneles.vista;

import javax.swing.*;
import java.awt.*;

public class PanelSolicitudesUsuarioVista extends JPanel {
    public JPanel panelContenedor;

    public PanelSolicitudesUsuarioVista() {
        setLayout(new BorderLayout());
        setBackground(new Color(250, 250, 255));

        panelContenedor = new JPanel(new GridLayout(0, 3, 10, 50));
        panelContenedor.setBackground(new Color(250, 250, 255));

        JScrollPane scrollPane = new JScrollPane(panelContenedor);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);

        add(scrollPane, BorderLayout.CENTER);
    }
}
