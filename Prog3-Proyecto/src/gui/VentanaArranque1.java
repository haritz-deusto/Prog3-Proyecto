package gui;

import javax.swing.*;

import main.Main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VentanaArranque1 extends JFrame {
    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(Main.class.getName());

    public VentanaArranque1() {

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

        JButton botonPrueba = new JButton("Botón Prueba");
        botonPrueba.setToolTipText("test");

        //PERSONALIZAR BOTONES
        botonInicioSesion.setBackground(new Color(0, 123, 255)); // Color fondo azul
        botonInicioSesion.setForeground(Color.WHITE); 
        botonInicioSesion.setFont(new Font("Arial", Font.BOLD, 14)); 
        botonInicioSesion.setPreferredSize(new Dimension(150, 40)); 
        
        botonInicioSesionAdmin.setBackground(new Color(0, 123, 255)); // Color fondo azul
        botonInicioSesionAdmin.setForeground(Color.WHITE); 
        botonInicioSesionAdmin.setFont(new Font("Arial", Font.BOLD, 14)); 
        botonInicioSesionAdmin.setPreferredSize(new Dimension(150, 40));

        botonRegistro.setBackground(new Color(0, 123, 255)); // Color de fondo azul
        botonRegistro.setForeground(Color.WHITE); 
        botonRegistro.setFont(new Font("Arial", Font.BOLD, 14));
        botonRegistro.setPreferredSize(new Dimension(150, 40));

        botonPrueba.setBackground(new Color(255, 193, 7)); // Color amarillo
        botonPrueba.setForeground(Color.BLACK);
        botonPrueba.setFont(new Font("Arial", Font.BOLD, 14));
        botonPrueba.setPreferredSize(new Dimension(150, 40));
        
        
        //PANEL INFERIOR
        panelInferior.add(botonPrueba);
        panelInferior.add(botonInicioSesion);
        panelInferior.add(botonRegistro);
        panelInferior.add(botonInicioSesionAdmin);

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
                VentanaInicioSesion1 ventanaInicioSesion = new VentanaInicioSesion1();
                ventanaInicioSesion.setVisible(true);
                logger.log(Level.INFO, "Se ha pulsado el botón de Inicio de Sesión");
            }
        });
        
        botonInicioSesionAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaInicioSesionAdmin1 ventanaInicioSesionAdmin = new VentanaInicioSesionAdmin1();
                ventanaInicioSesionAdmin.setVisible(true);
                logger.log(Level.INFO, "Se ha pulsado el botón de Inicio de Sesión como ADMIN");
            }
        });

        
        
		setIconImage(imagen.getImage());
        setExtendedState(MAXIMIZED_BOTH);
        setTitle("DeustoStream");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
