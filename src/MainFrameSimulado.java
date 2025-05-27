/*package otros;

import vistas.paneles.modelo.*;
import vistas.paneles.vista.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static otros.VisualInfo.color1;
import static otros.VisualInfo.color5;

public class CargaBaseDatos {

    public CargaBaseDatos() {
        String RUTA_BASE_DATOS = "datos/";
        File directorio = new File(RUTA_BASE_DATOS);
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Directorio 'datos/' creado exitosamente.");
            } else {
                System.err.println("No se pudo crear el directorio 'datos/'. Verifica permisos.");
            }
        }
        String animales = "datos/animales.txt";
        String FichaUsuario = "datos/FichaUsuario.txt";
        String solicitudes = "datos/solicitudes.txt";
        String solicitudesAceptadas = "datos/solicitudesAceptadas.txt";
        String usuarios = "datos/usuarios.txt";

        File directorioAnimales = new File(animales);
        File directorioFichaUsuario = new File(FichaUsuario);
        File directorioSolicitudes = new File(solicitudes);
        File directorioSolicitudesAceptada = new File(solicitudesAceptadas);
        File directorioUsers = new File(usuarios);


        if (!directorioUsers.exists()) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(usuarios, true));
                writer.write("");
                CrearAdmin();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!directorioAnimales.exists()) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(animales));
                writer.write("");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!directorioSolicitudes.exists()) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(solicitudes));
                writer.write("");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!directorioSolicitudesAceptada.exists()) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(solicitudesAceptadas));
                writer.write("");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!directorioFichaUsuario.exists()) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(FichaUsuario));
                writer.write("");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void CrearAdmin() {
        String usuario = "a";
        String password = "a:admin";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("datos/usuarios.txt", true))) {
            bw.write(usuario + ":" + password + "\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void CrearAnimales() {
        String[] Tipo = {
                "Perro", "Perro", "Perro", "Gato", "Gato", "Gato", "Erizo", "Huron"
        };
        String[] Raza = {
                "Labrador", "Salchicha", "Border Collie", "Esfinge", "Bengalí", "Scottish Fold", "Africano", "Albino"
        };
        String[] Nombre = {
                "Max", "Chorizo", "Chico", "MewTwo", "Misifu", "Bola", "Pinchos", "Blanquito"
        };
        String[] Edad = {
                "2", "3", "1", "2", "4", "15", "1", "2"
        };
        for (int i = 0; i < Nombre.length; i++) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("datos/animales.txt", true))) {
                bw.write("Animal:" + Tipo[i] + "," + Raza[i] + "," + Nombre[i] + "," + Edad[i]);
                bw.newLine();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
package otros;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SolicitudAdopcion {
    private final String usuario;
    private final String IDanimal;
    private final String estado;

    public SolicitudAdopcion(String usuario, String IDanimal, String estado) {
        this.usuario = usuario;
        this.IDanimal = IDanimal;
        this.estado = estado;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getIDanimal() {
        return IDanimal;
    }

    public String getEstado() {
        return estado;
    }

    public static String obtenerNombreAnimalPorID(String idAnimal) {
        try (BufferedReader br = new BufferedReader(new FileReader("datos/animales.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(":");
                if (partes.length >= 5 && partes[0].equals(idAnimal)) {
                    return partes[3]; // Nombre del animal
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Desconocido";
    }
}
package otros;

import java.awt.*;

public class VisualInfo {
    public static final Font Botones = new Font("Arial", Font.PLAIN, 20);
    public static final Font Nombres = new Font("Arial", Font.PLAIN, 24);
    public static final Font Historia = new Font("Arial", Font.BOLD, 12);
    public static final Color color0 = new Color(230, 230, 230);
    public static final Color color1 = new Color(223, 235, 246);
    public static final Color color2 = new Color(170, 199, 216);
    public static final Color color3 = new Color(118, 138, 150);
    public static final Color color4 = new Color(68, 87, 109);
    public static final Color color5 = new Color(41, 53, 60);
    public static final Color colorEspera = new Color(253,253,150);
    public static final Color colorAceptado = new Color(119, 221, 119);
    public static final Color colorRechazado = new Color(255, 105, 97);




}
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
package vistas.paneles.controlador;

import vistas.paneles.modelo.PanelAnimalAdminModelo;
import vistas.paneles.vista.PanelAnimalAdminVista;

import javax.imageio.ImageIO;
import javax.swing.*;
        import java.awt.*;
        import java.io.File;
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
            if (modelo.eliminarAnimal(idAnimal)) {
                JOptionPane.showMessageDialog(this, "Animal eliminado.");
                try {
                    mainFrame.getClass().getMethod("mostrarInicioAdmin").invoke(mainFrame);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
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
}
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
package vistas.paneles.controlador;

import vistas.paneles.modelo.PanelInicioAdminModelo;
import vistas.paneles.vista.PanelInicioAdminVista;

import javax.imageio.ImageIO;
import javax.swing.*;
        import java.awt.*;
        import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class PanelInicioAdminControlador extends JPanel {
    private final Object mainFrame;
    private final PanelInicioAdminVista vista;
    private final PanelInicioAdminModelo modelo;

    public PanelInicioAdminControlador(Object mainFrame) {
        this.mainFrame = mainFrame;
        this.vista = new PanelInicioAdminVista();
        this.modelo = new PanelInicioAdminModelo();
        setLayout(new BorderLayout());
        add(vista, BorderLayout.CENTER);

        cargarTarjetas();
    }

    private void cargarTarjetas() {
        vista.panelContenedor.add(crearTarjetaAgregarAnimal());

        List<String[]> animales = modelo.cargarAnimalesDesdeArchivo("datos/animales.txt");
        for (String[] datos : animales) {
            vista.panelContenedor.add(crearTarjetaAnimal(datos));
        }

        vista.revalidate();
        vista.repaint();
    }

    private JPanel crearTarjetaAgregarAnimal() {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(new Color(70, 130, 180)); // color3
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        card.setPreferredSize(new Dimension(200, 200));

        JLabel plusLabel = new JLabel("+", SwingConstants.CENTER);
        plusLabel.setFont(new Font("Arial", Font.BOLD, 48));
        plusLabel.setForeground(Color.WHITE);

        card.add(plusLabel, BorderLayout.CENTER);

        card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    mainFrame.getClass().getMethod("cambiarPanel", JPanel.class)
                            .invoke(mainFrame, Class.forName("vistas.paneles.controlador.PanelAgregarAnimalControlador")
                                    .getConstructor(Object.class)
                                    .newInstance(mainFrame));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return card;
    }

    private JPanel crearTarjetaAnimal(String[] datos) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(new Color(225, 245, 254)); // color2
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        card.setPreferredSize(new Dimension(200, 200));

        JLabel imageLabel = new JLabel();
        String rutaImagen = "src/resources/animales/" + datos[0] + "_image.png";

        try {
            Image imagen = ImageIO.read(new File(rutaImagen));
            imageLabel.setIcon(new ImageIcon(imagen.getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
        } catch (IOException e) {
            imageLabel.setText("Imagen no disponible");
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        }

        JLabel descriptionLabel = new JLabel("<html>Nombre: " + datos[3] +
                "<br>Animal: " + datos[1] +
                "<br>Raza: " + datos[2] +
                "<br>Edad: " + datos[4] + " años</html>", SwingConstants.CENTER);
        descriptionLabel.setFont(new Font("Arial", Font.BOLD, 19));

        card.add(imageLabel, BorderLayout.WEST);
        card.add(descriptionLabel, BorderLayout.CENTER);

        if (modelo.tieneNotificacion(datos[0])) {
            JLabel notiLabel = new JLabel();
            try {
                Image iconoNoti = ImageIO.read(new File("src/resources/menu/InboxNotificacion.png"));
                notiLabel.setIcon(new ImageIcon(iconoNoti.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
            } catch (IOException e) {
                notiLabel.setText("Notificación");
            }

            JPanel notificacion = new JPanel(new BorderLayout());
            notificacion.setOpaque(false);
            notificacion.add(notiLabel, BorderLayout.EAST);
            card.add(notificacion, BorderLayout.NORTH);
        }

        card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    mainFrame.getClass().getMethod("cambiarPanel", JPanel.class)
                            .invoke(mainFrame, Class.forName("vistas.paneles.controlador.PanelAnimalAdminControlador")
                                    .getConstructor(Object.class, String[].class)
                                    .newInstance(mainFrame, datos));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return card;
    }
}
package vistas.paneles.controlador;

import vistas.paneles.modelo.PanelInicioModelo;
import vistas.paneles.vista.PanelInicioVista;

import javax.imageio.ImageIO;
import javax.swing.*;
        import java.awt.*;
        import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class PanelInicioControlador extends JPanel {
    private final Object mainFrame;
    private final PanelInicioVista vista;
    private final PanelInicioModelo modelo;

    public PanelInicioControlador(Object mainFrame) {
        this.mainFrame = mainFrame;
        this.vista = new PanelInicioVista();
        this.modelo = new PanelInicioModelo();

        setLayout(new BorderLayout());
        add(vista, BorderLayout.CENTER);

        cargarTarjetas();
    }

    private void cargarTarjetas() {
        List<String[]> animales = modelo.cargarAnimales();
        for (String[] datos : animales) {
            vista.panelContenedor.add(crearTarjetaAnimal(datos));
        }
        vista.revalidate();
        vista.repaint();
    }

    private JPanel crearTarjetaAnimal(String[] datos) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(new Color(225, 245, 254));
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        JLabel imageLabel = new JLabel();
        String rutaImagen = "src/resources/animales/" + datos[0] + "_image.png";
        try {
            Image imagen = ImageIO.read(new File(rutaImagen));
            imageLabel.setIcon(new ImageIcon(imagen.getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
        } catch (IOException e) {
            imageLabel.setText("Imagen no disponible");
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        }

        JLabel descriptionLabel = new JLabel("<html>Nombre: " + datos[3] +
                "<br>Animal: " + datos[1] +
                "<br>Raza: " + datos[2] +
                "<br>Edad: " + datos[4] + " años</html>", SwingConstants.CENTER);
        descriptionLabel.setFont(new Font("Arial", Font.BOLD, 16));

        card.add(imageLabel, BorderLayout.WEST);
        card.add(descriptionLabel, BorderLayout.CENTER);

        try {
            boolean logueado = (boolean) mainFrame.getClass().getMethod("isUsuarioLogueado").invoke(mainFrame);
            if (logueado) {
                JButton botonFavorito = crearBotonFavorito(datos);
                JPanel panelTop = new JPanel(new BorderLayout());
                panelTop.setOpaque(false);
                panelTop.add(botonFavorito, BorderLayout.EAST);
                card.add(panelTop, BorderLayout.NORTH);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    boolean logueado = (boolean) mainFrame.getClass().getMethod("isUsuarioLogueado").invoke(mainFrame);
                    if (logueado) {
                        vistas.paneles.controlador.PanelAnimalControlador panelAnimal = new vistas.paneles.controlador.PanelAnimalControlador(mainFrame, datos);
                        mainFrame.getClass().getMethod("cambiarPanel", JPanel.class)
                                .invoke(mainFrame, panelAnimal);
                    } else {
                        JOptionPane.showMessageDialog(null, "Debes iniciar sesión para acceder a los detalles del animal.");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return card;
    }

    private JButton crearBotonFavorito(String[] datos) {
        JButton boton = new JButton();
        boton.setOpaque(false);
        boton.setBackground(new Color(225, 245, 254));
        boton.setBorderPainted(false);

        try {
            String usuario = (String) mainFrame.getClass().getMethod("getCorreoUsuario").invoke(mainFrame);
            boolean esFavorito = modelo.esFavorito(usuario, datos[0]);

            String iconoPath = esFavorito
                    ? "src/resources/menu/FavoritoTrue.png"
                    : "src/resources/menu/FavoritoFalse.png";

            Image imagen = ImageIO.read(new File(iconoPath));
            boton.setIcon(new ImageIcon(imagen.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        } catch (Exception e) {
            boton.setText("❤");
        }

        boton.addActionListener(e -> {
            try {
                String usuario = (String) mainFrame.getClass().getMethod("getCorreoUsuario").invoke(mainFrame);
                boolean esFavorito = modelo.esFavorito(usuario, datos[0]);
                modelo.modificarFavorito(usuario, datos[0], !esFavorito);

                // AQUÍ ES DONDE HACEMOS COMO EN TU PROYECTO ORIGINAL:
                mainFrame.getClass().getMethod("mostrarInicio").invoke(mainFrame);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        return boton;
    }
}
package vistas.paneles.controlador;

import vistas.paneles.modelo.PanelLoginModelo;
import vistas.paneles.vista.PanelLoginVista;

import javax.swing.*;
        import java.awt.*;
        import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PanelLoginControlador extends JPanel {
    private final PanelLoginVista vista;
    private final PanelLoginModelo modelo;
    private final Object mainFrame;  // <--- Usamos Object

    public PanelLoginControlador(Object mainFrame) {
        this.mainFrame = mainFrame;
        this.vista = new PanelLoginVista();
        this.modelo = new PanelLoginModelo();
        setLayout(new BorderLayout());
        add(vista, BorderLayout.CENTER);

        vista.btnLogin.addActionListener(e -> iniciarSesion());
        vista.btnRegistro.addActionListener(e -> mostrarRegistro());
    }

    private void iniciarSesion() {
        String usuario = vista.txtUsuario.getText().trim();
        String password = new String(vista.txtPassword.getPassword()).trim();

        if (usuario.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, completa ambos campos.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader("datos/usuarios.txt"))) {
            String linea;
            boolean encontrado = false;

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(":");
                if (partes.length >= 2 && partes[0].equals(usuario)) {
                    encontrado = true;
                    String passwordArchivo = partes[1];

                    if (passwordArchivo.equals(password)) {
                        if (partes.length == 3 && partes[2].equals("admin")) {
                            mainFrame.getClass().getMethod("setUsuarioLogueado", boolean.class, String.class, String.class)
                                    .invoke(mainFrame, true, usuario, "admin");
                            mainFrame.getClass().getMethod("mostrarInicioAdmin").invoke(mainFrame);
                        } else {
                            mainFrame.getClass().getMethod("setUsuarioLogueado", boolean.class, String.class, String.class)
                                    .invoke(mainFrame, true, usuario, "user");
                            mainFrame.getClass().getMethod("mostrarInicio").invoke(mainFrame);
                        }
                        return;
                    } else {
                        JOptionPane.showMessageDialog(this, "Contraseña incorrecta.");
                        return;
                    }
                }
            }

            if (!encontrado) {
                JOptionPane.showMessageDialog(this, "Usuario no encontrado.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al procesar el inicio de sesión.");
            e.printStackTrace();
        }
    }

    private void mostrarRegistro() {
        try {
            mainFrame.getClass().getMethod("mostrarPanelRegistro").invoke(mainFrame);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public PanelLoginVista getVista() {
        return vista;
    }

}
package vistas.paneles.controlador;

import vistas.paneles.modelo.PanelPerfilModelo;
import vistas.paneles.vista.PanelPerfilVista;

import javax.imageio.ImageIO;
import javax.swing.*;
        import java.awt.*;
        import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;
        import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class PanelPerfilControlador extends JPanel {
    private final PanelPerfilVista vista;
    private final PanelPerfilModelo modelo;
    private final Object mainFrame;
    private final String usuario;

    public PanelPerfilControlador(Object mainFrame, String usuario) {
        this.mainFrame = mainFrame;
        this.usuario = usuario;
        this.vista = new PanelPerfilVista();
        this.modelo = new PanelPerfilModelo();

        setLayout(new BorderLayout());
        add(vista, BorderLayout.CENTER);

        cargarPerfil();
        conectarEventos();
    }

    private void cargarPerfil() {
        vista.lblNombre.setText(usuario);

        ImageIcon imagen = modelo.cargarFotoPerfil(usuario);
        if (imagen != null) {
            vista.lblFotoPerfil.setIcon(imagen);
            vista.lblFotoPerfil.setText("");
        }

        vista.txtBiografia.setText(modelo.cargarBiografia(usuario));

        for (String[] datos : modelo.cargarFavoritos(usuario)) {
            vista.panelFavoritos.add(crearTarjeta(datos));
        }
    }

    private void conectarEventos() {
        vista.btnGuardarBiografia.addActionListener(e -> {
            try {
                modelo.guardarBiografia(usuario, vista.txtBiografia.getText());
                JOptionPane.showMessageDialog(this, "Biografía guardada.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        vista.btnCambiarFoto.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                File archivo = fc.getSelectedFile();
                if (!archivo.getName().toLowerCase().endsWith(".jpg")) {
                    JOptionPane.showMessageDialog(this, "Solo se permiten imágenes JPG.", "Formato inválido", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                try {
                    modelo.guardarFotoPerfil(usuario, archivo);
                    cargarPerfil();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error al guardar imagen.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        vista.btnVerSolicitudes.addActionListener(e -> {
            try {
                mainFrame.getClass().getMethod("mostrarSolicitudesUsuario").invoke(mainFrame);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    private JPanel crearTarjeta(String[] datos) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(new Color(230, 240, 255));
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JLabel imageLabel = new JLabel();
        String rutaImagen = "src/resources/animales/" + datos[0] + "_image.png";

        try {
            Image imagen = ImageIO.read(new File(rutaImagen));
            imageLabel.setIcon(new ImageIcon(imagen.getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
        } catch (IOException e) {
            imageLabel.setText("Imagen no disponible");
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        }

        JLabel nombre = new JLabel(datos[3], SwingConstants.CENTER);
        nombre.setFont(new Font("Arial", Font.BOLD, 21));

        card.add(imageLabel, BorderLayout.CENTER);
        card.add(nombre, BorderLayout.SOUTH);

        card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    // Usar el nombre completo del paquete
                    Class<?> claseAnimal = Class.forName("vistas.paneles.controlador.PanelAnimalControlador");

                    // Crear el panel de animal pasando mainFrame y datos
                    JPanel panelAnimal = (JPanel) claseAnimal
                            .getConstructor(Object.class, String[].class)
                            .newInstance(mainFrame, datos);

                    // Cambiar el panel actual por el panel del animal
                    mainFrame.getClass().getMethod("cambiarPanel", JPanel.class)
                            .invoke(mainFrame, panelAnimal);

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(card, "No se pudo abrir el animal. Intenta más tarde.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });



        return card;
    }
}



package vistas.paneles.controlador;


import vistas.paneles.modelo.PanelRegistroModelo;
import vistas.paneles.vista.PanelRegistroVista;

import javax.swing.*;

public class PanelRegistroControlador extends JPanel {
    private final vistas.paneles.vista.PanelRegistroVista vista;
    private final PanelRegistroModelo modelo;
    private final Object mainFrame; // ← definir tipo real más adelante

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
package vistas.paneles.controlador;

import otros.SolicitudAdopcion;
import vistas.paneles.modelo.PanelSolicitudesUsuarioModelo;
import vistas.paneles.vista.PanelSolicitudesUsuarioVista;

import javax.imageio.ImageIO;
import javax.swing.*;
        import java.awt.*;
        import java.io.File;
import java.io.IOException;
import java.util.List;

public class PanelSolicitudesUsuarioControlador extends JPanel {
    private final PanelSolicitudesUsuarioVista vista;
    private final PanelSolicitudesUsuarioModelo modelo;
    private final String usuario;

    public PanelSolicitudesUsuarioControlador(String usuario) {
        this.usuario = usuario;
        this.vista = new PanelSolicitudesUsuarioVista();
        this.modelo = new PanelSolicitudesUsuarioModelo();

        setLayout(new BorderLayout());
        add(vista, BorderLayout.CENTER);

        cargarTarjetas();
    }

    private void cargarTarjetas() {
        List<SolicitudAdopcion> solicitudes = modelo.cargarSolicitudesUsuario(usuario);
        for (SolicitudAdopcion solicitud : solicitudes) {
            vista.panelContenedor.add(crearTarjeta(solicitud));
        }
        vista.revalidate();
        vista.repaint();
    }

    private JPanel crearTarjeta(SolicitudAdopcion solicitud) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        card.setPreferredSize(new Dimension(200, 200));

        JLabel imageLabel = new JLabel();
        String rutaImagen = "src/resources/animales/" + solicitud.getIDanimal() + "_image.png";
        try {
            Image imagen = ImageIO.read(new File(rutaImagen));
            imageLabel.setIcon(new ImageIcon(imagen.getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
        } catch (IOException e) {
            imageLabel.setText("Imagen no disponible");
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        }

        JLabel descriptionLabel = new JLabel("Solicitud de " + SolicitudAdopcion.obtenerNombreAnimalPorID(solicitud.getIDanimal()), SwingConstants.CENTER);
        descriptionLabel.setFont(new Font("Arial", Font.BOLD, 19));

        JLabel estadoLabel = new JLabel();
        ImageIcon iconoEstado = obtenerIconoEstado(solicitud.getEstado());
        if (iconoEstado != null) estadoLabel.setIcon(iconoEstado);

        switch (solicitud.getEstado()) {
            case "Aceptada":
                card.setBackground(new Color(198, 239, 206)); break;
            case "Rechazada":
                card.setBackground(new Color(255, 199, 206)); break;
            default:
                card.setBackground(new Color(255, 255, 204)); break;
        }

        JPanel notificacion = new JPanel(new BorderLayout());
        notificacion.setOpaque(false);
        notificacion.add(estadoLabel, BorderLayout.EAST);

        card.add(notificacion, BorderLayout.NORTH);
        card.add(imageLabel, BorderLayout.WEST);
        card.add(descriptionLabel, BorderLayout.CENTER);

        return card;
    }

    private ImageIcon obtenerIconoEstado(String estado) {
        String ruta = switch (estado) {
            case "Aceptada" -> "src/resources/EstadoSolicitud/Aceptada.png";
            case "Rechazada" -> "src/resources/EstadoSolicitud/Rechazada.png";
            default -> "src/resources/EstadoSolicitud/EnEspera.png";
        };
        try {
            Image imagen = ImageIO.read(new File(ruta));
            return new ImageIcon(imagen.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        } catch (IOException e) {
            return null;
        }
    }
}
package vistas.paneles.modelo;

import java.io.*;
        import java.util.List;

public class FormularioAdopcionModelo {

    private final double[] puntuaciones = {0.0, 1.0, 0.5, 0.0};

    public boolean existeSolicitud(String correoUsuario, String IDAnimal) {
        try (BufferedReader br = new BufferedReader(new FileReader("datos/solicitudes.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.contains("Usuario: " + correoUsuario) && linea.contains(IDAnimal)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void guardarSolicitud(String correoUsuario, String IDAnimal, String respuestasTexto, double puntajeTotal, String resultado) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("datos/solicitudes.txt", true))) {
            bw.write("<html>Usuario: " + correoUsuario + "<br>");
            bw.write("ID_Animal:" + IDAnimal + "<br>");
            bw.write(respuestasTexto);
            bw.write("Puntaje total: " + puntajeTotal + "<br>");
            bw.write("Resultado: " + resultado + "<br>");
            bw.write("| Estado: En espera\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public double[] getPuntuaciones() {
        return puntuaciones;
    }
}
package vistas.paneles.modelo;

import java.io.*;
        import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class PanelAgregarAnimalModelo {
    private File imagenSeleccionada;

    public boolean guardarAnimal(String tipo, String raza, String nombre, String edad, File imagen) {
        String nuevoID = generarNuevoID();
        String nuevoAnimal = nuevoID + ":" + tipo + ":" + raza + ":" + nombre + ":" + edad;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("datos/animales.txt", true))) {
            writer.write(nuevoAnimal);
            writer.newLine();

            File destino = new File("src/resources/animales/" + nuevoID + "_image.png");
            Files.copy(imagen.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);

            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private String generarNuevoID() {
        File archivoID = new File("datos/ultimoID.txt");
        int ultimoID = 0;

        try {
            if (archivoID.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(archivoID));
                String linea = reader.readLine();
                if (linea != null) {
                    ultimoID = Integer.parseInt(linea.trim());
                }
                reader.close();
            }
        } catch (IOException | NumberFormatException e) {
            ultimoID = 0;
        }

        int nuevoID = ultimoID + 1;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoID))) {
            writer.write(String.valueOf(nuevoID));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return String.format("ID%05d", nuevoID);
    }

    public void setImagenSeleccionada(File imagen) {
        this.imagenSeleccionada = imagen;
    }


    public File getImagenSeleccionada() {
        return imagenSeleccionada;
    }
}
package vistas.paneles.modelo;

import java.io.*;
        import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class PanelAnimalAdminModelo {

    public int contarSolicitudes(String idAnimal) {
        int contador = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("datos/solicitudes.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.contains(idAnimal)) {
                    contador++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contador;
    }

    public boolean actualizarAnimal(String idAnimal, String tipo, String raza, String nombre, String edad) {
        try (BufferedReader br = new BufferedReader(new FileReader("datos/animales.txt"))) {
            BufferedWriter bw = new BufferedWriter(new FileWriter("datos/animales_temp.txt"));
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(":");
                if (partes[0].equals(idAnimal)) {
                    linea = idAnimal + ":" + tipo + ":" + raza + ":" + nombre + ":" + edad;
                }
                bw.write(linea);
                bw.newLine();
            }
            bw.close();
            Files.move(new File("datos/animales_temp.txt").toPath(), new File("datos/animales.txt").toPath(), StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarAnimal(String idAnimal) {
        try (BufferedReader br = new BufferedReader(new FileReader("datos/animales.txt"))) {
            BufferedWriter bw = new BufferedWriter(new FileWriter("datos/animales_temp.txt"));
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(":");
                if (!partes[0].equals(idAnimal)) {
                    bw.write(linea);
                    bw.newLine();
                }
            }
            bw.close();
            Files.move(new File("datos/animales_temp.txt").toPath(), new File("datos/animales.txt").toPath(), StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean cambiarImagen(String idAnimal, File nuevaImagen) {
        try {
            File destino = new File("src/resources/animales/" + idAnimal + "_image.png");
            Files.copy(nuevaImagen.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
package vistas.paneles.modelo;

import javax.imageio.ImageIO;
import javax.swing.*;
        import java.awt.*;
        import java.io.File;
import java.io.IOException;

public class PanelAnimalModelo {
    public ImageIcon cargarIcono(String ruta, int ancho, int alto) {
        try {
            Image imagen = ImageIO.read(new File(ruta));
            return new ImageIcon(imagen.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH));
        } catch (IOException e) {
            return null;
        }
    }
}package vistas.paneles.modelo;

import java.io.*;
        import java.util.ArrayList;
import java.util.List;

public class PanelInicioAdminModelo {

    public List<String[]> cargarAnimalesDesdeArchivo(String ruta) {
        List<String[]> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(":");
                if (datos.length >= 5) {
                    lista.add(datos);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer los animales desde " + ruta);
        }

        return lista;
    }

    public boolean tieneNotificacion(String IDAnimal) {
        try (BufferedReader br = new BufferedReader(new FileReader("datos/solicitudes.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.contains(IDAnimal) && linea.contains("En espera")) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error al verificar notificaciones.");
        }

        return false;
    }
}
package vistas.paneles.modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PanelInicioModelo {
    private static final String RUTA_ANIMALES = "datos/animales.txt";
    private static final String RUTA_FAVORITOS = "datos/Favoritos.txt";

    public List<String[]> cargarAnimales() {
        List<String[]> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_ANIMALES))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(":");
                if (datos.length >= 5) {
                    lista.add(datos);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean esFavorito(String correoUsuario, String idAnimal) {
        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_FAVORITOS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith(correoUsuario + ":")) {
                    String[] partes = linea.split(":");
                    if (partes.length > 1) {
                        String[] favoritos = partes[1].split(",");
                        for (String fav : favoritos) {
                            if (fav.trim().equals(idAnimal)) {
                                return true;
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void modificarFavorito(String correoUsuario, String idAnimal, boolean agregar) {
        List<String> lineas = new ArrayList<>();
        boolean usuarioEncontrado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_FAVORITOS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith(correoUsuario + ":")) {
                    usuarioEncontrado = true;
                    String[] partes = linea.split(":");
                    List<String> favoritos = new ArrayList<>();

                    if (partes.length > 1 && !partes[1].isEmpty()) {
                        for (String fav : partes[1].split(",")) {
                            if (!fav.isBlank()) {
                                favoritos.add(fav.trim());
                            }
                        }
                    }

                    if (agregar) {
                        if (!favoritos.contains(idAnimal)) {
                            favoritos.add(idAnimal);
                        }
                    } else {
                        favoritos.remove(idAnimal);
                    }

                    String nuevaLinea = correoUsuario + ":" + String.join(",", favoritos) + ",";
                    lineas.add(nuevaLinea);
                } else {
                    lineas.add(linea);
                }
            }

            if (!usuarioEncontrado && agregar) {
                lineas.add(correoUsuario + ":" + idAnimal + ",");
            }

            // Reescribir el archivo
            java.nio.file.Files.write(java.nio.file.Paths.get(RUTA_FAVORITOS), lineas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
package vistas.paneles.modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PanelLoginModelo {
    public String verificarUsuario(String correo, String contraseña) {
        try (BufferedReader br = new BufferedReader(new FileReader("datos/usuarios.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(":");
                if (partes.length < 2) continue;
                String correoArchivo = partes[0].trim();
                String contraseñaArchivo = partes[1].trim();
                String rolArchivo = partes.length > 2 ? partes[2].trim() : "";

                if (correoArchivo.equals(correo)) {
                    if (contraseñaArchivo.equals(contraseña)) {
                        return "admin".equals(rolArchivo) ? "admin" : "user";
                    } else {
                        return "wrong_password";
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "not_found";
    }
}package vistas.paneles.modelo;

import javax.imageio.ImageIO;
import javax.swing.*;
        import java.awt.*;
        import java.awt.image.BufferedImage;
import java.io.*;
        import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PanelPerfilModelo {
    private final String rutaBiografias = "datos/Biografias.txt";
    private final String rutaPerfiles = "src/resources/Perfiles/";
    private final String rutaFavoritos = "datos/Favoritos.txt";
    private final String rutaAnimales = "datos/animales.txt";

    public String cargarBiografia(String usuario) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaBiografias))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith(usuario + " :")) {
                    return linea.substring((usuario + " :").length()).replace("\\n", "\n").trim();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public void guardarBiografia(String usuario, String biografia) throws IOException {
        List<String> lineas = Files.readAllLines(Paths.get(rutaBiografias));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaBiografias))) {
            boolean encontrado = false;
            for (String linea : lineas) {
                if (linea.startsWith(usuario + " :")) {
                    writer.write(usuario + " : " + biografia.replace("\n", "\\n"));
                    encontrado = true;
                } else {
                    writer.write(linea);
                }
                writer.newLine();
            }
            if (!encontrado) {
                writer.write(usuario + " : " + biografia.replace("\n", "\\n"));
                writer.newLine();
            }
        }
    }

    public ImageIcon cargarFotoPerfil(String usuario) {
        File archivo = new File(rutaPerfiles + usuario + ".jpg");
        if (archivo.exists()) {
            try {
                BufferedImage img = ImageIO.read(archivo);
                return new ImageIcon(img.getScaledInstance(300, 300, Image.SCALE_SMOOTH));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void guardarFotoPerfil(String usuario, File archivoSeleccionado) throws IOException {
        BufferedImage imagen = ImageIO.read(archivoSeleccionado);
        ImageIO.write(imagen, "jpg", new File(rutaPerfiles + usuario + ".jpg"));
    }

    public List<String[]> cargarFavoritos(String usuario) {
        List<String[]> lista = new ArrayList<>();
        List<String> ids = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaFavoritos))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith(usuario + ":")) {
                    String[] partes = linea.split(":");
                    if (partes.length > 1) {
                        for (String id : partes[1].split(",")) {
                            if (!id.isEmpty()) ids.add(id.trim());
                        }
                    }
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(rutaAnimales))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(":");
                if (datos.length >= 5 && ids.contains(datos[0])) {
                    lista.add(datos);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lista;
    }
}package vistas.paneles.modelo;

import java.io.*;
        import java.util.Random;

public class PanelRegistroModelo {
    private final char[] LETRAS_DNI = "TRWAGMYFPDXBNJZSQVHLCKE".toCharArray();

    public boolean correoYaExiste(String correo) {
        try (BufferedReader br = new BufferedReader(new FileReader("datos/usuarios.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(":");
                if (partes[0].trim().equals(correo)) return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean guardarUsuario(String correo, String password, String dni, String cp, String fecha, String tel) {
        try (BufferedWriter bwFicha = new BufferedWriter(new FileWriter("datos/FichaUsuario.txt", true));
             BufferedWriter bwUsuarios = new BufferedWriter(new FileWriter("datos/usuarios.txt", true));
             BufferedWriter bwFavoritos = new BufferedWriter(new FileWriter("datos/Favoritos.txt", true));
             BufferedWriter bwBiografias = new BufferedWriter(new FileWriter("datos/biografias.txt", true))) {

            bwFicha.write("Correo: " + correo + "\nDNI: " + dni + "\nCódigo Postal: " + cp +
                    "\nFecha de Nacimiento: " + fecha + "\nTeléfono: " + tel + "\n-------------------------\n");

            bwUsuarios.write(correo + ":" + password + "\n");
            bwFavoritos.write(correo + ":\n");
            bwBiografias.write(correo + ":\n");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String[] generarUsuario() {
        Random r = new Random();
        char letraUser = (char) ('a' + r.nextInt(26));
        String correo = letraUser + "@gmail.com";
        String password = String.valueOf(letraUser);

        String numeroDNI = String.format("%08d", r.nextInt(100000000));
        String dni = numeroDNI + LETRAS_DNI[Integer.parseInt(numeroDNI) % 23];

        String cp = String.format("%02d", r.nextInt(53) + 1) + String.format("%03d", r.nextInt(1000));
        String fecha = String.format("%02d", r.nextInt(28) + 1) + "/" +
                String.format("%02d", r.nextInt(12) + 1) + "/" +
                (1950 + r.nextInt(58));

        String tel = "6" + String.format("%08d", r.nextInt(100000000));

        return new String[]{correo, password, dni, cp, fecha, tel};
    }
}
package vistas.paneles.modelo;

import java.io.*;
        import java.util.ArrayList;
import java.util.List;

public class PanelSolicitudesAdminModelo {

    private static final String RUTA_SOLICITUDES = "datos/solicitudes.txt";
    private static final String RUTA_REVISADAS = "datos/solicitudesRevisadas.txt";
    private static final String RUTA_ANIMALES = "datos/animales.txt";

    public List<String> obtenerSolicitudes(String idAnimal) {
        List<String> solicitudes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_SOLICITUDES))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.contains(idAnimal) && linea.contains("Estado: En espera")) {
                    solicitudes.add(linea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return solicitudes;
    }

    public void aceptarSolicitud(String idAnimal, String solicitudOriginal) {
        guardarSolicitudRevisada(solicitudOriginal.replace("En espera", "Aceptada"));

        eliminarSolicitudDeEspera(solicitudOriginal);
        rechazarSolicitudesRestantes(idAnimal);
        eliminarAnimal(idAnimal);
        eliminarImagenAnimal(idAnimal);
    }

    public void rechazarSolicitud(String solicitudOriginal) {
        guardarSolicitudRevisada(solicitudOriginal.replace("En espera", "Rechazada"));
        eliminarSolicitudDeEspera(solicitudOriginal);
    }
    private void guardarSolicitudRevisada(String solicitud) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA_REVISADAS, true))) {
            bw.write(solicitud);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void eliminarSolicitudDeEspera(String solicitudAEliminar) {
        File inputFile = new File(RUTA_SOLICITUDES);
        File tempFile = new File("datos/solicitudes_temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String linea;
            while ((linea = reader.readLine()) != null) {
                if (!linea.trim().equals(solicitudAEliminar.trim())) {
                    writer.write(linea);
                    writer.newLine();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
            System.err.println("Error actualizando solicitudes.txt");
        }
    }private void rechazarSolicitudesRestantes(String idAnimal) {
        File inputFile = new File(RUTA_SOLICITUDES);
        File tempFile = new File("datos/solicitudes_temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter tempWriter = new BufferedWriter(new FileWriter(tempFile));
             BufferedWriter revisadasWriter = new BufferedWriter(new FileWriter(RUTA_REVISADAS, true))) {

            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.contains(idAnimal) && linea.contains("En espera")) {
                    String rechazada = linea.replace("En espera", "Rechazada");
                    revisadasWriter.write(rechazada);
                    revisadasWriter.newLine();
                } else {
                    tempWriter.write(linea);
                    tempWriter.newLine();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
            System.err.println("Error actualizando solicitudes.txt tras rechazar las restantes.");
        }
    }

    private void eliminarAnimal(String idAnimal) {
        File inputFile = new File(RUTA_ANIMALES);
        File tempFile = new File("datos/animales_temp.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.startsWith(idAnimal + ":")) {
                    bw.write(linea);
                    bw.newLine();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
            System.err.println("Error actualizando animales.txt");
        }
    }  private void eliminarImagenAnimal(String idAnimal) {
        File imagen = new File("src/resources/animales/" + idAnimal + "_image.png");
        if (imagen.exists()) {
            if (!imagen.delete()) {
                System.err.println("No se pudo eliminar la imagen del animal " + idAnimal);
            }
        }
    }


}
package vistas.paneles.modelo;

import otros.SolicitudAdopcion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PanelSolicitudesUsuarioModelo {
    private final String RUTA_SOLICITUDES = "datos/solicitudes.txt";
    private final String RUTA_SOLICITUDES_ACEPTADAS = "datos/solicitudesRevisadas.txt";

    public List<SolicitudAdopcion> cargarSolicitudesUsuario(String usuario) {
        List<SolicitudAdopcion> lista = new ArrayList<>();
        lista.addAll(leerDesdeArchivo(RUTA_SOLICITUDES, usuario, "En espera"));
        lista.addAll(leerDesdeArchivo(RUTA_SOLICITUDES_ACEPTADAS, usuario, "Desconocido"));
        return lista;
    }

    private List<SolicitudAdopcion> leerDesdeArchivo(String ruta, String usuario, String estadoPredeterminado) {
        List<SolicitudAdopcion> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.contains("Usuario: " + usuario)) {
                    String idAnimal = extraerValor(linea, "ID_Animal:");
                    String estado = estadoPredeterminado.equals("Desconocido") ? extraerEstado(linea) : estadoPredeterminado;
                    lista.add(new SolicitudAdopcion(usuario, idAnimal, estado));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lista;
    }

    private String extraerValor(String linea, String clave) {
        int inicio = linea.indexOf(clave);
        if (inicio == -1) return "";
        inicio += clave.length();
        int fin = linea.indexOf("<br>", inicio);
        return (fin == -1) ? linea.substring(inicio).trim() : linea.substring(inicio, fin).trim();
    }

    private String extraerEstado(String linea) {
        if (linea.contains("Estado: Aceptada")) return "Aceptada";
        if (linea.contains("Estado: Rechazada")) return "Rechazada";
        return "En espera";
    }
}package vistas.paneles.vista;

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
package vistas.paneles.vista;


import javax.swing.*;
        import java.awt.*;

        import static otros.VisualInfo.color1;
import static otros.VisualInfo.color5;

public class MenuSuperior extends JPanel {
    private final JLabel frase;

    public MenuSuperior() {
        setPreferredSize(new Dimension(1720, 100));
        setBackground(color1); // Color de fondo

        frase = new JLabel("Adopta, no compres", SwingConstants.CENTER);
        frase.setFont(new Font("Arial", Font.BOLD, 30));
        frase.setForeground(color5);

        setLayout(new BorderLayout());
        add(frase, BorderLayout.CENTER);

        // ESPACIO para botones a la derecha si queremos en el futuro
        JPanel panelDerecha = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelDerecha.setOpaque(false);
        add(panelDerecha, BorderLayout.EAST);
    }


    public void setFrase(String nuevoTexto) {
        frase.setText(nuevoTexto);
    }
}
package vistas.paneles.vista;

import javax.swing.*;
        import java.awt.*;
        import java.io.File;

public class PanelAgregarAnimalVista extends JPanel {
    public JTextField nombreField, tipoField, razaField, edadField;
    public JButton seleccionarImagenButton, agregarButton;
    public JLabel estadoImagenLabel;


    public PanelAgregarAnimalVista() {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 248, 255)); // color1

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBackground(new Color(240, 248, 255)); // color1
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        nombreField = new JTextField();
        tipoField = new JTextField();
        razaField = new JTextField();
        edadField = new JTextField();
        seleccionarImagenButton = new JButton("Seleccionar Imagen");
        agregarButton = new JButton("Agregar Animal");

        estadoImagenLabel = new JLabel("Imagen no seleccionada.");
        estadoImagenLabel.setForeground(Color.RED);

        formPanel.add(new JLabel("Nombre:"));
        formPanel.add(nombreField);
        formPanel.add(new JLabel("Tipo de Animal:"));
        formPanel.add(tipoField);
        formPanel.add(new JLabel("Raza / Especie:"));
        formPanel.add(razaField);
        formPanel.add(new JLabel("Edad:"));
        formPanel.add(edadField);
        formPanel.add(seleccionarImagenButton);
        formPanel.add(estadoImagenLabel);
        formPanel.add(new JLabel());
        formPanel.add(agregarButton);

        add(formPanel, BorderLayout.CENTER);
    }
}
package vistas.paneles.vista;

import javax.swing.*;
        import java.awt.*;

public class PanelAnimalAdminVista extends JPanel {
    public JLabel imageLabel, contadorSolicitudesLabel;
    public JTextField nombreField, tipoField, razaField, edadField;
    public JButton actualizarButton, eliminarButton, cambiarImagenButton, revisarSolicitudesButton;

    public PanelAnimalAdminVista() {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 248, 255)); // color1

        imageLabel = new JLabel("Sin imagen", SwingConstants.CENTER);
        imageLabel.setPreferredSize(new Dimension(250, 250));

        JPanel panelInfo = new JPanel(new GridLayout(6, 2, 10, 10));
        panelInfo.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        panelInfo.setBackground(new Color(240, 248, 255)); // color1

        nombreField = new JTextField();
        tipoField = new JTextField();
        razaField = new JTextField();
        edadField = new JTextField();
        contadorSolicitudesLabel = new JLabel("0 solicitudes");

        actualizarButton = new JButton("Actualizar Datos");
        eliminarButton = new JButton("Eliminar Animal");
        cambiarImagenButton = new JButton("Cambiar Imagen");
        revisarSolicitudesButton = new JButton("Revisar solicitudes");

        JPanel solicitudesPanel = new JPanel(new BorderLayout());
        solicitudesPanel.setOpaque(false);
        solicitudesPanel.add(contadorSolicitudesLabel, BorderLayout.WEST);
        solicitudesPanel.add(revisarSolicitudesButton, BorderLayout.EAST);

        panelInfo.add(new JLabel("Nombre:")); panelInfo.add(nombreField);
        panelInfo.add(new JLabel("Tipo:")); panelInfo.add(tipoField);
        panelInfo.add(new JLabel("Raza:")); panelInfo.add(razaField);
        panelInfo.add(new JLabel("Edad:")); panelInfo.add(edadField);
        panelInfo.add(new JLabel("Solicitudes:")); panelInfo.add(solicitudesPanel);
        panelInfo.add(actualizarButton); panelInfo.add(eliminarButton);

        add(imageLabel, BorderLayout.WEST);
        add(panelInfo, BorderLayout.CENTER);
        add(cambiarImagenButton, BorderLayout.SOUTH);
    }
}
package vistas.paneles.vista;


import javax.swing.*;
        import java.awt.*;

public class PanelAnimalVista extends JPanel {
    public JLabel imageLabel, nombreLabel, tipoLabel, razaLabel, edadLabel;
    public JButton btnVolver, btnAdoptar;

    public PanelAnimalVista() {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 248, 255));

        imageLabel = new JLabel("Imagen no disponible", SwingConstants.CENTER);
        imageLabel.setPreferredSize(new Dimension(450, 450));

        JPanel panelImagen = new JPanel();
        panelImagen.setBackground(Color.WHITE);
        panelImagen.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
        panelImagen.add(imageLabel);

        JPanel infoPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        infoPanel.setBackground(Color.WHITE);

        nombreLabel = new JLabel();
        tipoLabel = new JLabel();
        razaLabel = new JLabel();
        edadLabel = new JLabel();

        nombreLabel.setFont(new Font("Arial", Font.BOLD, 35));
        tipoLabel.setFont(new Font("Arial", Font.BOLD, 35));
        razaLabel.setFont(new Font("Arial", Font.BOLD, 35));
        edadLabel.setFont(new Font("Arial", Font.BOLD, 35));

        infoPanel.add(nombreLabel);
        infoPanel.add(tipoLabel);
        infoPanel.add(razaLabel);
        infoPanel.add(edadLabel);

        JPanel botonesPanel = new JPanel();
        botonesPanel.setBackground(new Color(230, 230, 250));

        btnVolver = new JButton("⬅ Volver");
        btnVolver.setFont(new Font("Arial", Font.BOLD, 30));

        btnAdoptar = new JButton("💖 Solicitar Adopción");
        btnAdoptar.setFont(new Font("Arial", Font.BOLD, 30));

        botonesPanel.add(btnVolver);
        botonesPanel.add(btnAdoptar);

        JPanel contenedor = new JPanel(new BorderLayout(20, 20));
        contenedor.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        contenedor.setBackground(new Color(240, 248, 255));
        contenedor.add(panelImagen, BorderLayout.WEST);
        contenedor.add(infoPanel, BorderLayout.CENTER);

        add(contenedor, BorderLayout.CENTER);
        add(botonesPanel, BorderLayout.SOUTH);
    }
}package vistas.paneles.vista;

import javax.swing.*;
        import java.awt.*;

public class PanelInicioAdminVista extends JPanel {
    public JPanel panelContenedor;

    public PanelInicioAdminVista() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        panelContenedor = new JPanel(new GridLayout(0, 3, 10, 50));
        panelContenedor.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(panelContenedor);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);

        add(scrollPane, BorderLayout.CENTER);
    }
}

package vistas.paneles.vista;

import javax.swing.*;
        import java.awt.*;

public class PanelInicioVista extends JPanel {
    public JPanel panelContenedor;
    public JScrollPane scrollPane;

    public PanelInicioVista() {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 245));

        panelContenedor = new JPanel(new GridLayout(0, 3, 10, 50));
        panelContenedor.setBackground(Color.WHITE);

        scrollPane = new JScrollPane(panelContenedor);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);

        add(scrollPane, BorderLayout.CENTER);
    }
}




package vistas.paneles.vista;

import javax.swing.*;
        import java.awt.*;

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
        txtUsuario = crearCampoTexto("E-mail / Username", gbc, row++);
        txtPassword = new JPasswordField(20);
        txtPassword.setPreferredSize(new Dimension(200, 35));
        txtPassword.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(new JLabel("Password"), gbcAt(gbc, row++));
        add(txtPassword, gbcAt(gbc, row++));

        btnLogin = new JButton("Log in");
        btnLogin.setPreferredSize(new Dimension(220, 35));
        add(btnLogin, gbcAt(gbc, row++));

        btnRegistro = new JButton("Sign Up Now");
        btnRegistro.setPreferredSize(new Dimension(220, 35));
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
}
package vistas.paneles.vista;

import javax.swing.*;
        import java.awt.*;

public class PanelPerfilVista extends JPanel {
    public JLabel lblNombre, lblFotoPerfil;
    public JTextArea txtBiografia;
    public JButton btnCambiarFoto, btnGuardarBiografia, btnVerSolicitudes;
    public JPanel panelFavoritos;

    public PanelPerfilVista() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Panel izquierdo para favoritos
        panelFavoritos = new JPanel(new GridLayout(0, 1, 0, 10));
        panelFavoritos.setBackground(new Color(240, 240, 240));
        JScrollPane scroll = new JScrollPane(panelFavoritos);
        scroll.setPreferredSize(new Dimension(250, getHeight()));
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.getVerticalScrollBar().setUnitIncrement(20);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scroll, BorderLayout.WEST);

        // Panel central
        JPanel panelCentral = new JPanel(new GridBagLayout());
        panelCentral.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        lblFotoPerfil = new JLabel("Sin foto", SwingConstants.CENTER);
        lblFotoPerfil.setPreferredSize(new Dimension(300, 300));

        btnCambiarFoto = new JButton("📸 Cambiar Foto");
        lblNombre = new JLabel("Nombre", SwingConstants.CENTER);
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 22));

        txtBiografia = new JTextArea(5, 20);
        txtBiografia.setLineWrap(true);
        txtBiografia.setWrapStyleWord(true);
        txtBiografia.setBorder(BorderFactory.createTitledBorder("Biografía"));

        btnGuardarBiografia = new JButton("💾 Guardar");
        btnVerSolicitudes = new JButton("📋 Ver Solicitudes");

        int y = 0;
        panelCentral.add(lblFotoPerfil, gbcAt(gbc, y++));
        panelCentral.add(btnCambiarFoto, gbcAt(gbc, y++));
        panelCentral.add(lblNombre, gbcAt(gbc, y++));
        panelCentral.add(new JScrollPane(txtBiografia), gbcAt(gbc, y++));
        panelCentral.add(btnGuardarBiografia, gbcAt(gbc, y++));
        panelCentral.add(btnVerSolicitudes, gbcAt(gbc, y++));

        add(panelCentral, BorderLayout.CENTER);
    }

    private GridBagConstraints gbcAt(GridBagConstraints gbc, int y) {
        GridBagConstraints clone = (GridBagConstraints) gbc.clone();
        clone.gridy = y;
        return clone;
    }
}package vistas.paneles.vista;

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
package vistas.paneles.vista;

import javax.swing.*;
        import java.awt.*;

public class PanelSolicitudesUsuarioVista extends JPanel {
    public JPanel panelContenedor;

    public PanelSolicitudesUsuarioVista() {
        setLayout(new BorderLayout());
        setBackground(new Color(250, 250, 255));

        panelContenedor = new JPanel(new GridLayout(0, 3, 10, 50));
        panelContenedor.setBackground(new Color(250, 250, 255));

        JScrollPane scrollPane = new JScrollPane(panelContenedor);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);

        add(scrollPane, BorderLayout.CENTER);
    }
}
import vistas.paneles.controlador.*;
        import vistas.paneles.vista.*;

        import javax.swing.*;
        import java.awt.*;
        import java.io.File;

public class DemoRescuePal {
    private static MainFrameSimuladoV2 mainFrame;
    private static int paso = 0;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            mainFrame = new MainFrameSimuladoV2();
            mainFrame.setVisible(true);

            Timer timer = new Timer(1500, e -> ejecutarPaso());
            timer.start();
        });
    }

    private static void ejecutarPaso() {
        try {
            switch (paso) {
                case 0 -> clickBotonInicioSesion();
                case 1 -> clickSignUpNow();
                case 2 -> rellenarRegistro();
                case 3 -> iniciarSesionConUsuarioNuevo();
                case 4 -> loginComoAdmin();
                case 5 -> clickAgregarAnimal();
                case 6 -> rellenarFormularioAnimal();
                case 7 -> finalizarDemo();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void clickBotonInicioSesion() {
        System.out.println("Paso 0: Pulsar 'Iniciar Sesión'");
        JButton btnLogin = (JButton) mainFrame.getPanelLateral().getComponent(1);
        btnLogin.doClick();
        paso++;
    }

    private static void clickSignUpNow() {
        if (mainFrame.getPanelDerecho().getComponent(0) instanceof vistas.paneles.controlador.PanelLoginControlador loginControlador) {
            System.out.println("Paso 1: Pulsar 'Sign Up Now'");
            loginControlador.getVista().btnRegistro.doClick();
            paso++;
        } else {
            System.out.println("Esperando a estar en el panel de Login...");
        }
    }

    private static void rellenarRegistro() {
        if (mainFrame.getPanelDerecho().getComponent(0) instanceof vistas.paneles.controlador.PanelRegistroControlador registroControlador) {
            System.out.println("Paso 2: Rellenar y Registrar Usuario");
            vistas.paneles.vista.PanelRegistroVista vista = registroControlador.getVista();
            vista.txtCorreo.setText("demo@demo.com");
            vista.txtPassword.setText("demo");
            vista.btnGenerar.doClick();
            vista.btnRegistrar.doClick();
            paso++;
        } else {
            System.out.println("Esperando estar en el panel de Registro...");
        }
    }

    private static void iniciarSesionConUsuarioNuevo() {
        if (mainFrame.getPanelDerecho().getComponent(0) instanceof vistas.paneles.controlador.PanelLoginControlador loginControlador) {
            System.out.println("Paso 3: Iniciar Sesión como Usuario Nuevo");
            vistas.paneles.vista.PanelLoginVista vista = loginControlador.getVista();
            vista.txtUsuario.setText("demo@demo.com");
            vista.txtPassword.setText("demo");
            vista.btnLogin.doClick();
            paso++;
        } else {
            System.out.println("Esperando volver al panel de Login...");
        }
    }

    private static void loginComoAdmin() {
        if (mainFrame.getPanelDerecho().getComponent(0) instanceof vistas.paneles.controlador.PanelInicioControlador inicioControlador) {
            System.out.println("Paso 4: Logout e Iniciar Sesión como Admin");
            JButton btnLogout = (JButton) mainFrame.getPanelLateral().getComponent(2); // Suponemos botón 'Cerrar sesión'
            btnLogout.doClick();

            // Volver a login
            JButton btnLogin = (JButton) mainFrame.getPanelLateral().getComponent(1);
            btnLogin.doClick();

            Timer t = new Timer(1200, ev -> {
                if (mainFrame.getPanelDerecho().getComponent(0) instanceof vistas.paneles.controlador.PanelLoginControlador loginControlador) {
                    vistas.paneles.vista.PanelLoginVista vista = loginControlador.getVista();
                    vista.txtUsuario.setText("a");
                    vista.txtPassword.setText("a");
                    vista.btnLogin.doClick();
                    paso++;
                }
            });
            t.setRepeats(false);
            t.start();
        } else {
            System.out.println("Esperando Inicio normal para logout...");
        }
    }

    private static void clickAgregarAnimal() {
        if (mainFrame.getPanelDerecho().getComponent(0) instanceof vistas.paneles.controlador.PanelInicioAdminControlador inicioAdminControlador) {
            System.out.println("Paso 5: Pulsar 'Agregar Animal'");
            JButton btnAgregarAnimal = (JButton) mainFrame.getPanelLateral().getComponent(3); // Botón agregar
            btnAgregarAnimal.doClick();
            paso++;
        } else {
            System.out.println("Esperando Inicio Admin...");
        }
    }

    private static void rellenarFormularioAnimal() {
        if (mainFrame.getPanelDerecho().getComponent(0) instanceof vistas.paneles.controlador.PanelAgregarAnimalControlador agregarAnimalControlador) {
            System.out.println("Paso 6: Rellenar Animal y Agregar");
            vistas.paneles.vista.PanelAgregarAnimalVista vista = agregarAnimalControlador.getVista();
            vista.nombreField.setText("Firulais");
            vista.tipoField.setText("Perro");
            vista.razaField.setText("Labrador");
            vista.edadField.setText("3");

            // Simular imagen
            File imagen = new File(System.getProperty("user.home") + "/Desktop/FotoGuapa.jpg");
            if (imagen.exists()) {
                agregarAnimalControlador.getModelo().setImagenSeleccionada(imagen);
            }

            vista.agregarButton.doClick();
            paso++;
        } else {
            System.out.println("Esperando formulario Agregar Animal...");
        }
    }

    private static void finalizarDemo() {
        System.out.println("\n✅ Demo completada con éxito ✅\n");
    }
}
import otros.CargaBaseDatos;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CargaBaseDatos cargaBaseDatos = new CargaBaseDatos();
            MainFrameSimuladoV2 mainFrame = new MainFrameSimuladoV2();
            mainFrame.setVisible(true);
        });
    }
}
//no quiero que se genere un archivo nuevo, se almacena everything en el mismo y quiero que se sobreescriba, para no perder informacion ya almacenada
import vistas.paneles.controlador.*;
        import vistas.paneles.vista.MenuSuperior;

import javax.imageio.ImageIO;
import javax.swing.*;
        import java.awt.*;
        import java.io.File;
import java.io.IOException;

public class MainFrameSimuladoV2 extends JFrame {
    private JPanel panelLateral;
    private JPanel panelDerecho;
    private boolean usuarioLogueado = false;
    private String correoUsuario = "";
    private String rolUsuario = "";
    private vistas.paneles.vista.MenuSuperior menuSuperior;


    public MainFrameSimuladoV2() {
        setTitle("RescuePal - Protectora de Animales");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        crearMenuSuperior();
        crearPanelLateral();
        crearPanelDerecho();

        mostrarInicio();
    }
    private void crearMenuSuperior() {
        menuSuperior = new vistas.paneles.vista.MenuSuperior();
        add(menuSuperior, BorderLayout.NORTH);
    }

    private void crearPanelLateral() {
        panelLateral = new JPanel();
        panelLateral.setLayout(new GridLayout(10, 1, 5, 5));
        panelLateral.setPreferredSize(new Dimension(200, getHeight()));
        panelLateral.setBackground(new Color(200, 220, 255));
        actualizarMenuLateral();
        add(panelLateral, BorderLayout.WEST);
    }
    public boolean isUsuarioLogueado() {
        return usuarioLogueado;
    }

    private void crearPanelDerecho() {
        panelDerecho = new JPanel(new BorderLayout());
        add(panelDerecho, BorderLayout.CENTER);
    }

    public void actualizarMenuLateral() {
        panelLateral.removeAll();


        JButton btnInicio = new JButton("Inicio");
        btnInicio.setFont(new Font("Arial", Font.BOLD, 25));

        btnInicio.setBorderPainted(false);
        btnInicio.setContentAreaFilled(false);
        btnInicio.setFocusPainted(false);
        btnInicio.setFocusable(false);

        btnInicio.addActionListener(e -> {
            if ("admin".equals(rolUsuario)) {
                mostrarInicioAdmin();
            } else {
                mostrarInicio();
            }
        });
        panelLateral.add(new JLabel(createIcon("src/resources/menu/Logo.png", 75, 75)));
        panelLateral.add(btnInicio);

        if (!usuarioLogueado) {
            JButton btnLogin = new JButton("Iniciar Sesión");
            btnLogin.setFont(new Font("Arial", Font.BOLD, 25));

            btnLogin.setBorderPainted(false);
            btnLogin.setContentAreaFilled(false);
            btnLogin.setFocusPainted(false);
            btnLogin.setFocusable(false);
            btnLogin.addActionListener(e -> mostrarLogin());
            panelLateral.add(btnLogin);
        } else {
            if (!"admin".equals(rolUsuario)) {  // Solo usuarios normales tienen perfil
                JButton btnPerfil = new JButton("Perfil");
                btnPerfil.setFont(new Font("Arial", Font.BOLD, 25));
                btnPerfil.setBorderPainted(false);
                btnPerfil.setContentAreaFilled(false);
                btnPerfil.setFocusPainted(false);
                btnPerfil.setFocusable(false);
                btnPerfil.addActionListener(e -> mostrarPerfil());
                panelLateral.add(btnPerfil);
            }

            JButton btnCerrarSesion = new JButton("Cerrar Sesión");
            btnCerrarSesion.setFont(new Font("Arial", Font.BOLD, 25));
            btnCerrarSesion.setBorderPainted(false);
            btnCerrarSesion.setContentAreaFilled(false);
            btnCerrarSesion.setFocusPainted(false);
            btnCerrarSesion.setFocusable(false);
            btnCerrarSesion.addActionListener(e -> cerrarSesion());
            panelLateral.add(btnCerrarSesion);
        }

        panelLateral.revalidate();
        panelLateral.repaint();
    }

    public void cambiarPanel(JPanel nuevoPanel) {
        panelDerecho.removeAll();
        panelDerecho.add(nuevoPanel, BorderLayout.CENTER);
        panelDerecho.revalidate();
        panelDerecho.repaint();
    }
    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void mostrarInicio() {
        cambiarPanel(new vistas.paneles.controlador.PanelInicioControlador(this));
    }

    public void mostrarLogin() {
        cambiarPanel(new vistas.paneles.controlador.PanelLoginControlador(this));
    }
    public void setUsuarioLogueado(boolean logueado, String correo, String rol) {
        this.usuarioLogueado = logueado;
        this.correoUsuario = correo;
        this.rolUsuario = rol;
        actualizarMenuLateral();
        mostrarInicio();
    }

    public void mostrarPanelRegistro() {
        cambiarPanel(new vistas.paneles.controlador.PanelRegistroControlador(this));
    }

    public void mostrarPerfil() {
        cambiarPanel(new vistas.paneles.controlador.PanelPerfilControlador(this, correoUsuario));
    }
    public void mostrarSolicitudesUsuario() {
        cambiarPanel(new vistas.paneles.controlador.PanelSolicitudesUsuarioControlador(correoUsuario));
    }









    public void mostrarInicioAdmin() {
        cambiarPanel(new vistas.paneles.controlador.PanelInicioAdminControlador(this));
    }
    public void mostrarAgregarAnimal() {
        cambiarPanel(new vistas.paneles.controlador.PanelAgregarAnimalControlador(this));
    }
    public void mostrarSolicitudesAdmin(String idAnimal) {
        cambiarPanel(new vistas.paneles.controlador.PanelSolicitudesAdminControlador(this, idAnimal));
    }





    private void cerrarSesion() {
        setUsuarioLogueado(false, "", "");
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









    public JPanel getPanelLateral() {
        return panelLateral;
    }

    public JPanel getPanelDerecho() {
        return panelDerecho;
    }








}
*/