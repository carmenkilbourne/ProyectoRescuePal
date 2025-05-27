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
            lista.addAll(leerDesdeArchivo(RUTA_SOLICITUDES_ACEPTADAS, usuario, "AAAAA"));
            return lista;
        }

        private List<SolicitudAdopcion> leerDesdeArchivo(String ruta, String usuario, String estadoPredeterminado) {
            List<SolicitudAdopcion> lista = new ArrayList<>();

            try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    if (linea.contains("Usuario: " + usuario)) {
                        String idAnimal = extraerValor(linea, "ID_Animal:");
                        System.out.println(linea);
                        String estado = estadoPredeterminado.equals("AAAAA") ? extraerEstado(linea) : estadoPredeterminado;
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
    }