package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class VentanaPrincipal extends JFrame {
    
    private static final long serialVersionUID = 1L;
    
    private JMenuBar barraMenu;
    private JMenuItem itemCuenta, itemPeliculas;
    private JMenu visualizarCuenta, visualizarPeliculas; //No se como hacerlo para que sea directo al pulsar el boton
    private JPanel panelCentro, panelSur, panelNorte, panelEste, panelOeste;
    private JLabel labelTitulo;
    private JButton btnVolver;
    
    public VentanaPrincipal() {
        
        barraMenu = new JMenuBar();
        
        visualizarCuenta = new JMenu("Cuenta");
        itemCuenta = new JMenuItem("Configurar cuenta");
        visualizarCuenta.add(itemCuenta);
        
        visualizarPeliculas = new JMenu("Peliculas");
        itemPeliculas = new JMenuItem("Peliculas");
        visualizarPeliculas.add(itemPeliculas);
        
        barraMenu.add(visualizarCuenta);
        barraMenu.add(visualizarPeliculas);
        
        panelCentro = new JPanel();
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
        });
        
        setTitle("DeustoStream");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
}
