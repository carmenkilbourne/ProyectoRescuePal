/*import vistas.paneles.controlador.*;
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
        if (mainFrame.getPanelDerecho().getComponent(0) instanceof PanelLoginControlador loginControlador) {
            System.out.println("Paso 1: Pulsar 'Sign Up Now'");
            loginControlador.getVista().btnRegistro.doClick();
            paso++;
        } else {
            System.out.println("Esperando a estar en el panel de Login...");
        }
    }

    private static void rellenarRegistro() {
        if (mainFrame.getPanelDerecho().getComponent(0) instanceof PanelRegistroControlador registroControlador) {
            System.out.println("Paso 2: Rellenar y Registrar Usuario");
            PanelRegistroVista vista = registroControlador.getVista();
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
        if (mainFrame.getPanelDerecho().getComponent(0) instanceof PanelLoginControlador loginControlador) {
            System.out.println("Paso 3: Iniciar Sesión como Usuario Nuevo");
            PanelLoginVista vista = loginControlador.getVista();
            vista.txtUsuario.setText("demo@demo.com");
            vista.txtPassword.setText("demo");
            vista.btnLogin.doClick();
            paso++;
        } else {
            System.out.println("Esperando volver al panel de Login...");
        }
    }

    private static void loginComoAdmin() {
        if (mainFrame.getPanelDerecho().getComponent(0) instanceof PanelInicioControlador inicioControlador) {
            System.out.println("Paso 4: Logout e Iniciar Sesión como Admin");
            JButton btnLogout = (JButton) mainFrame.getPanelLateral().getComponent(2); // Suponemos botón 'Cerrar sesión'
            btnLogout.doClick();

            // Volver a login
            JButton btnLogin = (JButton) mainFrame.getPanelLateral().getComponent(1);
            btnLogin.doClick();

            Timer t = new Timer(1200, ev -> {
                if (mainFrame.getPanelDerecho().getComponent(0) instanceof PanelLoginControlador loginControlador) {
                    PanelLoginVista vista = loginControlador.getVista();
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
        if (mainFrame.getPanelDerecho().getComponent(0) instanceof PanelInicioAdminControlador inicioAdminControlador) {
            System.out.println("Paso 5: Pulsar 'Agregar Animal'");
            JButton btnAgregarAnimal = (JButton) mainFrame.getPanelLateral().getComponent(3); // Botón agregar
            btnAgregarAnimal.doClick();
            paso++;
        } else {
            System.out.println("Esperando Inicio Admin...");
        }
    }

    private static void rellenarFormularioAnimal() {
        if (mainFrame.getPanelDerecho().getComponent(0) instanceof PanelAgregarAnimalControlador agregarAnimalControlador) {
            System.out.println("Paso 6: Rellenar Animal y Agregar");
            PanelAgregarAnimalVista vista = agregarAnimalControlador.getVista();
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
}*/