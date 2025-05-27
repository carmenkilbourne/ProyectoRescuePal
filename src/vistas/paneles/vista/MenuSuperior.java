package vistas.paneles.vista;


import javax.swing.*;
import java.awt.*;

import static otros.VisualInfo.color1;
import static otros.VisualInfo.color5;

public class MenuSuperior extends JPanel {
    private final JLabel frase;

    public MenuSuperior() {
        setPreferredSize(new Dimension(1720, 100));
        setBackground(color1);

        frase = new JLabel("Adopta, no compres", SwingConstants.CENTER);
        frase.setFont(new Font("Arial", Font.BOLD, 30));
        frase.setForeground(color5);

        setLayout(new BorderLayout());
        add(frase, BorderLayout.CENTER);


        JPanel panelDerecha = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelDerecha.setOpaque(false);
        add(panelDerecha, BorderLayout.EAST);
    }

    /**
     * Cambia el texto que se muestra en el menú superior.
     * @param nuevoTexto El nuevo mensaje a mostrar.
     */
    public void setFrase(String nuevoTexto) {
        frase.setText(nuevoTexto);
    }
}
