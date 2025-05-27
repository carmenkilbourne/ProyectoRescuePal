package otros;

import java.util.HashMap;
import java.util.Map;

public class Usuario {
    private String correo;
    private String dni;
    private String codigoPostal;
    private String fechaNacimiento;
    private String telefono;
    private String permisos;
    private Map<String, String> errores = new HashMap<>();

    public Usuario(String correo, String dni, String codigoPostal,
                   String fechaNacimiento, String telefono, String permisos) {

        this.correo = correo;
        this.dni = dni;
        this.codigoPostal = codigoPostal;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.permisos = permisos;

        validarCampos();
    }

    private void validarCampos() {
        if (correo == null || !correo.matches("^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{2,}$")) {
            errores.put("correo", "Correo inválido");
        }
        if (dni == null || !dni.matches("\\d{8}[A-Z]")) {
            errores.put("dni", "DNI inválido");
        }
        if (codigoPostal == null || !codigoPostal.matches("\\d{5}")) {
            errores.put("codigoPostal", "Código postal inválido");
        }
        if (fechaNacimiento == null || !fechaNacimiento.matches("\\d{4}-\\d{2}-\\d{2}")) {
            errores.put("fechaNacimiento", "Fecha inválida (formato esperado: AAAA-MM-DD)");
        }
        if (telefono == null || !telefono.matches("\\d{9}")) {
            errores.put("telefono", "Teléfono inválido (deben ser 9 dígitos)");
        }
        if (permisos == null || !(permisos.equals("usuario") || permisos.equals("admin") || permisos.equals("superadmin"))) {
            errores.put("permisos", "Permisos inválidos (deben ser usuario, admin o superadmin)");
        }
    }

    public boolean esValido() {
        return errores.isEmpty();
    }

    public Map<String, String> getErrores() {
        return errores;
    }

    // Getters normales
    public String getCorreo() { return correo; }
    public String getDni() { return dni; }
    public String getCodigoPostal() { return codigoPostal; }
    public String getFechaNacimiento() { return fechaNacimiento; }
    public String getTelefono() { return telefono; }
    public String getPermisos() { return permisos; }
}
