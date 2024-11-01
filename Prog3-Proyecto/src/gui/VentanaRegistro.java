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

public class VentanaRegistro extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    // Componentes de la ventana
    private JLabel labelNombre, labelApellido, labelEmail, labelNumTel, labelNumTarjeta, labelContrasenia, labelConfirmarContrasenia;
    private JTextField txtNombre, txtApellido, txtEmail, txtNumTel, txtNumTarjeta;
    private JPasswordField txtContrasenia, txtConfirmarContrasenia;
    private JButton btnVolver, btnConfirmar;

    public VentanaRegistro() {
        configurarVentana();
        inicializarComponentes();
        agregarComponentes();
        agregarEventos();
    }

    // Método para configurar las propiedades de la ventana
    private void configurarVentana() {
        setTitle("Registro");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setLayout(new BorderLayout());
    }

    // Método para inicializar los componentes
    private void inicializarComponentes() {
        labelNombre = new JLabel("Introduzca su nombre:");
        labelApellido = new JLabel("Introduzca su apellido:");
        labelEmail = new JLabel("Introduzca su email:");
        labelNumTel = new JLabel("Introduzca su número de teléfono:");
        labelNumTarjeta = new JLabel("Introduzca su número de tarjeta:");
        labelContrasenia = new JLabel("Introduzca su contraseña:");
        labelConfirmarContrasenia = new JLabel("Confirme su contraseña:");

        txtNombre = new JTextField(20);
        txtApellido = new JTextField(20);
        txtEmail = new JTextField(20);
        txtNumTel = new JTextField(15);
        txtNumTarjeta = new JTextField(16);
        txtContrasenia = new JPasswordField(20);
        txtConfirmarContrasenia = new JPasswordField(20);

        btnVolver = new JButton("Volver");
        btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setEnabled(false); //desactivado hasta confirmar las 2 contrasenas
    }

    // Método para agregar los componentes al panel principal con GridBagLayout
    private void agregarComponentes() {
        JPanel panelCentro = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Márgenes entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Añadir campos al panel
        agregarComponente(panelCentro, labelNombre, gbc, 0, 0);
        agregarComponente(panelCentro, txtNombre, gbc, 1, 0);
        agregarComponente(panelCentro, labelApellido, gbc, 0, 1);
        agregarComponente(panelCentro, txtApellido, gbc, 1, 1);
        agregarComponente(panelCentro, labelEmail, gbc, 0, 2);
        agregarComponente(panelCentro, txtEmail, gbc, 1, 2);
        agregarComponente(panelCentro, labelNumTel, gbc, 0, 3);
        agregarComponente(panelCentro, txtNumTel, gbc, 1, 3);
        agregarComponente(panelCentro, labelNumTarjeta, gbc, 0, 4);
        agregarComponente(panelCentro, txtNumTarjeta, gbc, 1, 4);
        agregarComponente(panelCentro, labelContrasenia, gbc, 0, 5);
        agregarComponente(panelCentro, txtContrasenia, gbc, 1, 5);
        agregarComponente(panelCentro, labelConfirmarContrasenia, gbc, 0, 6);
        agregarComponente(panelCentro, txtConfirmarContrasenia, gbc, 1, 6);

        // BOTON VOLVER Y CONFIRMAR
        JPanel panelSur = new JPanel();
        panelSur.add(btnVolver);
        panelSur.add(btnConfirmar);

        add(panelCentro, BorderLayout.CENTER);
        add(panelSur, BorderLayout.SOUTH);
        
        
        ImageIcon imagen = new ImageIcon(getClass().getResource("/imagenes/deustoStreamLogo1.png"));
		setIconImage(imagen.getImage());
    }

    // MÉTODO PARA AÑADIR COMPONENTES AL PANEL
    private void agregarComponente(JPanel panel, java.awt.Component componente, GridBagConstraints gbc, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
        panel.add(componente, gbc);
    }

    //método para agregar los eventos a los componentes
    private void agregarEventos() {
        btnVolver.addActionListener(e -> dispose()); // Cierra la ventana de registro
        btnVolver.addActionListener((e) -> logger.log(Level.INFO, "Se ha pulsado el botón volver"));

        //Habilitar botón Confirmar solo cuando las contraseñas coincidan
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
        btnConfirmar.setEnabled(contrasenia.equals(confirmarContrasenia) && !contrasenia.isEmpty()); 
    } //habilitar boton contraseña si las contraseñas son iguales. En el futuro pdoemos hacer que solo
    //se habilite si todos los campos estan completos (escritos) 
}
