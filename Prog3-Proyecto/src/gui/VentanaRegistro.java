package gui;


import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


class VentanaRegistro extends JFrame{
	  
	private JPanel panelCentro, panelSur, panelNorte, panelEste, panelOeste;
	    
	private JButton btnVolver;
	public VentanaRegistro() {
	        setTitle("Registro");
	        setSize(1000, 800);
	        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Para cerrar solo esta ventana
	        setLocationRelativeTo(null);
	        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	        
	     panelCentro = new JPanel();
	     panelSur = new JPanel();
	     btnVolver = new JButton("Volver");
	     
	     
	     
	     
	     
	     getContentPane().add(panelCentro, BorderLayout.CENTER);
	     getContentPane().add(panelSur, BorderLayout.SOUTH);
	     
	     
	     panelSur.add(btnVolver);
	     
	     
	     btnVolver.addActionListener((e)->{
	    	 dispose();
	     });
	}
}
