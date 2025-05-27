package vistas.paneles.controlador;


import vistas.paneles.modelo.PanelAnimalModelo;
import vistas.paneles.vista.PanelAnimalVista;

import javax.swing.*;
import java.awt.*;
public class PanelAnimalControlador extends JPanel {
    private final PanelAnimalVista vista;
    private final PanelAnimalModelo modelo;
    private final Object mainFrame;
    private final String[] datos;

    public PanelAnimalControlador(Object mainFrame, String[] datos) {
        this.mainFrame = mainFrame;
        this.datos = datos;
        this.vista = new PanelAnimalVista();
        this.modelo = new PanelAnimalModelo();

        setLayout(new BorderLayout());
        add(vista, BorderLayout.CENTER);

        cargarDatosAnimal();
        conectarEventos();
    }

    private void cargarDatosAnimal() {
        if (datos.length < 5) {
            JOptionPane.showMessageDialog(this, "Datos del animal incompletos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String rutaImagen = "src/resources/animales/" + datos[0] + "_image.png";
        ImageIcon icono = modelo.cargarIcono(rutaImagen, 450, 450);
        if (icono != null) {
            vista.imageLabel.setIcon(icono);
            vista.imageLabel.setText("");
        }

        vista.nombreLabel.setText("Nombre: " + datos[3]);
        vista.tipoLabel.setText("Tipo: " + datos[1]);
        vista.razaLabel.setText("Raza: " + datos[2]);
        vista.edadLabel.setText("Edad: " + datos[4] + " años");
    }

    private void conectarEventos() {
        vista.btnVolver.addActionListener(e -> {
            try {
                mainFrame.getClass().getMethod("mostrarInicio").invoke(mainFrame);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        vista.btnAdoptar.addActionListener(e -> {
            try {
                Class<?> formClass = Class.forName("vistas.paneles.controlador.FormularioAdopcionControlador");
                String correoUsuario = (String) mainFrame.getClass().getMethod("getCorreoUsuario").invoke(mainFrame);

                formClass.getConstructor(String.class, String.class, String.class)
                        .newInstance(datos[0], datos[3], correoUsuario);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "No se pudo abrir el formulario de adopción.", "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });
    }
}
