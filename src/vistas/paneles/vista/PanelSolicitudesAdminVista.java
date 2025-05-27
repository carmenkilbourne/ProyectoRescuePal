package vistas.paneles.vista;

import javax.swing.*;
import java.awt.*;

public class PanelSolicitudesAdminVista extends JPanel {
    public final DefaultListModel<String> modeloLista;
    public final JList<String> listaSolicitudes;
    public final JButton btnAceptar;
    public final JButton btnRechazar;

    public PanelSolicitudesAdminVista() {
        setLayout(new BorderLayout());
        setBackground(new Color(230, 240, 255));

        modeloLista = new DefaultListModel<>();
        listaSolicitudes = new JList<>(modeloLista);
        JScrollPane scrollPane = new JScrollPane(listaSolicitudes);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(new Color(230, 240, 255));

        btnAceptar = crearBoton("Aceptar", new Color(46, 204, 113));
        btnRechazar = crearBoton("Rechazar", new Color(231, 76, 60));

        panelBotones.add(btnAceptar);
        panelBotones.add(btnRechazar);

        add(panelBotones, BorderLayout.SOUTH);
    }
    public void actualizarListaSolicitudes(java.util.List<String> solicitudes) {
        modeloLista.clear();
        for (String solicitud : solicitudes) {
            modeloLista.addElement(solicitud);
        }
    }
    public String getSolicitudSeleccionada() {
        return listaSolicitudes.getSelectedValue();
    }


    private JButton crearBoton(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        boton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return boton;
    }
}
