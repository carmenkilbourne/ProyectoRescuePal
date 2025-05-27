
package vistas.paneles.controlador;


import vistas.paneles.modelo.PanelRegistroModelo;
import vistas.paneles.vista.PanelRegistroVista;

import javax.swing.*;

public class PanelRegistroControlador extends JPanel {
    private final vistas.paneles.vista.PanelRegistroVista vista;
    private final PanelRegistroModelo modelo;
    private final Object mainFrame;

    public PanelRegistroControlador(Object mainFrame) {
        this.mainFrame = mainFrame;
        this.vista = new vistas.paneles.vista.PanelRegistroVista();
        this.modelo = new PanelRegistroModelo();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(vista);

        vista.btnRegistrar.addActionListener(e -> registrar());
        vista.btnGenerar.addActionListener(e -> autocompletar());
    }

    private void registrar() {
        String correo = vista.txtCorreo.getText().trim();
        String password = new String(vista.txtPassword.getPassword()).trim();
        String dni = vista.txtDNI.getText().trim();
        String cp = vista.txtCodigoPostal.getText().trim();
        String fecha = vista.txtFechaNacimiento.getText().trim();
        String tel = vista.txtTelefono.getText().trim();

        if (password.contains(":")) { error("La contraseña no puede contener el carácter ':'"); return; }
        if (!dni.matches("\\d{8}[A-Z]")) { error("DNI no válido."); return; }
        if (!cp.matches("\\d{5}")) { error("Código Postal inválido."); return; }
        if (!fecha.matches("\\d{2}/\\d{2}/\\d{4}")) { error("Fecha inválida."); return; }
        if (!tel.matches("\\d{9}")) { error("Teléfono no válido."); return; }
        if (modelo.correoYaExiste(correo)) { error("Correo ya registrado."); return; }

        if (modelo.guardarUsuario(correo, password, dni, cp, fecha, tel)) {
            JOptionPane.showMessageDialog(this, "Usuario registrado exitosamente.");
            try {
                mainFrame.getClass().getMethod("mostrarLogin").invoke(mainFrame);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            error("Error al guardar el usuario.");
        }
    }

    private void autocompletar() {
        String[] datos = modelo.generarUsuario();
        vista.txtDNI.setText(datos[2]);
        vista.txtCodigoPostal.setText(datos[3]);
        vista.txtFechaNacimiento.setText(datos[4]);
        vista.txtTelefono.setText(datos[5]);
    }

    private void error(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public PanelRegistroVista getVista() {
        return vista;
    }

}
