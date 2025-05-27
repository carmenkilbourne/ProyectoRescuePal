
package integracion;

import org.junit.jupiter.api.Test;
import vistas.paneles.controlador.PanelAnimalControlador;
import vistas.paneles.vista.PanelAnimalVista;

import javax.swing.*;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

public class AnimalVistaControladorIntegracionTest {

    @Test
    public void testCargarDatosAnimal() throws Exception {
        String[] datos = {"ID001", "Perro", "Labrador", "Toby", "5"};

        PanelAnimalControlador controlador = new PanelAnimalControlador(new Object(), datos);

        Field vistaField = PanelAnimalControlador.class.getDeclaredField("vista");
        vistaField.setAccessible(true);
        PanelAnimalVista vista = (PanelAnimalVista) vistaField.get(controlador);

        assertEquals("Nombre: Toby", vista.nombreLabel.getText());
        assertEquals("Tipo: Perro", vista.tipoLabel.getText());
        assertEquals("Raza: Labrador", vista.razaLabel.getText());
        assertEquals("Edad: 5 años", vista.edadLabel.getText());
    }
}
