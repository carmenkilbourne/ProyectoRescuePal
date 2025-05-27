package vistas.paneles.controlador;

import vistas.paneles.modelo.PanelSolicitudesAdminModelo;
import vistas.paneles.vista.PanelSolicitudesAdminVista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class PanelSolicitudesAdminControlador extends JPanel {
    private final Object mainFrame;
    private final PanelSolicitudesAdminVista vista;
    private final PanelSolicitudesAdminModelo modelo;
    private final String idAnimal;

    public PanelSolicitudesAdminControlador(Object mainFrame, String idAnimal) {
        this.mainFrame = mainFrame;
        this.idAnimal = idAnimal;
        this.vista = new PanelSolicitudesAdminVista();
        this.modelo = new PanelSolicitudesAdminModelo();

        setLayout(new BorderLayout());
        add(vista, BorderLayout.CENTER);

        cargarSolicitudes();
        conectarEventos();
    }

    private void cargarSolicitudes() {
        List<String> solicitudes = modelo.obtenerSolicitudes(idAnimal);
        vista.actualizarListaSolicitudes(solicitudes);
    }

    private void conectarEventos() {
        vista.btnAceptar.addActionListener(e -> procesarSolicitud(true));
        vista.btnRechazar.addActionListener(e -> procesarSolicitud(false));
    }

    private void procesarSolicitud(boolean aceptar) {
        String solicitudSeleccionada = vista.getSolicitudSeleccionada();

        if (solicitudSeleccionada == null) {
            JOptionPane.showMessageDialog(this, "Selecciona una solicitud.");
            return;
        }

        int opcion = JOptionPane.showConfirmDialog(this,
                "¿Seguro que quieres " + (aceptar ? "aceptar" : "rechazar") + " esta solicitud?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION);

        if (opcion != JOptionPane.YES_OPTION) return;

        if (aceptar) {
            modelo.aceptarSolicitud(idAnimal,solicitudSeleccionada);
        } else {
            modelo.rechazarSolicitud(solicitudSeleccionada);
        }

        try {
            mainFrame.getClass().getMethod("mostrarInicioAdmin").invoke(mainFrame);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
