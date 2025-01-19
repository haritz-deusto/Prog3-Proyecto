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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import db.BaseDeDatos;
import domain.Admin;
import domain.Cliente;
import domain.Usuario;
import main.Main;

public class VentanaInicioSesion extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    // Componentes de la ventana
    private JLabel labelEmail;
    private JLabel labelContrasenia;

    private JTextField txtEmail;
    private JPasswordField txtContrasenia;

    private JButton btnVolver;
    private JButton btnConfirmar;

    public VentanaInicioSesion() {
        configurarVentana();
        inicializarComponentes();
        agregarComponentes();
        agregarEventos();
    }

    // Método para configurar las propiedades de la ventana
    private void configurarVentana() {
        setTitle("Inicio de Sesión");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); 
        setLayout(new BorderLayout());
    }

    // Método para inicializar los componentes
    private void inicializarComponentes() {
        labelEmail = new JLabel("Introduzca su email:");
        labelContrasenia = new JLabel("Introduzca su contraseña:");

        txtEmail = new JTextField(20);
        txtContrasenia = new JPasswordField(20);

        btnVolver = new JButton("Volver");
        btnConfirmar = new JButton("Confirmar"); 
    }

    // Método para agregar los componentes al panel
    private void agregarComponentes() {
        JPanel panelCentro = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Márgenes entre los componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Añadir componentes al panel
        agregarComponente(panelCentro, labelEmail, gbc, 0, 0);
        agregarComponente(panelCentro, txtEmail, gbc, 1, 0);
        agregarComponente(panelCentro, labelContrasenia, gbc, 0, 1);
        agregarComponente(panelCentro, txtContrasenia, gbc, 1, 1);

        // BOTON VOLVER
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 3; 
        gbc.anchor = GridBagConstraints.CENTER;
        panelCentro.add(btnVolver, gbc);

        // BOTON CONFIRMAR
        gbc.gridy = 2; 
        panelCentro.add(btnConfirmar, gbc);
        
        
        
        add(panelCentro, BorderLayout.CENTER);
        
        
        
        ImageIcon imagen = new ImageIcon(getClass().getResource("/imagenes/deustoStreamLogo1.png"));
		setIconImage(imagen.getImage());
    }

    // MÉTODO PARA AÑADIR COMPONENTES AL PANEL
    private void agregarComponente(JPanel panel, java.awt.Component componente, GridBagConstraints gbc, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
        panel.add(componente, gbc);
    }

    private void agregarEventos() {
        btnVolver.addActionListener((e) -> dispose()); // Cierra la ventana
        btnVolver.addActionListener((e) -> logger.log(Level.INFO, "Se ha pulsado el botón volver"));
        
        btnConfirmar.addActionListener((e)->{
        	String email = txtEmail.getText();
        	String contrasenia = new String(txtContrasenia.getPassword());
        	
        	// Usar el método para obtener el usuario de la base de datos
            Usuario usuario = BaseDeDatos.obtenerUsuarioPorEmailYContrasenia(email, contrasenia);

            if (usuario != null && usuario instanceof Cliente) {
                JOptionPane.showMessageDialog(this, "Bienvenido!");
                new VentanaPrincipal();
                dispose();
            } else if (usuario instanceof Admin) {
                JOptionPane.showMessageDialog(this, "Acceso denegado. Usa la ventana de admin para iniciar sesión.");
            } else {
                JOptionPane.showMessageDialog(this, "Credenciales incorrectas. Inténtelo de nuevo.");
            }

            limpiarCampos();
        });
    }
    private void limpiarCampos() {
    	txtEmail.setText("");
    	txtContrasenia.setText("");
    }
}
