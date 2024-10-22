package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class VentanaPrincipal extends JFrame{
	
	private JMenuBar barraMenu;
	
	private JMenuItem itemCuenta, itemPeliculas;
	
	private JMenu visualizarCuenta, visualizarPeliculas;
	
	private JPanel panelCentro, panelSur, panelNorte, panelEste, panelOeste;
	
	private JLabel labelTitulo;
	
	private JButton btnVolver;
	
	public VentanaPrincipal(){
		setTitle("DeuStream");
		setSize(1000,800);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		barraMenu = new JMenuBar();
		
		visualizarCuenta = new JMenu("Cuenta");
		itemCuenta = new JMenuItem("Mi cuenta");
		
		visualizarPeliculas = new JMenu("Peliculas");
		itemCuenta = new JMenuItem("Peliculas");
		
		barraMenu.add(visualizarCuenta);
		barraMenu.add(visualizarPeliculas);
		
		panelCentro = new JPanel();
		panelNorte = new JPanel(new BorderLayout());
		panelSur = new JPanel();
		panelEste = new JPanel();
		panelOeste = new JPanel();
		
		btnVolver = new JButton("Volver");
		
		labelTitulo = new JLabel("DeuStream");
		labelTitulo.setFont(new Font(Font.SERIF, Font.BOLD, 40));
		labelTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		
		getContentPane().add(panelNorte, BorderLayout.NORTH);
		getContentPane().add(panelEste, BorderLayout.EAST);
		getContentPane().add(panelOeste, BorderLayout.WEST);
		getContentPane().add(panelSur, BorderLayout.SOUTH);
		getContentPane().add(panelCentro, BorderLayout.CENTER);
		
		panelNorte.add(labelTitulo, BorderLayout.NORTH);
		panelNorte.add(barraMenu, BorderLayout.CENTER);
		
		btnVolver.addActionListener((e)->{
        	dispose();
        });
	}
}
