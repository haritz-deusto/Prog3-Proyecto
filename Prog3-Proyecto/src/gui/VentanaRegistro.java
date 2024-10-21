package gui;


import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


class VentanaRegistro extends JFrame{
	  
	private JPanel panelCentro, panelSur, panelNorte, panelEste, panelOeste;
	 
	private JButton btnVolver;
	
	private JLabel labelNombre;
	private JLabel labelApellido;
	private JLabel labelDni;
	private JLabel labelEmail;
	private JLabel labelNumTel;
	private JLabel labelNumTarjeta;
	private JLabel labelContrasenia;
	
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDni;
	private JTextField txtEmail;
	private JTextField txtnumTel;
	private JTextField txtNumTarjeta;
	private JPasswordField txtContrasenia;
	
	
	public VentanaRegistro() {
		setTitle("Registro");
		setSize(1000, 800);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Para cerrar solo esta ventana
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	        
	    panelCentro = new JPanel(new GridLayout(14,1));
	    panelSur = new JPanel();
	    
	    btnVolver = new JButton("Volver");
	     
	    labelNombre = new JLabel("Introduzca su nombre");
	    labelApellido = new JLabel("Introduzca su apellido");
	    labelDni = new JLabel("Introduzca su DNI");
	    labelEmail = new JLabel("Introduzca su email");
	    labelNumTel = new JLabel("Introduzca su numero de telefono");
	    labelNumTarjeta = new JLabel("Introduzca su numero de tarjeta");
	    labelContrasenia = new JLabel("Introduzca su contraseña");
	    
	    txtNombre = new JTextField();
	    txtApellido = new JTextField();
	    txtDni = new JTextField();
	    txtEmail = new JTextField();
	    txtnumTel = new JTextField();
	    txtNumTarjeta = new JTextField();
	    txtContrasenia = new JPasswordField();
	     
	     
	    getContentPane().add(panelCentro, BorderLayout.CENTER);
	    getContentPane().add(panelSur, BorderLayout.SOUTH);
	     
	    panelCentro.add(labelNombre);
	    panelCentro.add(txtNombre);
	    panelCentro.add(labelApellido);
	    panelCentro.add(txtApellido);
	    panelCentro.add(labelDni);
	    panelCentro.add(txtDni);
	    panelCentro.add(labelEmail);
	    panelCentro.add(txtEmail);
	    panelCentro.add(labelNumTel);
	    panelCentro.add(txtnumTel);
	    panelCentro.add(labelNumTarjeta);
	    panelCentro.add(txtNumTarjeta);
	    panelCentro.add(labelContrasenia);
	    panelCentro.add(txtContrasenia);
	  
	    panelSur.add(btnVolver);
	     
	    btnVolver.addActionListener((e)->{
	    	 dispose();
	    });
	}
}
