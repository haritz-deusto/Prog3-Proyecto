package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaArranque extends JFrame {
    private static final long serialVersionUID = 1L;

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

        JButton botonRegistro = new JButton("Registrarse");
        botonRegistro.setToolTipText("Haz clic para registrarte");

        JButton botonPrueba = new JButton("Botón Prueba");
        botonPrueba.setToolTipText("test");

        //PERSONALIZAR BOTONES
        botonInicioSesion.setBackground(new Color(0, 123, 255)); // Color fondo azul
        botonInicioSesion.setForeground(Color.WHITE); 
        botonInicioSesion.setFont(new Font("Arial", Font.BOLD, 14)); 
        botonInicioSesion.setPreferredSize(new Dimension(150, 40)); 

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
            }
        });

        botonRegistro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaRegistro ventanaRegistro = new VentanaRegistro();
                ventanaRegistro.setVisible(true);
            }
        });

        botonInicioSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaInicioSesion ventanaInicioSesion = new VentanaInicioSesion();
                ventanaInicioSesion.setVisible(true);
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
