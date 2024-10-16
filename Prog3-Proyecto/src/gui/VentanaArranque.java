package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import imagenes.*;

public class VentanaArranque  extends JFrame{
	private static final long serialVersionUID = 1L;
	public VentanaArranque() {
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        JPanel panelCentral = new JPanel();
        JPanel panelSuperior = new JPanel();
        JLabel labelImagen = new JLabel();
        add(panelCentral, BorderLayout.CENTER);
        ImageIcon imagen = new ImageIcon("imagenes/LogoDeustoStream.jpeg");
        panelSuperior.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton botonInicioSesion = new JButton("Iniciar Sesión");
        JButton botonRegistro = new JButton("Registrase");
        JButton botonPrueba = new JButton("Iniciar Sesión como Admin");
        panelSuperior.add(botonPrueba);
        panelSuperior.add(botonInicioSesion);
        panelSuperior.add(botonRegistro);
        add(panelSuperior, BorderLayout.NORTH);
        panelCentral.add(labelImagen);
        
        
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
