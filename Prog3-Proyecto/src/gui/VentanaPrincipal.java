package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

import domain.Contenedora;
import domain.Pelicula;
import main.Main;

public class VentanaPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(Main.class.getName());
    
    private JMenuBar barraMenu;
    private JMenuItem itemCuenta, itemGenero1, itemGenero2, itemGenero3, itemBuscar;
    private JMenu visualizarCuenta, visualizarPeliculas;
    private JPanel panelCentro, panelSur, panelNorte, panelEste, panelOeste;
    private JLabel labelTitulo;
    private JButton btnVolver;
    private JTable tablaPeliculas;
    private TableRowSorter<ModeloTablaPeliculas> sorter;

    public VentanaPrincipal() {
        barraMenu = new JMenuBar();
        
        visualizarCuenta = new JMenu("Cuenta");
        itemCuenta = new JMenuItem("Configurar cuenta");
        visualizarCuenta.add(itemCuenta);
        
        visualizarPeliculas = new JMenu("Géneros");
        itemGenero1 = new JMenuItem("Género 1");
        itemGenero2 = new JMenuItem("Género 2");
        itemGenero3 = new JMenuItem("Género 3");
        visualizarPeliculas.add(itemGenero1);
        visualizarPeliculas.add(itemGenero2);
        visualizarPeliculas.add(itemGenero3);
        
        itemBuscar = new JMenuItem("Buscar");
        
        barraMenu.add(visualizarCuenta);
        barraMenu.add(visualizarPeliculas);
        barraMenu.add(itemBuscar);

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
        itemCuenta.addActionListener(e -> {
            VentanaCuenta ventanaCuenta = new VentanaCuenta();
            ventanaCuenta.setVisible(true);
            logger.log(Level.INFO, "Se ha pulsado el botón volver");
        });
        
        itemBuscar.addActionListener(e -> {
            String titulo = JOptionPane.showInputDialog(this, "Ingrese el título de la película:", "Buscar Película", JOptionPane.PLAIN_MESSAGE);
            if (titulo != null) {
                buscarPelicula(titulo);
                logger.log(Level.INFO, "Se ha buscado la pelicula: " + titulo);
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
                    Pelicula pelicula = Contenedora.getLPeliculas().get(row);
                    new VentanaPelicula(pelicula).setVisible(true);
                    logger.log(Level.INFO, "Se ha pulsado el botón película para: " + pelicula.getTitulo() + ".");
                }
            }
        });
        
        JScrollPane scrollTabla = new JScrollPane(tablaPeliculas);
        scrollTabla.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelCentro.add(scrollTabla, BorderLayout.CENTER);
    }
    
    private void buscarPelicula(String titulo) {
        if (titulo != null && !titulo.trim().isEmpty()) {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + titulo, 1));
        } else {
            sorter.setRowFilter(null); //elimina el filtro si no hay nada escrito
        }
    }
}