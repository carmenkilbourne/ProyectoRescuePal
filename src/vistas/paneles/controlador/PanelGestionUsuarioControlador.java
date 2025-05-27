package vistas.paneles.controlador;

import vistas.paneles.modelo.PanelGestionUsuarioModelo;
import vistas.paneles.vista.PanelGestionUsuarioVista;
import otros.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PanelGestionUsuarioControlador extends JPanel {

    private final PanelGestionUsuarioVista vista;
    private final PanelGestionUsuarioModelo modelo;
    private final Object mainFrame;
    private JTable tablaUsuarios;

    public PanelGestionUsuarioControlador(Object mainFrame) {
        this.mainFrame = mainFrame;
        this.modelo = new PanelGestionUsuarioModelo();
        this.vista = new PanelGestionUsuarioVista();

        setLayout(new BorderLayout());
        add(vista, BorderLayout.CENTER);

        construirTabla();
        conectarEventos();
    }

    private void construirTabla() {
        String[] columnas = {
                "Correo", "DNI", "Código Postal", "Fecha de Nacimiento", "Teléfono","Permisos"
        };

        List<Usuario> usuarios = modelo.obtenerUsuarios();
        String[][] datos = new String[usuarios.size()][6];

        for (int i = 0; i < usuarios.size(); i++) {
            Usuario u = usuarios.get(i);
            datos[i][0] = u.getCorreo();
            datos[i][1] = u.getDni();
            datos[i][2] = u.getCodigoPostal();
            datos[i][3] = u.getFechaNacimiento();
            datos[i][4] = u.getTelefono();
            datos[i][5] = u.getPermisos().toString();
        }

        DefaultTableModel modeloTabla = new DefaultTableModel(datos, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaUsuarios = new JTable(modeloTabla);
        tablaUsuarios.setFillsViewportHeight(true);
        tablaUsuarios.setRowHeight(28);
        tablaUsuarios.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        tablaUsuarios.setFont(new Font("Arial", Font.PLAIN, 14));
        tablaUsuarios.setGridColor(Color.LIGHT_GRAY);
        tablaUsuarios.setShowGrid(true);
        tablaUsuarios.setSelectionBackground(new Color(220, 230, 255));

        JScrollPane scrollPane = new JScrollPane(tablaUsuarios);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Usuarios Registrados"));

        vista.removeAll();
        vista.setLayout(new BorderLayout());
        vista.add(scrollPane, BorderLayout.CENTER);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton btnPermisos = new JButton("Cambiar permisos");
        JButton btnEliminar = new JButton("Eliminar usuario");

        btnPermisos.addActionListener(e -> {
            int fila = tablaUsuarios.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(this, "Selecciona un usuario.");
                return;
            }

            String correo = (String) tablaUsuarios.getValueAt(fila, 0);
            modelo.cambiarPermisos(correo);
            construirTabla();
        });

        btnEliminar.addActionListener(e -> {
            int fila = tablaUsuarios.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(this, "Selecciona un usuario.");
                return;
            }

            String correo = (String) tablaUsuarios.getValueAt(fila, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar a " + correo + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                modelo.eliminarUsuario(correo);
                construirTabla();
            }
        });

        botones.add(btnPermisos);
        botones.add(btnEliminar);
        botones.add(vista.btnAgregar);
        vista.add(botones, BorderLayout.SOUTH);

        vista.revalidate();
        vista.repaint();
    }

    private void conectarEventos() {


        vista.btnAgregar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Funcionalidad aún no implementada.");
        });
    }
}
