package gui;

import javax.swing.*;
import domain.Pelicula;
import domain.Valoracion;
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
        
        JPanel panelContenido = new JPanel(new BorderLayout(10, 10));
        panelContenido.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelContenido.setBackground(new Color(245, 245, 245));
        
        ImageIcon posterIcon = new ImageIcon(getClass().getResource(pelicula.getRutaFoto()));
        JLabel labelPoster = new JLabel(posterIcon);
        labelPoster.setHorizontalAlignment(SwingConstants.CENTER);
        panelContenido.add(labelPoster, BorderLayout.NORTH);
        
        JTextArea textArea = new JTextArea();
        textArea.setText("Descripción:\n" + pelicula.getDescripcion() + "\n\n"
                       + "Género: " + pelicula.getGenero() + "\n"
                       + "Duración: " + pelicula.getDuracion() + " minutos");
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Serif", Font.PLAIN, 18));
        textArea.setForeground(new Color(50, 50, 50));
        textArea.setBackground(new Color(245, 245, 245));
        textArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        panelContenido.add(scrollPane, BorderLayout.CENTER);
        
        JLabel labelValoracion = new JLabel("Valoración: " + convertirValoracionAEstrellas(pelicula.getEstrellas()));
        labelValoracion.setFont(new Font("Serif", Font.BOLD, 18));
        labelValoracion.setForeground(new Color(50, 50, 50));
        panelContenido.add(labelValoracion, BorderLayout.SOUTH);
        
        JPanel panelBoton = new JPanel();
        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Arial", Font.BOLD, 16));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setBackground(new Color(70, 130, 180));
        btnVolver.setFocusPainted(false);
        btnVolver.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnVolver.addActionListener(e -> dispose());
        btnVolver.addActionListener((e) -> logger.log(Level.INFO, "Se ha pulsado el botón volver"));
        panelBoton.add(btnVolver);
        
        add(panelContenido, BorderLayout.CENTER);
        add(panelBoton, BorderLayout.SOUTH);
        getContentPane().setBackground(Color.WHITE);
        
        setVisible(true);
    }
    private String convertirValoracionAEstrellas(Valoracion valoracion) {
        switch (valoracion) {
            case UNA_ESTRELLA: return "★☆☆☆☆";
            case DOS_ESTRELLAS: return "★★☆☆☆";
            case TRES_ESTRELLAS: return "★★★☆☆";
            case CUATRO_ESTRELLAS: return "★★★★☆";
            case CINCO_ESTRELLAS: return "★★★★★";
            default: return "";
        }
    }
}
