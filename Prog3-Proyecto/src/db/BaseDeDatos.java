package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import domain.Admin;
import domain.Cliente;
import domain.Comentario;
import domain.Contenedora;
import domain.Genero;
import domain.Pelicula;
import domain.Valoracion;
import main.Main;
import domain.Contenedora;
import domain.Usuario;


public class BaseDeDatos {
	public BaseDeDatos() {
	   }
	
	   public static Connection initBD(String nomBD) {
		      Connection con = null;

		      try {
		         Class.forName("org.sqlite.JDBC");
		         con = DriverManager.getConnection("jdbc:sqlite:ficheros/" + nomBD);
		      } catch (ClassNotFoundException var3) {
		         var3.printStackTrace();
		      } catch (SQLException var4) {
		         var4.printStackTrace();
		      }

		      return con;
		   }
	   
	   public static void closeBD(Connection con) {
		      if (con != null) {
		         try {
		            con.close();
		         } catch (SQLException var2) {
		            var2.printStackTrace();
		         }
		      }

		   }
	   
	   /**
	 * @param con
	 */
	public static void crearTablas(Connection con) {
		      String sql = "CREATE TABLE IF NOT EXISTS Cliente (email String, nombre String, apellido String,  dni String, contrasenia String, nunTel String, numtarjeta String)";
		      String sql2 = "CREATE TABLE IF NOT EXISTS Pelicula (titulo String, genero Genero, estrellas Valoracion, duracion String, descripcion String rutafoto String)";
		      String sql3 = "CREATE TABLE IF NOT EXISTS Admin (email String, nombre String, apellido String,  dni String, contrasenia String, nunTel String, numtarjeta String)";

		      try {
		         Statement st = con.createStatement();
		         st.executeUpdate(sql);
		         st.executeUpdate(sql2);
		         st.executeUpdate(sql3);
					/*
					 * Iterator var6 = Contenedora.getLPeliculas().iterator();
					 * 
					 * while(var6.hasNext()) { Pelicula p = (Pelicula)var6.next(); String nombre =
					 * p.getTitulo().replace(" ", ""); st.executeUpdate(String.
					 * format("CREATE TABLE IF NOT EXISTS Asientos%s (c1 String, c2 String, c3 String, c4 String, c5 String, c6 String, c7 String, c8 String, c9 String, c10 String)"
					 * , nombre)); }
					 */

		         st.close();
		      } catch (SQLException var8) {
		         var8.printStackTrace();
		      }

		   }
	   
	   
	

}
