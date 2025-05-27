package vistas.paneles.controlador;

import vistas.paneles.modelo.PanelAnimalAdminModelo;
import vistas.paneles.vista.PanelAnimalAdminVista;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PanelAnimalAdminControlador extends JPanel {

    private final Object mainFrame;
    private final PanelAnimalAdminVista vista;
    private final PanelAnimalAdminModelo modelo;
    private final String idAnimal;
    private File nuevaImagen = null;

    public PanelAnimalAdminControlador(Object mainFrame, String[] datosAnimal) {
        this.mainFrame = mainFrame;
        this.vista = new PanelAnimalAdminVista();
        this.modelo = new PanelAnimalAdminModelo();
        this.idAnimal = datosAnimal[0];

        setLayout(new BorderLayout());
        add(vista, BorderLayout.CENTER);

        cargarDatos(datosAnimal);
        cargarImagen();

        vista.actualizarButton.addActionListener(e -> actualizarAnimal());
        vista.eliminarButton.addActionListener(e -> eliminarAnimal());
        vista.cambiarImagenButton.addActionListener(e -> seleccionarNuevaImagen());
        vista.revisarSolicitudesButton.addActionListener(e -> mostrarSolicitudes());
    }

    private void cargarDatos(String[] datosAnimal) {
        vista.tipoField.setText(datosAnimal[1]);
        vista.razaField.setText(datosAnimal[2]);
        vista.nombreField.setText(datosAnimal[3]);
        vista.edadField.setText(datosAnimal[4]);
        int solicitudes = modelo.contarSolicitudes(datosAnimal[0]);
        vista.contadorSolicitudesLabel.setText(solicitudes + " solicitudes");
        vista.revisarSolicitudesButton.setEnabled(solicitudes > 0);
    }

    private void cargarImagen() {
        try {
            Image imagen = ImageIO.read(new File("src/resources/animales/" + idAnimal + "_image.png"));
            vista.imageLabel.setIcon(new ImageIcon(imagen.getScaledInstance(250, 250, Image.SCALE_SMOOTH)));
        } catch (IOException e) {
            vista.imageLabel.setText("Imagen no disponible");
        }
    }

    private void actualizarAnimal() {
        if (modelo.actualizarAnimal(idAnimal, vista.tipoField.getText(), vista.razaField.getText(), vista.nombreField.getText(), vista.edadField.getText())) {
            if (nuevaImagen != null) {
                modelo.cambiarImagen(idAnimal, nuevaImagen);
            }
            JOptionPane.showMessageDialog(this, "Datos actualizados exitosamente.");
            try {
                mainFrame.getClass().getMethod("mostrarInicioAdmin").invoke(mainFrame);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar el animal.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarAnimal() {
        int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar este animal?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
                try {
                    
                    // ...dentro de eliminarAnimal(), antes de eliminarSolicitudesDelAnimal...
                    modelo.eliminarSolicitudesDelAnimal(idAnimal, "datos/solicitudes.txt");
                    modelo.eliminarSolicitudesDelAnimal(idAnimal, "datos/solicitudesRevisadas.txt");
                    // ...luego puedes eliminar si quieres limpiar las solicitudes...
                    modelo.eliminarSolicitudesDelAnimal(idAnimal, "datos/solicitudes.txt");
                    modelo.eliminarSolicitudesDelAnimal(idAnimal, "datos/solicitudesRevisadas.txt");
                    // ...existing code...
                    modelo.eliminarAnimal(idAnimal,"datos/animales");
                    modelo.eliminarAnimal(idAnimal,"datos/animalesAceptados");
                    modelo.eliminarDeFavoritos("datos/Favoritos.txt", idAnimal);
                    modelo.eliminarSolicitudesDelAnimal(idAnimal, "datos/solicitudes.txt");
                    modelo.eliminarSolicitudesDelAnimal(idAnimal, "datos/solicitudesRevisadas.txt");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                JOptionPane.showMessageDialog(this, "Animal eliminado.");

                try {
                    mainFrame.getClass().getMethod("mostrarInicioAdmin").invoke(mainFrame);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        else{

                JOptionPane.showMessageDialog(this, "Error al eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }


    private void seleccionarNuevaImagen() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            nuevaImagen = chooser.getSelectedFile();
            cargarImagen();
        }
    }

    private void mostrarSolicitudes() {
        try {
            mainFrame.getClass().getMethod("mostrarSolicitudesAdmin", String.class).invoke(mainFrame, idAnimal);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


// ...existing code...
}
