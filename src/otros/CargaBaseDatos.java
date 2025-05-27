package otros;

import java.io.*;

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
        String animalesAceptados = "datos/animalesAceptados.txt";
        String FichaUsuario = "datos/FichaUsuario.txt";
        String solicitudes = "datos/solicitudes.txt";
        String solicitudesRevisadas = "datos/solicitudesRevisadas.txt";
        String usuarios = "datos/usuarios.txt";

        File directorioAnimales = new File(animales);
        File directorioAnimalesAceptados = new File(animalesAceptados);
        File directorioFichaUsuario = new File(FichaUsuario);
        File directorioSolicitudes = new File(solicitudes);
        File directorioSolicitudesRevisadas = new File(solicitudesRevisadas);
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
        if (!directorioAnimalesAceptados.exists()) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(animalesAceptados));
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
        if (!directorioSolicitudesRevisadas.exists()) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(directorioSolicitudesRevisadas));
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
        String nuevoID = generarNuevoID();

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
                bw.write(nuevoID + ":" + Tipo[i] + "," + Raza[i] + "," + Nombre[i] + "," + Edad[i]);
                bw.newLine();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
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
}
