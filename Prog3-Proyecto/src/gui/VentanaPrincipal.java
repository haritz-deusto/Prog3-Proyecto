package gui;

import javax.swing.JFrame;

public class VentanaPrincipal extends JFrame{
	public VentanaPrincipal(){
		setTitle("DeuStream");
		setSize(1000,800);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
}
