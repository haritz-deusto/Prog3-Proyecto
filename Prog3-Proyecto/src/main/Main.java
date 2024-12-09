package main;

import gui.VentanaArranque;
import java.util.logging.Logger;

import db.BaseDeDatos;
import domain.Contenedora;

import java.util.logging.Level;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
	
	private static final Logger logger = Logger.getLogger(Main.class.getName());
	
	public static void main(String[] args) throws SQLException {
		// Logger
		logger.setLevel(Level.ALL);
		logger.log(Level.INFO, "Cargando DeustoStream");

		// Ejemplos de logger
		// logger.info("Se ha mostrado un mensaje en consola");
		// logger.warning("Mensaje de warning"); logger.severe("Mensaje de error");
		Connection con = null;
		try {
			con = BaseDeDatos.initBD("baseDeDatos.db");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		BaseDeDatos.initBD("baseDeDatos.db");
		BaseDeDatos.crearTablas(con);
		
		
		Contenedora.cargarPeliculas();
		
		VentanaArranque ventanaArranque = new VentanaArranque();
		
		BaseDeDatos.closeBD(con);
	}
}
