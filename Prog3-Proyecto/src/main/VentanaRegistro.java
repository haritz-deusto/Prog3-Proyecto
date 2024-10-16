package main;


import javax.swing.JFrame;


class VentanaRegistro extends JFrame{
	public VentanaRegistro() {
	        setTitle("Registro");
	        setSize(1000, 800);
	        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Para cerrar solo esta ventana
	        setLocationRelativeTo(null);
	        setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
}
