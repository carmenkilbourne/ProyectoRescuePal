import vistas.paneles.controlador.*;
import vistas.paneles.vista.MenuSuperior;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static otros.VisualInfo.color3;

public class MainFrameSimuladoV2 extends JFrame {
    private JPanel panelLateral;
    private JPanel panelDerecho;
    private boolean usuarioLogueado = false;
    private String correoUsuario = "";
    private String rolUsuario = "";
    private MenuSuperior menuSuperior;


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
        menuSuperior = new MenuSuperior();
        add(menuSuperior, BorderLayout.NORTH);
    }

    private void crearPanelLateral() {
        panelLateral = new JPanel();
        panelLateral.setLayout(new GridLayout(10, 1, 5, 5));
        panelLateral.setPreferredSize(new Dimension(170, getHeight()));
        panelLateral.setBackground(color3);
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


        JButton btnInicio = new JButton("Inicio", createIcon("src/resources/menu/home.png",35,35));
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
            JButton btnLogin = new JButton("<html>Iniciar<br>Sesión", createIcon("src/resources/menu/login.png",35,35));
            btnLogin.setFont(new Font("Arial", Font.BOLD, 25));

            btnLogin.setBorderPainted(false);
            btnLogin.setContentAreaFilled(false);
            btnLogin.setFocusPainted(false);
            btnLogin.setFocusable(false);
            btnLogin.addActionListener(e -> mostrarLogin());
            panelLateral.add(btnLogin);
        } else {
            if (!"admin".equals(rolUsuario)) {
                JButton btnPerfil = new JButton("Perfil", createIcon("src/resources/menu/avatar.png",35,35));
                btnPerfil.setFont(new Font("Arial", Font.BOLD, 25));
                btnPerfil.setBorderPainted(false);
                btnPerfil.setContentAreaFilled(false);
                btnPerfil.setFocusPainted(false);
                btnPerfil.setFocusable(false);
                btnPerfil.addActionListener(e -> mostrarPerfil());
                panelLateral.add(btnPerfil);
                JButton btnSolicitudes = new JButton("<html>Mis<br>Solicitudes", createIcon("src/resources/menu/avatar.png",35,35));
                btnSolicitudes.setFont(new Font("Arial", Font.BOLD, 25));
                btnSolicitudes.setBorderPainted(false);
                btnSolicitudes.setContentAreaFilled(false);
                btnSolicitudes.setFocusPainted(false);
                btnSolicitudes.setFocusable(false);
                btnSolicitudes.addActionListener(e -> {
                    try {
                        mostrarSolicitudesUsuario();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
            }else{
                JButton btnGestion = new JButton("<html>Gestión<br>Usuarios", createIcon("src/resources/menu/avatar.png",35,35));
                btnGestion.setFont(new Font("Arial", Font.BOLD, 25));
                btnGestion.setBorderPainted(false);
                btnGestion.setContentAreaFilled(false);
                btnGestion.setFocusPainted(false);
                btnGestion.setFocusable(false);
                btnGestion.addActionListener(e -> mostrarGestionUsuario());
                panelLateral.add(btnGestion);
            }

            JButton btnCerrarSesion = new JButton("<html>Cerrar<br>Sesión",createIcon("src/resources/menu/logout.png",35,35));
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
        cambiarPanel(new PanelInicioControlador(this));
    }

    public void mostrarLogin() {
        cambiarPanel(new PanelLoginControlador(this));
    }
    public void setUsuarioLogueado(boolean logueado, String correo, String rol) {
        this.usuarioLogueado = logueado;
        this.correoUsuario = correo;
        this.rolUsuario = rol;
        actualizarMenuLateral();
        mostrarInicio();
    }

    public void mostrarPanelRegistro() {
        cambiarPanel(new PanelRegistroControlador(this));
    }

    public void mostrarPerfil() {
        cambiarPanel(new PanelPerfilControlador(this, correoUsuario));
    }
    public void mostrarSolicitudesUsuario() {
        cambiarPanel(new PanelSolicitudesUsuarioControlador(correoUsuario));
    }
   public void mostrarGestionUsuario() {cambiarPanel(new PanelGestionUsuarioControlador(this));}









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
