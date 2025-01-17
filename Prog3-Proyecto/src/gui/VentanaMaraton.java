package gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import domain.Pelicula;

public class VentanaMaraton extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtNumeroPeliculas;
    private JButton btnGenerarMaraton;
    private JList<Pelicula> listaPeliculas;
    private DefaultListModel<Pelicula> modeloListaPeliculas;

    public VentanaMaraton() {
        setTitle("Generar Maratón");
        setLayout(new BorderLayout());
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelConfiguracion = new JPanel();
        panelConfiguracion.setLayout(new FlowLayout());
        panelConfiguracion.add(new JLabel("Número de películas:"));

        txtNumeroPeliculas = new JTextField(5);
        panelConfiguracion.add(txtNumeroPeliculas);

        btnGenerarMaraton = new JButton("Generar Maratón");
        panelConfiguracion.add(btnGenerarMaraton);
        add(panelConfiguracion, BorderLayout.NORTH);

        modeloListaPeliculas = new DefaultListModel<>();
        listaPeliculas = new JList<>(modeloListaPeliculas);
        listaPeliculas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaPeliculas.setCellRenderer(new PeliculaCellRenderer());
        listaPeliculas.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Pelicula seleccionada = listaPeliculas.getSelectedValue();
                if (seleccionada != null) {
                    new VentanaPelicula(seleccionada);
                }
            }
        });

        add(new JScrollPane(listaPeliculas), BorderLayout.CENTER);

        btnGenerarMaraton.addActionListener(e -> {
            try {
                int numPeliculas = Integer.parseInt(txtNumeroPeliculas.getText());
                List<Pelicula> maraton = generarMaraton(numPeliculas);
                mostrarResultado(maraton);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private List<Pelicula> generarMaraton(int numPeliculas) {
        List<Pelicula> maraton = new ArrayList<>();
        List<Pelicula> todasLasPeliculas = domain.Contenedora.getLPeliculas();

        if (todasLasPeliculas.isEmpty() || numPeliculas <= 0) {
            return maraton;
        }

        //primero aleatorio
        Pelicula actual = todasLasPeliculas.get((int) (Math.random() * todasLasPeliculas.size()));
        maraton.add(actual);

        while (maraton.size() < numPeliculas) {
            Pelicula siguiente = buscarRelacionada(actual, todasLasPeliculas, maraton);

            if (siguiente == null) {
                // Si no encuentra relacionadas, selecciona una nueva película aleatoria no repetida.
                siguiente = buscarNoRepetida(todasLasPeliculas, maraton);
            }

            if (siguiente != null) {
                maraton.add(siguiente);
                actual = siguiente;
            } else {
                break;
            }
        }

        return maraton;
    }

    private Pelicula buscarRelacionada(Pelicula actual, List<Pelicula> todasLasPeliculas, List<Pelicula> excluidas) {
        for (Pelicula p : todasLasPeliculas) {
            if (!excluidas.contains(p) && p.getGenero().equalsIgnoreCase(actual.getGenero())) {
                return p;
            }
        }
        return null;
    }

    private Pelicula buscarNoRepetida(List<Pelicula> todasLasPeliculas, List<Pelicula> excluidas) {
        for (Pelicula p : todasLasPeliculas) {
            if (!excluidas.contains(p)) {
                return p;
            }
        }
        return null;
    }

    private void mostrarResultado(List<Pelicula> maraton) {
        modeloListaPeliculas.clear();
        if (maraton.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se pudo generar una maratón.", "Error", JOptionPane.INFORMATION_MESSAGE);
        } else {
            for (Pelicula p : maraton) {
                modeloListaPeliculas.addElement(p);
            }
        }
    }

    private static class PeliculaCellRenderer extends DefaultListCellRenderer {

		private static final long serialVersionUID = 1L;

		@Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof Pelicula) {
                Pelicula pelicula = (Pelicula) value;
                setText(pelicula.getTitulo());
            }
            return this;
        }
    }
}
