package vistas.paneles.vista;

import javax.swing.*;
import java.awt.*;

public class PanelInicioAdminVista extends JPanel {
    public JPanel panelContenedor;

    public PanelInicioAdminVista() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        panelContenedor = new JPanel(new GridLayout(0, 3, 10, 50));
        panelContenedor.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(panelContenedor);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);

        add(scrollPane, BorderLayout.CENTER);
    }
}
