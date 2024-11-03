package gui;

import java.awt.BorderLayout;
import java.awt.Font;
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

import domain.Contenedora;
import domain.Pelicula;
import main.Main;

public class VentanaPrincipal extends JFrame {
    
    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(Main.class.getName());
    
    private JMenuBar barraMenu;
    private JMenuItem itemCuenta, itemGenero1, itemGenero2, itemGenero3;
    private JMenu visualizarCuenta, visualizarPeliculas;
    private JPanel panelCentro, panelSur, panelNorte, panelEste, panelOeste;
    private JLabel labelTitulo;
    private JButton btnVolver;
    private JTable tablaPeliculas;

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
        
        barraMenu.add(visualizarCuenta);
        barraMenu.add(visualizarPeliculas);
        
        panelCentro = new JPanel(new BorderLayout());
        panelNorte = new JPanel(new BorderLayout());
        panelSur = new JPanel();
        panelEste = new JPanel();
        panelOeste = new JPanel();
        
        btnVolver = new JButton("Volver");
        
        labelTitulo = new JLabel("DeustoStream");
        labelTitulo.setFont(new Font(Font.SERIF, Font.BOLD, 40));
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
        
        ImageIcon imagen = new ImageIcon(getClass().getResource("/imagenes/deustoStreamLogo1.png"));
        setIconImage(imagen.getImage());
        setTitle("DeustoStream");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        configurarTablaPeliculas();
    }

    private void configurarTablaPeliculas() {
        ModeloTablaPeliculas modeloTabla = new ModeloTablaPeliculas();
        
        tablaPeliculas = new JTable(modeloTabla);
        tablaPeliculas.setRowHeight(200);
        tablaPeliculas.setDefaultRenderer(Object.class, new RenderTablaPeliculas());
        
        for (Pelicula pelicula : Contenedora.getLPeliculas()) {
            ImageIcon posterIcon = new ImageIcon(getClass().getResource(pelicula.getRutaFoto()));
            modeloTabla.addRow(new Object[]{posterIcon, pelicula.getTitulo()});
        }
        
        JScrollPane scrollTabla = new JScrollPane(tablaPeliculas);
        panelCentro.add(scrollTabla, BorderLayout.CENTER);
    }
}
