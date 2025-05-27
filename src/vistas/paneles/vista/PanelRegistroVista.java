package vistas.paneles.vista;

import javax.swing.*;
import java.awt.*;

public class PanelRegistroVista extends JPanel {
    public JTextField txtCorreo, txtDNI, txtCodigoPostal, txtFechaNacimiento, txtTelefono;
    public JPasswordField txtPassword;
    public JButton btnRegistrar, btnGenerar;

    public PanelRegistroVista() {
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;
        txtCorreo = crearCampo(this, gbc, "Correo Electrónico:", row++);
        txtPassword = new JPasswordField(15);
        agregarCampo(this, gbc, "Contraseña:", txtPassword, row++);
        txtDNI = crearCampo(this, gbc, "DNI:", row++);
        txtCodigoPostal = crearCampo(this, gbc, "Código Postal:", row++);
        txtFechaNacimiento = crearCampo(this, gbc, "Fecha de Nacimiento (DD/MM/YYYY):", row++);
        txtTelefono = crearCampo(this, gbc, "Número de Teléfono:", row++);

        btnRegistrar = new JButton("Registrar");
        gbc.gridx = 0; gbc.gridy = row++; gbc.gridwidth = 2;
        add(btnRegistrar, gbc);

        btnGenerar = new JButton("Generar");
        gbc.gridy = row;
        add(btnGenerar, gbc);
    }

    private JTextField crearCampo(JPanel panel, GridBagConstraints gbc, String label, int row) {
        JTextField textField = new JTextField(15);
        agregarCampo(panel, gbc, label, textField, row);
        return textField;
    }

    private void agregarCampo(JPanel panel, GridBagConstraints gbc, String label, JComponent field, int row) {
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 1;
        panel.add(new JLabel(label), gbc);
        gbc.gridx = 1;
        panel.add(field, gbc);
    }
}

