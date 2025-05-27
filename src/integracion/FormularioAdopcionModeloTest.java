
package otros;

import org.junit.jupiter.api.*;
import vistas.paneles.modelo.FormularioAdopcionModelo;

import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

public class FormularioAdopcionModeloTest {

    private static final String TEST_FILE_PATH = "datos/solicitudes.txt";

    @BeforeEach
    public void setUp() {
        File file = new File(TEST_FILE_PATH);
        file.getParentFile().mkdirs();
        if (file.exists()) {
        }
    }

    @AfterEach
    public void tearDown() {
        File file = new File(TEST_FILE_PATH);
        if (file.exists()) {

        }
    }

    @Test
    public void testGetPuntuaciones() {
        FormularioAdopcionModelo modelo = new FormularioAdopcionModelo();
        double[] expected = {0.0, 1.0, 0.5, 0.0};
        assertArrayEquals(expected, modelo.getPuntuaciones(), 0.001);
    }

    @Test
    public void testExisteSolicitudTrueAndFalse() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEST_FILE_PATH))) {
            writer.write("Usuario: prueba@correo.com<br>ID_Animal:ID00123<br>");
        }

        FormularioAdopcionModelo modelo = new FormularioAdopcionModelo();
        assertTrue(modelo.existeSolicitud("prueba@correo.com", "ID00123"));
        assertFalse(modelo.existeSolicitud("otro@correo.com", "ID99999"));
    }

    @Test
    public void testExisteSolicitudArchivoInexistente() {
        // No se crea el archivo
        FormularioAdopcionModelo modelo = new FormularioAdopcionModelo();
        assertFalse(modelo.existeSolicitud("nadie@correo.com", "ID00000"));
    }

    @Test
    public void testGuardarSolicitudContenidoCorrecto() throws IOException {
        FormularioAdopcionModelo modelo = new FormularioAdopcionModelo();
        modelo.guardarSolicitud(
                "test@correo.com", "ID00001",
                "Pregunta 1: Sí<br>Pregunta 2: No<br>", 10.5, "✅Apto✅"
        );

        StringBuilder contenido = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(TEST_FILE_PATH))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
        }

        String resultado = contenido.toString();
        assertTrue(resultado.contains("Usuario: test@correo.com"));
        assertTrue(resultado.contains("ID_Animal:ID00001"));
        assertTrue(resultado.contains("Pregunta 1: Sí"));
        assertTrue(resultado.contains("Puntaje total: 10.5"));
        assertTrue(resultado.contains("Resultado: ✅Apto✅"));
        assertTrue(resultado.contains("| Estado: En espera"));
    }
}
