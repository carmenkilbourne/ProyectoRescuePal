package vistas.paneles.vista;

import javax.swing.*;
import java.awt.*;

public class FormularioAdopcionVista extends JPanel {
    public JComboBox<String>[] respuestas;
    public JButton btnEnviar;

    private final String[] preguntas = {
            "¿Tiene experiencia previa tratando con mascotas?",
            "¿Tiene disponibilidad horaria para dedicarle a su mascota?",
            "¿De qué espacio dispone en su hogar?",
            "¿La vivienda es propia o de alquiler?",
            "¿Cuántas personas viven en su hogar y qué opinan sobre la adopción?",
            "¿Hay niños en casa? Si es así, ¿han convivido antes con animales?",
            "¿Qué haría si su mascota presenta problemas de comportamiento?",
            "¿Está dispuesto a dedicar tiempo a entrenar y educar al animal?",
            "¿Tiene conocimientos sobre la especie y raza del animal que desea adoptar?",
            "En caso de viajar o ausentarse, ¿quién cuidaría del animal?",
            "¿Ha calculado cuánto cuesta mantener una mascota anualmente?",
            "¿Qué haría con su mascota si tuviera que mudarse?",
            "Si su situación económica o personal cambia, ¿seguirá comprometido con el cuidado del animal?"
    };

    private final String[][] opciones = {
            {"", "Sí, tengo experiencia", "Algo de experiencia", "No tengo experiencia"},
            {"", "Sí, tengo tiempo suficiente", "Depende del día", "No tengo tiempo"},
            {"", "250+", "175-250m2", "0-175m2"},
            {"", "Propia o alquiler que permite mascotas", "Alquiler sin restricciones explícitas sobre mascotas", "Alquiler donde no se permite"},
            {"", "Todos están de acuerdo", "Algunos tienen dudas", "Alguien no quiere o es alérgico"},
            {"", "Sí, saben tratar animales", "Sí, pero sin experiencia", "No, y preocupa"},
            {"", "Buscaría ayuda profesional", "Intentaría corregirlo", "Podría devolverlo"},
            {"", "Sí, compromiso de educarlo", "Depende del tiempo disponible", "No, espero que se adapte solo"},
            {"", "Sí, sé sus necesidades", "Tengo idea básica", "No sé mucho"},
            {"", "Tengo un plan", "Improvisaría", "No tengo plan"},
            {"", "Sí, calculado", "Idea aproximada", "No considerado"},
            {"", "Buscaría vivienda pet-friendly", "Intentaría reubicarlo", "Podría devolverlo"},
            {"", "Sí, buscaría soluciones", "Depende de la situación", "Buscaría otra opción para él"}
    };

    public FormularioAdopcionVista() {
        setLayout(new BorderLayout());

        JPanel panelPreguntas = new JPanel(new GridLayout(0, 1, 10, 10));
        respuestas = new JComboBox[preguntas.length];

        for (int i = 0; i < preguntas.length; i++) {
            panelPreguntas.add(new JLabel((i + 1) + ". " + preguntas[i]));
            respuestas[i] = new JComboBox<>(opciones[i]);
            panelPreguntas.add(respuestas[i]);
        }

        JScrollPane scrollPane = new JScrollPane(panelPreguntas);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        btnEnviar = new JButton("Enviar Solicitud");

        JPanel panelBoton = new JPanel();
        panelBoton.add(btnEnviar);

        add(scrollPane, BorderLayout.CENTER);
        add(panelBoton, BorderLayout.SOUTH);
    }
}
