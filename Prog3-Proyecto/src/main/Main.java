package main;

import gui.VentanaArranque1;
import java.util.logging.Logger;

import domain.Contenedora;

import java.util.logging.Level;

public class Main {
	
	private static final Logger logger = Logger.getLogger(Main.class.getName());
	
	public static void main(String[] args) {
		// Logger
		logger.setLevel(Level.ALL);
		logger.log(Level.INFO, "Cargando DeustoStream");

		// Ejemplos de logger
		// logger.info("Se ha mostrado un mensaje en consola");
		// logger.warning("Mensaje de warning"); logger.severe("Mensaje de error");
		
		Contenedora.cargarPeliculas();
		
		VentanaArranque1 ventanaArranque = new VentanaArranque1();
	}
}
