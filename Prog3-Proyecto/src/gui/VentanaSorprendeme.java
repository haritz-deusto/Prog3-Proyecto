package gui;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import domain.Pelicula;

public class VentanaSorprendeme extends JFrame {

    private JLabel labelTitulo;

    public VentanaSorprendeme(List<Pelicula> peliculas) {
        setTitle("Sorpréndeme");
        setSize(400, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        labelTitulo = new JLabel("", SwingConstants.CENTER);
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 20));
        add(labelTitulo, BorderLayout.CENTER);

        setVisible(true);

        //Hilo animacion
        new Thread(() -> iniciarAnimacion(peliculas)).start();

        //Hilo sonido
        new Thread(this::reproducirSonido).start();
    }

    private void iniciarAnimacion(List<Pelicula> peliculas) {
        Random random = new Random();
        long startTime = System.currentTimeMillis();

        while (System.currentTimeMillis() - startTime < 5500) { //5 segundos de animación
            int index = random.nextInt(peliculas.size());
            Pelicula peliculaActual = peliculas.get(index);

            SwingUtilities.invokeLater(() -> labelTitulo.setText(peliculaActual.getTitulo()));

            try {
                Thread.sleep(100); // Cambia de título cada 100 ms
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        //seleccion pelicula random
        int peliculaSeleccionadaIndex = random.nextInt(peliculas.size());
        Pelicula peliculaSeleccionada = peliculas.get(peliculaSeleccionadaIndex);

        SwingUtilities.invokeLater(() -> {
            new VentanaPelicula(peliculaSeleccionada);
            dispose();
        });
    }

    private void reproducirSonido() {
        try {
            File sonidoFile = new File(getClass().getResource("/imagenes/peliculaRandom.wav").getFile());
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(sonidoFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);

            Thread.sleep(5500);

            clip.stop();
            clip.close();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
            System.err.println("Error al reproducir el sonido: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
