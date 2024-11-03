package gui;

import javax.swing.*;
import domain.Pelicula;
import main.Main;

import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VentanaPelicula extends JFrame {
    
	private static final Logger logger = Logger.getLogger(Main.class.getName());
	
    public VentanaPelicula(Pelicula pelicula) {
        setTitle(pelicula.getTitulo());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        
        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(new BorderLayout());
        
        ImageIcon posterIcon = new ImageIcon(getClass().getResource(pelicula.getRutaFoto()));
        JLabel labelPoster = new JLabel(posterIcon);
        labelPoster.setHorizontalAlignment(SwingConstants.CENTER);
        panelContenido.add(labelPoster, BorderLayout.NORTH);
        
        JTextArea textArea = new JTextArea();
        textArea.setText("Descripción: " + pelicula.getDescripcion() + "\n"
                       + "Género: " + pelicula.getGenero() + "\n"
                       + "Duración: " + pelicula.getDuracion() + " minutos");
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        textArea.setMargin(new Insets(10, 10, 10, 10));
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        panelContenido.add(scrollPane, BorderLayout.CENTER);
        
        JPanel panelBoton = new JPanel();
        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> dispose());
        btnVolver.addActionListener((e) -> logger.log(Level.INFO, "Se ha pulsado el botón volver"));
        panelBoton.add(btnVolver);
        
        add(panelContenido, BorderLayout.CENTER);
        add(panelBoton, BorderLayout.SOUTH);
        getContentPane().setBackground(Color.WHITE);
        
        setVisible(true);
    }
}
