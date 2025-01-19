package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import db.BaseDeDatos;
import domain.Cliente;
import domain.Admin;
import domain.Pelicula;
import domain.Genero;
import domain.Valoracion;
import java.awt.*;
import java.util.List;

public class VentanaAdmin extends JFrame {

	private static final long serialVersionUID = 1L;

	public VentanaAdmin() {
        setTitle("Gestión de Base de Datos - Admin");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Panel principal con pestañas
        JTabbedPane tabbedPane = new JTabbedPane();

        // Pestaña Clientes
        JPanel panelClientes = createClientesPanel();
        tabbedPane.addTab("Clientes", panelClientes);

        // Pestaña Administradores
        JPanel panelAdmins = createAdminsPanel();
        tabbedPane.addTab("Administradores", panelAdmins);

        // Pestaña Películas
        JPanel panelPeliculas = createPeliculasPanel();
        tabbedPane.addTab("Películas", panelPeliculas);

        // Botón Volver
        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> dispose()); // Cierra la ventana

        // Agregar componentes a la ventana
        add(tabbedPane, BorderLayout.CENTER);
        add(btnVolver, BorderLayout.SOUTH);
    }

    private JPanel createClientesPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        String[] columnas = {"Dni", "Nombre", "Apellido", "Email", "Teléfono", "Tarjeta", "Contraseña"};
        DefaultTableModel tableModel = new DefaultTableModel(columnas, 0);
        JTable table = new JTable(tableModel);

        // Cargar datos de la base de datos
        List<Cliente> clientes = BaseDeDatos.obtenerListaClientes();
        for (Cliente cliente : clientes) {
            tableModel.addRow(new Object[]{
                cliente.getDni(),
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getEmail(),
                cliente.getNumTel(),
                cliente.getNumTarjeta(),
                cliente.getContrasenia()
            });
        }

        // Botón Añadir Cliente
        JButton btnAdd = new JButton("Añadir Cliente");
        btnAdd.addActionListener(e -> {
        	String dni = JOptionPane.showInputDialog("DNI:");        
            String nombre = JOptionPane.showInputDialog("Nombre:");
            String apellido = JOptionPane.showInputDialog("Apellido:");
            String email = JOptionPane.showInputDialog("Email:");
            String telefono = JOptionPane.showInputDialog("Teléfono:");
            String tarjeta = JOptionPane.showInputDialog("Tarjeta:");
            String contrasenia = JOptionPane.showInputDialog("Contraseña:");

            if (dni != null && nombre != null && apellido != null && email != null && telefono != null && tarjeta != null && contrasenia != null) {
                int confirmacion = JOptionPane.showConfirmDialog(
                        this, "¿Confirmar añadir cliente?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if (confirmacion == JOptionPane.YES_OPTION) {
                    BaseDeDatos.anadirCliente(dni, nombre, apellido, email, telefono, tarjeta, contrasenia);
                    tableModel.addRow(new Object[]{dni, nombre, apellido, email, telefono, tarjeta, contrasenia});
                }
            }
        });

        
     // Botón Eliminar Cliente
        JButton btnDelete = new JButton("Eliminar Cliente");
        btnDelete.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String email = (String) tableModel.getValueAt(selectedRow, 4); 
                int confirmacion = JOptionPane.showConfirmDialog(
                        this, "¿Confirmar eliminar cliente?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if (confirmacion == JOptionPane.YES_OPTION) {
                    // Eliminar cliente de la base de datos
                    boolean eliminado = BaseDeDatos.eliminarCliente(email);
                    if (eliminado) {
                        // Eliminar la fila de la tabla
                        tableModel.removeRow(selectedRow);
                        JOptionPane.showMessageDialog(this, "Cliente eliminado correctamente.");
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al eliminar el cliente.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecciona un cliente para eliminar.");
            }
        });


        // Panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnDelete);

        // Agregar componentes al panel
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createAdminsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        String[] columnas = {"Email", "Nombre", "Apellido", "DNI", "Teléfono", "Tarjeta"};
        DefaultTableModel tableModel = new DefaultTableModel(columnas, 0);
        JTable table = new JTable(tableModel);

        // Cargar datos de la base de datos
        List<Admin> admins = BaseDeDatos.obtenerListaAdmins();
        for (Admin admin : admins) {
            tableModel.addRow(new Object[]{
            		admin.getDni(),
                    admin.getNombre(),
                    admin.getApellido(),
                    admin.getEmail(),
                    admin.getNumTel(),
                    admin.getNumTarjeta(),
                    admin.getContrasenia()
            });
        }

        // Panel de botones para futuras funcionalidades
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(new JLabel("Funcionalidades futuras aquí"));

        // Agregar componentes al panel
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createPeliculasPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        String[] columnas = {"Título", "Género", "Estrellas", "Duración", "Descripción"};
        DefaultTableModel tableModel = new DefaultTableModel(columnas, 0);
        JTable table = new JTable(tableModel);

        // Cargar datos de la base de datos
        List<Pelicula> peliculas = BaseDeDatos.obtenerListaPeliculas();
        for (Pelicula pelicula : peliculas) {
            tableModel.addRow(new Object[]{
                pelicula.getTitulo(),
                pelicula.getGenero(),
                pelicula.getEstrellas(),
                pelicula.getDuracion(),
                pelicula.getDescripcion()
            });
        }

        JButton btnAdd = new JButton("Añadir Película");
        btnAdd.addActionListener(e -> {
            JTextField tituloField = new JTextField();
            JComboBox<Genero> generoBox = new JComboBox<>(Genero.values());
            JComboBox<Valoracion> estrellasBox = new JComboBox<>(Valoracion.values());
            JTextField duracionField = new JTextField();
            JTextField descripcionField = new JTextField();
            JTextField fotoField = new JTextField(); // Campo para la ruta de la foto

            JPanel inputPanel = new JPanel(new GridLayout(6, 2)); // Cambiado a 6 filas
            inputPanel.add(new JLabel("Título:"));
            inputPanel.add(tituloField);
            inputPanel.add(new JLabel("Género:"));
            inputPanel.add(generoBox);
            inputPanel.add(new JLabel("Estrellas:"));
            inputPanel.add(estrellasBox);
            inputPanel.add(new JLabel("Duración:"));
            inputPanel.add(duracionField);
            inputPanel.add(new JLabel("Descripción:"));
            inputPanel.add(descripcionField);
            inputPanel.add(new JLabel("Ruta de la foto:")); 
            inputPanel.add(fotoField);

            int confirmacion = JOptionPane.showConfirmDialog(
                    VentanaAdmin.this, inputPanel, "Añadir Película", JOptionPane.OK_CANCEL_OPTION);

            if (confirmacion == JOptionPane.OK_OPTION) {
                BaseDeDatos.anadirPelicula(
                        tituloField.getText(),
                        (Genero) generoBox.getSelectedItem(),
                        (Valoracion) estrellasBox.getSelectedItem(),
                        duracionField.getText(),
                        descripcionField.getText(),
                        fotoField.getText() 
                );
                tableModel.addRow(new Object[]{
                        tituloField.getText(),
                        generoBox.getSelectedItem(),
                        estrellasBox.getSelectedItem(),
                        duracionField.getText(),
                        descripcionField.getText(),
                        fotoField.getText() 
                });
            }
        });


        // Botón Eliminar Película
        JButton btnDelete = new JButton("Eliminar Película");
        btnDelete.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String titulo = (String) tableModel.getValueAt(selectedRow, 0);
                int confirmacion = JOptionPane.showConfirmDialog(
                        this, "¿Confirmar eliminar película?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if (confirmacion == JOptionPane.YES_OPTION) {
                    BaseDeDatos.eliminarPelicula(titulo);
                    tableModel.removeRow(selectedRow);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecciona una película para eliminar.");
            }
        });

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnDelete);

        // Agregar componentes al panel
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }
}
