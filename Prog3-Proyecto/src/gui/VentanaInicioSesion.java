package gui;

import javax.swing.JFrame;

class VentanaInicioSesion extends JFrame {
    public VentanaInicioSesion() {
        // Configurar la ventana de inicio de sesión
        setTitle("Inicio de Sesión");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Para cerrar solo esta ventana
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
}