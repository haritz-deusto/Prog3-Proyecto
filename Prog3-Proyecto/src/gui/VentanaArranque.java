package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class VentanaArranque  extends JFrame{
	private static final long serialVersionUID = 1L;
	public VentanaArranque() {
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        JPanel panelCentral = new JPanel();
        JPanel panelInferior = new JPanel();
        
        
        ImageIcon imagen = new ImageIcon("Prog3-Proyecto/imagenes/Logo.png");
        JLabel labelImagen = new JLabel(imagen);
        
        panelInferior.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelCentral.setLayout(new BorderLayout());
        
        JButton botonInicioSesion = new JButton("Iniciar Sesión");
        JButton botonRegistro = new JButton("Registrase");
        JButton botonPrueba = new JButton("Boton Prueba");
        JButton botonInicioAdmin = new JButton("Inicio de Sesion como Administrador");
        
        add(panelInferior, BorderLayout.SOUTH);   
        add(panelCentral, BorderLayout.CENTER);
        
        panelInferior.add(botonPrueba);
        panelInferior.add(botonInicioSesion);
        panelInferior.add(botonRegistro);
        panelInferior.add(botonInicioAdmin);
        panelCentral.add(labelImagen,BorderLayout.CENTER);
        
        
       
        
        botonPrueba.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
            	ventanaPrincipal.setVisible(true);
			}
		});
        
        botonRegistro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VentanaRegistro ventanaRegistro = new VentanaRegistro();
                ventanaRegistro.setVisible(true);
            }
        });

        botonInicioSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VentanaInicioSesion ventanaInicioSesion = new VentanaInicioSesion();
                ventanaInicioSesion.setVisible(true);
            	
            }
        });
        

        
        setVisible(true);
	}
}
