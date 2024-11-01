package gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
        setTitle("Cuenta");
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
    }

    private void comprobarCoincidenciaContrasenia() {
        String contrasenia = new String(txtContrasenia.getPassword());
        String confirmarContrasenia = new String(txtConfirmarContrasenia.getPassword());
        btnGuardarCambios.setEnabled(contrasenia.equals(confirmarContrasenia) && !contrasenia.isEmpty());
    }
}
