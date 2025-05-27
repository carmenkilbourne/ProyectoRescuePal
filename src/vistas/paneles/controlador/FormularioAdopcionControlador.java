package vistas.paneles.controlador;

import vistas.paneles.vista.FormularioAdopcionVista;
import vistas.paneles.modelo.FormularioAdopcionModelo;

import javax.swing.*;
import java.awt.*;

public class FormularioAdopcionControlador extends JFrame {
    private final FormularioAdopcionVista vista;
    private final FormularioAdopcionModelo modelo;
    private final String IDAnimal;
    private final String nombreAnimal;
    private final String correoUsuario;

    public FormularioAdopcionControlador(String IDAnimal, String nombreAnimal, String correoUsuario) {
        this.IDAnimal = IDAnimal;
        this.nombreAnimal = nombreAnimal;
        this.correoUsuario = correoUsuario;

        this.modelo = new FormularioAdopcionModelo();
        this.vista = new FormularioAdopcionVista();

        setTitle("Formulario de Adopción - " + nombreAnimal);
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        add(vista, BorderLayout.CENTER);

        vista.btnEnviar.addActionListener(e -> procesarFormulario());

        setVisible(true);
    }

    private void procesarFormulario() {
        if (modelo.existeSolicitud(correoUsuario, IDAnimal)) {
            JOptionPane.showMessageDialog(this, "Ya has enviado una solicitud para este animal.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double puntajeTotal = 0;
        StringBuilder respuestasTexto = new StringBuilder();

        for (int i = 0; i < vista.respuestas.length; i++) {
            int seleccion = vista.respuestas[i].getSelectedIndex();
            if (seleccion == 0) {
                JOptionPane.showMessageDialog(this, "Por favor, responda todas las preguntas.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            puntajeTotal += modelo.getPuntuaciones()[Math.min(seleccion, modelo.getPuntuaciones().length - 1)];
            respuestasTexto.append("Pregunta ").append(i + 1).append(": ")
                    .append(vista.respuestas[i].getSelectedItem()).append("<br>");
        }

        String resultado;
        if (puntajeTotal < 5) {
            resultado = "❌No Apto❌<br>";
        } else if (puntajeTotal < 10) {
            resultado = "⚠️Revisión⚠️<br>";
        } else {
            resultado = "✅Apto✅<br>";
        }

        modelo.guardarSolicitud(correoUsuario, IDAnimal, respuestasTexto.toString(), puntajeTotal, resultado);
        JOptionPane.showMessageDialog(this, "La solicitud se ha enviado correctamente.");
        dispose();
    }
}
