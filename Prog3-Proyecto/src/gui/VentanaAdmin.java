package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class VentanaAdmin extends JFrame{
	
	private static final long serialVersionUID = 1L;
	public VentanaAdmin() {
		configurarVentanaAdmin();
	}
	private void configurarVentanaAdmin() {
        setTitle("Ventana ADMIN");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); 
        setLayout(new BorderLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
	
}
