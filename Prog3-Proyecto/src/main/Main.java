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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BaseDeDatos.initBD("baseDeDatos.db");
		BaseDeDatos.crearTablas(con);
		
		BaseDeDatos.anadirAdmin("09876543B", "Enrique", "Corres", "enrique.corres@opendeusto.es", "enrique123", "690875123", "098765432109");
	    BaseDeDatos.anadirAdmin("86750099W", "Iker", "Díez", "iker.diez.perez@opendeusto.es", "iker123", "654000999", "123412341234");
	    BaseDeDatos.anadirAdmin("36540119Y", "Haritz", "Carvalho", "haritz.carvalho@opendeusto.es", "haritz123", "600123556", "090987651234");
	    BaseDeDatos.anadirAdmin("2", "2", "2", "2", "2", "2", "2");
		
		Contenedora.cargarPeliculas();
		
		VentanaArranque ventanaArranque = new VentanaArranque();
		
		BaseDeDatos.closeBD(con);
	}
}
