package gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import main.Main;

public class VentanaCuenta extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    private JLabel labelEmail, labelTelefono, labelContrasenia, labelConfirmarContrasenia;
    private JTextField txtEmail, txtTelefono;
    private JPasswordField txtContrasenia, txtConfirmarContrasenia;
    private JButton btnGuardarCambios, btnVolver;

    public VentanaCuenta() {
        configurarVentana();
        inicializarComponentes();
        agregarComponentes();
        agregarEventos();
        ImageIcon imagen = new ImageIcon(getClass().getResource("/imagenes/deustoStreamLogo1.png"));
		setIconImage(imagen.getImage());
    }

    private void configurarVentana() {
        setTitle("Configurar cuenta");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }

    private void inicializarComponentes() {
        labelEmail = new JLabel("Email:");
        labelTelefono = new JLabel("Teléfono:");
        labelContrasenia = new JLabel("Contraseña:");
        labelConfirmarContrasenia = new JLabel("Confirmar contraseña:");

        txtEmail = new JTextField(20);
        txtTelefono = new JTextField(15);
        txtContrasenia = new JPasswordField(20);
        txtConfirmarContrasenia = new JPasswordField(20);

        btnGuardarCambios = new JButton("Guardar cambios");
        btnGuardarCambios.setEnabled(false); // Deshabilitado por defecto
        btnVolver = new JButton("Volver");
    }

    private void agregarComponentes() {
        JPanel panelCentro = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        agregarComponente(panelCentro, labelEmail, gbc, 0, 0);
        agregarComponente(panelCentro, txtEmail, gbc, 1, 0);
        agregarComponente(panelCentro, labelTelefono, gbc, 0, 1);
        agregarComponente(panelCentro, txtTelefono, gbc, 1, 1);
        agregarComponente(panelCentro, labelContrasenia, gbc, 0, 2);
        agregarComponente(panelCentro, txtContrasenia, gbc, 1, 2);
        agregarComponente(panelCentro, labelConfirmarContrasenia, gbc, 0, 3);
        agregarComponente(panelCentro, txtConfirmarContrasenia, gbc, 1, 3);

        JPanel panelSur = new JPanel();
        panelSur.add(btnGuardarCambios);
        panelSur.add(btnVolver);

        add(panelCentro, BorderLayout.CENTER);
        add(panelSur, BorderLayout.SOUTH);
    }

    private void agregarComponente(JPanel panel, java.awt.Component componente, GridBagConstraints gbc, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
        panel.add(componente, gbc);
    }

    private void agregarEventos() {
        btnVolver.addActionListener(e -> dispose());
        btnVolver.addActionListener((e) -> logger.log(Level.INFO, "Se ha pulsado el botón volver"));
        
        DocumentListener passwordListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                comprobarCoincidenciaContrasenia();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                comprobarCoincidenciaContrasenia();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                comprobarCoincidenciaContrasenia();
            }
        };

        txtContrasenia.getDocument().addDocumentListener(passwordListener);
        txtConfirmarContrasenia.getDocument().addDocumentListener(passwordListener);
        
        btnGuardarCambios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = txtEmail.getText();
                String telefono = txtTelefono.getText();
                String nuevaContrasenia = new String(txtContrasenia.getPassword());
                boolean exito = actualizarCuenta(email, telefono, nuevaContrasenia);
                if (exito) {
                    JOptionPane.showMessageDialog(null, "Datos actualizados correctamente.");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "No se ha encontrado el usuario o las contraseñas no coinciden.");
                }
            }
        });
    }

    private void comprobarCoincidenciaContrasenia() {
        String contrasenia = new String(txtContrasenia.getPassword());
        String confirmarContrasenia = new String(txtConfirmarContrasenia.getPassword());
        btnGuardarCambios.setEnabled(contrasenia.equals(confirmarContrasenia) && !contrasenia.isEmpty());
    }
    
    private boolean actualizarCuenta(String email, String telefono, String nuevaContrasenia) {
        File archivo = new File("usuarios.txt");
        List<String> lineas = new ArrayList<>();
        boolean usuarioEncontrado = false;

        try {
            Scanner scanner = new Scanner(archivo);
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] datos = linea.split(";");
                String emailGuardado = datos[2];
                String telefonoGuardado = datos[3];

                if (email.equals(emailGuardado) && telefono.equals(telefonoGuardado)) {
                    // Si el email y el teléfono coinciden, actualizamos la contraseña
                    datos[5] = nuevaContrasenia;  // Cambiar la contraseña
                    linea = String.join(";", datos);
                    usuarioEncontrado = true;
                }
                lineas.add(linea);
            }
            scanner.close();

            if (usuarioEncontrado) {
                // Sobrescribir el archivo con las nuevas líneas
                PrintWriter writer = new PrintWriter(new FileWriter(archivo));
                for (String linea : lineas) {
                    writer.println(linea);
                }
                writer.close();
                return true;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false; // Si no se encontró el usuario
    }
}
