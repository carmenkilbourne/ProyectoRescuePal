package vistas.paneles.vista;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static otros.VisualInfo.*;

public class PanelLoginVista extends JPanel {
    public JTextField txtUsuario;
    public JPasswordField txtPassword;
    public JButton btnLogin, btnRegistro;

    public PanelLoginVista() {
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;
        JLabel iconoUsuario = new JLabel(createIcon("src/resources/user_icon.png", 100, 100));
        add(iconoUsuario, gbcAt(gbc,row++));
        txtUsuario = crearCampoTexto("E-mail / Username", gbc, row++);
        txtPassword = new JPasswordField(20);
        txtPassword.setPreferredSize(new Dimension(200, 35));
        txtPassword.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(new JLabel("Password"), gbcAt(gbc, row++));
        add(txtPassword, gbcAt(gbc, row++));

        btnLogin = new JButton("Log in");
        btnLogin.setBorderPainted(false);
        btnLogin.setBackground(color2);
        btnLogin.setPreferredSize(new Dimension(110, 35));
        add(btnLogin, gbcAt(gbc, row++));

        btnRegistro = new JButton("Sign Up Now");
        btnRegistro.setBorderPainted(false);
        btnRegistro.setBackground(color2);
        btnRegistro.setPreferredSize(new Dimension(110, 35));
        add(btnRegistro, gbcAt(gbc, row));
    }

    private JTextField crearCampoTexto(String label, GridBagConstraints gbc, int row) {
        add(new JLabel(label), gbcAt(gbc, row++));
        JTextField campo = new JTextField(20);
        campo.setPreferredSize(new Dimension(200, 35));
        campo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(campo, gbcAt(gbc, row));
        return campo;
    }

    private GridBagConstraints gbcAt(GridBagConstraints gbc, int y) {
        GridBagConstraints clone = (GridBagConstraints) gbc.clone();
        clone.gridy = y;
        clone.gridx = 0;
        clone.gridwidth = 2;
        return clone;
    }
    private ImageIcon createIcon(String ruta, int ancho, int alto) {
        try {
            Image imagen = ImageIO.read(new File(ruta));
            return new ImageIcon(imagen.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH));
        } catch (IOException e) {
            System.err.println("No se pudo cargar la imagen: " + ruta);
            return null;
        }
    }
}
