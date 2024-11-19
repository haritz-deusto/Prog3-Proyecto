package gui;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import domain.Contenedora;
import domain.Pelicula;

public class InputDialog extends JDialog{
	private JLabel lblTexto;
	private JTextField txtTexto;
	private JPanel pSur,pCentro;
	public InputDialog(JTable tabla) {
		setBounds(500, 400, 300, 100);
		txtTexto = new JTextField(20);
		lblTexto = new JLabel("Introduzca el título: ");
		pSur = new JPanel();
		pCentro = new JPanel();
		setLayout(new BorderLayout());
		pCentro.add(lblTexto);
		pSur.add(txtTexto);
		add(pCentro, BorderLayout.CENTER);
		add(pSur, BorderLayout.SOUTH);
		
		txtTexto.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				String texto = txtTexto.getText();
				ModeloTablaPeliculas modeloTabla = (ModeloTablaPeliculas) tabla.getModel();
				modeloTabla.setRowCount(0);
				for(Pelicula p : Contenedora.getLPeliculas()) {
					if(p.getTitulo().contains(texto)) {
						ImageIcon posterIcon = new ImageIcon(getClass().getResource(p.getRutaFoto()));
			            String tituloConGenero = p.getTitulo() + " - " + p.getGenero();
			            modeloTabla.addRow(new Object[]{posterIcon, tituloConGenero});
					}
				}
				tabla.setModel(modeloTabla);
				tabla.repaint();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				String texto = txtTexto.getText();
				ModeloTablaPeliculas modeloTabla = (ModeloTablaPeliculas) tabla.getModel();
				modeloTabla.setRowCount(0);
				for(Pelicula p : Contenedora.getLPeliculas()) {
					if(p.getTitulo().contains(texto)) {
						ImageIcon posterIcon = new ImageIcon(getClass().getResource(p.getRutaFoto()));
			            String tituloConGenero = p.getTitulo() + " - " + p.getGenero();
			            modeloTabla.addRow(new Object[]{posterIcon, tituloConGenero});
					}
				}
				tabla.setModel(modeloTabla);
				tabla.repaint();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		setVisible(true);
	}
}
