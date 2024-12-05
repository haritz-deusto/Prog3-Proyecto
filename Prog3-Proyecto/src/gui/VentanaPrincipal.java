package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

import domain.Contenedora;
import domain.Pelicula;
import domain.Usuario;
import main.Main;

public class VentanaPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(Main.class.getName());
    
    private JMenuBar barraMenu;
    private JMenuItem itemCuenta, itemGenero1, itemGenero2, itemGenero3, itemGenero4, itemGenero5, itemGenero6, itemGenero7, itemBuscar, itemSorprendeme;
    private JMenu visualizarCuenta, visualizarPeliculas;
    private JPanel panelCentro, panelSur, panelNorte, panelEste, panelOeste; 
    private JLabel labelTitulo;
    private JButton btnVolver;
    private JTable tablaPeliculas;
    private TableRowSorter<ModeloTablaPeliculas> sorter;
    private List<Usuario> usuarios;

    public VentanaPrincipal() {
        barraMenu = new JMenuBar();
        
        visualizarCuenta = new JMenu("Cuenta");
        itemCuenta = new JMenuItem("Configurar cuenta");
        visualizarCuenta.add(itemCuenta);
        
        visualizarPeliculas = new JMenu("Géneros");
        itemGenero1 = new JMenuItem("Ciencia Ficción");
        itemGenero2 = new JMenuItem("Drama");
        itemGenero3 = new JMenuItem("Musical");
        itemGenero4 = new JMenuItem("Comedia");
        itemGenero5 = new JMenuItem("Animación");
        itemGenero6 = new JMenuItem("Fantasia");
        itemGenero7 = new JMenuItem("Romance");
        visualizarPeliculas.add(itemGenero1);
        visualizarPeliculas.add(itemGenero2);
        visualizarPeliculas.add(itemGenero3);
        visualizarPeliculas.add(itemGenero4);
        visualizarPeliculas.add(itemGenero5);
        visualizarPeliculas.add(itemGenero6);
        visualizarPeliculas.add(itemGenero7);
        
        itemBuscar = new JMenu("Buscar");
        
        itemSorprendeme = new JMenuItem("Sorpréndeme");
        
        barraMenu.add(visualizarCuenta);
        barraMenu.add(visualizarPeliculas);
        barraMenu.add(itemBuscar);
        barraMenu.add(itemSorprendeme);

        panelCentro = new JPanel(new BorderLayout());
        panelNorte = new JPanel(new BorderLayout());
        panelSur = new JPanel();
        panelEste = new JPanel();
        panelOeste = new JPanel();
        
        btnVolver = new JButton("Volver");
        
        labelTitulo = new JLabel("DeustoStream");
        labelTitulo.setFont(new Font("Serif", Font.BOLD, 40));
        labelTitulo.setHorizontalAlignment(SwingConstants.LEFT);
        
        getContentPane().add(panelNorte, BorderLayout.NORTH);
        getContentPane().add(panelEste, BorderLayout.EAST);
        getContentPane().add(panelOeste, BorderLayout.WEST);
        getContentPane().add(panelSur, BorderLayout.SOUTH);
        getContentPane().add(panelCentro, BorderLayout.CENTER);
        
        panelSur.add(btnVolver);
        panelNorte.add(labelTitulo, BorderLayout.NORTH);
        panelNorte.add(barraMenu, BorderLayout.CENTER);
        
        btnVolver.addActionListener(e -> dispose());
        
        itemGenero1.addActionListener(e -> filtrarPorGenero("CIENCIA_FICCION"));
        itemGenero2.addActionListener(e -> filtrarPorGenero("DRAMA"));
        itemGenero3.addActionListener(e -> filtrarPorGenero("MUSICAL"));
        itemGenero4.addActionListener(e -> filtrarPorGenero("COMEDIA"));
        itemGenero5.addActionListener(e -> filtrarPorGenero("ANIMACION"));
        itemGenero6.addActionListener(e -> filtrarPorGenero("FANTASIA"));
        itemGenero7.addActionListener(e -> filtrarPorGenero("ROMANCE"));
       
        itemCuenta.addActionListener(e -> {
            VentanaConfigurarCuenta ventanaCuenta = new VentanaConfigurarCuenta();
            ventanaCuenta.setVisible(true);
            logger.log(Level.INFO, "Se ha pulsado el botón volver");
        });
        
        itemBuscar.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				/*String titulo = JOptionPane.showInputDialog(null, "Ingrese el título de la película:", "Buscar Película", JOptionPane.PLAIN_MESSAGE);
	            if (titulo != null) {
	                buscarPelicula(titulo);
	                logger.log(Level.INFO, "Se ha buscado la pelicula: " + titulo);
	            }*/
				new InputDialog(tablaPeliculas);
				tablaPeliculas.repaint();
	        
			}
		});
        
        itemSorprendeme.addActionListener(e -> {
            List<Pelicula> peliculas = Contenedora.getLPeliculas();
            if (!peliculas.isEmpty()) {
                new VentanaSorprendeme(peliculas);
            } else {
                JOptionPane.showMessageDialog(this, "No hay películas disponibles.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        
        ImageIcon imagen = new ImageIcon(getClass().getResource("/imagenes/deustoStreamLogo1.png"));
        setIconImage(imagen.getImage());
        setTitle("DeustoStream");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        configurarTablaPeliculas();
        setVisible(true);
    }

    private void configurarTablaPeliculas() {
        ModeloTablaPeliculas modeloTabla = new ModeloTablaPeliculas();
        
        tablaPeliculas = new JTable(modeloTabla);
        tablaPeliculas.setRowHeight(200);
        tablaPeliculas.setFont(new Font("Arial", Font.PLAIN, 16));
        tablaPeliculas.setGridColor(Color.LIGHT_GRAY);
        tablaPeliculas.setDefaultRenderer(Object.class, new RenderTablaPeliculas());
        
        JTableHeader header = tablaPeliculas.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 18));
        header.setBackground(new Color(70, 130, 180));
        header.setForeground(Color.WHITE);
        header.setReorderingAllowed(false);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        tablaPeliculas.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        
        for (Pelicula pelicula : Contenedora.getLPeliculas()) {
            ImageIcon posterIcon = new ImageIcon(getClass().getResource(pelicula.getRutaFoto()));
            String tituloConGenero = pelicula.getTitulo() + " - " + pelicula.getGenero();
            modeloTabla.addRow(new Object[]{posterIcon, tituloConGenero});
        }
        
        sorter = new TableRowSorter<>(modeloTabla);
        tablaPeliculas.setRowSorter(sorter);
        
        tablaPeliculas.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                int row = tablaPeliculas.rowAtPoint(evt.getPoint());
                if (row >= 0) {
                	int modelRow = tablaPeliculas.convertRowIndexToModel(row);
                    Pelicula pelicula = Contenedora.getLPeliculas().get(modelRow);
                    new VentanaPelicula(pelicula).setVisible(true);
                    logger.log(Level.INFO, "Se ha pulsado el botón película para: " + pelicula.getTitulo() + ".");
                }
            }
        });
        
        JScrollPane scrollTabla = new JScrollPane(tablaPeliculas);
        scrollTabla.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelCentro.add(scrollTabla, BorderLayout.CENTER);
    }
    
    private void filtrarPorGenero(String genero) {
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + genero, 1));
        logger.log(Level.INFO, "Filtrando películas por género: " + genero);
    }
    
    private void buscarPelicula(String titulo) {
        if (titulo != null && !titulo.trim().isEmpty()) {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + titulo, 1));
        } else {
            sorter.setRowFilter(null); //elimina el filtro si no hay nada escrito
        }
    }
    
}