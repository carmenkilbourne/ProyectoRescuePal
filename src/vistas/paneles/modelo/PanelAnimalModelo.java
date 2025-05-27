package vistas.paneles.modelo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PanelAnimalModelo {
    public ImageIcon cargarIcono(String ruta, int ancho, int alto) {
        try {
            Image imagen = ImageIO.read(new File(ruta));
            return new ImageIcon(imagen.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH));
        } catch (IOException e) {
            return null;
        }
    }
}