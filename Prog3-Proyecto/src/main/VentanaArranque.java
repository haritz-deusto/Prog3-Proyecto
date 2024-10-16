package main;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaArranque  extends JFrame{
	private static final long serialVersionUID = 1L;
	public static void main(String[] args) {
        JFrame ventana = new JFrame("Deusto Stream");
        ventana.setSize(1000, 800);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
        ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JButton botonInicioSesion = new JButton("Iniciar Sesión");
        JButton botonRegistro = new JButton("Registrase");
        JButton botonPrueba = new JButton("Boton Prueba");
        panelSuperior.add(botonPrueba);
        panelSuperior.add(botonInicioSesion);
        panelSuperior.add(botonRegistro);
        ventana.add(panelSuperior, BorderLayout.NORTH);
        
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
        
        ventana.setVisible(true);
    }
}
