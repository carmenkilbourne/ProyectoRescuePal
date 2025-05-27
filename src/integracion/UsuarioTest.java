package otros;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    @Test
    public void testConstructorAndGetters() {
        Usuario usuario = new Usuario(
                "test@correo.com", "12345678A", "28080",
                "2000-01-01", "600123456", "usuario"
        );

        assertEquals("test@correo.com", usuario.getCorreo());
        assertEquals("12345678A", usuario.getDni());
        assertEquals("28080", usuario.getCodigoPostal());
        assertEquals("2000-01-01", usuario.getFechaNacimiento());
        assertEquals("600123456", usuario.getTelefono());
        assertEquals("usuario", usuario.getPermisos());
    }

    @Test
    public void testDatosVacios() {
        Usuario usuario = new Usuario("", "", "", "", "", "");

        assertEquals("", usuario.getCorreo());
        assertEquals("", usuario.getDni());
        assertEquals("", usuario.getCodigoPostal());
        assertEquals("", usuario.getFechaNacimiento());
        assertEquals("", usuario.getTelefono());
        assertEquals("", usuario.getPermisos());
    }

    @Test
    public void testDatosNulos() {
        Usuario usuario = new Usuario(null, null, null, null, null, null);

        assertNull(usuario.getCorreo());
        assertNull(usuario.getDni());
        assertNull(usuario.getCodigoPostal());
        assertNull(usuario.getFechaNacimiento());
        assertNull(usuario.getTelefono());
        assertNull(usuario.getPermisos());
    }

    @Test
    public void testDatosBorde() {
        Usuario usuario = new Usuario(
                "a@b.co", "00000000Z", "00000",
                "1900-01-01", "000000000", "admin"
        );

        assertEquals("a@b.co", usuario.getCorreo());
        assertEquals("00000000Z", usuario.getDni());
        assertEquals("00000", usuario.getCodigoPostal());
        assertEquals("1900-01-01", usuario.getFechaNacimiento());
        assertEquals("000000000", usuario.getTelefono());
        assertEquals("admin", usuario.getPermisos());
    }

    @Test
    public void testPermisosEspeciales() {
        Usuario usuario = new Usuario(
                "root@system.local", "99999999X", "99999",
                "2100-12-31", "999999999", "superadmin"
        );

        assertEquals("superadmin", usuario.getPermisos());
    }

    @Test
    public void testDatosInvalidosSimulados() {
        Usuario usuario = new Usuario(
                "noemail", "DNIINVALIDO", "abcde",
                "not-a-date", "telefono?", "???"
        );

        assertEquals("noemail", usuario.getCorreo());
        assertEquals("DNIINVALIDO", usuario.getDni());
        assertEquals("abcde", usuario.getCodigoPostal());
        assertEquals("not-a-date", usuario.getFechaNacimiento());
        assertEquals("telefono?", usuario.getTelefono());
        assertEquals("???", usuario.getPermisos());
    }
}
