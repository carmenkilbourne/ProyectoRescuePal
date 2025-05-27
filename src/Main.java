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

/**
 * Crear un metodo de olvidar contraseña, si introduces los datos correctos, se te dice tu contraseña.
 */


