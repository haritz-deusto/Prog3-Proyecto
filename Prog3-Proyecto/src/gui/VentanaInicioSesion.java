package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

class VentanaInicioSesion extends JFrame {
	private JLabel labelNombre;
    private JLabel labelDni;
    private JLabel labelContrasenia;
    
    private JTextField txtNombre;
    private JTextField txtDni;
    private JPasswordField txtContrasenia;
    
    private JPanel panelCentro, panelSur, panelNorte, panelEste, panelOeste;
    
    private JButton btnVolver;
    
    public VentanaInicioSesion() {
        // Configurar la ventana de inicio de sesión
        setTitle("Inicio de Sesión");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Para cerrar solo esta ventana
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        panelCentro = new JPanel();
        panelCentro.setLayout(new GridLayout(6,1));
		panelNorte = new JPanel();
		panelSur = new JPanel();
		panelEste = new JPanel();
		panelOeste = new JPanel();
		
		labelNombre = new JLabel("Introduzca su nombre");
		labelDni= new JLabel("Introduzca su DNI");
		labelContrasenia = new JLabel("Introduzca su contraseña");
		
		txtNombre = new JTextField(20);
		txtDni= new JTextField(9);
		txtContrasenia = new JPasswordField(30);
		
		btnVolver = new JButton("Volver");
        
        
        getContentPane().add(panelNorte, BorderLayout.NORTH);
		getContentPane().add(panelEste, BorderLayout.EAST);
		getContentPane().add(panelOeste, BorderLayout.WEST);
		getContentPane().add(panelSur, BorderLayout.SOUTH);
		getContentPane().add(panelCentro, BorderLayout.CENTER);
		
		panelCentro.add(labelNombre);
		panelCentro.add(txtNombre);
		panelCentro.add(labelDni);
		panelCentro.add(txtDni);
		panelCentro.add(labelContrasenia);
		panelCentro.add(txtContrasenia);
		
        panelSur.add(btnVolver);
        
        
        btnVolver.addActionListener((e)->{
        	dispose();
        });
    }
}