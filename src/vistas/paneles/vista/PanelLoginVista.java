
package vistas.paneles.vista;

import javax.swing.*;
import java.awt.*;

public class PanelInicioVista extends JPanel {
    public JPanel panelContenedor;
    public JScrollPane scrollPane;

    public PanelInicioVista() {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 245));

        panelContenedor = new JPanel(new GridLayout(0, 3, 10, 50));
        panelContenedor.setBackground(Color.WHITE);

        scrollPane = new JScrollPane(panelContenedor);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);

        add(scrollPane, BorderLayout.CENTER);
    }
}




