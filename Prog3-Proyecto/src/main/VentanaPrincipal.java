package main;

import javax.swing.JFrame;

public class VentanaPrincipal {
	public static void main(String[] args) {
        JFrame ventana = new JFrame("Deusto Stream");
        ventana.setSize(1000, 800);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
}
