package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import domain.Admin;
import domain.Cliente;

import domain.Genero;
import domain.Pelicula;
import domain.Valoracion;

import domain.Usuario;

public class BaseDeDatos {
	public BaseDeDatos() {
	}

	public static Connection initBD(String nomBD) throws SQLException {
		Connection con = null;

		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:ficheros/" + nomBD);
			/*
			 * } catch (ClassNotFoundException var3) { var3.printStackTrace(); } catch
			 * (SQLException var4) { var4.printStackTrace(); }
			 * 
			 * return con;
			 */

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public static void closeBD(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * @param con
	 */
	public static void crearTablas(Connection con) {
		String tablaUsuario = """
				    CREATE TABLE IF NOT EXISTS Usuarios (
				        dni TEXT PRIMARY KEY,
				        nombre TEXT NOT NULL,
				        apellido TEXT NOT NULL,
				        email TEXT UNIQUE NOT NULL,
				        num_telefono TEXT,
				        num_tarjeta TEXT,
				        contrasenia TEXT NOT NULL,
				        tipo_usuario TEXT CHECK(tipo_usuario IN ('ADMIN', 'CLIENTE')) NOT NULL
				    );
				""";

		String tablaGeneros = """
				    CREATE TABLE IF NOT EXISTS Generos (
				        idGenero INTEGER PRIMARY KEY AUTOINCREMENT,
				        genero TEXT UNIQUE NOT NULL
				    );
				""";

		String tablaPeliculas = """
				    CREATE TABLE IF NOT EXISTS Peliculas (
				        idPelicula INTEGER PRIMARY KEY AUTOINCREMENT,
				        titulo TEXT NOT NULL,
				        genero TEXT,
				        valoracion TEXT CHECK (valoracion IN ('UNA_ESTRELLA', 'DOS_ESTRELLAS', 'TRES_ESTRELLAS', 'CUATRO_ESTRELLAS', 'CINCO_ESTRELLAS')),
				        duracion INTEGER,
				        descripcion TEXT,
				        foto TEXT,
				        FOREIGN KEY (genero) REFERENCES Generos(genero)
				    );
				""";

		String tablaComentarios = """
				    CREATE TABLE IF NOT EXISTS Comentarios (
				        idComentario INTEGER PRIMARY KEY AUTOINCREMENT,
				        dni INTEGER NOT NULL,
				        idPelicula INTEGER NOT NULL,
				        titulo TEXT NOT NULL,
				        comentario TEXT,
				        fecha DATE DEFAULT CURRENT_DATE,
				        FOREIGN KEY (dni) REFERENCES Usuarios(dni),
				        FOREIGN KEY (idPelicula) REFERENCES Peliculas(idPelicula),
				        FOREIGN KEY (titulo) REFERENCES Peliculas(titulo)
				    );
				""";

		try {
			Statement st = con.createStatement();
			st.executeUpdate(tablaUsuario);
			System.out.println("Tabla 'Usuario' creada o ya existente.");
			st.executeUpdate(tablaPeliculas);
			System.out.println("Tabla 'Peliculas' creada o ya existente.");
			st.executeUpdate(tablaComentarios);
			System.out.println("Tabla 'Comentarios' creada o ya existente.");
			st.executeUpdate(tablaGeneros);
			System.out.println("Tabla 'Generos' creada o ya existente.");
			/*
			 * Iterator var6 = Contenedora.getLPeliculas().iterator();
			 * 
			 * while(var6.hasNext()) { Pelicula p = (Pelicula)var6.next(); String nombre =
			 * p.getTitulo().replace(" ", ""); st.executeUpdate(String.
			 * format("CREATE TABLE IF NOT EXISTS Asientos%s (c1 String, c2 String, c3 String, c4 String, c5 String, c6 String, c7 String, c8 String, c9 String, c10 String)"
			 * , nombre)); }
			 */

			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static boolean existeEmail(String email) {
		String sql = "SELECT 1 FROM Usuarios WHERE email = ?";
		try (Connection con = initBD("baseDeDatos.db"); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, email);
			try (ResultSet rs = ps.executeQuery()) {
				return rs.next(); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void anadirCliente(String dni, String nombre, String apellido, String email, String contrasenia,
			String numTel, String numTarjeta) {
		String sql = "INSERT INTO Usuarios (dni, nombre, apellido, email, num_telefono, num_tarjeta, contrasenia, tipo_usuario) VALUES (?, ?, ?, ?, ?, ?, ?, 'CLIENTE')";
		try (Connection con = initBD("baseDeDatos.db"); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, dni);
			ps.setString(2, nombre);
			ps.setString(3, apellido);
			ps.setString(4, email);
			ps.setString(5, numTel);
			ps.setString(6, numTarjeta);
			ps.setString(7, contrasenia);
			ps.executeUpdate();
			System.out.println("Usuario añadido correctamente.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Usuario obtenerUsuarioPorEmailYContrasenia(String email, String contrasenia) {
		Usuario usuario = null;

		String sql = "SELECT * FROM Usuarios WHERE email = ? AND contrasenia = ?";
		try (Connection con = initBD("baseDeDatos.db"); PreparedStatement pst = con.prepareStatement(sql)) {

			pst.setString(1, email);
			pst.setString(2, contrasenia);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				String dni = rs.getString("dni");
				String numTel = rs.getString("num_telefono");
				String numTarjeta = rs.getString("num_tarjeta");
				String tipoUsuario = rs.getString("tipo_usuario");

				if ("CLIENTE".equals(tipoUsuario)) {
					usuario = new Cliente(nombre, apellido, dni, email, contrasenia, numTel, numTarjeta, tipoUsuario);
				} else if ("ADMIN".equals(tipoUsuario)) {
					usuario = new Admin(nombre, apellido, dni, email, contrasenia, numTel, numTarjeta, tipoUsuario);
				}

			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return usuario;
	}

	public static boolean validarAdmin(String email, String contrasenia) {
		boolean esValido = false;
		String sql = "SELECT * FROM Usuarios WHERE email = ? AND contrasenia = ? AND tipo_usuario = 'ADMIN'";

		try (Connection con = initBD("baseDeDatos.db"); PreparedStatement pst = con.prepareStatement(sql)) {

			pst.setString(1, email);
			pst.setString(2, contrasenia);

			try (ResultSet rs = pst.executeQuery()) {
				esValido = rs.next(); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return esValido;
	}

	public static void anadirAdmin(String dni, String nombre, String apellido, String email, String contrasenia,
			String numTel, String numTarjeta) {
		String sql = "INSERT INTO Usuarios (dni, nombre, apellido, email, num_telefono, num_tarjeta, contrasenia, tipo_usuario) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, 'ADMIN')";

		try (Connection con = initBD("baseDeDatos.db"); PreparedStatement pst = con.prepareStatement(sql)) {

			pst.setString(1, dni);
			pst.setString(2, nombre);
			pst.setString(3, apellido);
			pst.setString(4, email);
			pst.setString(5, numTel);
			pst.setString(6, numTarjeta);
			pst.setString(7, contrasenia);

			pst.executeUpdate();
			System.out.println("Administrador añadido correctamente.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static List<Cliente> obtenerListaClientes() {
		List<Cliente> clientes = new ArrayList<>();
		String sql = "SELECT * FROM Usuarios WHERE tipo_usuario = 'CLIENTE'";

		try (Connection con = initBD("baseDeDatos.db");
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql)) {

			while (rs.next()) {
				String email = rs.getString("email");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				String dni = rs.getString("dni");
				String telefono = rs.getString("num_telefono");
				String tarjeta = rs.getString("num_tarjeta");
				String contrasenia = rs.getString("contrasenia");
				String tipoUsuario = rs.getString("tipo_usuario");


				clientes.add(new Cliente(email, nombre, apellido, dni, telefono, tarjeta, contrasenia, tipoUsuario));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return clientes;
	}

	public static List<Admin> obtenerListaAdmins() {
		List<Admin> admins = new ArrayList<>();
		String sql = "SELECT * FROM Usuarios WHERE tipo_usuario = 'ADMIN'";

		try (Connection con = initBD("baseDeDatos.db");
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql)) {

			while (rs.next()) {
				String email = rs.getString("email");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				String dni = rs.getString("dni");
				String telefono = rs.getString("num_telefono");
				String tarjeta = rs.getString("num_tarjeta");
				String contrasenia = rs.getString("contrasenia");
				String tipoUsuario = rs.getString("tipo_usuario");


				admins.add(new Admin(email, nombre, apellido, dni, telefono, tarjeta, contrasenia, tipoUsuario));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return admins;
	}

	public static List<Pelicula> obtenerListaPeliculas() {
		List<Pelicula> peliculas = new ArrayList<>();
		String sql = "SELECT * FROM Peliculas";

		try (Connection con = initBD("baseDeDatos.db");
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql)) {

			while (rs.next()) {
				peliculas.add(new Pelicula(rs.getInt("idPelicula"), rs.getString("titulo"), rs.getInt("duracion"),
						rs.getString("descripcion"), Genero.valueOf(rs.getString("genero")),
						Valoracion.valueOf(rs.getString("valoracion")), rs.getString("foto")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return peliculas;
	}

	public static boolean eliminarCliente(String email) {
		String sql = "DELETE FROM Usuarios WHERE email = ? AND tipo_usuario = 'CLIENTE'";

		try (Connection con = initBD("baseDeDatos.db"); PreparedStatement pst = con.prepareStatement(sql)) {

			pst.setString(1, email);
			int filasAfectadas = pst.executeUpdate();

			// Verificar si se eliminó el cliente
			if (filasAfectadas > 0) {
				System.out.println("Cliente con email " + email + " eliminado correctamente.");
				return true;
			} else {
				System.out.println("No se encontró el cliente con email " + email + " para eliminar.");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void anadirPelicula(String titulo, Genero genero, Valoracion estrellas, String duracion,
			String descripcion, String foto) {
		String sql = "INSERT INTO Peliculas (titulo, genero, valoracion, duracion, descripcion, foto) VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection con = initBD("baseDeDatos.db"); PreparedStatement pst = con.prepareStatement(sql)) {

			pst.setString(1, titulo);
			pst.setString(2, genero.name());
			pst.setString(3, estrellas.name());
			pst.setInt(4, Integer.parseInt(duracion));
			pst.setString(5, descripcion);
			pst.setString(6, foto);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void eliminarPelicula(String titulo) {
		String sql = "DELETE FROM Peliculas WHERE titulo = ?";

		try (Connection con = initBD("baseDeDatos.db"); PreparedStatement pst = con.prepareStatement(sql)) {

			pst.setString(1, titulo);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
