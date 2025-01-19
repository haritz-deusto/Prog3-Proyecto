package gui;

import javax.swing.*;

import main.Main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VentanaArranque extends JFrame {
    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(Main.class.getName());

    public VentanaArranque() {

        JPanel panelCentral = new JPanel(new BorderLayout());
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20)); // Espaciado entre botones

        //LOGO
        ImageIcon imagen = new ImageIcon(getClass().getResource("/imagenes/deustoStreamLogo1.png"));
        Image imagenEscalada = imagen.getImage().getScaledInstance(512, 256, Image.SCALE_SMOOTH); 
        ImageIcon imagenFinal = new ImageIcon(imagenEscalada); 
        JLabel labelImagen = new JLabel(imagenFinal); 
        labelImagen.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 
        labelImagen.setHorizontalAlignment(JLabel.CENTER); 
        labelImagen.setVerticalAlignment(JLabel.CENTER);   

        //BOTONES
        JButton botonInicioSesion = new JButton("Iniciar Sesión");
        botonInicioSesion.setToolTipText("Haz clic para iniciar sesión");
        
        JButton botonInicioSesionAdmin = new JButton("ADMIN");
        botonInicioSesionAdmin.setToolTipText("Haz clic para iniciar sesión como ADMIN");

        JButton botonRegistro = new JButton("Registrarse");
        botonRegistro.setToolTipText("Haz clic para registrarte");

        JButton botonPrueba = new JButton("Iniciar sin registro");
        botonPrueba.setToolTipText("Boton iniciar sin registro. Accede a la aplicación sin registrarte ni iniciar sesión.");

        //PERSONALIZAR BOTONES
        Color ColorDeustoStream = new Color(100, 125, 255);
        
        botonInicioSesion.setBackground(new Color(0, 123, 255)); // Color fondo azul
        botonInicioSesion.setForeground(ColorDeustoStream); 
        botonInicioSesion.setFont(new Font("Arial", Font.BOLD, 14)); 
        botonInicioSesion.setPreferredSize(new Dimension(150, 40)); 
        
        botonInicioSesionAdmin.setBackground(new Color(0, 123, 255)); // Color fondo azul
        botonInicioSesionAdmin.setForeground(ColorDeustoStream); 
        botonInicioSesionAdmin.setFont(new Font("Arial", Font.BOLD, 14)); 
        botonInicioSesionAdmin.setPreferredSize(new Dimension(150, 40));

        botonRegistro.setBackground(new Color(0, 123, 255)); // Color de fondo azul
        botonRegistro.setForeground(ColorDeustoStream); 
        botonRegistro.setFont(new Font("Arial", Font.BOLD, 14));
        botonRegistro.setPreferredSize(new Dimension(150, 40));

        botonPrueba.setBackground(new Color(255, 193, 7)); // Color amarillo
        botonPrueba.setForeground(ColorDeustoStream);
        botonPrueba.setFont(new Font("Arial", Font.BOLD, 14));
        botonPrueba.setPreferredSize(new Dimension(170, 40));
        
        
        //PANEL INFERIOR
        panelInferior.add(botonPrueba);
        panelInferior.add(botonInicioSesion);
        panelInferior.add(botonRegistro);
        panelInferior.add(botonInicioSesionAdmin);
        panelInferior.setBackground(Color.DARK_GRAY);

        //PANEL CENTRAL
        panelCentral.setBackground(Color.LIGHT_GRAY); 
        panelCentral.add(labelImagen, BorderLayout.CENTER);

        add(panelCentral, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        botonPrueba.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
                ventanaPrincipal.setVisible(true);
                logger.log(Level.INFO, "Se ha pulsado el botón de prueba");
            }
        });

        botonRegistro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaRegistro ventanaRegistro = new VentanaRegistro();
                ventanaRegistro.setVisible(true);
                logger.log(Level.INFO, "Se ha pulsado el botón Registro");
            }
        });

        botonInicioSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaInicioSesion ventanaInicioSesion = new VentanaInicioSesion();
                ventanaInicioSesion.setVisible(true);
                logger.log(Level.INFO, "Se ha pulsado el botón de Inicio de Sesión");
            }
        });
        
        botonInicioSesionAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaInicioSesionAdmin ventanaInicioSesionAdmin = new VentanaInicioSesionAdmin();
                ventanaInicioSesionAdmin.setVisible(true);
            	
                
            	//VentanaAdmin ventanaAdmin = new VentanaAdmin();
            	//ventanaAdmin.setVisible(true);
            	logger.log(Level.INFO, "Se ha pulsado el botón de Inicio de Sesión como ADMIN");
            	
            }
        });

        
        
		setIconImage(imagen.getImage());
        setExtendedState(MAXIMIZED_BOTH);
        setTitle("DeustoStream");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
