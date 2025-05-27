package vistas.paneles.controlador;

import vistas.paneles.modelo.PanelAgregarAnimalModelo;
import vistas.paneles.vista.PanelAgregarAnimalVista;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class PanelAgregarAnimalControlador extends JPanel {
    private final PanelAgregarAnimalVista vista;
    private final PanelAgregarAnimalModelo modelo;
    private final Object mainFrame;
    private File imagenSeleccionada;


    public PanelAgregarAnimalControlador(Object mainFrame) {
        this.mainFrame = mainFrame;
        this.vista = new PanelAgregarAnimalVista();
        this.modelo = new PanelAgregarAnimalModelo();

        setLayout(new BorderLayout());
        add(vista, BorderLayout.CENTER);

        vista.seleccionarImagenButton.addActionListener(e -> seleccionarImagen());
        vista.agregarButton.addActionListener(e -> agregarAnimal());
    }

    private void seleccionarImagen() {
        JFileChooser fileChooser = new JFileChooser();
        int opcion = fileChooser.showOpenDialog(this);
        if (opcion == JFileChooser.APPROVE_OPTION) {
            imagenSeleccionada = fileChooser.getSelectedFile();
            vista.estadoImagenLabel.setText("Imagen seleccionada ✔");
            vista.estadoImagenLabel.setForeground(new Color(0, 153, 0));
        } else {
            imagenSeleccionada = null;
            vista.estadoImagenLabel.setText("Imagen no seleccionada.");
            vista.estadoImagenLabel.setForeground(Color.RED);
        }
    }

    private void agregarAnimal() {
        String nombre = vista.nombreField.getText().trim();
        String tipo = vista.tipoField.getText().trim();
        String raza = vista.razaField.getText().trim();
        String edad = vista.edadField.getText().trim();

        if (nombre.isEmpty() || tipo.isEmpty() || raza.isEmpty() || edad.isEmpty() || imagenSeleccionada == null) {
            JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos y selecciona una imagen.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (modelo.guardarAnimal(tipo, raza, nombre, edad, imagenSeleccionada)) {
            JOptionPane.showMessageDialog(this, "Animal agregado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            try {
                mainFrame.getClass().getMethod("mostrarInicioAdmin").invoke(mainFrame);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Error al agregar el animal.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public PanelAgregarAnimalVista getVista() {
        return vista;
    }
    public PanelAgregarAnimalModelo getModelo() {
        return modelo;
    }
    public void setImagenSeleccionada(File imagen) {
        this.imagenSeleccionada = imagen;
    }
}
